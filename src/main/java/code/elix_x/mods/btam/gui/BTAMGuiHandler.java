package code.elix_x.mods.btam.gui;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import code.elix_x.mods.btam.BTAMBase;
import code.elix_x.mods.btam.core.horses.NewEntityHorse;
import code.elix_x.mods.btam.core.horses.gui.NewContainerHorseInventory;
import code.elix_x.mods.btam.core.horses.gui.NewGuiScreenHorseInventory;

public class BTAMGuiHandler implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		/*TileEntity te = world.getTileEntity(x, y, z);

		if(te != null){
			switch(ID){
			case MainToolsCompressor.guiIdToolsCompressor:
				if(te instanceof TileEntityToolsCompressor){
					return new ContainerToolsCompressor(player.inventory, (TileEntityToolsCompressor) te);
				}
				return null;
			}
		}*/
		
//		Entity e = world.getE
		switch(ID){
		case BTAMBase.newHorseGui:
			Entity entity = world.getEntityByID(x);
			if(entity != null){
				if(entity instanceof NewEntityHorse){
					NewEntityHorse horse = (NewEntityHorse) entity;
					return new NewContainerHorseInventory(player.inventory, horse.horseChest, horse, player);
				}
			}
			return null;
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		/*TileEntity te = world.getTileEntity(x, y, z);

		if(te != null){
			switch(ID){
			case MainToolsCompressor.guiIdToolsCompressor:
				if(te instanceof TileEntityToolsCompressor){
					return new GUIToolsCompressor(player.inventory, (TileEntityToolsCompressor) te);
				}
				return null;
			}
		}*/
		switch(ID){
		case BTAMBase.newHorseGui:
			Entity entity = world.getEntityByID(x);
			if(entity != null){
				if(entity instanceof NewEntityHorse){
					NewEntityHorse horse = (NewEntityHorse) entity;
					return new NewGuiScreenHorseInventory(player.inventory, horse.horseChest, horse);
				}
			}
			return null;
		}
		return null;
	}

}
