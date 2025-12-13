package moze_intel.projecte.rendering;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import moze_intel.projecte.PECore;
import moze_intel.projecte.gameObjs.blocks.PowerFlower;
import moze_intel.projecte.gameObjs.tiles.PowerFlowerTile;
import moze_intel.projecte.utils.PELogger;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class PowerFlowerRenderer extends TileEntitySpecialRenderer
{
    private IModelCustom objModel = null;

    public PowerFlowerRenderer()
    {
        try
        {
            objModel = AdvancedModelLoader.loadModel(new ResourceLocation(PECore.MODID.toLowerCase(), "models/power_flower.obj"));
        }
        catch (Exception e)
        {
            PELogger.logDebug("Failed to load Power Flower model: " + e.getMessage());
            objModel = null;
        }
    }

    @Override
    public void renderTileEntityAt(TileEntity te, double x, double y, double z, float var8)
    {
        if (!(te instanceof PowerFlowerTile))
        {
            return;
        }

        PowerFlowerTile tile = (PowerFlowerTile) te;
        int tier = 1;

        if (tile.getWorldObj() != null)
        {
            if (tile.getWorldObj().getBlock(tile.xCoord, tile.yCoord, tile.zCoord) instanceof PowerFlower)
            {
                PowerFlower p = (PowerFlower) tile.getWorldObj().getBlock(tile.xCoord, tile.yCoord, tile.zCoord);
                tier = p.getTier();
            }
        }

        ResourceLocation texture = new ResourceLocation(PECore.MODID.toLowerCase(), "textures/blocks/power_flower/power_flower_" + Integer.toString(tier) + ".png");
        this.bindTexture(texture);
        GL11.glPushMatrix();
        GL11.glEnable(org.lwjgl.opengl.GL12.GL_RESCALE_NORMAL);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glTranslatef((float) x + 0.5F, (float) y, (float) z + 0.5F);
        if (objModel != null)
        {
            objModel.renderAll();
        }
        GL11.glDisable(org.lwjgl.opengl.GL12.GL_RESCALE_NORMAL);
        GL11.glPopMatrix();
    }
}
