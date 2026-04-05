package org.example.mapper;

import org.example.model.SourceObj;
import org.example.model.TargetObj;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;

/**
 * NoIterableToIterableMapper — demonstrates wrapping a scalar value in a
 * single-element list during mapping.
 *
 * <p><b>Concept: @Named qualifier with qualifiedByName</b><br>
 * When simple source → target field pairing is not enough, you can define a
 * custom conversion method inside the mapper interface as a {@code default}
 * method and annotate it with {@code @Named}. The {@code @Mapping} annotation
 * then references it by name via {@code qualifiedByName}. This is more
 * maintainable than inline {@code expression = "java(...)"} because the logic
 * is a real Java method that can be unit-tested.</p>
 *
 * <p><b>Concept: scalar → collection</b><br>
 * MapStruct does not automatically wrap a {@code String} in a {@code List<String>}.
 * This mapper shows the idiomatic pattern for doing so explicitly.</p>
 */
@Mapper(componentModel = "spring")
public interface NoIterableToIterableMapper {

    /**
     * Converts a {@link SourceObj} with a single string into a {@link TargetObj}
     * whose field is a list containing that string.
     *
     * @param s source object
     * @return target object with the wrapped list
     */
    @Mapping(source = "myString", target = "myStrings", qualifiedByName = "wrapInList")
    TargetObj toTarget(SourceObj s);

    /**
     * Wraps a single non-null string in an immutable single-element list.
     * Returns {@code null} when the input is blank, preserving the MapStruct
     * convention of mapping null inputs to null outputs.
     *
     * @param in the string to wrap
     * @return a list containing {@code in}, or {@code null}
     */
    @Named("wrapInList")
    @SuppressWarnings("deprecation")
    default List<String> wrapInList(String in) {
        if (!StringUtils.isEmpty(in)) {
            return Arrays.asList(in);
        } else {
            return null;
        }
    }
}
