package code.elix_x.mods.btam.core.horses;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.ArrayUtils;

import code.elix_x.mods.btam.core.horses.ItemHorseArmor.HorseArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class HorseArmorRegistry {

	private static Map<Item, HorseArmorMaterial> armors = new HashMap();
	private static Map<HorseArmorMaterial, Item> armorss = new HashMap();
	private static Item[] armorIds = new Item[]{};

	public static void registerHorseArmor(ItemHorseArmor item) {
		if(ArrayUtils.contains(armorIds, item))
			return;
		
		armors.put(item, item.getMaterial());
		armorss.put(item.getMaterial(), item);
		ArrayUtils.add(armorIds, item);
		
//		System.out.println("Succesfully registered item: " + item.getUnlocalizedName());
//		System.out.println("armorIds contains this item: " + ArrayUtils.contains(armorIds, item));
	}
	
	public static void registerHorseArmor(Item item, HorseArmorMaterial material) {
		if(ArrayUtils.contains(armorIds, item))
			return;
		
		armors.put(item, material);
		armorss.put(material, item);
		armorIds = ArrayUtils.add(armorIds, item);
		
//		System.out.println("Succesfully registered item: " + item.getUnlocalizedName());
//		System.out.println("armorIds contains this item: " + ArrayUtils.contains(armorIds, item));
	}

	public static boolean isArmorValid(ItemStack itemstack) {
		if(itemstack == null)
			return false;
		return isArmorValid(itemstack.getItem());
	}

	public static boolean isArmorValid(Item item) {
		return armors.containsKey(item);
	}

	public static boolean isArmorValid(HorseArmorMaterial material) {
		return isArmorValid(getItem(material));
	}

	public static HorseArmorMaterial getMaterial(ItemStack itemstack){
		if(itemstack == null)
			return null;
		return getMaterial(itemstack.getItem());
	}

	public static HorseArmorMaterial getMaterial(Item item){
		return armors.get(item);
	}
	
	public static HorseArmorMaterial getMaterial(int armorId) {
		return armors.get(getItem(armorId));
	}

	public static Item getItem(HorseArmorMaterial material){
		return armorss.get(material);
	}

	public static Item getItem(int id){
		if(id == -1)
			return null;
		return armorIds[id];
	}

	public static int getId(ItemStack itemstack){
		if(itemstack == null)
			return -1;
		return getId(itemstack.getItem());
	}

	public static int getId(Item item) {
//		System.out.println("armorIds contains this item: " + ArrayUtils.contains(armorIds, item));
		if(!ArrayUtils.contains(armorIds, item))
			return -1;
		return ArrayUtils.indexOf(armorIds, item);
	}

	public static int getId(HorseArmorMaterial material) {
		return getId(getItem(material));
	}

}
