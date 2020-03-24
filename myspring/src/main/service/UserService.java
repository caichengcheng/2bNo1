package service;

import org.springframework.stereotype.Service;

/**
 * 用于debug 注解扫描源码
 * create by caichengcheng
 * date:2019-10-09
 */
@Service
public class UserService implements IUserService {
    @Override
    public void say() {
        System.out.println("i am user");
    }
}
