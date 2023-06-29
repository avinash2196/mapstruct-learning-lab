/*
package org.example.mapper;

import org.example.model.testing.FinalResponse;
import org.example.model.testing.FirstNestedLevel;
import org.example.model.testing.SecondNestedLevel1;
import org.example.model.testing.TargetResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper
public interface SecondObjectMapper {

        ResponseMapper INSTANCE = Mappers.getMapper(ResponseMapper.class);

        @Mapping(source = "topElement1", target = "id")
        @Mapping(source = "getTopElement3", target = ".", qualifiedByName = "mapFirstNestedLevelToSecondNestedLevel1")
        @Mapping(source = "getTopElement3.secondNestedLevel2.thirdNestLevel1.b1.values[0].value", target = "b1")
        @Mapping(source = "getTopElement3.secondNestedLevel2.thirdNestLevel12.b1.values[0].value", target = "b2")
        TargetResponse mapToTargetResponse(FinalResponse finalResponse);

        default TargetResponse.SecondNestedLevel1 mapFirstNestedLevelToSecondNestedLevel1(FirstNestedLevel firstNestedLevel) {
                if (firstNestedLevel == null || firstNestedLevel.getSecondNestedLevel1s().isEmpty()) {
                        return null;
                }

                SecondNestedLevel1 secondNestedLevel1 = firstNestedLevel.getSecondNestedLevel1s().get(0);
                TargetResponse.SecondNestedLevel1 targetSecondNestedLevel1 = new TargetResponse.SecondNestedLevel1();
                targetSecondNestedLevel1.setGId(secondNestedLevel1.getGId());
                targetSecondNestedLevel1.setPId(secondNestedLevel1.getPId());
                targetSecondNestedLevel1.setAId(secondNestedLevel1.getThirdNestedLevel3().getAttributes().getAId());
                targetSecondNestedLevel1.setWId(secondNestedLevel1.getThirdNestedLevel3().getAttributes().getWId());

                return targetSecondNestedLevel1;
        }
}


*/
