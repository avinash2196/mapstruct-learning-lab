package org.example.mapper;

import javax.annotation.Generated;
import org.example.model.SourceObj;
import org.example.model.TargetObj;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-01-03T21:22:28-0500",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.1 (Oracle Corporation)"
)
@Component
public class NoIterableToIterableMapperImpl implements NoIterableToIterableMapper {

    @Override
    public TargetObj toTarget(SourceObj s) {
        if ( s == null ) {
            return null;
        }

        TargetObj targetObj = new TargetObj();

        targetObj.setMyStrings( nonIterableToIterable( s.getMyString() ) );

        return targetObj;
    }
}
