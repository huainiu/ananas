package ananas.roadmap.uikit.impl;

import ananas.roadmap.uikit.IUI;
import ananas.roadmap.uikit.IUIAgent;

public class IUIAgentImpl implements IUIAgent {

	private IUI mCurUI;

	public IUIAgentImpl() {
	}

	@Override
	public void setCurrentUI(IUI ui) {
		final IUI pnew = ui;
		final IUI pold;
		synchronized (this) {
			pold = this.mCurUI;
			this.mCurUI = ui;
		}
		if (pold != null) {
			pold.onHide();
		}
		if (pnew != null) {
			pnew.onShow();
		}
	}

}
