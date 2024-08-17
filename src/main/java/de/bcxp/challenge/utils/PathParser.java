package de.bcxp.challenge.utils;

import java.nio.file.Path;
import java.nio.file.Paths;

public class PathParser {
    public static Path getPathToResourceFile(String subfolder, String fileName) {
        return Paths.get("src", subfolder, "resources", "de", "bcxp", "challenge", fileName);
    }
}
