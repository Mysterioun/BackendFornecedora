package br.ufsm.sci.pi.service.serviceImpl;

import br.ufsm.sci.pi.JWT.JwtFilter;
import br.ufsm.sci.pi.dao.ProdutoDao;
import br.ufsm.sci.pi.model.Categoria;
import br.ufsm.sci.pi.model.Produto;
import br.ufsm.sci.pi.service.ProdutoService;
import br.ufsm.sci.pi.utils.CafeUtils;
import br.ufsm.sci.pi.wrapper.ProdutoWrapper;
import br.ufsm.sci.pi.constantes.CafeConstantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ProdutoServiceImpl implements ProdutoService {

    @Autowired
    ProdutoDao produtoDao;

    @Autowired
    JwtFilter jwtFilter;

    @Override
    public ResponseEntity<String> addNovoProduto(Map<String, String> requestMap) {

        try {
            if(jwtFilter.isAdmin()){

                if(validarProdutoMap(requestMap, false)){
                    produtoDao.save(getProdutoFromMap(requestMap, false));
                    return CafeUtils.getResponseEntity("Produto Adicionado com Sucesso", HttpStatus.OK);
                }
                return CafeUtils.getResponseEntity(CafeConstantes.DADOS_INVALIDOS, HttpStatus.BAD_REQUEST);
            }else{
                return CafeUtils.getResponseEntity(CafeConstantes.ACESSO_NAO_AUTORIZADO, HttpStatus.UNAUTHORIZED);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return CafeUtils.getResponseEntity(CafeConstantes.ALGO_DEU_ERRADO, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    private boolean validarProdutoMap(Map<String, String> requestMap, boolean validarId) {

        if(requestMap.containsKey("nome")){
            if(requestMap.containsKey("id") && validarId){
                return true;
            } else if (!validarId) {
                return true;
            }
        }
        return false;
    }

    private Produto getProdutoFromMap(Map<String, String> requestMap, boolean isAdd) {

        Categoria categoria = new Categoria();
        categoria.setId(Integer.parseInt(requestMap.get("categoriaId")));

        Produto produto = new Produto();
        if(isAdd){
            produto.setId(Integer.parseInt(requestMap.get("id")));
        }else{
            produto.setStatus("true");
        }

        produto.setCategoria(categoria);
        produto.setNome(requestMap.get("nome"));
        produto.setDescricao(requestMap.get("descricao"));
        produto.setPreco(Float.parseFloat(requestMap.get("preco")));
        return produto;
    }


    @Override
    public ResponseEntity<List<ProdutoWrapper>> getTodosProdutos() {
        try {
            return new ResponseEntity<>(produtoDao.getTodosProdutos(), HttpStatus.OK);

        }catch (Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> editarProduto(Map<String, String> requestMap) {
        try {
            if(jwtFilter.isAdmin()){
                if(validarProdutoMap(requestMap, true)){
                    Optional<Produto> optional =  produtoDao.findById(Integer.parseInt(requestMap.get("id")));
                    if(!optional.isEmpty()){
                        Produto produto = getProdutoFromMap(requestMap, true);
                        produto.setStatus(optional.get().getStatus());
                        produtoDao.save(produto);
                        return CafeUtils.getResponseEntity("Produto editado com sucesso", HttpStatus.OK);

                    }else{
                        return CafeUtils.getResponseEntity("Produto não existe", HttpStatus.OK);
                    }
                }else{
                    return CafeUtils.getResponseEntity(CafeConstantes.DADOS_INVALIDOS, HttpStatus.BAD_REQUEST);
                }
            }else{
                return CafeUtils.getResponseEntity(CafeConstantes.ACESSO_NAO_AUTORIZADO, HttpStatus.UNAUTHORIZED);
            }

        }catch (Exception ex){
            ex.printStackTrace();
        }
        return CafeUtils.getResponseEntity(CafeConstantes.ALGO_DEU_ERRADO, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @Override
    public ResponseEntity<String> excluirProduto(Integer id) {
        try {
            if(jwtFilter.isAdmin()){
                Optional optional = produtoDao.findById(id);
                if(!optional.isEmpty()){
                    produtoDao.deleteById(id);
                    return CafeUtils.getResponseEntity("Produto excluido com sucesso", HttpStatus.OK);

                }
                return CafeUtils.getResponseEntity("Id do Produto não existe", HttpStatus.OK);

            }else {
                return CafeUtils.getResponseEntity(CafeConstantes.ACESSO_NAO_AUTORIZADO, HttpStatus.UNAUTHORIZED);
            }

        }catch (Exception ex){
            ex.printStackTrace();
        }

        return CafeUtils.getResponseEntity(CafeConstantes.ALGO_DEU_ERRADO, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> editarStatus(Map<String, String> requestMap) {
        try {
            if(jwtFilter.isAdmin()){
                Optional optional = produtoDao.findById(Integer.parseInt(requestMap.get("id")));
                if(!optional.isEmpty()){
                    produtoDao.editarProdutoStatus(requestMap.get("status"), Integer.parseInt(requestMap.get("id")));
                    return CafeUtils.getResponseEntity("Status do Produto editado com sucesso", HttpStatus.OK);

                }
                return CafeUtils.getResponseEntity("Id do Produto não existe", HttpStatus.OK);
            }else{
                return CafeUtils.getResponseEntity(CafeConstantes.ACESSO_NAO_AUTORIZADO, HttpStatus.UNAUTHORIZED);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return CafeUtils.getResponseEntity(CafeConstantes.ALGO_DEU_ERRADO, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<List<ProdutoWrapper>> getPelaCategoria(Integer id) {
        try{
            return new ResponseEntity<>(produtoDao.getProdutoPelaCategoria(id), HttpStatus.OK);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @Override
    public ResponseEntity<ProdutoWrapper> getProdutoPeloId(Integer id) {
        try {
            return new ResponseEntity<>(produtoDao.getProdutoPeloId(id), HttpStatus.OK);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ProdutoWrapper(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
