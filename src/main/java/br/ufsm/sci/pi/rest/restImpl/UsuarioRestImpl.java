package br.ufsm.sci.pi.rest.restImpl;

import br.ufsm.sci.pi.rest.UsuarioRest;
import br.ufsm.sci.pi.service.UsuarioService;
import br.ufsm.sci.pi.utils.CafeUtils;
import br.ufsm.sci.pi.wrapper.ProdutoWrapper;
import br.ufsm.sci.pi.wrapper.UsuarioWrapper;
import br.ufsm.sci.pi.constantes.CafeConstantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class UsuarioRestImpl implements UsuarioRest {

    @Autowired
    UsuarioService usuarioService;

    @Override
    public ResponseEntity<String> cadastrar(Map<String, String> requestMap) {
        try {

            return usuarioService.cadastrar(requestMap);

        }catch (Exception ex){
            ex.printStackTrace();
        }
        return CafeUtils.getResponseEntity(CafeConstantes.ALGO_DEU_ERRADO, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> login(Map<String, String> requestMap) {
        try{
            return usuarioService.login(requestMap);

        }catch (Exception ex){
            ex.printStackTrace();
        }
        return CafeUtils.getResponseEntity(CafeConstantes.ALGO_DEU_ERRADO, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @Override
    public ResponseEntity<List<UsuarioWrapper>> obterTodosUsuarios() {
        try{
            return usuarioService.obterTodosUsuarios();

        }catch (Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<List<UsuarioWrapper>>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> editar(Map<String, String> requestMap) {
        try {
            return usuarioService.editar(requestMap);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return CafeUtils.getResponseEntity(CafeConstantes.ALGO_DEU_ERRADO, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> checkToken() {
        try {

            return usuarioService.checkToken();

        }catch (Exception ex){
            ex.printStackTrace();
        }
        return CafeUtils.getResponseEntity(CafeConstantes.ALGO_DEU_ERRADO, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @Override
    public ResponseEntity<String> esqueceuSenha(Map<String, String> requestMap) {
        try{
            return usuarioService.esqueceuSenha(requestMap);

        }catch (Exception ex){
            ex.printStackTrace();
        }
        return CafeUtils.getResponseEntity(CafeConstantes.ALGO_DEU_ERRADO, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<UsuarioWrapper> getUsuarioPeloId(Integer id) {
        try {
            return usuarioService.getUsuarioPeloId(id);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<UsuarioWrapper>(new UsuarioWrapper(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<UsuarioWrapper> getUsuarioLogado(String authorizationHeader) {
        try{
            return usuarioService.getUsuarioLogado(authorizationHeader);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<UsuarioWrapper>(new UsuarioWrapper(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}


