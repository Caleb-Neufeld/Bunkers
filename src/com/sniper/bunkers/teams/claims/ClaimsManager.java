package com.sniper.bunkers.teams.claims;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;

import com.sniper.bunkers.Core;
import com.sniper.bunkers.objects.Claim;
import com.sniper.bunkers.objects.Coordinants;
import com.sniper.bunkers.objects.Team;

public class ClaimsManager {
	
	public static ArrayList<Claim> registeredClaims = new ArrayList<>();
	
	public static void registerClaims() {
		for(String string : Core.getInstance().getConfig().getStringList("Claims")) {
			String[] format = string.split(":");
			String team = format[0], xSA = format[1], zSA = format[2], xSB = format[3], zSB = format[4];
			Coordinants x = new Coordinants(Integer.valueOf(xSA), Integer.valueOf(zSA)), z =new Coordinants(Integer.valueOf(xSB), Integer.valueOf(zSB)) ;
			registeredClaims.add(new Claim(Team.valueOf(team), x, z));
		}
	}
	
	public static Team getOwnerAt(Location location) {
		for(Claim claim : registeredClaims) {
			if(location.getX() >= claim.getPos1().x() && location.getX() <= claim.getPos2().x() && location.getZ() >= claim.getPos1().z() && location.getZ() <= claim.getPos2().z()) {
				return claim.getOwner();
			}
		}
		return Team.WILD;
	}
	
	public static void createClaim(Team team, Coordinants x, Coordinants y) {
		List<String> claims = Core.getInstance().getConfig().getStringList("Claims");
		claims.add(team.getName() + ":" + x.x() + ":" + x.z() + ":" + y.x() + ":" + y.z());
		Core.getInstance().getConfig().set("Claims", claims);
		Core.getInstance().saveConfig();
	}
	

}
