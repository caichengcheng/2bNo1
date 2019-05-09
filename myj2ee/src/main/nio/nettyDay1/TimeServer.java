package nio.nettyDay1;

import org.junit.Test;

/**
 * create by caichengcheng
 * date:2019-04-02
 */
public class TimeServer {

    public static void main(String[] args) {
        MultiplexerTimeServer server = new MultiplexerTimeServer(8080);
        new Thread(server,"server -001").start();
    }

    @Test
    public void client(){

    }
}
