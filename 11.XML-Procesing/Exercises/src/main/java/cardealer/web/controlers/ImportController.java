package cardealer.web.controlers;

import cardealer.domain.dto.view.CarsImportRootDto;
import cardealer.domain.dto.view.PartsImportRootDto;
import cardealer.domain.dto.view.SupplierImportRootDto;
import cardealer.service.api.CarService;
import cardealer.service.api.PartService;
import cardealer.service.api.SupplierService;
import cardealer.util.XmlParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ImportController {

    private final SupplierService supplierService;
    private final PartService partService;
    private final CarService carService;
    private final XmlParser xmlParser;

    @Autowired
    public ImportController(SupplierService supplierService, PartService partService, CarService carService, XmlParser xmlParser) {
        this.supplierService = supplierService;
        this.partService = partService;
        this.carService = carService;
        this.xmlParser = xmlParser;
    }


    public String importSuppliers() {
        //unmarshal the xml file to importSupplierDto
        SupplierImportRootDto suppliersImportRootDto = this.xmlParser.parseXml(SupplierImportRootDto.class,
                "/Users/igor/Desktop/GIT_Projects/SoftUni-DatabaseAdvanced-Frameworks/11.XML-Procesing/Exercises/src/main/resources/filesxml/in/suppliers.xml");

        //pass the dto to supplierService to be mapped to entity and saved to db
        this.supplierService.importSuppliers(suppliersImportRootDto);
        return "Imported Suppliers";
    }

    public String importParts() {
        //get the file unmarshal the xml file to Dto
        PartsImportRootDto partsImportRootDto = xmlParser.parseXml(PartsImportRootDto.class,
                "/Users/igor/Desktop/GIT_Projects/SoftUni-DatabaseAdvanced-Frameworks/11.XML-Procesing/Exercises/src/main/resources/filesxml/in/parts.xml");
        //pass the root dto to service
        this.partService.seedParts(partsImportRootDto);
        return "Parts Imported";
    }

    public String importCars() {
        //get the file unmarshal the xml file to Dto
        CarsImportRootDto dto = this.xmlParser.parseXml(CarsImportRootDto.class,
                "/Users/igor/Desktop/GIT_Projects/SoftUni-DatabaseAdvanced-Frameworks/11.XML-Procesing/Exercises/src/main/resources/filesxml/in/cars.xml");
        //pass the root dto to service
        this.carService.seedCars(dto);
        return "Parts Imported";
    }
}
