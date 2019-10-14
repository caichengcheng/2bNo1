package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * create by caichengcheng
 * date:2019-09-02
 */
@Service
public class MessageServiceImpl implements MessageService {
//    @Autowired
//    private IUserService userService;
    @Override
    public String getMessage() {
        return "hello world";
    }

}
