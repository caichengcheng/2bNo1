package zookeeper.cfgcenter;

import java.util.Properties;

/**
 * create by caichengcheng
 * date:2019-08-04
 */
public interface IWriter {
    /**
     * 创建一个新的配置文件
     * @param source
     * @param fileName
     */
    void createFile(Properties source,String fileName);

    /**
     * 在现有配置基础上，新增一个配置
     * @param path
     * @param key
     * @param value
     */
    void createPropertyEntry(String path,String key,String value);



}
