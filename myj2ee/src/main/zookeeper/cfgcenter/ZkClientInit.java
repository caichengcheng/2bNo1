package zookeeper.cfgcenter;

import org.I0Itec.zkclient.ZkClient;

/**
 * create by caichengcheng
 * date:2019-08-04
 */
public class ZkClientInit {

    private static volatile ZkClient zkClient ;

    public static void init(){
        try {
            zkClient = new ZkClient("localhost:2181");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static ZkClient getZkClient() {
        return zkClient;
    }
}
