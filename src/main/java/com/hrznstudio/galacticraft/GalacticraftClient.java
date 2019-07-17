package com.hrznstudio.galacticraft;

import com.hrznstudio.galacticraft.blocks.machines.basicsolarpanel.BasicSolarPanelScreen;
import com.hrznstudio.galacticraft.blocks.machines.circuitfabricator.CircuitFabricatorScreen;
import com.hrznstudio.galacticraft.blocks.machines.coalgenerator.CoalGeneratorScreen;
import com.hrznstudio.galacticraft.blocks.machines.compressor.CompressorScreen;
import com.hrznstudio.galacticraft.blocks.machines.electriccompressor.ElectricCompressorScreen;
import com.hrznstudio.galacticraft.blocks.machines.energystoragemodule.EnergyStorageModuleScreen;
import com.hrznstudio.galacticraft.blocks.machines.oxygencollector.OxygenCollectorScreen;
import com.hrznstudio.galacticraft.client.render.block.entity.GalacticraftBlockEntityRenderers;
import com.hrznstudio.galacticraft.client.render.entity.RenderOreAsteroid;
import com.hrznstudio.galacticraft.client.render.entity.RenderSpaceDebris;
import com.hrznstudio.galacticraft.client.render.fluid.OilFluidRenderHandler;
import com.hrznstudio.galacticraft.container.GalacticraftContainers;
import com.hrznstudio.galacticraft.container.screen.PlayerInventoryGCScreen;
import com.hrznstudio.galacticraft.entity.RocketEntityRenderer;
import com.hrznstudio.galacticraft.entity.asteroid.OreAsteroidEntity;
import com.hrznstudio.galacticraft.entity.asteroid.SpaceDebrisEntity;
import com.hrznstudio.galacticraft.entity.moonvillager.T1RocketEntity;
import com.hrznstudio.galacticraft.fluids.GalacticraftFluids;
import com.hrznstudio.galacticraft.misc.capes.CapeLoader;
import com.hrznstudio.galacticraft.misc.capes.JsonCapes;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.render.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandlerRegistry;
import net.fabricmc.fabric.api.client.screen.ScreenProviderRegistry;
import net.fabricmc.fabric.api.event.client.ClientSpriteRegistryCallback;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.util.Identifier;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author <a href="https://github.com/StellarHorizons">StellarHorizons</a>
 */
public class GalacticraftClient implements ClientModInitializer {

    public static JsonCapes jsonCapes;
    public static CapeLoader capeLoader;


    @Override
    public void onInitializeClient() {
        capeLoader = new CapeLoader();
        jsonCapes = new JsonCapes();
        capeLoader.register(jsonCapes);
        capeLoader.load();

        ClientSpriteRegistryCallback.event(SpriteAtlasTexture.BLOCK_ATLAS_TEX).register((spriteAtlasTexture, registry) -> {
//        ClientSpriteRegistryCallback.EVENT.register((spriteAtlasTexture, registry) -> {
            registry.register(new Identifier(Constants.MOD_ID, Constants.ScreenTextures.COAL_GENERATOR_SCREEN));
            registry.register(new Identifier(Constants.MOD_ID, Constants.ScreenTextures.BASIC_SOLAR_PANEL_SCREEN));
            registry.register(new Identifier(Constants.MOD_ID, Constants.ScreenTextures.MACHINE_CONFIG_TABS));
            registry.register(new Identifier(Constants.MOD_ID, Constants.ScreenTextures.MACHINE_CONFIG_PANELS));
            registry.register(new Identifier(Constants.MOD_ID, Constants.ScreenTextures.CIRCUIT_FABRICATOR_SCREEN));
            registry.register(new Identifier(Constants.MOD_ID, Constants.ScreenTextures.PLAYER_INVENTORY_SCREEN));
            registry.register(new Identifier(Constants.MOD_ID, Constants.ScreenTextures.PLAYER_INVENTORY_TABS));
            registry.register(new Identifier(Constants.MOD_ID, Constants.ScreenTextures.MAP_SCREEN));

            registry.register(new Identifier(Constants.MOD_ID, Constants.SlotSprites.THERMAL_HEAD));
            registry.register(new Identifier(Constants.MOD_ID, Constants.SlotSprites.THERMAL_CHEST));
            registry.register(new Identifier(Constants.MOD_ID, Constants.SlotSprites.THERMAL_PANTS));
            registry.register(new Identifier(Constants.MOD_ID, Constants.SlotSprites.THERMAL_BOOTS));
            registry.register(new Identifier(Constants.MOD_ID, Constants.SlotSprites.OXYGEN_MASK));
            registry.register(new Identifier(Constants.MOD_ID, Constants.SlotSprites.OXYGEN_GEAR));
            registry.register(new Identifier(Constants.MOD_ID, Constants.SlotSprites.OXYGEN_TANK));

            registry.register(Constants.Fluids.getIdentifier(Constants.Fluids.CRUDE_OIL_FLUID_STILL));
            registry.register(Constants.Fluids.getIdentifier(Constants.Fluids.CRUDE_OIL_FLUID_FLOWING));
        });

        ScreenProviderRegistry.INSTANCE.registerFactory(GalacticraftContainers.PLAYER_INVENTORY_CONTAINER, (syncId, identifier, playerEntity, packetByteBuf) -> new PlayerInventoryGCScreen(playerEntity));

        ScreenProviderRegistry.INSTANCE.registerFactory(GalacticraftContainers.COAL_GENERATOR_CONTAINER, CoalGeneratorScreen.FACTORY);
        ScreenProviderRegistry.INSTANCE.registerFactory(GalacticraftContainers.BASIC_SOLAR_PANEL_CONTAINER, BasicSolarPanelScreen.FACTORY);
        ScreenProviderRegistry.INSTANCE.registerFactory(GalacticraftContainers.CIRCUIT_FABRICATOR_CONTAINER, CircuitFabricatorScreen.FACTORY);
        ScreenProviderRegistry.INSTANCE.registerFactory(GalacticraftContainers.COMPRESSOR_CONTAINER, CompressorScreen.FACTORY);
        ScreenProviderRegistry.INSTANCE.registerFactory(GalacticraftContainers.ELECTRIC_COMPRESSOR_CONTAINER, ElectricCompressorScreen.ELECTRIC_FACTORY);
        ScreenProviderRegistry.INSTANCE.registerFactory(GalacticraftContainers.ENERGY_STORAGE_MODULE_CONTAINER, EnergyStorageModuleScreen.FACTORY);
        ScreenProviderRegistry.INSTANCE.registerFactory(GalacticraftContainers.OXYGEN_COLLECTOR_CONTAINER, OxygenCollectorScreen.FACTORY);

        EntityRendererRegistry.INSTANCE.register(T1RocketEntity.class, (manager, context) -> new RocketEntityRenderer(manager));
        EntityRendererRegistry.INSTANCE.register(OreAsteroidEntity.class, (manager, context) -> new RenderOreAsteroid(manager));
        EntityRendererRegistry.INSTANCE.register(SpaceDebrisEntity.class, (manager, context) -> new RenderSpaceDebris(manager));
        GalacticraftBlockEntityRenderers.register();

        FluidRenderHandlerRegistry.INSTANCE.register(GalacticraftFluids.STILL_CRUDE_OIL, new OilFluidRenderHandler());
        FluidRenderHandlerRegistry.INSTANCE.register(GalacticraftFluids.FLOWING_CRUDE_OIL, new OilFluidRenderHandler());

        if (FabricLoader.getInstance().isModLoaded("modmenu")) {
            try {
                Class<?> clazz = Class.forName("io.github.prospector.modmenu.api.ModMenuApi");
                Method method = clazz.getMethod("addConfigOverride", String.class, Runnable.class);
                method.invoke(null, Constants.MOD_ID, (Runnable) () -> Galacticraft.configHandler.openConfigScreen());
            } catch (NoSuchMethodException | ClassNotFoundException | IllegalAccessException | InvocationTargetException e) {
                Galacticraft.logger.error("[Galacticraft] Failed to add modmenu config override. {1}", e);
            }
        }
    }
}
