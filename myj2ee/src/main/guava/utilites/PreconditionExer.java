package guava.utilites;

import com.google.common.base.Preconditions;
import org.hamcrest.core.Is;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.isA;
import static org.junit.Assert.assertThat;

/**
 * @author huangzq
 * @ClassName PrecondtionExer
 * @Description Preconditions提供静态方法来检查方法或构造函数
 * @Date 2019/7/27 4:11 PM
 */
public class PreconditionExer {

    @Test(expected = NullPointerException.class)
    public void testCheckNotNull(){
        checkNotNull(null);
    }

    @Test
    public void testCheckNotNullWithMessage(){
        try {
            checkNotNullWithMessage(null);
        } catch (Exception e) {
        }
    }

    private void checkNotNull(List<String> stringList) {
        Preconditions.checkNotNull(stringList);
    }

    private void checkNotNullWithMessage(List<String> stringList) {
        Preconditions.checkNotNull(stringList,"list is null");
    }
}
