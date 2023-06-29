package org.example;

import lombok.AllArgsConstructor;
import org.example.mapper.second.ObjMapper;
import org.example.model.second.*;
import org.example.model.testing.FinalResponse;
import org.example.model.testing.TargetResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@AllArgsConstructor
public class Controller {
    private final ObjMapper objMapper;
    @GetMapping(value = "test")
    public TargetObjectWrapper targetObjectList(){
        SourceObjectLevel2Obj1 sourceObjectLevel2Obj11=SourceObjectLevel2Obj1.builder().obj1("obj11").key1("key1").val1("val11").build();
        SourceObjectLevel2Obj1 sourceObjectLevel2Obj12= SourceObjectLevel2Obj1.builder().obj1("obj21").key1("key2").val1("val21").build();
        SourceObjectLevel2Obj2 sourceObjectLevel2Obj21=SourceObjectLevel2Obj2.builder().obj2("obj11_2").key2("key1").val2("val11_2").build();
        SourceObjectLevel2Obj2 sourceObjectLevel2Obj22=SourceObjectLevel2Obj2.builder().obj2("obj21_2").key2("key2").val2("val21_2").build();
        SourceObject sourceObject=SourceObject.builder().sourceObjectLevel1(SourceObjectLevel1.builder().sourceObjectLevel2Obj1List(Arrays.asList(sourceObjectLevel2Obj11,sourceObjectLevel2Obj12)).sourceObjectLevel2Obj2List(Arrays.asList(sourceObjectLevel2Obj21,sourceObjectLevel2Obj22)).build()).build();

       // return null;

        TargetResponse t=TargetResponse.builder().build();
        FinalResponse f= FinalResponse.builder().build();
        t.setId(f.getTopElement1());
        t.setAId(f.getGetTopElement3().getSecondNestedLevel1s().get(0).getThirdNestedLevel3().getAttributes().getAId());
        t.setWId(f.getGetTopElement3().getSecondNestedLevel1s().get(0).getThirdNestedLevel3().getAttributes().getWId());
        t.setGId(f.getGetTopElement3().getSecondNestedLevel1s().get(0).getGId());
        t.setPId(f.getGetTopElement3().getSecondNestedLevel1s().get(0).getPId());
        t.setB1(f.getGetTopElement3().getSecondNestedLevel2().getThirdNestLevel1().getB1().getValues().get(0).getValue());
        t.setB2(f.getGetTopElement3().getSecondNestedLevel2().getThirdNestLevel12().getB1().getValues().get(0).getValue());



        return objMapper.convert(sourceObject);
    }
}
