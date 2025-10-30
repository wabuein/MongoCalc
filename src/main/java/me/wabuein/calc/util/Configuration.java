package me.wabuein.calc.util;

import java.io.*;
import java.util.Properties;

public class Configuration {

    private final File file;
    private final Properties properties;

    /**
     * Creates a new configuration file
     */

    public Configuration() {
        this.file = new File("config.txt");
        this.properties = new Properties();

        try {
            this.loadConfig();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Loads the config file
     *
     * @throws IOException exception if any error is ran into while creating it
     */

    private void loadConfig() throws IOException {
        if (file.exists()) {
            try (FileReader reader = new FileReader(file)) {
                properties.load(reader);
            }

            return;
        }

        try (InputStream in = getClass().getClassLoader().getResourceAsStream("config.txt")) {
            if (in == null)
                throw new FileNotFoundException("Default config.txt not found in resources!");

            try (OutputStream out = new FileOutputStream(file)) {
                in.transferTo(out);
            }

            System.out.println("Created default config.txt");
        }
    }

    /**
     * Gets a property of the file
     *
      * @param key key of the property
     * @return {@link String}
     */

    public final String get(String key) {
        return properties.getProperty(key);
    }

    /**
     * Gets a boolean
     *
     * @param key key linked to get the boolean value
     * @return {@link Boolean}
     */

    public final Boolean getBoolean(String key) {
        return Boolean.parseBoolean(get(key));
    }

    /**
     * Gets an integer
     *
     * @param key key linked to the integer value
     * @return {@link Integer}
     */

    public final Integer getInteger(String key) {
        return Integer.parseInt(get(key));
    }

    /**
     * Gets a double
     *
     * @param key key linked to the double value
     * @return {@link Double}
     */

    public final Double getDouble(String key) {
        return Double.parseDouble(get(key));
    }

    /**
     * Sets a certain property in the file
     *
     * @param key key to set value to
     * @param value value to set to the key
     * @throws IOException exception if any error is ran into
     */

    public void set(String key, String value) throws IOException {
        properties.setProperty(key, value);
        try (FileWriter writer = new FileWriter(file)) {
            properties.store(writer, "User Config");
        }
    }

}
