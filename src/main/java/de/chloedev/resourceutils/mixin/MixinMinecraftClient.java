package de.chloedev.resourceutils.mixin;

import de.chloedev.resourceutils.res.WaterColormap;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.ModificationPhase;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.RunArgs;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public class MixinMinecraftClient {

    @Inject(method = "<init>", at = @At("TAIL"))
    public void init(RunArgs args, CallbackInfo ci) {
        WaterColormap colormap = new WaterColormap();
        BiomeModifications.create(new Identifier("fabric", "resourceutils_colormap"))
                .add(
                        ModificationPhase.REPLACEMENTS,
                        selCtx -> colormap.shouldReplace(selCtx.getBiomeKey()),
                        (selCtx, modCtx) -> {
                            modCtx.getEffects().setWaterColor(colormap.getColorForBiome(selCtx.getBiomeKey()));
                            modCtx.getEffects().setWaterFogColor(colormap.getColorForBiome(selCtx.getBiomeKey()));
                        }
                )
        ;
    }
}
