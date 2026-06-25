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
      , () -> assertEquals(value, temperatureTest.value, "value is correct")
      , () -> assertEquals(tunit, temperatureTest.unit, "unit is correct")
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
}
