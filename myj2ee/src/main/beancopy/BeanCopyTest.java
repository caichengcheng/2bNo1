package beancopy;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

/**
 * create by caichengcheng
 * date:2019-10-16
 */
public class BeanCopyTest {
    /**
     * 浅拷贝：通过clone方法复制时，对象的属性值的复制通过复制引用的方式完成的对象的复制就叫浅复制
     * 深拷贝：深复制就是复制一个完全不同的对象出来，没有任何关联，只是它们的属性值都是相同的，后续
     *        对任何一个的修改或其他操作都不会对另外一个有影响！本文是通过对象流的读写来完成深复制的
     */



    @Test
    public void test1(){
        //浅拷贝
        Student studentA = new Student();
        studentA.setName("老王");
        Subject subjectA = new Subject();
        subjectA.setName("语文");
        studentA.setSubject(subjectA);
        Student studentB = (Student)studentA.clone();
        System.out.println(studentA == studentB); //false ,可以证明，浅拷贝出来的对象和原对象地址不一样
        System.out.println(studentA.getSubject() == studentB.getSubject()); //ture 对于属性，引用数据类型复制的是地址，基本数据类型复制的是值
    }

    @Test
    public void test2(){
        //多层嵌套浅拷贝
        Student studentA = new Student();
        studentA.setName("老王");
        Friend friendA = new Friend();
        friendA.setBestFriendName("小王");
        studentA.setFriend(friendA);
        Student studentB = (Student)studentA.clone();
        System.out.println(studentA == studentB); //false
        System.out.println(studentA.getFriend() == studentB.getFriend()); //false ,嵌套浅拷贝，实现属性的clone
    }

    @Test
    public void test3() throws Exception {
        //深拷贝
        Student studentA = new Student();
        studentA.setName("老王");
        Friend friendA = new Friend();
        friendA.setBestFriendName("小王");
        studentA.setFriend(friendA);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        objectOutputStream.writeObject(studentA);

        ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        Student studentB = (Student) objectInputStream.readObject();

        System.out.println(studentA == studentB); //false
        System.out.println(studentA.getFriend() == studentB.getFriend());//false

        outputStream.close();
        objectOutputStream.close();
        inputStream.close();
        objectInputStream.close();
    }

}
