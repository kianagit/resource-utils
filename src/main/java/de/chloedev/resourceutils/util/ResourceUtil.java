package de.chloedev.resourceutils.util;

import net.minecraft.client.MinecraftClient;
import net.minecraft.resource.Resource;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

public class ResourceUtil {

    public static Identifier getVanillaIdentifier(String path) {
        return new Identifier("minecraft", path);
    }

    public static Identifier getModIdentifier(String path) {
        return new Identifier("resourceutils", path);
    }

    @Nullable
    public static File extractAsset(Identifier asset, File destFile, boolean deleteOnExit) {
        if (destFile.isDirectory() || destFile.getParentFile() == null) throw new IllegalArgumentException("Destination-File invalid...");
        Optional<Resource> res = MinecraftClient.getInstance().getResourceManager().getResource(asset);
        if (res.isEmpty()) return null;
        if (deleteOnExit) destFile.deleteOnExit();
        try (InputStream in = res.get().getInputStream()) {
            FileOutputStream out = new FileOutputStream(destFile);
            out.write(in.readAllBytes());
            out.close();
            System.out.println(String.format("Extracted %s to %s", asset, destFile.getAbsolutePath()));
            return destFile;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
