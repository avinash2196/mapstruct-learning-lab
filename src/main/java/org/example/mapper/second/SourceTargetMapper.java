/*
 * Copyright MapStruct Authors.
 *
 * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */
package org.example.mapper.second;

import org.example.model.second.Source;
import org.example.model.second.Target;
import org.example.util.FirstElement;
import org.example.util.IterableNonIterableUtil;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring" ,uses = {IterableNonIterableUtil.class,CarMapper.class} )
public interface SourceTargetMapper {



    @Mapping( source = "s.cars", target = "car", qualifiedBy = FirstElement.class )
     Target toTarget(Source s );

   /* @Mapping( source = "s.cars", target = "carDto", qualifiedBy = FirstElement.class )
    Target1 toTarget(Source1 s );*/
}
