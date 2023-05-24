package com.driver;

import java.util.*;

public class StudentRepository {
    private Map<String, Student> studentData = new HashMap<>();
    private Map<String, Teacher> teacherData = new HashMap<>();
    private Map<String, ArrayList<String>> teacherStudentMap = new HashMap<>();
    public void add(Student student) {
        studentData.put(student.getName(), student);
    }

    public void add(Teacher teacher) {
        teacherData.put(teacher.getName(), teacher);
    }

    public Optional<Student> getStudent(String student) {
        if(studentData.containsKey(student)){
            return Optional.of(studentData.get(student));
        }
        return Optional.empty();
    }

    public Optional<Teacher> getTeacher(String teacher) {
        if(teacherData.containsKey(teacher)){
            return Optional.of(teacherData.get(teacher));
        }
        return Optional.empty();
    }

    public void add(String student, String teacher) {
        ArrayList<String> studentList = teacherStudentMap.getOrDefault(teacher, new ArrayList<>());
        studentList.add(student);
        teacherStudentMap.put(teacher, studentList);
    }

    public Optional<List<String>> getStudentByTeacher(String teacher) {
        if(teacherStudentMap.containsKey(teacher)){
            return Optional.of(teacherStudentMap.get(teacher));
        }
        return Optional.empty();
    }

    public List<String> getAllStudents() {
        return new ArrayList<>(studentData.keySet());
    }

    public void deleteStudent(String stud) {
        studentData.remove(stud);
    }

    public void deleteTeacher(String teacher) {
        teacherData.remove(teacher);
        teacherStudentMap.remove(teacher);
    }

    public List<String> getAllTeachers() {
        return new ArrayList<>(teacherData.keySet());
    }
}
