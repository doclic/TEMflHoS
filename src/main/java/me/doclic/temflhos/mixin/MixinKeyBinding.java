package me.doclic.temflhos.mixin;

import me.doclic.temflhos.event.KeyboardEvent;
import me.doclic.temflhos.event.ListenerManager;
import net.minecraft.client.Minecraft;
import org.lwjgl.input.Keyboard;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.lang.reflect.Field;
import java.nio.ByteBuffer;

@Mixin(Minecraft.class)
public abstract class MixinKeyBinding {
    @Unique
    private Field temflhos$readBufferField = null;

    @Inject(method = "runTick", at = @At("HEAD"))
    private void onRunTick(CallbackInfo ci) {
        try {
            if (temflhos$readBufferField == null) {
                temflhos$readBufferField = Keyboard.class.getDeclaredField("readBuffer");
                temflhos$readBufferField.setAccessible(true);
            }

            ByteBuffer readBuffer = (ByteBuffer) temflhos$readBufferField.get(null);
            readBuffer.mark();
            while (Keyboard.next()) {
                final KeyboardEvent e = new KeyboardEvent(Keyboard.getEventKey(), Keyboard.getEventKeyState());
                ListenerManager.INSTANCE.queue(e);
            }
            readBuffer.reset();
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
