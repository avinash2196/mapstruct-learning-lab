package org.example.model.second;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SourceObjectLevel1 {
    private List<SourceObjectLevel2Obj1> sourceObjectLevel2Obj1List;
    private List<SourceObjectLevel2Obj2> sourceObjectLevel2Obj2List;

}
