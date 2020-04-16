package redis.autoredis;

import bean.EnglishBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AutoRedisBookService implements IAutoRedisBookService{
    @Autowired
    IAutoRedisBookDao dao;
    @Override
    public EnglishBook getBook(long bookId){
        return dao.getBook(bookId);
    }
}
