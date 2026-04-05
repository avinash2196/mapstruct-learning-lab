package org.example.mapper;

import org.example.model.SourceObj;
import org.example.model.TargetObj;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit tests for {@link NoIterableToIterableMapper}.
 *
 * <p>Verifies the scalar-to-collection pattern: a single {@code String} is
 * wrapped in a {@code List<String>} via a {@code @Named} default method.</p>
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {NoIterableToIterableMapperImpl.class})
class NoIterableToIterableMapperTest {

    @Autowired
    private NoIterableToIterableMapper mapper;

    @Test
    void toTarget_wrapsStringInList() {
        SourceObj source = SourceObj.builder().myString("hello").build();

        TargetObj target = mapper.toTarget(source);

        assertThat(target).isNotNull();
        assertThat(target.getMyStrings()).containsExactly("hello");
    }

    @Test
    void toTarget_nullString_targetListIsNull() {
        SourceObj source = SourceObj.builder().myString(null).build();

        TargetObj target = mapper.toTarget(source);

        assertThat(target.getMyStrings()).isNull();
    }

    @Test
    void toTarget_emptyString_targetListIsNull() {
        SourceObj source = SourceObj.builder().myString("").build();

        TargetObj target = mapper.toTarget(source);

        // Empty string is treated as blank by StringUtils.isEmpty — returns null
        assertThat(target.getMyStrings()).isNull();
    }
}
