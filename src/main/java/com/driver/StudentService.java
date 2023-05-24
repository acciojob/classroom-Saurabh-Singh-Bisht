package com.driver;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StudentService {
    private StudentRepository studentRepository = new StudentRepository();

    public void addStudent(Student student) {
        studentRepository.add(student);
    }

    public void addTeacher(Teacher teacher) {
        studentRepository.add(teacher);
    }

    public void addStudentTeacherPair(String student, String teacher) {
        Optional<Student> studentOptional = studentRepository.getStudent(student);
        Optional<Teacher> teacherOptional = studentRepository.getTeacher(teacher);
        if(studentOptional.isEmpty()){
            throw new RuntimeException("Student Not Found");
        }
        if(teacherOptional.isEmpty()){
            throw new RuntimeException("Teacher Not Found");
        }
        Teacher teacher1 = teacherOptional.get();
        teacher1.setNumberOfStudents(teacher1.getNumberOfStudents()+1);
        studentRepository.add(teacher1);

        studentRepository.add(student, teacher);
    }

    public Student getStudentByName(String name) {
        Optional<Student> studentOpt = studentRepository.getStudent(name);
        if(studentOpt.isPresent()){
            return studentOpt.get();
        }
        throw new StudentNotFoundException("Student in not present in Database");
    }

    public Teacher getTeacherByName(String name) {
        Optional<Teacher> teacherOpt = studentRepository.getTeacher(name);
        if(teacherOpt.isPresent()){
            return teacherOpt.get();
        }
        throw new TeacherNotFoundException("Teacher is not present in Database");
    }

    public List<String> getStudentByTeacher(String teacher) {
        Optional<List<String>> studentList = studentRepository.getStudentByTeacher(teacher);
        if(studentList.isPresent()){
            return studentList.get();
        }
        return new ArrayList<>();
    }

    public List<String> getAllStudents() {
        return studentRepository.getAllStudents();
    }

    public void deleteTeacherByName(String teacher) {
        List<String> studentList = getStudentByTeacher(teacher);
        studentRepository.deleteTeacher(teacher);
        for(String stud: studentList){
            studentRepository.deleteStudent(stud);
        }
    }

    public void deleteAllTeachers() {
        List<String> teachersList = studentRepository.getAllTeachers();
        for(String teach: teachersList){
            studentRepository.deleteTeacher(teach);
        }
    }
}
