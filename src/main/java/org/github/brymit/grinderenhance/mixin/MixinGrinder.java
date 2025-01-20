package org.github.brymit.grinderenhance.mixin;

import com.brandon3055.draconicevolution.blocks.tileentity.TileGrinder;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.boss.WitherEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(TileGrinder.class)
public class MixinGrinder {
    @Redirect(method = "queNextTarget()V", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;isInvulnerable()Z"))
    public boolean addInvulnerableTest(LivingEntity instance) {
        if (instance.isInvulnerable()) return true;
        if (instance instanceof WitherEntity) return ((WitherEntity) instance).getInvulnerableTicks() > 0;
        return false;
    }
}
