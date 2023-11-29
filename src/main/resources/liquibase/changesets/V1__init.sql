create table if not exists users
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
    nationality varchar(255) not null,
);

create table if not exists professor
(
    id              bigserial primary key,
    name     varchar(255) not null,
    qualification     varchar(255) null,
    dean          varchar(255) not null,
    course_id bigint not null,
    constraint fk_professor_course_professor foreign key (course_id) references course (id) on delete cascade on update no action
);
create table if not exists course (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    courseName VARCHAR(255),
    courseCode BIGINT UNIQUE,
    courseCreditHour VARCHAR(255),
    preRequisite VARCHAR(255),
    semester VARCHAR(255),
    faculty_id BIGINT,
    FOREIGN KEY (faculty_id) REFERENCES Faculty(id)

);

create table if not exists tasks
(
    id              bigserial primary key,
    title           varchar(255) not null,
    description     varchar(255) null,
    status          varchar(255) not null,
    expiration_date timestamp    null
    );

create table if not exists users_tasks
(
    user_id bigint not null,
    task_id bigint not null,
    primary key (user_id, task_id),
    constraint fk_users_tasks_users foreign key (user_id) references users (id) on delete cascade on update no action,
    constraint fk_users_tasks_tasks foreign key (task_id) references tasks (id) on delete cascade on update no action
    );

create table if not exists professors
(
    id              bigserial primary key,
    name     varchar(255) not null,
    qualification     varchar(255) null,
    dean          varchar(255) not null
);

create table if not exists finance_cabinet (
     id BIGINT PRIMARY KEY AUTO_INCREMENT,
     semester BIGINT,
     type VARCHAR(255),
     total_amount BIGINT,
     submit_amount BIGINT,
     date DATE,
     student_id BIGINT,
     FOREIGN KEY (student_id) REFERENCES users(id)
);

create table if not exists users_roles
(
    user_id bigint       not null,
    role    varchar(255) not null,
    primary key (user_id, role),
    constraint fk_users_roles_users foreign key (user_id) references users (id) on delete cascade on update no action
    );

create table if not exists transcript (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
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
    totalMarks BIGINT
    course_id bigint not null
    constraint fk_transcript_course foreign key (course_id) references course (id) on delete cascade on update no action
);

create table if not exists chat_message (
      id BIGINT PRIMARY KEY AUTO_INCREMENT,
      sender VARCHAR(255),
      content TEXT,
      local_date_time VARCHAR(255),
      type VARCHAR(255)
);

create table if not exists users_images
(
    student_id bigint       not null,
    image   varchar(255) not null,
    constraint fk_users_images_users foreign key (student_id) references users (id) on delete cascade on update no action
);