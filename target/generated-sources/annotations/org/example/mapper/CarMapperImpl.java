package org.example.mapper;

import javax.annotation.Generated;
import org.example.model.Car;
import org.example.model.CarDto;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-01-03T21:22:28-0500",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.1 (Oracle Corporation)"
)
@Component
public class CarMapperImpl implements CarMapper {

    @Override
    public Car carDTOCar(CarDto carDto) {
        if ( carDto == null ) {
            return null;
        }

        Car.CarBuilder car = Car.builder();

        car.make( carDto.getMake() );
        car.type( carDto.getType() );

        return car.build();
    }
}
