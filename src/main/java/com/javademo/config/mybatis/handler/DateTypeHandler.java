package com.javademo.config.mybatis.handler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

/**
 * @Program: javademo
 * @Description:
 * @Author: zls
 * @Date: 2024-04-01 15:15
 **/
@MappedJdbcTypes({JdbcType.TIMESTAMP})
@MappedTypes({LocalDateTime.class, Date.class})
public class DateTypeHandler extends BaseTypeHandler<Date> {
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Date parameter, JdbcType jdbcType)
            throws SQLException {
        Objects.requireNonNull(parameter, "Date parameter must not be null");
        ps.setTimestamp(i, new Timestamp(parameter.getTime()));
    }

    @Override
    public Date getNullableResult(ResultSet rs, String columnName)
            throws SQLException {
        return getNullableDate(rs.getTimestamp(columnName));
    }

    @Override
    public Date getNullableResult(ResultSet rs, int columnIndex)
            throws SQLException {
        return getNullableDate(rs.getTimestamp(columnIndex));
    }

    @Override
    public Date getNullableResult(CallableStatement cs, int columnIndex)
            throws SQLException {
        return getNullableDate(cs.getTimestamp(columnIndex));
    }

    private Date getNullableDate(Timestamp timestamp) {
        return timestamp != null ? new Date(timestamp.getTime()) : null;
    }

}