package ananas.roadmap;

import ananas.roadmap.service.DefaultRoadmapServiceConnector;
import ananas.roadmap.service.IRoadmapServiceConnector;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class RoadmapActivity extends Activity {

	private IRoadmapServiceConnector mServConn;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		this.mServConn = new DefaultRoadmapServiceConnector(this);
	}

	@Override
	protected void onStart() {
		super.onStart();
		this.mServConn.connect();
	}

	@Override
	protected void onStop() {
		this.mServConn.disconnect();
		super.onStop();
	}

	private static final int menu_item_a = 1;
	private static final int menu_item_b = 2;
	private static final int menu_item_maps = 3;

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		boolean ret = super.onCreateOptionsMenu(menu);
		int groupId = 1;
		int order = 1;
		menu.add(groupId, menu_item_a, order, "a");
		menu.add(groupId, menu_item_b, order, "b");
		menu.add(groupId, menu_item_maps, order, "maps");
		return ret;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		boolean ret = super.onOptionsItemSelected(item);
		switch (item.getItemId()) {
		case menu_item_a: {

			break;
		}
		case menu_item_b: {

			break;
		}
		case menu_item_maps: {
			Intent intent = new Intent(this, MapsActivity.class);
			this.startActivity(intent);
			break;
		}
		default:
		}
		return ret;
	}

}
