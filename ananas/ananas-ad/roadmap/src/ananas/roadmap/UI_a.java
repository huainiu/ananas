package ananas.roadmap;

import ananas.roadmap.uikit.AbstractUI;
import ananas.roadmap.uikit.IActivityCenter;

public class UI_a extends AbstractUI {

	public UI_a(IActivityCenter center) {
		super(center);
	}

	@Override
	protected void onShow() {
		this.getCenter().getActivity().setContentView(R.layout.ui_a);

	}

	@Override
	protected void onHide() {
	}

}
