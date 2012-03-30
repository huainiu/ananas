package ananas.roadmap;

import ananas.roadmap.uikit.AbstractUI;
import ananas.roadmap.uikit.IActivityCenter;

public class UI_b extends AbstractUI {

	public UI_b(IActivityCenter center) {
		super(center);
	}

	@Override
	protected void onShow() {
		this.getCenter().getActivity().setContentView(R.layout.ui_b);
	}

	@Override
	protected void onHide() {
	}

}
