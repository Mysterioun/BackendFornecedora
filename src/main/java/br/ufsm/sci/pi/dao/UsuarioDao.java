package br.ufsm.sci.pi.dao;

import br.ufsm.sci.pi.model.Usuario;
import br.ufsm.sci.pi.wrapper.ProdutoWrapper;
import br.ufsm.sci.pi.wrapper.UsuarioWrapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface UsuarioDao extends JpaRepository<Usuario, Integer> {

    Usuario findByEmailId(@Param("email")String email);

    List<UsuarioWrapper> obterTodosUsuarios();

    @Transactional
    @Modifying
    Integer editarStatus(@Param("status") String status, @Param("id") Integer id);

    Usuario findByEmail(String email);

    UsuarioWrapper obterUsuarioPeloId(@Param("id") Integer id);

}
