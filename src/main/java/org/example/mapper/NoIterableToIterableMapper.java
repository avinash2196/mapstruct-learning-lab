package org.example.mapper;

import org.example.model.SourceObj;
import org.example.model.TargetObj;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;

@Mapper(componentModel = "spring")
public interface NoIterableToIterableMapper {

    @Mappings( {
            @Mapping( source = "myString", target = "myStrings", qualifiedByName = "nonIterableToIterable" )
    } )
            TargetObj toTarget(SourceObj s );

@Named("nonIterableToIterable")
    public default List<String> nonIterableToIterable(String in) {
        if (!StringUtils.isEmpty(in)) {
            return Arrays.asList(in);

        } else {
            return null;
        }
    }
}
