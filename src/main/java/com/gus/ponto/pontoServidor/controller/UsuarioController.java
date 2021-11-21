package com.gus.ponto.pontoServidor.controller;

import com.gus.ponto.pontoServidor.model.UsuarioModel;
import com.gus.ponto.pontoServidor.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController

public class UsuarioController {
     private final UsuarioService service;
    
    @Autowired
    public UsuarioController(UsuarioService service){
        this.service = service;
    }
    
    @GetMapping("/usuario")
    public ResponseEntity getUsuario(@RequestHeader MultiValueMap<String, String> header){
        return service.getUsuario(header);
    }
    
    @GetMapping("/getusuario")
    public ResponseEntity getListUsuario(){
        return service.getListUsuario();
    }
    
    @PostMapping("/setusuario")
    public ResponseEntity setUsuario(@RequestBody UsuarioModel user){
        return service.setUsuario(user);
    };
}
