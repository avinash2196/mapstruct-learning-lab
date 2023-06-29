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
import org.example.model.second.Car;

/**
 *
 * @author Sjaak Derksen
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Target {

    private Car car;
    private String myString;

}
