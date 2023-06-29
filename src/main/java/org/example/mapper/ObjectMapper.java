package org.example.mapper;

import lombok.Data;
import org.example.model.testing.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ObjectMapper {
    ObjectMapper INSTANCE = Mappers.getMapper(ObjectMapper.class);

   /* @Mapping(source = "topElement1", target = "id")
    @Mapping(source = "getTopElement3", target = "intermediateLevel")
    TargetResponse mapToTarget1(FinalResponse finalResponse);*/
    @Mapping(source = "intermediateLevel.secondNestedLevel1.thirdNestedLevel3.attributes", target = "aId",qualifiedByName = "getAid")
    @Mapping(source = "intermediateLevel.secondNestedLevel1.thirdNestedLevel3.attributes", target = "wId",qualifiedByName = "getWid")
    @Mapping(source = "intermediateLevel.secondNestedLevel1", target = "gId",qualifiedByName = "getGId")
    @Mapping(source = "intermediateLevel.secondNestedLevel1", target = "pId",qualifiedByName = "getPId")
    @Mapping(source = "intermediateLevel.secondNestedLevel2.thirdNestLevel1.b1", target = "b1",qualifiedByName = "getValues")
    @Mapping(source = "intermediateLevel.secondNestedLevel2.thirdNestLevel1.b2", target = "b2",qualifiedByName = "getValues")
    TargetResponse mapToTargetBuilder(IntermediateLevel intermediateLevel);

  @Named("getValues")
    default String getValues(FouthNestedLevel1 value){
      return   value.getValues().stream().findFirst().orElseGet(()-> Values.builder().value("").build()).getValue();
    }

  @Named("getWid")
  default int getWid(FourthNestedLevel2 value){
      return value.getWId();
  }
    @Named("getAid")
    default int getAid(FourthNestedLevel2 value){
        return value.getAId();
    }
    @Named("getGId")
    default String getGId(SecondNestedLevel1 value){
      return value.getGId();

    }
    @Named("getPId")
    default String getPId(SecondNestedLevel1 value){
        return value.getPId();
    }
    default TargetResponse mapToTarget(FinalResponse finalResponse) {
        IntermediateLevel intermediateLevel = mapToIntermediateLevel(finalResponse.getGetTopElement3());
        return mapToTargetBuilder(intermediateLevel);
    }

    default IntermediateLevel mapToIntermediateLevel(FirstNestedLevel firstNestedLevel) {
        if (firstNestedLevel != null) {
            IntermediateLevel intermediateLevel = new IntermediateLevel();
            intermediateLevel.setSecondNestedLevel1(firstNestedLevel.getSecondNestedLevel1s().get(0));
            intermediateLevel.setSecondNestedLevel2(firstNestedLevel.getSecondNestedLevel2());
            return intermediateLevel;
        }
        return null;
    }


    @Data
    class IntermediateLevel {
        private SecondNestedLevel1 secondNestedLevel1;
        private SecondNestedLevel2 secondNestedLevel2;

    }
}
