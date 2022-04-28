package cn.javabb.elasticsearch.util;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

/**
 * @desc:
 * @author: javabb (javabob(a)163.com)
 * @create: 2022/04/10 21:03
 */
public class ESQueryUtil {

    public static void notEmptyQuery(BoolQueryBuilder boolQueryBuilder, String key) {
        boolQueryBuilder.must(QueryBuilders.wildcardQuery(key, ""));
    }

    public static void notQuery(BoolQueryBuilder boolQueryBuilder, String key) {
        boolQueryBuilder.must(QueryBuilders.existsQuery(key));
    }



}
