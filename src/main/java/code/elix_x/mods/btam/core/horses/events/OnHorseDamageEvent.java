package code.elix_x.mods.btam.core.horses.events;

import java.lang.reflect.Field;

import code.elix_x.mods.btam.core.horses.HorseArmorRegistry;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.inventory.AnimalChest;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

public class OnHorseDamageEvent {

	public OnHorseDamageEvent() {

	}
	
	public void onHorseDamge(LivingAttackEvent event){
		if(event.entityLiving instanceof EntityHorse){
			
		}
	}
	
	@SubscribeEvent
	public void onHorseHurt(LivingHurtEvent event){
		if(event.entityLiving instanceof EntityHorse){
			try {
				event.ammount = applyArmorCalculations(event.source, event.ammount, (EntityHorse) event.entityLiving);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	protected float applyArmorCalculations(DamageSource p_70655_1_, float p_70655_2_, EntityHorse horse) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException
    {
        if (!p_70655_1_.isUnblockable())
        {
            int i = 25 - this.getTotalArmorValue(horse);
            float f1 = p_70655_2_ * (float)i;
            this.damageArmor(p_70655_2_, horse);
            p_70655_2_ = f1 / 25.0F;
        }

        return p_70655_2_;
    }

	private void damageArmor(float amount, EntityHorse horse) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		AnimalChest horseChest = getHorseChest(horse);
		if (amount < 1.0F)
		{
			amount = 1.0F;
		}
		if (horseChest.getStackInSlot(1) != null)
		{
			horseChest.getStackInSlot(1).damageItem((int)amount, horse);

			if (horseChest.getStackInSlot(1).stackSize == 0)
			{
				horseChest.setInventorySlotContents(1, null);;
			}
		}
	}

	private int getTotalArmorValue(EntityHorse horse) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
		AnimalChest horseChest = getHorseChest(horse);
		return horseChest.getStackInSlot(1) == null ? 0 : HorseArmorRegistry.getMaterial(horseChest.getStackInSlot(1)).getDamageReductionAmount();
	}

	private AnimalChest getHorseChest(EntityHorse horse) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		return ReflectionHelper.getPrivateValue(EntityHorse.class, horse, "horseChest");
	}
}
