package com.techacademy.logisticpackage.commons.annotations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ValueOfEnumValidator implements ConstraintValidator<ValueOfEnum, CharSequence> {
  private List<String> acceptedValues;
  private boolean isAllow;

  @Override
  public void initialize(ValueOfEnum annotation) {
    acceptedValues = Stream.of(annotation.enumClass().getEnumConstants())
        .map(Enum::name)
        .collect(Collectors.toList());
    isAllow = annotation.allowNull();
  }

  @Override
  public boolean isValid(CharSequence value, ConstraintValidatorContext context) {

    if (isAllow && value == null) {
      return true;
    }

    if (value == null || value.length() == 0) {
      return false;
    }
    return acceptedValues.contains(value.toString());
  }
}