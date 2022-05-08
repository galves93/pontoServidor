package com.gus.ponto.pontoServidor.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseConfig {

//    private final DatabaseProperties dbProperties;
    private String dbUrl;

//    @Autowired
//    public DatabaseConfig(DatabaseProperties dbProperties) {
//        this.dbProperties = dbProperties;
//    }

    @Value("${spring.datasource.url}")

    @Bean
    public DataSource dataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(dbUrl);
        return new HikariDataSource(config);

//        DataSource ds = DataSourceBuilder
//                .create()
//                .username(dbProperties.getUsuario())
//                .password(dbProperties.getSenha())
//                .url(dbProperties.getUrl())
//                .driverClassName(dbProperties.getDriver())
//                .build();
//
//        return ds;
    }

}
