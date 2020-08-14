package com.xy.mapper;

import com.xy.entity.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StudentMapper {

    public List<Student> selstudent();

    public int delstudent(int sid);

    public Student queryBysid(@Param("sid")int sid);

    public void updatestudent(Student student);

    public void insertstudent(@Param("sname")String sname);

    public void addStudent(List<Student> resultList);


    public void addStudents(Student student);

    public int selstudentYou();

    public int selstudentLiang();

    public int selstudentCha();

    public int selstudentJige();
}
