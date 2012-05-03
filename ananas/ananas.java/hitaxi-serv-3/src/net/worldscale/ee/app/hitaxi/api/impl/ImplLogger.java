package net.worldscale.ee.app.hitaxi.api.impl;

import java.util.Vector;

import net.worldscale.ee.app.hitaxi.api.ILogger;

public class ImplLogger implements ILogger {

	final Vector<String> mList = new Vector<String>();

	@Override
	public void log(String line) {
		this.mList.addElement(line);
	}

	@Override
	public String[] listAll() {
		return this.mList.toArray(new String[this.mList.size()]);
	}

}
