package ananas.roadmap.uikit;

public abstract class AbstractUI {

	private final IActivityCenter mCenter;

	public AbstractUI(IActivityCenter center) {
		this.mCenter = center;
	}

	public IActivityCenter getCenter() {
		return this.mCenter;
	}

	public final void show() {
		this.getCenter().getUIAgent().setCurrentUI(m_iui);
	}

	private final IUI m_iui = new IUI() {

		@Override
		public void onShow() {
			AbstractUI.this.onShow();
		}

		@Override
		public void onHide() {
			AbstractUI.this.onHide();
		}
	};

	protected abstract void onShow();

	protected abstract void onHide();
}
