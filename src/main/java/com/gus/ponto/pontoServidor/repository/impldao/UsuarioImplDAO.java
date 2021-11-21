package com.gus.ponto.pontoServidor.repository.impldao;

import com.gus.ponto.pontoServidor.model.FiltroModel;
import com.gus.ponto.pontoServidor.model.UsuarioModel;
import com.gus.ponto.pontoServidor.repository.ProjectDAO;
import com.gus.ponto.pontoServidor.repository.UsuarioDAO;
import com.gus.ponto.pontoServidor.utils.NestedRowMapper;
import static java.lang.Integer.parseInt;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.util.MultiValueMap;

@Repository
public class UsuarioImplDAO extends ProjectDAO implements UsuarioDAO {

    @Override
    public UsuarioModel getUsuario(MultiValueMap<String, String> header) throws DataAccessException, Exception {
        try {
            int id_cliente = parseInt(HeadersFiltros(header));

            StringBuilder sql = new StringBuilder();
            sql.append(" SELECT * FROM usuario");
            sql.append(" WHERE id = " + id_cliente);

            return jdbc.queryForObject(sql.toString(), new NestedRowMapper<>(UsuarioModel.class));

        } catch (EmptyResultDataAccessException ex) {
            System.out.println(ex);
            return null;
        }
    }

    @Override
    public List<UsuarioModel> getListUsuario() throws DataAccessException, Exception {
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT * FROM usuario");

            return jdbc.query(sql.toString(), new NestedRowMapper<>(UsuarioModel.class));
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public void setUsuario(UsuarioModel user) throws DataAccessException, Exception {
        try {
            StringBuilder sql = new StringBuilder();

            sql.append(" INSERT INTO usuario ( ");
            sql.append(" id,");
            sql.append(" login,");
            sql.append(" senha,");
            sql.append(" nome,");
            sql.append(" chavegerente,");
            sql.append(" gerente )");
            sql.append(" VALUES (?,?,?,?,?,?);");

            user.setId(getIdUser());

            final PreparedStatementCreator psc = (final Connection connection) -> {
                final PreparedStatement ps = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);

                ps.setInt(1, user.getId());
                ps.setString(2, user.getLogin());
                ps.setString(3, user.getSenha());
                ps.setString(4, user.getNome());
                ps.setString(5, user.getChavegerente());
                ps.setBoolean(6, user.isGerente());

                return ps;
            };

            final KeyHolder holder = new GeneratedKeyHolder();

            jdbc.update(psc, holder);

        } catch (EmptyResultDataAccessException e) {
        }
    }

    private Integer getIdUser() {
        int idUsuario = -1;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append(" SELECT MAX(id) FROM usuario");

            idUsuario = jdbc.queryForObject(sql.toString(), Integer.class);

            return idUsuario + 1;

        } catch (Exception e) {
            return idUsuario;
        }
    }

    private String HeadersFiltros(MultiValueMap<String, String> headers) throws DataAccessException, Exception {
        String retornoFiltro = "";
        try {
            FiltroModel id_cliente = new FiltroModel();

            headers.forEach((key, value) -> {
                if ("idcliente".equals(key)) {
                    id_cliente.setId_cliente(value.stream().collect(Collectors.joining(",")));
                }
            });

            retornoFiltro = id_cliente.getId_cliente();

            if (retornoFiltro == null) {
                throw new Exception("Id Cliente nao pode ser nulo");
            } else {
                return retornoFiltro;
            }
        } catch (Exception e) {
            return retornoFiltro = ("Erro " + e);
        }

    }

}
