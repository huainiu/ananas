package ananas.roadmap.jsonable.cmd;

import ananas.roadmap.service.IRoadmapServiceBinderEx;

public class DoIsGpsOn extends JsonableBinderCommand {

	public boolean mIsOn;

	@Override
	public void execute(IRoadmapServiceBinderEx binder) {
		this.mIsOn = binder.isGpsOn();
	}

}
