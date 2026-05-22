package br.com.sistemasbas.pedidos.api.model;

import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@NoArgsConstructor
public class Produto {

    private UUID id = UUID.randomUUID();

    private String nome;

    private BigDecimal valor;


    public Produto(UUID id, String nome, BigDecimal valor) {
        this.id = UUID.randomUUID();
        this.nome = nome;
        this.valor = valor;
    }
}
