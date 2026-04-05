package org.example.mapper;

import org.example.model.Object1WithArray;
import org.example.model.Object2WithArray;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * ObjWithArrayMapper — demonstrates collection mapping and mapper composition.
 *
 * <p><b>Concept: collection mapping</b><br>
 * MapStruct automatically iterates {@code List<Car>} and converts each element
 * to {@code CarDto} using {@link CarMapper#carDtoToCar} (referenced via
 * {@code uses = {CarMapper.class}}). You do not write a loop; MapStruct generates
 * it in the implementation class.</p>
 *
 * <p><b>Concept: mapper composition (uses)</b><br>
 * The {@code uses} attribute tells MapStruct to delegate to another mapper when it
 * encounters a type it cannot map on its own. This keeps individual mappers small
 * and focused on a single responsibility.</p>
 *
 * <p><b>Concept: field renaming</b><br>
 * {@code cars} → {@code carsDto} and {@code key1} → {@code key2} are simple
 * renames handled by explicit {@code @Mapping} annotations.</p>
 */
@Mapper(componentModel = "spring", uses = {CarMapper.class})
public interface ObjWithArrayMapper {

    /**
     * Converts {@link Object1WithArray} (domain) to {@link Object2WithArray} (DTO),
     * including converting each {@code Car} in the list to a {@code CarDto}.
     *
     * @param obj source object containing a list of {@link org.example.model.Car}
     * @return target object with a list of {@link org.example.model.CarDto}
     */
    @Mapping(source = "cars", target = "carsDto")
    @Mapping(source = "key1", target = "key2")
    Object2WithArray convert(Object1WithArray obj);
}
