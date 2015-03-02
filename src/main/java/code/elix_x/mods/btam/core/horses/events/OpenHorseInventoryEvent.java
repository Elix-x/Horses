package code.elix_x.mods.btam.core.horses.events;

import java.lang.reflect.Field;

import code.elix_x.mods.btam.core.horses.HorseArmorRegistry;
import net.minecraft.client.gui.inventory.GuiScreenHorseInventory;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.inventory.ContainerHorseInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.event.entity.player.PlayerOpenContainerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class OpenHorseInventoryEvent {

	public OpenHorseInventoryEvent(){

	}

	@SubscribeEvent
	public void openInventory(PlayerOpenContainerEvent event){
		if(event.entityPlayer.openContainer instanceof ContainerHorseInventory){
			ContainerHorseInventory container = (ContainerHorseInventory) event.entityPlayer.openContainer;
			Slot armorSlot = container.getSlot(1);
			//			System.out.println("Saddle valid: " + armorSlot.isItemValid(new ItemStack(Items.saddle)) + " armor valid: " + armorSlot.isItemValid(new ItemStack(Items.diamond_horse_armor)) + " random item valid: " + armorSlot.isItemValid(new ItemStack(Items.golden_hoe)));
			try {
//				Field f = container.getClass().getField("theHorse");
				/*Field f = ContainerHorseInventory.class.getDeclaredField("theHorse");
				f.setAccessible(true);
				Object object = f.get(container);*/
				Object object = ReflectionHelper.getPrivateValue(ContainerHorseInventory.class, container, "theHorse");
				if(object instanceof EntityHorse){
					final EntityHorse horse = (EntityHorse) object;
					armorSlot = new Slot(armorSlot.inventory, 1, armorSlot.xDisplayPosition, armorSlot.yDisplayPosition){
						private static final String __OBFID = "CL_00001753";
						public boolean isItemValid(ItemStack itemstack)
						{
//							return true;
							return super.isItemValid(itemstack) && horse.canWearArmor()/* && HorseArmorRegistry.isArmorValid(itemstack)*/;
						}
						@SideOnly(Side.CLIENT)
						public boolean canBeHovered()
						{
							return horse.canWearArmor();
						}
					};
//					container.inventorySlots.remove(1);
					container.inventorySlots.set(1, armorSlot);
				}
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			}/* catch (NoSuchFieldException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}*/
		}
	}
	
	@SubscribeEvent
	public void openInventoryGui(GuiOpenEvent event){
		if(event.gui instanceof GuiScreenHorseInventory){
			GuiScreenHorseInventory gui = (GuiScreenHorseInventory) event.gui;
			ContainerHorseInventory container = (ContainerHorseInventory) gui.inventorySlots;
			Slot armorSlot = container.getSlot(1);
			//			System.out.println("Saddle valid: " + armorSlot.isItemValid(new ItemStack(Items.saddle)) + " armor valid: " + armorSlot.isItemValid(new ItemStack(Items.diamond_horse_armor)) + " random item valid: " + armorSlot.isItemValid(new ItemStack(Items.golden_hoe)));
			try {
//				Field f = container.getClass().getField("theHorse");
				/*Field f = ContainerHorseInventory.class.getDeclaredField("theHorse");
				f.setAccessible(true);
				Object object = f.get(container);*/
				Object object = ReflectionHelper.getPrivateValue(ContainerHorseInventory.class, container, "theHorse");
				if(object instanceof EntityHorse){
					final EntityHorse horse = (EntityHorse) object;
					armorSlot = new Slot(armorSlot.inventory, 1, armorSlot.xDisplayPosition, armorSlot.yDisplayPosition){
						private static final String __OBFID = "CL_00001753";
						public boolean isItemValid(ItemStack itemstack)
						{
//							return true;
							return super.isItemValid(itemstack) && horse.canWearArmor()/* && HorseArmorRegistry.isArmorValid(itemstack)*/;
						}
						@SideOnly(Side.CLIENT)
						public boolean canBeHovered()
						{
							return horse.canWearArmor();
						}
					};
//					container.inventorySlots.remove(1);
					container.inventorySlots.set(1, armorSlot);
				}
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			}/* catch (NoSuchFieldException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}*/
		}
	}
}
