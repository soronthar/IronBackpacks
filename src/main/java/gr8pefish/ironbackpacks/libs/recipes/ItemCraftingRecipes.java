package gr8pefish.ironbackpacks.libs.recipes;

import gr8pefish.ironbackpacks.api.Constants;
import gr8pefish.ironbackpacks.api.register.ItemICraftingRegistry;
import gr8pefish.ironbackpacks.registry.ItemRegistry;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class ItemCraftingRecipes {

    private static ShapedOreRecipe upgradeCoreRecipe = new ShapedOreRecipe(Constants.RECIPE_GROUP,new ItemStack(ItemRegistry.craftingItem, 1, ItemICraftingRegistry.getIndexOf(ItemRegistry.upgradeCore)),
            "sps",
            "plp",
            "sps",
            'l', "leather",
            's', "string",
            'p', "paper");


    public static void registerItemCraftingRecipes(RegistryEvent.Register<IRecipe> event){
        upgradeCoreRecipe.setRegistryName(Constants.MODID,upgradeCoreRecipe.getRecipeOutput().getUnlocalizedName());
        event.getRegistry().register(upgradeCoreRecipe);
    }
}
