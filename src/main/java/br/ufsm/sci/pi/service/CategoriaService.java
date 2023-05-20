package br.ufsm.sci.pi.service;

import br.ufsm.sci.pi.model.Categoria;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface CategoriaService {

    ResponseEntity<String> addNovaCategoria(Map<String, String> requestMap);

    ResponseEntity<List<Categoria>> getTodasCategorias(String filterValue);

    ResponseEntity<String> editarCategoria(Map<String, String> requestMap);

    ResponseEntity<String> excluirCategoria(Integer id);
}
