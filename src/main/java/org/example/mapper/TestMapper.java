package org.example.mapper;

import org.example.model.Student;
import org.example.model.TargetStudent;
import org.example.model.Teacher;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Mapper(componentModel = "spring")
public interface TestMapper {
    @Mappings({
            @Mapping(target = "teacherName", ignore = true),
            @Mapping(target = "teacherSex", ignore = true),
            @Mapping(target = "teacherAge", ignore = true),
            @Mapping(target = "studentName", source = "name"),
            @Mapping(target = "studentSex", source = "sex"),
            @Mapping(target = "studentAge", source = "age")
    })
    TargetStudent toTargetStudent(Student student);

    @Mappings({
            @Mapping(target = "teacherName", source = "name"),
            @Mapping(target = "teacherSex", source = "sex"),
            @Mapping(target = "teacherAge", source = "age"),
            @Mapping(target = "studentName", ignore = true),
            @Mapping(target = "studentSex", ignore = true),
            @Mapping(target = "studentAge", ignore = true)
    })
    void updateTargetStudentFromTeacher(Teacher teacher, @MappingTarget TargetStudent targetStudent);

    default List<TargetStudent> toTargetStudents(Teacher teacher) {
        if (teacher == null) {
            return null;
        }
        List<Student> studentList = teacher.getStudents();
        if (studentList == null) {
            return null;
        }
        if (studentList.isEmpty()) {
            return Collections.emptyList();
        }
        List<TargetStudent> targetStudentList = new ArrayList<>(studentList.size());
        for (Student student : studentList) {
            TargetStudent targetStudent = toTargetStudent(student);
            updateTargetStudentFromTeacher(teacher, targetStudent);
            targetStudentList.add(targetStudent);
        }
        return targetStudentList;
    }
}
