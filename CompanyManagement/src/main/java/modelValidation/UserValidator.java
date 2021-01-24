package modelValidation;

import model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

public class UserValidator {
    public static List<String> validate(User user) {
        List<String> errors = new ArrayList();
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        for (ConstraintViolation<User> violation : violations) {
            errors.add(violation.getMessage());
            System.out.println(violation.getMessage());
        }
        return errors;
    }
}