package com.xy.service;

import com.xy.entity.Student;

import java.util.List;

public interface IStudentService {
    public List<Student> selstudent();

    public int delstudent(int sid);

    public Student queryBysid( int sid);

    public void updatestudent(Student student);

    public void insertstudent(String sname);

    public void addStudent(List<Student> resultList);


    public void addStudents(Student student);

    public int selstudentYou();

    public int selstudentCha();

    public int selstudentLiang();

    public int selstudentJige();
}
