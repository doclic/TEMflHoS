package me.doclic.temflhos.mixin.crashpatch;

import me.doclic.temflhos.module.ModuleManager;
import me.doclic.temflhos.util.CommonFunctionsKt;
import net.minecraft.block.BlockNewLog;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.util.EnumChatFormatting;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(BlockNewLog.class)
public class MixinBlockNewLog {
    @Final
    @Shadow
    public static PropertyEnum<BlockPlanks.EnumType> VARIANT;

    @ModifyVariable(method = "getStateFromMeta", at = @At("HEAD"), ordinal = 0, argsOnly = true)
    public int getStateFromMeta(int meta){
        if (!ModuleManager.INSTANCE.isEnabled("crash_patch")) return meta;
        if (!VARIANT.getAllowedValues().contains(BlockPlanks.EnumType.byMetadata((meta & 3) + 4))) {
            CommonFunctionsKt.tChat(EnumChatFormatting.GREEN + "Crashpatch prevented a crash!");
            return 0;
        }
        return meta;
    }

}
