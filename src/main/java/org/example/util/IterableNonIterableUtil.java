/*
 * Copyright MapStruct Authors.
 *
 * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */
package org.example.util;

import org.example.model.second.CarDto;
import org.example.model.testing.SecondNestedLevel1;
import org.springframework.stereotype.Component;

import java.util.List;
//import org.mapstruct.ap.test.selection.qualifier.annotation.TitleTranslator;

/**
 *
 * @author avinash
 */
@Component
public class IterableNonIterableUtil {

    @FirstElement
    public <T extends CarDto> T first(List<T> cars ) {
        if ( cars != null && !cars.isEmpty() ) {
            return cars.get( 0 );
        }
        else {
            return null;
        }
    }


    @LastElement
    public <T> T last( List<T> in ) {
        if ( in != null && !in.isEmpty() ) {
            return in.get( in.size() - 1 );
        }
        else {
            return null;
        }
    }


}
