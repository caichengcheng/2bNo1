package redis.autoredis;

import bean.EnglishBook;

public interface IAutoRedisBookService {
    EnglishBook getBook(long bookId);
}
