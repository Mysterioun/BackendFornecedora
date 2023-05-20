package br.ufsm.sci.pi.dao;

import br.ufsm.sci.pi.model.Produto;
import br.ufsm.sci.pi.wrapper.ProdutoWrapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface ProdutoDao extends JpaRepository<Produto, Integer> {


    List<ProdutoWrapper> getTodosProdutos();

    @Modifying
    @Transactional
    Integer editarProdutoStatus(@Param("status") String status, @Param("id") Integer id);


    List<ProdutoWrapper> getProdutoPelaCategoria(@Param("id") Integer id);

    ProdutoWrapper getProdutoPeloId(@Param("id") Integer id);

}
