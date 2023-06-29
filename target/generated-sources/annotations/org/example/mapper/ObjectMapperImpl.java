package org.example.mapper;

import javax.annotation.Generated;
import org.example.model.testing.FourthNestedLevel2;
import org.example.model.testing.FouthNestedLevel1;
import org.example.model.testing.SecondNestedLevel1;
import org.example.model.testing.SecondNestedLevel2;
import org.example.model.testing.TargetResponse;
import org.example.model.testing.ThirdNestLevel1;
import org.example.model.testing.ThirdNestedLevel3;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-06-28T01:09:50-0400",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.1 (Oracle Corporation)"
)
public class ObjectMapperImpl implements ObjectMapper {

    @Override
    public TargetResponse mapToTargetBuilder(IntermediateLevel intermediateLevel) {
        if ( intermediateLevel == null ) {
            return null;
        }

        TargetResponse.TargetResponseBuilder targetResponse = TargetResponse.builder();

        targetResponse.aId( getAid( intermediateLevelSecondNestedLevel1ThirdNestedLevel3Attributes( intermediateLevel ) ) );
        targetResponse.wId( getWid( intermediateLevelSecondNestedLevel1ThirdNestedLevel3Attributes( intermediateLevel ) ) );
        targetResponse.gId( getGId( intermediateLevel.getSecondNestedLevel1() ) );
        targetResponse.pId( getPId( intermediateLevel.getSecondNestedLevel1() ) );
        targetResponse.b1( getValues( intermediateLevelSecondNestedLevel2ThirdNestLevel1B1( intermediateLevel ) ) );
        targetResponse.b2( getValues( intermediateLevelSecondNestedLevel2ThirdNestLevel1B2( intermediateLevel ) ) );

        return targetResponse.build();
    }

    private FourthNestedLevel2 intermediateLevelSecondNestedLevel1ThirdNestedLevel3Attributes(IntermediateLevel intermediateLevel) {
        if ( intermediateLevel == null ) {
            return null;
        }
        SecondNestedLevel1 secondNestedLevel1 = intermediateLevel.getSecondNestedLevel1();
        if ( secondNestedLevel1 == null ) {
            return null;
        }
        ThirdNestedLevel3 thirdNestedLevel3 = secondNestedLevel1.getThirdNestedLevel3();
        if ( thirdNestedLevel3 == null ) {
            return null;
        }
        FourthNestedLevel2 attributes = thirdNestedLevel3.getAttributes();
        if ( attributes == null ) {
            return null;
        }
        return attributes;
    }

    private FouthNestedLevel1 intermediateLevelSecondNestedLevel2ThirdNestLevel1B1(IntermediateLevel intermediateLevel) {
        if ( intermediateLevel == null ) {
            return null;
        }
        SecondNestedLevel2 secondNestedLevel2 = intermediateLevel.getSecondNestedLevel2();
        if ( secondNestedLevel2 == null ) {
            return null;
        }
        ThirdNestLevel1 thirdNestLevel1 = secondNestedLevel2.getThirdNestLevel1();
        if ( thirdNestLevel1 == null ) {
            return null;
        }
        FouthNestedLevel1 b1 = thirdNestLevel1.getB1();
        if ( b1 == null ) {
            return null;
        }
        return b1;
    }

    private FouthNestedLevel1 intermediateLevelSecondNestedLevel2ThirdNestLevel1B2(IntermediateLevel intermediateLevel) {
        if ( intermediateLevel == null ) {
            return null;
        }
        SecondNestedLevel2 secondNestedLevel2 = intermediateLevel.getSecondNestedLevel2();
        if ( secondNestedLevel2 == null ) {
            return null;
        }
        ThirdNestLevel1 thirdNestLevel1 = secondNestedLevel2.getThirdNestLevel1();
        if ( thirdNestLevel1 == null ) {
            return null;
        }
        FouthNestedLevel1 b2 = thirdNestLevel1.getB2();
        if ( b2 == null ) {
            return null;
        }
        return b2;
    }
}
