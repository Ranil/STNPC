package me.Ranil.STNPC.mobs;

import java.util.Random;

import net.minecraft.server.v1_7_R1.EntityZombie;
import net.minecraft.server.v1_7_R1.ItemStack;
import net.minecraft.server.v1_7_R1.Items;
import net.minecraft.server.v1_7_R1.World;

import org.bukkit.ChatColor;

public class NoviceZombieWarrior extends EntityZombie{

	
	public NoviceZombieWarrior(World world) {
		super(world);
		Random lev = new Random();
		int level = lev.nextInt(9);
		this.setCustomName("Novice Zombie Warrior " + ChatColor.GRAY + "LVL " + level);
		this.setCustomNameVisible(true);
		this.setEquipment(4, new ItemStack(Items.LEATHER_HELMET));
		this.setEquipment(3, new ItemStack(Items.LEATHER_CHESTPLATE));
		this.setEquipment(2, new ItemStack(Items.LEATHER_LEGGINGS));
		this.setEquipment(1, new ItemStack(Items.LEATHER_BOOTS));
	}

}
