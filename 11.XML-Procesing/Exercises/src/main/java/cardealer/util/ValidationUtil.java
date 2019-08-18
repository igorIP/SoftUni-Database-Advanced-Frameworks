package cardealer.util;

import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import java.util.Set;

@Component
public interface ValidationUtil {

   <T> Boolean isValid(T object);

   <T> Set<ConstraintViolation<T>> violations(T objects);
}
