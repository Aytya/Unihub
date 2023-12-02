create table if not exists _user
(
    id       bigserial primary key,
    first_name     varchar(255) not null,
    last_name     varchar(255) not null,
    email varchar(255) not null unique,
    password varchar(255) not null,
    department varchar(255) not null,
    program varchar(255) not null,
    yearOfEnrollment varchar(255) not null,
    yearOfSubmission varchar(255) not null,
    dateOfBirth varchar(255) not null,
    idNo varchar(255) not null,
    permanentAddress varchar(255) not null,
    maillingAddress varchar(255) not null,
    phone varchar(255) not null,
    nationality varchar(255) not null
);

create table if not exists professor
(
    id              bigserial primary key,
    name     varchar(255) not null,
    student_id bigint       not null,
    faculty_id BIGINT,
    course_id bigint       not null,
    qualification     varchar(255) null
);

create table if not exists course (
    id BIGINT PRIMARY KEY,
    courseName VARCHAR(255),
    courseCode BIGINT UNIQUE,
    courseCreditHour VARCHAR(255),
    preRequisite VARCHAR(255),
    semester VARCHAR(255),

    professor_id BIGINT,
    faculty_id BIGINT
);

-- create table if not exists attendance
-- (
--     id              bigserial primary key,
--     title           varchar(255) not null,
--     description     varchar(255) null,
--     status          varchar(255) not null,
--     expiration_date timestamp    null
--     );

create table if not exists tasks
(
    id              bigserial primary key,
    title           varchar(255) not null,
    description     varchar(255) null,
    status          varchar(255) not null,
    expiration_date timestamp    null
);


create table if not exists finance_cabinet (
     id BIGINT PRIMARY KEY,
     semester BIGINT,
     type VARCHAR(255),
     total_amount BIGINT,
     submit_amount BIGINT,
     date DATE,
     student_id BIGINT,
     FOREIGN KEY (student_id) REFERENCES _user (id)
);
create table if not exists transcript (
    id BIGINT PRIMARY KEY,
    semester VARCHAR(255),
    semesterCreditHour FLOAT,
    GPA FLOAT,
    studentName VARCHAR(255),
    dean VARCHAR(255),
    courseName VARCHAR(255),
    date VARCHAR(255),
    grade FLOAT,
    attendanceStatus BOOLEAN,
    totalAttendance BIGINT,
    totalMarks BIGINT,
    course_id bigint not null,
    constraint fk_transcript_course foreign key (course_id) references course (id) on delete cascade on update no action
);

create table if not exists attendance (
    id BIGINT PRIMARY KEY ,
    duration BIGINT,
    unlock BOOlEAN,
    lastUpdateTime BIGINT,
    startTime DATE,
    group_id BIGINT
);

CREATE TABLE IF NOT EXISTS _group (
   id BIGINT PRIMARY KEY,
   student_id BIGINT,
   attendance_id BIGINT
);

create table if not exists faculty (
    id BIGINT PRIMARY KEY,
    facultyName VARCHAR(255),
    deanName VARCHAR(255),
    managerName VARCHAR(255),
    phoneNumber BIGINT,

    course_id BIGINT,
    professor_id BIGINT,
    student_id BIGINT
);
create table if not exists chat_message (
      id BIGINT PRIMARY KEY,
      sender VARCHAR(255),
      content TEXT,
      local_date_time VARCHAR(255),
      type VARCHAR(255)
);

create table if not exists users_images
(
    student_id bigint       not null,
    image   varchar(255) not null,
    constraint fk_users_images_users foreign key (student_id) references _user (id) on delete cascade on update no action
);

create table if not exists users_roles
(
    user_id bigint       not null,
    role    varchar(255) not null,
    primary key (user_id, role),
    constraint fk_users_roles_users foreign key (user_id) references _user (id) on delete cascade on update no action
);

create table if not exists users_tasks
(
    user_id bigint not null,
    task_id bigint not null,
    primary key (user_id, task_id),
    constraint fk_users_tasks_users foreign key (user_id) references _user (id) on delete cascade on update no action,
    constraint fk_users_tasks_tasks foreign key (task_id) references tasks (id) on delete cascade on update no action
);

create table if not exists students_images (
                                               users_id bigint       not null,
                                               image   varchar(255) not null,
                                               constraint fk_students_images_users foreign key (users_id) references _user (id) on delete cascade on update no action
);

create table if not exists users_roles(
                                          user_id bigint       not null,
                                          role    varchar(255) not null,
                                          primary key (user_id, role),
                                          constraint fk_users_roles_users foreign key (user_id) references _user (id) on delete cascade on update no action
);

create table if not exists users_token
(
    user_id bigint       not null,
    token   varchar(255) not null,
    primary key (user_id, token),
    constraint fk_users_token_users foreign key (user_id) references _user (id) on delete cascade on update no action
);

create table if not exists users_professors (
                                                users_id bigint       not null,
                                                professors bigint  not null,
                                                primary key (users_id, professors),
                                                constraint fk_users_professors_users foreign key (users_id) references _user (id) on delete cascade on update no action,
                                                constraint fk_users_professors_professors foreign key (professors) references professor (id) on delete cascade on update no action
);

alter table faculty add constraint fk_faculty_professors FOREIGN KEY (professor_id) REFERENCES professor (id);
alter table faculty add constraint fk_faculty_courses FOREIGN KEY (course_id) REFERENCES course (id);
alter table faculty add constraint fk_faculty_students FOREIGN KEY (student_id) REFERENCES _user (id);
alter table course add constraint fk_course_professors FOREIGN KEY (professor_id) REFERENCES professor (id);
alter table course add constraint fk_course_faculty FOREIGN KEY (faculty_id) REFERENCES faculty(id);
alter table professor add constraint fk_professor_faculty FOREIGN KEY (faculty_id) REFERENCES faculty(id);
alter table professor add constraint fk_professor_students FOREIGN KEY (student_id) REFERENCES _user (id);
alter table professor add constraint fk_professor_courses FOREIGN KEY (course_id) REFERENCES course (id);
alter table attendance add constraint fk_attendance_group FOREIGN KEY (group_id) REFERENCES _group (id);
alter table _group add constraint fk_group_users FOREIGN KEY (student_id) REFERENCES _user (id);
alter table _group add constraint fk_group_attendance FOREIGN KEY (attendance_id) REFERENCES _user (id);