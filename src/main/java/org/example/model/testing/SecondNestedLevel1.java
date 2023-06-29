package org.example.model.testing;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SecondNestedLevel1 {
    private String pId;
    private String gId;
    private ThirdNestedLevel3 thirdNestedLevel3;
}
