package com.africacraft.mob;

import javax.annotation.Nullable;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIFollowParent;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootTableList;

public class EntityGemsbok extends EntityAnimal
{
    public EntityGemsbok(World worldIn)
    {
        super(worldIn);
        this.setSize(0.9F, 1.4F);
    }

    public static void registerFixesCow(DataFixer fixer)
    {
        EntityLiving.registerFixesMob(fixer, "Cow");
    }

    protected void initEntityAI()
    {
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityAIPanic(this, 2.0D));
        this.tasks.addTask(2, new EntityAIMate(this, 1.0D));
        this.tasks.addTask(3, new EntityAITempt(this, 1.25D, Items.WHEAT, false));
        this.tasks.addTask(4, new EntityAIFollowParent(this, 1.25D));
        this.tasks.addTask(5, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        this.tasks.addTask(7, new EntityAILookIdle(this));
    }
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.20000000298023224D);
    }


    /**
     * Returns the volume for the sounds this mob makes.
     */
    protected float getSoundVolume()
    {
        return 0.4F;
    }

    @Nullable
    protected ResourceLocation getLootTable()
    {
        return LootTableList.ENTITIES_COW;
    }

    public boolean processInteract(EntityPlayer player, EnumHand hand, @Nullable ItemStack stack)
    {
        if (stack != null && stack.getItem() == Items.BUCKET && !player.capabilities.isCreativeMode && !this.isChild())
        {
            player.playSound(SoundEvents.ENTITY_COW_MILK, 1.0F, 1.0F);

            if (--stack.stackSize == 0)
            {
                player.setHeldItem(hand, new ItemStack(Items.MILK_BUCKET));
            }
            else if (!player.inventory.addItemStackToInventory(new ItemStack(Items.MILK_BUCKET)))
            {
                player.dropItem(new ItemStack(Items.MILK_BUCKET), false);
            }

            return true;
        }
        else
        {
            return super.processInteract(player, hand, stack);
        }
    }

    public EntityGemsbok createChild(EntityAgeable ageable)
    {
        return new EntityGemsbok(this.worldObj);
    }

    public float getEyeHeight()
    {
        return this.isChild() ? this.height : 1.3F;
    }
}