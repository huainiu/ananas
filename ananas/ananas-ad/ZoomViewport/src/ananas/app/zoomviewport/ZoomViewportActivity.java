package ananas.app.zoomviewport;

import android.app.Activity;
import android.hardware.Camera;
import android.os.Bundle;
import android.widget.FrameLayout;

public class ZoomViewportActivity extends Activity {

	private Camera mCamera;
	private CameraPreview mPreview;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		CameraTools ctools = new CameraTools();
		if (!ctools.checkCameraHardware(this)) {
			System.out.println("this device no support camera!");
		}

		// Create an instance of Camera
		this.mCamera = ctools.getCameraInstance();

		// Create our Preview view and set it as the content of our activity.
		this.mPreview = new CameraPreview(this, mCamera);
		FrameLayout preview = (FrameLayout) findViewById(R.id.camera_preview);
		preview.addView(mPreview);
	}
}