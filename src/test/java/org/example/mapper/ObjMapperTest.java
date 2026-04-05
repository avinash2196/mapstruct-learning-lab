package org.example.mapper;

import org.example.model.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit tests for {@link ObjMapper}.
 *
 * <p>Verifies the most complex scenario in this project: mapping a
 * two-level nested source graph (two correlated lists) into a flat
 * merged target list using {@code @AfterMapping}.</p>
 *
 * <p>The context includes both {@code ObjMapperImpl} and its dependency
 * {@code SourceObjectLevel2Obj1MapperImpl} so that the full mapping chain
 * functions correctly.</p>
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {ObjMapperImpl.class, SourceObjectLevel2Obj1MapperImpl.class})
class ObjMapperTest {

    @Autowired
    private ObjMapper objMapper;

    @Test
    void convert_mergesTwoCorrelatedLists() {
        SourceObjectLevel2Obj1 obj1A = SourceObjectLevel2Obj1.builder()
                .obj1("objA").key1("k1").val1("v1A").build();
        SourceObjectLevel2Obj1 obj1B = SourceObjectLevel2Obj1.builder()
                .obj1("objB").key1("k2").val1("v1B").build();

        SourceObjectLevel2Obj2 obj2A = SourceObjectLevel2Obj2.builder()
                .obj2("objA_2").key2("k1").val2("v2A").build();
        SourceObjectLevel2Obj2 obj2B = SourceObjectLevel2Obj2.builder()
                .obj2("objB_2").key2("k2").val2("v2B").build();

        SourceObjectLevel1 level1 = SourceObjectLevel1.builder()
                .sourceObjectLevel2Obj1List(Arrays.asList(obj1A, obj1B))
                .sourceObjectLevel2Obj2List(Arrays.asList(obj2A, obj2B))
                .build();
        SourceObject source = SourceObject.builder().sourceObjectLevel1(level1).build();

        TargetObjectWrapper result = objMapper.convert(source);

        assertThat(result).isNotNull();
        List<TargetObject> targets = result.getTargetObjectList();
        assertThat(targets).hasSize(2);

        // Verify Obj1 fields are present
        TargetObject first = targets.stream()
                .filter(t -> "k1".equals(t.getKey())).findFirst().orElseThrow();
        assertThat(first.getVal1()).isEqualTo("v1A");
        assertThat(first.getObj1()).isEqualTo("objA");

        // Verify @AfterMapping stitched in val2 from Obj2List by matching key
        assertThat(first.getVal2()).isEqualTo("v2A");
    }

    @Test
    void convert_secondEntry_isAlsoMergedCorrectly() {
        SourceObjectLevel2Obj1 obj1B = SourceObjectLevel2Obj1.builder()
                .obj1("objB").key1("k2").val1("v1B").build();
        SourceObjectLevel2Obj2 obj2B = SourceObjectLevel2Obj2.builder()
                .obj2("objB_2").key2("k2").val2("v2B").build();

        SourceObjectLevel1 level1 = SourceObjectLevel1.builder()
                .sourceObjectLevel2Obj1List(List.of(obj1B))
                .sourceObjectLevel2Obj2List(List.of(obj2B))
                .build();
        SourceObject source = SourceObject.builder().sourceObjectLevel1(level1).build();

        TargetObjectWrapper result = objMapper.convert(source);

        TargetObject target = result.getTargetObjectList().get(0);
        assertThat(target.getKey()).isEqualTo("k2");
        assertThat(target.getVal2()).isEqualTo("v2B");
    }
}
