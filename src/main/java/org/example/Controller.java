package org.example;

import lombok.AllArgsConstructor;
import org.example.mapper.ObjMapper;
import org.example.model.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@AllArgsConstructor
public class Controller {
    private final ObjMapper objMapper;
    @GetMapping(value = "test")
    public TargetObjectWrapper targetObjectList(){
        SourceObjectLevel2Obj1 sourceObjectLevel2Obj11=SourceObjectLevel2Obj1.builder().obj1("obj11").key1("key1").val1("val11").build();
        SourceObjectLevel2Obj1 sourceObjectLevel2Obj12=SourceObjectLevel2Obj1.builder().obj1("obj21").key1("key2").val1("val21").build();
        SourceObjectLevel2Obj2 sourceObjectLevel2Obj21=SourceObjectLevel2Obj2.builder().obj2("obj11_2").key2("key1").val2("val11_2").build();
        SourceObjectLevel2Obj2 sourceObjectLevel2Obj22=SourceObjectLevel2Obj2.builder().obj2("obj21_2").key2("key2").val2("val21_2").build();
        SourceObject sourceObject=SourceObject.builder().sourceObjectLevel1(SourceObjectLevel1.builder().sourceObjectLevel2Obj1List(Arrays.asList(sourceObjectLevel2Obj11,sourceObjectLevel2Obj12)).sourceObjectLevel2Obj2List(Arrays.asList(sourceObjectLevel2Obj21,sourceObjectLevel2Obj22)).build()).build();
        return objMapper.convert(sourceObject);
       // return null;

    }
}
