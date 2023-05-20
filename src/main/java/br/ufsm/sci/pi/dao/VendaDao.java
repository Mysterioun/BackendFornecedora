package br.ufsm.sci.pi.dao;

import br.ufsm.sci.pi.model.Venda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VendaDao extends JpaRepository<Venda, Integer> {

    List<Venda> getTodasVendas();

    List<Venda> getVendaPeloUsername(@Param("username") String username);
}
