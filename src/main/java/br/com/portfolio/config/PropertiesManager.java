package br.com.portfolio.config;

import br.com.portfolio.enums.keys;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesManager {

    private static PropertiesManager instance = null;

    private PropertiesManager() {
    }

    public static PropertiesManager getInstance() {
        if (instance == null) instance = new PropertiesManager();
        return instance;
    }

    public String getProperty(keys key) {
        final Properties properties = new Properties();
        try (final InputStream input = ClassLoader.getSystemResourceAsStream("api.properties")){
            properties.load(input);
            return properties.getProperty(key.getKey());
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

