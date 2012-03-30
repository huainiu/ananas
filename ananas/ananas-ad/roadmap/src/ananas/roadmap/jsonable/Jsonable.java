package ananas.roadmap.jsonable;

import org.json.JSONException;
import org.json.JSONObject;

public abstract class Jsonable {

	public static Jsonable load(String data) {
		try {
			JSONObject jo = new JSONObject(data);
			String cls_name = jo.getString("class");
			Class<?> cls;
			cls = Class.forName(cls_name);
			Jsonable inst = (Jsonable) cls.newInstance();
			inst.onLoad(jo);
			return inst;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String save(Jsonable ja) {
		try {
			JSONObject jo = new JSONObject();
			ja.onSave(jo);
			String cls_name = ja.getClass().getName();
			jo.put("class", cls_name);
			return jo.toString();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

	protected abstract void onLoad(JSONObject jo);

	protected abstract void onSave(JSONObject jo);

}
