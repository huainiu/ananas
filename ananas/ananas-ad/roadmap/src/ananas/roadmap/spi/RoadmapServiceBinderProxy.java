package ananas.roadmap.spi;

public class RoadmapServiceBinderProxy implements IRoadmapServiceBinder {

	private IRoadmapServiceBinder mTarget;

	public RoadmapServiceBinderProxy() {
	}

	public void setTarget(IRoadmapServiceBinder target) {
		this.mTarget = target;
	}

	@Override
	public String invoke(String param) {
		IRoadmapServiceBinder tar = this.mTarget;
		if (tar == null) {
			return null;
		}
		return tar.invoke(param);
	}

}
