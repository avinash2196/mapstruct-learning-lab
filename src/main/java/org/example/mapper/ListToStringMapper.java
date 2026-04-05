package org.example.mapper;

import org.example.model.Obj1WithoutArray;
import org.example.model.Object2WithArray;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * ListToStringMapper — demonstrates extracting a scalar value from a collection
 * using an inline Java expression.
 *
 * <p><b>Concept: expression-based mapping</b><br>
 * When the target field requires logic that MapStruct cannot infer automatically
 * (e.g. picking the first element of a list and reading a property from it), you
 * can embed a Java expression directly in the {@code @Mapping} annotation using
 * {@code expression = "java(...)"}. This is a last-resort escape hatch; prefer
 * {@code qualifiedByName} with a named method where possible for testability.</p>
 *
 * <p><b>Trade-off:</b> Inline expressions are concise but opaque — they bypass
 * MapStruct's null-safety generation and are harder to unit-test in isolation.
 * Use them sparingly.</p>
 */
@Mapper(componentModel = "spring")
public interface ListToStringMapper {

    /**
     * Flattens a {@link Object2WithArray} (which holds a list of DTOs) into a
     * flat {@link Obj1WithoutArray} by extracting the first element's {@code type}
     * field via an inline Java expression.
     *
     * @param obj source object with a list of {@link org.example.model.CarDto}
     * @return flat target object with scalar car-type and renamed key
     */
    @Mapping(expression = "java(obj.getCarsDto() != null && !obj.getCarsDto().isEmpty() ? obj.getCarsDto().get(0).getType() : null)",
            target = "carType")
    @Mapping(source = "key2", target = "key3")
    Obj1WithoutArray convert(Object2WithArray obj);
}
