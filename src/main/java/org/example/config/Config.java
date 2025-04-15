package org.example.config;

import org.example.DiscordBot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
    private static final Logger logger = LoggerFactory.getLogger(DiscordBot.class);
    public static final String CONFIG_FILE = "config.properties";
    public static final Properties properties = new Properties();

    static {
        try (InputStream input = Config.class.getClassLoader().getResourceAsStream(CONFIG_FILE)) {
            if (input == null) {
                logger.error("config file: {} not found", CONFIG_FILE);
            } else {
                properties.load(input);
                logger.info("config loaded from: {}", CONFIG_FILE);
            }

        } catch (IOException exception) {
            logger.error("Error loading config file:", exception);
        }
    }

    public static String getToken() {
        return properties.getProperty("TOKEN");
    }
}
