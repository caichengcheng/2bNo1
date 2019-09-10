package springframwork;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.MessageService;

/**
 * create by caichengcheng
 * date:2019-09-02
 */
public class SpringTest {

    @Test
    public void ioc(){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");

        System.out.println("context 启动成功");

        // 从 context 中取出我们的 Bean，而不是用 new MessageServiceImpl() 这种方式
        MessageService messageService = applicationContext.getBean(MessageService.class);
        // 这句将输出: hello world
        System.out.println(messageService.getMessage());
    }
}
