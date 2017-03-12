package me.molchanoff.amqp.common;

/**
 * Created by Daniil Molchanov on 28.02.17.
 */
public interface SimpleInterface {

    /**
     * Returns UUID generated on server side
     * @param clientId client ID
     */
    String doJob(int clientId);
}
