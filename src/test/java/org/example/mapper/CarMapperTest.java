package org.example.mapper;

import org.example.model.Car;
import org.example.model.CarDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit tests for {@link CarMapper}.
 *
 * <p>Uses a minimal Spring context containing only the generated
 * {@code CarMapperImpl} bean — no web layer, no full application context.
 * This keeps tests fast and focused.</p>
 *
 * <p><b>Concept verified:</b> explicit field-name mapping ({@code seatCount}
 * → {@code numberOfSeats}) and implicit mapping for same-named fields
 * ({@code make}, {@code type}).</p>
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {CarMapperImpl.class})
class CarMapperTest {

    @Autowired
    private CarMapper carMapper;

    @Test
    void carDtoToCar_mapsAllFields() {
        CarDto dto = CarDto.builder()
                .make("Toyota")
                .seatCount(5)
                .type("SUV")
                .build();

        Car car = carMapper.carDtoToCar(dto);

        assertThat(car).isNotNull();
        assertThat(car.getMake()).isEqualTo("Toyota");
        assertThat(car.getNumberOfSeats()).isEqualTo(5);  // key: different field names
        assertThat(car.getType()).isEqualTo("SUV");
    }

    @Test
    void carDtoToCar_nullInput_returnsNull() {
        assertThat(carMapper.carDtoToCar(null)).isNull();
    }

    @Test
    void carDtoToCar_preservesMakeAndType() {
        CarDto dto = CarDto.builder().make("BMW").type("Sedan").seatCount(4).build();

        Car car = carMapper.carDtoToCar(dto);

        assertThat(car.getMake()).isEqualTo("BMW");
        assertThat(car.getType()).isEqualTo("Sedan");
    }
}
