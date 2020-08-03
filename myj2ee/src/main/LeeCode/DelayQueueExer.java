package LeeCode;

import io.netty.util.HashedWheelTimer;
import io.netty.util.TimerTask;


import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class DelayQueueExer {
    public static void main(String[] args) {
        HashedWheelTimer timer = new HashedWheelTimer();
        System.gc();

    }

    static class Timer implements Delayed{

        @Override
        public long getDelay(TimeUnit unit) {
            return 0;
        }

        @Override
        public int compareTo(Delayed o) {
            return 0;
        }
    }
}
