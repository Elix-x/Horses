package code.elix_x.mods.btam.proxy;

import code.elix_x.mods.btam.core.horses.NewEntityHorse;
import code.elix_x.mods.btam.core.horses.NewEntityHorseRenderer;
import code.elix_x.mods.btam.core.horses.NewModelHorse;
import code.elix_x.mods.btam.core.horses.renderer.EntityHorseRenderer;
import code.elix_x.mods.btam.renderer.ItemRenderingRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelHorse;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class BTAMClientProxy extends BTAMCommonProxy{

	public void preInit(FMLPreInitializationEvent event){
		ItemRenderingRegistry.preInit(event);
	}

	public void init(FMLInitializationEvent event){
//		RenderingRegistry.registerEntityRenderingHandler(NewEntityHorse.class, new NewEntityHorseRenderer(Minecraft.getMinecraft().getRenderManager(), new NewModelHorse(), 1.0f));
		RenderingRegistry.registerEntityRenderingHandler(EntityHorse.class, new EntityHorseRenderer(Minecraft.getMinecraft().getRenderManager(), new ModelHorse()));
		
		ItemRenderingRegistry.init(event);
	}

	public void postInit(FMLPostInitializationEvent event){
		ItemRenderingRegistry.postInit(event);
	}

}
