package ananas.lib.blueprint.elements.swing;

import javax.swing.JFrame;

import ananas.lib.blueprint.elements.awt.IEFrame;

public interface IEJFrame extends IEFrame {

	public static class Wrapper extends IEFrame.Wrapper implements IEJFrame {

		@Override
		public Object createTarget() {
			return new JFrame();
		}

		@Override
		public void tagEnd() {

			super.tagEnd();

			// Frame frame = (Frame) this.getTarget(true);

		}

	}
}
