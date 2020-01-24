package com.foxminded.university.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.Optional;

public abstract class AbstractDao<T, E> implements CrudDao<T> {

    private final String sqlFind;
    private final String sqlDelete;
    private final String sqlUpdate;
    private final String sqlInsert;
    private final String sqlExists;
    private final RowMapper<T> mapper;
    private final JdbcTemplate jdbcTemplate;

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
    public boolean delete(T entity) {
        return jdbcTemplate.update(sqlDelete, getDeleteArgs(entity)) > 0;
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
    public boolean isExist(T entity) {
        return jdbcTemplate.queryForObject(sqlExists, Boolean.class, getExistArgs(entity));
    }

    protected abstract Object[] getDeleteArgs(T entity);

    protected abstract Object[] getUpdateArgs(T entity);

    protected abstract Object[] getCreateArgs(T entity);

    protected abstract E getExistArgs(T entity);
}
