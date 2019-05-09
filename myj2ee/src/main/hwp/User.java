package hwp;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * create by caichengcheng
 * date:2019-04-06
 */
public class User {
    private String name;
    private String password;
    private int age;
    private User(String name,String password,int age){
        this.name = name;
        this.password = password;
        this.age = age;
    }

    public User() {
    }

    public String getName() {
        return name;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public int getAge() {
        return age;
    }

    public User setAge(int age) {
        this.age = age;
        return this;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> strings = new ArrayList<>();
        String numStr = scanner.next();
        Integer num = Integer.valueOf(numStr)+1;
        for(int i=0 ;i<num && scanner.hasNext() ;i++ ){
            String next = scanner.next();
            strings.add(next);
        }
        String last = strings.get(num - 1);
//        System.out.println("----"+last);
        String text = "";
        Integer count = new Integer(0);
        boolean flag = false;
        for(int i=0;i<last.length();i++){
            text=text+last.toCharArray()[i];
            System.out.println("子串："+text);
            for(int j=0;j<num-1;j++){
                if(strings.get(j).equals(text)){
                    System.out.println("子串匹配："+text);
                    flag = true;
                    break;
                }
            }
            if(flag){
                flag = false;
                count++;
                text = "";
            }
        }
        System.out.println(count);
    }

    static void flag(Integer count,String t,ArrayList<String> strings){
        String text = "";
        boolean flag = false;
        for(int i=0;i<t.length();i++){
            text=text+t.toCharArray()[i];
            System.out.println("子串："+text);
            for(int j=0;j<strings.size()-1;j++){
                if(strings.get(j).equals(text)){
                    System.out.println("子串匹配："+text);
                    flag = true;
                    break;
                }
            }
            if(flag){
                flag = false;
                count++;
                text = "";
            }
        }

    }
}
