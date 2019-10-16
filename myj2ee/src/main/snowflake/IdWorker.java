package snowflake;

import java.util.concurrent.atomic.AtomicLongArray;

/**
 * 简单的雪花算法：1位符号位，41位时间戳，5业务，5机器，12位sequence（4095个冗余）
 * 问题：如何解决时钟回拨问题？
 * 方案1：可以记录下上一次生成id的时间lastTime，本次生成如果时钟回拨了，也就是说curTime < lastTime ，生成id用的时间戳用的lastTime，把sequence+1,来生成id，如果sequence
 *       已经到达上限，则报错，返回-1；
 *    优点：只维护上次时间，成本低
 *    缺点：若时钟回拨后，并发请求量大，有可能出现sequence不够用
 * 方案2：存储一个容量为200的AtomicLongArray数组,index就是0-199 ，index=时间戳&(200-1)，value=id，也就是说能允许存最近200次不同毫秒生成的id,每次新生成id的时候，
 *       都那curTime & 199 ,得到index，然后取出对应位置的上次id,拿id右移22位，得到inxTime,若id为空，或者inxTime<curTime,表示正常情况，正常生产id,并覆盖即可，
 *       若inxTime >= curTime,代表着回拨情况/同个毫秒竞争情况，则直接拿对应位置的sequence+1，即可
 *    优点：风险均摊，降低方案sequence超上限的概率
 *    缺点：维护一个数组，提高了内存成本
 * create by caichengcheng
 * date:2019-05-22
 */
public class IdWorker {
    private long workerId; // 这个就是代表了机器id
    private long datacenterId; // 这个就是代表了机房id
    private long sequence; // 这个就是代表了一毫秒内生成的多个id的最新序号

    public IdWorker(long workerId, long datacenterId, long sequence) {

        // sanity check for workerId
        // 这儿不就检查了一下，要求就是你传递进来的机房id和机器id不能超过32，不能小于0
        if (workerId > maxWorkerId || workerId < 0) {

            throw new IllegalArgumentException(
                    String.format("worker Id can't be greater than %d or less than 0",maxWorkerId));
        }

        if (datacenterId > maxDatacenterId || datacenterId < 0) {

            throw new IllegalArgumentException(
                    String.format("datacenter Id can't be greater than %d or less than 0",maxDatacenterId));
        }

        this.workerId = workerId;
        this.datacenterId = datacenterId;
        this.sequence = sequence;
    }
    //最小日期1970-01-01
    private long twepoch = 1288834974657L;

    private long workerIdBits = 5L;
    private long datacenterIdBits = 5L;

    // 这个是二进制运算，就是5 bit最多只能有31个数字，也就是说机器id最多只能是32以内
    private long maxWorkerId = -1L ^ (-1L << workerIdBits);

    // 这个是一个意思，就是5 bit最多只能有31个数字，机房id最多只能是32以内
    private long maxDatacenterId = -1L ^ (-1L << datacenterIdBits);
    private long sequenceBits = 12L;

    private long workerIdShift = sequenceBits;
    //1024
    private long datacenterIdShift = sequenceBits + workerIdBits;
    private long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits;
    //4096
    private long sequenceMask = -1L ^ (-1L << sequenceBits);

    private long lastTimestamp = -1L;

    public long getWorkerId(){
        return workerId;
    }

    public long getDatacenterId() {
        return datacenterId;
    }

    public long getTimestamp() {
        return System.currentTimeMillis();
    }

    // 这个是核心方法，通过调用nextId()方法，让当前这台机器上的snowflake算法程序生成一个全局唯一的id
    public synchronized long nextId() {

        // 这儿就是获取当前时间戳，单位是毫秒
        long timestamp = timeGen();

        if (timestamp < lastTimestamp) {
            System.err.printf(
                    "clock is moving backwards. Rejecting requests until %d.", lastTimestamp);
            throw new RuntimeException(
                    String.format("Clock moved backwards. Refusing to generate id for %d milliseconds",
                            lastTimestamp - timestamp));
        }


        // 下面是说假设在同一个毫秒内，又发送了一个请求生成一个id
        // 这个时候就得把seqence序号给递增1，最多就是4096
        if (lastTimestamp == timestamp) {

            // 这个意思是说一个毫秒内最多只能有4096个数字，无论你传递多少进来，
            //这个位运算保证始终就是在4096这个范围内，避免你自己传递个sequence超过了4096这个范围
            sequence = (sequence + 1) & sequenceMask;

            if (sequence == 0) {
                timestamp = tilNextMillis(lastTimestamp);
            }

        } else {
            sequence = 0;
        }

        // 这儿记录一下最近一次生成id的时间戳，单位是毫秒
        lastTimestamp = timestamp;

        // 这儿就是最核心的二进制位运算操作，生成一个64bit的id
        // 先将当前时间戳左移，放到41 bit那儿；将机房id左移放到5 bit那儿；将机器id左移放到5 bit那儿；将序号放最后12 bit
        // 最后拼接起来成一个64 bit的二进制数字，转换成10进制就是个long型
        return ((timestamp - twepoch) << timestampLeftShift) |
                (datacenterId << datacenterIdShift) |
                (workerId << workerIdShift) | sequence;
    }

    private long tilNextMillis(long lastTimestamp) {

        long timestamp = timeGen();

        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    private long timeGen(){
        return System.currentTimeMillis();
    }

    //---------------测试---------------
    public static void main(String[] args) {

        IdWorker worker = new IdWorker(1,1,1);

        for (int i = 0; i < 30; i++) {
            System.out.println(worker.nextId());
        }
    }
}
