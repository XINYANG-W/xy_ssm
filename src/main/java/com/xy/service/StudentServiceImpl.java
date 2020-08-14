package com.xy.service;

import com.xy.entity.Student;
import com.xy.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl  implements  IStudentService{

    @Autowired
    private StudentMapper studentMapper;


    @Override
    public List<Student> selstudent() {
        return studentMapper.selstudent();
    }

    @Override
    public int delstudent(int sid) {
        return studentMapper.delstudent(sid);
    }

    @Override
    public Student queryBysid(int sid) {
        return studentMapper.queryBysid(sid);
    }

    @Override
    public void updatestudent(Student student) {
        studentMapper.updatestudent(student);
    }

    @Override
    public void insertstudent(String sname) {
        studentMapper.insertstudent(sname);
    }

    @Override
    public void addStudent(List<Student> resultList) {
        studentMapper.addStudent(resultList);
    }

    @Override
    public void addStudents(Student student) {
        studentMapper.addStudents(student);
    }

    @Override
    public int selstudentYou() {


        return studentMapper.selstudentYou();
    }

    @Override
    public int selstudentCha() {
        return studentMapper.selstudentCha();
    }

    @Override
    public int selstudentLiang() {
        return studentMapper.selstudentLiang();
    }

    @Override
    public int selstudentJige() {
        return studentMapper.selstudentJige();
    }


}
