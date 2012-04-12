package ananas.app.roadmap.util;

import ananas.app.roadmap.util.task.TaskLoadKML;
import android.graphics.drawable.Drawable;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.OverlayItem;

public class ArmKmlOverlay extends ItemizedOverlay<OverlayItem> {

	private OverlayItem mItem;
	private MyLoadTask mCurTask;
	private final MapActivity mActivity;

	public ArmKmlOverlay(MapActivity mapActivity, MapView mapView, Drawable icon) {
		super(boundCenterBottom(icon));

		this.mActivity = mapActivity;

		this.populate();
	}

	@Override
	protected OverlayItem createItem(int i) {

		if (this.mItem == null) {
			GeoPoint point = new GeoPoint(0, 0);
			this.mItem = new MyItem(point, "title", "snippet");
		}
		return this.mItem;
	}

	@Override
	public int size() {
		return 1;
	}

	class MyItem extends OverlayItem {

		public MyItem(GeoPoint point, String title, String snippet) {
			super(point, title, snippet);
			// TODO Auto-generated constructor stub
		}
	}

	public void startLoad() {

		MyLoadTask task = new MyLoadTask();
		Runnable runn = task.getWorkerRunnable();
		this._setCurrentTask(task);
		(new Thread(runn)).start();
	}

	private MyLoadTask _setCurrentTask(MyLoadTask task) {
		MyLoadTask pnew = task;
		MyLoadTask pold = null;
		synchronized (this) {
			pold = this.mCurTask;
			this.mCurTask = pnew;
		}
		return pold;
	}

	class MyLoadTask {

		public Runnable getWorkerRunnable() {
			return new Runnable() {

				@Override
				public void run() {
					MyLoadTask.this._doLoad();
				}
			};
		}

		protected void _doLoad() {
			this._notifyBegin();
			try {
				TaskLoadKML task = new TaskLoadKML();
				task.run();
			} catch (Exception e) {
				e.printStackTrace();
			}
			this._notifyFinish();
		}

		private void _notifyBegin() {
			Runnable runn = new Runnable() {

				@Override
				public void run() {
					MyLoadTask.this._onBegin();
				}
			};
			ArmKmlOverlay.this.mActivity.runOnUiThread(runn);

		}

		private void _notifyFinish() {
			Runnable runn = new Runnable() {

				@Override
				public void run() {
					MyLoadTask.this._onFinish();
				}
			};
			ArmKmlOverlay.this.mActivity.runOnUiThread(runn);
		}

		private void _onBegin() {
			System.out.println(this + ".onBegin");
		}

		private void _onFinish() {
			System.out.println(this + ".onFinish");
		}
	}

}
