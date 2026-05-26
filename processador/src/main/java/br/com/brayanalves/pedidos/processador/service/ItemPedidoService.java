package br.com.brayanalves.pedidos.processador.service;

import br.com.brayanalves.pedidos.processador.model.ItemPedido;
import br.com.brayanalves.pedidos.processador.model.Pedido;
import br.com.brayanalves.pedidos.processador.repository.ItemPedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ItemPedidoService {

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    public List<ItemPedido> save(List<ItemPedido> itens) {
       return itemPedidoRepository.saveAll(itens);
    }

    public void save(ItemPedido item){
        itemPedidoRepository.save(item);
    }

    public void updatedItemPedido(List<ItemPedido> itemPedidos, Pedido pedido) {
        itemPedidos.forEach(item -> {
            item.setPedido(pedido);
            this.save(item);

        });
    }
}
