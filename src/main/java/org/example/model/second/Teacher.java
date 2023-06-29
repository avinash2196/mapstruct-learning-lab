package org.example.model.second;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Teacher {
        private String name;
        private String sex;
        private Integer age;
        private List<Student> students;
    }


