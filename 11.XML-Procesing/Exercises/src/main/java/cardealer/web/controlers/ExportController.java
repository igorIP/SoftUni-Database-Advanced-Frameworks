package cardealer.web.controlers;

import cardealer.domain.dto.view.CarImportDto;
import cardealer.domain.dto.view.CarsImportRootDto;
import cardealer.service.api.CarService;
import cardealer.util.XmlParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ExportController {

    private final CarService carService;
    private final XmlParser xmlParser;

    @Autowired
    public ExportController(CarService carService, XmlParser xmlParser) {
        this.carService = carService;
        this.xmlParser = xmlParser;
    }

    public String getAllCarsByBrand(String brand) {
        //get dto from carService
        CarsImportRootDto dtos = this.carService.getAllCarsFromBrand(brand);
        //export the dto to xml and write it tio xml file
        this.xmlParser.exportToXml(dtos, CarsImportRootDto.class,
                "/Users/igor/Desktop/GIT_Projects/SoftUni-DatabaseAdvanced-Frameworks/11.XML-Procesing/Exercises/src/main/resources/filesxml/in/export_cars.xml");
        return "Status ok";
    }
}
