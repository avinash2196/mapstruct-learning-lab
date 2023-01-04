package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TargetObject {
    private String key;
    private String obj1;
    private String val1;
    private String val2;
    private String obj2;
}
