package br.com.brayanalves.pedidos.processador.service;

import br.com.brayanalves.pedidos.processador.model.ItemPedido;
import br.com.brayanalves.pedidos.processador.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public void save(List<ItemPedido> itens) {
        itens.forEach(item -> {
            produtoRepository.save(item.getProduto());
        });
    }
}
