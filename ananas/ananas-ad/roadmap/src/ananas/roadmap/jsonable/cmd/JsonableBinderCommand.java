package ananas.roadmap.jsonable.cmd;

import ananas.roadmap.jsonable.Jsonable;
import ananas.roadmap.service.IRoadmapServiceBinderEx;

public abstract class JsonableBinderCommand extends Jsonable {

	public abstract void execute(IRoadmapServiceBinderEx binder);

}
