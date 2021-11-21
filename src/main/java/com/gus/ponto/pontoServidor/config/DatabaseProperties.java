package com.gus.ponto.pontoServidor.config;

import com.google.gson.Gson;
import org.springframework.stereotype.Component;

@Component
public class DatabaseProperties {
    private final String ipBanco;
    private final int portaBanco;
    private final String nomeBanco;
    private final String usuario;
    private final String senha;
    
    public DatabaseProperties() throws Exception{
        ipBanco = "localhost";
        portaBanco = 5432;
        nomeBanco = "bancoPonto";
        usuario = "postgres";
        senha = "postgres";
    }
    
     public String getUrl() {
        return "jdbc:postgresql://" + ipBanco + ":" + portaBanco + "/" + nomeBanco;
    }

    public String getDriver() {
        return "org.postgresql.Driver";
    }

    public String getUsuario() {
        return usuario;
    }

    public String getSenha() {
        return senha;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
