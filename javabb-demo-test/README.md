# javabb-demo-test



包含内容

- **Junit**
- **SpringbootTest**
- **Mockito**



## Junit

### 相关资料

https://www.jianshu.com/p/a3fa5d208c93



### 基本用法

引入依赖

~~~xml
<!-- https://mvnrepository.com/artifact/junit/junit -->
<dependency>
    <groupId>junit</groupId>
    <artifactId>junit</artifactId>
    <version>4.12</version>
    <scope>test</scope>
</dependency>
~~~

测试代码和生成代码分开放置，Maven默认目录正好符号这个要求。



### Junit注解

`@Test`:这个注释说明依附在 JUnit 的 public void 方法可以作为一个测试案例。

`@Before`:有些测试在运行前需要创造几个相似的对象。在 public void 方法加该注释是因为该方法需要在 test 方法前运行。

`@After`:如果你将外部资源在 Before 方法中分配，那么你需要在测试运行后释放他们。在 public void 方法加该注释是因为该方法需要在 test 方法后运行。

`@BeforeClass`:在 public void 方法加该注释是因为该方法需要在类中所有方法前运行。

`@AfterClass`:它将会使方法在所有测试结束后执行。这个可以用来进行清理活动。

`@Ignore`:这个注释是用来忽略有关不需要执行的测试的。

### Junit断言

~~~java
// 判断条件为真
Assert.assertTrue(true);
// 判断条件为真，不为真返回错误message
Assert.assertTrue("错误", true);
// 与assertFalse相反
Assert.assertFalse(false);
// 判断条件为空，只能为null，为“”，0，“ ”都不行
Assert.assertNull(null);
// 判断两个条件是否相等
Assert.assertEquals("javabb","javabb");
// 判断两个参数指向的对象是否一样
String a = "javabb";
String b = "javabb";
String c = new String("javabb");
Assert.assertSame(a, b);
Assert.assertNotSame(a,c);
// 判断两个数组是否相等，下面的如果换了元素的位置就不相等了。
Assert.assertArrayEquals(new int[]{1,2,3},new int[]{1,2,3});
//Assert.assertArrayEquals(new int[]{1,2,3},new int[]{3,2,1});
~~~

