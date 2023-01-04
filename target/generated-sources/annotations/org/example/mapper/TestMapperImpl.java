package org.example.mapper;

import javax.annotation.Generated;
import org.example.model.Student;
import org.example.model.TargetStudent;
import org.example.model.Teacher;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-01-03T21:22:28-0500",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.1 (Oracle Corporation)"
)
@Component
public class TestMapperImpl implements TestMapper {

    @Override
    public TargetStudent toTargetStudent(Student student) {
        if ( student == null ) {
            return null;
        }

        TargetStudent.TargetStudentBuilder targetStudent = TargetStudent.builder();

        targetStudent.studentName( student.getName() );
        targetStudent.studentSex( student.getSex() );
        targetStudent.studentAge( student.getAge() );

        return targetStudent.build();
    }

    @Override
    public void updateTargetStudentFromTeacher(Teacher teacher, TargetStudent targetStudent) {
        if ( teacher == null ) {
            return;
        }

        targetStudent.setTeacherName( teacher.getName() );
        targetStudent.setTeacherSex( teacher.getSex() );
        targetStudent.setTeacherAge( teacher.getAge() );
    }
}
