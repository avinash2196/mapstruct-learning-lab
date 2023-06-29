package org.example.mapper.second;

import org.example.model.second.Obj1WithoutArray;
import org.example.model.second.Object2WithArray;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ListToStringMapper {
    @Mapping(expression ="java(obj.getCarsDto() !=null && obj.getCarsDto().size()>0 ?obj.getCarsDto().get(0).getType():null)",target = "carType")
  //  @Mapping(source = "cars", target = "carsMake")
    @Mapping(source = "key2", target = "key3")
    Obj1WithoutArray convert (Object2WithArray obj);
}
