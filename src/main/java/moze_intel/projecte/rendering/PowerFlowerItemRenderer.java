package moze_intel.projecte.rendering;

import cpw.mods.fml.client.FMLClientHandler;
import moze_intel.projecte.PECore;
import moze_intel.projecte.utils.PELogger;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import org.lwjgl.opengl.GL11;

public class PowerFlowerItemRenderer implements IItemRenderer
{
    private final ResourceLocation texture;
    private IModelCustom objModel = null;

    public PowerFlowerItemRenderer(int tier)
    {
        this.texture = new ResourceLocation(PECore.MODID.toLowerCase(), "textures/blocks/power_flower/power_flower_" + Integer.toString(tier) + ".png");
        try {
            objModel = AdvancedModelLoader.loadModel(new ResourceLocation(PECore.MODID.toLowerCase(), "models/power_flower.obj"));
        } catch (Exception e) {
            PELogger.logDebug("Failed to load Power Flower model: " + e.getMessage());
            objModel = null;
        }
    }

    @Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type)
    {
        return true;
    }

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper)
    {
        return true;
    }

    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data)
    {
        switch (type)
        {
            case ENTITY:
                renderPowerFlower(0.0F, 0.5F, 0.0F, 0.5F);
                break;
            case EQUIPPED:
                renderPowerFlower(0.0F, 0.0F, 0.0F, 1.0F);
                break;
            case EQUIPPED_FIRST_PERSON:
                renderPowerFlower(0.0F, 0.0F, 0.0F, 1.0F);
                break;
            case INVENTORY:
                renderPowerFlower(0.0F, 0.0F, 0.0F, 1.0F);
                break;
            default:
                break;
        }
    }

    private void renderPowerFlower(float x, float y, float z, float scale)
    {
        FMLClientHandler.instance().getClient().renderEngine.bindTexture(texture);
        GL11.glPushMatrix();
        GL11.glTranslatef(x, y, z);
        GL11.glScalef(scale, scale, scale);
        if (objModel != null)
        {
            objModel.renderAll();
        }
        GL11.glPopMatrix();
    }
}
