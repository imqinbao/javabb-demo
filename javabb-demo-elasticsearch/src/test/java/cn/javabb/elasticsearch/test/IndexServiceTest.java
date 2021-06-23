package cn.javabb.elasticsearch.test;

import cn.javabb.elasticsearch.constants.EsConst;
import cn.javabb.elasticsearch.model.ItemVO;
import cn.javabb.elasticsearch.service.IndexService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

/**
 * @desc:
 * @author: javabb (javabob(a)163.com)
 * @create: 2021/06/23 17:24
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class IndexServiceTest {

    @Autowired
    private IndexService indexService;

    @Before
    public void before() {
        indexService.createItemIndex(EsConst.INDEX_NAME);
    }

    @Test
    public void testCreate() {
        ItemVO item = ItemVO.builder().id("1")
                .content("java是一门面向对象编程语言，不仅吸收了C++语言的各种优点，还摒弃了C++里难以理解的多继承、指针等概念，因此Java语言具有功能强大和简单易用两个特征。")
                .title("java")
                .build();
        indexService.save(EsConst.INDEX_NAME,item);
    }

    @Test
    public void testQuery() {
        List<Map<String, Object>> maps = indexService.highLightSearch(EsConst.INDEX_NAME, "语言", 1, 10);
        for (Map<String, Object> map : maps) {
            System.out.println(map);
        }
    }


}
