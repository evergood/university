package com.foxminded.university.service.impl;

import com.foxminded.university.dao.StudentDao;
import com.foxminded.university.dao.impl.StudentDaoImpl;
import com.foxminded.university.domain.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class StudentService {

    private StudentDao studentDao;

    @Autowired
    public StudentService(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    public Optional<Student> getById(Integer id) {
        return studentDao.getById(id);
    }

    public boolean delete(Student student) {
        return studentDao.delete(student);
    }

    public boolean update(Student student) {
        return studentDao.update(student);
    }

    public boolean create(Student student) {
        return studentDao.create(student);
    }

    public boolean isExist(Student student) {
        return studentDao.isExist(student);
    }

    public boolean putMark(Integer studentId, Integer courseId, Integer mark) {
        return studentDao.putMark(studentId, courseId, mark);
    }

    public Map<String, Character> viewMarks(Integer studentId) {
        return studentDao.viewMarks(studentId);
    }

    public List<String> viewStudentCourses(Integer courseId) {
        return studentDao.viewStudentCourses(courseId);
    }

    public boolean insertStudentTimeUnit(Integer studentId, String courseName, Integer timeInitId) {
        return studentDao.insertStudentTimeUnit(studentId, courseName, timeInitId);
    }

    public Map<String, Integer> getStudentSchedule(Integer studentId) {
        return studentDao.getStudentSchedule(studentId);
    }


}
