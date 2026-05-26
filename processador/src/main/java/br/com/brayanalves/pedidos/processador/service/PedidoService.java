package br.com.brayanalves.pedidos.processador.service;

import br.com.brayanalves.pedidos.processador.model.ItemPedido;
import br.com.brayanalves.pedidos.processador.model.Pedido;
import br.com.brayanalves.pedidos.processador.repository.PedidoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PedidoService {

    private final Logger logger = LoggerFactory.getLogger(PedidoService.class);

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private ItemPedidoService itemPedidoService;

    public void save(Pedido pedido){

        produtoService.save(pedido.getItens());

        List<ItemPedido> itemPedidos = itemPedidoService.save(pedido.getItens());

        pedidoRepository.save(pedido);

        itemPedidoService.updatedItemPedido(itemPedidos, pedido);

        logger.info("Pedido salvo: {}", pedido.toString());

    }


}
