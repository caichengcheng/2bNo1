package redis.autoredis;

import org.springframework.context.annotation.EnableAspectJAutoProxy;
import springall.bean.EnglishBook;
import com.alibaba.fastjson.JSON;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("redis")
@EnableAspectJAutoProxy
public class AutoRedisTest {
    public static void main(String[] args) {
//        ClassPathXmlApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext.xml");
        //这里以注解形式启动ioc
        AnnotationConfigApplicationContext ioc = new AnnotationConfigApplicationContext(AutoRedisTest.class);
        IAutoRedisBookService bean = ioc.getBean(IAutoRedisBookService.class);
        EnglishBook book = bean.getBook(12345L);
        System.out.println(JSON.toJSONString(book));
        System.out.println("=========");
        System.out.println(JSON.toJSONString(bean.getBook(12345L)));
    }
}
