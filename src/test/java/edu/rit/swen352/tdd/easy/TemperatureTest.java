package edu.rit.swen352.tdd.easy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test suite for the {@link Temperature} component.
 */
class TemperatureTest {
  @ParameterizedTest(name = "Test value={0}, unit={1}")
  @CsvSource({"1,KELVIN", "460,FAHRENHEIT", "274,CELSIUS"})
  @DisplayName("ctor with both unit and value")
  void ctor_1(int value, String unit) {
    Temperature.TemperatureUnit tunit = Temperature.TemperatureUnit.valueOf(unit); //need to convert
    final Temperature temperatureTest = new Temperature(value, tunit);
    assertAll("group assertions"
      , () -> assertNotNull(temperatureTest)
      , () -> assertEquals(value, temperatureTest.getValue(), "value is correct")
      , () -> assertEquals(tunit, temperatureTest.getUnit(), "unit is correct")
    );
  }

  @Test
  @DisplayName("ctor rejects impossible -1 kelvin temp")
  void ctor_2() {
    Temperature.TemperatureUnit unit = Temperature.TemperatureUnit.KELVIN;
    final Exception e = assertThrows(IllegalArgumentException.class, () -> new Temperature(-1, unit));
    assertEquals("Value must be greater than or equal to 0 when unit is KELVIN", e.getMessage());
  }

  @Test
  @DisplayName("ctor rejects impossible -460 Fahrenheit temp")
  void ctor_3() {
    Temperature.TemperatureUnit unit = Temperature.TemperatureUnit.FAHRENHEIT;
    final Exception e = assertThrows(IllegalArgumentException.class, () -> new Temperature(-460, unit));
    assertEquals("Value must be greater than or equal to -459.67 when unit is FAHRENHEIT", e.getMessage());
  }

  @Test
  @DisplayName("ctor rejects impossible -274 Celsius temp")
  void ctor_4() {
    Temperature.TemperatureUnit unit = Temperature.TemperatureUnit.CELSIUS;
    final Exception e = assertThrows(IllegalArgumentException.class, () -> new Temperature(-274, unit));
    assertEquals("Value must be greater than or equal to -273.15 when unit is CELSIUS", e.getMessage());
  }

  @Test
  @DisplayName("ctor with no unit supplied")
  void ctor_5() {
    final Temperature temperatureTest = new Temperature(100);
    assertAll("group assertions"
      , () -> assertNotNull(temperatureTest)
      , () -> assertEquals(100, temperatureTest.getValue(), "value is correct")
      , () -> assertEquals(Temperature.TemperatureUnit.CELSIUS, temperatureTest.getUnit(), "unit is correct")
    );
  }

  @Test
  @DisplayName("convert valid fahrenheit to celsius")
  void convert_1() {
    Temperature fTemp = new Temperature(212, Temperature.TemperatureUnit.FAHRENHEIT);
    Temperature cTemp = fTemp.convertTo(Temperature.TemperatureUnit.CELSIUS);

    assertAll("group convert assertions"
      , () -> assertNotNull(cTemp)
      , () -> assertEquals(100, cTemp.getValue())
      , () -> assertEquals(Temperature.TemperatureUnit.CELSIUS, cTemp.getUnit())
    );
  }

  @Test
  @DisplayName("convert valid fahrenheit to kelvin")
  void convert_2() {
    Temperature fTemp = new Temperature(212, Temperature.TemperatureUnit.FAHRENHEIT);
    Temperature cTemp = fTemp.convertTo(Temperature.TemperatureUnit.KELVIN);

    assertAll("group convert assertions"
      , () -> assertNotNull(cTemp)
      , () -> assertEquals(373.15, cTemp.getValue())
      , () -> assertEquals(Temperature.TemperatureUnit.KELVIN, cTemp.getUnit())
    );
  }

  @Test
  @DisplayName("convert valid celsius to kelvin")
  void convert_3() {
    Temperature fTemp = new Temperature(100, Temperature.TemperatureUnit.CELSIUS);
    Temperature cTemp = fTemp.convertTo(Temperature.TemperatureUnit.KELVIN);

    assertAll("group convert assertions"
      , () -> assertNotNull(cTemp)
      , () -> assertEquals(373.15, cTemp.getValue())
      , () -> assertEquals(Temperature.TemperatureUnit.KELVIN, cTemp.getUnit())
    );
  }

  @Test
  @DisplayName("convert valid kelvin to fahrenheit")
  void convert_4() {
    Temperature fTemp = new Temperature(373.15, Temperature.TemperatureUnit.KELVIN);
    Temperature cTemp = fTemp.convertTo(Temperature.TemperatureUnit.FAHRENHEIT);

    assertAll("group convert assertions"
      , () -> assertNotNull(cTemp)
      , () -> assertEquals(212, cTemp.getValue())
      , () -> assertEquals(Temperature.TemperatureUnit.FAHRENHEIT, cTemp.getUnit())
    );
  }

  @Test
  @DisplayName("convert valid kelvin to celsius")
  void convert_5() {
    Temperature fTemp = new Temperature(373.15, Temperature.TemperatureUnit.KELVIN);
    Temperature cTemp = fTemp.convertTo(Temperature.TemperatureUnit.CELSIUS);

    assertAll("group convert assertions"
      , () -> assertNotNull(cTemp)
      , () -> assertEquals(100, cTemp.getValue())
      , () -> assertEquals(Temperature.TemperatureUnit.CELSIUS, cTemp.getUnit())
    );
  }

  @Test
  @DisplayName("toString celsius shows correctly")
  void string_1(){
    Temperature temp = new Temperature(100);
    assertEquals("100.00°C", temp.toString());
  }

  @Test
  @DisplayName("toString fahrenheit shows correctly")
  void string_2(){
    Temperature temp = new Temperature(212, Temperature.TemperatureUnit.FAHRENHEIT);
    assertEquals("212.00°F", temp.toString());
  }
}
