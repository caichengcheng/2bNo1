package redis.autoredis;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class AutoRedisProxy {
    @Pointcut("@annotation(redis.autoredis.AutoSetRedis)")
    public void pointcut(){
    }
    @Around("pointcut()")
    public Object arround(ProceedingJoinPoint point) throws Throwable{
        System.out.println("proxy-进入切面");
        //获取参数
        Object[] args = point.getArgs();
        //获取签名
        Signature signature = point.getSignature();
        MethodSignature methodSign = (MethodSignature) signature;
        //获取方法
        Method method = methodSign.getMethod();
        //获取注解
        AutoSetRedis aotuSetRedis = method.getAnnotation(AutoSetRedis.class);
        System.out.println("proxy-注解信息："+JSON.toJSONString(aotuSetRedis));
        //获取返回值类型
        Class<?> returnType = method.getReturnType();
        //解析注解
        String keyPre = aotuSetRedis.keyPre();
        String operate = aotuSetRedis.operate();
        int unionKeyIndex = aotuSetRedis.unionParamIndex();
        String key = keyPre + args[unionKeyIndex];
        Object proceed  = null;
        if(operate.equals("get")){
            System.out.println("proxy-目标方法get操作，需要查询缓存");
            String cache = LocalCache.hashCache.get(key);
            if(StringUtils.isNotBlank(cache)){
                System.out.println("proxy-命中缓存");
                return JSON.parseObject(cache,returnType);
            }else{
                System.out.println("proxy-未命中缓存");
                proceed = point.proceed();
                if(proceed != null){
                    LocalCache.hashCache.put(key,JSON.toJSONString(proceed));
                }
            }
        }
        return proceed;
    }
}
