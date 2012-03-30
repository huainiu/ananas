package ananas.roadmap.service;

import ananas.roadmap.jsonable.Jsonable;
import ananas.roadmap.jsonable.cmd.JsonableBinderCommand;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class RoadmapService extends Service {

	@Override
	public IBinder onBind(Intent arg0) {
		return this.mBinderBase;
	}

	private final MyBaseBinder mBinderBase = new MyBaseBinder();

	class MyBaseBinder extends Binder implements IRoadmapServiceBinder {

		@Override
		public String invoke(String param) {
			JsonableBinderCommand cmd = (JsonableBinderCommand) Jsonable
					.load(param);
			cmd.execute(RoadmapService.this.mBinderEx);
			return Jsonable.save(cmd);
		}
	}

	private final IRoadmapServiceBinderEx mBinderEx = new IRoadmapServiceBinderEx() {

		@Override
		public boolean isGpsOn() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public void setGpsOn(boolean enable) {
			// TODO Auto-generated method stub

		}

	};

}
