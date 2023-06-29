package org.example.model.testing;

import lombok.Builder;
import lombok.Data;

import java.util.List;
@Data
@Builder
public class FouthNestedLevel1 {
    private List<Values> values;
}
