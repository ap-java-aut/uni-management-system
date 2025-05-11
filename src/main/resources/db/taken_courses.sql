create table taken_courses(
    id int primary key auto_increment,
    presented_course_id int not null,
    student_id int not null,
    grade decimal(4, 2),

    foreign key (presented_course_id) references presented_courses(id),
    foreign key (student_id) references students(id)
);