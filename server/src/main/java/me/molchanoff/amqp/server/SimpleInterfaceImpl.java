package me.molchanoff.amqp.server;

import me.molchanoff.amqp.common.SimpleInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by Daniil Molchanov on 28.02.17.
 */
@Component
public class SimpleInterfaceImpl implements SimpleInterface {
    private final int nodeId;

    public SimpleInterfaceImpl(@Value(SpringConfig.NODE_ID_PROPERTY_EL) int nodeId) {
        log.info("Node with ID: {} started", nodeId);
        this.nodeId = nodeId;
    }

    private final static Logger log = LoggerFactory.getLogger(SimpleInterfaceImpl.class);

    @Override
    public String doJob(int clientId) {
        log.info("Client with ID: {} called doJob method on {} node", clientId, nodeId);

        return "Simple payload generated on node " + nodeId + ". Client " + clientId + " called this method";
    }
}
