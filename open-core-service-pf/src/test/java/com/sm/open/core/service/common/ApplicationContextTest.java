package com.sm.open.core.service.common;

import org.junit.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

/**
 * @ClassName: ApplicationContextTest
 * @Description: 自定义测试基类，其他测试类均可继承此基类
 */
@ContextConfiguration(locations = { "classpath:spring/opencore_service_server.xml" })
public class ApplicationContextTest extends AbstractTransactionalJUnit4SpringContextTests {
	
	public ApplicationContextTest(){
		System.out.println("2. 第二步执行");
	}
	
	@BeforeClass
	public static void setUpClass() throws Exception {
		System.out.println("1. 第一步执行");
	}
	
	@AfterClass
	public static void tearDownClass() throws Exception {
		System.out.println("5. 第五步执行");
	}
	
	@Before
	public void setUp() {
		System.out.println("3. 第三步执行");
	}
	
	@After
	public void tearDown() {
		System.out.println("4. 第四步执行");
	}
	
	@Test
	public void testMapping() {
		System.out.println("【测试正文内容】测试基类，在第三步与第四步之间执行");
	}
	
}
