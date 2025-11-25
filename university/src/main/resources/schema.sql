CREATE TABLE staff_member (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  first_name VARCHAR(100) NOT NULL,
  last_name VARCHAR(100) NOT NULL
);


CREATE TABLE department (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(100) NOT NULL,
  chair_id BIGINT
);

ALTER TABLE department ADD FOREIGN KEY (chair_id) REFERENCES staff_member(id);

CREATE TABLE student (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  first_name VARCHAR(100) NOT NULL,
  last_name VARCHAR(100) NOT NULL,
  age INTEGER NOT NULL,
  full_time BOOLEAN NOT NULL
);

CREATE TABLE course (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(100) NOT NULL,
  credits INTEGER NOT NULL,
  instructor_id BIGINT NOT NULL,
  department_id BIGINT NOT NULL
);
ALTER TABLE course ADD FOREIGN KEY (instructor_id) REFERENCES staff_member(id);
ALTER TABLE course ADD FOREIGN KEY (department_id) REFERENCES department(id);

CREATE TABLE course_student (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    course_id BIGINT NOT NULL,
    student_id BIGINT NOT NULL
);

ALTER TABLE course_student ADD FOREIGN KEY (course_id) REFERENCES course(id);
ALTER TABLE course_student ADD FOREIGN KEY (student_id) REFERENCES student(id);

CREATE TABLE department_course (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    course_id BIGINT  NOT NULL,
    department_id BIGINT NOT NULL
);

ALTER TABLE department_course ADD FOREIGN KEY (course_id) REFERENCES course(id);
ALTER TABLE department_course ADD FOREIGN KEY (department_id) REFERENCES department(id);

CREATE TABLE course_prerequisites (
 id BIGINT AUTO_INCREMENT PRIMARY KEY,
 id_of_course BIGINT,
 id_prerequisite_course BIGINT
);

ALTER TABLE course_prerequisites ADD FOREIGN KEY (id_of_course) REFERENCES course(id);
ALTER TABLE course_prerequisites ADD FOREIGN KEY (id_prerequisite_course) REFERENCES course(id);