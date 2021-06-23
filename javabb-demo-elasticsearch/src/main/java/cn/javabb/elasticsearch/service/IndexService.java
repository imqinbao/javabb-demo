package cn.javabb.elasticsearch.service;

import cn.hutool.json.JSONUtil;
import cn.javabb.elasticsearch.model.ItemVO;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.WriteRequest;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.Strings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchPhraseQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @desc:
 * @author: javabb (javabob(a)163.com)
 * @create: 2021/06/23 16:55
 */
@Service
public class IndexService {
    @Autowired
    private RestHighLevelClient client;
    /**
     * 创建索引
     *
     * 本来应该先检查索引是否存在，存在则先删除
     * @param indexName
     * @return
     */
    public boolean createItemIndex(String indexName) {
        // 判断索引是否存在，存在就先删除
        if (isIndexExist(indexName)) return true;
        // 创建索引请求
        CreateIndexRequest indexRequest = new CreateIndexRequest(indexName);
        // 设置分片和副本数
        indexRequest.settings(Settings.builder()
                .put("index.number_of_shards", 5)
                .put("index.number_of_replicas", 1));
        // 可选参数，超时时间
        indexRequest.setTimeout(TimeValue.timeValueSeconds(2));

        // 字段和类型，可选
        /*indexRequest.mapping(
                "{\n" +
                        "  \"properties\": {\n" +
                        "    \"title\": {\n" +
                        "      \"type\": \"text\"\n" +
                        "    },\n" +
                        "    \"content\": {\n" +
                        "      \"type\": \"text\"\n" +
                        "    }\n" +
                        "    \"location\": {\n" +
                        "      \"type\": \"text\"\n" +
                        "    }\n" +
                        "  }\n" +
                        "}",
                XContentType.JSON);*/
        try {
            // 同步方式
            client.indices().create(indexRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            System.out.println("---创建ES索引失败---");
            e.printStackTrace();
        }
        return true;
    }

    public Map<String,Object> query(String indexName,String keyword) {
        GetRequest getRequest = new GetRequest(indexName);
        String[] includes = new String[]{"title", keyword};
        String[] excludes = Strings.EMPTY_ARRAY;
        FetchSourceContext fetchSourceContext = new FetchSourceContext(true, includes, excludes);
        GetRequest request = getRequest.fetchSourceContext(fetchSourceContext);
        try {
            GetResponse getResponse = client.get(request,RequestOptions.DEFAULT);
            return getResponse.getSource();

        } catch (Exception e) {

        }
        return null;
    }
    /**
     * 保存索引
     * @param indexName
     * @param itemVO
     */
    public void save(String indexName, ItemVO itemVO) {
        IndexRequest request = new IndexRequest(indexName);
        request.id(itemVO.getId());
        request.source(JSONUtil.toJsonStr(itemVO), XContentType.JSON);
        try {
            client.index(request, RequestOptions.DEFAULT);
        } catch (IOException e) {
            System.out.println("---插入ES失败---");
            e.printStackTrace();
        }
    }

    public void save(String indexName, Map<String, Object> map) {
        IndexRequest request = new IndexRequest(indexName);
        request.source(map);
        try {
            client.index(request, RequestOptions.DEFAULT);
        } catch (IOException e) {
            System.out.println("---插入ES失败---");
            e.printStackTrace();
        }
    }

    /**
     * 批量插入
     * @param indexName
     * @param itemVOList
     */
    public void batchSave(String indexName, List<ItemVO> itemVOList) {

        // 批量插入请求
        BulkRequest bulkRequest = new BulkRequest();
        bulkRequest.timeout("2m");
        // 写入完成立即刷新，否则会有延时，前端获取不到数据
        bulkRequest.setRefreshPolicy(WriteRequest.RefreshPolicy.IMMEDIATE);
        itemVOList.forEach(itemVO -> {
            IndexRequest indexRequest = new IndexRequest(indexName);
            indexRequest.source(JSONUtil.toJsonStr(itemVO), XContentType.JSON);
            bulkRequest.add(indexRequest);
        });
        try {
            client.bulk(bulkRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            System.out.println("---批量插入ES失败---");
            e.printStackTrace();
        }
    }

    
    public List<Map<String, Object>> highLightSearch(String indexName, String keyword, Integer pageNum, Integer pageSize) {
        List<Map<String, Object>> resList = new ArrayList<>();

        /**
         * 1. 创建 SearchRequest
         * Creates the SearchRequest. Without arguments this runs against all indices.
         *
         * 2. 创建 SearchSourceBuilder
         * Most search parameters are added to the SearchSourceBuilder. It offers setters for everything that goes into the search request body.
         *
         * 3. 在SearchSourceBuilder中设置搜索规则
         * Add a match_all query to the SearchSourceBuilder.
         *
         * 4. 将 SearchSourceBuilder 添加到 SearchRequest
         * Add the SearchSourceBuilder to the SearchRequest.
         *
         * 5. 执行搜索
         */
        String field1 = "title";
        String field2 = "content";
        // 1. 创建 SearchRequest
        SearchRequest searchRequest = new SearchRequest(indexName);

        // 2. 创建 SearchSourceBuilder
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        // 3. 在SearchSourceBuilder中设置搜索规则，
        // 使用term精确匹配
        // searchSourceBuilder.query(QueryBuilders.termQuery(field, keyword));
        // 使用term精确匹配时，中文查询失败，因此改用match匹配

        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
// 3.设置boolQueryBuilder条件
        MatchPhraseQueryBuilder matchPhraseQueryBuilder = QueryBuilders
                .matchPhraseQuery(field1, keyword);
        MatchPhraseQueryBuilder matchPhraseQueryBuilder2 = QueryBuilders
                .matchPhraseQuery(field2, keyword);
        // 子boolQueryBuilder条件条件，用should来表示查询条件or的关系
        BoolQueryBuilder childBoolQueryBuilder = new BoolQueryBuilder()
                .should(matchPhraseQueryBuilder)
                .should(matchPhraseQueryBuilder2);
// 4.添加查询条件到boolQueryBuilder中
        boolQueryBuilder.must(childBoolQueryBuilder);
        searchSourceBuilder.query(boolQueryBuilder);
        // 设置分页参数
        searchSourceBuilder.from((pageNum) * pageSize);
        searchSourceBuilder.size(pageSize);
        searchSourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
        // 设置高亮规则
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.field(field1);
        highlightBuilder.field(field2);
        highlightBuilder.requireFieldMatch(false);//是否多个字段都高亮
        highlightBuilder.preTags("<em><font class=\"skcolor_ljg\">");
        highlightBuilder.postTags("</font></em>");
        searchSourceBuilder.highlighter(highlightBuilder);

        // 4. 将 SearchSourceBuilder 添加到 SearchRequest
        searchRequest.source(searchSourceBuilder);
        try {
            // 5. 搜索
            SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);
            // 6. 查询到的文档
            SearchHit[] hits = response.getHits().getHits();
            for (SearchHit hit : hits) {
                // 获取到全部字段高亮部分
                Map<String, HighlightField> highlightFields = hit.getHighlightFields();
                Map<String, Object> source = hit.getSourceAsMap();
                if(highlightFields.containsKey(field1)){
                    // 获取 指定field部分高亮出的片段
                    Text[] fragments = highlightFields.get(field1).fragments();
                    // 拼装
                    StringBuilder builder = new StringBuilder();
                    for (Text text : fragments) {
                        builder.append(text);
                    }
                    // 用高亮后的结果取代原来字段
                    source.put(field1, builder.toString());
                }
                if(highlightFields.containsKey(field2)){
                    // 获取 指定field部分高亮出的片段
                    Text[] fragments = highlightFields.get(field2).fragments();
                    // 拼装
                    StringBuilder builder = new StringBuilder();
                    for (Text text : fragments) {
                        builder.append(text);
                    }
                    // 用高亮后的结果取代原来字段
                    source.put(field2, builder.toString());
                }
                // 将这条记录加入结果集
                resList.add(source);
            }
        } catch (IOException e) {
            System.out.println("===执行搜索失败===");
            e.printStackTrace();
        }
        return resList;
    }

    /**
     * 索引是否存在
     * @param index
     * @return
     */
    private boolean isIndexExist(String index) {
        GetIndexRequest request = new GetIndexRequest(index);
        try {
            boolean exists = client.indices().exists(request, RequestOptions.DEFAULT);
            if (exists) return true;
        } catch (IOException e) {
            System.out.println("===判断索引是否存在请求失败");
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 删除索引
     * @param indexName
     */
    public void deleteIndex(String indexName) {
        DeleteIndexRequest request = new DeleteIndexRequest(indexName);
        request.timeout("2m");
        try {
            AcknowledgedResponse delete = client.indices().delete(request, RequestOptions.DEFAULT);
        } catch (IOException e) {
            System.out.println("===删除索引失败===");
            e.printStackTrace();
        }
    }

    /**
     * 删除记录
     * @param indexName
     */
    public void deleteRecord(String indexName,String type,String id) {
        DeleteRequest deleteRequest=new DeleteRequest(indexName);
        try {
            client.delete(deleteRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            System.out.println("===删除数据失败===");
            e.printStackTrace();
        }
    }
}
