package br.ufsm.sci.pi.rest.restImpl;

import br.ufsm.sci.pi.rest.ProdutoRest;
import br.ufsm.sci.pi.service.ProdutoService;
import br.ufsm.sci.pi.utils.CafeUtils;
import br.ufsm.sci.pi.wrapper.ProdutoWrapper;
import br.ufsm.sci.pi.constantes.CafeConstantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class ProdutoRestImpl implements ProdutoRest {
    @Autowired
    ProdutoService produtoService;

    @Override
    public ResponseEntity<String> addNovoProduto(Map<String, String> requestMap) {
        try {

            return produtoService.addNovoProduto(requestMap);

        }catch (Exception ex){
            ex.printStackTrace();
        }
        return CafeUtils.getResponseEntity(CafeConstantes.ALGO_DEU_ERRADO, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<List<ProdutoWrapper>> getTodosProdutos() {

        try {
            return produtoService.getTodosProdutos();

        }catch (Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> editarProduto(Map<String, String> requestMap) {
        try {
            return produtoService.editarProduto(requestMap);

        }catch (Exception ex){
            ex.printStackTrace();
        }
        return CafeUtils.getResponseEntity(CafeConstantes.ALGO_DEU_ERRADO, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @Override
    public ResponseEntity<String> excluirProduto(Integer id) {
        try {
            return produtoService.excluirProduto(id);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return CafeUtils.getResponseEntity(CafeConstantes.ALGO_DEU_ERRADO, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> editarStatus(Map<String, String> requestMap) {
        try {
            return produtoService.editarStatus(requestMap);

        }catch (Exception ex){
            ex.printStackTrace();
        }
        return CafeUtils.getResponseEntity(CafeConstantes.ALGO_DEU_ERRADO, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<List<ProdutoWrapper>> getPelaCategoria(Integer id) {
        try {
            return produtoService.getPelaCategoria(id);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<ProdutoWrapper> getProdutoPeloId(Integer id) {
        try {
            return produtoService.getProdutoPeloId(id);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ProdutoWrapper(), HttpStatus.INTERNAL_SERVER_ERROR);

    }
}
