
package org.example.model.second;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Sjaak Derksen
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Target1 {

    private int carDto;
    private String myString;

}
