package com.gus.ponto.pontoServidor.utils;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import org.hibernate.TypeMismatchException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.NotWritablePropertyException;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.JdbcUtils;

public class  NestedRowMapper<T> implements RowMapper<T> {
    private final Logger LOGGER = LoggerFactory.getLogger(NestedRowMapper.class);
    private final Class<T> mappedClass;

    public NestedRowMapper(Class<T> mappedClass) {
        this.mappedClass = mappedClass;
    }

    @Override
    public T mapRow(ResultSet rs, int rowNum) throws SQLException {

        T mappedObject = BeanUtils.instantiateClass(this.mappedClass);
        BeanWrapper bw = PropertyAccessorFactory.forBeanPropertyAccess(mappedObject);

        bw.setAutoGrowNestedPaths(true);
        bw.getPropertyDescriptors();

        ResultSetMetaData meta_data = rs.getMetaData();
        int columnCount = meta_data.getColumnCount();

        for (int index = 1; index <= columnCount; index++) {

            try {

                String column = JdbcUtils.lookupColumnName(meta_data, index);
                Object value = JdbcUtils.getResultSetValue(rs, index, Class.forName(meta_data.getColumnClassName(index)));

                bw.setPropertyValue(column, value);

            } catch (TypeMismatchException | NotWritablePropertyException | ClassNotFoundException e) {
                LOGGER.error("Erro", e);
            }
        }

        return mappedObject;
    }
}
