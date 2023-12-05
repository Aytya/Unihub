create sequence finance_cabinet_seq start with 1 increment by 50;
create sequence token_seq start with 1 increment by 50;
create table _group (id bigserial not null, primary key (id));
create table _user (_group_id bigint, id bigserial not null, mailling_address varchar(1000), permanent_address varchar(1000), date_of_birth varchar(255), department varchar(255), email varchar(255), first_name varchar(255), id_no varchar(255) unique, image varchar(255), last_name varchar(255), nationality varchar(255), password varchar(255), password_confirmation varchar(255), phone varchar(255) unique, program varchar(255), role varchar(255) check (role in ('USER','ADMIN','MANAGER')), year_of_enrollment varchar(255), year_of_submission varchar(255), primary key (id));
create table _user_tasks (tasks_id bigint not null unique, user_id bigint not null);
create table attendance (unlock boolean, _group_id bigint, duration bigint, id bigserial not null, last_update_time bigint, start_time timestamp(6), primary key (id));
create table chat_message (type smallint check (type between 0 and 2), id bigserial not null, content varchar(255), local_date_time varchar(255), sender varchar(255), primary key (id));
create table course_createdto (course_code bigint, id bigserial not null, course_credit_hour varchar(255), course_name varchar(255), faculty_name varchar(255), lecture_time varchar(255), pre_requisite varchar(255), professor_name varchar(255), semester varchar(255), seminar_time varchar(255), primary key (id));
create table courses (course_code bigint unique, faculty_id bigint not null, group_id bigint, id bigserial not null, professor bigint not null, course_credit_hour varchar(255), course_name varchar(255), lecture_time varchar(255), pre_requisite varchar(255), semester varchar(255), seminar_time varchar(255), primary key (id));
create table faculty (id bigserial not null, phone_number bigint unique, dean_name varchar(255), faculty_name varchar(255), manager_name varchar(255), primary key (id));
    create table faculty_course_list (course_list_id bigint not null unique, faculty_id bigint not null);
    create table finance_cabinet (date timestamp(6), id bigint not null, semester bigint, submit_amount bigint, total_amount bigint, user_id bigint unique, type varchar(255), primary key (id));
     create table professor (faculty_id bigint, professors_id bigserial not null, name varchar(255), qualification varchar(255), primary key (professors_id));
    create table professor_course_list (course_list_id bigint not null unique, professor_professors_id bigint not null);
   create table professor_student_list (professor_professors_id bigint not null, student_list_id bigint not null unique);
  create table professors (faculty_id bigint not null, professor_list_professors_id bigint not null unique);
create table students (faculty_id bigint not null, student_list_id bigint not null unique);
 create table task (expiration_date timestamp(6), id bigserial not null, description varchar(255), status varchar(255) check (status in ('TODO','IN_PROGRESS','DONE')), title varchar(255), user_id varchar(255), primary key (id));
 create table token (expired boolean not null, id integer not null, revoked boolean not null, user_id bigint, token varchar(255) unique, token_type varchar(255) check (token_type in ('BEARER')), primary key (id));
 create table transcript (attendance_status boolean not null, gpa float4 not null, marks float4 not null, semester_credit_hour float4 not null, id bigserial not null, total_attendance bigint, total_marks bigint, course_name varchar(255), date varchar(255), dean varchar(255), semester varchar(255), student_name varchar(255), primary key (id));
create table transcript_course (course_id bigint not null unique, transcript_id bigint not null);
create table users_professors (professor_professors_id bigint not null unique, user_id bigint not null);