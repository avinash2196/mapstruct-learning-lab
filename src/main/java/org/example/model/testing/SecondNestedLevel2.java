package org.example.model.testing;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SecondNestedLevel2 {
    private ThirdNestLevel1 ThirdNestLevel1;
    private ThirdNestedLevel2 thirdNestedLevel2;
    private ThirdNestLevel1 ThirdNestLevel12;
}
