package ratelimit;

import com.google.common.util.concurrent.Monitor;
import com.google.common.util.concurrent.RateLimiter;

/**
 * 限流 - 漏桶算法
 * @author caichengcheng
 * date:2019-12-09
 */
public class Bucket {
    //
    RateLimiter rateLimiter = RateLimiter.create(1);
    int bucketLinit = 2;
    Monitor inputMonitor = new Monitor();
    Monitor outputMonitor = new Monitor();

    private void produce(){
//        inputMonitor.enterIf();
    }
}
