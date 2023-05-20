package br.ufsm.sci.pi.serviceImpl;

import br.ufsm.sci.pi.dao.CategoriaDao;
import br.ufsm.sci.pi.dao.ProdutoDao;
import br.ufsm.sci.pi.dao.VendaDao;
import br.ufsm.sci.pi.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class DashboardServiceImpl implements DashboardService {
    @Autowired
    CategoriaDao categoriaDao;

    @Autowired
    ProdutoDao produtoDao;

    @Autowired
    VendaDao vendaDao;

    @Override
    public ResponseEntity<Map<String, Object>> getCount() {
        Map<String, Object> map = new HashMap<>();
        map.put("categoria", categoriaDao.count());
        map.put("produto", produtoDao.count());
        map.put("venda", vendaDao.count());
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
