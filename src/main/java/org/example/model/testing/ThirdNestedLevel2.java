package org.example.model.testing;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ThirdNestedLevel2 {
private List<FouthNestedLevel> values;
}
