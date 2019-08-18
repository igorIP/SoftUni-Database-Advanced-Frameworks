package cardealer.util;


public interface XmlParser {
    <T> T parseXml(Class<T> objectClass, String path);

    <T> T exportToXml(T objectToMarshal, Class<T> objectClass,String path);
}
