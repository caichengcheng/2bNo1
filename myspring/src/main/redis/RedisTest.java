package redis;

import org.junit.Test;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * @author caichengcheng
 * date:2020-04-07
 */
public class RedisTest {
    public static void main(String[] args) {
        StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();
        stringRedisTemplate.executePipelined(new SessionCallback<Object>() {
            @Override
            public <K, V> Object execute(RedisOperations<K, V> redisOperations) throws DataAccessException {
                return null;
            }
        });

    }
    @Test
    public void testRedission(){
        RedissonClient redissonClient = Redisson.create(new Config());
        RLock lock = redissonClient.getLock("");
        lock.tryLock();
        lock.unlock();
    }
}
