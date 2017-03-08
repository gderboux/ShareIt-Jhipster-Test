package com.shareit.app.service.mapper;

import com.shareit.app.domain.*;
import com.shareit.app.service.dto.CarDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity Car and its DTO CarDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CarMapper {

    CarDTO carToCarDTO(Car car);

    List<CarDTO> carsToCarDTOs(List<Car> cars);

    Car carDTOToCar(CarDTO carDTO);

    List<Car> carDTOsToCars(List<CarDTO> carDTOs);
}
