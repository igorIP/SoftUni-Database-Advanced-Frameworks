package cardealer.service.api;

import cardealer.domain.dto.view.CarImportDto;
import cardealer.domain.dto.view.CarsImportRootDto;
import cardealer.domain.entities.Car;

import java.util.List;

public interface CarService {
    void seedCars(CarsImportRootDto dtos);

    CarsImportRootDto getAllCarsFromBrand(String brandName);
}
