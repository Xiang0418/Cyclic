package com.lothrazar.cyclicmagic.event;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.PlayerDropsEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import java.util.Iterator;
import com.lothrazar.cyclicmagic.item.ItemInventoryStorage;
import com.lothrazar.cyclicmagic.util.UtilChat;

public class EventPlayerDeathCoords {

	@SubscribeEvent
	public void onLivingDeathEvent(LivingDeathEvent event) {

		Entity entity = event.getEntity();

		if (entity instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) entity;

			// TODO: could restrict to certain damage soruces
			// event.getSource() == DamageSource.
			// ItemStack skull = UtilNBT.buildNamedPlayerSkull(player);

			// UtilEntity.dropItemStackInWorld(entity.worldObj, player.getPosition(),
			// skull);

			String coordsStr = UtilChat.blockPosToString(player.getPosition());
			UtilChat.addChatMessage(player, player.getDisplayNameString() + " " + UtilChat.lang("player.death.location") + " " + coordsStr);

		}
	}
/*
	@SubscribeEvent
	public void onPlayerDropsEvent(PlayerDropsEvent event) {

		for (EntityItem item : event.getDrops()) {

			if (item.getEntityItem() != null && item.getEntityItem().getItem() instanceof ItemInventoryStorage) {

			}
		}

	}
*/
	
	/*
	@SubscribeEvent
	public void onPlayerClone(PlayerEvent.Clone event) {

		EntityPlayer dying = event.getOriginal();

		EntityPlayer spawned = event.getEntityPlayer();

		// for(ItemStack s : dying.inventory.mainInventory){
		for (int i = 0; i < dying.inventory.mainInventory.length; i++) {
			ItemStack s = dying.inventory.mainInventory[i];

			if (s != null && s.getItem() instanceof ItemInventoryStorage) {

				if (spawned.inventory.addItemStackToInventory(s)) {
 
					dying.inventory.mainInventory[i] = null;
				}
			}
		}
	}
	*/
}
