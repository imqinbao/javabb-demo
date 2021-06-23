package cn.javabb.elasticsearch.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @desc:
 * @author: javabb (javabob(a)163.com)
 * @create: 2021/06/23 16:28
 */
@Configuration
public class EsClientConfig {

    @Value("${spring.es.url}")
    private String url;
    @Value("${spring.es.scheme}")
    private String scheme;
    @Value("${spring.es.port}")
    private Integer port;
    @Bean
    public RestHighLevelClient highLevelClient(){
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost(url, port, scheme)));
        return client;
    }
}
