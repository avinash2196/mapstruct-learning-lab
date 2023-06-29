package org.example.mapper.second;

import javax.annotation.Generated;
import org.example.model.second.Source;
import org.example.model.second.Target;
import org.example.util.IterableNonIterableUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-06-28T01:09:49-0400",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.1 (Oracle Corporation)"
)
@Component
public class SourceTargetMapperImpl implements SourceTargetMapper {

    @Autowired
    private IterableNonIterableUtil iterableNonIterableUtil;
    @Autowired
    private CarMapper carMapper;

    @Override
    public Target toTarget(Source s) {
        if ( s == null ) {
            return null;
        }

        Target.TargetBuilder target = Target.builder();

        target.car( carMapper.carDTOCar( iterableNonIterableUtil.first( s.getCars() ) ) );

        return target.build();
    }
}
