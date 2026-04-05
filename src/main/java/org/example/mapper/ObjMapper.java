package org.example.mapper;

import org.example.model.SourceObject;
import org.example.model.TargetObject;
import org.example.model.TargetObjectWrapper;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

/**
 * ObjMapper — the most advanced mapper in this project, demonstrating nested
 * collection mapping combined with {@code @AfterMapping} post-processing to
 * merge data from two correlated source lists.
 *
 * <p><b>Problem being solved:</b><br>
 * The source object contains two parallel lists ({@code Obj1List} and
 * {@code Obj2List}) related by a shared {@code key} field. MapStruct can handle
 * the initial mapping of {@code Obj1List} → {@code targetObjectList} via
 * {@link SourceObjectLevel2Obj1Mapper}, but it has no built-in mechanism to
 * join the two lists and stitch in data from {@code Obj2List}.</p>
 *
 * <p><b>Concept: @AfterMapping</b><br>
 * The {@code @AfterMapping} method runs after MapStruct has finished the
 * generated mapping code. It receives the partially-built
 * {@link TargetObjectWrapper.TargetObjectWrapperBuilder} (because Lombok's
 * {@code @Builder} is in use) plus the original source. The method performs
 * the join manually: for each {@code TargetObject}, it finds the matching
 * {@code Obj2} entry by key and copies the missing fields.</p>
 *
 * <p><b>Design note:</b> The {@code @AfterMapping} approach keeps the generated
 * code clean while still allowing complex post-processing. An alternative would
 * be a custom conversion method annotated with {@code @Named}, but that would
 * require more boilerplate for the list-level iterating.</p>
 *
 * <p><b>Trade-off:</b> The after-mapping join is O(n×m). In a production system
 * with large lists you would instead build a {@code Map<String, Obj2>} keyed on
 * the shared key before iterating.</p>
 */
@Mapper(componentModel = "spring", uses = {SourceObjectLevel2Obj1Mapper.class})
public interface ObjMapper {

    /**
     * Maps a deeply nested {@link SourceObject} (two levels deep) to a flat
     * {@link TargetObjectWrapper} containing a merged list of
     * {@link TargetObject}s.
     *
     * <p>The initial mapping uses {@link SourceObjectLevel2Obj1Mapper} to convert
     * {@code Obj1List} entries. Fields from {@code Obj2List} are merged in by
     * {@link #mergeObj2Fields} after the primary mapping completes.</p>
     *
     * @param sourceObject the root source object; must not be {@code null}
     * @return wrapper containing the fully merged target list
     */
    @Mapping(target = "targetObjectList", source = "sourceObject.sourceObjectLevel1.sourceObjectLevel2Obj1List")
    TargetObjectWrapper convert(SourceObject sourceObject);

    /**
     * Post-processing step that joins {@code Obj2List} data into the mapped targets.
     *
     * <p>For each {@link TargetObject} in the builder, this method finds the
     * corresponding entry in {@code sourceObjectLevel2Obj2List} by matching
     * {@code key2 == target.key} and copies {@code val2} into the target.</p>
     *
     * @param targetWrapper the builder holding the partially mapped result
     * @param sourceObject  the original source (needed to access Obj2List)
     */
    @AfterMapping
    default void mergeObj2Fields(
            @MappingTarget TargetObjectWrapper.TargetObjectWrapperBuilder targetWrapper,
            SourceObject sourceObject) {
        List<TargetObject> targetObjectList = targetWrapper.build().getTargetObjectList();
        targetObjectList.forEach(targetObject ->
                sourceObject.getSourceObjectLevel1().getSourceObjectLevel2Obj2List().stream()
                        .filter(obj2 -> obj2.getKey2().equals(targetObject.getKey()))
                        .forEach(obj2 -> targetObject.setVal2(obj2.getVal2())));
    }
}
