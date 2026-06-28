package edu.rit.swen352.tdd.easy;

import edu.rit.swen352.tdd.easy.Temperature.TemperatureUnit;
import static edu.rit.swen352.tdd.easy.Temperature.TemperatureUnit.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test suite for the {@link Temperature} component.
 */
class TemperatureTest {

  @ParameterizedTest(name = "Test value={0}, unit={1}")
  @CsvSource({"1,KELVIN", "460,FAHRENHEIT", "274,CELSIUS"})
  @DisplayName("ctor with both unit and value")
  void ctor_1(int value, TemperatureUnit unit) {
    final Temperature temperatureTest = new Temperature(value, unit);
    assertAll("group assertions"
      , () -> assertNotNull(temperatureTest)
      , () -> assertEquals(value, temperatureTest.getValue(), "value is correct")
      , () -> assertEquals(unit, temperatureTest.getUnit(), "unit is correct")
    );
  }

  @ParameterizedTest(name = "Test value={0}, unit={1}")
  @MethodSource("tempsBelowAbsoluteZero")
  @DisplayName("ctor rejects impossible -1 kelvin temp")
  void ctor_2(double value, TemperatureUnit unit, String expectedErrorMsg) {
    final Exception e = assertThrows(IllegalArgumentException.class, () -> new Temperature(value, unit));
    assertEquals(expectedErrorMsg, e.getMessage());
  }
  static Stream<Arguments> tempsBelowAbsoluteZero() {
    return Stream.of(
      Arguments.of(-0.1, KELVIN, Temperature.BAD_KELVIN_VALUE_MSG),
      Arguments.of(-460, FAHRENHEIT, Temperature.BAD_FAHR_VALUE_MSG),
      Arguments.of(-274, CELSIUS, Temperature.BAD_CELSIUS_VALUE_MSG)
    );
  }

  @Test
  @DisplayName("ctor with no unit supplied")
  void ctor_5() {
    final Temperature temperatureTest = new Temperature(100);
    assertAll("group assertions"
      , () -> assertNotNull(temperatureTest)
      , () -> assertEquals(100, temperatureTest.getValue(), "value is correct")
      , () -> assertEquals(CELSIUS, temperatureTest.getUnit(), "unit is correct")
    );
  }

  @Test
  @DisplayName("convert valid fahrenheit to celsius")
  void convert_1() {
    Temperature fTemp = new Temperature(212, FAHRENHEIT);
    Temperature cTemp = fTemp.convertTo(CELSIUS);

    assertAll("group convert assertions"
      , () -> assertNotNull(cTemp)
      , () -> assertEquals(100, cTemp.getValue())
      , () -> assertEquals(CELSIUS, cTemp.getUnit())
    );
  }

  @Test
  @DisplayName("convert valid fahrenheit to kelvin")
  void convert_2() {
    Temperature fTemp = new Temperature(212, FAHRENHEIT);
    Temperature cTemp = fTemp.convertTo(KELVIN);

    assertAll("group convert assertions"
      , () -> assertNotNull(cTemp)
      , () -> assertEquals(373.15, cTemp.getValue())
      , () -> assertEquals(KELVIN, cTemp.getUnit())
    );
  }

  @Test
  @DisplayName("convert valid celsius to kelvin")
  void convert_3() {
    Temperature fTemp = new Temperature(100, CELSIUS);
    Temperature cTemp = fTemp.convertTo(KELVIN);

    assertAll("group convert assertions"
      , () -> assertNotNull(cTemp)
      , () -> assertEquals(373.15, cTemp.getValue())
      , () -> assertEquals(KELVIN, cTemp.getUnit())
    );
  }

  @Test
  @DisplayName("convert valid kelvin to fahrenheit")
  void convert_4() {
    Temperature fTemp = new Temperature(373.15, KELVIN);
    Temperature cTemp = fTemp.convertTo(FAHRENHEIT);

    assertAll("group convert assertions"
      , () -> assertNotNull(cTemp)
      , () -> assertEquals(212, cTemp.getValue())
      , () -> assertEquals(FAHRENHEIT, cTemp.getUnit())
    );
  }

  @Test
  @DisplayName("convert valid kelvin to celsius")
  void convert_5() {
    Temperature fTemp = new Temperature(373.15, KELVIN);
    Temperature cTemp = fTemp.convertTo(CELSIUS);

    assertAll("group convert assertions"
      , () -> assertNotNull(cTemp)
      , () -> assertEquals(100, cTemp.getValue())
      , () -> assertEquals(CELSIUS, cTemp.getUnit())
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
    Temperature temp = new Temperature(212, FAHRENHEIT);
    assertEquals("212.00°F", temp.toString());
  }

  @Test
  @DisplayName("toString kelvin shows correctly")
  void string_3(){
    Temperature temp = new Temperature(373.15, KELVIN);
    assertEquals("373.15K", temp.toString());
  }
}
