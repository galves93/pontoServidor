package com.gus.ponto.pontoServidor.service;

import com.gus.ponto.pontoServidor.model.UsuarioModel;
import com.gus.ponto.pontoServidor.repository.impldao.UsuarioImplDAO;
import java.util.List;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

@Service
public class UsuarioService {

    private final UsuarioImplDAO usuarioRepository;

    public UsuarioService(UsuarioImplDAO usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public ResponseEntity getUsuario(MultiValueMap<String, String> headers) {
        try {
            UsuarioModel retorno = usuarioRepository.getUsuario(headers);

            return new ResponseEntity<>(retorno, HttpStatus.OK);

        } catch (DataAccessException ex) {
            System.out.println("Erro: " + ex.getMessage());
            return new ResponseEntity<>("Erro: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception ex) {
            System.out.println("Erro: " + ex.getMessage());
            return new ResponseEntity<>("Erro: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity getListUsuario() {
        try {
            List<UsuarioModel> retorno = usuarioRepository.getListUsuario();

            return new ResponseEntity<>(retorno, HttpStatus.OK);

        } catch (DataAccessException ex) {
            System.out.println("Erro: " + ex.getMessage());
            return new ResponseEntity<>("Erro: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception ex) {
            System.out.println("Erro: " + ex.getMessage());
            return new ResponseEntity<>("Erro: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity setUsuario(UsuarioModel user) {
        try {
            
            usuarioRepository.setUsuario(user);
            
            return new ResponseEntity<>("Usuario cadastrado", HttpStatus.OK);

        } catch (DataAccessException ex) {
            System.out.println("Erro: " + ex.getMessage());
            return new ResponseEntity<>("Erro: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception ex) {
            System.out.println("Erro: " + ex.getMessage());
            return new ResponseEntity<>("Erro: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
