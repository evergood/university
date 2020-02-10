package com.foxminded.university.dao.impl;

import com.foxminded.university.dao.CrudDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractDao.class);

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
        LOGGER.debug("Getting entity with ID " + id  + " from DB");
        T entity = jdbcTemplate.queryForObject(sqlFind, new Object[]{id}, mapper);
        LOGGER.debug("Returned entity with ID " + id + ": " + entity);
        return Optional.ofNullable(entity);
    }

    @Override
    public boolean deleteById(Integer id) {
        LOGGER.debug("Deleting entity with ID " + id);
        return jdbcTemplate.update(sqlDelete, id) > 0;
    }

    @Override
    public boolean update(T entity) {
        LOGGER.debug("Updating entity " + entity);
        return jdbcTemplate.update(sqlUpdate, getUpdateArgs(entity)) > 0;
    }

    @Override
    public boolean create(T entity) {
        LOGGER.debug("Creating entity " + entity);
        return jdbcTemplate.update(sqlInsert, getCreateArgs(entity)) > 0;
    }

    @Override
    public boolean isExist(Integer id) {
        LOGGER.debug("Checking if entity with ID " + id + " exists...");
        return jdbcTemplate.queryForObject(sqlExists, Boolean.class, id);
    }

    protected abstract Object[] getUpdateArgs(T entity);

    protected abstract Object[] getCreateArgs(T entity);

}
