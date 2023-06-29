package org.example.model.testing;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TargetResponse {
    private String id;
    private int aId;
    private int wId;
    private String pId;
    private String gId;
    private String size1;
    private String b1;
    private String b2;
}
