package com.foxminded.university.service.major;

import com.foxminded.university.dao.CourseDao;
import com.foxminded.university.dao.StudentDao;
import com.foxminded.university.domain.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentDao studentDao;
    private final CourseDao courseDao;

    @Autowired
    public StudentService(StudentDao studentDao, CourseDao courseDao) {
        this.studentDao = studentDao;
        this.courseDao = courseDao;
    }

    public Optional<Student> getById(Integer id) {
        Optional<Student> studentOptional = studentDao.getById(id);
        studentOptional.ifPresent(value -> value.setCourses(courseDao.findCoursesByStudentId(id)));
        return studentOptional;
    }

    public boolean deleteById(Integer id) {
        return studentDao.deleteById(id);
    }

    public boolean update(Student student) {
        return studentDao.update(student);
    }

    public boolean create(Student student) {
        return studentDao.create(student);
    }

    public boolean isExist(Integer id) {
        return studentDao.isExist(id);
    }

    public Map<String, Character> viewMarks(Integer studentId) {
        return studentDao.viewMarks(studentId);
    }

    public boolean insertStudentTimeUnit(Integer studentId, String courseName, Integer timeInitId) {
        return studentDao.insertStudentTimeUnit(studentId, courseName, timeInitId);
    }

    public Map<String, Integer> getStudentSchedule(Integer studentId) {
        return studentDao.getStudentSchedule(studentId);
    }
}
