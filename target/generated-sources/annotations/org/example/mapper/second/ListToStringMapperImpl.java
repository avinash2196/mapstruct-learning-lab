package org.example.mapper.second;

import javax.annotation.Generated;
import org.example.model.second.Obj1WithoutArray;
import org.example.model.second.Object2WithArray;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-06-28T01:09:50-0400",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.1 (Oracle Corporation)"
)
@Component
public class ListToStringMapperImpl implements ListToStringMapper {

    @Override
    public Obj1WithoutArray convert(Object2WithArray obj) {
        if ( obj == null ) {
            return null;
        }

        Obj1WithoutArray.Obj1WithoutArrayBuilder obj1WithoutArray = Obj1WithoutArray.builder();

        obj1WithoutArray.key3( obj.getKey2() );

        obj1WithoutArray.carType( obj.getCarsDto() !=null && obj.getCarsDto().size()>0 ?obj.getCarsDto().get(0).getType():null );

        return obj1WithoutArray.build();
    }
}
