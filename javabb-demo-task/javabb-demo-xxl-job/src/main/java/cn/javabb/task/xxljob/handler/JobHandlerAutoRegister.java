package cn.javabb.task.xxljob.handler;

import cn.hutool.json.JSONUtil;
import cn.javabb.task.xxljob.annotation.XxlJobAutoRegistry;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.util.XxlJobRemotingUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @desc:
 * @author: javabb (javabob(a)163.com)
 * @create: 2022/04/04 00:54
 */
@Slf4j
@Component
public class JobHandlerAutoRegister implements ApplicationContextAware {

    private ApplicationContext applicationContext;
    @Value("${xxl.job.admin.address:xxx}")
    private String adminAddress;
    @Value("${xxl.job.accessToken:xxx}")
    private String accessToken;

    public JobHandlerAutoRegister() {
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
        this.initJob();
    }

    private void initJob() {
        String[] beanDefinitionNames = this.applicationContext.getBeanDefinitionNames();
        String[] var = beanDefinitionNames;
        int beanLength = beanDefinitionNames.length;

        for (int i = 0; i < beanLength; i++) {
            String beanDefinitionName = var[i];
            Object bean = this.applicationContext.getBean(beanDefinitionName);
            Method[] methods = bean.getClass().getDeclaredMethods();
            Method[] copyMethods = methods;
            Arrays.asList(copyMethods).stream().parallel().forEach(method->{
                XxlJobAutoRegistry annotation = (XxlJobAutoRegistry) AnnotationUtils.findAnnotation(method, XxlJobAutoRegistry.class);
                if (annotation != null) {
                    Map<String, Object> stringMultiValueMap = new HashMap<>(16);
                    stringMultiValueMap.put("jobGroupName", annotation.jobGroupName());
                    stringMultiValueMap.put("scheduleType", annotation.scheduleType());
                    stringMultiValueMap.put("scheduleConf", annotation.scheduleConf());
                    stringMultiValueMap.put("jobDesc", annotation.jobDesc());
                    stringMultiValueMap.put("author", annotation.author());
                    stringMultiValueMap.put("alarmEmail", annotation.alarmEmail());
                    stringMultiValueMap.put("executorHandler", annotation.executorHandler());
                    stringMultiValueMap.put("executorRouteStrategy", annotation.executorRouteStrategy());
                    stringMultiValueMap.put("executorTimeout", annotation.executorTimeout());
                    stringMultiValueMap.put("executorFailRetryCount", annotation.executorFailRetryCount());
                    stringMultiValueMap.put("executorBlockStrategy", annotation.executorBlockStrategy());
                    stringMultiValueMap.put("glueType", annotation.glueType());
                    log.info("=======registry xxl-job jsonStr,{}", JSONUtil.toJsonStr(stringMultiValueMap));
                    ReturnT<String> returnT = XxlJobRemotingUtil.postBody(this.adminAddress + "/jobinfo/addAndStart", this.accessToken,5,stringMultiValueMap,String.class);
                    log.info("=======registry xxl-job finish,{}", returnT.getMsg());
                }
            });
        }
    }

}
