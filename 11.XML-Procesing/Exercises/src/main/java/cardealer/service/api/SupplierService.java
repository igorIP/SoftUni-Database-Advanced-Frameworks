package cardealer.service.api;

import cardealer.domain.dto.view.SupplierImportRootDto;

public interface SupplierService {
    void importSuppliers(SupplierImportRootDto suppliersImportRootDtos);
}
