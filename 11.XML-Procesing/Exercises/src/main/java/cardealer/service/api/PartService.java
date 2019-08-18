package cardealer.service.api;

import cardealer.domain.dto.view.PartsImportRootDto;

public interface PartService {
    void seedParts(PartsImportRootDto dtos);
}
