package de.chloedev.resourceutils.mixin;

import com.mojang.blaze3d.systems.RenderSystem;
import de.chloedev.resourceutils.util.ResourceUtil;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.CreativeInventoryScreen;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.screen.ingame.InventoryScreen;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

public class ScreenMixins {

    @Mixin(InventoryScreen.class)
    public static class MixinInventoryScreen {
        @Redirect(method = "drawBackground", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screen/ingame/InventoryScreen;drawEntity(Lnet/minecraft/client/gui/DrawContext;IIIIIFFFLnet/minecraft/entity/LivingEntity;)V"))
        public void removeEntityModel(DrawContext context, int x1, int y1, int x2, int y2, int size, float f, float mouseX, float mouseY, LivingEntity entity) {
            System.out.println();
        }
    }

    @Mixin(CreativeInventoryScreen.class)
    public static class MixinCreativeInventoryScreen {
        @Redirect(method = "drawBackground", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screen/ingame/InventoryScreen;drawEntity(Lnet/minecraft/client/gui/DrawContext;IIIIIFFFLnet/minecraft/entity/LivingEntity;)V"))
        public void removeEntityModel(DrawContext context, int x1, int y1, int x2, int y2, int size, float f, float mouseX, float mouseY, LivingEntity entity) {
        }
    }
    @Mixin(HandledScreen.class)
    public static class MixinHandledScreen {
        @Redirect(method = "drawSlotHighlight", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/DrawContext;fillGradient(Lnet/minecraft/client/render/RenderLayer;IIIIIII)V"))
        private static void drawSlotHighlight(DrawContext context, RenderLayer layer, int startX, int startY, int endX, int endY, int colorStart, int colorEnd, int z) {
            RenderSystem.enableBlend();
            context.drawTexture(ResourceUtil.getModIdentifier("textures/gui/slot_highlight.png"), startX, startY, endX - startX, endY - startY, 0, 0, 16, 16, 16, 16);
            RenderSystem.disableBlend();
        }
    }
}