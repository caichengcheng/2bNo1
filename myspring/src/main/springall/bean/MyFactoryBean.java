package springall.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * @author caichengcheng
 * date:2020-04-10
 */
@Configuration
public class MyFactoryBean {
    @Bean
    @Scope(scopeName = "prototype")
    public Student getStudent(){
        return new Student();
    }
}
