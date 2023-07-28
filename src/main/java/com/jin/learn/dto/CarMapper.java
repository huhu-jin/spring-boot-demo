package com.jin.learn.dto;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CarMapper {

    CarMapper INSTANCE = Mappers.getMapper( CarMapper.class );


    @Mapping(target = "passNumbers", source = "passNumber")
    @Mapping(target = "prices", source = "price")
    @Mapping(target = "isEV", source = ".")
    CarDto carToCarDto(Car car);

    default boolean mapIsEV(Car car){
        return car.isEV();
    }

}