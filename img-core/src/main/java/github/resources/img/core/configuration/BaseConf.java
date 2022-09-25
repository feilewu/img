package github.resources.img.core.configuration;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class BaseConf {

    private final ConcurrentHashMap<String, Object> settings;

    public BaseConf() {
        this.settings = new ConcurrentHashMap<>();
    }

    <T> void setValueInternal(String key, T value) {
        if (key == null) {
            throw new NullPointerException("Key must not be null.");
        }
        if (value == null) {
            throw new NullPointerException("Value must not be null.");
        }
        this.settings.put(key, value);
    }


    /**
     * Adds the given key/value pair to the configuration object.
     *
     * @param key the key of the key/value pair to be added
     * @param value the value of the key/value pair to be added
     */
    public void setBoolean(String key, boolean value) {
        setValueInternal(key, value);
    }

    /**
     * Adds the given value to the configuration object.
     * The main key of the config option will be used to map the value.
     *
     * @param key the option specifying the key to be added
     * @param value the value of the key/value pair to be added
     */
    public void setBoolean(ConfigOption<Boolean> key, boolean value) {
        setValueInternal(key.key(), value);
    }


    public boolean getBoolean(String key, boolean defaultValue) {
        return getRawValue(key)
                .map(ConfigUtils::convertToBoolean)
                .orElse(defaultValue);
    }

    /**
     * Returns the value associated with the given config option as a boolean.
     *
     * @param configOption The configuration option
     * @return the (default) value associated with the given config option
     */
    public boolean getBoolean(ConfigOption<Boolean> configOption) {
        return getOptional(configOption)
                .orElseGet(configOption::defaultValue);
    }


    /**
     * Adds the given key/value pair to the configuration object.
     *
     * @param key the key of the key/value pair to be added
     * @param value the value of the key/value pair to be added
     */
    public void setString(String key, String value) {
        setValueInternal(key, value);
    }

    /**
     * Adds the given value to the configuration object.
     * The main key of the config option will be used to map the value.
     *
     * @param key the option specifying the key to be added
     * @param value the value of the key/value pair to be added
     */
    public void setString(ConfigOption<String> key, String value) {
        setValueInternal(key.key(), value);
    }


    /**
     * Returns the value associated with the given key as a string.
     *
     * @param key the key pointing to the associated value
     * @param defaultValue the default value which is returned when there is no
     *     value associated with the given key
     * @return the (default) value associated with the given key
     */
    public String getString(String key, String defaultValue) {
        return getRawValue(key)
                .map(ConfigUtils::convertToString)
                .orElse(defaultValue);
    }

    /**
     * Returns the value associated with the given config option as a string.
     *
     * @param configOption The configuration option
     * @return the (default) value associated with the given config option
     */
    public String getString(ConfigOption<String> configOption) {
        return getOptional(configOption)
                .orElseGet(configOption::defaultValue);
    }


    private Optional<Object> getRawValue(String key) {
        if (key == null) {
            throw new NullPointerException("Key must not be null.");
        }
        return Optional.ofNullable(this.settings.get(key));
    }

    public <T> Optional<T> getOptional(ConfigOption<T> option) {
        Optional<Object> rawValue = getRawValueFromOption(option);
        Class<?> clazz = option.getClazz();
        Optional<T> value = rawValue.map(v -> option.convertValue(v, clazz));
        return value;
    }

    private Optional<Object> getRawValueFromOption(ConfigOption<?> configOption) {
        return getRawValue(configOption.key());
    }
}
