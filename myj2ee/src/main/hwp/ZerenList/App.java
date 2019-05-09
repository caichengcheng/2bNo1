package hwp.ZerenList;

import sun.applet.Main;

/**
 * create by caichengcheng
 * date:2019-04-07
 */
public class App {
    public static void main(String [] args){
        Context context = new Context();

        context.setLocationUser(false);

        context.setNewUser(false);
        RulerHandler isLocation = new IsLocationUser();
        RulerHandler isNewUer = new NewUser();
        isNewUer.setSuccessor(isLocation);
        //不检验是不是新用户,直接跳过新用户节点，
        //与用一个list来存放所有的规则相比较，这样的方式，更加简便，可以直接跳过某些步骤，但是不能跳过中间的步骤
        isLocation.apply(context);
    }
}
