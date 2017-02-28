package me.molchanoff.amqp.common;

/**
 * Created by Daniil Molchanov on 28.02.17.
 */
public interface SimpleInterface {

    /**
     * Returns string with node ID who processed method invokation
     * @param clientId client ID
     */
    String doJob(int clientId);
}
