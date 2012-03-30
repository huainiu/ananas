package ananas.roadmap;

import ananas.roadmap.uikit.AbstractUI;
import ananas.roadmap.uikit.IActivityCenter;

public class UI_c extends AbstractUI {

	public UI_c(IActivityCenter center) {
		super(center);
	}

	@Override
	protected void onShow() {
		this.getCenter().getActivity().setContentView(R.layout.ui_c);
	}

	@Override
	protected void onHide() {
	}

}
