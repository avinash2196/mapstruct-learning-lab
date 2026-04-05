package org.example.mapper;

import org.example.model.Car;
import org.example.model.CarDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * CarMapper — demonstrates basic field-name mapping between a DTO and a domain object.
 *
 * <p><b>Concept: explicit field-name mapping</b><br>
 * MapStruct maps fields with <em>identical names</em> automatically. Fields with
 * different names require an explicit {@code @Mapping} annotation. Here
 * {@code CarDto.seatCount} maps to {@code Car.numberOfSeats} — names differ,
 * so an explicit {@code @Mapping} is required.</p>
 *
 * <p><b>componentModel = "spring":</b> MapStruct generates a Spring
 * {@code @Component} for the implementation class, making it injectable anywhere
 * in the application context via {@code @Autowired} or constructor injection.</p>
 *
 * <p><b>Reuse:</b> This mapper is also consumed by {@link ObjWithArrayMapper} and
 * {@link SourceTargetMapper} via the {@code uses} attribute, demonstrating mapper
 * composition.</p>
 */
@Mapper(componentModel = "spring")
public interface CarMapper {

    /**
     * Converts a {@link CarDto} (data transfer / API layer) to a {@link Car}
     * (domain layer).
     *
     * <p>{@code seatCount} and {@code numberOfSeats} have different names, so an
     * explicit {@code @Mapping} is required to establish the relationship.</p>
     *
     * @param carDto the incoming DTO
     * @return mapped domain object
     */
    @Mapping(source = "seatCount", target = "numberOfSeats")
    Car carDtoToCar(CarDto carDto);
}
