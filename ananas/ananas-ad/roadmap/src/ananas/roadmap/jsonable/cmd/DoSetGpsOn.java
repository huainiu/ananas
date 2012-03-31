package ananas.roadmap.jsonable.cmd;

import ananas.roadmap.service.IRoadmapServiceBinderEx;

public class DoSetGpsOn extends JsonableBinderCommand {

	public boolean mIsOn;

	@Override
	public void execute(IRoadmapServiceBinderEx binder) {
		binder.setGpsOn(this.mIsOn);
	}

}
