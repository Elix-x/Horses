package code.elix_x.mods.btam.core.horses.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import code.elix_x.mods.btam.core.horses.NewEntityHorse;

public class NewContainerHorseInventory extends Container{

	private IInventory horseInventory;
    private NewEntityHorse theHorse;
    private static final String __OBFID = "CL_00001751";

    public NewContainerHorseInventory(IInventory playerInventory, final IInventory horseInventoryIn, final NewEntityHorse horse, EntityPlayer player)
    {
        this.horseInventory = horseInventoryIn;
        this.theHorse = horse;
        byte b0 = 3;
        horseInventoryIn.openInventory(player);
        int i = (b0 - 4) * 18;
        this.addSlotToContainer(new Slot(horseInventoryIn, 0, 8, 18)
        {
            private static final String __OBFID = "CL_00001752";
            public boolean isItemValid(ItemStack stack)
            {
                return super.isItemValid(stack) && stack.getItem() == Items.saddle && !this.getHasStack();
            }
            public void onSlotChanged() {
            	theHorse.updateArmorAndSaddle();
				super.onSlotChanged();
			}
        });
        this.addSlotToContainer(new Slot(horseInventoryIn, 1, 8, 36)
        {
            private static final String __OBFID = "CL_00001753";
            public boolean isItemValid(ItemStack stack)
            {
                return super.isItemValid(stack) && horse.canWearArmor() && NewEntityHorse.func_146085_a(stack.getItem());
            }
            @SideOnly(Side.CLIENT)
            public boolean canBeHovered()
            {
                return horse.canWearArmor();
            }
        });
        int j;
        int k;

        if (horse.isChested())
        {
            for (j = 0; j < b0; ++j)
            {
                for (k = 0; k < 5; ++k)
                {
                    this.addSlotToContainer(new Slot(horseInventoryIn, 2 + k + j * 5, 80 + k * 18, 18 + j * 18));
                }
            }
        }

        for (j = 0; j < 3; ++j)
        {
            for (k = 0; k < 9; ++k)
            {
                this.addSlotToContainer(new Slot(playerInventory, k + j * 9 + 9, 8 + k * 18, 102 + j * 18 + i));
            }
        }

        for (j = 0; j < 9; ++j)
        {
            this.addSlotToContainer(new Slot(playerInventory, j, 8 + j * 18, 160 + i));
        }
    }

    public boolean canInteractWith(EntityPlayer playerIn)
    {
        return this.horseInventory.isUseableByPlayer(playerIn) && this.theHorse.isEntityAlive() && this.theHorse.getDistanceToEntity(playerIn) < 8.0F;
    }

    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index)
    {
        ItemStack itemstack = null;
        Slot slot = (Slot)this.inventorySlots.get(index);

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (index < this.horseInventory.getSizeInventory())
            {
                if (!this.mergeItemStack(itemstack1, this.horseInventory.getSizeInventory(), this.inventorySlots.size(), true))
                {
                    return null;
                }
            }
            else if (this.getSlot(1).isItemValid(itemstack1) && !this.getSlot(1).getHasStack())
            {
                if (!this.mergeItemStack(itemstack1, 1, 2, false))
                {
                    return null;
                }
            }
            else if (this.getSlot(0).isItemValid(itemstack1))
            {
                if (!this.mergeItemStack(itemstack1, 0, 1, false))
                {
                    return null;
                }
            }
            else if (this.horseInventory.getSizeInventory() <= 2 || !this.mergeItemStack(itemstack1, 2, this.horseInventory.getSizeInventory(), false))
            {
                return null;
            }

            if (itemstack1.stackSize == 0)
            {
                slot.putStack((ItemStack)null);
            }
            else
            {
                slot.onSlotChanged();
            }
        }

        return itemstack;
    }

    public void onContainerClosed(EntityPlayer playerIn)
    {
        super.onContainerClosed(playerIn);
        this.horseInventory.closeInventory(playerIn);
    }
	
}
