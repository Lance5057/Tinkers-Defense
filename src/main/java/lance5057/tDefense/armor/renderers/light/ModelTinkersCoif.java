package lance5057.tDefense.armor.renderers.light;

import lance5057.tDefense.TinkersDefense;
import lance5057.tDefense.armor.ArmorCore;
import lance5057.tDefense.armor.renderers.ArmorRenderer;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.FMLClientHandler;

/**
 * ModelBiped - Either Mojang or a mod author
 * Created using Tabula 4.1.1
 */
public class ModelTinkersCoif extends ArmorRenderer
{
	public ModelRenderer	Coif;
	public ModelRenderer	Head;

	public ModelTinkersCoif()
	{
		super(1.1f, 0, 64, 64);

		this.textureWidth = 64;
		this.textureHeight = 64;

		this.Head = new ModelRenderer(this, 32, 48);
		this.Head.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.Head.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.01F);
		this.bipedHead.addChild(this.Head);

		this.Coif = new ModelRenderer(this, 0, 46);
		this.Coif.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.Coif.addBox(-4.0F, -8.0F, -4.0F, 8, 10, 8, 0.51F);
		this.bipedHead.addChild(this.Coif);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		GL11.glPushMatrix();

		for(int i = 0; i < 10; i++)
		{
			String texture = ((ArmorCore) this.stack.getItem()).getTexture(i, stack);
			if(texture != "")
			{

				GL11.glPushMatrix();

				((ArmorCore) this.stack.getItem()).renderArmor(entity, f, f1, f2, f3, f4, f5, colors, stack, i);
				super.render(entity, f, f1, f2, f3, f4, f5);

				GL11.glPopMatrix();
			}
		}

		GL11.glPopMatrix();
	}

	/**
	 * This is a helper function from Tabula to set the rotation of model parts
	 */
	public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z)
	{
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}