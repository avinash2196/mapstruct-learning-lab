package org.example.mapper;

import org.example.model.Source;
import org.example.model.Target;
import org.example.util.FirstElement;
import org.example.util.IterableNonIterableUtil;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * SourceTargetMapper — demonstrates custom qualifier-based list-to-scalar mapping.
 *
 * <p><b>Concept: @Qualifier annotation</b><br>
 * Sometimes multiple conversion methods exist for the same source type (e.g. take
 * the first element vs. the last). MapStruct qualifiers let you distinguish
 * between them at the mapping site without relying on fragile method-name matching.
 * {@link FirstElement} is a custom annotation marked with MapStruct's
 * {@code @Qualifier}, and {@link IterableNonIterableUtil#first(java.util.List)}
 * is annotated with it. The {@code @Mapping} below routes the {@code cars} list
 * through that specific method.</p>
 *
 * <p><b>Concept: uses with @Component utility class</b><br>
 * {@code IterableNonIterableUtil} is a plain Spring {@code @Component} — not a
 * mapper — but MapStruct can delegate to it via {@code uses}. This is useful
 * when you want conversion logic to be reusable outside of the mapping layer.</p>
 *
 * <p><b>Note on Source1 / Target1:</b> A commented-out overload shows how the
 * same qualifier pattern would apply when the list element type differs. See
 * {@link org.example.model.Source1} and {@link org.example.model.Target1}.</p>
 */
@Mapper(componentModel = "spring", uses = {IterableNonIterableUtil.class, CarMapper.class})
public interface SourceTargetMapper {

    /**
     * Picks the first {@link org.example.model.CarDto} from {@code Source.cars},
     * converts it to a {@link org.example.model.Car} via {@link CarMapper}, and
     * assigns it to {@code Target.car}.
     *
     * @param s source object whose {@code cars} list must have at least one element
     *          for a non-null result
     * @return mapped target; {@code target.car} will be {@code null} if the list
     *         is empty or null
     */
    @Mapping(source = "s.cars", target = "car", qualifiedBy = FirstElement.class)
    Target toTarget(Source s);
}
