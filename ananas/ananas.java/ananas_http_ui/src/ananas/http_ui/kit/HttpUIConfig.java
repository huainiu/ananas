package ananas.http_ui.kit;

public class HttpUIConfig implements IHttpUIConfig {

	public int mPortMax = 8810;
	public int mPortMin = 8800;
	private IHttpUIServlet mMainServlet = new DefaultServlet();

	@Override
	public int getPortMin() {
		return this.mPortMin;
	}

	@Override
	public int getPortMax() {
		return this.mPortMax;
	}

	@Override
	public IHttpUIServlet getMainServlet() {
		return this.mMainServlet;
	}

}
