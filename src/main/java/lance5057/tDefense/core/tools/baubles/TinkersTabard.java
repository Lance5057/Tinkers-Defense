package lance5057.tDefense.core.tools.baubles;

import java.util.List;

import baubles.api.BaubleType;
import lance5057.tDefense.core.materials.stats.FabricMaterialStats;
import lance5057.tDefense.core.parts.TDParts;
import lance5057.tDefense.core.tools.bases.BaubleTool;
import lance5057.tDefense.core.tools.baubles.renderers.ModelTinkerTabard;
import net.minecraft.client.renderer.BannerTextures;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.BannerPattern;
import slimeknights.tconstruct.library.materials.Material;
import slimeknights.tconstruct.library.tinkering.PartMaterialType;

public class TinkersTabard extends BaubleTool {

	private String name;
	private EnumDyeColor baseColor = EnumDyeColor.BLACK;
	/** A list of all the banner patterns. */
	private NBTTagList patterns;
	private boolean patternDataSet;
	/** A list of all patterns stored on this banner. */
	private List<BannerPattern> patternList;
	/** A list of all the color values stored on this banner. */
	private List<EnumDyeColor> colorList;
	/**
	 * This is a String representation of this banners pattern and color lists, used
	 * for texture caching.
	 */
	private String patternResourceLocation;

	public TinkersTabard() {
		super(BaubleType.BODY, new PartMaterialType(TDParts.fabric, FabricMaterialStats.TYPE),
				new PartMaterialType(TDParts.fabric, FabricMaterialStats.TYPE));
		// TODO Auto-generated constructor stub
		
		setUnlocalizedName("tinkerstabard");
	}

	@Override
	public void onPlayerBaubleRender(ItemStack stack, EntityPlayer player, RenderType type, float partialTicks) {
		if (type == RenderType.BODY) {
			ModelTinkerTabard model = new ModelTinkerTabard(stack);

			model.render(player, player.limbSwing, player.limbSwingAmount, player.ticksExisted,
					player.getRotationYawHead(), player.rotationPitch, 1f);
		}
	}

	@Override
	public NBTTagCompound setupTexture(List<Material> materials) {
		return null;
	}

}
