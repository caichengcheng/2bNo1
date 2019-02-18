package proxy.staticproxy;

import proxy.IUserDao;

/**
 * 静态代理的目标对象
 * @author caichengcheng
 *         time: 2019/2/18.
 */
public class UserDao implements IUserDao {
    @Override
    public void save() {
        System.out.println("模拟：保存用户信息");
    }

    @Override
    public void find() {
        System.out.println("模拟：获取用户信息");
    }
}
