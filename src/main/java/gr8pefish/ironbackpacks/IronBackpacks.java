package gr8pefish.ironbackpacks;

import gr8pefish.ironbackpacks.achievements.IronBackpacksAchievements;
import gr8pefish.ironbackpacks.api.Constants;
import gr8pefish.ironbackpacks.capabilities.IronBackpacksCapabilities;
import gr8pefish.ironbackpacks.client.gui.GuiHandler;
import gr8pefish.ironbackpacks.commands.IronBackpacksCommands;
import gr8pefish.ironbackpacks.config.ConfigHandler;
import gr8pefish.ironbackpacks.events.ForgeEventHandler;
import gr8pefish.ironbackpacks.integration.InterModSupport;
import gr8pefish.ironbackpacks.network.NetworkingHandler;
import gr8pefish.ironbackpacks.proxies.CommonProxy;
import gr8pefish.ironbackpacks.registry.GuiButtonRegistry;
import gr8pefish.ironbackpacks.registry.ItemRegistry;
import gr8pefish.ironbackpacks.registry.RecipeRegistry;
import gr8pefish.ironbackpacks.sounds.IronBackpacksSounds;
import gr8pefish.ironbackpacks.util.IronBackpacksConstants;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

import java.io.File;

@Mod(modid = Constants.MODID, name = Constants.MOD_NAME, version = Constants.VERSION, dependencies = Constants.DEPEND)//, guiFactory = Constants.GUIFACTORY)
public class IronBackpacks {

	//Make a custom creative tab with the iron backpack as the logo
	public static final CreativeTabs creativeTab = new CreativeTabs(Constants.MODID) {
		@Override
		public ItemStack getTabIconItem() {
			return new ItemStack(ItemRegistry.ironBackpackStorageEmphasis);
		}
	};

	//The proxies for siding
	@SidedProxy(clientSide = IronBackpacksConstants.General.CLIENTPROXY, serverSide = IronBackpacksConstants.General.COMMONPROXY)
	public static CommonProxy proxy;

	//The instance of this mod
	@Mod.Instance
	public static IronBackpacks instance;

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {

		//register capabilities
		IronBackpacksCapabilities.registerAllCapabilities();

		//compatibility
//		InterModSupport.preinit(); //Nothing yet in 1.8

		//config file
		File config = event.getSuggestedConfigurationFile();
		ConfigHandler.init(config);

		//networking
		NetworkingHandler.initPackets();
		NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GuiHandler());

        //Register buttons
        GuiButtonRegistry.registerButtons(); //need it on server side for inventory stuff (i.e. containerAltGui)

		//Achievements
//		IronBackpacksAchievements.init();

		//Keybindings, Client Event Handler, and Rendering
		proxy.preInit();

	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent event) {

		//compatibility
		InterModSupport.init();

		//event config
		ForgeEventHandler forgeEventHandler = new ForgeEventHandler();
		MinecraftForge.EVENT_BUS.register(forgeEventHandler);
		FMLCommonHandler.instance().bus().register(forgeEventHandler);

		//recipes
//TODO		RecipeRegistry.registerAllRecipes();

		//entity rendering
		proxy.init();

	}

	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event) {

        //compatibility
//		InterModSupport.postinit();

        //holder
        proxy.postInit();

	}

	// Register the commands used for the mod
	@Mod.EventHandler
	public void onServerStart(FMLServerStartingEvent event) {
		event.registerServerCommand(new IronBackpacksCommands());
	}
}
