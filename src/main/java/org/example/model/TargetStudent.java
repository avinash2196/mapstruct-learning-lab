package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TargetStudent {
   private String teacherName;
        private String teacherSex;
        private Integer teacherAge;
        private String studentName;
        private String studentSex;
        private Integer studentAge;
    }
