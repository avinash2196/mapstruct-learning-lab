package org.example.mapper;

import java.util.HashMap;
import javax.annotation.Generated;
import org.example.model.SourceWithCode;
import org.example.model.TargetWithValue;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-06-28T01:09:49-0400",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.1 (Oracle Corporation)"
)
public class AdditionalParamMapperImpl implements AdditionalParamMapper {

    @Override
    public TargetWithValue mapSourceToTarget(SourceWithCode source, HashMap<String, String> codeValueMap) {
        if ( source == null ) {
            return null;
        }

        TargetWithValue targetWithValue = new TargetWithValue();

        targetWithValue.setValue( AdditionalParamMapper.fetchValueFromMap( source.getCode(), codeValueMap ) );

        return targetWithValue;
    }
}
