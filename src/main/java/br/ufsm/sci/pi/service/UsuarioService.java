package br.ufsm.sci.pi.service;

import br.ufsm.sci.pi.wrapper.UsuarioWrapper;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface UsuarioService {

    ResponseEntity<String> cadastrar(Map<String, String> requestMap);

    ResponseEntity<String> login(Map<String, String> requestMap);

    ResponseEntity<List<UsuarioWrapper>> obterTodosUsuarios();

    ResponseEntity<String> editar(Map<String, String> requestMap);

    ResponseEntity<String> checkToken();

    ResponseEntity<String> esqueceuSenha(Map<String, String> requestMap);

    ResponseEntity<UsuarioWrapper> getUsuarioPeloId(Integer id);

}
