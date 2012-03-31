package ananas.roadmap.jsonable.cmd;

import ananas.android.jsonable.DefaultJsonable;
import ananas.roadmap.service.IRoadmapServiceBinderEx;

public abstract class JsonableBinderCommand extends DefaultJsonable {

	public abstract void execute(IRoadmapServiceBinderEx binder);

	public boolean test1 = false;
	public boolean test1_2 = true;
	public int test2_1 = 0x12345678;
	public long test2_2 = 0x123456789abcdefL;
	public short test2_3 = 0x1234;
	public double test3_1 = -951.159;
	public float test3_2 = -951.159f;
	public String test4 = "hello";
	public String test4_2 = null;

}
