insert into staff_member (id, first_name, last_name) values
(1, 'Dean', 'Thomas'),
(2, 'Dean', 'Green'),
(3, 'Dean', 'Jones'),
(4, 'Dean', 'Martin'),
(5, 'James', 'Brown'),
(6, 'Judy', 'MIller'),
(7, 'James', 'Davis'),
(8, 'Allson', 'Moore'),
(9, 'Whitney', 'White'),
(10, 'Jack', 'Black'),
(11, 'Queen', 'King');

insert into department (id, name, chair_id) values
(21, 'Humanities', 3),
(22, 'Natural Sciences', 4),
(23, 'Social Sciences', 3);


insert into course (id, name, credits, instructor_id, department_id) values
(31, 'English 101', 3, 10, 21),
(32, 'English 201', 3, 5, 21),
(33, 'English 301', 4, 10, 21),
(34, 'Chemistry', 3, 7, 22),
(35, 'Physics', 3, 7, 22),
(36, 'C Programming', 3, 8, 22),
(37, 'Java Programming', 4, 8, 22),
(38, 'History 101', 3, 6, 23),
(39, 'Anthropology', 3, 11, 23),
(40, 'Sociology', 3, 11, 23),
(41, 'Psychology', 3, 10, 23),
(42, 'Chemistry Lab', 1, 7, 22),
(43, 'Physics Lab', 1, 7, 22);

insert into course_prerequisites (id, id_of_course, id_prerequisite_course) values
(50, 32, 31),
(51, 33, 32),
(52, 35, 34),
(53, 39, 38),
(54, 40, 38),
(55, 41, 38);

ALTER TABLE staff_member ALTER COLUMN id RESTART WITH (SELECT MAX(id) FROM staff_member) + 1;
ALTER TABLE department ALTER COLUMN id RESTART WITH (SELECT MAX(id) FROM department) + 1;
ALTER TABLE course ALTER COLUMN id RESTART WITH (SELECT MAX(id) FROM course) + 1;
ALTER TABLE course_prerequisites ALTER COLUMN id RESTART WITH (SELECT MAX(id) FROM course_prerequisites) + 1;