package cardealer.domain.dto.view;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "cars")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class CarsImportRootDto {

    @XmlElement(name = "car")
    private List<CarImportDto> carImportDtos;

    public CarsImportRootDto() {
    }

    public List<CarImportDto> getCarImportDtos() {
        return carImportDtos;
    }

    public void setCarImportDtos(List<CarImportDto> carImportDtos) {
        this.carImportDtos = carImportDtos;
    }
}
