package TA.Utils;

import java.util.Collection;
import java.util.EnumSet;

import org.lwjgl.input.Keyboard;

import DummyCore.Utils.DataStorage;
import DummyCore.Utils.DummyData;
import TA.API.IArtifact;
import TA.Common.Inventory.ContainerArtifacts;
import TA.Common.Inventory.InventoryArtifacts;
import TA.Network.TAPacketHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.ai.attributes.AttributeInstance;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;
import cpw.mods.fml.common.network.PacketDispatcher;

public class TAPlayerTickHandler implements ITickHandler{

	public static boolean isKeyPressed = false;
	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData) {
	}

	@Override
	public void tickEnd(EnumSet<TickType> type, Object... tickData) {
		MinecraftServer mc = MinecraftServer.getServer();
		String[] usernames = mc.getAllUsernames();
		for(int i = 0; i < usernames.length; ++i)
		{
			EntityPlayer player = mc.getConfigurationManager().getPlayerForUsername(usernames[i]);
			AttributeInstance ainst = player.getAttributeMap().getAttributeInstanceByName("generic.movementSpeed");
			Collection coll = ainst.func_111122_c();
			InventoryArtifacts iarts = (InventoryArtifacts) TAUtils.playerInvTable.get(player.username);
			String[] last5 = new String[5];
			for(int i1 = 0; i1 < iarts.getSizeInventory(); ++i1)
			{
				ItemStack stack = iarts.getStackInSlot(i1);
				if(stack != null)
				{
					Item item = stack.getItem();
					if(item instanceof IArtifact)
					{
						last5[i1] = ((IArtifact)item).getSpeedModifierName(stack);
					}
				}
			}
			for(int j = 0; j < coll.size(); ++j)
			{
				if(coll.toArray()[j] instanceof AttributeModifier)
				{
					AttributeModifier mod = (AttributeModifier) coll.toArray()[j];
					String name = mod.getName();
					if(name.equals("movementSpeedTerraArts"))
					{
						String sub = mod.getID().toString().substring(mod.getID().toString().length()-5, mod.getID().toString().length());
						if(last5[0]!=null && last5[0].equals(sub))
						{
							
						}else
						{
							if(last5[1]!=null && last5[1].equals(sub))
							{
								
							}else
							{
								if(last5[2]!=null && last5[2].equals(sub))
								{
									
								}else
								{
									if(last5[3]!=null && last5[3].equals(sub))
									{
										
									}else
									{
										if(last5[4]!=null && last5[4].equals(sub))
										{
											
										}else
										{
											TAUtils.applySpeedModifier(player, sub, 1F, true);
										}
									}
								}
							}
						}
					}
				}
			}
			AttributeInstance ainst1 = player.getAttributeMap().getAttributeInstanceByName("generic.knockbackResistance");
			Collection coll1 = ainst1.func_111122_c();
			for(int j = 0; j < coll1.size(); ++j)
			{
				if(coll1.toArray()[j] instanceof AttributeModifier)
				{
					AttributeModifier mod = (AttributeModifier) coll1.toArray()[j];
					String name = mod.getName();
					if(name.equals("generic.knockbackResistance"))
					{
						String sub = mod.getID().toString().substring(mod.getID().toString().length()-5, mod.getID().toString().length());
						if(last5[0]!=null && last5[0].equals(sub))
						{
							
						}else
						{
							if(last5[1]!=null && last5[1].equals(sub))
							{
								
							}else
							{
								if(last5[2]!=null && last5[2].equals(sub))
								{
									
								}else
								{
									if(last5[3]!=null && last5[3].equals(sub))
									{
										
									}else
									{
										if(last5[4]!=null && last5[4].equals(sub))
										{
											
										}else
										{
											TAUtils.applyKnokbackModifier(player, sub, 1F, true);
										}
									}
								}
							}
						}
					}
				}
			}
			for(int i1 = 0; i1 < iarts.getSizeInventory(); ++i1)
			{
				ItemStack stack = iarts.getStackInSlot(i1);
				if(stack != null)
				{
					Item item = stack.getItem();
					if(item instanceof IArtifact)
					{
						((IArtifact)item).onArtUpdate(stack, player);
					}
				}
			}
		}
	}

	@Override
	public EnumSet<TickType> ticks() {
		return EnumSet.of(TickType.SERVER);
	}

	@Override
	public String getLabel() {
		return "terraarts";
	}

}
