package zookeeper.cfgcenter;

import org.I0Itec.zkclient.IZkDataListener;

import java.util.List;
import java.util.Properties;

/**
 * create by caichengcheng
 * date:2019-08-04
 */
public class ReaderImpl implements IReader {

    @Override
    public void load(String fileName) {
        List<String> propertyList = ZkClientInit.getZkClient().getChildren("/cfgcenter/" + fileName);
        propertyList.parallelStream().forEach(e->{
            String o = (String)ZkClientInit.getZkClient().readData("/cfgcenter/"+fileName+"/" + e);
            System.out.println("load "+fileName+":key="+e+",value="+o);
        });
    }

    @Override
    public void listenProperyChange(String fileName) {
        System.out.println("开启监听listenProperyChange");
        List<String> strings = ZkClientInit.getZkClient().subscribeChildChanges("/cfgcenter/" + fileName,(path,childs)->{
            System.out.println(path+"文件发生变化");
            System.out.println("变化后的文件配置项="+childs);
        });
        System.out.println("结束监听listenProperyChange");

    }

    @Override
    public void listenDataChange(String fileName) {
        System.out.println("开启监听listenDataChange");

        ZkClientInit.getZkClient().subscribeDataChanges("/cfgcenter/" + fileName, new IZkDataListener() {
            @Override
            public void handleDataChange(String dataPath, Object data) throws Exception {
                System.out.println(dataPath+"数据发生改变，->" +data);
            }

            @Override
            public void handleDataDeleted(String dataPath) throws Exception {
                System.out.println(dataPath + "配置被删除咯~");
            }
        });
        System.out.println("结束监听listenDataChange");
    }
}
