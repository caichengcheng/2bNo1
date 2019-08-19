package zookeeper.cfgcenter;

import java.util.Properties;

/**
 * create by caichengcheng
 * date:2019-08-04
 */
public interface IReader {
    /**
     * 从远端获取配置
     * @param fileName
     * @return
     */
    void load(String fileName);

    /**
     * 监听配置文件变化，只针对节点的新增、删除(可用于进行重新加载）
     * @param fileName
     */
    void listenProperyChange(String fileName);

    /**
     * 监听配置文件变化，只针对节点数据的变化(可用于进行重新加载）
     * @param fileName
     */
    void listenDataChange(String fileName);
}
