package ananas.lib.blueprint.elements.awt;

import java.awt.Container;

import ananas.lib.blueprint.IElement;

public interface IEContainer extends IEComponent {

	Container toContainer();

	public static class Wrapper extends IEComponent.Wrapper implements
			IEContainer {

		@Override
		public Container toContainer() {
			return (Container) this.getTarget(true);
		}

		@Override
		public boolean appendChild(IElement element) {

			if (element == null) {
				return false;

			} else if (element instanceof IEComponent) {
				IEComponent comp = (IEComponent) element;
				this.toContainer().add(comp.toComponent());

			} else {
				return super.appendChild(element);

			}
			return true;
		}

	}

}
