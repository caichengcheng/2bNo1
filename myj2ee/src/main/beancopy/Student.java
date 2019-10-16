package beancopy;

import java.io.Serializable;

/**
 * create by caichengcheng
 * date:2019-10-16
 */
public class Student implements Cloneable, Serializable {
    private String name;
    private Subject subject;
    private Friend friend;

    public Friend getFriend() {
        return friend;
    }

    public void setFriend(Friend friend) {
        this.friend = friend;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }
    //浅拷贝
//    @Override
//    public Object clone(){
//        try {
//            return super.clone();
//        } catch (CloneNotSupportedException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

    //多层浅拷贝，可以达到深拷贝的效果
    @Override
    public Object clone(){
        try {
            Student student = (Student)super.clone();
            student.setFriend( (Friend) student.getFriend().clone());
            return student;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
