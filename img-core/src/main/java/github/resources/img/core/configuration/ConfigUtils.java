/*
 * Tencent is pleased to support the open source community by making
 * Firestorm-Spark remote shuffle server available.
 *
 * Copyright (C) 2021 THL A29 Limited, a Tencent company.  All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use
 * this file except in compliance with the License. You may obtain a copy of the
 * License at
 *
 * https://opensource.org/licenses/Apache-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OF ANY KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations under the License.
 */

package github.resources.img.core.configuration;


import github.resources.img.core.utils.UnitConverter;

import java.util.function.Function;

public class ConfigUtils {

  // --------------------------------------------------------------------------------------------
  //  Type conversion
  // --------------------------------------------------------------------------------------------

  // Make sure that we cannot instantiate this class
  private ConfigUtils() {
  }

  /**
   * Tries to convert the raw value into the provided type.
   *
   * @param rawValue rawValue to convert into the provided type clazz
   * @param clazz    clazz specifying the target type
   * @param <T>      type of the result
   * @return the converted value if rawValue is of type clazz
   * @throws IllegalArgumentException if the rawValue cannot be converted in the
   *                                  specified target type clazz
   */
  @SuppressWarnings("unchecked")
  public static <T> T convertValue(Object rawValue, Class<?> clazz) {
    if (Integer.class.equals(clazz)) {
      return (T) convertToInt(rawValue);
    } else if (Long.class.equals(clazz)) {
      return (T) convertToLong(rawValue);
    } else if (Boolean.class.equals(clazz)) {
      return (T) convertToBoolean(rawValue);
    } else if (Float.class.equals(clazz)) {
      return (T) convertToFloat(rawValue);
    } else if (Double.class.equals(clazz)) {
      return (T) convertToDouble(rawValue);
    } else if (String.class.equals(clazz)) {
      return (T) convertToString(rawValue);
    }
    throw new IllegalArgumentException("Unsupported type: " + clazz);
  }

  static String convertToString(Object o) {
    if (o.getClass() == String.class) {
      return (String) o;
    }
    return o.toString();
  }

  static Integer convertToInt(Object o) {
    if (o.getClass() == Integer.class) {
      return (Integer) o;
    } else if (o.getClass() == Long.class) {
      long value = (Long) o;
      if (value <= Integer.MAX_VALUE && value >= Integer.MIN_VALUE) {
        return (int) value;
      } else {
        throw new IllegalArgumentException(String.format(
          "Configuration value %s overflows/underflows the integer type.",
          value));
      }
    }
    return Integer.parseInt(o.toString());
  }

  static Long convertToLong(Object o) {
    if (o.getClass() == Long.class) {
      return (Long) o;
    } else if (o.getClass() == Integer.class) {
      return ((Integer) o).longValue();
    }
    if (UnitConverter.isByteString(o.toString())) {
      return UnitConverter.byteStringAsBytes(o.toString());
    } else {
      // intType only support the size that less than 4GB,
      // sometimes it's dangerous.
      // So we should use longType as mush as possible.
      return Long.parseLong(o.toString());
    }
  }

  static Long convertToSizeInBytes(Object o) {
    if (o.getClass() == Long.class) {
      return (Long) o;
    } else if (o.getClass() == Integer.class) {
      return ((Integer) o).longValue();
    }
    return UnitConverter.byteStringAsBytes(o.toString());
  }

  static Boolean convertToBoolean(Object o) {
    if (o.getClass() == Boolean.class) {
      return (Boolean) o;
    }
    switch (o.toString().toUpperCase()) {
      case "TRUE":
        return true;
      case "FALSE":
        return false;
      default:
        throw new IllegalArgumentException(String.format(
          "Unrecognized option for boolean: %s. Expected either true or false(case insensitive)",
          o));
    }
  }

  static Float convertToFloat(Object o) {
    if (o.getClass() == Float.class) {
      return (Float) o;
    } else if (o.getClass() == Double.class) {
      double value = ((Double) o);
      if (value == 0.0
        || (value >= Float.MIN_VALUE && value <= Float.MAX_VALUE)
        || (value >= -Float.MAX_VALUE && value <= -Float.MIN_VALUE)) {
        return (float) value;
      } else {
        throw new IllegalArgumentException(String.format(
          "Configuration value %s overflows/underflows the float type.",
          value));
      }
    }

    return Float.parseFloat(o.toString());
  }

  static Double convertToDouble(Object o) {
    if (o.getClass() == Double.class) {
      return (Double) o;
    } else if (o.getClass() == Float.class) {
      return ((Float) o).doubleValue();
    }

    return Double.parseDouble(o.toString());
  }

  public static Function<Long, Boolean> positiveLongValidator = value -> value > 0;

  public static Function<Long, Boolean> non_negativeLongValidator = value -> value >= 0;

  public static Function<Long, Boolean> positiveIntegerValidator =
      value -> value > 0L && value <= Integer.MAX_VALUE;

  public static Function<Integer, Boolean> positiveIntegerValidator2 =
      value -> value > 0;

  public static Function<Double, Boolean> percentageDoubleValidator =
      (Function<Double, Boolean>) value -> Double.compare(value, 100.0) <= 0 && Double.compare(value, 0.0) >= 0;

}
