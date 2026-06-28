package edu.rit.swen352.tdd.easy;

/**
 * A temperature measurement.
 *
 * <p>
 * This must be an immutable
 * <a href='https://en.wikipedia.org/wiki/Value_object'>Value Object</a>.
 * Conversions must create new instances.
 * </p>
 *
 * <p>
 * You must implement these features:
 * <ul>
 *   <li>constructor:
 *     <ul>
 *       <li>a ctor that supplies both a value, as {@code double}, and a {@linkplain TemperatureUnit unit}</li>
 *       <li>another ctor with just a value; unit is defaulted to {@link TemperatureUnit#CELSIUS}</li>
 *     </ul>
 *   </li>
 *   <li>{@code getValue()}: returns the temperature value in the current units</li>
 *   <li>{@code getUnit()}: return the current temperature units</li>
 *   <li>{@code convertTo(unit)}: create a new Temperature in the new unit</li>
 *   <li>{@code toString()}: returns a human-friendly representation of the temperature, eg 25°C or 25K</li>
 * </ul>
 */
public class Temperature {
  public enum TemperatureUnit {
    CELSIUS, FAHRENHEIT, KELVIN
  }

  final double value;
  final TemperatureUnit unit;

  Temperature (double value, TemperatureUnit unit) {
    if (unit == TemperatureUnit.KELVIN && value < 0) {
      throw new IllegalArgumentException("Value must be greater than or equal to 0 when unit is KELVIN");
    } else if (unit == TemperatureUnit.FAHRENHEIT && value < -459.67) {
      throw new IllegalArgumentException("Value must be greater than or equal to -459.67 when unit is FAHRENHEIT");
    } else if (unit == TemperatureUnit.CELSIUS && value < -273.15) {
      throw new IllegalArgumentException("Value must be greater than or equal to -273.15 when unit is CELSIUS");
    }
    this.value = value;
    this.unit = unit;
  }

  Temperature (double value) {
    this(value, TemperatureUnit.CELSIUS);
  }

  public double getValue() {
    return value;
  }

  public TemperatureUnit getUnit() {
    return unit;
  }

  public Temperature convertTo(TemperatureUnit unit){
    double currentValue = this.value;
    TemperatureUnit currentUnit = this.unit;

    while (currentUnit != unit) {
      if (currentUnit == TemperatureUnit.FAHRENHEIT) {
        currentValue = (currentValue - 32) * (5.0/9.0);
        currentUnit = TemperatureUnit.CELSIUS;
      } else if (currentUnit == TemperatureUnit.CELSIUS) {
        currentValue += 273.15;
        currentUnit = TemperatureUnit.KELVIN;
      } else if (currentUnit == TemperatureUnit.KELVIN) {
        currentValue = (currentValue - 273.15) * (9.0/5.0) + 32;
        currentUnit = TemperatureUnit.FAHRENHEIT;
      }
    }

    return new Temperature(currentValue, currentUnit);
  }

  public String toString(){
    assert false: "Not yet implemented";
    return null;
  }
}
