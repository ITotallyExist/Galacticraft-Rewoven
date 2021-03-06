/*
 * Copyright (c) 2020 HRZN LTD
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
 *
 */

package com.hrznstudio.galacticraft.client.render.block.entity;

import com.hrznstudio.galacticraft.entity.GalacticraftBlockEntities;
import net.fabricmc.fabric.api.client.rendereregistry.v1.BlockEntityRendererRegistry;

/**
 * @author <a href="https://github.com/StellarHorizons">StellarHorizons</a>
 */
public class GalacticraftBlockEntityRenderers {
    public static void register() {
        BlockEntityRendererRegistry.INSTANCE.register(GalacticraftBlockEntities.BASIC_SOLAR_PANEL_TYPE, BasicSolarPanelBlockEntityRenderer::new);
        BlockEntityRendererRegistry.INSTANCE.register(GalacticraftBlockEntities.COAL_GENERATOR_TYPE, CoalGeneratorBlockEntityRenderer::new);
        BlockEntityRendererRegistry.INSTANCE.register(GalacticraftBlockEntities.CIRCUIT_FABRICATOR_TYPE, CircuitFabricatorBlockEntityRenderer::new);
        BlockEntityRendererRegistry.INSTANCE.register(GalacticraftBlockEntities.OXYGEN_COLLECTOR_TYPE, OxygenCollectorBlockEntityRenderer::new);
        BlockEntityRendererRegistry.INSTANCE.register(GalacticraftBlockEntities.REFINERY_TYPE, RefineryBlockEntityRenderer::new);
        BlockEntityRendererRegistry.INSTANCE.register(GalacticraftBlockEntities.ENERGY_STORAGE_MODULE_TYPE, EnergyStorageModuleBlockEntityRenderer::new);
    }
}
