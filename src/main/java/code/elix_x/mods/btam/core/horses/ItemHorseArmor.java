package code.elix_x.mods.btam.core.horses;

import java.util.List;

import net.minecraft.block.BlockDispenser;
import net.minecraft.command.IEntitySelector;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.dispenser.BehaviorDefaultDispenseItem;
import net.minecraft.dispenser.IBehaviorDispenseItem;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;

public class ItemHorseArmor extends Item{

	private static final IBehaviorDispenseItem dispenserBehavior = new BehaviorDefaultDispenseItem()
	{
		protected ItemStack dispenseStack(IBlockSource source, ItemStack itemstack)
		{
			BlockPos blockpos = source.getBlockPos().offset(BlockDispenser.getFacing(source.getBlockMetadata()));
			int i = blockpos.getX();
			int j = blockpos.getY();
			int k = blockpos.getZ();
			AxisAlignedBB axisalignedbb = new AxisAlignedBB((double)i, (double)j, (double)k, (double)(i + 1), (double)(j + 1), (double)(k + 1));
			List list = source.getWorld().getEntitiesWithinAABB(NewEntityHorse.class, axisalignedbb, IEntitySelector.NOT_SPECTATING);

			if (list.size() > 0)
			{
				NewEntityHorse horse = (NewEntityHorse)list.get(0);
				if(horse.replaceItemInInventory(1, itemstack)){
					--itemstack.stackSize;
					return itemstack;
				}
				/* int l = 0;// Forge: We fix the indexes. Mojang Stop hard coding this!
	                int i1 = EntityLiving.getArmorPosition(stack);
	                ItemStack itemstack1 = stack.copy();
	                itemstack1.stackSize = 1;
	                entitylivingbase.setCurrentItemOrArmor(i1 - l, itemstack1);

	                if (entitylivingbase instanceof EntityLiving)
	                {
	                    ((EntityLiving)entitylivingbase).setEquipmentDropChance(i1, 2.0F);
	                }*/
			}
			return super.dispenseStack(source, itemstack);
		}
	};

	private HorseArmorMaterial material;

	public ItemHorseArmor(HorseArmorMaterial mat){
		material = mat;
		maxStackSize = 1;
		setMaxDamage(mat.getMaxDamage());
		setCreativeTab(CreativeTabs.tabCombat);
		BlockDispenser.dispenseBehaviorRegistry.putObject(this, dispenserBehavior);
		HorseArmorRegistry.registerHorseArmor(this);
	}

	public ItemHorseArmor(String n, int maxDamage, int reductionAmounts, int enchant, String texture, String shortTextureName){
		this(new HorseArmorMaterial(n, maxDamage, reductionAmounts, enchant, texture, shortTextureName));
	}

	public ItemHorseArmor(String n, int maxDamage, int reductionAmounts, int enchant, String texture, String shortTextureName, Item repair){
		this(new HorseArmorMaterial(n, maxDamage, reductionAmounts, enchant, texture, shortTextureName, repair));
	}

	public HorseArmorMaterial getMaterial(){
		return material;
	}

	public String getMaterialName(){
		return getMaterial().getName();
	}

	public int getMaxDamage(){
		return getMaterial().getMaxDamage();
	}

	public int getDamageReductionAmount(){
		return getMaterial().getDamageReductionAmount();
	}

	public int getEnchantability(){
		return getMaterial().getEnchantability();
	}

	public Item getRepairItem(){
		return getMaterial().getRepairItem();
	}

	public boolean reparable(){
		return getMaterial().reparable();
	}

	public static class HorseArmorMaterial{

		private final String name;
		private final int maxDamage;
		private final int damageReductionAmount;
		private final int enchantability;
		private Item repairItem;
		
		private String textureName;
		private String shortTextureName;

		public HorseArmorMaterial(String n, int maxD, int reductionAmounts, int enchant, String texture, String shortTexture)
		{
			name = n;
			maxDamage = maxD;
			damageReductionAmount = reductionAmounts;
			enchantability = enchant;
			repairItem = null;
			textureName = texture;
			shortTextureName = shortTexture;
		}

		public HorseArmorMaterial(String n, int maxDamage, int reductionAmounts, int enchant, String texture, String shortTextureName, Item repair)
		{
			this(n, maxDamage, reductionAmounts, enchant, texture, shortTextureName);
			repairItem = repair;
		}

		public HorseArmorMaterial(ArmorMaterial material, String texture, String shortTextureName) {
			this(material.getName(), material.getDurability(1), material.getDamageReductionAmount(1), material.getEnchantability(), texture, shortTextureName, material.getRepairItem());
		}

		public String getName(){
			return name;
		}

		public int getMaxDamage(){
			return maxDamage;
		}

		public int getDamageReductionAmount(){
			return damageReductionAmount;
		}

		public int getEnchantability(){
			return enchantability;
		}

		public Item getRepairItem(){
			return repairItem;
		}

		public boolean reparable(){
			return getRepairItem() != null;
		}
		
		public String getTextureName(){
			return textureName;
		}

		public String getShortTextureName() {
			return shortTextureName;
		}
	}
}
