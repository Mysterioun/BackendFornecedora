package br.ufsm.sci.pi.service;

import br.ufsm.sci.pi.model.Venda;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface VendaService {
    ResponseEntity<String> gerarReport(Map<String, Object> requestMap);

    ResponseEntity<List<Venda>> getVendas();

    ResponseEntity<byte[]> getPdf(Map<String, Object> requestMap);

    ResponseEntity<String> excluirVenda(Integer id);
}
