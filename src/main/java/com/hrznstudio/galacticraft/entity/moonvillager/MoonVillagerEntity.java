package com.hrznstudio.galacticraft.entity.moonvillager;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AbsoluteHand;
import net.minecraft.world.World;

public class MoonVillagerEntity extends LivingEntity {

    public MoonVillagerEntity(EntityType<? extends MoonVillagerEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public Iterable<ItemStack> getArmorItems() {
        return null;
    }

    @Override
    public ItemStack getEquippedStack(EquipmentSlot equipmentSlot) {
        return ItemStack.EMPTY;
    }

    @Override
    public void setEquippedStack(EquipmentSlot equipmentSlot, ItemStack itemStack) {

    }

    @Override
    public AbsoluteHand getMainHand() {
        return null;
    }
}
