package org.example.util;

import org.example.model.CarDto;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * IterableNonIterableUtil — a Spring-managed utility bean that provides
 * MapStruct qualifier methods for extracting a single element from a list.
 *
 * <p><b>Concept: @Qualifier-based disambiguation</b><br>
 * When a mapper needs to convert a {@code List<T>} to a single {@code T},
 * MapStruct cannot decide which element to pick. Custom qualifier annotations
 * — {@link FirstElement} and {@link LastElement} — mark specific methods on this
 * component, allowing mappers to reference them unambiguously via
 * {@code qualifiedBy = FirstElement.class} in a {@code @Mapping}.</p>
 *
 * <p><b>Why a @Component instead of an interface default method?</b><br>
 * Placing selection logic in a separate {@code @Component} makes the utility
 * independently reusable and testable outside the mapper context. Mappers
 * reference it via {@code uses = {IterableNonIterableUtil.class}}.</p>
 *
 * <p><b>Design note:</b> Both methods return {@code null} when the list is
 * absent or empty, following MapStruct's convention that mapping a null input
 * yields a null output.</p>
 */
@Component
public class IterableNonIterableUtil {

    /**
     * Returns the first element of a list of {@link CarDto}s, or {@code null}
     * if the list is null or empty.
     *
     * <p>Annotated with {@link FirstElement} so that MapStruct mappers can
     * select this method by qualifier rather than by type alone.</p>
     *
     * @param <T>  element type, bounded to {@link CarDto}
     * @param cars the source list
     * @return first element, or {@code null}
     */
    @FirstElement
    public <T extends CarDto> T first(List<T> cars) {
        if (cars != null && !cars.isEmpty()) {
            return cars.get(0);
        } else {
            return null;
        }
    }

    /**
     * Returns the last element of any list, or {@code null} if the list is
     * null or empty.
     *
     * <p>Annotated with {@link LastElement} for qualifier-based dispatch.
     * This method is provided as a counterpart to {@link #first} to illustrate
     * that multiple qualifiers can co-exist on the same utility class.</p>
     *
     * @param <T> element type
     * @param in  the source list
     * @return last element, or {@code null}
     */
    @LastElement
    public <T> T last(List<T> in) {
        if (in != null && !in.isEmpty()) {
            return in.get(in.size() - 1);
        } else {
            return null;
        }
    }
}
