package ananas.app.htcplink.webproxy.servlets;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Hashtable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Bridge
 */
public class Bridge extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Bridge() {
		super();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		this._doProc(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this._doProc(request, response);
	}

	private void _doProc(HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		// {"session":"123","method":"listen","length":1022,"data":"[base64]"}

		String channel = request.getHeader("htcplink.channel").trim();
		String action = request.getHeader("htcplink.action").trim();// open |
																	// get

		ChannelManager cm = DefaultChannelManager.getDefault();
		Channel chl = null;
		boolean master = false;
		if (action.equals("")) {
		} else if (action.equals("open")) {
			chl = cm.openChannel(channel);
			master = true;
		} else if (action.equals("get")) {
			chl = cm.getChannel(channel);
			master = false;
		}
		InputStream is = request.getInputStream();
		OutputStream os = response.getOutputStream();
		Endpoint ep = chl.bindEndpoint(master, is, os);

		response.setStatus(HttpServletResponse.SC_OK);
		os.write("{}".getBytes());
		os.flush();

		if (!master) {
			ep.startPump();
		}
	}

	public static interface Endpoint {

		void startPump();

		void close();

		InputStream getInputStream();

		OutputStream getOutputStream();
	}

	public static interface Channel {

		Endpoint bindEndpoint(boolean master, InputStream is, OutputStream os);

		Endpoint getPairEndpoint(Endpoint ep);

		String getId();

		void close();

	}

	public static interface ChannelManager {

		Channel openChannel(String channelId);

		Channel getChannel(String channelId);

		void closeChannel(Channel channel);

	}

	private static class DefaultEndpoint implements Endpoint {

		private final Channel mOwnerChannel;
		private final InputStream mIS;
		private final OutputStream mOS;

		public DefaultEndpoint(Channel ownerChannel, InputStream is,
				OutputStream os) {
			this.mOwnerChannel = ownerChannel;
			this.mIS = is;
			this.mOS = os;
		}

		@Override
		public void startPump() {

			Endpoint ep1 = this;
			InputStream is1 = ep1.getInputStream();
			OutputStream os1 = ep1.getOutputStream();

			Endpoint ep2 = this.mOwnerChannel.getPairEndpoint(ep1);
			InputStream is2 = ep2.getInputStream();
			OutputStream os2 = ep2.getOutputStream();

			IOStreamPump pump1 = new IOStreamPump(is1, os2);
			IOStreamPump pump2 = new IOStreamPump(is2, os1);

			pump1.start();
			pump2.start();
		}

		@Override
		public void close() {
			try {
				this.mIS.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				this.mOS.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		@Override
		public InputStream getInputStream() {
			return this.mIS;
		}

		@Override
		public OutputStream getOutputStream() {
			return this.mOS;
		}

	}

	private static class DefaultChannel implements Channel {

		private final String mId;

		private Endpoint mMasterEP;
		private Endpoint mSlaverEP;

		public DefaultChannel(String chId) {
			this.mId = chId;
		}

		@Override
		public String getId() {
			return this.mId;
		}

		@Override
		public void close() {

			Endpoint ep;

			ep = this.mMasterEP;
			if (ep != null) {
				ep.close();
			}

			ep = this.mSlaverEP;
			if (ep != null) {
				ep.close();
			}

		}

		@Override
		public Endpoint bindEndpoint(boolean master, InputStream is,
				OutputStream os) {

			Endpoint ep = new DefaultEndpoint(this, is, os);
			if (master) {
				this.mMasterEP = ep;
			} else {
				this.mSlaverEP = ep;
			}
			return ep;
		}

		@Override
		public Endpoint getPairEndpoint(Endpoint ep) {
			if (ep == null) {
				return null;
			} else if (ep.equals(this.mMasterEP)) {
				return this.mSlaverEP;
			} else if (ep.equals(this.mSlaverEP)) {
				return this.mMasterEP;
			} else {
				return null;
			}
		}
	}

	private static class DefaultChannelManager implements ChannelManager {

		private static ChannelManager sDefault;

		public static ChannelManager getDefault() {
			if (sDefault == null) {
				sDefault = new DefaultChannelManager();
			}
			return sDefault;
		}

		private final Hashtable<String, Channel> mChannelTable;

		public DefaultChannelManager() {
			this.mChannelTable = new Hashtable<String, Channel>();
		}

		@Override
		public Channel openChannel(String chId) {
			Channel chl = this.mChannelTable.get(chId);
			if (chl == null) {
				chl = new DefaultChannel(chId);
				this.mChannelTable.put(chId, chl);
				return chl;
			} else {
				return null;
			}
		}

		@Override
		public Channel getChannel(String chId) {
			return this.mChannelTable.get(chId);
		}

		@Override
		public void closeChannel(Channel chl) {
			String id = chl.getId();
			this.mChannelTable.remove(id);
			chl.close();
		}
	}

	private static class IOStreamPump implements Runnable {

		private final InputStream mIS;
		private final OutputStream mOS;

		public IOStreamPump(InputStream is, OutputStream os) {
			this.mIS = is;
			this.mOS = os;
		}

		public void start() {
			Thread thd = new Thread(this);
			thd.start();
		}

		@Override
		public void run() {
			try {
				InputStream is = this.mIS;
				OutputStream os = this.mOS;
				byte[] buf = new byte[512];
				for (int cb = is.read(buf); cb > 0; cb = is.read(buf)) {
					os.write(buf, 0, cb);
					os.flush();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
