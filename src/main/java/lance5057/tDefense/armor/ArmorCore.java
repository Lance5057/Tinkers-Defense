package lance5057.tDefense.armor;

import org.lwjgl.opengl.GL11;

import lance5057.tDefense.TDIntegration;
import lance5057.tDefense.TinkersDefense;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.ISpecialArmor;
import tconstruct.library.tools.AbilityHelper;
import tconstruct.library.tools.ToolCore;
import thaumcraft.api.IRunicArmor;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.Optional;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@Optional.InterfaceList({@Optional.Interface(modid = "Thaumcraft", iface = "thaumcraft.api.IRunicArmor", striprefs = true)})
public class ArmorCore extends ToolCore implements ISpecialArmor, IRunicArmor
{
	int				slot;
	public float	reductionPercent	= 0f;
	protected int	maxReduction		= 100;

	//Thaumcraft
	boolean			Charge				= false;

	public ArmorCore(int baseProtection, int slot)
	{
		super(baseProtection);

		this.slot = slot;
	}

	@Override
	public ArmorProperties getProperties(EntityLivingBase player, ItemStack armor, DamageSource source, double damage, int slot)
	{
		ArmorProperties armorp;
		if(!source.isUnblockable())
		{
			armorp = new ArmorProperties(0, reductionPercent, maxReduction); // 0.04
																				// per
																				// half
																				// shirt
		}
		else
			armorp = new ArmorProperties(0, 0, 0);

		return armorp;
	}

	@Override
	public int getArmorDisplay(EntityPlayer player, ItemStack armor, int slot)
	{
		return (int) ((int) reductionPercent / 0.04);
	}

	public int getSlot()
	{
		return slot;
	}

	@SideOnly(Side.CLIENT)
	public ModelBiped getModel(String[] color, NBTTagCompound tags)
	{
		return null;
	}

	@Override
	public boolean isValidArmor(ItemStack stack, int armorType, Entity entity)
	{
		return armorType == slot;
	}

	@Override
	public void damageArmor(EntityLivingBase entity, ItemStack stack, DamageSource source, int damage, int slot)
	{
		AbilityHelper.damageTool(stack, damage, entity, false);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, int armorSlot)
	{
		return null;
	}

	@Override
	public Item getAccessoryItem()
	{
		return null;
	}

	@Override
	public String getDefaultFolder()
	{
		return null;
	}

	@Override
	public String getEffectSuffix()
	{
		return null;
	}

	@Override
	public Item getHeadItem()
	{
		return null;
	}

	@Override
	public String getIconSuffix(int arg0)
	{
		return null;
	}

	@Override
	public String[] getTraits()
	{
		return new String[] {"armor"};
	}

	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack)
	{

		//TinkersDefense.mods.AMod.UpdateAll((ToolCore) itemStack.getItem(), itemStack, world, player, itemStack.getTagCompound().getCompoundTag("InfiTool"));
	}

	@Override
	public int getRunicCharge(ItemStack itemstack)
	{
		return 0;
	}

	@Override
	public void onUpdate(ItemStack stack, World world, Entity ent, int p_77663_4_, boolean p_77663_5_)
	{
		//Check if runic shielding level has changed
		if(TDIntegration.thaumcraft)
		{
			NBTTagCompound tcTag = stack.getTagCompound();
			NBTTagCompound ticoTag = stack.getTagCompound().getCompoundTag("InfiTool");

			byte rs = tcTag.getByte("RS.HARDEN");
			if(!Charge && rs > 0)
			{
				if(ticoTag.getInteger("Modifiers") > 0)
				{
					ticoTag.setInteger("Modifiers", ticoTag.getInteger("Modifiers") - 1);
					Charge = true;
				}
				else
					tcTag.setByte("RS.HARDEN", (byte) 0);
			}
		}
	}

	public void renderArmor(Entity entity, float f, float f1, float f2, float f3, float f4, float f5, String[] colors, ItemStack stack, int pass)
	{

		ResourceLocation rc = new ResourceLocation("tinkersdefense:textures/" + this.getDefaultFolder() + "/" + getTexture(pass, stack) + ".png");
		FMLClientHandler.instance().getClient().renderEngine.bindTexture(rc);

		float size = 1.6f;
		GL11.glScalef(1.0F / size, 1.0F / size, 1.0F / size);
		GL11.glTranslatef(0.0F, -0.01F, 0.0F);

		int[] intColors = TinkersDefense.hexToRGB(colors[pass]);
		GL11.glColor3d((float) intColors[0] / 255, (float) intColors[1] / 255, (float) intColors[2] / 255);

	}

	public String getTexture(int pass, ItemStack stack)
	{
		NBTTagCompound tags = stack.getTagCompound().getCompoundTag("InfiTool");
		switch(pass)
		{
			case 0:
				return this.getIconSuffix(2);
			case 1:
				return this.getIconSuffix(0);
			case 2:
				return this.getIconSuffix(3);
			case 3:
				return this.getIconSuffix(4);

			default:
				if(tags != null && tags.hasKey("Effect" + (1 + pass - getPartAmount())))
				{
					String effect = effectStrings.get(tags.getInteger("Effect" + (1 + pass - getPartAmount())));
					if(effect != null)
						return effect.substring(effect.lastIndexOf("/"));
					else
						return "";
						
				}
		}
		return "";
	}
}