package Main;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ResourceLoader {
    private static final String CONFIG_FILE = "token";

    public static String getToken() {
        Properties properties = new Properties();
        try (InputStream input = ResourceLoader.class.getClassLoader().getResourceAsStream(CONFIG_FILE)) {
            if (input == null) {
                System.err.println("Fehler: " + CONFIG_FILE + " nicht gefunden!");
                return null;
            }
            properties.load(input);
            return properties.getProperty("TOKEN");
        } catch (IOException e) {
            System.err.println("Fehler beim Laden der " + CONFIG_FILE + ": " + e.getMessage());
            return null;
        }
    }
    private static final String COMMAND_FILE = "Command Messages";

    public static String getMessage(String command) {
        Properties properties = new Properties();
        try (InputStream input = ResourceLoader.class.getClassLoader().getResourceAsStream(COMMAND_FILE)) {
            if (input == null) {
                System.err.println("Fehler: " + COMMAND_FILE + " nicht gefunden!");
                return null;
            }
            properties.load(input);
            return properties.getProperty(command);
        } catch (IOException e) {
            System.err.println("Fehler beim Laden der " + COMMAND_FILE + ": " + e.getMessage());
            return null;
        }
    }

}
