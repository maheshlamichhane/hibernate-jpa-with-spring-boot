create table person
(
 id integer not null,
name varchar(255) not null,
location varchar(255),
birth_date timestamp,
primary key(id)
);

insert into person values(1001,'Mahesh Lamichhane','Kathmandu',sysdate());
insert into person values(1002,'Akash Lamichhane','Baglung',sysdate());