package org.example.mapper;

import org.example.model.SourceWithCode;
import org.example.model.TargetWithValue;
import org.mapstruct.*;

import java.util.HashMap;

@Mapper
public interface AdditionalParamMapper{


    @Mapping(source = "code", target = "value",qualifiedByName = "fetchValueFromMap")
    TargetWithValue mapSourceToTarget(SourceWithCode source, @Context HashMap<String, String> codeValueMap);

    @Named("fetchValueFromMap")
    static String fetchValueFromMap(String code, @Context HashMap<String, String> codeValueMap) {
        return codeValueMap.get(code);
    }
}

