package org.example.controller;

import lombok.AllArgsConstructor;
import org.example.mapper.ObjMapper;
import org.example.model.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

/**
 * MappingDemoController — REST entry point for the MapStruct Learning Lab.
 *
 * <p><b>Purpose:</b> Exposes a single HTTP endpoint that exercises the most complex
 * mapping scenario in the project: merging two parallel lists of source objects
 * into a unified list of target objects using {@link ObjMapper}.</p>
 *
 * <p><b>System fit:</b> In a real application this controller would accept
 * request bodies from clients and return mapped DTOs. Here the input data is
 * hard-coded so that the mapping behaviour can be verified without a database
 * or external system.</p>
 *
 * <p><b>Learning note — @AfterMapping:</b> The interesting part of this endpoint
 * is not the HTTP layer but the mapper it calls. {@code ObjMapper} uses MapStruct's
 * {@code @AfterMapping} hook to perform a post-processing step that correlates data
 * from two separate source lists by a shared key — something MapStruct cannot do
 * automatically. See {@link ObjMapper} for details.</p>
 *
 * <p><b>Design note:</b> Controller logic is intentionally minimal. Data assembly
 * belongs in a service layer in production code; it lives here only to keep the
 * learning example self-contained.</p>
 */
@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class MappingDemoController {

    private final ObjMapper objMapper;

    /**
     * Returns a {@link TargetObjectWrapper} built by mapping a nested source graph
     * that contains two correlated lists ({@code Obj1} and {@code Obj2}) joined on
     * a shared key.
     *
     * <p>Try it: {@code GET http://localhost:8086/api/nested-mapping}</p>
     *
     * <p>The response demonstrates {@code @AfterMapping} post-processing — values
     * from {@code sourceObjectLevel2Obj2List} are stitched into the target objects
     * that were originally built from {@code sourceObjectLevel2Obj1List}.</p>
     *
     * @return a wrapper containing a list of fully merged {@link TargetObject} instances
     */
    @GetMapping("/nested-mapping")
    public TargetObjectWrapper nestedMappingExample() {
        SourceObjectLevel2Obj1 level2Obj1A = SourceObjectLevel2Obj1.builder()
                .obj1("obj11").key1("key1").val1("val11").build();
        SourceObjectLevel2Obj1 level2Obj1B = SourceObjectLevel2Obj1.builder()
                .obj1("obj21").key1("key2").val1("val21").build();

        SourceObjectLevel2Obj2 level2Obj2A = SourceObjectLevel2Obj2.builder()
                .obj2("obj11_2").key2("key1").val2("val11_2").build();
        SourceObjectLevel2Obj2 level2Obj2B = SourceObjectLevel2Obj2.builder()
                .obj2("obj21_2").key2("key2").val2("val21_2").build();

        SourceObjectLevel1 level1 = SourceObjectLevel1.builder()
                .sourceObjectLevel2Obj1List(Arrays.asList(level2Obj1A, level2Obj1B))
                .sourceObjectLevel2Obj2List(Arrays.asList(level2Obj2A, level2Obj2B))
                .build();

        SourceObject sourceObject = SourceObject.builder().sourceObjectLevel1(level1).build();

        return objMapper.convert(sourceObject);
    }
}
