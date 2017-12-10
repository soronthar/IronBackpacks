package gr8pefish.ironbackpacks.sounds;

import gr8pefish.ironbackpacks.api.Constants;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = Constants.MODID)
public class IronBackpacksSounds {

    public static SoundEvent open_backpack;
    public static SoundEvent close_backpack;

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<SoundEvent> event) {
        //open the backpack sound
        ResourceLocation open_sound = new ResourceLocation(Constants.MODID, "open_backpack");
        event.getRegistry().register(new SoundEvent(open_sound).setRegistryName(open_sound));

        //close the backpack sound
        ResourceLocation close_sound = new ResourceLocation(Constants.MODID, "close_backpack");
        event.getRegistry().register(new SoundEvent(close_sound).setRegistryName(close_sound));
    }
}