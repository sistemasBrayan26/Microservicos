package br.com.brayanalves.pedidos.processador.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
public class Produto {

    @Id
    private UUID id = UUID.randomUUID();

    private String nome;

    private BigDecimal valor;


    public Produto(UUID id, String nome, BigDecimal valor) {
        this.id = UUID.randomUUID();
        this.nome = nome;
        this.valor = valor;
    }
}
