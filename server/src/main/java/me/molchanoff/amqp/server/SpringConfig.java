package me.molchanoff.amqp.server;

import me.molchanoff.amqp.common.SimpleInterface;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.remoting.service.AmqpInvokerServiceExporter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Daniil Molchanov on 28.02.17.
 */
@Configuration
@ComponentScan(basePackages = "me.molchanoff.amqp.server")
public class SpringConfig {

    public static final String NODE_ID_PROPERTY = "node";
    public static final String NODE_ID_PROPERTY_EL = "#{systemProperties[" + NODE_ID_PROPERTY + "]}";

    @Bean
    public ConnectionFactory connectionFactory() {
        return new CachingConnectionFactory("localhost", 5672);
    }

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange("rpc");
    }

    @Bean
    public Queue queue() {
        return new Queue(SimpleInterface.class.getSimpleName());
    }

    @Bean
    public Binding binding(Queue queue, Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(SimpleInterface.class.getSimpleName()).noargs();
    }

    @Bean
    public RabbitAdmin admin(ConnectionFactory factory) {
        RabbitAdmin rabbitAdmin = new RabbitAdmin(factory);

        return rabbitAdmin;
    }

    @Bean
    public RabbitTemplate template(ConnectionFactory factory) {
        RabbitTemplate template = new RabbitTemplate(factory);

        return template;
    }

    @Bean
    public AmqpInvokerServiceExporter exporter(RabbitTemplate template, SimpleInterface service) {
        AmqpInvokerServiceExporter exporter = new AmqpInvokerServiceExporter();
        exporter.setAmqpTemplate(template);
        exporter.setService(service);
        exporter.setServiceInterface(SimpleInterface.class);

        return exporter;
    }

    @Bean
    public SimpleMessageListenerContainer container(ConnectionFactory connectionFactory, Queue queue, AmqpInvokerServiceExporter exporter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
        container.setQueues(queue);
        container.setMessageListener(exporter);

        return container;
    }
}
