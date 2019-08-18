package cardealer.service.implemetation;

import cardealer.domain.dto.view.PartImportDto;
import cardealer.domain.dto.view.PartsImportRootDto;
import cardealer.domain.entities.Part;
import cardealer.domain.entities.Supplier;
import cardealer.repository.PartRepository;
import cardealer.repository.SupplierRepository;
import cardealer.service.api.PartService;
import cardealer.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class PartsServiceImpl implements PartService {

    private final PartRepository partRepository;
    private final SupplierRepository supplierRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    @Autowired
    public PartsServiceImpl(PartRepository partRepository, SupplierRepository supplierRepository, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.partRepository = partRepository;
        this.supplierRepository = supplierRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public void seedParts(PartsImportRootDto dtos) {
        //validate each dto to entity
        //each dto to entity class an save
        for (PartImportDto partDto : dtos.getPartImportDtos()) {
            if (!this.validationUtil.isValid(partDto)) {
                System.out.println("Dto is not valid");
                return;
            }
            Part partEntity = this.modelMapper.map(partDto, Part.class);
            partEntity.setSupplier(getRandomSupplier());
            this.partRepository.saveAndFlush(partEntity);
        }
    }

    private Supplier getRandomSupplier() {
        Random random = new Random();
        Supplier supplier = this.supplierRepository.findAll().get(random.nextInt((int) this.supplierRepository.count() - 1));
        return supplier;
    }
}
