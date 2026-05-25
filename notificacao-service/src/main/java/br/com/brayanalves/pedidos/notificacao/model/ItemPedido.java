package br.com.brayanalves.pedidos.notificacao.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class ItemPedido {

    private UUID id = UUID.randomUUID();

    private Produto produto;

    private Integer quantidade;

    public ItemPedido(UUID id, Produto produto, Integer quantidade) {
        this.id = UUID.randomUUID();
        this.produto = produto;
        this.quantidade = quantidade;
    }
}
