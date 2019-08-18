package cardealer.util;

import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.nio.charset.StandardCharsets;

@Component
public class XmlParserImpl implements XmlParser {

    public XmlParserImpl() {
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T parseXml(Class<T> objectClass, String path) {
        try (final InputStream inputStream = new FileInputStream(path)) {
            JAXBContext context = JAXBContext.newInstance(objectClass);
            //BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(path))));
            Unmarshaller unmarshaller = context.createUnmarshaller();
            return (T) unmarshaller.unmarshal(inputStream);
        } catch (JAXBException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public <T> T exportToXml(T objectToMarshal, Class<T> objectClass, String path) {
        try (final OutputStream outputStream = new FileOutputStream(path)) {
            JAXBContext context = JAXBContext.newInstance(objectClass);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.setProperty(Marshaller.JAXB_ENCODING, StandardCharsets.UTF_8.displayName());
            marshaller.marshal(objectToMarshal,outputStream);
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }
}
