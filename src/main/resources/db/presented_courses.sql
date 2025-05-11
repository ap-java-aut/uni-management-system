create table presented_courses(
    id int primary key auto_increment,
    course_id int not null,
    professor_id int not null,

    foreign key (course_id) references courses(id),
    foreign key (professor_id) references professors(id)
);