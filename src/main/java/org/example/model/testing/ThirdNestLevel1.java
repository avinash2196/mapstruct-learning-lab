package org.example.model.testing;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ThirdNestLevel1 {
    private FouthNestedLevel1 b1;
    private FouthNestedLevel1 b2;
}
