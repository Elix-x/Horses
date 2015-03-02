package code.elix_x.mods.btam.renderer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import code.elix_x.mods.btam.BTAMBase;

import com.sun.org.apache.xml.internal.security.encryption.Reference;

public class ItemRenderingRegistry {

	private static List<Item> items = new ArrayList<Item>();
	private static Map<Item, String> itemsModels = new HashMap();

	public static void preInit(FMLPreInitializationEvent event) {

	}

	public static void init(FMLInitializationEvent event) {
		registerItemRenderers();
	}

	public static void postInit(FMLPostInitializationEvent event) {

	}

	private static void registerItemRenderers() {
		RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
		for(Item item : items){
			System.out.println("Registering item (" + item.getUnlocalizedName() + ") model: " + itemsModels.get(item));
			renderItem.getItemModelMesher().register(item, 0, new ModelResourceLocation(itemsModels.get(item), "inventory"));
		}
	}

	public static void registerItemModel(Item item, String model, EnumItemRendererRegisterType type){
		if(type == EnumItemRendererRegisterType.MISSINGMODID){
			registerItemModel(item, BTAMBase.constructResource(model), EnumItemRendererRegisterType.FINAL);
		}
		if(type == EnumItemRendererRegisterType.FINAL){
			items.add(item);
			itemsModels.put(item, model);
		}
	}

	public static enum EnumItemRendererRegisterType{
		FINAL, MISSINGMODID;
	}
}
