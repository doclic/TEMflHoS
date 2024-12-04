package me.doclic.temflhos.mixin;

import me.doclic.temflhos.module.ModuleManager;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.event.ClickEvent;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.IChatComponent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(GuiScreen.class)
public abstract class MixinGuiScreen {
    @Inject(at = @At("HEAD"), method = "handleComponentClick")
    public void handleComponentClick(IChatComponent component, CallbackInfoReturnable<Boolean> cir) {
        if (!ModuleManager.INSTANCE.getRegistry().get("suggest_commands").getEnabled().getValue()) return;
        if (component == null) return;
        ChatStyle style = component.getChatStyle();
        if (style == null) return;
        ClickEvent clickEvent = style.getChatClickEvent();
        if (clickEvent == null) return;
        if (clickEvent.getAction() != ClickEvent.Action.RUN_COMMAND) return;
        style.setChatClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, clickEvent.getValue()));
        component.setChatStyle(style);
    }
}
