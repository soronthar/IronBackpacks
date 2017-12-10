package gr8pefish.ironbackpacks.integration.jei.increaseTier;

import gr8pefish.ironbackpacks.util.TextUtils;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.ICraftingGridHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeCategory;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class IncreaseTierRecipeCategory { //TODO implements IRecipeCategory {

//    private final String title;
//    private final IDrawable background;
//    private final IDrawable icon;
//
//    private final ICraftingGridHelper craftingGridHelper;
//
//    public IncreaseTierRecipeCategory(IGuiHelper guiHelper) {
//        title = TextUtils.localize("jei.ironbackpacks.increaseTierRecipe.name");
//        ResourceLocation RL = new ResourceLocation("ironbackpacks", "textures/jei/craftingGridRecipeJEI.png");
//        background = guiHelper.createDrawable(RL, 0, 0, 166, 130);
//        icon = guiHelper.createDrawable(RL, 168, 0, 16, 16);
//        craftingGridHelper = guiHelper.createCraftingGridHelper(1, 0);
//    }
//
//    @Nonnull
//    @Override
//    public String getUid() {
//        return "ironbackpacks.increaseTier";
//    }
//
//    @Nonnull
//    @Override
//    public String getTitle() {
//        return title;
//    }
//
//    @Nonnull
//    @Override
//    public IDrawable getBackground() {
//        return background;
//    }
//
//    @Nullable
//    @Override
//    public IDrawable getIcon() {
//        return icon;
//    }
//
//    @Override
//    public void drawExtras(@Nonnull Minecraft minecraft) {
//
//    }
//
//    @Override
//    public void drawAnimations(@Nonnull Minecraft minecraft) {
//
//    }
//
//    @Override
//    public void setRecipe(@Nonnull IRecipeLayout recipeLayout, @Nonnull IRecipeWrapper recipeWrapper) {
//        //deprecated
//    }
//
//    @Override
//    public void setRecipe(@Nonnull IRecipeLayout recipeLayout, @Nonnull IRecipeWrapper recipeWrapper, @Nonnull IIngredients ingredients) {
//        //get the items to display
//        IGuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();
//
//        //init the output slot
//        guiItemStacks.init(0, false, 118, 29);
//
//        //init the input slots
//        int craftInputSlot1 = 1;
//        for (int y = 0; y < 3; ++y) {
//            for (int x = 0; x < 3; ++x) {
//                int index = craftInputSlot1 + x + (y * 3);
//                guiItemStacks.init(index, true, (x * 18) + 23, (y * 18) + 11);
//            }
//        }
//
//        //set the slots with the correct items
//        craftingGridHelper.setInput(guiItemStacks, recipeWrapper.getInputs());
//        craftingGridHelper.setOutput(guiItemStacks, recipeWrapper.getOutputs());
//    }
}
