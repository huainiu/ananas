package ananas.roadmap;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;

public class ArmScaleOverlay extends Overlay {

	private final Paint mPaint0;
	private final Paint mPaint1;
	private ScaleProperties mScalePropertiesCache;
	private final Rect mRectCache = new Rect();

	public ArmScaleOverlay() {

		Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
		paint.setStrokeWidth(3);
		paint.setColor(Color.WHITE);
		paint.setTextSize(16);
		this.mPaint0 = paint;

		paint = new Paint(paint);
		paint.setColor(Color.BLACK);
		this.mPaint1 = paint;

	}

	@Override
	public void draw(Canvas canvas, MapView mapView, boolean shadow) {
		// super.draw(canvas, mapView, shadow);

		ScaleProperties sp = this.mScalePropertiesCache;
		sp = this.calcScaleProperties(mapView, sp);
		this.mScalePropertiesCache = sp;

		int x = 10;
		int y = 10;
		this.drawSimpleScale(canvas, sp, x - 1, y - 1, this.mPaint0);
		this.drawSimpleScale(canvas, sp, x - 1, y + 0, this.mPaint0);
		this.drawSimpleScale(canvas, sp, x - 1, y + 1, this.mPaint0);
		this.drawSimpleScale(canvas, sp, x + 0, y - 1, this.mPaint0);
		// this.drawSimpleScale(canvas, sp, x + 0, y + 0, this.mPaint0);
		this.drawSimpleScale(canvas, sp, x + 0, y + 1, this.mPaint0);
		this.drawSimpleScale(canvas, sp, x + 1, y - 1, this.mPaint0);
		this.drawSimpleScale(canvas, sp, x + 1, y + 0, this.mPaint0);
		this.drawSimpleScale(canvas, sp, x + 1, y + 1, this.mPaint0);
		this.drawSimpleScale(canvas, sp, x, y, this.mPaint1);

	}

	private void drawSimpleScale(Canvas canvas, ScaleProperties sp, int x,
			int y, Paint paint) {
		canvas.drawLine(x, y, x + sp.width, y, paint);
		String text = sp.distance + " " + sp.distanceUnit;
		Rect bounds = this.mRectCache;
		paint.getTextBounds(text, 0, text.length(), bounds);
		canvas.drawText(text, x, y + bounds.height() + 5, paint);
	}

	class ScaleProperties {

		public float width = 100;
		public int distance = 10;
		public String distanceUnit = "km";
	}

	private ScaleProperties calcScaleProperties(MapView mapView,
			ScaleProperties sp) {

		final int widthMax = mapView.getWidth() - 50;
		final GeoPoint point = mapView.getMapCenter();
		// mapView.getProjection().metersToEquatorPixels( )

		if (sp == null)
			return new ScaleProperties();
		else
			return sp;
	}

}
