package br.ufsm.sci.pi.restImpl;

import br.ufsm.sci.pi.constantes.CafeConstantes;
import br.ufsm.sci.pi.model.Venda;
import br.ufsm.sci.pi.rest.VendaRest;
import br.ufsm.sci.pi.service.VendaService;
import br.ufsm.sci.pi.utils.CafeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class VendaRestImpl implements VendaRest {
    @Autowired
    VendaService vendaService;

    @Override
    public ResponseEntity<String> gerarReport(Map<String, Object> requestMap) {
        try {
            return vendaService.gerarReport(requestMap);

        }catch (Exception ex){
            ex.printStackTrace();
        }
        return CafeUtils.getResponseEntity(CafeConstantes.ALGO_DEU_ERRADO, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<List<Venda>> getVendas() {
        try {
            return vendaService.getVendas();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public ResponseEntity<byte[]> getPdf(Map<String, Object> requestMap) {
        try {
            System.out.println("Dentro de GetPdf"+requestMap);
            return vendaService.getPdf(requestMap);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public ResponseEntity<String> excluirVenda(Integer id) {
        try {
            return vendaService.excluirVenda(id);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return CafeUtils.getResponseEntity(CafeConstantes.ALGO_DEU_ERRADO, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
