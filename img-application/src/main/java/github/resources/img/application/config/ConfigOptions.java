package github.resources.img.application.config;

import java.util.Objects;
import java.util.function.Function;

public class ConfigOptions {

    public static OptionBuilder key(String key) {
        Objects.requireNonNull(key);
        return new OptionBuilder(key);
    }




    public static final class OptionBuilder {
        /**
         * The key for the config option.
         */
        private final String key;

        /**
         * Creates a new OptionBuilder.
         *
         * @param key The key for the config option
         */
        OptionBuilder(String key) {
            this.key = key;
        }

        /**
         * Defines that the value of the option should be of {@link Boolean} type.
         */
        public TypedConfigOptionBuilder<Boolean> booleanType() {
            return new TypedConfigOptionBuilder<>(key, Boolean.class);
        }

        /**
         * Defines that the value of the option should be of {@link Integer} type.
         */
        public TypedConfigOptionBuilder<Integer> intType() {
            return new TypedConfigOptionBuilder<>(key, Integer.class);
        }

        /**
         * Defines that the value of the option should be of {@link Long} type.
         */
        public TypedConfigOptionBuilder<Long> longType() {
            return new TypedConfigOptionBuilder<>(key, Long.class);
        }

        /**
         * Defines that the value of the option should be of {@link Float} type.
         */
        public TypedConfigOptionBuilder<Float> floatType() {
            return new TypedConfigOptionBuilder<>(key, Float.class);
        }

        /**
         * Defines that the value of the option should be of {@link Double} type.
         */
        public TypedConfigOptionBuilder<Double> doubleType() {
            return new TypedConfigOptionBuilder<>(key, Double.class);
        }

        /**
         * Defines that the value of the option should be of {@link String} type.
         */
        public TypedConfigOptionBuilder<String> stringType() {
            return new TypedConfigOptionBuilder<>(key, String.class);
        }
    }

    public static class TypedConfigOptionBuilder<T> {
        private final String key;
        private final Class<T> clazz;
        private final Function<Object, T> converter;

        TypedConfigOptionBuilder(String key, Class<T> clazz) {
            this.key = key;
            this.clazz = clazz;
            this.converter = (v) -> {
                try {
                    return ConfigUtils.convertValue(v, clazz);
                } catch (Exception e) {
                    throw new IllegalArgumentException(String.format(
                            "Could not parse value '%s' for key '%s'.", v.toString(),
                            key), e);
                }
            };
        }

        /**
         * Creates a ConfigOption with the given default value.
         *
         * @param value The default value for the config option
         * @return The config option with the default value.
         */
        public ConfigOption<T> defaultValue(T value) {
            return new ConfigOption<>(
                    key,
                    clazz,
                    ConfigOption.EMPTY_DESCRIPTION,
                    value,
                    converter);
        }

    }

}
