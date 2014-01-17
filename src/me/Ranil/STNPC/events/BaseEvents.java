package me.Ranil.STNPC.events;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import me.Ranil.STNPC.STNPCBase;
import me.Ranil.STNPC.enums.CustomEntityType;
import net.minecraft.server.v1_7_R1.EntityInsentient;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_7_R1.CraftWorld;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class BaseEvents implements Listener {

	private STNPCBase plugin;

	public BaseEvents(STNPCBase instance) {
		this.plugin = instance;
	}

	@EventHandler
	public void onInteract(PlayerInteractEvent event) {

		Player player = event.getPlayer();
		Location loc = player.getLocation();
		CraftWorld world = (CraftWorld) loc.getWorld();

		if (event.getAction() == Action.RIGHT_CLICK_AIR
				&& player.getItemInHand().getType() == Material.STICK) {
			try {
				spawnCustomEntity(CustomEntityType.NOVICEZOMBWARR, loc,
						world.getHandle());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	@EventHandler
	public void onCreatureSpawn(CreatureSpawnEvent e) {
		if (e.getSpawnReason() == SpawnReason.NATURAL) {
			e.setCancelled(true);
			/*Bukkit.getScheduler().scheduleSyncDelayedTask(plugin,
					new Runnable() {
						public void run() {
							if (plugin.config.contains("10")) {
								List<String> id = plugin.config
										.getStringList("'10'");
								for (String string : id) {
									Bukkit.broadcastMessage(string);
								}
							} else {
							
							}
						}
					}, 60L);*/
		}
	}

	@EventHandler
	public void onEntityDeath(EntityDeathEvent e) {
		Player player = e.getEntity().getKiller();
		if (!(e.getEntity() instanceof Player)) {
			e.getDrops().clear();
			e.getEntity().getEquipment().setBootsDropChance(0);
			e.getEntity().getEquipment().setHelmetDropChance(0);
			e.getEntity().getEquipment().setChestplateDropChance(0);
			e.getEntity().getEquipment().setLeggingsDropChance(0);
			e.setDroppedExp(0);
		}
		Random intel = new Random();
		Random dex = new Random();
		Random str = new Random();
		Random vit = new Random();
		Random chance = new Random();
		Random type = new Random();
		if (e.getEntity().getCustomName() != null) {
			if (e.getEntity().getCustomName().contains("Novice")) {
				int probOne = chance.nextInt(30);
				player.sendMessage("You got a " + probOne);
				if (probOne == 0) {
					player.sendMessage("You should be getting a drop");
					if (e.getEntity().getCustomName().contains("Warrior")) {
						int intelligence = intel.nextInt(6) + 1;
						int dexterity = dex.nextInt(9) + 1;
						int strength = str.nextInt(20) + 11;
						int vitality = vit.nextInt(10) + 1;
						int kind = type.nextInt(4);
						if (kind == 0) {
							ItemStack drop = createItem("Weak Warrior Helmet",
									Material.LEATHER_HELMET, ChatColor.AQUA
											+ "Int: " + intelligence,
									ChatColor.GREEN + "Dex: " + dexterity,
									ChatColor.RED + "Str: " + strength,
									ChatColor.LIGHT_PURPLE + "Vit: " + vitality);
							e.getEntity()
									.getWorld()
									.dropItemNaturally(
											e.getEntity().getLocation(), drop);
						} else if (kind == 1) {
							ItemStack drop1 = createItem(
									"Weak Warrior Chestplate",
									Material.LEATHER_CHESTPLATE, ChatColor.AQUA
											+ "Int: " + intelligence,
									ChatColor.GREEN + "Dex: " + dexterity,
									ChatColor.RED + "Str: " + strength,
									ChatColor.LIGHT_PURPLE + "Vit: " + vitality);
							e.getEntity()
									.getWorld()
									.dropItemNaturally(
											e.getEntity().getLocation(), drop1);
						} else if (kind == 2) {
							ItemStack drop2 = createItem(
									"Weak Warrior Leggings",
									Material.LEATHER_LEGGINGS, ChatColor.AQUA
											+ "Int: " + intelligence,
									ChatColor.GREEN + "Dex: " + dexterity,
									ChatColor.RED + "Str: " + strength,
									ChatColor.LIGHT_PURPLE + "Vit: " + vitality);
							e.getEntity()
									.getWorld()
									.dropItemNaturally(
											e.getEntity().getLocation(), drop2);
						} else {
							ItemStack drop3 = createItem("Weak Warrior Boots",
									Material.LEATHER_BOOTS, ChatColor.AQUA
											+ "Int: " + intelligence,
									ChatColor.GREEN + "Dex: " + dexterity,
									ChatColor.RED + "Str: " + strength,
									ChatColor.LIGHT_PURPLE + "Vit: " + vitality);
							e.getEntity()
									.getWorld()
									.dropItemNaturally(
											e.getEntity().getLocation(), drop3);
						}
						// int dex str vit

					}
				}
			}
		}
	}

	private ItemStack createItem(String name, Material item, String lore,
			String lore2, String lore3, String lore4) {
		ItemStack i = new ItemStack(item);
		ItemMeta im = i.getItemMeta();
		im.setDisplayName(name);
		List<String> loreLines = new ArrayList<String>();
		loreLines.add(lore);
		loreLines.add(lore2);
		loreLines.add(lore3);
		loreLines.add(lore4);
		im.setLore(loreLines);
		i.setItemMeta(im);
		return i;
	}

	public void spawnCustomEntity(CustomEntityType entity, Location loc,
			net.minecraft.server.v1_7_R1.World world)
			throws InstantiationException, IllegalAccessException {

		EntityInsentient ei;
		try {
			ei = (EntityInsentient) (entity.getCustomClass()
					.getDeclaredConstructor(
							net.minecraft.server.v1_7_R1.World.class)
					.newInstance(world));
			ei.setPosition(loc.getX(), loc.getY(), loc.getZ());

			world.addEntity(ei, SpawnReason.CUSTOM);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public Location pullLoc(String id, World world) {
		Double x = plugin.config.getDouble(id + ".x");
		Double y = plugin.config.getDouble(id + ".y");
		Double z = plugin.config.getDouble(id + ".z");
		Location loc = new Location(world, x, y, z);
		return loc;
	}

}