package guava.utilites;

import com.google.common.base.Stopwatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * @author huangzq
 * @ClassName ElapsedExample
 * @Description 秒表
 * @Date 2019/7/29 4:41 PM
 */
public class StopWathcExample {
    private final static Logger LOGGER = LoggerFactory.getLogger(StopWathcExample.class);

    public static void main(String[] args) throws InterruptedException {
        process("123123123  ");
    }

    private static void process(String orderNo) throws InterruptedException {
        LOGGER.info("start process the order [{}]",orderNo);
        Stopwatch stopwatch = Stopwatch.createStarted();
        TimeUnit.MILLISECONDS.sleep(100);
        LOGGER.info("The order [{}] has been handled and use time [{}]",orderNo,stopwatch.stop());
    }
}
