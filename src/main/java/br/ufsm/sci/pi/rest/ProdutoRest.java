package br.ufsm.sci.pi.rest;

import br.ufsm.sci.pi.wrapper.ProdutoWrapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping(path = "/produto")
public interface ProdutoRest {

    @PostMapping(path = "/add")
    ResponseEntity<String> addNovoProduto(@RequestBody Map<String, String> requestMap);

    @GetMapping(path = "/get")
    ResponseEntity<List<ProdutoWrapper>> getTodosProdutos();

    @PostMapping(path = "/editar")
    ResponseEntity<String> editarProduto(@RequestBody Map<String, String> requestMap);


    @PostMapping(path = "/excluir/{id}")
    ResponseEntity<String> excluirProduto(@PathVariable Integer id);

    @PostMapping(path = "/editarStatus")
    ResponseEntity<String> editarStatus(@RequestBody Map<String, String> requestMap);

    @GetMapping(path = "/getPelaCategoria/{id}")
    ResponseEntity<List<ProdutoWrapper>> getPelaCategoria(@PathVariable Integer id);

    @GetMapping(path = "/getPeloId/{id}")
    ResponseEntity<ProdutoWrapper> getProdutoPeloId (@PathVariable Integer id);

}
