/*
    mclm_survival
    Contributor(s): MCLegoMan
    Github: https://github.com/MCLegoMan/mclm_survival
    Licence: GNU LGPLv3
*/

// This class should be identical to SurvivalInteractionManager but with an added constructor, so we can use it.

package com.mclegoman.mclm_survival.client.interaction;

import net.minecraft.block.Block;
import net.minecraft.client.C_5664496;
import net.minecraft.client.interaction.ClientPlayerInteractionManager;
import net.minecraft.entity.living.player.PlayerEntity;
import net.minecraft.unmapped.C_1051907;
import net.minecraft.world.World;

public class SurvivalPlayerInteractionManager extends ClientPlayerInteractionManager {
    private int targetBlockX;
    private int targetBlockY;
    private int targetBlockZ;
    private int miningProgress;
    private int lastMiningProgress;
    private int miningCooldown;
    private C_1051907 f_5967858;
    public SurvivalPlayerInteractionManager(C_5664496 c_5664496) {
        super(c_5664496);
    }
    public void m_1312357(PlayerEntity playerEntity) {
        playerEntity.inventory.inventorySlots[5] = Block.STONE_SLAB.id;
        playerEntity.inventory.f_7338809[5] = 99;
        playerEntity.inventory.inventorySlots[6] = Block.STONE.id;
        playerEntity.inventory.f_7338809[6] = 99;
        playerEntity.inventory.inventorySlots[7] = Block.FLOWING_WATER.id;
        playerEntity.inventory.f_7338809[7] = 99;
        playerEntity.inventory.inventorySlots[8] = Block.FLOWING_LAVA.id;
        playerEntity.inventory.f_7338809[8] = 99;
    }
    public void finishMiningBlock(int i, int j, int k) {
        int var4 = this.minecraft.f_5854988.m_5102244(i, j, k);
        Block.BY_ID[var4].dropItems(this.minecraft.f_5854988);
        super.finishMiningBlock(i, j, k);
    }
    public boolean m_5458213(int i) {
        return this.minecraft.f_6058446.inventory.m_8673786(i);
    }
    public void mineBlockInCreative(int i, int j, int k) {
        int var4;
        if ((var4 = this.minecraft.f_5854988.m_5102244(i, j, k)) > 0 && Block.BY_ID[var4].m_3343046() == 0) this.finishMiningBlock(i, j, k);
    }
    public void stopMiningBlock() {
        this.miningProgress = 0;
        this.miningCooldown = 0;
    }
    public void startMiningBlock(int i, int j, int k) {
        if (this.miningCooldown > 0) {
            --this.miningCooldown;
        } else if (i == this.targetBlockX && j == this.targetBlockY && k == this.targetBlockZ) {
            int var4;
            if ((var4 = this.minecraft.f_5854988.m_5102244(i, j, k)) != 0) {
                Block var5 = Block.BY_ID[var4];
                this.lastMiningProgress = var5.m_3343046();
                ++this.miningProgress;
                if (this.miningProgress == this.lastMiningProgress + 1) {
                    this.finishMiningBlock(i, j, k);
                    this.miningProgress = 0;
                    this.miningCooldown = 5;
                }
            }
        } else {
            this.miningProgress = 0;
            this.targetBlockX = i;
            this.targetBlockY = j;
            this.targetBlockZ = k;
        }
    }

    public void m_8681363(float f) {
        if (this.miningProgress <= 0) this.minecraft.f_4021716.f_3435063 = 0.0F;
        else this.minecraft.f_4021716.f_3435063 = ((float)this.miningProgress + f - 1.0F) / (float)this.lastMiningProgress;
    }
    public float getReach() {
        return 4.0F;
    }
    public boolean m_6297649(PlayerEntity playerEntity, int i) {
        Block var3;
        if ((var3 = Block.BY_ID[i]) == Block.RED_MUSHROOM && this.minecraft.f_6058446.inventory.m_8673786(i)) {
            playerEntity.damage(null, 3);
            return true;
        } else if (var3 == Block.BROWN_MUSHROOM && this.minecraft.f_6058446.inventory.m_8673786(i)) {
            if (playerEntity.health > 0) {
                playerEntity.health += 5;
                if (playerEntity.health > 20) playerEntity.health = 20;
                playerEntity.f_6260339 = playerEntity.defaultMaxHealth / 2;
            }
            return true;
        } else {
            return false;
        }
    }
    public void m_9659430(World world) {
        super.m_9659430(world);
        this.f_5967858 = new C_1051907(world);
        int var2 = world.f_3061106 * world.f_8212213 * world.f_4184003 / 64 / 64 / 8;
        for(int var3 = 0; var3 < var2; ++var3) this.f_5967858.m_6963744(var2, world.f_2176096, null);
    }
    public void tick() {
        this.f_5967858.m_8088466();
    }
}
