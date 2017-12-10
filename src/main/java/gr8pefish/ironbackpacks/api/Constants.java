package gr8pefish.ironbackpacks.api;

public class Constants {

	public static final String MOD_NAME = "Iron Backpacks";
	public static final String MODID = "ironbackpacks";
	public static final String WEARING_BACKPACK_CAPABILITY_STRING = "WEARING_BACKPACK_CAP"; //For typical usage, equipped and current packs
	public static final String DEATH_BACKPACK_CAPABILITY_STRING = "DEATH_BACKPACK_CAP"; //For backpacks persisting through death
	public static final String DOMAIN = MODID + ":"; //For resources
	public static final String VERSION = "@VERSION@";
	public static final String DEPEND = "required-after:forge@[14.23.0.2491,); after:JEI;"; //ToDo: Move Forge, caps, and version out. keep modid, mod name, and domain.
}
