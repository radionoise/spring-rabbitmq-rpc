package me.molchanoff.amqp.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by Daniil Molchanov on 28.02.17.
 */
public class ServerMain {

    private static final Logger log = LoggerFactory.getLogger(ServerMain.class);

    public static void main(String[] args) {
        log.info("Started");

        if (System.getProperty(SpringConfig.NODE_ID_PROPERTY) != null) {
            AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        }
        else {
            log.error("node property is missing. Pass -Dnode=[node ID] argument");
        }
    }
}
