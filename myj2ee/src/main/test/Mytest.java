package test;

import com.alibaba.fastjson.JSON;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * 将对象转成map: 要求属性是数组类型的，转换成json
 * @author caichengcheng
 *         time: 2019/2/20.
 */
public class Mytest {
    private Object o;

    public static void main(String[] args) {
        ParamBean paramBean = new ParamBean();
        paramBean.setName("啊土伯");
        paramBean.setFather(new String[]{"mother1","mother2"});
        paramBean.setFather(new String[]{"father1","father2"});
        Mytest mytest = new Mytest();
        try {
            Map<String, String> convert = mytest.convert(paramBean);
            System.out.println(convert);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public  Map<String,String> convert( Object o) throws Exception {
        HashMap<String, String> resultMap = new HashMap<>();
        Field[] declaredFields = o.getClass().getDeclaredFields();
        for (Field field : declaredFields){
            //获取属性类型
            Class<?> type = field.getType();
            //属性名
            String name = field.getName();
            if(type.isArray()){
                //若属性类型是集合list,转换成json
                Object fieldValue = getFieldValue(o, o.getClass(), name);
                String jsonFieldValue = JSON.toJSONString(fieldValue);
                resultMap.put(name,jsonFieldValue);
            }else{
                Object fieldValue = getFieldValue(o, o.getClass(), name);

                resultMap.put(name,String.valueOf(fieldValue));
            }
        }
        return resultMap;
    }

    Object getFieldValue(Object o ,Class c ,String fieldName) throws Exception {
        String methodName = "get" + fieldName.substring(0,1).toUpperCase() + fieldName.substring(1) ;
        Method method = o.getClass().getMethod(methodName, null);
        Object value = method.invoke(o, null);
        return value;
    }
}
