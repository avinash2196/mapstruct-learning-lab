package org.example.mapper;

import org.example.model.Car;
import org.example.model.CarDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CarMapper {


    Car carDTOCar(CarDto carDto);
}
