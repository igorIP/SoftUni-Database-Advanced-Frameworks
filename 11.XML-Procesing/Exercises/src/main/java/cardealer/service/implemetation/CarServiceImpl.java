package cardealer.service.implemetation;

import cardealer.domain.dto.view.CarImportDto;
import cardealer.domain.dto.view.CarsImportRootDto;
import cardealer.domain.entities.Car;
import cardealer.repository.CarRepository;
import cardealer.service.api.CarService;
import cardealer.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    @Autowired
    public CarServiceImpl(CarRepository carRepository, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.carRepository = carRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public void seedCars(CarsImportRootDto dtos) {
        //validate each dto to entity
        //each dto to entity class an save
        for (CarImportDto carDto : dtos.getCarImportDtos()) {
            if (!this.validationUtil.isValid(carDto)) {
                System.out.println("Dto is not valid");
                return;
            }
            Car carEntity = this.modelMapper.map(carDto, Car.class);
            this.carRepository.saveAndFlush(carEntity);
        }
    }

    @Override
    public CarsImportRootDto getAllCarsFromBrand(String brandName) {
        List<CarImportDto> dtos = new ArrayList<>();
        List<Car> cars = this.carRepository.findAllByMakeEquals(brandName);
        for (Car car1 : cars) {
            CarImportDto carImportDto = this.modelMapper.map(car1, CarImportDto.class);
            dtos.add(carImportDto);
        }
        CarsImportRootDto rootDto = new CarsImportRootDto();
        rootDto.setCarImportDtos(dtos);
        return rootDto;
    }
}
