package de.chloedev.resourceutils.mixin;

import de.chloedev.resourceutils.ResourceUtilsClient;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.RunArgs;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public class MixinMinecraftClient {

    @Inject(method = "<init>", at = @At(value = "FIELD", target = "Lnet/minecraft/client/MinecraftClient;navigationType:Lnet/minecraft/client/gui/navigation/GuiNavigationType;"))
    public void preInit(RunArgs args, CallbackInfo ci) {
        ResourceUtilsClient.getInstance().preInit();
    }

    @Inject(method = "<init>", at = @At("TAIL"))
    public void init(RunArgs args, CallbackInfo ci) {
        ResourceUtilsClient.getInstance().init();
    }
}
