package org.example.mapper.second;

import javax.annotation.Generated;
import org.example.model.second.Student;
import org.example.model.second.TargetStudent;
import org.example.model.second.Teacher;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-06-28T01:09:49-0400",
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
