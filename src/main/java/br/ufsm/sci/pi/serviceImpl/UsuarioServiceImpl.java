package br.ufsm.sci.pi.serviceImpl;

import br.ufsm.sci.pi.JWT.CustomerUserDatailsService;
import br.ufsm.sci.pi.JWT.JwtFilter;
import br.ufsm.sci.pi.JWT.JwtUtil;
import br.ufsm.sci.pi.dao.UsuarioDao;
import br.ufsm.sci.pi.model.Usuario;
import br.ufsm.sci.pi.service.UsuarioService;
import br.ufsm.sci.pi.utils.CafeUtils;
import br.ufsm.sci.pi.utils.EmailUtils;
import br.ufsm.sci.pi.wrapper.ProdutoWrapper;
import br.ufsm.sci.pi.wrapper.UsuarioWrapper;
import com.google.common.base.Strings;
import br.ufsm.sci.pi.constantes.CafeConstantes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    UsuarioDao usuarioDao;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    CustomerUserDatailsService customerUserDatailsService;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    JwtFilter jwtFilter;

    @Autowired
    EmailUtils emailUtils;

    @Override
    public ResponseEntity<String> cadastrar(Map<String, String> requestMap) {
        log.info("Dentro cadastro {}", requestMap);

        try {
            if (validarCadastrarMap(requestMap)) {

                Usuario usuario = usuarioDao.findByEmailId(requestMap.get("email"));
                if (Objects.isNull(usuario)) {

                    usuarioDao.save(getUsuarioFromMap(requestMap));
                    return CafeUtils.getResponseEntity("Cadastrado com Sucesso.", HttpStatus.OK);

                } else {
                    return CafeUtils.getResponseEntity("Email ja existe.", HttpStatus.BAD_REQUEST);
                }

            } else {
                return CafeUtils.getResponseEntity(CafeConstantes.DADOS_INVALIDOS, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return CafeUtils.getResponseEntity(CafeConstantes.ALGO_DEU_ERRADO, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    private boolean validarCadastrarMap(Map<String, String> requestMap) {
        if (requestMap.containsKey("nome") && requestMap.containsKey("numeroContato")
                && requestMap.containsKey("email") && requestMap.containsKey("senha")) {
            return true;
        }
        return false;
    }

    private Usuario getUsuarioFromMap(Map<String, String> requestMap) {

        Usuario usuario = new Usuario();
        usuario.setNome(requestMap.get("nome"));
        usuario.setNumeroContato(requestMap.get("numeroContato"));
        usuario.setEmail(requestMap.get("email"));
        usuario.setSenha(requestMap.get("senha"));
        usuario.setStatus("true");
        usuario.setRole("cliente");
        return usuario;
    }

    @Override
    public ResponseEntity<String> login(Map<String, String> requestMap) {

        log.info("Dentro Login");
        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(requestMap.get("email"), requestMap.get("senha"))
            );
            if (auth.isAuthenticated()) {
                if (customerUserDatailsService.getUserDatail().getStatus().equalsIgnoreCase("true")) {
                    return new ResponseEntity<String>("{\"token\":\"" +
                            jwtUtil.generateToken(customerUserDatailsService.getUserDatail().getEmail(),
                                    customerUserDatailsService.getUserDatail().getRole()) + "\"}",
                            HttpStatus.OK);
                }
            }

        } catch (Exception ex) {
            log.info("{}", ex);
        }
        return new ResponseEntity<String>("{\"Mensagem\":\"" + "Dados Errados." + "\"}",
                HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<List<UsuarioWrapper>> obterTodosUsuarios() {
        try {
            if (jwtFilter.isAdmin()) {
                return new ResponseEntity<>(usuarioDao.obterTodosUsuarios(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new ArrayList<>(), HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> editar(Map<String, String> requestMap) {
        try {

            if (jwtFilter.isAdmin()) {
                Optional<Usuario> optional = usuarioDao.findById(Integer.parseInt(requestMap.get("id")));
                if (!optional.isEmpty()) {
                    usuarioDao.editarStatus(requestMap.get("status"), Integer.parseInt(requestMap.get("id")));
                    return CafeUtils.getResponseEntity("Status do usuario editado com sucesso.", HttpStatus.OK);

                } else {
                    return CafeUtils.getResponseEntity("Id do usuario não existe", HttpStatus.OK);
                }
            } else {
                return CafeUtils.getResponseEntity(CafeConstantes.ACESSO_NAO_AUTORIZADO, HttpStatus.UNAUTHORIZED);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return CafeUtils.getResponseEntity(CafeConstantes.ALGO_DEU_ERRADO, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> checkToken() {
        return CafeUtils.getResponseEntity("true", HttpStatus.OK);
    }


    @Override
    public ResponseEntity<String> esqueceuSenha(Map<String, String> requestMap) {
        try {
            Usuario usuario = usuarioDao.findByEmail(requestMap.get("email"));
            if (!Objects.isNull(usuario) && !Strings.isNullOrEmpty(usuario.getEmail()))

                emailUtils.esqueceuEmail(usuario.getEmail(), "Dados do Cliente", usuario.getSenha());
            return CafeUtils.getResponseEntity("Verifique seu email para receber os dados de login", HttpStatus.OK);


        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return CafeUtils.getResponseEntity(CafeConstantes.ALGO_DEU_ERRADO, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<UsuarioWrapper> getUsuarioPeloId(Integer id) {
        try {
            return new ResponseEntity<>(usuarioDao.obterUsuarioPeloId(id), HttpStatus.OK);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new UsuarioWrapper(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}


