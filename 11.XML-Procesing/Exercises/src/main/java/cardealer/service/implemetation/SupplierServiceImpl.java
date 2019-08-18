package cardealer.service.implemetation;

import cardealer.domain.dto.view.SupplierImportDto;
import cardealer.domain.dto.view.SupplierImportRootDto;
import cardealer.domain.entities.Supplier;
import cardealer.repository.SupplierRepository;
import cardealer.service.api.SupplierService;
import cardealer.util.ValidationUtil;
import cardealer.util.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;

@Service
public class SupplierServiceImpl implements SupplierService {

    private final SupplierRepository supplierRepository;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;

    @Autowired
    public SupplierServiceImpl(SupplierRepository supplierRepository, ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.supplierRepository = supplierRepository;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }


    @Override//gets dto from view controller(importController)
    public void importSuppliers(SupplierImportRootDto suppliersImportRootDtos) {
        for (SupplierImportDto supplier : suppliersImportRootDtos.getSuppliers()) {
            //validate each of the suppliers di they match the entity suppliers
            if (!this.validationUtil.isValid(supplier)) {
                System.out.println("Something went wrong");
                return;
            }
            //maps EACH the dto to ONE SUPPLIER entity
            Supplier entity = this.modelMapper.map(supplier, Supplier.class);
            //saves the EACH - ONE BY ONE entity to db
            this.supplierRepository.saveAndFlush(entity);
        }
    }
}
