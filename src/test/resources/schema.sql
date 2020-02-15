DROP TABLE IF EXISTS courses CASCADE;
DROP TABLE IF EXISTS rooms CASCADE;
DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS weeklytimeunits CASCADE;
DROP TABLE IF EXISTS studentcourses CASCADE;
DROP TABLE IF EXISTS studenttimeunits CASCADE;
DROP TABLE IF EXISTS studentmarks CASCADE;

CREATE TABLE courses
(
    course_id   INT PRIMARY KEY,
    course_name VARCHAR(255) UNIQUE
);
CREATE TABLE weeklytimeunits
(
    weekly_time_unit_id INT PRIMARY KEY,
    weekday             VARCHAR(255),
    starttime           TIME,
    endtime             TIME
);
CREATE TABLE rooms
(
    room_id INT PRIMARY KEY
);
CREATE TABLE users
(
    user_id    INT PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name  VARCHAR(255) NOT NULL,
    email      VARCHAR(255) NOT NULL,
    password   VARCHAR(255) NOT NULL,
    rank       VARCHAR(255),
    role       VARCHAR(255) NOT NULL
);
CREATE TABLE studentcourses
(
    student_id   INT NOT NULL,
    course_id INT NOT NULL,
    FOREIGN KEY (student_id) REFERENCES users (user_id) ON DELETE CASCADE,
    FOREIGN KEY (course_id) REFERENCES courses (course_id) ON DELETE CASCADE,
    UNIQUE (student_id, course_id)
);
CREATE TABLE studenttimeunits
(
    student_id  INT          NOT NULL,
    course_name VARCHAR(255) NOT NULL,
    timeunit_id INT,
    FOREIGN KEY (student_id) REFERENCES users (user_id) ON DELETE CASCADE,
    FOREIGN KEY (course_name) REFERENCES courses (course_name) ON DELETE CASCADE,
    FOREIGN KEY (timeunit_id) REFERENCES weeklytimeunits (weekly_time_unit_id) ON DELETE CASCADE
);
CREATE TABLE studentmarks
(
    student_id INT PRIMARY KEY,
    course     VARCHAR(255),
    mark       CHAR,
    FOREIGN KEY (student_id) REFERENCES users (user_id) ON DELETE CASCADE,
    FOREIGN KEY (course) REFERENCES courses (course_name) ON DELETE CASCADE,
    UNIQUE (student_id, course)
);
