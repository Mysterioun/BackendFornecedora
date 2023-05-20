package br.ufsm.sci.pi.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@RequestMapping(path = "/dashboard")
public interface DashboardRest {

    @GetMapping(path = "/detalhes")
    ResponseEntity<Map<String, Object>> getCount();

}
