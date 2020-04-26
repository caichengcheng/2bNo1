package redis.autoredis;

import springall.bean.EnglishBook;

public interface IAutoRedisBookDao {
    String keyPre = "集群名：业务：key前缀";
//    @AutoSetRedis(keyPre=keyPre,operate = "get", unionParamIndex = 0)
    EnglishBook getBook(long bookId);
}
