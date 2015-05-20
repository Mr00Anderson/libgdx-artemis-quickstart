package net.mostlyoriginal.game.system.view;

import com.artemis.Entity;
import com.artemis.World;
import com.artemis.annotations.Wire;
import com.artemis.utils.EntityBuilder;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import net.mostlyoriginal.api.component.basic.Pos;
import net.mostlyoriginal.api.component.graphics.Anim;
import net.mostlyoriginal.api.component.graphics.Renderable;
import net.mostlyoriginal.api.manager.AbstractAssetSystem;

/**
 * @author Daan van Yperen
 */
@Wire
public class FeatureScreenAssetSystem extends AbstractAssetSystem {

	public static final int LOGO_WIDTH = 175;
	public static final int LOGO_HEIGHT = 45;
	public static final int FEATURE_WIDTH = 21;
	public static final int FEATURE_HEIGHT = 21;
	public static final int BACKGROUND_WIDTH = 125;
	public static final int BACKGROUND_HEIGHT = 58;

	@Override
	protected void initialize() {
		super.initialize();

		add("background", 0, 0, BACKGROUND_WIDTH, BACKGROUND_HEIGHT, 1);

		add("logo", 40, 60, LOGO_WIDTH, LOGO_HEIGHT, 1);
		add("feature-packed",  160, 114, FEATURE_WIDTH, FEATURE_HEIGHT, 1);
		add("feature-pooled",  182, 114, FEATURE_WIDTH, FEATURE_HEIGHT, 1);
		add("feature-hotspot", 204, 114, FEATURE_WIDTH, FEATURE_HEIGHT, 1);
		add("feature-factory", 226, 114, FEATURE_WIDTH, FEATURE_HEIGHT, 1);
	}

	@Override
	public Animation get(String identifier) {
		return super.get(identifier);
	}

	public int scaleToScreenRounded(float percentageOfScreen, int pixelWidth) {
		return Math.max(1, (int)scaleToScreen(percentageOfScreen, pixelWidth));
	}

	/** Returns pixel perfect zoom that approximates screen percentage. */
	private float scaleToScreen(float percentageOfScreen, int pixelWidth) {
		return ((Gdx.graphics.getWidth() * percentageOfScreen) / pixelWidth);
	}

	public Entity createAnimCenteredAt(World world, int width, int height, String animId, float zoom) {
		return createAnimAt(world,
				(int)((Gdx.graphics.getWidth() / 2) - ((width / 2) * zoom)),
				(int)((Gdx.graphics.getHeight() / 2) - ((height / 2) * zoom)),
				animId,
				zoom);
	}

	public Entity createAnimAt(World world, int x, int y, String animId, float scale) {

		final Anim anim = new Anim(animId);
		anim.scale= scale;
		return new EntityBuilder(world)
				.with(Renderable.class)
				.with(new Pos(x, y), anim).build();
	}

}
