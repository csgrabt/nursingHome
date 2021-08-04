package com.example.nursinghome.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NameValidator implements ConstraintValidator<Name, String> {

    private int maxLength;

    @Override

    public void initialize(Name constraintAnnotation) {
        maxLength = constraintAnnotation.maxLength();
    }

    @Override
    public boolean isValid(String name, ConstraintValidatorContext constraintValidatorContext) {
        return name != null &&
                !name.isBlank() &&
                name.length() > 5 &&
                name.contains(" ") &&
                charactersValidatorOfName(name) &&
                name.length() <= maxLength;
    }

    private boolean charactersValidatorOfName(String name) {
        String[] array = name.split(" ");

        for (String item : array
        ) {
            if (Character.isLowerCase(item.charAt(0))) {
                return false;
            }
            if (alphabeticValidator(item)) return false;
        }
        return true;
    }

    private boolean alphabeticValidator(String item) {
        char[] array2 = item.toCharArray();
        for (Character item2 : array2
        ) {
            if (!Character.isAlphabetic(item2)) {
                return true;
            }
        }
        return false;
    }


}

