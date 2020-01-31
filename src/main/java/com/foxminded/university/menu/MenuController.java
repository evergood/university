package com.foxminded.university.menu;

import com.foxminded.university.domain.Lecturer;
import com.foxminded.university.domain.Student;
import com.foxminded.university.domain.User;
import com.foxminded.university.service.major.CourseService;
import com.foxminded.university.service.major.LecturerService;
import com.foxminded.university.service.major.StudentService;
import com.foxminded.university.service.major.UserService;
import com.foxminded.university.service.major.ValidatorImpl;
import com.foxminded.university.service.major.WeeklyTimeUnitService;
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
            "\tH. View courses by student's ID\n" +
            "\tI. View students by courses' ID\n" +
            "\tJ. Input student's schedule\n" +
            "\tK. View student's schedule\n" +
            "\tL. Change user's credentials\n";

    private final MenuView view;
    private final StudentService studentService;
    private final LecturerService lecturerService;
    private final UserService userService;
    private final CourseService courseService;
    private final WeeklyTimeUnitService weeklyTimeUnitService;

    @Autowired
    public MenuController(MenuView view, StudentService studentService,
                          LecturerService lecturerService, UserService userService,
                          CourseService courseService, WeeklyTimeUnitService weeklyTimeUnitService, ValidatorImpl validator) {
        this.view = view;
        this.studentService = studentService;
        this.lecturerService = lecturerService;
        this.userService = userService;
        this.courseService = courseService;
        this.weeklyTimeUnitService = weeklyTimeUnitService;
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
        view.printText("Input e-mail");
        String email = view.readText();
        view.printText("Input password");
        String password = view.readText();
        boolean isRegistered = userService.signIn(email, password);
        User currentUser = userService.getByEmail(email).get();
        if (isRegistered) {
            executeMenu(currentUser);
        } else {
            view.printText("No such user or incorrect password");
        }
    }

    public void executeSignUp() {
        view.printText("Input your ID");
        Integer id = view.readDigit();
        view.printText("Input email");
        String email = view.readText();
        view.printText("Input password");
        String password = view.readText();
        User user = User.builder().withId(id).withEmail(email).withPassword(password).build();
        userService.signUp(user);
        executeLoginMenu();
    }

    public void executeMenu(User currentUser) {
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
                    putMark(currentUser);
                case 'G':
                    viewMarks();
                case 'H':
                    findCoursesByStudentId();
                case 'I':
                    findStudentsByCourseId();
                case 'J':
                    inputSchedule();
                case 'K':
                    viewStudentSchedule();
                case 'L':
                    updateCredentials(currentUser);
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
        studentService.deleteById(id);
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

    public void putMark(User currentUser) {
        view.printText("Insert student's ID");
        Integer studentId = view.readDigit();
        view.printText("Insert course name");
        Integer courseId = view.readDigit();
        view.printText("Input mark");
        Character mark = view.readOption();
        lecturerService.putMark(currentUser, studentId, courseId, mark);
    }

    public void viewMarks() {
        view.printText("Insert student's ID");
        Integer studentId = view.readDigit();
        view.printText(studentService.viewMarks(studentId).toString());
    }

    public void findCoursesByStudentId() {
        view.printText("Insert student's ID");
        Integer studentId = view.readDigit();
        view.printText(courseService.findCoursesByStudentId(studentId).toString());
    }

    public void findStudentsByCourseId() {
        view.printText("Insert course ID");
        Integer courseId = view.readDigit();
        view.printText(courseService.findStudentsByCourseId(courseId).toString());
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

    public void updateCredentials(User currentUser) {
        view.printText("Input user's e-mail");
        String email = view.readText();
        view.printText("Input first name");
        String firstName = view.readText();
        view.printText("Input last name");
        String lastName = view.readText();
        User targetUser = userService.getByEmail(email).get();
        User user = User.builder().withEmail(email)
                .withFirstName(firstName)
                .withLastName(lastName)
                .withPassword(targetUser.getPassword())
                .withId(targetUser.getId())
                .withRole(targetUser.getRole())
                .build();
        userService.updateCredentials(currentUser, user);
    }
}
