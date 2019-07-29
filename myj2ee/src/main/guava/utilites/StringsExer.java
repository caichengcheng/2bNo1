package guava.utilites;

import com.google.common.base.CharMatcher;
import com.google.common.base.Charsets;
import com.google.common.base.Strings;
import org.junit.Test;

import java.nio.charset.Charset;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;

/**
 * @author huangzq
 * @ClassName StringsExer
 * @Description TODO
 * @Date 2019/7/29 11:25 AM
 */
public class StringsExer {

    @Test
    public void testStringsMethod(){
        assertThat(Strings.emptyToNull(""),nullValue());
        assertThat(Strings.nullToEmpty(null),equalTo(""));
        assertThat(Strings.nullToEmpty("hello"),equalTo("hello"));
        assertThat(Strings.isNullOrEmpty(null),equalTo(true));
        assertThat(Strings.commonPrefix("hello","hehe"),equalTo("he"));
        assertThat(Strings.commonPrefix("hello","xixhe"),equalTo(""));
        assertThat(Strings.repeat("wsnd",3),equalTo("wsndwsndwsnd"));
        assertThat(Strings.repeat("wsnd",1),equalTo("wsnd"));
        assertThat(Strings.padEnd("hello",3,'x'),equalTo("hello"));
        assertThat(Strings.padEnd("hello",8,'x'),equalTo("helloxxx"));
        assertThat(Strings.padStart("hello",8,'y'),equalTo("yyyhello"));
    }

    /**
     * 字符集
     */
    @Test
    public void testCharsets(){
        Charset charset = Charset.forName("utf-8");
        assertThat(Charsets.UTF_8,equalTo(charset));
    }

    /**
     * 字符匹配
     */
    @Test
    public void testCharMatcher(){
        assertThat(CharMatcher.javaDigit().matches('5'),equalTo(true));//是否是数字类型
        assertThat(CharMatcher.javaDigit().matches('x'),equalTo(false));//是否是数字类型

        assertThat(CharMatcher.is('A').countIn("I Am Your Father"),equalTo(1));//返回字符串中某字符数量
        assertThat(CharMatcher.anyOf("a ").collapseFrom("hello     Guava",'*'),equalTo("hello*Gu*v*"));//字符串中任何a ,都替换为*
        assertThat(CharMatcher.javaDigit().or(CharMatcher.whitespace()).retainFrom("hello  121   java"),equalTo("  121   "));
        assertThat(CharMatcher.javaDigit().or(CharMatcher.whitespace()).removeFrom("hello  121   java"),equalTo("hellojava"));

    }
}
