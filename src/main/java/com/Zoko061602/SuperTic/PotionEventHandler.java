package com.Zoko061602.SuperTic;

import java.util.Random;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import tconstruct.library.tools.ToolCore;

public class PotionEventHandler {

	 private static PotionEventHandler INSTANCE = new PotionEventHandler();
	  	  
	  public static PotionEventHandler getInstance(){
	    return INSTANCE;
	  }
	  

	  
	  
	  public void applyEffects(AttackEntityEvent event){
	    if (((event.target instanceof EntityLivingBase)) && ((event.entityPlayer instanceof EntityPlayerMP))){
	      EntityLivingBase target = (EntityLivingBase)event.target;
	      EntityPlayerMP player = (EntityPlayerMP)event.entityPlayer;
	      
	      ItemStack toolStack = player.getCurrentEquippedItem();
	      
	      if (toolStack == null) {
	        return;
	      }
	      

	      if (((target instanceof EntityPlayer)) && (!player.canAttackPlayer((EntityPlayer)target))){
	        return;
	      }
	      

	      if ((toolStack.getItem() instanceof ToolCore)){
	        ToolCore tool = (ToolCore)toolStack.getItem();
	        
	        if (tool == null) {
	          return;
	        }
	        
	        NBTTagCompound tags = toolStack.getTagCompound();
	        
	        if (tags == null) {
	          return;
	        }
	        
	        int head = tags.getCompoundTag("InfiTool").getInteger("Head");
	        int handle = tags.getCompoundTag("InfiTool").getInteger("Handle");
	        
	        int accessory = -1;
	        int extra = -1;
	        
	        if (tool.getPartAmount() >= 3){
	          accessory = tags.getCompoundTag("InfiTool").getInteger("Accessory");
	        }
	        

	        if (tool.getPartAmount() >= 4){
	          extra = tags.getCompoundTag("InfiTool").getInteger("Extra");
	        }
	      
	        if (Config.eff.containsKey(head)){
	         if(Config.eff.get(head)>0)
	        	addEffect(target, head);
	         else if(Config.eff.get(head)<0)
		        	addEffect(player, head);
	        	
	        }
	        if (Config.eff.containsKey(handle)){
		         if(Config.eff.get(handle)>0)
			        	addEffect(target, handle);
		         else if(Config.eff.get(handle)<0)
			        	addEffect(player, handle);
		        	
		        }
	        if (Config.eff.containsKey(accessory)){
		         if(Config.eff.get(accessory)>0)
			        	addEffect(target, accessory);
		         else if(Config.eff.get(accessory)<0)
		        	 addEffect(player, accessory);
		        	
		        }
	        if (Config.eff.containsKey(extra)){
		         if(Config.eff.get(extra)>0)
		        	 addEffect(target, extra);
		         else if(Config.eff.get(extra)<0)
		        	 addEffect(player, extra);
		        	
		        }
	            
	      }
	    }
	  }
	  
	  private void addEffect(EntityLivingBase e,int id){
		  int r= new Random().nextInt(Config.prob.get(id));
		   if(r==1)
	        	 e.addPotionEffect(new PotionEffect(Config.eff.get(id)*(-1),Config.dur.get(id),Config.amp.get(id)));
	  }
	  
}
