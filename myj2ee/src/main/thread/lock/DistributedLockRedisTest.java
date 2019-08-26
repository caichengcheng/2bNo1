package thread.lock;

import org.junit.Test;
import org.redisson.Redisson;
import org.redisson.RedissonRedLock;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * 分布式锁
 * create by caichengcheng
 * date:2019-08-26
 */
public class DistributedLockRedisTest {
    /**
     * 单机redis下的锁
     */
    @Test
    private void redis(){
        Config config = new Config();
        //1、基于代码初始化
        //单机模式下：初始化config
        config.useClusterServers()
                .addNodeAddress("redis://127.0.0.1:7181");

        //2、基于配置文件初始化
        try {
            config = Config.fromYAML(new File("config-file.yaml"));
        } catch (IOException e) {
            e.printStackTrace();
        }


        RedissonClient redisson = Redisson.create(config);

        RLock lock = redisson.getLock("lockkey");
        //这个貌似是阻塞的？
        lock.lock(10, TimeUnit.SECONDS);
        lock.unlock();

        boolean lockSuccess = false;
        try {
            lockSuccess = lock.tryLock(500, 10, TimeUnit.SECONDS);
            if(lockSuccess){
                //获取锁成功
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }

    }

    /**
     * 哨兵redis下的锁，集群和哨兵类似
     */
    @Test
    private void redisSentinal(){
        //哨兵模式下：初始化config
        Config config1 = new Config();
        config1.useSingleServer().setAddress("redis://172.29.1.180:5378")
                .setPassword("a123456").setDatabase(0);
        RedissonClient redissonClient1 = Redisson.create(config1);

        Config config2 = new Config();
        config2.useSingleServer().setAddress("redis://172.29.1.180:5379")
                .setPassword("a123456").setDatabase(0);
        RedissonClient redissonClient2 = Redisson.create(config2);

        Config config3 = new Config();
        config3.useSingleServer().setAddress("redis://172.29.1.180:5380")
                .setPassword("a123456").setDatabase(0);
        RedissonClient redissonClient3 = Redisson.create(config3);

        String resourceName = "REDLOCK";
        RLock lock1 = redissonClient1.getLock(resourceName);
        RLock lock2 = redissonClient2.getLock(resourceName);
        RLock lock3 = redissonClient3.getLock(resourceName);

        RedissonRedLock redLock = new RedissonRedLock(lock1, lock2, lock3);
        boolean isLock;
        try {
            //500ms拿不到锁, 就认为获取锁失败。第二个参数是是锁失效时间。
            isLock = redLock.tryLock(500, 30000, TimeUnit.MILLISECONDS);
            System.out.println("isLock = "+isLock);
            if (isLock) {
                //TODO if get lock success, do something;
                Thread.sleep(30000);
            }
        } catch (Exception e) {
        } finally {
            // 无论如何, 最后都要解锁
            System.out.println("");
            redLock.unlock();
        }

    }


}
