create table professors(
    id int primary key auto_increment,
    person_id int not null,
    number nvarchar(8) not null,

    foreign key (person_id) references people(id)
);