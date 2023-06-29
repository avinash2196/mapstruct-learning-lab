package org.example.mapper.second;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.example.model.second.SourceObject;
import org.example.model.second.SourceObjectLevel1;
import org.example.model.second.SourceObjectLevel2Obj1;
import org.example.model.second.TargetObject;
import org.example.model.second.TargetObjectWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-06-28T01:09:49-0400",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.1 (Oracle Corporation)"
)
@Component
public class ObjMapperImpl implements ObjMapper {

    @Autowired
    private SourceObjectLevel2Obj1Mapper sourceObjectLevel2Obj1Mapper;

    @Override
    public TargetObjectWrapper convert(SourceObject sourceObject) {
        if ( sourceObject == null ) {
            return null;
        }

        TargetObjectWrapper.TargetObjectWrapperBuilder targetObjectWrapper = TargetObjectWrapper.builder();

        List<SourceObjectLevel2Obj1> sourceObjectLevel2Obj1List = sourceObjectSourceObjectLevel1SourceObjectLevel2Obj1List( sourceObject );
        targetObjectWrapper.targetObjectList( sourceObjectLevel2Obj1ListToTargetObjectList( sourceObjectLevel2Obj1List ) );

        updateList( targetObjectWrapper, sourceObject );

        return targetObjectWrapper.build();
    }

    private List<SourceObjectLevel2Obj1> sourceObjectSourceObjectLevel1SourceObjectLevel2Obj1List(SourceObject sourceObject) {
        if ( sourceObject == null ) {
            return null;
        }
        SourceObjectLevel1 sourceObjectLevel1 = sourceObject.getSourceObjectLevel1();
        if ( sourceObjectLevel1 == null ) {
            return null;
        }
        List<SourceObjectLevel2Obj1> sourceObjectLevel2Obj1List = sourceObjectLevel1.getSourceObjectLevel2Obj1List();
        if ( sourceObjectLevel2Obj1List == null ) {
            return null;
        }
        return sourceObjectLevel2Obj1List;
    }

    protected List<TargetObject> sourceObjectLevel2Obj1ListToTargetObjectList(List<SourceObjectLevel2Obj1> list) {
        if ( list == null ) {
            return null;
        }

        List<TargetObject> list1 = new ArrayList<TargetObject>( list.size() );
        for ( SourceObjectLevel2Obj1 sourceObjectLevel2Obj1 : list ) {
            list1.add( sourceObjectLevel2Obj1Mapper.convert( sourceObjectLevel2Obj1 ) );
        }

        return list1;
    }
}
