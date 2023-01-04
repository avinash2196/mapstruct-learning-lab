package org.example.mapper;

import javax.annotation.Generated;
import org.example.model.SourceObjectLevel2Obj1;
import org.example.model.TargetObject;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-01-03T21:22:28-0500",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.1 (Oracle Corporation)"
)
@Component
public class SourceObjectLevel2Obj1MapperImpl implements SourceObjectLevel2Obj1Mapper {

    @Override
    public TargetObject convert(SourceObjectLevel2Obj1 sourceObjectLevel2Obj1) {
        if ( sourceObjectLevel2Obj1 == null ) {
            return null;
        }

        TargetObject.TargetObjectBuilder targetObject = TargetObject.builder();

        targetObject.key( sourceObjectLevel2Obj1.getKey1() );
        targetObject.obj1( sourceObjectLevel2Obj1.getObj1() );
        targetObject.val1( sourceObjectLevel2Obj1.getVal1() );

        return targetObject.build();
    }
}
