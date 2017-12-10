package gr8pefish.ironbackpacks.client.model;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;

import java.util.ArrayList;

public class ModelBackpack extends ModelBase {

    private ArrayList<ModelRenderer> parts;

    public ModelBackpack() {
        textureWidth = 32;
        textureHeight = 32;

        parts = new ArrayList<>();

        ModelRenderer main = new ModelRenderer(this, 0, 0);
        main.addBox(-2, -3, -1, 4, 6, 2);
        main.setRotationPoint(0, 0, 0);
        parts.add(main);

        ModelRenderer bottomPouch = new ModelRenderer(this, 0, 8);
        bottomPouch.addBox(-1.5F, -1, -0.5F, 3, 2, 1);
        bottomPouch.setRotationPoint(0, 1.5F, -1.5F);
        parts.add(bottomPouch);

        ModelRenderer bottomPouchAddition = new ModelRenderer(this, 0, 11);
        bottomPouchAddition.addBox(-1.5F, -1F, -0.5F, 3, 2, 1);
        bottomPouchAddition.setRotationPoint(0, .5F, -1F);
        parts.add(bottomPouchAddition);

    }

    @Override
    public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        //ToDo: test these Gl calls
        GlStateManager.disableRescaleNormal();
        GlStateManager.disableCull();
        for (ModelRenderer part : parts) {
            part.render(scale);
        }
    }

    /**
     * Set all the details of the backpack to render.
     */
    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn){

        super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);

        // update the rotation to sit parallel to the player's back
        EntityPlayer player = (EntityPlayer) entityIn;
        final float rotation = interpolateRotation(player.prevRotationYaw, player.rotationYaw);

        if (Minecraft.getMinecraft().currentScreen instanceof GuiInventory) //the main inventory screen that renders the player
            GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F); //rotates it to the back of the player, and at the correct angle
        else //in-world
            GlStateManager.rotate(180.0F - rotation, 0.0F, 1.0F, 0.0F); //add rotation depending on movement
        GlStateManager.scale(1.6f, 1.4f, 1.6f); //scale slightly larger
        GlStateManager.translate(0, .225, -.14); //move it into the correct location

        if (!player.inventory.armorInventory.get(2).isEmpty()) //if has chest armor slot
            if (player.inventory.armorInventory.get(2).getItem() == Items.ELYTRA) //if elytra
                GlStateManager.translate(0, 0, .0175); //move it forwards a little
            else
                GlStateManager.translate(0, 0, -.04); //move it backwards a little

        if (player.isSneaking()){
            GlStateManager.rotate(-25F, 1, 0, 0); //rotate it forward to still be on the player's back
            GlStateManager.translate(0, .075, -.05); //move it down and back a little
        }

    }

    /**
     * Get the correct rotation of the player, so that the backpack can be parallel to the player's back.
     * @param prevRotation - initial rotation (yaw)
     * @param nextRotation - next rotation (yaw)
     * @return - angle to rotate the backpack to
     */
    private static float interpolateRotation(float prevRotation, float nextRotation) {
        float rotation = nextRotation - prevRotation;
        return prevRotation * rotation;
    }

}
