package me.molchanoff.amqp.client;

import me.molchanoff.amqp.common.SimpleInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by Daniil Molchanov on 28.02.17.
 */
public class ClientMain {

    private final static Logger log = LoggerFactory.getLogger(ClientMain.class);

    public static void main(String[] args) throws InterruptedException {
        log.info("Started");

        if (System.getProperty("client") != null) {
            int clientId = Integer.parseInt(System.getProperty("client"));
            AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
            SimpleInterface proxy = context.getBean(SimpleInterface.class);

            for (int i = 0; i < 10; i++) {
                String payload = proxy.doJob(clientId);
                log.info("Client {} received payload: {}", clientId, payload);
                Thread.sleep(2000);
            }
        }
        else {
            log.error("client property is missing. Pass -Dclient=[client ID] argument");
        }
    }
}
