package com.gus.ponto.pontoServidor.service;

import com.gus.ponto.pontoServidor.model.DataHoraModel;
import com.gus.ponto.pontoServidor.model.UsuarioModel;
import com.gus.ponto.pontoServidor.repository.impldao.DataHoraImplDAO;
import com.gus.ponto.pontoServidor.repository.impldao.RetornoDataHoraModel;
import java.util.ArrayList;
import java.util.List;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DataHoraService {
    
    private final DataHoraImplDAO dataHoraRepository;
    
    DataHoraService(DataHoraImplDAO dataHoraRepository){
        this.dataHoraRepository = dataHoraRepository;
    }
    
    public ResponseEntity getDataHora(){
         try {
             
             List<Integer> vIdUsuario = dataHoraRepository.getIdUsuario();
             
            List<DataHoraModel> vRetornoDataHora = new ArrayList();
            RetornoDataHoraModel oRetorno = new RetornoDataHoraModel();
            List<RetornoDataHoraModel> vRetorno = new ArrayList();
            
             for(int i_idusuario : vIdUsuario){
                vRetornoDataHora = dataHoraRepository.getDataHora(i_idusuario);
                
                oRetorno.setIdusuario(i_idusuario);
                oRetorno.setHorarios(vRetornoDataHora);
                
                vRetorno.add(oRetorno);
             }
             

            return new ResponseEntity<>(vRetorno, HttpStatus.OK);

        } catch (DataAccessException ex) {
            System.out.println("Erro: " + ex.getMessage());
            return new ResponseEntity<>("Erro: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception ex) {
            System.out.println("Erro: " + ex.getMessage());
            return new ResponseEntity<>("Erro: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    
    public ResponseEntity setDataHora(UsuarioModel idUsuario) {
        try {
            
            dataHoraRepository.setDataHora(idUsuario);
            
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
