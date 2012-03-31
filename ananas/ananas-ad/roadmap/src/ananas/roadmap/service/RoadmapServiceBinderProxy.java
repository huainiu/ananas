package ananas.roadmap.service;

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
		String ret = tar.invoke(param);
		System.out.println(this + ".invoke");
		System.out.println("    param=" + param);
		System.out.println("      ret=" + ret);
		return ret;
	}

}
