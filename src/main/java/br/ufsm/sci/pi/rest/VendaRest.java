package br.ufsm.sci.pi.rest;

import br.ufsm.sci.pi.model.Venda;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping(path = "/venda")
public interface VendaRest {

    @PostMapping(path = "/gerarReport")
    ResponseEntity<String> gerarReport (@RequestBody Map<String, Object> requestMap);

    @GetMapping(path = "/getVendas")
    ResponseEntity<List<Venda>> getVendas();

    @PostMapping(path = "/getPdf")
    ResponseEntity<byte[]> getPdf(@RequestBody Map<String, Object> requestMap);

    @PostMapping(path = "/excluir/{id}")
    ResponseEntity<String> excluirVenda(@PathVariable Integer id);
}
