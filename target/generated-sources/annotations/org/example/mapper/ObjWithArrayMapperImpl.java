package org.example.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.example.model.Car;
import org.example.model.CarDto;
import org.example.model.Object1WithArray;
import org.example.model.Object2WithArray;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-01-03T21:22:28-0500",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.1 (Oracle Corporation)"
)
@Component
public class ObjWithArrayMapperImpl implements ObjWithArrayMapper {

    @Override
    public Object2WithArray convert(Object1WithArray obj) {
        if ( obj == null ) {
            return null;
        }

        Object2WithArray.Object2WithArrayBuilder object2WithArray = Object2WithArray.builder();

        object2WithArray.carsDto( carListToCarDtoList( obj.getCars() ) );
        object2WithArray.key2( obj.getKey1() );

        return object2WithArray.build();
    }

    protected CarDto carToCarDto(Car car) {
        if ( car == null ) {
            return null;
        }

        CarDto.CarDtoBuilder carDto = CarDto.builder();

        carDto.make( car.getMake() );
        carDto.type( car.getType() );

        return carDto.build();
    }

    protected List<CarDto> carListToCarDtoList(List<Car> list) {
        if ( list == null ) {
            return null;
        }

        List<CarDto> list1 = new ArrayList<CarDto>( list.size() );
        for ( Car car : list ) {
            list1.add( carToCarDto( car ) );
        }

        return list1;
    }
}
