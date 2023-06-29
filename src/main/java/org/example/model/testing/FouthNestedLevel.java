package org.example.model.testing;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FouthNestedLevel {
    private FifthNestedLevel fifthNestedLevel;
}
