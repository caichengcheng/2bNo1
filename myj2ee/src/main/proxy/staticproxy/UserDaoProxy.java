package proxy.staticproxy;

import proxy.IUserDao;

/**
 * 静态代理
 * 特点：
 *   1.目标对象必须实现接口
 *   2.代理对象也要实现接口，并在接口内部调用目标对象所实现的接口
 * @author caichengcheng
 *         time: 2019/2/18.
 */
public class UserDaoProxy implements IUserDao{
    private IUserDao target = new UserDao();

    @Override
    public void save() {
        System.out.println("代理操作 - 开启事务");
        target.save();
        System.out.println("代理操作 - 提交事务");
    }

    @Override
    public void find() {
        System.out.println("代理操作 - 开启事务");
        target.find();
        System.out.println("代理操作 - 提交事务");
    }

    public static void main(String[] args) {
        UserDaoProxy proxy = new UserDaoProxy();
        proxy.find();
    }
}
