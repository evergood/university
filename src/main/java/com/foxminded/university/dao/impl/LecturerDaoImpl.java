package com.foxminded.university.dao.impl;

import com.foxminded.university.dao.LecturerDao;
import com.foxminded.university.domain.Lecturer;
import com.foxminded.university.mapper.LecturerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository("lecturerDao")
public class LecturerDaoImpl extends AbstractDao<Lecturer, Integer> implements LecturerDao {

    private static final String SQL_FIND_LECTURER = "SELECT * FROM lecturers WHERE lecturer_id = ?";
    private static final String SQL_DELETE_LECTURER = "DELETE FROM lecturers WHERE lecturer_id = ?";
    private static final String SQL_UPDATE_LECTURER = "UPDATE lecturers SET first_name = ?, last_name = ? WHERE lecturer_id = ?";
    private static final String SQL_INSERT_LECTURER = "INSERT INTO lecturers(lecturer_id, first_name, last_name) VALUES(?,?,?)";
    private static final String SQL_LECTURER_EXISTS = "SELECT EXISTS(SELECT FROM lecturers WHERE lecturer_id = ?)";

    @Autowired
    public LecturerDaoImpl(DataSource dataSource) {
        super(SQL_FIND_LECTURER, SQL_DELETE_LECTURER, SQL_UPDATE_LECTURER,
                SQL_INSERT_LECTURER, SQL_LECTURER_EXISTS, new LecturerMapper(), new JdbcTemplate(dataSource));
    }

    @Override
    protected Object[] getDeleteArgs(Lecturer lecturer) {
        return new Object[]{lecturer.getId()};
    }

    @Override
    protected Object[] getUpdateArgs(Lecturer lecturer) {
        return new Object[]{lecturer.getFirstName(), lecturer.getLastName(), lecturer.getId()};
    }

    @Override
    protected Object[] getCreateArgs(Lecturer lecturer) {
        return new Object[]{lecturer.getId(), lecturer.getFirstName(), lecturer.getLastName()};
    }

    @Override
    protected Integer getExistArgs(Lecturer lecturer) {
        return lecturer.getId();
    }
}
