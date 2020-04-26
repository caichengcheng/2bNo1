package springall.aop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BService {
    @Autowired
    IBusinessService service;
    public void doSomething(){
        service.doBusiness();
    }
}
