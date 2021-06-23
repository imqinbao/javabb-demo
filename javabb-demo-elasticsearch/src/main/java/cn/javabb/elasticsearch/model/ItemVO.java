package cn.javabb.elasticsearch.model;

import cn.javabb.elasticsearch.constants.EsConst;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * @author wangwei
 * 2020/7/9 22:47
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = EsConst.INDEX_NAME,type = EsConst.TYPE_NAME)
public class ItemVO {
    //编号
    private String id;
    // 标题
    private String title;
    // 内容
    private String content;
    //来源:文件/数据库
    private String source;
    // 类型
    private String type;
    //位置
    private String location;
}
