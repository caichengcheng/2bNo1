package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * create by caichengcheng
 * date:2019-09-02
 */
@Service
public class MessageServiceImpl implements MessageService {
    /**
     * 用于debug 在初始化过程中，对Autowired 属性的装配
     * @return
     */
    @Autowired
    private IUserService userService;
    @Override
    public String getMessage() {
        new Thread(()->{userService.say();}).start();
        return "hello world";
    }

}
