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

/**
 * TestMapper — demonstrates {@code @MappingTarget} for updating an existing
 * object and combining data from two source objects into one target.
 *
 * <p><b>Problem being solved:</b><br>
 * A {@link TargetStudent} record needs to contain data from <em>both</em> a
 * {@link Student} <em>and</em> its associated {@link Teacher}. Neither
 * MapStruct's standard mapping nor a single-source mapper can express this
 * directly.</p>
 *
 * <p><b>Concept: @MappingTarget — update-in-place</b><br>
 * {@link #updateTargetStudentFromTeacher} does not return a new object; instead
 * it <em>mutates</em> an existing {@link TargetStudent} passed as
 * {@code @MappingTarget}. This is MapStruct's idiomatic pattern for merging a
 * second source into an already-mapped target.</p>
 *
 * <p><b>Concept: ignored fields</b><br>
 * Each mapping method declares {@code ignore = true} for fields it does not own,
 * ensuring one method's mapping does not accidentally overwrite the other's
 * output.</p>
 *
 * <p><b>Concept: default method for orchestration</b><br>
 * {@link #toTargetStudents} is a {@code default} method that orchestrates the
 * two individual mapping methods. MapStruct cannot generate this aggregation
 * automatically because it involves iterating a list from an object that is not
 * a direct source collection.</p>
 */
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
