package ananas.lib.blueprint.elements.awt;

import java.awt.LayoutManager;

public interface IEBorderLayout extends IEObject, IELayoutManager {

	public static class Wrapper extends IEObject.Wrapper implements
			IEBorderLayout {

		@Override
		public LayoutManager toLayoutManager() {
			return (LayoutManager) this.getTarget(true);
		}

		@Override
		public void addComponentToContainer(IEComponent component,
				IEContainer container, IE_position position) {

			container.toContainer().add(component.toComponent());

		}
	}
}
