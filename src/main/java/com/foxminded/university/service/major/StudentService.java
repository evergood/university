package com.foxminded.university.service.major;

import com.foxminded.university.dao.CourseDao;
import com.foxminded.university.dao.StudentDao;
import com.foxminded.university.domain.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentDao studentDao;
    private final CourseDao courseDao;

    private static final int ELEMENTS_PER_PAGE = 5;

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

    public List<Student> getAllStudents(int page) {
        int numOfStudents = studentDao.getNumOfStudents();
        int maxPage = numOfStudents % ELEMENTS_PER_PAGE == 0 ?
                numOfStudents / ELEMENTS_PER_PAGE :
                numOfStudents / ELEMENTS_PER_PAGE + 1;
        if (page < 0 || page > maxPage) {
            return studentDao.getAllStudents(1, ELEMENTS_PER_PAGE);
        } else {
            return studentDao.getAllStudents(page, ELEMENTS_PER_PAGE);
        }
    }
}
