package com.gus.ponto.pontoServidor.repository.impldao;

import com.gus.ponto.pontoServidor.model.DataHoraModel;
import java.util.List;

public class RetornoDataHoraModel {
    private int idusuario;
    private List<DataHoraModel> horarios;

    public int getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
    }

    public List<DataHoraModel> getHorarios() {
        return horarios;
    }

    public void setHorarios(List<DataHoraModel> horarios) {
        this.horarios = horarios;
    }
    
    
}
