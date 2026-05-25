package br.com.brayanalves.pedidos.notificacao.model;

import br.com.brayanalves.pedidos.notificacao.model.enums.Status;
import com.fasterxml.jackson.annotation.JsonFormat;
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
public class Pedido {

    private UUID id = UUID.randomUUID();

    private String cliente;

    private List<ItemPedido> itens = new ArrayList<>();

    private BigDecimal valorTotal = BigDecimal.ZERO;

    private String emailNotificacao;

    private Status status = Status.EM_PROCESSAMENTO;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dataHora = LocalDateTime.now();

    public Pedido(UUID id, String cliente, List<ItemPedido> itens, BigDecimal valorTotal, String emailNotificacao, Status status,
                  LocalDateTime dataHora) {
        this.id = UUID.randomUUID();
        this.cliente = cliente;
        this.itens = itens;
        this.valorTotal = valorTotal;
        this.emailNotificacao = emailNotificacao;
        this.status = Status.EM_PROCESSAMENTO;
        this.dataHora = LocalDateTime.now();
    }
}
