package br.com.brayanalves.pedidos.notificacao.config;

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

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Configuration
public class RabbitmqConfig {

    @Value("${rabbitmq.exchange.name}")
    private String exchangeName;

    @Value("${rabbitmq.queue.name}")
    private String queueName;

    @Value("${rabbitmq.exchange.dlx.name}")
    private String exchangeDlxName;

    @Value("${rabbitmq.queue.dlq.name}")
    private String queueDlqName;

    @Bean
    public FanoutExchange notificacaoExchange() {
        return new FanoutExchange(exchangeName);
    }

    @Bean
    public FanoutExchange pedidoDlxExchange() {
        return new FanoutExchange(exchangeDlxName);
    }

    @Bean
    public Queue notificacaoQueue() {

        Map<String, Object> argumentos = new HashMap<>();
        argumentos.put("x-dead-letter-exchange", exchangeDlxName);

        return new Queue(queueName, true, false, false , argumentos);
    }

    @Bean
    public Queue notificacaoDlqQueue() {
        return new Queue(queueDlqName);
    }

    @Bean
    public Binding binding() {
        return BindingBuilder.bind(notificacaoQueue()).to(notificacaoExchange());
    }

    @Bean
    public Binding bindingDlq() {
        return BindingBuilder.bind(notificacaoDlqQueue()).to(pedidoDlxExchange());
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
