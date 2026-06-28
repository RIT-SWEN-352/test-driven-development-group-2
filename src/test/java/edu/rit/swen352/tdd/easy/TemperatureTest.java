package edu.rit.swen352.tdd.easy;

import edu.rit.swen352.tdd.easy.Temperature.TemperatureUnit;
import static edu.rit.swen352.tdd.easy.Temperature.TemperatureUnit.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
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
  void ctor_2(double value, TemperatureUnit unit) {
    final Exception e = assertThrows(IllegalArgumentException.class, () -> new Temperature(value, unit));
    assertEquals(Temperature.BAD_TEMP_VALUE, e.getMessage());
  }
  static Stream<Arguments> tempsBelowAbsoluteZero() {
    return Stream.of(
      Arguments.of(-0.1, KELVIN),
      Arguments.of(-460, FAHRENHEIT),
      Arguments.of(-274, CELSIUS)
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
  @DisplayName("convert valid Fahrenheit to kelvin")
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
  @DisplayName("convert valid Celsius to kelvin")
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
  @DisplayName("convert valid Kelvin to Fahrenheit")
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

  @Nested
  @DisplayName("toString tests")
  class toStringTests {
    @ParameterizedTest(name = "Test {0}-{1} => \"{2}\"")
    @DisplayName("toString")
    @MethodSource("toStringTests")
    void test_toString(double value, TemperatureUnit unit, String expectedString) {
      final Temperature CuT =  new Temperature(value, unit);
      assertEquals(expectedString, CuT.toString());
    }
    static Stream<Arguments> toStringTests() {
      return Stream.of(
        Arguments.of(100.0f, CELSIUS, "100.00°C"),
        Arguments.of(212.0f, FAHRENHEIT, "212.00°F"),
        Arguments.of(373.15, KELVIN, "373.15K")
      );
    }
  }

}
