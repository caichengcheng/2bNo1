package redis.autoredis;

import springall.bean.EnglishBook;
import org.springframework.stereotype.Component;


@Component
public class AutoRedisBookDao implements IAutoRedisBookDao{
//     static final String keyPre = "集群名：业务：key前缀";

    @AutoSetRedis(keyPre=keyPre,operate = "get", unionParamIndex = 0)
    @Override
    public EnglishBook getBook(long bookId){
        System.out.println("进入getBook");
        //从dao获取数据
        System.out.println("查询DB获取数据开始");
        EnglishBook book = new EnglishBook();
        book.setPrice(100D);
        System.out.println("查询DB获取数据结束");
        return book;
    }
}
