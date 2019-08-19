package guava;

import com.google.common.base.Optional;
import org.junit.Test;

/**
 * @author huangzq
 * @ClassName GuavaExer
 * @Description Guava
 * @Date 2019/7/26 8:03 PM
 */

/**
 * 1.
 *
 *
 *
 *
 *
 *
 *
 */
public class GuavaExer {

    @Test
    public void test1(){
        Integer i1 = null;
        Optional<Integer> optional = Optional.fromNullable(i1);
        System.out.println(optional.isPresent());
        System.out.println(Optional.absent());
    }
}
