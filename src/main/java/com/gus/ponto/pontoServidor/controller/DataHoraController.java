package com.gus.ponto.pontoServidor.controller;

import com.gus.ponto.pontoServidor.model.DataHoraModel;
import com.gus.ponto.pontoServidor.model.UsuarioModel;
import com.gus.ponto.pontoServidor.service.DataHoraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class DataHoraController {

    private final DataHoraService service;

    @Autowired
    DataHoraController(DataHoraService service) {
        this.service = service;
    }

    @GetMapping("/getdatahora")
    public ResponseEntity getDataHora() {
        return service.getDataHora();
    }

    @PostMapping("/setdatahora")
    public ResponseEntity setDataHora(@RequestBody UsuarioModel idUsuario) {
        return service.setDataHora(idUsuario);
    }
}
