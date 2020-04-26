package springall.aop;

import org.springframework.stereotype.Service;

@Service
public class BusinessService implements IBusinessService{
    @Override
    public void doBusiness() {
        checkPrarm();
        System.out.println("进入doBusiness");
    }

    @Override
    public void checkPrarm() {
        System.out.println("进入checkparam");
    }
}
