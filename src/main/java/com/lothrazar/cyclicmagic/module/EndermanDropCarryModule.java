package com.lothrazar.cyclicmagic.module;
import com.lothrazar.cyclicmagic.IHasConfig;
import com.lothrazar.cyclicmagic.util.Const;
import com.lothrazar.cyclicmagic.util.UtilEntity;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EndermanDropCarryModule extends BaseEventModule implements IHasConfig {
  private boolean endermanDrop;
  @SubscribeEvent
  public void onLivingDeathEvent(LivingDeathEvent event) {
    if (endermanDrop){
      Entity entity = event.getEntity();
      if (entity instanceof EntityEnderman) {
        EntityEnderman mob = (EntityEnderman) entity;
        IBlockState bs = mob.getHeldBlockState();// mob.func_175489_ck();
        if (bs != null && bs.getBlock() != null && entity.worldObj.isRemote == false) {
          UtilEntity.dropItemStackInWorld(entity.worldObj, mob.getPosition(), bs.getBlock());
        }
      }
    }
  }
  @Override
  public void syncConfig(Configuration config) {
    String category = Const.ConfigCategory.mobs;
    config.addCustomCategoryComment(category, "Changes to vanilla mobs");
    endermanDrop = config.getBoolean("Enderman Block", category, true,
        "Enderman will always drop block they are carrying 100%");
  }
}