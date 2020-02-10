package com.foxminded.university.menu;

import com.foxminded.university.domain.Lecturer;
import com.foxminded.university.domain.Student;
import com.foxminded.university.domain.User;
import com.foxminded.university.service.major.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(MenuController.class);

    @Autowired
    public MenuController(MenuView view, StudentService studentService,
                          LecturerService lecturerService, UserService userService,
                          CourseService courseService, WeeklyTimeUnitService weeklyTimeUnitService) {
        this.view = view;
        this.studentService = studentService;
        this.lecturerService = lecturerService;
        this.userService = userService;
        this.courseService = courseService;
        this.weeklyTimeUnitService = weeklyTimeUnitService;
    }

    public void executeLoginMenu() {
        view.printText(LOGIN_MENU);
        LOGGER.info("Menu has been displayed");
        switch (view.readOption()) {
            case 'A':
                LOGGER.info("Executing sign in option");
                executeSignIn();
            case 'B':
                LOGGER.info("Executing sign up option");
                executeSignUp();
        }
    }

    public void executeSignIn() {
        view.printText("Input e-mail");
        String email = view.readText();
        view.printText("Input password");
        String password = view.readText();
        LOGGER.info("User to sign in: email " + email + " ,password" + password);
        boolean isRegistered = userService.signIn(email, password);
        LOGGER.info("User is registered: " + isRegistered);
        User currentUser = userService.getByEmail(email).get();
        if (isRegistered) {
            LOGGER.info("Executing menu, current user " + currentUser);
            executeMenu(currentUser);
        } else {
            LOGGER.info("No such user or incorrect password " + currentUser);
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
        LOGGER.info("User to sign up " + user);
        userService.signUp(user);
        LOGGER.info("User has been signed up, returning to menu");
        executeLoginMenu();
    }

    public void executeMenu(User currentUser) {
        while (true) {
            view.printText(MENU);
            switch (view.readOption()) {
                case 'A':
                    LOGGER.info("Executing  option: find student by ID");
                    findStudentById();
                case 'B':
                    LOGGER.info("Executing  option: find lecturer by ID");
                    findLecturerById();
                case 'C':
                    LOGGER.info("Executing  option: add new student");
                    addNewStudent();
                case 'D':
                    LOGGER.info("Executing  option: delete student by ID");
                    deleteStudentById();
                case 'E':
                    LOGGER.info("Executing  option: update student info");
                    updateStudentInfo();
                case 'F':
                    LOGGER.info("Executing  option: put mark to student");
                    putMark(currentUser);
                case 'G':
                    LOGGER.info("Executing  option: view student's marks");
                    viewMarks();
                case 'H':
                    LOGGER.info("Executing  option: find corses by student ID");
                    findCoursesByStudentId();
                case 'I':
                    LOGGER.info("Executing  option: find students by course ID");
                    findStudentsByCourseId();
                case 'J':
                    LOGGER.info("Executing  option: input schedule");
                    inputSchedule();
                case 'K':
                    LOGGER.info("Executing  option: view student's schedule");
                    viewStudentSchedule();
                case 'L':
                    LOGGER.info("Executing  option: update credentials");
                    updateCredentials(currentUser);
                default:
                    view.printText("Pick an option from the list");
            }
        }
    }

    public void findStudentById() {
        view.printText("Input student ID");
        int id = view.readDigit();
        LOGGER.info("Student's ID to find " + id);
        Student student = studentService.getById(id).get();
        LOGGER.info("Student found " + student);
        view.printText(student.toString());
    }

    public void findLecturerById() {
        view.printText("Input lecturer ID");
        int id = view.readDigit();
        LOGGER.info("Lecturer's ID to find " + id);
        Lecturer lecturer = lecturerService.getById(id).get();
        LOGGER.info("Lecturer found " + lecturer);
        view.printText(lecturer.toString());
    }

    public void addNewStudent() {
        view.printText("Input student's ID");
        int id = view.readDigit();
        view.printText("Input student's first name");
        String firstName = view.readText();
        view.printText("Input student's last name");
        String lastName = view.readText();
        Student student = Student.builder()
                .withId(id)
                .withFirstName(firstName)
                .withLastName(lastName)
                .build();
        LOGGER.info("Student to add " + student);
        studentService.create(student);
        LOGGER.info("Student with ID " + id + " created");
    }

    public void deleteStudentById() {
        view.printText("Input student ID");
        int id = view.readDigit();
        LOGGER.info("Student's ID to delete " + id);
        studentService.deleteById(id);
        LOGGER.info("Student with ID " + id + " deleted");
    }

    public void updateStudentInfo() {
        view.printText("Input student's ID");
        int id = view.readDigit();
        view.printText("Input student's first name");
        String firstName = view.readText();
        view.printText("Input student's last name");
        String lastName = view.readText();
        Student student = Student.builder()
                .withId(id)
                .withFirstName(firstName)
                .withLastName(lastName)
                .build();
        LOGGER.info("Student to update " + student);
        studentService.update(student);
        LOGGER.info("Student with ID " + id + " updated");
    }

    public void putMark(User currentUser) {
        view.printText("Insert student's ID");
        Integer studentId = view.readDigit();
        view.printText("Insert course name");
        Integer courseId = view.readDigit();
        view.printText("Input mark");
        Character mark = view.readOption();
        LOGGER.info("Putting mark " + mark + "");
        lecturerService.putMark(currentUser, studentId, courseId, mark);
    }

    public void viewMarks() {
        view.printText("Insert student's ID");
        Integer studentId = view.readDigit();
        LOGGER.info("Student's ID to view marks " + studentId);
        view.printText(studentService.viewMarks(studentId).toString());
        LOGGER.info("Marks has been displayed");
    }

    public void findCoursesByStudentId() {
        view.printText("Insert student's ID");
        Integer studentId = view.readDigit();
        LOGGER.info("Student's ID find courses for" + studentId);
        view.printText(courseService.findCoursesByStudentId(studentId).toString());
        LOGGER.info("Courses have been displayed");
    }

    public void findStudentsByCourseId() {
        view.printText("Insert course ID");
        Integer courseId = view.readDigit();
        LOGGER.info("Course ID to find students for" + courseId);
        view.printText(courseService.findStudentsByCourseId(courseId).toString());
        LOGGER.info("Students have been displayed");
    }

    public void inputSchedule() {
        view.printText("Input student's ID");
        Integer studentId = view.readDigit();
        LOGGER.info("Student's ID to input schedule for" + studentId);
        LOGGER.info("Executing schedule combiner");
        combineSchedule(studentId);
    }

    public void combineSchedule(Integer studentId) {
        view.printText("Input course name.Press enter to exit");
        String courseName = view.readText();
        LOGGER.info("Course name to add in schedule " + courseName);
        while (!courseName.equals("\n")) {
            view.printText("Input time ID");
            Integer timeId = view.readDigit();
            LOGGER.info("Time unit ID to put in schedule " + timeId);
            studentService.insertStudentTimeUnit(studentId, courseName, timeId);
            LOGGER.info("Time unit has been added: " + "student ID " + studentId + " ,course name " + courseName +
                    "time unit ID " + timeId);
        }
    }

    public void viewStudentSchedule() {
        view.printText("Input student's ID");
        Integer studentId = view.readDigit();
        LOGGER.info("Student ID to view schedulr for " + studentId);
        Map<String, Integer> coursesTimes = studentService.getStudentSchedule(studentId);
        LOGGER.info("Getting student schedule...");
        for (Map.Entry<String, Integer> entry : coursesTimes.entrySet()) {
            view.printText(entry.getKey() + " " + weeklyTimeUnitService.getById(entry.getValue()).toString());
        }
        LOGGER.info("Schedule has been displayed");
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
        LOGGER.info("User to be updated " + user);
        userService.updateCredentials(currentUser, user);
        LOGGER.info("User has been updated");
    }
}
