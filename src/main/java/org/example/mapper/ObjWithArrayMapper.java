package org.example.mapper;

import org.example.model.Object1WithArray;
import org.example.model.Object2WithArray;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",uses = {CarMapper.class})
public interface ObjWithArrayMapper {
    @Mapping(source = "cars", target = "carsDto")
    @Mapping(source = "key1", target = "key2")
    Object2WithArray convert (Object1WithArray obj);
}
