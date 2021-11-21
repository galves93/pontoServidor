package com.gus.ponto.pontoServidor.repository.impldao;

import com.gus.ponto.pontoServidor.model.DataHoraModel;
import com.gus.ponto.pontoServidor.model.UsuarioModel;
import com.gus.ponto.pontoServidor.repository.DataHoraDAO;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;
import com.gus.ponto.pontoServidor.repository.ProjectDAO;
import com.gus.ponto.pontoServidor.utils.NestedRowMapper;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

@Repository
public class DataHoraImplDAO extends ProjectDAO implements DataHoraDAO{
    
    @Override
    public List<Integer> getIdUsuario() throws DataAccessException, Exception{
        try {
            StringBuilder sql = new StringBuilder();
            
            sql.append("SELECT idusuario FROM datahoramarcada GROUP BY idusuario");
            
            return jdbc.queryForList(sql.toString(), Integer.class);
            
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
    
    @Override
    public List<DataHoraModel> getDataHora(int idusuario)throws DataAccessException, Exception{
        try {
            StringBuilder sql = new StringBuilder();
            sql.append(" SELECT data, hora FROM datahoramarcada");
            sql.append(" WHERE idusuario = " + idusuario);
            
            
            return jdbc.query(sql.toString(), new NestedRowMapper<>(DataHoraModel.class));
            
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
    
    
    @Override
    public void setDataHora(UsuarioModel idUsuario) throws DataAccessException, Exception {
        try {
            StringBuilder sql = new StringBuilder();

            sql.append(" INSERT INTO datahoramarcada ( ");
            sql.append(" idusuario,");
            sql.append(" data,");
            sql.append(" hora )");
            sql.append(" VALUES (?,?,?);");


            final PreparedStatementCreator psc = (final Connection connection) -> {
                Date today = new Date(System.currentTimeMillis());
                Time now = new Time(System.currentTimeMillis());
                final PreparedStatement ps = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);

                ps.setInt(1, idUsuario.getId());
                ps.setDate(2, today);
                ps.setTime(3, now);

                return ps;
            };

            final KeyHolder holder = new GeneratedKeyHolder();

            jdbc.update(psc, holder);

        } catch (EmptyResultDataAccessException e) {
        }
    }
}
