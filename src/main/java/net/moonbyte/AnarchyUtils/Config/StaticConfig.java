package net.moonbyte.AnarchyUtils.Config;

import org.bukkit.Location;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class StaticConfig {
    public static boolean usingPAPI = false;
    public static HashMap<String, Location> locations = new HashMap<>();
    public static Set<String> tplocations = new HashSet<>();
}
