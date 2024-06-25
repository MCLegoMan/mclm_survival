/*
    mclm_survival
    Contributor(s): MCLegoMan
    Github: https://github.com/MCLegoMan/mclm_survival
    Licence: GNU LGPLv3
*/

package com.mclegoman.mclm_survival.mixin.client;

import com.mclegoman.mclm_survival.client.interaction.SurvivalPlayerInteractionManager;
import com.mclegoman.mclm_survival.mixin.client.accessors.ClientPlayerInteractionManagerAccessor;
import net.minecraft.client.C_5664496;
import net.minecraft.client.interaction.ClientPlayerInteractionManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(C_5664496.class)
public abstract class MinecraftClientMixin {
	@Shadow public ClientPlayerInteractionManager f_1273243;
	@Inject(method = "run", at = @At(value = "HEAD"))
	private void save$run(CallbackInfo ci) {
		this.f_1273243 = new SurvivalPlayerInteractionManager(((ClientPlayerInteractionManagerAccessor)this.f_1273243).getMinecraft());
	}
}