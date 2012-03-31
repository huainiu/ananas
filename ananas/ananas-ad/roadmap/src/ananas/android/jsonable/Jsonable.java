package ananas.android.jsonable;

import org.json.JSONObject;

public interface Jsonable {

	void onLoad(JSONObject jo);

	void onSave(JSONObject jo);

}
