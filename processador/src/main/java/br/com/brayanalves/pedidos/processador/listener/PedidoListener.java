package br.com.brayanalves.pedidos.processador.listener;

import br.com.brayanalves.pedidos.processador.model.Pedido;
import br.com.brayanalves.pedidos.processador.model.enums.Status;
import br.com.brayanalves.pedidos.processador.service.PedidoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PedidoListener {

    private final Logger logger = LoggerFactory.getLogger(PedidoListener.class);

    @Autowired
    private PedidoService pedidoService;

    @RabbitListener(queues = "pedidos.v1.pedido-criado-gerar-processamento")
    public void salvarPedido(Pedido pedido) {
        pedido.setStatus(Status.PROCESSADO);

        pedidoService.save(pedido);
    }
}
