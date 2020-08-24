package lance5057.tDefense.core.tools.baubles;

import java.util.List;

import baubles.api.BaubleType;
import lance5057.tDefense.core.parts.TDParts;
import lance5057.tDefense.core.tools.bases.BaubleTool;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import slimeknights.tconstruct.library.materials.Material;
import slimeknights.tconstruct.library.tinkering.PartMaterialType;

public class TinkerRing extends BaubleTool
{
	public TinkerRing()
	{
		super(BaubleType.RING, PartMaterialType.head(TDParts.ringShank), PartMaterialType.handle(TDParts.filigree),
				PartMaterialType.extra(TDParts.setting));
		
		setUnlocalizedName("tinkerRing");
	}

	@Override
	public void onPlayerBaubleRender(ItemStack stack, EntityPlayer player, RenderType type, float partialTicks) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public NBTTagCompound setupTexture(List<Material> materials) {
		// TODO Auto-generated method stub
		return null;
	}

}
