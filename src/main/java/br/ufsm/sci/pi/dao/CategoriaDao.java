package br.ufsm.sci.pi.dao;

import br.ufsm.sci.pi.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoriaDao extends JpaRepository<Categoria, Integer> {

    List<Categoria> getTodasCategorias();

}
