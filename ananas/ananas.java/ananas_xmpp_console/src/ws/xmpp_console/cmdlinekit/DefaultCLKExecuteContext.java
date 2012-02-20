package ws.xmpp_console.cmdlinekit;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;

public class DefaultCLKExecuteContext implements CLKExecuteContext {

	private CLKCommand command;
	private CLKCommandSet commandSet;
	private CLKElementsFactory elementsFactory;
	private CLKRunLoop runLoop;
	private PrintStream out;
	private InputStream in;
	private CLKParameterList paramList;

	public DefaultCLKExecuteContext() {
		this.elementsFactory = DefaultCLKElementsFactory.getFactory();
		this.out = System.out;
		this.in = System.in;
	}

	public DefaultCLKExecuteContext(CLKExecuteContext p) {
		this.command = p.getCommand();
		this.commandSet = p.getCommandSet();
		this.elementsFactory = p.getElementsFactory();
		this.runLoop = p.getRunLoop();
		this.in = p.getInput();
		this.out = p.getPrint();
		this.paramList = p.getParameterList();
	}

	@Override
	public CLKCommand getCommand() {
		return this.command;
	}

	@Override
	public CLKCommandSet getCommandSet() {
		return this.commandSet;
	}

	@Override
	public CLKElementsFactory getElementsFactory() {
		return this.elementsFactory;
	}

	@Override
	public CLKRunLoop getRunLoop() {
		return this.runLoop;
	}

	@Override
	public InputStream getInput() {
		return this.in;
	}

	@Override
	public OutputStream getOutput() {
		return this.out;
	}

	@Override
	public PrintStream getPrint() {
		return this.out;
	}

	@Override
	public CLKParameterList getParameterList() {
		return this.paramList;
	}

}
