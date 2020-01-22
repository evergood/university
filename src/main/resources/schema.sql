DROP TABLE IF EXISTS students CASCADE;
DROP TABLE IF EXISTS lecturers CASCADE;
DROP TABLE IF EXISTS courses CASCADE;
DROP TABLE IF EXISTS rooms CASCADE;
DROP TABLE IF EXISTS weeklytimeunits CASCADE;

CREATE TABLE students(
	student_id INT PRIMARY KEY,
	first_name VARCHAR(255),
	last_name VARCHAR(255)
);
CREATE TABLE lecturers(
	lecturer_id INT PRIMARY KEY,
	first_name VARCHAR(255),
	last_name VARCHAR(255)
);
CREATE TABLE courses(
	course_id INT PRIMARY KEY,
	course_name VARCHAR(255)
);
CREATE TABLE weeklytimeunits(
	weekly_time_unit_id INT PRIMARY KEY,
	weekday VARCHAR(255),
	starttime TIME,
	endtime TIME
);
CREATE TABLE rooms(
	room_id INT PRIMARY KEY
);
CREATE TABLE loginpassword(
	login VARCHAR(255) PRIMARY KEY,
	password VARCHAR(255),
	UNIQUE (login, password)
);
CREATE TABLE studentmarks(
	student_id INT PRIMARY KEY,
	course VARCHAR(255),
	mark CHAR,
	FOREIGN KEY (student_id) REFERENCES students(student_id) ON DELETE CASCADE,
	FOREIGN KEY (course) REFERENCES courses(course_id) ON DELETE CASCADE,
	UNIQUE (student_id, course)
);