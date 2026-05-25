package br.com.brayanalves.pedidos.notificacao.listener;

import br.com.brayanalves.pedidos.notificacao.model.Pedido;
import br.com.brayanalves.pedidos.notificacao.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PedidoListener {

    private Logger logger = LoggerFactory.getLogger(PedidoListener.class);

    @Autowired
    private EmailService emailService;

    @RabbitListener(queues = "pedidos.v1.pedido-criado-gerar-notificacao")
    public void enviarNotificacao(Pedido pedido) {
        emailService.enviarEmail(pedido);
        logger.info("Notificação gerada: {}", pedido.toString());
    }
}
