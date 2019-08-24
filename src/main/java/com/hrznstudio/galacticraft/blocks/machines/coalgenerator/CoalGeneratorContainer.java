package com.hrznstudio.galacticraft.blocks.machines.coalgenerator;

import alexiil.mc.lib.attributes.item.compat.InventoryFixedWrapper;
import com.hrznstudio.galacticraft.blocks.machines.MachineContainer;
import com.hrznstudio.galacticraft.container.slot.ChargeSlot;
import com.hrznstudio.galacticraft.container.slot.ItemSpecificSlot;
import net.fabricmc.fabric.api.container.ContainerFactory;
import net.minecraft.container.Container;
import net.minecraft.container.Property;
import net.minecraft.container.Slot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

/**
 * @author <a href="https://github.com/StellarHorizons">StellarHorizons</a>
 */
public class CoalGeneratorContainer extends MachineContainer<CoalGeneratorBlockEntity> {

    public static final ContainerFactory<Container> FACTORY = createFactory(CoalGeneratorBlockEntity.class, CoalGeneratorContainer::new);

    private static Item[] fuel = new Item[]{Items.COAL_BLOCK, Items.COAL, Items.CHARCOAL, Items.AIR};
    private ItemStack itemStack;
    private Inventory inventory;

    public final Property status = Property.create();

    public CoalGeneratorContainer(int syncId, PlayerEntity playerEntity, CoalGeneratorBlockEntity generator) {
        super(syncId, playerEntity, generator);
        this.inventory = new InventoryFixedWrapper(generator.getInventory()) {
            @Override
            public boolean canPlayerUseInv(PlayerEntity player) {
                return CoalGeneratorContainer.this.canUse(player);
            }
        };
        addProperty(status);
        // Coal Generator fuel slot
        this.addSlot(new ItemSpecificSlot(this.inventory, 0, 8, 72, fuel));
        this.addSlot(new ChargeSlot(this.inventory, 1, 8, 8));

        // Player inventory slots
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(playerEntity.inventory, j + i * 9 + 9, 8 + j * 18, 94 + i * 18));
            }
        }

        // Hotbar slots
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerEntity.inventory, i, 8 + i * 18, 152));
        }

    }

    @Override
    public ItemStack transferSlot(PlayerEntity playerEntity, int slotId) {

        ItemStack itemStack = ItemStack.EMPTY;
        Slot slot = this.slotList.get(slotId);

        if (slot != null && slot.hasStack()) {
            ItemStack itemStack1 = slot.getStack();
            itemStack = itemStack1.copy();

            if (itemStack.isEmpty()) {
                return itemStack;
            }

            if (slotId < this.blockEntity.getInventory().getSlotCount()) {

                if (!this.insertItem(itemStack1, this.inventory.getInvSize(), this.slotList.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.insertItem(itemStack1, 0, this.inventory.getInvSize(), false)) {
                return ItemStack.EMPTY;
            }
            if (itemStack1.getCount() == 0) {
                slot.setStack(ItemStack.EMPTY);
            } else {
                slot.markDirty();
            }
        }
        return itemStack;
    }

    @Override
    public boolean canUse(PlayerEntity playerEntity) {
        return true;
    }
    
    @Override
    public void sendContentUpdates() {
        status.set(blockEntity.status.ordinal());
        super.sendContentUpdates();
    }

    @Override
    public void setProperties(int index, int value) {
        super.setProperties(index, value);
        blockEntity.status = CoalGeneratorStatus.get(status.get());
    }
}