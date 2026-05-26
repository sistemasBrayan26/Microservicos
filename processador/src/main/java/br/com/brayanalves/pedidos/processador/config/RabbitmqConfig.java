package br.com.brayanalves.pedidos.processador.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitmqConfig {

    @Value("${rabbitmq.exchange.name}")
    private String exchangeName;

    @Value("${rabbitmq.queue.name}")
    private String queueName;

    @Bean
    public FanoutExchange pedidoExchange() {
        return new FanoutExchange(exchangeName);
    }

    @Bean
    public Queue processadorqueue() {
        return new Queue(queueName);
    }

    @Bean
    public Binding binding() {
        return BindingBuilder.bind(processadorqueue()).to(pedidoExchange());
    }

    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);

        // Usamos o conversor moderno do Spring 4.0+ (sem o "2" no nome)
        JacksonJsonMessageConverter converter = new JacksonJsonMessageConverter();

        // O SEGREDO: Força o conversor a olhar apenas para o parâmetro do método do seu listener (Pedido)
        // e ignorar completamente o "__TypeId__" com o pacote antigo enviado pelo outro microsserviço.
        converter.setAlwaysConvertToInferredType(true);

        factory.setMessageConverter(converter);
        return factory;
    }

    @Bean
    public ApplicationListener<ApplicationReadyEvent> applicationReadyEventApplicationListener(RabbitAdmin rabbitAdmin){
        return event -> rabbitAdmin.initialize();
    }
}
