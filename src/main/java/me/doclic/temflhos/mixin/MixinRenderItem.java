package me.doclic.temflhos.mixin;

import me.doclic.temflhos.event.ListenerManager;
import me.doclic.temflhos.event.RenderItemOverlayPostEvent;
import me.doclic.temflhos.event.SplashTextEvent;
import me.doclic.temflhos.util.RenderUtilKt;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(RenderItem.class)
public abstract class MixinRenderItem {
    @Inject(at = @At("RETURN"), method = "renderItemOverlayIntoGUI")
    public void renderItemOverlayIntoGUI(FontRenderer fr, ItemStack stack, int xPosition, int yPosition, String text, CallbackInfo ci) {
        final RenderItemOverlayPostEvent e = new RenderItemOverlayPostEvent(fr, stack, xPosition, yPosition, "");
        ListenerManager.INSTANCE.queue(e);
        ListenerManager.INSTANCE.waitForDispatch();
        RenderUtilKt.drawOverlay(e);
    }
}
