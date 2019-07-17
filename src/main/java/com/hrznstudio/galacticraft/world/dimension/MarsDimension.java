package com.hrznstudio.galacticraft.world.dimension;

import com.hrznstudio.galacticraft.Constants;
import com.hrznstudio.galacticraft.Galacticraft;
import com.hrznstudio.galacticraft.api.space.CelestialBody;
import com.hrznstudio.galacticraft.api.space.CelestialBodyIcon;
import com.hrznstudio.galacticraft.api.space.RocketTier;
import com.hrznstudio.galacticraft.blocks.GalacticraftBlocks;
import com.hrznstudio.galacticraft.misc.RocketTiers;
import com.hrznstudio.galacticraft.util.registry.CelestialBodyRegistry;
import com.hrznstudio.galacticraft.world.biome.GalacticraftBiomes;
import com.hrznstudio.galacticraft.world.gen.chunk.GalacticraftChunkGeneratorTypes;
import com.hrznstudio.galacticraft.world.gen.chunk.MarsChunkGeneratorConfig;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.biome.source.BiomeSourceType;
import net.minecraft.world.dimension.Dimension;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.ChunkGeneratorType;

/**
 * @author <a href="https://github.com/StellarHorizons">StellarHorizons</a>
 */
public class MarsDimension extends Dimension implements CelestialBody {

    public MarsDimension(World worldIn, DimensionType typeIn) {
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
        int i = 8421536;
        float f2 = MathHelper.cos(v * 3.141593F * 2.0F) * 2.0F + 0.5F;
        f2 = MathHelper.clamp(f2, 0.0F, 1.0F);
        float f3 = (float) (i >> 16 & 255) / 255.0F;
        float f4 = (float) (i >> 8 & 255) / 255.0F;
        float f5 = (float) (i & 255) / 255.0F;
        f3 = f3 * (f2 * 0.94F + 0.06F);
        f4 = f4 * (f2 * 0.94F + 0.06F);
        f5 = f5 * (f2 * 0.91F + 0.09F);
        return new Vec3d((double) f3, (double) f4, (double) f5);
    }

    @Override
    public BlockPos getForcedSpawnPoint() {
        return new BlockPos(0, 100, 0);
    }

    public ChunkGenerator<?> createChunkGenerator() {
        MarsChunkGeneratorConfig cavesChunkGeneratorConfig_1 = GalacticraftChunkGeneratorTypes.MARS.createSettings();
        cavesChunkGeneratorConfig_1.setDefaultBlock(GalacticraftBlocks.MARS_SURFACE_ROCK.getDefaultState());
        cavesChunkGeneratorConfig_1.setDefaultFluid(Blocks.AIR.getDefaultState());
        return ChunkGeneratorType.SURFACE.create(this.world, BiomeSourceType.FIXED.applyConfig(BiomeSourceType.FIXED.getConfig().setBiome(GalacticraftBiomes.MARS)), cavesChunkGeneratorConfig_1);
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
    public float getSkyAngle(long var1, float var3) {
        int int_1 = (int) (var1 % 24000L);
        float float_2 = ((float) int_1 + (float) var1) / 24000.0F - 0.25F;
        if (float_2 < 0.0F) {
            ++float_2;
        }

        if (float_2 > 1.0F) {
            --float_2;
        }

        float var7 = 1.0F - (float) ((Math.cos((double) float_2 * Math.PI) + 1.0D) / 2.0D);
        float_2 = float_2 + (var7 - float_2) / 3.0F;
        return float_2;
    }

    public boolean hasVisibleSky() {
        return true;
    }

    @Environment(EnvType.CLIENT)
    public float[] getBackgroundColor(float var1, float var2) {
        return new float[]{0, 0, 0, 0};
    }

    @Environment(EnvType.CLIENT)
    @Override
    public float getCloudHeight() {
        return -100.0F;
    }

    public boolean canPlayersSleep() {
        return false;
    }

    public boolean shouldRenderFog(int var1, int var2) {
        return false;
    }

    public DimensionType getType() {
        return GalacticraftDimensions.MARS;
    }

    @Override
    public RocketTier accessTier() {
        return RocketTiers.tierTwo;
    }

    @Override
    public CelestialBody getParent() {
        return null;
    }

    @Override
    public double getOrbitSize() {
        return 30d;
    }

    @Override
    public float getGravity() {
        return Galacticraft.configHandler.getConfig().marsGravity;
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
                return 48;
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
        return I18n.translate("mars");
    }

    @Override
    public Block[] getOreAsteroidBlocks() {
        return new Block[0];
    }

}
