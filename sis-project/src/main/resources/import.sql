INSERT INTO school(name) VALUES('SilverBell School');
INSERT INTO subject(name) VALUES('국어'),('수학'),('영어');
INSERT INTO student(school_id, name) VALUES(1, '이윤선'),(1, '고병룡'),(1, '홍주광');
INSERT INTO student_subject(student_id, subject_id, required_status) VALUES(1, 1, 'REQUIRED'),(2, 2, 'REQUIRED'),(3, 3, 'REQUIRED')