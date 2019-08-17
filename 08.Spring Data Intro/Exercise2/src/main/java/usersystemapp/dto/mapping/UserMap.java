package usersystemapp.dto.mapping;

import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;
import usersystemapp.domain.entities.models.User;
import usersystemapp.dto.user.UserDto;

@Component
public class UserMap extends PropertyMap<User, UserDto> implements MapperDto {
    @Override
    protected void configure() {
        map().setFirstName(source.getFirstName());
        using(stringConverter).map().setLastName(source.getLastName());
        map().setAge(source.getAge());
        //skip().setAge(null);
    }

    //Converting Properties – Java 7
    public static final Converter<String, String> stringConverter = new AbstractConverter<String, String>() {
        @Override
        protected String convert(String s) {
            return s == null ? null : s.toUpperCase();
        }
    };
}
