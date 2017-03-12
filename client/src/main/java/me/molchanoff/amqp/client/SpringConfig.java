package me.molchanoff.amqp.client;

import me.molchanoff.amqp.common.SimpleInterface;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.remoting.client.AmqpProxyFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Daniil Molchanov on 28.02.17.
 */
@Configuration
public class SpringConfig {

    @Bean
    public ConnectionFactory connectionFactory() {
        return new CachingConnectionFactory("localhost", 5672);
    }

    @Bean
    public RabbitTemplate template() {
        RabbitTemplate template = new RabbitTemplate(connectionFactory());
        template.setExchange("rpc");

        return template;
    }

    @Bean
    public AmqpProxyFactoryBean proxy(RabbitTemplate template) {
        AmqpProxyFactoryBean proxy = new AmqpProxyFactoryBean();
        proxy.setAmqpTemplate(template);
        proxy.setServiceInterface(SimpleInterface.class);
        proxy.setRoutingKey(SimpleInterface.class.getSimpleName());

        return proxy;
    }
}
