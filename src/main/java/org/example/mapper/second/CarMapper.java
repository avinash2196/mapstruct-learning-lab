package org.example.mapper.second;

import org.example.model.second.Car;
import org.example.model.second.CarDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CarMapper {


    Car carDTOCar(CarDto carDto);
}
