package br.ufsm.sci.pi.service;

import br.ufsm.sci.pi.wrapper.ProdutoWrapper;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface ProdutoService {

    ResponseEntity<String> addNovoProduto(Map<String, String> requestMap);

    ResponseEntity<List<ProdutoWrapper>> getTodosProdutos();

    ResponseEntity<String> editarProduto(Map<String, String> requestMap);

    ResponseEntity<String> excluirProduto(Integer id);

    ResponseEntity<String> editarStatus(Map<String, String> requestMap);

    ResponseEntity<List<ProdutoWrapper>> getPelaCategoria(Integer id);

    ResponseEntity<ProdutoWrapper> getProdutoPeloId(Integer id);
}
