package moze_intel.projecte.gameObjs.items.armor;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import moze_intel.projecte.gameObjs.ObjHandler;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraftforge.common.ISpecialArmor;

import java.util.Locale;

public abstract class AbstractPEArmor extends ItemArmor implements ISpecialArmor
{
	protected final EnumArmorType armorPiece;
	private final String iconPrefix;
	private final String texturePrefix;
	private final int explosionAbsorb;
	private final int fallAbsorb;
	private final int lightAbsorb;
	private final int heavyAbsorb;

	protected AbstractPEArmor(EnumArmorType armorPiece, String unlocalizedName, String iconPrefix, String texturePrefix,
			int explosionAbsorb, int fallAbsorb, int lightAbsorb, int heavyAbsorb)
	{
		super(ArmorMaterial.DIAMOND, 0, armorPiece.ordinal());
		this.setCreativeTab(ObjHandler.cTab);
		this.setUnlocalizedName(unlocalizedName);
		this.setHasSubtypes(false);
		this.setMaxDamage(0);
		this.armorPiece = armorPiece;
		this.iconPrefix = iconPrefix;
		this.texturePrefix = texturePrefix;
		this.explosionAbsorb = explosionAbsorb;
		this.fallAbsorb = fallAbsorb;
		this.lightAbsorb = lightAbsorb;
		this.heavyAbsorb = heavyAbsorb;
	}

	@Override
	public ArmorProperties getProperties(EntityLivingBase player, ItemStack armor, DamageSource source, double damage, int slot)
	{
		EnumArmorType type = ((AbstractPEArmor) armor.getItem()).armorPiece;
		if (source.isExplosion())
		{
			return new ArmorProperties(1, 1.0D, explosionAbsorb);
		}

		if (type == EnumArmorType.HEAD && source == DamageSource.fall)
		{
			return new ArmorProperties(1, 1.0D, fallAbsorb);
		}

		if (type == EnumArmorType.HEAD || type == EnumArmorType.FEET)
		{
			return new ArmorProperties(0, 0.2D, lightAbsorb);
		}

		return new ArmorProperties(0, 0.3D, heavyAbsorb);
	}

	@Override
	public int getArmorDisplay(EntityPlayer player, ItemStack armor, int slot)
	{
		EnumArmorType type = ((AbstractPEArmor) armor.getItem()).armorPiece;
		return (type == EnumArmorType.HEAD || type == EnumArmorType.FEET) ? 4 : 6;
	}

	@Override
	public void damageArmor(EntityLivingBase entity, ItemStack stack, DamageSource source, int damage, int slot) {}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister par1IconRegister)
	{
		String type = this.armorPiece.name.toLowerCase(Locale.ROOT);

		this.itemIcon = par1IconRegister.registerIcon("projecte:" + iconPrefix + "/" + type);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
	{
		char index = this.armorPiece == EnumArmorType.LEGS ? '2' : '1';
		return "projecte:textures/armor/" + texturePrefix + index + ".png";
	}
}
