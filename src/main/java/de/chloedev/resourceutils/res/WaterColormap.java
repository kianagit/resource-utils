package de.chloedev.resourceutils.res;

import de.chloedev.resourceutils.util.Constants;
import de.chloedev.resourceutils.util.ResourceUtil;
import net.minecraft.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WaterColormap {

    private static final List<RegistryKey<Biome>> BIOMES = new ArrayList<>();
    private static final Map<RegistryKey<Biome>, Integer> BIOME_COLORS = new HashMap();

    public WaterColormap() {
        reloadColors();
    }

    public static void reloadColors() {
        try {
            // Add Biomes to list above - Just in case.
            BIOMES.add(BiomeKeys.THE_VOID);
            BIOMES.add(BiomeKeys.PLAINS);
            BIOMES.add(BiomeKeys.SUNFLOWER_PLAINS);
            BIOMES.add(BiomeKeys.SNOWY_PLAINS);
            BIOMES.add(BiomeKeys.ICE_SPIKES);
            BIOMES.add(BiomeKeys.DESERT);
            BIOMES.add(BiomeKeys.SWAMP);
            BIOMES.add(BiomeKeys.MANGROVE_SWAMP);
            BIOMES.add(BiomeKeys.FOREST);
            BIOMES.add(BiomeKeys.FLOWER_FOREST);
            BIOMES.add(BiomeKeys.BIRCH_FOREST);
            BIOMES.add(BiomeKeys.DARK_FOREST);
            BIOMES.add(BiomeKeys.OLD_GROWTH_BIRCH_FOREST);
            BIOMES.add(BiomeKeys.OLD_GROWTH_PINE_TAIGA);
            BIOMES.add(BiomeKeys.OLD_GROWTH_SPRUCE_TAIGA);
            BIOMES.add(BiomeKeys.TAIGA);
            BIOMES.add(BiomeKeys.SNOWY_TAIGA);
            BIOMES.add(BiomeKeys.SAVANNA);
            BIOMES.add(BiomeKeys.SAVANNA_PLATEAU);
            BIOMES.add(BiomeKeys.WINDSWEPT_HILLS);
            BIOMES.add(BiomeKeys.WINDSWEPT_GRAVELLY_HILLS);
            BIOMES.add(BiomeKeys.WINDSWEPT_FOREST);
            BIOMES.add(BiomeKeys.WINDSWEPT_SAVANNA);
            BIOMES.add(BiomeKeys.JUNGLE);
            BIOMES.add(BiomeKeys.SPARSE_JUNGLE);
            BIOMES.add(BiomeKeys.BAMBOO_JUNGLE);
            BIOMES.add(BiomeKeys.BADLANDS);
            BIOMES.add(BiomeKeys.ERODED_BADLANDS);
            BIOMES.add(BiomeKeys.WOODED_BADLANDS);
            BIOMES.add(BiomeKeys.MEADOW);
            BIOMES.add(BiomeKeys.CHERRY_GROVE);
            BIOMES.add(BiomeKeys.GROVE);
            BIOMES.add(BiomeKeys.SNOWY_SLOPES);
            BIOMES.add(BiomeKeys.FROZEN_PEAKS);
            BIOMES.add(BiomeKeys.JAGGED_PEAKS);
            BIOMES.add(BiomeKeys.STONY_PEAKS);
            BIOMES.add(BiomeKeys.RIVER);
            BIOMES.add(BiomeKeys.FROZEN_RIVER);
            BIOMES.add(BiomeKeys.BEACH);
            BIOMES.add(BiomeKeys.SNOWY_BEACH);
            BIOMES.add(BiomeKeys.STONY_SHORE);
            BIOMES.add(BiomeKeys.WARM_OCEAN);
            BIOMES.add(BiomeKeys.LUKEWARM_OCEAN);
            BIOMES.add(BiomeKeys.DEEP_LUKEWARM_OCEAN);
            BIOMES.add(BiomeKeys.OCEAN);
            BIOMES.add(BiomeKeys.DEEP_OCEAN);
            BIOMES.add(BiomeKeys.COLD_OCEAN);
            BIOMES.add(BiomeKeys.DEEP_COLD_OCEAN);
            BIOMES.add(BiomeKeys.FROZEN_OCEAN);
            BIOMES.add(BiomeKeys.DEEP_FROZEN_OCEAN);
            BIOMES.add(BiomeKeys.MUSHROOM_FIELDS);
            BIOMES.add(BiomeKeys.DRIPSTONE_CAVES);
            BIOMES.add(BiomeKeys.LUSH_CAVES);
            BIOMES.add(BiomeKeys.DEEP_DARK);
            BIOMES.add(BiomeKeys.NETHER_WASTES);
            BIOMES.add(BiomeKeys.WARPED_FOREST);
            BIOMES.add(BiomeKeys.CRIMSON_FOREST);
            BIOMES.add(BiomeKeys.SOUL_SAND_VALLEY);
            BIOMES.add(BiomeKeys.BASALT_DELTAS);
            BIOMES.add(BiomeKeys.THE_END);
            BIOMES.add(BiomeKeys.END_HIGHLANDS);
            BIOMES.add(BiomeKeys.END_MIDLANDS);
            BIOMES.add(BiomeKeys.SMALL_END_ISLANDS);
            BIOMES.add(BiomeKeys.END_BARRENS);

            // Add all those Biomes into a Map together with a water-color for it.
            for (RegistryKey<Biome> key : BIOMES) {
                File tmp = ResourceUtil.extractAsset(ResourceUtil.getModIdentifier("textures/colors/water/" + key.getValue().getPath() + ".png"), new File(Constants.TEMP_DIR, key.getValue().getPath() + ".png"), true);
                BIOME_COLORS.put(key, tmp == null ? 0xff0000 : ImageIO.read(tmp).getRGB(0, 0));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public int getColorForBiome(RegistryKey<Biome> key) {
        return BIOME_COLORS.get(key);
    }

    public boolean shouldReplace(RegistryKey<Biome> key) {
        return BIOME_COLORS.containsKey(key);
    }
}
