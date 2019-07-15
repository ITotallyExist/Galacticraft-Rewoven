package com.hrznstudio.galacticraft.world.dimension;

import com.hrznstudio.galacticraft.Constants;
import com.hrznstudio.galacticraft.Galacticraft;
import com.hrznstudio.galacticraft.api.space.CelestialBody;
import com.hrznstudio.galacticraft.api.space.CelestialBodyIcon;
import com.hrznstudio.galacticraft.api.space.RocketTier;
import com.hrznstudio.galacticraft.misc.RocketTiers;
import com.hrznstudio.galacticraft.util.registry.CelestialBodyRegistry;
import com.hrznstudio.galacticraft.world.biome.source.GalacticraftBiomeSourceTypes;
import com.hrznstudio.galacticraft.world.gen.chunk.GalacticraftChunkGeneratorTypes;
import com.hrznstudio.galacticraft.world.gen.chunk.MoonChunkGeneratorConfig;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.dimension.Dimension;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.level.LevelGeneratorType;
import net.minecraft.world.level.LevelProperties;

/**
 * @author <a href="https://github.com/StellarHorizons">StellarHorizons</a>
 */
public class MoonDimension extends Dimension implements CelestialBody {

    public MoonDimension(World worldIn, DimensionType typeIn) {
        super(worldIn, typeIn);
        CelestialBodyRegistry.register(this);
    }

    @Override
    public int getMoonPhase(long long_1) {
        return 0;
    }


    @Override
    public boolean hasSkyLight() {
        return true;
    }

    @Override
    public boolean isNether() {
        return false;
    }

    @Override
    public Vec3d getFogColor(float v, float v1) {
        return new Vec3d(0, 0, 0);
    }

    @Override
    public BlockPos getForcedSpawnPoint() {
        return new BlockPos(0, 100, 0);
    }

    public ChunkGenerator<MoonChunkGeneratorConfig> createChunkGenerator() {
        MoonChunkGeneratorConfig moonChunkGeneratorConfig = GalacticraftChunkGeneratorTypes.MOON.createSettings();
        LevelProperties properties = this.world.getLevelProperties();
        properties.setGeneratorType(LevelGeneratorType.DEFAULT);
        return GalacticraftChunkGeneratorTypes.MOON.create(this.world, GalacticraftBiomeSourceTypes.MOON.applyConfig(GalacticraftBiomeSourceTypes.MOON.getConfig().setLevelProperties(properties).setGeneratorSettings(moonChunkGeneratorConfig)), moonChunkGeneratorConfig);
    }

    @Override
    public BlockPos getSpawningBlockInChunk(ChunkPos chunkPos, boolean b) {
        return new BlockPos(0, 100, 0);
    }

    @Override
    public BlockPos getTopSpawningBlockPosition(int i, int i1, boolean b) {
        return new BlockPos(0, 100, 0);
    }

    @Override
    public float getSkyAngle(long l1, float l2) {
        int i = (int) (l1 % 24000L);
        float f = ((float) i + (float) l1) / 24000.0F - 0.25F;
        if (f < 0.0F) {
            ++f;
        }

        if (f > 1.0F) {
            --f;
        }

        float g = 1.0F - (float) ((Math.cos((double) f * Math.PI) + 1.0D) / 2.0D);
        f = f + (g - f) / 3.0F;
        return f;
    }

    public boolean hasVisibleSky() {
        return true;
    }

    @Environment(EnvType.CLIENT)
    public float[] getBackgroundColor(float l1, float f2) {
        return new float[]{0, 0, 0, 0};
    }

    @Environment(EnvType.CLIENT)
    @Override
    public float getCloudHeight() {
        return -1000.0F;
    }

    public boolean canPlayersSleep() {
        return false;
    }

    public boolean shouldRenderFog(int l1, int f2) {
        return false;
    }

    public DimensionType getType() {
        return GalacticraftDimensions.MOON;
    }

    @Override
    public RocketTier accessTier() {
        return RocketTiers.tierOne;
    }

    @Override
    public CelestialBody getParent() {
        return null;
    }

    @Override
    public double getOrbitSize() {
        return 10d;
    }

    @Override
    public float getGravity() {
        return Galacticraft.configHandler.getConfig().moonGravity;
    }

    @Override
    public boolean hasOxygen() {
        return false;
    }

    @Override
    public CelestialBodyIcon getIcon() {
        return new CelestialBodyIcon() {
            @Override
            public Identifier getTexture() {
                return new Identifier(Constants.MOD_ID, Constants.ScreenTextures.PLANET_ICONS);
            }

            @Override
            public int getX() {
                return 32;
            }

            @Override
            public int getY() {
                return 0;
            }

            @Override
            public int getWidth() {
                return 16;
            }

            @Override
            public int getHeight() {
                return 16;
            }
        };
    }

    @Override
    public String getName() {
        return "moon";
    }
}
