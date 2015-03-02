package code.elix_x.mods.btam.core.horses.ai;

import code.elix_x.mods.btam.core.horses.NewEntityHorse;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.Vec3;

public class EntityAIRunAroundLikeCrazy extends EntityAIBase
{
    private NewEntityHorse horseHost;
    private double field_111178_b;
    private double field_111179_c;
    private double field_111176_d;
    private double field_111177_e;
    private static final String __OBFID = "CL_00001612";

    public EntityAIRunAroundLikeCrazy(NewEntityHorse p_i1653_1_, double p_i1653_2_)
    {
        this.horseHost = p_i1653_1_;
        this.field_111178_b = p_i1653_2_;
        this.setMutexBits(1);
    }

    public boolean shouldExecute()
    {
        if (!this.horseHost.isTame() && this.horseHost.riddenByEntity != null)
        {
            Vec3 vec3 = RandomPositionGenerator.findRandomTarget(this.horseHost, 5, 4);

            if (vec3 == null)
            {
                return false;
            }
            else
            {
                this.field_111179_c = vec3.xCoord;
                this.field_111176_d = vec3.yCoord;
                this.field_111177_e = vec3.zCoord;
                return true;
            }
        }
        else
        {
            return false;
        }
    }

    public void startExecuting()
    {
        this.horseHost.getNavigator().tryMoveToXYZ(this.field_111179_c, this.field_111176_d, this.field_111177_e, this.field_111178_b);
    }

    public boolean continueExecuting()
    {
        return !this.horseHost.getNavigator().noPath() && this.horseHost.riddenByEntity != null;
    }

    public void updateTask()
    {
        if (this.horseHost.getRNG().nextInt(50) == 0)
        {
            if (this.horseHost.riddenByEntity instanceof EntityPlayer)
            {
                int i = this.horseHost.getTemper();
                int j = this.horseHost.getMaxTemper();

                if (j > 0 && this.horseHost.getRNG().nextInt(j) < i)
                {
                    this.horseHost.setTamedBy((EntityPlayer)this.horseHost.riddenByEntity);
                    this.horseHost.worldObj.setEntityState(this.horseHost, (byte)7);
                    return;
                }

                this.horseHost.increaseTemper(5);
            }

            this.horseHost.riddenByEntity.mountEntity((Entity)null);
            this.horseHost.riddenByEntity = null;
            this.horseHost.makeHorseRearWithSound();
            this.horseHost.worldObj.setEntityState(this.horseHost, (byte)6);
        }
    }
}
