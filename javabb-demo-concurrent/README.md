## threadpool

1.异步/同步  
2.多线程执行

## EventBus

EventBus是Guava的事件处理机制，是设计模式中的观察者模式（生产/消费者编程模型）的优雅实现。
对于事件监听和发布订阅模式，EventBus非常优雅使用起来也非常的简单,这个可不是吹的是真的非常的简单。

**EventBus的使用注意问题**  
代码可读性很差，项目中使用的时候，从post的地方，查询handle使用，都是使用ide的搜索服务，问题很难定位，不如普通的接口调用方便查询
由于EventBus是将消息队列放入到内存中的，listener消费这个消息队列，故系统重启之后，保存或者堆积在队列中的消息丢失

## CompletableFuture
JDK1.8中的CompletableFuture为我们提供了异步函数式编程,CompletableFuture提供了非常强大的Future的扩展功能，可以帮助我们简化异步编程的复杂性，提供了函数式编程的能力，可以通过回调的方式处理计算结果，并且提供了转换和组合CompletableFuture的方法。


