package org.example.mapper;

import org.example.model.Car;
import org.example.model.CarDto;
import org.example.model.Source;
import org.example.model.Target;
import org.example.util.IterableNonIterableUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit tests for {@link SourceTargetMapper}.
 *
 * <p>Verifies the {@code @Qualifier} pattern: when mapping a {@code List<CarDto>}
 * to a single {@link Car}, the {@code @FirstElement}-annotated method in
 * {@link IterableNonIterableUtil} is selected, and then {@link CarMapper}
 * converts the resulting {@link CarDto} to a {@link Car}.</p>
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {SourceTargetMapperImpl.class, IterableNonIterableUtil.class, CarMapperImpl.class})
class SourceTargetMapperTest {

    @Autowired
    private SourceTargetMapper mapper;

    @Test
    void toTarget_picksFirstCarFromList() {
        CarDto first = CarDto.builder().make("Toyota").type("SUV").seatCount(5).build();
        CarDto second = CarDto.builder().make("Honda").type("Sedan").seatCount(4).build();
        Source source = Source.builder().cars(Arrays.asList(first, second)).build();

        Target target = mapper.toTarget(source);

        assertThat(target).isNotNull();
        assertThat(target.getCar()).isNotNull();
        assertThat(target.getCar().getMake()).isEqualTo("Toyota");  // first element only
        assertThat(target.getCar().getNumberOfSeats()).isEqualTo(5);
    }

    @Test
    void toTarget_emptyList_carIsNull() {
        Source source = Source.builder().cars(java.util.Collections.emptyList()).build();

        Target target = mapper.toTarget(source);

        assertThat(target.getCar()).isNull();
    }

    @Test
    void toTarget_nullList_carIsNull() {
        Source source = Source.builder().cars(null).build();

        Target target = mapper.toTarget(source);

        assertThat(target.getCar()).isNull();
    }
}
