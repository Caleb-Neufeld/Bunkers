package com.sniper.bunkers.teams.claims;

import java.util.ArrayList;

import org.bukkit.Location;

import com.sniper.bunkers.Core;
import com.sniper.bunkers.objects.Claim;
import com.sniper.bunkers.objects.Team;

public class ClaimsManager {
	
	public static ArrayList<Claim> registeredClaims = new ArrayList<>();
	
	public static void registerClaims() {
		for(String string : Core.getInstance().getConfig().getStringList("Claims")) {
			String[] format = string.split(":");
			String team = format[0], xS = format[1], zS = format[2];
			int xF = Integer.valueOf(xS), zF = Integer.valueOf(zS);
			//registeredClaims.add(new Claim(Team.valueOf(team), x, z));
		}
	}
	
	public static Team getOwnerAt(Location location) {
		int x = (int)location.getX();
		//int y = (int)location.getY();
		int z = (int)location.getZ();
		for(Claim claim : registeredClaims) {
			if(location.getX() >= claim.getPos1().x() && location.getX() <= claim.getPos2().x() && location.getZ() >= claim.getPos1().z() && location.getZ() <= claim.getPos2().z()) {
				return claim.getOwner();
			}
		}
		return null;
	}
	

}
