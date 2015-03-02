package code.elix_x.mods.btam;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import code.elix_x.mods.btam.core.horses.HorseRegistry;
import code.elix_x.mods.btam.gui.BTAMGuiHandler;
import code.elix_x.mods.btam.proxy.BTAMCommonProxy;

@Mod(modid = "BTAM", name = "Block Tools Armor Mod", version = "2.0")
public class BTAMBase {

	public static final String MODID = "BTAM";
	public static final String NAME = "Block Tools Armor Mod";
	public static final String VERSION = "2.0";
	
	public static final String commonProxy = "code.elix_x.mods.btam.proxy.BTAMCommonProxy";
	public static final String clientProxy = "code.elix_x.mods.btam.proxy.BTAMClientProxy";
	
	@SidedProxy(modId = MODID, serverSide = commonProxy, clientSide = clientProxy)
	public static BTAMCommonProxy proxy;
	
	@Instance(MODID)
	public static BTAMBase INSTANCE = new BTAMBase();
	
	public static SimpleNetworkWrapper net;
	
	public static final int newHorseGui = 0;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event){
		net = NetworkRegistry.INSTANCE.newSimpleChannel("BTAM");
		
		HorseRegistry.preInit(event);
		
		proxy.preInit(event);
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event){
		NetworkRegistry.INSTANCE.registerGuiHandler(INSTANCE, new BTAMGuiHandler());
		
		HorseRegistry.init(event);
		
		proxy.init(event);
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event){
		HorseRegistry.postInit(event);
		
		proxy.postInit(event);
	}

	public static String constructResource(String resource) {
		return MODID + ":" + resource;
	}
}
