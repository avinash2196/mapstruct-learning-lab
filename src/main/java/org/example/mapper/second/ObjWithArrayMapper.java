package org.example.mapper.second;

import org.example.model.second.Object1WithArray;
import org.example.model.second.Object2WithArray;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",uses = {CarMapper.class})
public interface ObjWithArrayMapper {
    @Mapping(source = "cars", target = "carsDto")
    @Mapping(source = "key1", target = "key2")
    Object2WithArray convert (Object1WithArray obj);
}
