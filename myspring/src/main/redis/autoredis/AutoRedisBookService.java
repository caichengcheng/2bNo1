package redis.autoredis;

import springall.bean.EnglishBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AutoRedisBookService implements IAutoRedisBookService{
    @Autowired
    AutoRedisBookDao dao;
    @Override
    public EnglishBook getBook(long bookId){
        return dao.getBook(bookId);
    }

}
