/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gus.ponto.pontoServidor.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public abstract class ProjectDAO {

    @Autowired
    protected NamedParameterJdbcTemplate jdbcNamed;
    @Autowired
    protected JdbcTemplate jdbc;
}
