package org.example.mapper;

import org.example.model.SourceObjectLevel2Obj1;
import org.example.model.TargetObject;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * SourceObjectLevel2Obj1Mapper — a leaf-level mapper used as a building block
 * by {@link ObjMapper} to convert individual source items in a nested list.
 *
 * <p><b>Concept: mapper composition (leaf mapper)</b><br>
 * This mapper handles the conversion of a single {@link SourceObjectLevel2Obj1}
 * to a {@link org.example.model.TargetObject}. It only maps the fields available
 * from {@code Obj1}; the fields sourced from {@code Obj2} ({@code val2},
 * {@code obj2}) are filled in later by {@link ObjMapper}'s
 * {@code @AfterMapping} hook.</p>
 *
 * <p>By isolating this conversion in its own mapper, {@link ObjMapper} stays
 * focused on orchestration rather than field-level detail.</p>
 */
@Mapper(componentModel = "spring")
public interface SourceObjectLevel2Obj1Mapper {

    /**
     * Maps {@link SourceObjectLevel2Obj1} to a partially-populated
     * {@link org.example.model.TargetObject}.
     * The {@code val2} and {@code obj2} fields of the target remain {@code null}
     * after this call and are populated by the parent mapper's after-mapping step.
     *
     * @param source source object from the first nested list
     * @return partially populated target object
     */
    @Mapping(source = "key1", target = "key")
    TargetObject convert(SourceObjectLevel2Obj1 source);
}
