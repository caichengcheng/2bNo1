package bean;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 用于测试 lookup - method
 * @author caichengcheng
 * date:2020-01-10
 */
public abstract class Book {
    protected abstract void desc();

    public static void main(String[] args) {
        ClassPathXmlApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext.xml");
        BookTest bookTest = ioc.getBean("bookTest", BookTest.class);
        Book book = bookTest.getBook();
        System.out.println(book);
        System.out.println(bookTest.getBook());
        System.out.println(bookTest);
        System.out.println(bookTest.getBook());
        System.out.println(bookTest);
        System.out.println(bookTest.getBook());
        System.out.println(bookTest);
    }
}
