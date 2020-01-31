package com.foxminded.university.dao.impl;

import com.foxminded.university.dao.CrudDao;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.Optional;

public abstract class AbstractDao<T> implements CrudDao<T> {

    private final String sqlFind;
    private final String sqlDelete;
    private final String sqlUpdate;
    private final String sqlInsert;
    private final String sqlExists;
    protected final RowMapper<T> mapper;
    protected final JdbcTemplate jdbcTemplate;

    protected AbstractDao(String sqlFind, String sqlDelete, String sqlUpdate,
                          String sqlInsert, String sqlExists, RowMapper<T> mapper, JdbcTemplate jdbcTemplate) {
        this.sqlFind = sqlFind;
        this.sqlDelete = sqlDelete;
        this.sqlUpdate = sqlUpdate;
        this.sqlInsert = sqlInsert;
        this.sqlExists = sqlExists;
        this.mapper = mapper;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<T> getById(Integer id) {
        T entity = jdbcTemplate.queryForObject(sqlFind, new Object[]{id}, mapper);
        return Optional.ofNullable(entity);
    }

    @Override
    public boolean deleteById(Integer id) {
        return jdbcTemplate.update(sqlDelete, id) > 0;
    }

    @Override
    public boolean update(T entity) {
        return jdbcTemplate.update(sqlUpdate, getUpdateArgs(entity)) > 0;
    }

    @Override
    public boolean create(T entity) {
        return jdbcTemplate.update(sqlInsert, getCreateArgs(entity)) > 0;
    }

    @Override
    public boolean isExist(Integer id) {
        return jdbcTemplate.queryForObject(sqlExists, Boolean.class, id);
    }

    protected abstract Object[] getUpdateArgs(T entity);

    protected abstract Object[] getCreateArgs(T entity);

}
