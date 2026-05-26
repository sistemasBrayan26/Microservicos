package br.com.brayanalves.pedidos.processador.model;

import br.com.brayanalves.pedidos.processador.model.enums.Status;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@Builder
@Entity
public class Pedido {

    @Id
    private UUID id = UUID.randomUUID();


    private String cliente;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemPedido> itens = new ArrayList<>();

    private BigDecimal valorTotal = BigDecimal.ZERO;

    private String emailNotificacao;

    @Enumerated(EnumType.STRING)
    private Status status = Status.EM_PROCESSAMENTO;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dataHora = LocalDateTime.now();

    public Pedido(UUID id, String cliente, List<ItemPedido> itens, BigDecimal valorTotal, String emailNotificacao,
                  Status status, LocalDateTime dataHora) {
        this.id = id != null ? id : UUID.randomUUID(); // Usa o ID que veio se existir
        this.cliente = cliente;
        this.itens = itens != null ? itens : new ArrayList<>();
        this.valorTotal = valorTotal;
        this.emailNotificacao = emailNotificacao;
        this.status = status != null ? status : Status.EM_PROCESSAMENTO;
        this.dataHora = dataHora != null ? dataHora : LocalDateTime.now();
    }
}
