package ananas.lib.blueprint.elements.awt;

import java.awt.Container;

public interface IEContainer extends IEComponent {

	Container toContainer();

	public static class Wrapper extends IEComponent.Wrapper implements
			IEContainer {

		@Override
		public Container toContainer() {
			return (Container) this.getTarget(true);
		}
	}

}
