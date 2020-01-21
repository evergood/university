package com.foxminded.university.menu;

import com.foxminded.university.dao.CrudDao;
import com.foxminded.university.domain.Lecturer;
import com.foxminded.university.domain.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MenuController {

    private static final String MENU = "Pick an option\n" +
            "\tA. Find a student by ID\n" +
            "\tB. Find a lecturer by ID\n" +
            "\tC. Add new student\n" +
            "\tD. Delete student by ID\n" +
            "\tE. Update student's info\n";

    private final MenuView view;
    private final CrudDao<Student> studentDao;
    private final CrudDao<Lecturer> lecturerDao;

    @Autowired
    public MenuController(MenuView view, CrudDao<Student> studentDao, CrudDao<Lecturer> lecturerDao) {
        this.view = view;
        this.studentDao = studentDao;
        this.lecturerDao = lecturerDao;
    }

    public void executeMenu() {
        while (true) {
            view.printText(MENU);
            switch (view.readOption()) {
                case 'A':
                    findStudentById();
                case 'B':
                    findLecturerById();
                case 'C':
                    addNewStudent();
                case 'D':
                    deleteStudentById();
                case 'E':
                    updateStudentInfo();
                default:
                    view.printText("Pick an option from the list");
            }
        }
    }

    public void findStudentById() {
        view.printText("Input student ID");
        int id = view.readDigit();
        Student student = studentDao.getById(id).get();
        view.printText(student.toString());
    }

    public void findLecturerById() {
        view.printText("Input lecturer ID");
        int id = view.readDigit();
        Lecturer lecturer = lecturerDao.getById(id).get();
        view.printText(lecturer.toString());
    }

    public void addNewStudent() {
        view.printText("Input student's ID");
        int id = view.readDigit();
        view.printText("Input student's first name");
        String firstName = view.readText();
        view.printText("Input student's last name");
        String lastName = view.readText();
        studentDao.create(Student.builder()
                .withId(id)
                .withFirstName(firstName)
                .withLastName(lastName)
                .build());

    }

    public void deleteStudentById() {
        view.printText("Input student ID");
        int id = view.readDigit();
        studentDao.delete(Student.builder().withId(id).build());
    }

    public void updateStudentInfo() {
        view.printText("Input student's ID");
        int id = view.readDigit();
        view.printText("Input student's first name");
        String firstName = view.readText();
        view.printText("Input student's last name");
        String lastName = view.readText();
        studentDao.update(Student.builder()
                .withId(id)
                .withFirstName(firstName)
                .withLastName(lastName)
                .build());
    }
}
