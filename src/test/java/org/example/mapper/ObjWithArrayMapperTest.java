package org.example.mapper;

import org.example.model.Car;
import org.example.model.CarDto;
import org.example.model.Object1WithArray;
import org.example.model.Object2WithArray;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit tests for {@link ObjWithArrayMapper}.
 *
 * <p>Verifies collection mapping (list of domain objects → list of DTOs) and
 * field renaming ({@code cars} → {@code carsDto}, {@code key1} → {@code key2})
 * using mapper composition with {@link CarMapper}.</p>
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {ObjWithArrayMapperImpl.class, CarMapperImpl.class})
class ObjWithArrayMapperTest {

    @Autowired
    private ObjWithArrayMapper mapper;

    @Test
    void convert_mapsCarListAndKey() {
        Car car1 = Car.builder().make("Toyota").type("SUV").numberOfSeats(5).build();
        Car car2 = Car.builder().make("Honda").type("Sedan").numberOfSeats(4).build();
        Object1WithArray source = Object1WithArray.builder()
                .cars(Arrays.asList(car1, car2))
                .key1("myKey")
                .build();

        Object2WithArray result = mapper.convert(source);

        assertThat(result).isNotNull();
        assertThat(result.getKey2()).isEqualTo("myKey");
        assertThat(result.getCarsDto()).hasSize(2);
        assertThat(result.getCarsDto().get(0).getMake()).isEqualTo("Toyota");
        assertThat(result.getCarsDto().get(1).getMake()).isEqualTo("Honda");
    }

    @Test
    void convert_emptyCarList_mapsToEmptyDtoList() {
        Object1WithArray source = Object1WithArray.builder()
                .cars(java.util.Collections.emptyList())
                .key1("k")
                .build();

        Object2WithArray result = mapper.convert(source);

        assertThat(result.getCarsDto()).isEmpty();
    }

    @Test
    void convert_carTypeAndMakeArePreserved() {
        Car car = Car.builder().make("BMW").type("Coupe").numberOfSeats(2).build();
        Object1WithArray source = Object1WithArray.builder()
                .cars(java.util.List.of(car))
                .key1("x")
                .build();

        CarDto mapped = mapper.convert(source).getCarsDto().get(0);

        assertThat(mapped.getMake()).isEqualTo("BMW");
        assertThat(mapped.getType()).isEqualTo("Coupe");
    }
}
