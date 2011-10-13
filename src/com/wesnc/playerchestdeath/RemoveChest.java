package com.wesnc.playerchestdeath;

import java.util.Iterator;
import java.util.LinkedList;

import org.bukkit.Material;
import org.bukkit.block.Block;

import com.griefcraft.model.Protection;

public class RemoveChest implements Runnable {
	
	ChestDeath plugin;
	LinkedList<Block> changeblocks;
	Block chestblock;

	public RemoveChest(ChestDeath plugin, LinkedList<Block> changeblocks, Block chestblock) {
		this.plugin = plugin;
		this.changeblocks = changeblocks;
		this.chestblock = chestblock;
	}
	
	public void run()
	{
		//A little fix to fix the pop off signs...
		Iterator<Block> rblocks = changeblocks.descendingIterator();
		while(rblocks.hasNext()) {
			Block tblock = rblocks.next();
			if(plugin.nodropblocks.contains(tblock)) {
				tblock.setType(Material.AIR);
				plugin.nodropblocks.remove(tblock);
			}
		}
		if(chestblock != null && plugin.lwc != null) {
			Protection protection = plugin.lwc.findProtection(chestblock); 
			if(protection != null) {
				protection.remove();
			}
		}
	}
	
}
