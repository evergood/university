package com.foxminded.university.dao.impl;

import com.foxminded.university.dao.CrudDao;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@Slf4j
public abstract class AbstractDao<T> implements CrudDao<T> {

    private final String sqlFind;
    private final String sqlDelete;
    private final String sqlUpdate;
    private final String sqlInsert;
    private final String sqlExists;
    private final String sqlGetTotalNum;
    private final String sqlGetAll;
    protected final RowMapper<T> mapper;
    protected final JdbcTemplate jdbcTemplate;

    @Override
    public Optional<T> getById(Integer id) {
        LOGGER.debug(String.format("Getting entity with ID [%d] from DB", id));
        if (isExist(id)) {
            T entity = jdbcTemplate.queryForObject(sqlFind, new Object[]{id}, mapper);
            LOGGER.debug(String.format("Returned entity with ID [%d] : [%s]", id, entity));
            return Optional.ofNullable(entity);
        } else {
            LOGGER.debug("Returning empty optional");
            return Optional.empty();
        }
    }

    @Override
    public boolean deleteById(Integer id) {
        LOGGER.debug(String.format("Deleting entity with ID [%d] ", id));
        return jdbcTemplate.update(sqlDelete, id) > 0;
    }

    @Override
    public boolean update(T entity) {
        LOGGER.debug(String.format("Updating entity [%s]", entity));
        return jdbcTemplate.update(sqlUpdate, getUpdateArgs(entity)) > 0;
    }

    @Override
    public boolean create(T entity) {
        LOGGER.debug(String.format("Creating entity [%s]", entity));
        return jdbcTemplate.update(sqlInsert, getCreateArgs(entity)) > 0;
    }

    @Override
    public boolean isExist(Integer id) {
        LOGGER.debug(String.format("Checking if entity with ID [%d] exists...", id));
        return jdbcTemplate.queryForObject(sqlExists, Boolean.class, id);
    }

    @Override
    public int getNumOfEntities() {
        return jdbcTemplate.queryForObject(sqlGetTotalNum, Integer.class);
    }

    @Override
    public List<T> getAllEntities(int page, int elementsPerPage) {
        int offset = (page - 1) * elementsPerPage;
        return jdbcTemplate.query(sqlGetAll, new Object[]{elementsPerPage, offset},
                rs -> {
                    List<T> items = new ArrayList<>();
                    while (rs.next()) {
                        items.add(mapper.mapRow(rs, 1));
                    }
                    return items;
                });
    }

    protected abstract Object[] getUpdateArgs(T entity);

    protected abstract Object[] getCreateArgs(T entity);

}
