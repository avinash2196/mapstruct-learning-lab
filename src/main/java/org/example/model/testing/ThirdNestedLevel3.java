package org.example.model.testing;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ThirdNestedLevel3 {
private FourthNestedLevel2 attributes;
}
