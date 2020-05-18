/*
 * Copyright (c) 2019 HRZN LTD
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.hrznstudio.galacticraft.world.biome.moon;

import com.hrznstudio.galacticraft.Constants;
import com.hrznstudio.galacticraft.api.biome.SpaceBiome;
import com.hrznstudio.galacticraft.world.gen.feature.GalacticraftFeatures;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.ConfiguredDecorator;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.decorator.DecoratorConfig;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.FeatureConfig;

/**
 * Base moon biome.
 * @author <a href="https://github.com/StellarHorizons">StellarHorizons</a>
 */
public abstract class MoonBiome extends Biome implements SpaceBiome {

    public MoonBiome(Settings settings) {
        super(settings);
    }

    protected void addMoonVillages() {
        this.addFeature(GenerationStep.Feature.RAW_GENERATION, GalacticraftFeatures.MOON_VILLAGE.configure(new DefaultFeatureConfig()).createDecoratedFeature(new ConfiguredDecorator<>(Decorator.NOPE, DecoratorConfig.DEFAULT)));
        this.addStructureFeature(GalacticraftFeatures.MOON_VILLAGE.configure(FeatureConfig.DEFAULT));
    }

    @Override
    public String getTranslationKey() {
        return "biome." + Constants.MOD_ID + ".moon." + getCategoryName() + "." + getBiomeName();
    }

    protected abstract String getCategoryName();

    protected abstract String getBiomeName();

    @Override
    protected float computeTemperature(BlockPos blockPos) {
        return -1000.0F;
    }

    @Override
    public int getSkyColor() {
        return 0;
    }

    @Override
    @Environment(EnvType.CLIENT)
    public int getFoliageColor() {
        return getWaterFogColor();
    }

    @Override
    @Environment(EnvType.CLIENT)
    public int getGrassColorAt(double x, double z) {
        return getWaterColor();
    }

    @Override
    public TemperatureGroup getTemperatureGroup() {
        return TemperatureGroup.COLD;
    }
}
