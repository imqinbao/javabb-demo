package cn.javabb.poi.easypoi.test.excel;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.params.ExcelExportEntity;
import cn.hutool.core.collection.CollUtil;
import cn.javabb.poi.easypoi.model.UserInfo;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

/**
 * @desc:
 * @author: javabb (javabob(a)163.com)
 * @create: 2021/06/29 21:27
 */
public class Export {
    /*
     * api地址:http://doc.wupaas.com/docs/easypoi/easypoi-1c0u97casmdlh
     * */

    /**
     * 封装成model导出
     *
     * @throws IOException
     */
    @Test
    public void modelExportTest() throws IOException {
        UserInfo user1 = new UserInfo();
        user1.setId(1L);
        user1.setUserName("张三");
        user1.setAddress("湖北武汉");
        user1.setBirthday(new Date());
        user1.setXiaoxue("武汉第一小学");
        user1.setChuzhong("武汉三中");

        UserInfo user2 = new UserInfo();
        user2.setId(1L);
        user2.setUserName("李四");
        user2.setAddress("北京大兴");
        user2.setBirthday(new Date());
        user2.setXiaoxue("北京第一小学");
        user2.setChuzhong("北京100中");
        ArrayList<UserInfo> list = CollUtil.newArrayList(user1, user2);
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("用户列表", "用户列表"),
                UserInfo.class, list);
        // 要确保D:/temp/文件夹存在
        FileOutputStream fos = new FileOutputStream("D:/temp/ExcelExportForModel.xls");
        workbook.write(fos);
        fos.close();
    }

    /**
     * Map数据导出到指定文件
     */
    @Test
    public void mapExportTest() {
        try {
            List<ExcelExportEntity> entityList = new ArrayList<ExcelExportEntity>();
            //构造对象等同于@Excel,一个ExcelExportEntity就是一个列
            ExcelExportEntity excelentity = new ExcelExportEntity("Id", "id");
            excelentity.setWidth(20);
            entityList.add(excelentity);
            entityList.add(new ExcelExportEntity("姓名", "userName"));
            entityList.add(new ExcelExportEntity("地址", "address"));
            excelentity = new ExcelExportEntity("生日", "birthday");
            excelentity.setFormat("yyyy-MM-dd HH:mm:ss");
            excelentity.setWidth(20);
            entityList.add(excelentity);
            //构造List等同于@ExcelCollection
            List<Map<String, Object>> params = new ArrayList<Map<String, Object>>();
            Map<String, Object> param1 = new HashMap<>();
            param1.put("id", 100010);
            param1.put("userName", "张三");
            param1.put("address", "湖北武汉");
            param1.put("birthday", new Date());
            Map<String, Object> param2 = new HashMap<>();
            param2.put("id", 100011);
            param2.put("userName", "李四");
            param2.put("address", "湖南长沙");
            param2.put("birthday", new Date());
            //把我们构造好的bean对象放到params就可以了
            params.add(param1);
            params.add(param2);
            Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("用户信息", "用户清单"), entityList,
                    params);
            FileOutputStream fos = new FileOutputStream("D:/temp/ExcelExportForMap.xls");
            workbook.write(fos);
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
