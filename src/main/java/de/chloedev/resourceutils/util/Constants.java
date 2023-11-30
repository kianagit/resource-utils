package de.chloedev.resourceutils.util;

import java.io.File;
import java.util.function.Supplier;

public class Constants {

    public static final File TEMP_DIR;

    static {
        File f = new File("./temp/");
        f.mkdir();
        TEMP_DIR = f;
    }
}
