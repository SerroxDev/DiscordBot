package Main.Loader;

import java.io.File;
import java.net.URL;
import java.util.Objects;

public class FileLoader {

    public static File getFile(String filePath) {
        URL file = FileLoader.class.getClassLoader().getResource(filePath);
        if (file == null) {
            throw new IllegalArgumentException("Keine Datei gefunden");
        }
        return new File(Objects.requireNonNull(file).getFile());
    }
}
