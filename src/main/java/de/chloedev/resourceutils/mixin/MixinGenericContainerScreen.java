package de.chloedev.resourceutils.mixin;

import de.chloedev.resourceutils.util.ResourceUtil;
import net.minecraft.client.gui.screen.ingame.GenericContainerScreen;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.screen.ingame.ScreenHandlerProvider;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.GenericContainerScreenHandler;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(GenericContainerScreen.class)
public abstract class MixinGenericContainerScreen extends HandledScreen<GenericContainerScreenHandler> implements ScreenHandlerProvider<GenericContainerScreenHandler> {

    @Shadow
    @Final
    private int rows;

    public MixinGenericContainerScreen(GenericContainerScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    @ModifyArgs(method = "drawBackground", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/DrawContext;drawTexture(Lnet/minecraft/util/Identifier;IIIIII)V", ordinal = 0))
    public void renderTop(Args args) {
        if (rows == 3) {
            args.set(0, ResourceUtil.getVanillaIdentifier("textures/gui/container/generic_27.png"));
        }
    }


    @ModifyArgs(method = "drawBackground", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/DrawContext;drawTexture(Lnet/minecraft/util/Identifier;IIIIII)V", ordinal = 1))
    public void renderBottom(Args args) {
        if (rows == 3) {
            args.set(0, ResourceUtil.getVanillaIdentifier("textures/gui/container/generic_27.png"));
            args.set(4, 72);
        } else if (rows == 6) {
            // Mojang use "126", which causes the first pixel-line after the rows to be ignored when rendering. Possibly a typo? Possibly ignorance? idfk, but this fixes it.
            args.set(4, 126);
        }
    }
}
