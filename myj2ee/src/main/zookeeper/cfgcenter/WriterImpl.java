package zookeeper.cfgcenter;

import org.I0Itec.zkclient.IZkDataListener;

import java.util.List;
import java.util.Properties;
import java.util.Random;

/**
 * create by caichengcheng
 * date:2019-08-04
 */
public class WriterImpl implements IWriter{
    @Override
    public void createFile(Properties source, String fileName) {
        if(!ZkClientInit.getZkClient().exists("/cfgcenter")) {
            ZkClientInit.getZkClient().createPersistent("/cfgcenter");
        }
        String parentName = "/cfgcenter/" +fileName;
        if(!ZkClientInit.getZkClient().exists(parentName)) {
            ZkClientInit.getZkClient().createPersistent(parentName);
        }
        source.keySet().parallelStream().forEach(e->{
            String path = parentName +"/" +e;
            if(ZkClientInit.getZkClient().exists(path)){
                ZkClientInit.getZkClient().writeData(path,source.get(e));
            }else {
                ZkClientInit.getZkClient().createPersistent(path,source.get(e));
            }
        });
    }

    @Override
    public void createPropertyEntry(String path, String key, String value) {
        if(ZkClientInit.getZkClient().exists(path+"/"+key)){
            ZkClientInit.getZkClient().writeData(path+"/"+key,value+new Random(10).nextInt());
        }else{

            ZkClientInit.getZkClient().createPersistent(path+"/"+key,value);
        }
    }


}
