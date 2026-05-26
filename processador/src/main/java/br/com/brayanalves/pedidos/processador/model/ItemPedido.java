package br.com.brayanalves.pedidos.processador.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
public class ItemPedido {

    @Id
    private UUID id = UUID.randomUUID();

    @ManyToOne
    private Produto produto;

    private Integer quantidade;

    @ManyToOne
    private Pedido pedido;

    public ItemPedido(UUID id, Produto produto, Integer quantidade) {
        this.id = UUID.randomUUID();
        this.produto = produto;
        this.quantidade = quantidade;
    }
}
