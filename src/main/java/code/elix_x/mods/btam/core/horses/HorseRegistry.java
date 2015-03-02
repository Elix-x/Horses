package code.elix_x.mods.btam.core.horses;

import java.util.Random;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import code.elix_x.mods.btam.BTAMBase;
import code.elix_x.mods.btam.GlobalRegistry;
import code.elix_x.mods.btam.core.horses.ItemHorseArmor.HorseArmorMaterial;
import code.elix_x.mods.btam.core.horses.events.OnHorseDamageEvent;
import code.elix_x.mods.btam.core.horses.events.OpenHorseInventoryEvent;

public class HorseRegistry {
	
	public static Item obsidianHorseArmor;

	public static void preInit(FMLPreInitializationEvent event){
		
	}
	
	public static void init(FMLInitializationEvent event){
//		registerEntity(NewEntityHorse.class, "New Horse", 121);
		
		obsidianHorseArmor = new Item().setCreativeTab(CreativeTabs.tabMisc);
//		GlobalRegistry.registerItem(obsidianHorseArmor, "obsdianHorseArmor");
		
		HorseArmorRegistry.registerHorseArmor(Items.iron_horse_armor, new HorseArmorMaterial(ArmorMaterial.IRON, "textures/entity/horse/armor/horse_armor_iron.png", "meo"));
		Items.iron_horse_armor.setMaxDamage(HorseArmorRegistry.getMaterial(Items.iron_horse_armor).getMaxDamage());

		HorseArmorRegistry.registerHorseArmor(Items.golden_horse_armor, new HorseArmorMaterial(ArmorMaterial.GOLD, "textures/entity/horse/armor/horse_armor_gold.png", "goo"));
		Items.golden_horse_armor.setMaxDamage(HorseArmorRegistry.getMaterial(Items.golden_horse_armor).getMaxDamage());
		
		HorseArmorRegistry.registerHorseArmor(Items.diamond_horse_armor, new HorseArmorMaterial(ArmorMaterial.DIAMOND, "textures/entity/horse/armor/horse_armor_diamond.png", "dio"));
		Items.diamond_horse_armor.setMaxDamage(HorseArmorRegistry.getMaterial(Items.diamond_horse_armor).getMaxDamage());
		
		HorseArmorRegistry.registerHorseArmor(obsidianHorseArmor, new HorseArmorMaterial("Obsidian", 1000, 12, 10, BTAMBase.constructResource("textures/entity/horse/armor/horse_armor_obsidian.png"), "obsi", Item.getItemFromBlock(Blocks.obsidian)));
		obsidianHorseArmor.setMaxDamage(HorseArmorRegistry.getMaterial(obsidianHorseArmor).getMaxDamage());
		
		MinecraftForge.EVENT_BUS.register(new OpenHorseInventoryEvent());
		MinecraftForge.EVENT_BUS.register(new OnHorseDamageEvent());
	}
	
	public static void postInit(FMLPostInitializationEvent event){
		
	}

	public static void registerEntity(Class entity, String name) {
		registerEntity(entity, name, EntityRegistry.findGlobalUniqueEntityId());
	}
	
	public static void registerEntity(Class entity, String name, int entityId) {
		long x = name.hashCode();
		Random random = new Random(x);
		int mainColor = random.nextInt() * 16777215;
		int secColor = random.nextInt() * 16777215;
		EntityRegistry.registerGlobalEntityID(entity, name, entityId, mainColor, secColor);
		EntityRegistry.registerModEntity(entity, name, entityId, BTAMBase.INSTANCE, 90, 1, true);
		//EntityList.entityEggs.put(Integer.valueOf(entityId), new EntityList(entityId, mainColor, secColor));	
	}
}
