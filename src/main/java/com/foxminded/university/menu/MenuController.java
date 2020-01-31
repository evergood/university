package com.foxminded.university.menu;

import com.foxminded.university.domain.Lecturer;
import com.foxminded.university.domain.Student;
import com.foxminded.university.domain.User;
import com.foxminded.university.service.Validator;
import com.foxminded.university.service.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

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
            "\tG. View student's marks\n" +
            "\tH. View student's courses\n" +
            "\tI. Input student's schedule\n" +
            "\tJ. View student's schedule\n";

    private final MenuView view;
    private final StudentService studentService;
    private final LecturerService lecturerService;
    private final UserService userService;
    private final WeeklyTimeUnitService weeklyTimeUnitService;
    private final Validator<User> validator;

    @Autowired
    public MenuController(MenuView view, StudentService studentService,
                          LecturerService lecturerService, UserService userService,
                          WeeklyTimeUnitService weeklyTimeUnitService, ValidatorImpl validator) {
        this.view = view;
        this.studentService = studentService;
        this.lecturerService = lecturerService;
        this.userService = userService;
        this.weeklyTimeUnitService = weeklyTimeUnitService;
        this.validator = validator;
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
        User user = User.builder().withLogin(login).withPassword(password).build();
        validator.validate(user);
        userService.signUp(user);
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
                case 'H':
                    viewStudentsCourses();
                case 'I':
                    inputSchedule();
                case 'J':
                    viewStudentSchedule();
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
        studentService.putMark(studentId, courseId, mark);
    }

    public void viewMarks() {
        view.printText("Insert student's ID");
        Integer studentId = view.readDigit();
        view.printText(studentService.viewMarks(studentId).toString());
    }

    public void viewStudentsCourses() {
        view.printText("Insert student's ID");
        Integer studentId = view.readDigit();
        view.printText(studentService.viewStudentCourses(studentId).toString());
    }

    public void inputSchedule() {
        view.printText("Input student's ID");
        Integer studentId = view.readDigit();
        view.printText("Input course name.Press enter to exit");
        combineSchedule(studentId);
    }

    public void combineSchedule(Integer studentId) {
        view.printText("Input course name.Press enter to exit");
        String courseName = view.readText();
        while (!courseName.equals("\n")) {
            view.printText("Input time ID");
            Integer timeId = view.readDigit();
            studentService.insertStudentTimeUnit(studentId, courseName, timeId);
        }
    }

    public void viewStudentSchedule() {
        view.printText("Input student's ID");
        Integer studentId = view.readDigit();
        Map<String, Integer> coursesTimes = studentService.getStudentSchedule(studentId);
        for (Map.Entry<String, Integer> entry : coursesTimes.entrySet()) {
            view.printText(entry.getKey() + " " + weeklyTimeUnitService.getById(entry.getValue()).toString());
        }
    }
}
