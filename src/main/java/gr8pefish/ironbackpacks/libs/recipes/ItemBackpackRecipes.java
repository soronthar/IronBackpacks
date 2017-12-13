package gr8pefish.ironbackpacks.libs.recipes;

import gr8pefish.ironbackpacks.api.Constants;
import gr8pefish.ironbackpacks.api.items.backpacks.interfaces.IBackpack;
import gr8pefish.ironbackpacks.api.register.ItemIBackpackRegistry;
import gr8pefish.ironbackpacks.registry.ItemRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;


public class ItemBackpackRecipes {
    public static ShapedOreRecipe basicBackpackRecipe = new ShapedOreRecipe(Constants.RECIPE_GROUP, new ItemStack(ItemRegistry.basicBackpack),
            "wlw",
            "lcl",
            "wlw",
            'l', "leather", 'c', "chestWood", 'w', Blocks.WOOL);

    public static void registerItemBackpackRecipes(RegistryEvent.Register<IRecipe> event){
        for (int i = 0; i < ItemIBackpackRegistry.getSize(); i++){
            IBackpack backpack = ItemIBackpackRegistry.getBackpackAtIndex(i);
            if (backpack != null) {
                IRecipe itemRecipe = backpack.getItemRecipe(null);
                if(itemRecipe != null) {
                    itemRecipe.setRegistryName(new ResourceLocation(Constants.MODID,itemRecipe.getRecipeOutput().getUnlocalizedName()));
                    event.getRegistry().register(itemRecipe); //hardcoded to ItemBackpack
                }
            }
        }
    }
}
