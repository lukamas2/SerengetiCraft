package com.africacraft.mob;


import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderGemsbok extends RenderLiving<EntityGemsbok>{
	
    private ResourceLocation mobTexture = new ResourceLocation("africacraft:textures/entity/preyanimal/gemsbok.png");
    
	public RenderGemsbok(RenderManager rendermanagerIn, ModelBase modelbaseIn, float shadowsizeIn) {
		super(rendermanagerIn, modelbaseIn, shadowsizeIn);
	}
	
    /**
     * Renders the desired {@code T} type Entity.
     */
    public void doRender(EntityGemsbok entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }

    /**
     * Allows the render to do state modifications necessary before the model is rendered.
     */
    protected void preRenderCallback(EntityGemsbok entitylivingbaseIn, float partialTickTime)
    { 
        super.preRenderCallback(entitylivingbaseIn, partialTickTime);
    }

	@Override
	protected ResourceLocation getEntityTexture(EntityGemsbok entity) {
		return mobTexture;
	}
}