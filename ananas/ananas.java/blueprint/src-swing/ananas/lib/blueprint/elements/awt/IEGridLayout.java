package ananas.lib.blueprint.elements.awt;

import java.awt.LayoutManager;

public interface IEGridLayout extends IEObject, IELayoutManager {

	public static class Wrapper extends IEObject.Wrapper implements
			IEGridLayout {

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
