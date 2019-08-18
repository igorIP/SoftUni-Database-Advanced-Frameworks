package cardealer.domain.dto.view;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "suppliers")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class SupplierImportRootDto implements Serializable {

    @XmlElement(name = "supplier")
    private List<SupplierImportDto> suppliers;

    public SupplierImportRootDto() {
    }

    public List<SupplierImportDto> getSuppliers() {
        return suppliers;
    }

    public void setSuppliers(List<SupplierImportDto> suppliers) {
        this.suppliers = suppliers;
    }
}
