
# RocketMQ介绍  
RocketMQ 是一款开源的分布式消息系统，基于高可用分布式集群技术，提供低延时的、高可靠的消息发布与订阅服务。同时，广泛应用于多个领域，包括异步通信解耦、企业解决方案、金融支付、电信、电子商务、快递物流、广告营销、社交、即时通信、移动应用、手游、视频、物联网、车联网等。

具备如下特点:
- 能够保证严格的消息顺序
- 提供丰富的消息拉取模式
- 高效的订阅者水平扩展能力
- 实时的消息订阅机制
- 亿级消息堆积能力

# 下载和安装
## 下载地址
中文文档: https://github.com/apache/rocketmq/tree/master/docs/cn
下载地址: https://mirrors.tuna.tsinghua.edu.cn/apache/rocketmq/4.8.0/rocketmq-all-4.8.0-bin-release.zip

## 配置环境变量
~~~
ROCKETMQ_HOME=D:\Software\rocketmq-all-4.8.0-bin-release
~~~
## 启动
在mq安装bin目录下 cmd 执行
~~~
# 启动NAMESERVER
start mqnamesrv.cmd
# 启动BROKER
start mqbroker.cmd -n 127.0.0.1:9876 autoCreateTopicEnable=true
~~~
>如果启动BROKER的时候,提示找不到主类,可以修改mq安装目录`bin`下的`runbroker.cmd`  
>修改之前:  
>set CLASSPATH=.;%BASE_DIR%conf;%CLASSPATH%  
>修改之后  
>set CLASSPATH=.;%BASE_DIR%conf;"%CLASSPATH%"  



demo主要功能:
> Producer 三种发送消息的方式。  
> Producer 发送顺序消息，Consumer 顺序消费消息。  
> Producer 发送定时消息。  
> Producer 批量发送消息。  
> Producer 发送事务消息。  
> Consumer 广播和集群消费消息。  


