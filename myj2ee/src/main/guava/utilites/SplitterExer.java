package guava.utilites;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * @author huangzq
 * @ClassName SplitterExer
 * @Description TODO
 * @Date 2019/7/27 3:17 PM
 */
public class SplitterExer {

    @Test
    public void splitOnSplit(){
        List<String> result = Splitter.on("|").splitToList("hello|world");
        assertThat(result,notNullValue());
        assertThat(result.size(),equalTo(2));
        assertThat(result.get(0),equalTo("hello"));
        assertThat(result.get(1),equalTo("world"));
    }

    @Test
    public void splitOnSplitOmitEmpty(){
        List<String> result = Splitter.on("|").splitToList("hello|world|||");
        assertThat(result,notNullValue());
        assertThat(result.size(),equalTo(5));

        List<String> resultOmit = Splitter.on("|").omitEmptyStrings().splitToList("hello|world|||");
        assertThat(resultOmit,notNullValue());
        assertThat(resultOmit.size(),equalTo(2));
    }

    @Test
    public void splitOnSplitOmitEmptyTrim(){
        List<String> result = Splitter.on("|").omitEmptyStrings().splitToList("hello  |  world|||");
        assertThat(result.size(),equalTo(2));
        assertThat(result.get(0),equalTo("hello  "));
        assertThat(result.get(1),equalTo("  world"));

        List<String> resultTrim = Splitter.on("|").trimResults().omitEmptyStrings().splitToList("hello  |  world|||");
        assertThat(resultTrim.size(),equalTo(2));
        assertThat(resultTrim.get(0),equalTo("hello"));
        assertThat(resultTrim.get(1),equalTo("world"));
    }


    /**
     * aaaabbbbccccdddd
     */
    @Test
    public void splitFixLength(){
        List<String> result = Splitter.fixedLength(4).splitToList("aaaabbbbccccdddd");
        assertThat(result,notNullValue());
        assertThat(result.size(),equalTo(4));
        assertThat(result.get(0),equalTo("aaaa"));
        assertThat(result.get(2),equalTo("cccc"));
    }
    /**
     * abcdabcdabcd
     */
    @Test
    public void splitFixLengthv1(){
        List<String> result = Splitter.fixedLength(4).splitToList("abcdabcdabcd");
        assertThat(result,notNullValue());
        assertThat(result.size(),equalTo(3));
        assertThat(result.get(0),equalTo("abcd"));
        assertThat(result.get(2),equalTo("abcd"));
    }

    @Test
    public void splitLimit(){
        List<String> result = Splitter.on("-").limit(3).splitToList("i-am-fine-thank-you");
        assertThat(result,notNullValue());
        assertThat(result.size(),equalTo(3));
        assertThat(result.get(1),equalTo("am"));
        assertThat(result.get(2),equalTo("fine-thank-you"));
    }

    @Test
    public void splitToMap(){
        Map<String, String> result = Splitter.on("|").omitEmptyStrings().trimResults().withKeyValueSeparator("=").split("i=am | fin=thank||");
        assertThat(result,notNullValue());
        assertThat(result.size(),equalTo(2));
        assertThat(result.get("i"),equalTo("am"));
    }
}
