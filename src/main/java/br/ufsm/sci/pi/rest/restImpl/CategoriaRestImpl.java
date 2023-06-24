package br.ufsm.sci.pi.rest.restImpl;

import br.ufsm.sci.pi.model.Categoria;
import br.ufsm.sci.pi.rest.CategoriaRest;
import br.ufsm.sci.pi.service.CategoriaService;
import br.ufsm.sci.pi.utils.CafeUtils;
import br.ufsm.sci.pi.constantes.CafeConstantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class CategoriaRestImpl implements CategoriaRest {


    @Autowired
    CategoriaService categoriaService;


    @Override
    public ResponseEntity<String> addNovaCategoria(Map<String, String> requestMap) {
        try {

            return categoriaService.addNovaCategoria(requestMap);

        }catch (Exception ex){
            ex.printStackTrace();
        }

        return CafeUtils.getResponseEntity(CafeConstantes.ALGO_DEU_ERRADO, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<List<Categoria>> getTodasCategorias(String filterValue) {
        try {

            return categoriaService.getTodasCategorias(filterValue);

        }catch (Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> editarCategoria(Map<String, String> requestMap) {
        try{
            return categoriaService.editarCategoria(requestMap);

        }catch (Exception ex){
            ex.printStackTrace();
        }
        return CafeUtils.getResponseEntity(CafeConstantes.ALGO_DEU_ERRADO, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @Override
    public ResponseEntity<String> excluirCategoria(Integer id) {
        try {
            return categoriaService.excluirCategoria(id);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return CafeUtils.getResponseEntity(CafeConstantes.ALGO_DEU_ERRADO, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
