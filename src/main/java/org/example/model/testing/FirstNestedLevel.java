package org.example.model.testing;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class FirstNestedLevel {
    private List<SecondNestedLevel1> secondNestedLevel1s =new ArrayList<>();
    private SecondNestedLevel2 secondNestedLevel2;
}
