# Exam
This is my exam


my sql!!


1.create tables codes:

use test;
create table Student(
		SNO varchar(20),
		Name varchar(10),
		Age int,
		College varchar(30)
		);
create table Course(
		CourseID varchar(15),
		CourseName varchar(30),
		CourseBeforeID varchar(15)
		);
create table Choose(
		SNO varchar(20),
		CourseID varchar(15),
		Score decimal(5,2)
		)


2.insert code:

insert into student values("S00001","张三",20,"计算机学院");
insert into student values("S00002","李四",19,"通信学院");
insert into student values("S00003","王五",21,"计算机学院");

insert into course values("C1","计算机导论",null);
insert into course values("C2","C语言","C1");
insert into course values("C3","数据结构","C2");

insert into choose values("S00001","C1",95);
insert into choose values("S00001","C2",80);
insert into choose values("S00001","C3",84);
insert into choose values("S00002","C1",80);
insert into choose values("S00002","C2",85);
insert into choose values("S00003","C1",78);
insert into choose values("S00003","C3",70);


3.select codes:

select course.* from course,choose,student where course.CourseID=choose.CourseID and choose.SNO=student.SNO and student.Name="王五";

4.UPDATE

update student set age=age+2 where College="计算机学院";


5.delete codes:

delete student,choose from student,choose,course where student.SNO=choose.SNO and choose.CourseID=course.CourseID and student.Name="李四";




