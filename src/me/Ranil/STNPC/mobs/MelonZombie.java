package me.Ranil.STNPC.mobs;

import java.lang.reflect.Field;
import java.util.Random;

import net.minecraft.server.v1_7_R1.Blocks;
import net.minecraft.server.v1_7_R1.EntityHuman;
import net.minecraft.server.v1_7_R1.EntityVillager;
import net.minecraft.server.v1_7_R1.EntityZombie;
import net.minecraft.server.v1_7_R1.ItemStack;
import net.minecraft.server.v1_7_R1.Items;
import net.minecraft.server.v1_7_R1.PathfinderGoalBreakDoor;
import net.minecraft.server.v1_7_R1.PathfinderGoalFloat;
import net.minecraft.server.v1_7_R1.PathfinderGoalHurtByTarget;
import net.minecraft.server.v1_7_R1.PathfinderGoalLookAtPlayer;
import net.minecraft.server.v1_7_R1.PathfinderGoalMeleeAttack;
import net.minecraft.server.v1_7_R1.PathfinderGoalMoveThroughVillage;
import net.minecraft.server.v1_7_R1.PathfinderGoalMoveTowardsRestriction;
import net.minecraft.server.v1_7_R1.PathfinderGoalNearestAttackableTarget;
import net.minecraft.server.v1_7_R1.PathfinderGoalRandomLookaround;
import net.minecraft.server.v1_7_R1.PathfinderGoalRandomStroll;
import net.minecraft.server.v1_7_R1.World;

import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_7_R1.util.UnsafeList;

public class MelonZombie extends EntityZombie {

	@SuppressWarnings("unchecked")
	public MelonZombie(World world) {
		super(world);
		Random lev = new Random();
		int level = lev.nextInt(9) + 10;
		this.setCustomName("Bleany Baby " + ChatColor.GRAY + "LVL " + level);
		this.setCustomNameVisible(true);
		this.setVillager(true);
		this.setBaby(true);
		this.setEquipment(0, new ItemStack(Items.DIAMOND_SWORD));
		this.setEquipment(4, new ItemStack(Blocks.MELON));
		// this.addEffect(new MobEffect(MobEffectList.HARM.id,
		// Integer.MAX_VALUE));

	}

}
