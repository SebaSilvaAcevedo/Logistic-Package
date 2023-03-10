package com.techacademy.logisticpackage.commons.utils;



import com.techacademy.logisticpackage.commons.enums.PackageEnumsContainer.Country;
import com.techacademy.logisticpackage.commons.enums.PackageEnumsContainer.Commerce;
import com.techacademy.logisticpackage.commons.exception.EnumRequestHeaderException;
import org.springframework.stereotype.Component;

@Component
public class HeadersUtils {

  private HeadersUtils() { super(); }
  public void validateCountryEnumHeader(String value)
      throws EnumRequestHeaderException {
    for (Country country : Country.values()) {
      if (country.name().equals(value)) {
        return;
      }
    }
    throw new EnumRequestHeaderException("Not valid data in X-country",
        new Throwable("Not valid data in X-country, must be  CL, AR, PE, CO, MX"));
  }
  public void validateCommerceEnumHeader(String value)
      throws EnumRequestHeaderException {
    for (Commerce commerce : Commerce.values()) {
      if (commerce.name().equals(value)) {
        return;
      }
    }
    throw new EnumRequestHeaderException("Not valid data in X-commerce",
        new Throwable("Not valid data in X-commerce, must be SODIMAC, TOTTUS, FALABELLA, LINIO, IKEA"));
  }
}
