package ananas.roadmap.jsonable.cmd;

import org.json.JSONException;
import org.json.JSONObject;

import ananas.roadmap.service.IRoadmapServiceBinderEx;

public class DoIsGpsOn extends JsonableBinderCommand {

	public boolean mIsOn;

	@Override
	public void execute(IRoadmapServiceBinderEx binder) {
		this.mIsOn = binder.isGpsOn();
	}

	private final static String field_is_on = "is_on";

	@Override
	protected void onLoad(JSONObject jo) {
		try {
			this.mIsOn = jo.getBoolean(field_is_on);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void onSave(JSONObject jo) {
		try {
			jo.put(field_is_on, this.mIsOn);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

}
