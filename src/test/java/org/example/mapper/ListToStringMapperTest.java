package org.example.mapper;

import org.example.model.CarDto;
import org.example.model.Obj1WithoutArray;
import org.example.model.Object2WithArray;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit tests for {@link ListToStringMapper}.
 *
 * <p>Verifies the inline Java expression pattern: the first element of a
 * {@code List<CarDto>} is extracted and its {@code type} field is placed in
 * a flat scalar field on the target.</p>
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {ListToStringMapperImpl.class})
class ListToStringMapperTest {

    @Autowired
    private ListToStringMapper mapper;

    @Test
    void convert_extractsFirstCarType() {
        CarDto first = CarDto.builder().type("SUV").make("Toyota").seatCount(5).build();
        CarDto second = CarDto.builder().type("Sedan").make("Honda").seatCount(4).build();
        Object2WithArray source = Object2WithArray.builder()
                .carsDto(Arrays.asList(first, second))
                .key2("k1")
                .build();

        Obj1WithoutArray result = mapper.convert(source);

        assertThat(result).isNotNull();
        assertThat(result.getCarType()).isEqualTo("SUV");  // only first element
        assertThat(result.getKey3()).isEqualTo("k1");
    }

    @Test
    void convert_nullCarsList_carTypeIsNull() {
        Object2WithArray source = Object2WithArray.builder()
                .carsDto(null)
                .key2("k2")
                .build();

        Obj1WithoutArray result = mapper.convert(source);

        assertThat(result.getCarType()).isNull();
        assertThat(result.getKey3()).isEqualTo("k2");
    }

    @Test
    void convert_emptyCarsList_carTypeIsNull() {
        Object2WithArray source = Object2WithArray.builder()
                .carsDto(Collections.emptyList())
                .key2("k3")
                .build();

        assertThat(mapper.convert(source).getCarType()).isNull();
    }
}
