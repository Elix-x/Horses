package code.elix_x.mods.btam;

import code.elix_x.mods.btam.renderer.ItemRenderingRegistry;
import code.elix_x.mods.btam.renderer.ItemRenderingRegistry.EnumItemRendererRegisterType;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class GlobalRegistry {

	public static void registerItem(Item item, String name) {
		item.setUnlocalizedName(name);
		ItemRenderingRegistry.registerItemModel(item, name, EnumItemRendererRegisterType.MISSINGMODID);
		GameRegistry.registerItem(item, name);
	}

}
