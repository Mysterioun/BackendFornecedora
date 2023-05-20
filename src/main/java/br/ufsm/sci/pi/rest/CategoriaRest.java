package br.ufsm.sci.pi.rest;


import br.ufsm.sci.pi.model.Categoria;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping(path = "/categoria")
public interface CategoriaRest {

    @PostMapping(path = "/add")
    ResponseEntity<String> addNovaCategoria(@RequestBody(required = true)
                                            Map<String, String> requestMap);

    @GetMapping(path = "/get")
    ResponseEntity<List<Categoria>> getTodasCategorias(@RequestParam(required = false)
                                                       String filterValue);

    @PostMapping(path = "/editar")
    ResponseEntity<String> editarCategoria(@RequestBody(required = true)
                                           Map<String, String> requestMap);

    @PostMapping(path = "/excluir/{id}")
    ResponseEntity<String> excluirCategoria(@PathVariable Integer id);
}
