package org.example.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration test for {@link MappingDemoController}.
 *
 * <p>Loads the full Spring Boot application context and exercises the HTTP
 * endpoint end-to-end, verifying that all mapper beans wire together correctly
 * and that the response JSON has the expected structure.</p>
 *
 * <p>This test starts the embedded Tomcat on a random port; {@code MockMvc}
 * performs requests in-process without an actual network connection.</p>
 */
@SpringBootTest
@AutoConfigureMockMvc
class MappingDemoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void nestedMappingExample_returns200WithTargetObjectList() throws Exception {
        mockMvc.perform(get("/api/nested-mapping")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.targetObjectList").isArray())
                .andExpect(jsonPath("$.targetObjectList.length()").value(2));
    }

    @Test
    void nestedMappingExample_firstEntry_hasExpectedKeyAndValues() throws Exception {
        mockMvc.perform(get("/api/nested-mapping")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                // Both entries should have non-null keys
                .andExpect(jsonPath("$.targetObjectList[0].key").exists())
                // @AfterMapping should have populated val2 from Obj2 list
                .andExpect(jsonPath("$.targetObjectList[0].val2").exists());
    }
}
