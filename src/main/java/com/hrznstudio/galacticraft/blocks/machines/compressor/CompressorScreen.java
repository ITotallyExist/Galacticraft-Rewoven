package com.hrznstudio.galacticraft.blocks.machines.compressor;

import com.hrznstudio.galacticraft.Constants;
import com.hrznstudio.galacticraft.api.block.entity.ConfigurableElectricMachineBlockEntity;
import com.hrznstudio.galacticraft.api.screen.MachineContainerScreen;
import com.hrznstudio.galacticraft.blocks.machines.MachineContainer;
import com.hrznstudio.galacticraft.util.DrawableUtils;
import com.mojang.blaze3d.platform.GlStateManager;
import net.fabricmc.fabric.api.container.ContainerFactory;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.gui.screen.ingame.AbstractContainerScreen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * @author <a href="https://github.com/StellarHorizons">StellarHorizons</a>
 */
public class CompressorScreen extends AbstractContainerScreen<CompressorContainer> {

    public static final ContainerFactory<AbstractContainerScreen> FACTORY = createFactory(CompressorBlockEntity.class, CompressorScreen::new);

    public static <T extends ConfigurableElectricMachineBlockEntity> ContainerFactory<AbstractContainerScreen> createFactory(
            Class<T> machineClass, MachineContainer.MachineContainerConstructor<? extends AbstractContainerScreen<?>, T> constructor)
    {
        return (syncId, id, player, buffer) -> {
            BlockPos pos = buffer.readBlockPos();
            BlockEntity be = player.world.getBlockEntity(pos);
            if (machineClass.isInstance(be)) {
                return constructor.create(syncId, player, machineClass.cast(be));
            } else {
                return null;
            }
        };
    }

    private static final int PROGRESS_X = 204;
    private static final int PROGRESS_Y = 0;
    private static final int PROGRESS_WIDTH = 52;
    private static final int PROGRESS_HEIGHT = 25;
    protected final Identifier BACKGROUND = new Identifier(Constants.MOD_ID, getBackgroundLocation());
    protected int progressDisplayX;

    protected int progressDisplayY;
    protected BlockPos blockPos;

    protected World world;

    public CompressorScreen(int syncId, PlayerEntity playerEntity, CompressorBlockEntity blockEntity) {
        this(new CompressorContainer(syncId, playerEntity, blockEntity), playerEntity, blockEntity, new TranslatableText("ui.galacticraft-rewoven.compressor.name"));
        this.containerHeight = 192;
    }

    public CompressorScreen(CompressorContainer electricCompressorContainer, PlayerEntity playerEntity, CompressorBlockEntity blockEntity, Text textComponents) {
        super(electricCompressorContainer, playerEntity.inventory, textComponents);
        this.world = playerEntity.world;
    }

    protected String getBackgroundLocation() {
        return Constants.ScreenTextures.getRaw(Constants.ScreenTextures.COMPRESSOR_SCREEN);
    }

    protected void updateProgressDisplay() {
        progressDisplayX = left + 77;
        progressDisplayY = top + 28;
    }

    @Override
    protected void drawBackground(float var1, int var2, int var3) {
        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.renderBackground();
        this.minecraft.getTextureManager().bindTexture(BACKGROUND);

        updateProgressDisplay();

        //this.drawTexturedRect(...)
        this.blit(this.left, this.top, 0, 0, this.containerWidth, this.containerHeight);

        this.drawFuelProgressBar();
        this.drawCraftProgressBar();
    }

    @Override
    public void render(int mouseX, int mouseY, float v) {
        super.render(mouseX, mouseY, v);
        DrawableUtils.drawCenteredString(this.minecraft.textRenderer, getContainerDisplayName(), (this.width / 2), this.top + 6, Formatting.DARK_GRAY.getColorValue());
        this.drawMouseoverTooltip(mouseX, mouseY);
    }

    protected String getContainerDisplayName() {
        return new TranslatableText("block.galacticraft-rewoven.compressor").asFormattedString();
    }

    protected void drawFuelProgressBar() {
        //this.drawTexturedReact(...)
        this.blit(left, top, 0, 0, this.containerWidth, this.containerHeight);
        int fuelUsageScale;
        CompressorStatus status = container.blockEntity.status;

        if (status != CompressorStatus.INACTIVE) {
            fuelUsageScale = getFuelProgress();
            this.blit(left + 80, top + 29 + 12 - fuelUsageScale, 203, 39 - fuelUsageScale, 14, fuelUsageScale + 1);
        }
    }

    protected void drawCraftProgressBar() {
        float progress = container.blockEntity.getProgress();
        float maxProgress = container.blockEntity.getMaxProgress();
        float progressScale = (progress / maxProgress);
        // Progress confirmed to be working properly, below code is the problem.

        this.minecraft.getTextureManager().bindTexture(BACKGROUND);
        this.blit(progressDisplayX, progressDisplayY, PROGRESS_X, PROGRESS_Y, (int) (PROGRESS_WIDTH * progressScale), PROGRESS_HEIGHT);
    }

    private int getFuelProgress() {
        int maxFuelTime = container.blockEntity.maxFuelTime;
        if (maxFuelTime == 0) {
            maxFuelTime = 200;
        }

        // 0 = CompressorBlockEntity#fuelTime
        return container.fuelTime.get() * 13 / maxFuelTime;
    }

    @Override
    public void drawMouseoverTooltip(int mouseX, int mouseY) {
        super.drawMouseoverTooltip(mouseX, mouseY);
        if (mouseX >= this.left - 22 && mouseX <= this.left && mouseY >= this.top + 3 && mouseY <= this.top + (22 + 3)) {
            this.renderTooltip("\u00A77" + new TranslatableText("ui.galacticraft-rewoven.tabs.side_config").asFormattedString(), mouseX, mouseY);
        }
    }

}