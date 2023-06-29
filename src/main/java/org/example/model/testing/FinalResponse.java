package org.example.model.testing;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FinalResponse {
    private  String topElement1;
    private String topElement2;
    private FirstNestedLevel getTopElement3;

}
