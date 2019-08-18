package cardealer.domain.dto.view;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "parts")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class PartsImportRootDto implements Serializable {

    @XmlElement(name = "part")
    private List<PartImportDto> partImportDtos;

    public PartsImportRootDto() {
    }

    public List<PartImportDto> getPartImportDtos() {
        return partImportDtos;
    }

    public void setPartImportDtos(List<PartImportDto> partImportDtos) {
        this.partImportDtos = partImportDtos;
    }
}
