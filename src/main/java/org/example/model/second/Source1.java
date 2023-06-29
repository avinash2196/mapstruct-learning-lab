/*
 * Copyright MapStruct Authors.
 *
 * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */
package org.example.model.second;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 *
 * @author Sjaak Derksen
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Source1 {

    private List<Integer> cars;
    private List<String> myStrings;

}
