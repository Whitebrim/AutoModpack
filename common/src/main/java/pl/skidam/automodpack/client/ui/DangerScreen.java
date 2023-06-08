package pl.skidam.automodpack.client.ui;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import pl.skidam.automodpack.TextHelper;
import pl.skidam.automodpack.client.ModpackUpdater;
import pl.skidam.automodpack.client.audio.AudioManager;

import java.io.File;
import java.util.concurrent.CompletableFuture;

import static pl.skidam.automodpack.StaticVariables.LOGGER;

public class DangerScreen extends Screen {
    private final Screen parent;
    private final String link;
    private final File modpackDir;
    private final File modpackContentFile;

    public DangerScreen(Screen parent, String link, File modpackDir, File modpackContentFile) {
        super(TextHelper.translatable("gui.automodpack.danger").formatted(Formatting.BOLD));
        this.parent = parent;
        this.link = link;
        this.modpackDir = modpackDir;
        this.modpackContentFile = modpackContentFile;

        if (AudioManager.isMusicPlaying()) {
            AudioManager.stopMusic();
        }
    }

    @Override
    protected void init() {
        super.init();
        assert this.client != null;

        this.addDrawableChild(ButtonWidget.builder(TextHelper.translatable("gui.automodpack.dontdownload").formatted(Formatting.RED), button -> {
            LOGGER.error("User canceled download, setting his to screen " + parent.getTitle().getString());
            this.client.setScreen(parent);
        }).position(this.width / 2 - 110, this.height / 6 + 96).size(120, 20).build());

        this.addDrawableChild(ButtonWidget.builder(TextHelper.translatable("gui.automodpack.download").formatted(Formatting.GREEN), button -> {
            CompletableFuture.runAsync(() -> {
                ModpackUpdater.ModpackUpdaterMain(link, modpackDir, modpackContentFile);
            });
        }).position(this.width / 2 + 10, this.height / 6 + 96).size(120, 20).build());
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        this.renderBackground(matrices);
        drawCenteredTextWithShadow(matrices, this.textRenderer, this.title, this.width / 2, 55, 16777215);
        drawCenteredTextWithShadow(matrices, this.textRenderer, TextHelper.translatable("gui.automodpack.danger.description"), this.width / 2, 80, 16777215);
        drawCenteredTextWithShadow(matrices, this.textRenderer, TextHelper.translatable("gui.automodpack.danger.secDescription"), this.width / 2, 90, 16777215);
        super.render(matrices, mouseX, mouseY, delta);
    }

    @Override
    public Text getTitle() { // hehe
        return TextHelper.literal("DangerScreen");
    }

    @Override
    public boolean shouldCloseOnEsc() {
        return false;
    }
}
