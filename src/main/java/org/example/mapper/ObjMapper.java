package org.example.mapper;

import org.example.model.SourceObject;
import org.example.model.SourceObjectLevel2Obj1;
import org.example.model.TargetObject;
import org.example.model.TargetObjectWrapper;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring",uses ={SourceObjectLevel2Obj1Mapper.class})
public interface ObjMapper {

    @Mapping(target = "targetObjectList",source = "sourceObject.sourceObjectLevel1.sourceObjectLevel2Obj1List")
    TargetObjectWrapper convert(SourceObject sourceObject);


    @AfterMapping
    default void updateList(@MappingTarget TargetObjectWrapper.TargetObjectWrapperBuilder targetObjectWrapper,SourceObject sourceObject){
        List<TargetObject> targetObjectList=targetObjectWrapper.build().getTargetObjectList();


        targetObjectList.forEach(targetObject -> sourceObject.getSourceObjectLevel1().getSourceObjectLevel2Obj2List().stream()
                .filter(sourceObjectLevel2Obj2 ->   sourceObjectLevel2Obj2.getKey2().equals(targetObject.getKey()))
                .forEach(sourceObjectLevel2Obj2 -> targetObject.setVal2(sourceObjectLevel2Obj2.getVal2())));
    }


}
