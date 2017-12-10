package gr8pefish.ironbackpacks.network.server;

import gr8pefish.ironbackpacks.capabilities.player.PlayerWearingBackpackCapabilities;
import gr8pefish.ironbackpacks.container.alternateGui.ContainerAlternateGui;
import gr8pefish.ironbackpacks.container.backpack.ContainerBackpack;
import gr8pefish.ironbackpacks.items.backpacks.ItemBackpack;
import gr8pefish.ironbackpacks.network.NetworkingHandler;
import gr8pefish.ironbackpacks.network.client.ClientCurrentPackMessage;
import gr8pefish.ironbackpacks.util.IronBackpacksConstants;
import gr8pefish.ironbackpacks.util.Logger;
import gr8pefish.ironbackpacks.util.NBTUtils;
import gr8pefish.ironbackpacks.util.helpers.IronBackpacksHelper;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

/**
 * A message that contains a single byte as the data sent
 */
public class SingleByteMessage implements IMessage {

    //the data sent
    private byte action;

    public SingleByteMessage() {} //default constructor is necessary

    public SingleByteMessage(byte action) {
        this.action = action;
    }

    @Override
    public void fromBytes(ByteBuf buf){
        action = (byte) ByteBufUtils.readVarShort(buf);
    }

    @Override
    public void toBytes(ByteBuf buf){
        ByteBufUtils.writeVarShort(buf, action);
    }

    public static class Handler implements IMessageHandler<SingleByteMessage, IMessage> {

        @Override
        public IMessage onMessage(SingleByteMessage message, MessageContext ctx) {

            ContainerBackpack container;
            ContainerAlternateGui altContainer;
            EntityPlayer player;

            switch (message.action) {
                case IronBackpacksConstants.Messages.SingleByte.BACKPACK_TO_INVENTORY:
                    container = (ContainerBackpack) ctx.getServerHandler().player.openContainer;
                    container.backpackToInventory();
                    break;
                case IronBackpacksConstants.Messages.SingleByte.INVENTORY_TO_BACKPACK:
                    container = (ContainerBackpack) ctx.getServerHandler().player.openContainer;
                    container.inventoryToBackpack();
                    break;
                case IronBackpacksConstants.Messages.SingleByte.HOTBAR_TO_BACKPACK:
                    container = (ContainerBackpack) ctx.getServerHandler().player.openContainer;
                    container.hotbarToBackpack();
                    break;
                case IronBackpacksConstants.Messages.SingleByte.SORT_BACKPACK:
                    container = (ContainerBackpack) ctx.getServerHandler().player.openContainer;
                    container.sort();
                    break;
                case IronBackpacksConstants.Messages.SingleByte.TOGGLE_SORT_BUTTON:
                    container = (ContainerBackpack) ctx.getServerHandler().player.openContainer;
                    container.getInventoryBackpack().toggleSortType();
                    break;
                case IronBackpacksConstants.Messages.SingleByte.MOVE_LEFT:
                    altContainer = (ContainerAlternateGui) ctx.getServerHandler().player.openContainer;
                    altContainer.changeAdvFilterSlots(IronBackpacksConstants.Miscellaneous.MOVE_LEFT);
                    break;
                case IronBackpacksConstants.Messages.SingleByte.MOVE_RIGHT:
                    altContainer = (ContainerAlternateGui) ctx.getServerHandler().player.openContainer;
                    altContainer.changeAdvFilterSlots(IronBackpacksConstants.Miscellaneous.MOVE_RIGHT);
                    break;
                case IronBackpacksConstants.Messages.SingleByte.CLEAR_ROW_1:
                    altContainer = (ContainerAlternateGui) ctx.getServerHandler().player.openContainer;
                    altContainer.removeSlotsInRow(1);
                    break;
                case IronBackpacksConstants.Messages.SingleByte.CLEAR_ROW_2:
                    altContainer = (ContainerAlternateGui) ctx.getServerHandler().player.openContainer;
                    altContainer.removeSlotsInRow(2);
                    break;
                case IronBackpacksConstants.Messages.SingleByte.CLEAR_ROW_3:
                    altContainer = (ContainerAlternateGui) ctx.getServerHandler().player.openContainer;
                    altContainer.removeSlotsInRow(3);
                    break;
                case IronBackpacksConstants.Messages.SingleByte.EQUIP_BACKPACK_KEYBINDING:
                    IronBackpacksHelper.equipBackpackFromKeybinding(ctx.getServerHandler().player);
                    break;
                case IronBackpacksConstants.Messages.SingleByte.OPEN_BACKPACK_KEYBINDING:
                    player = ctx.getServerHandler().player;
                    ItemStack backpackStack = PlayerWearingBackpackCapabilities.getEquippedBackpack(player);
                    if (!backpackStack.isEmpty()) {
                        NBTUtils.setUUID(backpackStack);
                        PlayerWearingBackpackCapabilities.setCurrentBackpack(player, backpackStack);
                        NetworkingHandler.network.sendTo(new ClientCurrentPackMessage(backpackStack), (EntityPlayerMP)player);
                        ItemBackpack backpackItem = (ItemBackpack) backpackStack.getItem();
                        backpackItem.handleBackpackOpening(backpackStack,player.world, player, EnumHand.MAIN_HAND,false);
                    }
                    break;
                default:
                    Logger.error("Error in sending message for Iron Backpacks in SingleByteMessage");
                    break;
            }
            return null; //no return message necessary
        }

    }
}
