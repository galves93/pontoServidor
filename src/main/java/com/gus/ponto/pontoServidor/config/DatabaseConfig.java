package com.gus.ponto.pontoServidor.config;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseConfig {

    private final DatabaseProperties dbProperties;

    @Autowired
    public DatabaseConfig(DatabaseProperties dbProperties) {
        this.dbProperties = dbProperties;
    }

    @Bean
    public DataSource dataSource() {
        DataSource ds = DataSourceBuilder
                .create()
                .username(dbProperties.getUsuario())
                .password(dbProperties.getSenha())
                .url(dbProperties.getUrl())
                .driverClassName(dbProperties.getDriver())
                .build();

        return ds;
    }

//    @Bean;
//    public PostgresUtil postgresUtil() {
//        return new PostgresUtil();
//    }
    }
