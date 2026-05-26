package br.com.brayanalves.pedidos.processador.repository;

import br.com.brayanalves.pedidos.processador.model.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedido, UUID> {
}
