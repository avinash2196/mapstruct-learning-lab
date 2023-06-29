package org.example.mapper.second;

import org.example.model.second.SourceObjectLevel2Obj1;
import org.example.model.second.TargetObject;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SourceObjectLevel2Obj1Mapper {
    @Mapping(source = "key1", target = "key")
TargetObject convert (SourceObjectLevel2Obj1 sourceObjectLevel2Obj1);
}
