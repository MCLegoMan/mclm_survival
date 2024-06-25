/*
    mclm_survival
    Contributor(s): MCLegoMan
    Github: https://github.com/MCLegoMan/mclm_survival
    Licence: GNU LGPLv3
*/

package com.mclegoman.mclm_survival.mixin.client.accessors;

import net.minecraft.client.C_5664496;
import net.minecraft.client.interaction.ClientPlayerInteractionManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(ClientPlayerInteractionManager.class)
public interface ClientPlayerInteractionManagerAccessor {
    @Accessor
    C_5664496 getMinecraft();
}
