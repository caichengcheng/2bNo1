package redis.autoredis;

import springall.bean.EnglishBook;

public interface IAutoRedisBookService {
    EnglishBook getBook(long bookId);
}
