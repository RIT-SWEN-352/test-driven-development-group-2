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
    CELSIUS(-273.15) {
      @Override
      public String displayString(double value) {
        return String.format("%.2f°C", value);
      }
    },
    FAHRENHEIT(-459.67) {
      @Override
      public String displayString(double value) {
        return String.format("%.2f°F", value);
      }
    },
    KELVIN(0) {
      @Override
      public String displayString(double value) {
        return String.format("%.2fK", value);
      }
    };

    public boolean isBelowAbsoluteZero(final double value) {
      return value <= absoluteZeroThreshold;
    }

    public abstract String displayString(final double value);

    private final double absoluteZeroThreshold;
    TemperatureUnit(double absoluteZeroThreshold) {
      this.absoluteZeroThreshold = absoluteZeroThreshold;
    }
  }

  final double value;
  final TemperatureUnit unit;

  Temperature (double value, TemperatureUnit unit) {
    if (unit.isBelowAbsoluteZero(value)) {
      throw new IllegalArgumentException(BAD_TEMP_VALUE);
    }
    this.value = value;
    this.unit = unit;
  }
  static final String BAD_TEMP_VALUE = "Value must be greater than or equal to absolute zero.";

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
    return unit.displayString(value);
  }
}
