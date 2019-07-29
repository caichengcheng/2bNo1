package guava.utilites;


import static com.google.common.collect.ImmutableMap.of;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.fail;

import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableMap;
import com.google.common.io.Files;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author huangzq
 * @ClassName Joiner
 * @Description TODO
 * @Date 2019/7/27 9:35 AM
 */
public class JoinerExer {

    private final List<String> stringList = Arrays.asList(
            "Java","Guava","Redis","Mysql"
    );

    private final List<String> stringListWithNullValue = Arrays.asList(
            "Java","Guava","Redis",null
    );


    @Test
    public void JoinerOnJoin(){
        String result = Joiner.on("-").join(stringList);
        assertThat(result,equalTo("Java-Guava-Redis-Mysql"));
    }

    @Test
    public void JoinerOnJoinWithNullValue(){
        String result = Joiner.on("-").skipNulls().join(stringListWithNullValue);
        assertThat(result,equalTo("Java-Guava-Redis"));
    }

    @Test
    public void JoinerOnJoinWithNullValueForDefalut(){
        String result = Joiner.on("-").useForNull("Default").join(stringListWithNullValue);
        assertThat(result,equalTo("Java-Guava-Redis-Default"));
    }

    @Test
    public void testJoinOnAppend(){
        StringBuilder stringBuilder = new StringBuilder();
        StringBuilder builder = Joiner.on("-").appendTo(stringBuilder, stringList);
        assertThat(stringBuilder,sameInstance(builder));
        assertThat(builder.toString(),equalTo("Java-Guava-Redis-Mysql"));
    }

    private final String fileName = "/Users/huangzq/code/hello.txt";
    private final String fileName_map = "/Users/huangzq/code/hello-map.txt";

    @Test
    public void testJoinOnAppendToWriter() {
        try(FileWriter fileWriter = new FileWriter(new File(fileName))) {
            Joiner.on("-").useForNull("Default").appendTo(fileWriter,stringListWithNullValue);
            assertThat(Files.isFile().test(new File(fileName)),equalTo(true));
        } catch (IOException e) {
            fail("");
        }
    }

    private final Map<String,String> stringMap = of( "Java","Guava","Redis","Mysql");

    @Test
    public void testJoinOnMap(){
        String result = Joiner.on("@").withKeyValueSeparator("=").join(stringMap);
        assertThat(result,equalTo("Java=Guava@Redis=Mysql"));

    }

    @Test
    public void testJoinOnMapToAppendable(){
        try(FileWriter fileWriter = new FileWriter(new File(fileName_map))) {
            Joiner.on("@").withKeyValueSeparator("=").appendTo(fileWriter,stringMap);
            assertThat(Files.isFile().test(new File(fileName)),equalTo(true));
        } catch (IOException e) {
            fail("write map fail");
        }

    }

}
