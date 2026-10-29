package es.utilidades;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class LectorFichero {

    public List<String> leerLineas(String ruta) {
        return leerLineas(Path.of(ruta));
    }

    public List<String> leerLineas(Path ruta) {
        try {
            return Files.readAllLines(ruta, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new UncheckedIOException("No se pudo leer el fichero: " + ruta, e);
        }
    }
}
