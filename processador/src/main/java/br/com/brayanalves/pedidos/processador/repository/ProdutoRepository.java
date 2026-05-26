package br.com.brayanalves.pedidos.processador.repository;

import br.com.brayanalves.pedidos.processador.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, UUID> {
}
