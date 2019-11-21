package snowflake;

import java.net.InetAddress;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author caichengcheng
 * date:2019-11-20
 */
public class DaojiaIdWroker {
    private static final String ID_URL = "tcp://unique/SeedProviderImpl";
    //
//    private static ISeedProvider seedProvider = null;
    private final long beginTime;
    private final long serverBits;
    private final long dbBits;
    private final long maxDbCount;
    private final long sequenceBits;
    private final long sequenceMask;
    private final long dbShift;
    private final long serverShift;
    private final long timeStampLeftShift;
    private long serverId;
    private long sequence;
    private long lastTimeStamp;
    int sequenceLoop;
    int sequenceLoopMax;


    private DaojiaIdWroker(String clusterName) throws Exception {
        this.beginTime = 1514736000000L;
        this.serverBits = 8L;
        this.dbBits = 9L;
        this.maxDbCount = 511L;
        this.sequenceBits = 5L;
        this.sequenceMask = 31L;
        this.dbShift = 5L;
        this.serverShift = 14L;
        this.timeStampLeftShift = 22L;
        this.serverId = -1L;
        this.sequence = 0L;
        this.lastTimeStamp = -1L;
        this.sequenceLoop = 0;
        this.sequenceLoopMax = 31;
        String ip = InetAddress.getLocalHost().getHostAddress();
//        seedProvider = (ISeedProvider)DSFProxyFactory.create(ISeedProvider.class, "tcp://unique/SeedProviderImpl");
//        this.serverId = (long)seedProvider.getSeed(clusterName, ip);
        System.out.println("IdWorker info: clusterName=>" + clusterName + " serverId=>" + this.serverId + " ip=>" + ip);
    }

    public synchronized long nextId(long dbShardingId) throws Exception {
        if (dbShardingId < 0L) {
            throw new IllegalArgumentException(String.format("生成数据库分库Id非法,必须是正整数"));
        } else {
            long dbFlagBit = dbShardingId & 511L;
            long timeStamp = this.timeGen();
            if (timeStamp < this.lastTimeStamp) {
                throw new RuntimeException(String.format("服务器时间被修改滞后，滞后时间%d", this.lastTimeStamp - timeStamp));
            } else {
                if (timeStamp == this.lastTimeStamp) {
                    if (this.sequenceLoop >= this.sequenceLoopMax) {
                        timeStamp = this.tilNextMills(timeStamp);
                        this.sequenceLoop = 0;
                        System.out.println("timeStamp=>" + timeStamp);
                    } else {
                        ++this.sequenceLoop;
                    }
                } else {
                    this.sequenceLoop = 0;
                }

                this.sequence &= 31L;
                System.out.println("timeStamp=>" + timeStamp + " lastTimeStamp=>" + this.lastTimeStamp + " loop=>" + this.sequenceLoop + " sequence=>" + this.sequence);
                this.lastTimeStamp = timeStamp;
                return timeStamp - 1514736000000L << 22 | this.serverId << 14 | dbFlagBit << 5 | this.sequence++;
            }
        }
    }

    protected long tilNextMills(long lastTimeStamp) {
        long timeStamp;
        for(timeStamp = this.timeGen(); timeStamp <= lastTimeStamp; timeStamp = this.timeGen()) {
        }

        return timeStamp;
    }

    protected long timeGen() {
        return System.currentTimeMillis();
    }

    public int getDBIndex(long id, int dbCount) {
        return (int)(id >> 5 & 511L % (long)dbCount);
    }

    public static DaojiaIdWroker getInstance(String clusterName) throws Exception {
        if (null != clusterName && !"".equals(clusterName)) {
            return DaojiaIdWroker.IdWorkerSingleTon.getIdWork(clusterName);
        } else {
            new Exception("非法的集群名,集群名称不能为空");
            throw new IllegalArgumentException(String.format("集群名称非法,集群名称不能为空"));
        }
    }

    private static class IdWorkerSingleTon {
        private static ConcurrentHashMap<String, DaojiaIdWroker> idWorkerMap = new ConcurrentHashMap();

        private IdWorkerSingleTon() {
        }

        private static DaojiaIdWroker getIdWork(String clusterName) throws Exception {
            DaojiaIdWroker idWorker = (DaojiaIdWroker)idWorkerMap.get(clusterName);
            if (idWorker != null) {
                return idWorker;
            } else {
                DaojiaIdWroker newIdWorder = new DaojiaIdWroker(clusterName);
                DaojiaIdWroker preValue = (DaojiaIdWroker)idWorkerMap.putIfAbsent(clusterName, newIdWorder);
                return preValue == null ? newIdWorder : preValue;
            }
        }
    }
}
