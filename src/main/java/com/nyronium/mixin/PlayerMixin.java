package com.nyronium.mixin;

import com.nyronium.actions.ActionTicker;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Player.class)
public class PlayerMixin {
    @Inject(method = "tick", at = @At("RETURN"))
    private void reverb$tick(CallbackInfo ci) {
        Player player = (Player) (Object) this;
        if(player.level().isClientSide()) return;
        ActionTicker.INSTANCE.handle(player);
    }
}
