package com.foxminded.university.menu;

import com.foxminded.university.domain.Lecturer;
import com.foxminded.university.domain.Student;
import com.foxminded.university.domain.User;
import com.foxminded.university.service.UserService;
import com.foxminded.university.service.impl.LecturerServiceImpl;
import com.foxminded.university.service.impl.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MenuController {

    private static final String LOGIN_MENU = "Pick an option\n" +
            "\tA. Sign in\n" +
            "\tB. Sign up\n";

    private static final String MENU = "Pick an option\n" +
            "\tA. Find a student by ID\n" +
            "\tB. Find a lecturer by ID\n" +
            "\tC. Add new student\n" +
            "\tD. Delete student by ID\n" +
            "\tE. Update student's info\n" +
            "\tF. Put mark for student\n" +
            "\tG. View student's marks\n";

    private final MenuView view;
    private final StudentServiceImpl studentService;
    private final LecturerServiceImpl lecturerService;
    private final UserService userService;

    @Autowired
    public MenuController(MenuView view, StudentServiceImpl studentService,
                          LecturerServiceImpl lecturerService, UserService userService) {
        this.view = view;
        this.studentService = studentService;
        this.lecturerService = lecturerService;
        this.userService = userService;
    }

    public void executeLoginMenu() {
        view.printText(LOGIN_MENU);
        switch (view.readOption()) {
            case 'A':
                executeSignIn();
            case 'B':
                executeSignUp();
        }
    }

    public void executeSignIn() {
        view.printText("Input login");
        String login = view.readText();
        view.printText("Input password");
        String password = view.readText();
        boolean isRegistered = userService.signIn(login, password);
        if (isRegistered) {
            executeMenu();
        } else {
            view.printText("No such user or incorrect password");
        }
    }

    public void executeSignUp() {
        view.printText("Input login");
        String login = view.readText();
        view.printText("Input password");
        String password = view.readText();
        userService.signUp(User.builder().withLogin(login).withPassword(password).build());
        executeLoginMenu();
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
                case 'F':
                    putMark();
                case 'G':
                    viewMarks();
                default:
                    view.printText("Pick an option from the list");
            }
        }
    }

    public void findStudentById() {
        view.printText("Input student ID");
        int id = view.readDigit();
        Student student = studentService.getById(id).get();
        view.printText(student.toString());
    }

    public void findLecturerById() {
        view.printText("Input lecturer ID");
        int id = view.readDigit();
        Lecturer lecturer = lecturerService.getById(id).get();
        view.printText(lecturer.toString());
    }

    public void addNewStudent() {
        view.printText("Input student's ID");
        int id = view.readDigit();
        view.printText("Input student's first name");
        String firstName = view.readText();
        view.printText("Input student's last name");
        String lastName = view.readText();
        studentService.create(Student.builder()
                .withId(id)
                .withFirstName(firstName)
                .withLastName(lastName)
                .build());

    }

    public void deleteStudentById() {
        view.printText("Input student ID");
        int id = view.readDigit();
        studentService.delete(Student.builder().withId(id).build());
    }

    public void updateStudentInfo() {
        view.printText("Input student's ID");
        int id = view.readDigit();
        view.printText("Input student's first name");
        String firstName = view.readText();
        view.printText("Input student's last name");
        String lastName = view.readText();
        studentService.update(Student.builder()
                .withId(id)
                .withFirstName(firstName)
                .withLastName(lastName)
                .build());
    }

    public void putMark() {
        view.printText("Insert student's ID");
        Integer studentId = view.readDigit();
        view.printText("Insert course name");
        Integer courseId = view.readDigit();
        view.printText("Input mark");
        Integer mark = view.readDigit();
        userService.putMark(studentId, courseId, mark);
    }

    public void viewMarks() {
        view.printText("Insert student's ID");
        Integer studentId = view.readDigit();
        view.printText(userService.viewMarks(studentId).toString());
    }
}
