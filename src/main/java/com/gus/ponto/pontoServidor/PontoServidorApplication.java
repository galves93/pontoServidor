package com.gus.ponto.pontoServidor;

import java.net.InetAddress;
import java.net.InetAddress;
import java.util.Collections;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
public class PontoServidorApplication implements CommandLineRunner {

    public final static String APP_NAME = "PONTO SERVIDOR";
    public static ConfigurableApplicationContext context;

    public static void main(String[] args) {
        try {
            SpringApplicationBuilder builder = new SpringApplicationBuilder(PontoServidorApplication.class);
            String porta = "9000";
            builder.properties(Collections.singletonMap("server.port", porta));
            builder.headless(false);
            context = builder.run(args);
            System.out.println("IP: " + InetAddress.getLocalHost().getHostAddress());
            System.out.println("Porta: " + porta);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void run(String[] args) throws Exception {
        System.out.println("Ponto Servidor funcinando!");; //To change body of generated methods, choose Tools | Templates.
    }

}
