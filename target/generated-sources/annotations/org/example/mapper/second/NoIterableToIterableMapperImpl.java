package org.example.mapper.second;

import javax.annotation.Generated;
import org.example.model.second.SourceObj;
import org.example.model.second.TargetObj;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-06-28T01:09:48-0400",
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
