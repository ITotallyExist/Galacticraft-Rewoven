package com.hrznstudio.galacticraft.entity;

import com.hrznstudio.galacticraft.Constants;
import com.hrznstudio.galacticraft.blocks.GalacticraftBlocks;
import com.hrznstudio.galacticraft.blocks.machines.basicsolarpanel.BasicSolarPanelBlockEntity;
import com.hrznstudio.galacticraft.blocks.machines.basicsolarpanel.BasicSolarPanelPartBlockEntity;
import com.hrznstudio.galacticraft.blocks.machines.circuitfabricator.CircuitFabricatorBlockEntity;
import com.hrznstudio.galacticraft.blocks.machines.coalgenerator.CoalGeneratorBlockEntity;
import com.hrznstudio.galacticraft.blocks.machines.compressor.CompressorBlockEntity;
import com.hrznstudio.galacticraft.blocks.machines.electriccompressor.ElectricCompressorBlockEntity;
import com.hrznstudio.galacticraft.blocks.machines.energystoragemodule.EnergyStorageModuleBlockEntity;
import com.hrznstudio.galacticraft.blocks.machines.forcefield.ForceFieldBlockEntity;
import com.hrznstudio.galacticraft.blocks.machines.oxygencollector.OxygenCollectorBlockEntity;
import com.hrznstudio.galacticraft.blocks.machines.refinery.RefineryBlockEntity;
import com.hrznstudio.galacticraft.blocks.special.aluminumwire.AluminumWireBlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

/**
 * @author <a href="https://github.com/StellarHorizons">StellarHorizons</a>
 */
public class GalacticraftBlockEntities {
    public static final BlockEntityType<CoalGeneratorBlockEntity> COAL_GENERATOR_TYPE = Registry.register(Registry.BLOCK_ENTITY, new Identifier(Constants.MOD_ID, Constants.Blocks.COAL_GENERATOR), BlockEntityType.Builder.create(CoalGeneratorBlockEntity::new, GalacticraftBlocks.COAL_GENERATOR).build(null));
    public static final BlockEntityType<BasicSolarPanelBlockEntity> BASIC_SOLAR_PANEL_TYPE = Registry.register(Registry.BLOCK_ENTITY, new Identifier(Constants.MOD_ID, Constants.Blocks.BASIC_SOLAR_PANEL), BlockEntityType.Builder.create(BasicSolarPanelBlockEntity::new, GalacticraftBlocks.BASIC_SOLAR_PANEL).build(null));
    public static final BlockEntityType<BasicSolarPanelPartBlockEntity> BASIC_SOLAR_PANEL_PART_TYPE = Registry.register(Registry.BLOCK_ENTITY, new Identifier(Constants.MOD_ID, Constants.Blocks.BASIC_SOLAR_PANEL_PART), BlockEntityType.Builder.create(BasicSolarPanelPartBlockEntity::new, GalacticraftBlocks.BASIC_SOLAR_PANEL_PART).build(null));
    public static final BlockEntityType<CircuitFabricatorBlockEntity> CIRCUIT_FABRICATOR_TYPE = Registry.register(Registry.BLOCK_ENTITY, new Identifier(Constants.MOD_ID, Constants.Blocks.CIRCUIT_FABRICATOR), BlockEntityType.Builder.create(CircuitFabricatorBlockEntity::new, GalacticraftBlocks.CIRCUIT_FABRICATOR).build(null));
    public static final BlockEntityType<ForceFieldBlockEntity> FORCE_FIELD_TYPE = Registry.register(Registry.BLOCK_ENTITY, new Identifier(Constants.MOD_ID, Constants.Blocks.FORCE_FIELD), BlockEntityType.Builder.create(ForceFieldBlockEntity::new, GalacticraftBlocks.FORCE_FIELD).build(null));
    public static final BlockEntityType<CompressorBlockEntity> COMPRESSOR_TYPE = Registry.register(Registry.BLOCK_ENTITY, new Identifier(Constants.MOD_ID, Constants.Blocks.COMPRESSOR), BlockEntityType.Builder.create(CompressorBlockEntity::new, GalacticraftBlocks.COMPRESSOR).build(null));
    public static final BlockEntityType<ElectricCompressorBlockEntity> ELECTRIC_COMPRESSOR_TYPE = Registry.register(Registry.BLOCK_ENTITY, new Identifier(Constants.MOD_ID, Constants.Blocks.ELECTRIC_COMPRESSOR), BlockEntityType.Builder.create(ElectricCompressorBlockEntity::new, GalacticraftBlocks.ELECTRIC_COMPRESSOR).build(null));
    public static final BlockEntityType<EnergyStorageModuleBlockEntity> ENERGY_STORAGE_MODULE_TYPE = Registry.register(Registry.BLOCK_ENTITY, new Identifier(Constants.MOD_ID, Constants.Blocks.ENERGY_STORAGE_MODULE), BlockEntityType.Builder.create(EnergyStorageModuleBlockEntity::new, GalacticraftBlocks.ENERGY_STORAGE_MODULE).build(null));
    public static final BlockEntityType<RefineryBlockEntity> REFINERY_TYPE = Registry.register(Registry.BLOCK_ENTITY, new Identifier(Constants.MOD_ID, Constants.Blocks.REFINERY), BlockEntityType.Builder.create(RefineryBlockEntity::new, GalacticraftBlocks.REFINERY).build(null));
    public static final BlockEntityType<OxygenCollectorBlockEntity> OXYGEN_COLLECTOR_TYPE = Registry.register(Registry.BLOCK_ENTITY, new Identifier(Constants.MOD_ID, Constants.Blocks.OXYGEN_COLLECTOR), BlockEntityType.Builder.create(OxygenCollectorBlockEntity::new, GalacticraftBlocks.OXYGEN_COLLECTOR).build(null));
    public static final BlockEntityType<AluminumWireBlockEntity> ALUMINUM_WIRE_TYPE = Registry.register(Registry.BLOCK_ENTITY, new Identifier(Constants.MOD_ID, Constants.Blocks.ALUMINUM_WIRE), BlockEntityType.Builder.create(AluminumWireBlockEntity::new, GalacticraftBlocks.ALUMINUM_WIRE).build(null));

    public static void init() {
    }
}