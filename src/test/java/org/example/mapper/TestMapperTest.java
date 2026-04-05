package org.example.mapper;

import org.example.model.Student;
import org.example.model.TargetStudent;
import org.example.model.Teacher;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit tests for {@link TestMapper}.
 *
 * <p>Verifies the {@code @MappingTarget} pattern: a target object is first
 * populated from a {@link Student} and then <em>updated in-place</em> with
 * teacher data, combining both sources into a single flat record.</p>
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestMapperImpl.class})
class TestMapperTest {

    @Autowired
    private TestMapper testMapper;

    @Test
    void toTargetStudent_mapsStudentFields() {
        Student student = Student.builder().name("Alice").sex("F").age(20).build();

        TargetStudent result = testMapper.toTargetStudent(student);

        assertThat(result.getStudentName()).isEqualTo("Alice");
        assertThat(result.getStudentSex()).isEqualTo("F");
        assertThat(result.getStudentAge()).isEqualTo(20);
        // Teacher fields must be ignored
        assertThat(result.getTeacherName()).isNull();
        assertThat(result.getTeacherSex()).isNull();
        assertThat(result.getTeacherAge()).isNull();
    }

    @Test
    void updateTargetStudentFromTeacher_overlaysTeacherFields() {
        TargetStudent target = TargetStudent.builder()
                .studentName("Alice").studentSex("F").studentAge(20).build();
        Teacher teacher = Teacher.builder().name("Prof. Smith").sex("M").age(45).build();

        testMapper.updateTargetStudentFromTeacher(teacher, target);

        assertThat(target.getTeacherName()).isEqualTo("Prof. Smith");
        assertThat(target.getTeacherSex()).isEqualTo("M");
        assertThat(target.getTeacherAge()).isEqualTo(45);
        // Student fields must remain unchanged
        assertThat(target.getStudentName()).isEqualTo("Alice");
    }

    @Test
    void toTargetStudents_combinesTeacherAndStudentData() {
        Student alice = Student.builder().name("Alice").sex("F").age(20).build();
        Student bob = Student.builder().name("Bob").sex("M").age(22).build();
        Teacher teacher = Teacher.builder()
                .name("Prof. Johnson").sex("F").age(50)
                .students(Arrays.asList(alice, bob))
                .build();

        List<TargetStudent> results = testMapper.toTargetStudents(teacher);

        assertThat(results).hasSize(2);
        results.forEach(ts -> {
            assertThat(ts.getTeacherName()).isEqualTo("Prof. Johnson");
            assertThat(ts.getTeacherAge()).isEqualTo(50);
        });
        assertThat(results.get(0).getStudentName()).isEqualTo("Alice");
        assertThat(results.get(1).getStudentName()).isEqualTo("Bob");
    }

    @Test
    void toTargetStudents_nullTeacher_returnsNull() {
        assertThat(testMapper.toTargetStudents(null)).isNull();
    }

    @Test
    void toTargetStudents_emptyStudentList_returnsEmptyList() {
        Teacher teacher = Teacher.builder().name("Prof. X").sex("M").age(40)
                .students(List.of()).build();

        assertThat(testMapper.toTargetStudents(teacher)).isEmpty();
    }
}
