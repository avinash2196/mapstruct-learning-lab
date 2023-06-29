package org.example.model.second;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SourceObject {
    private SourceObjectLevel1 sourceObjectLevel1;
}
