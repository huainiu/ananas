package ananas.app.roadmap.util.task;

import java.io.File;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import ananas.app.roadmap.util.RoadmapFileManager;
import ananas.app.roadmap.util.Task;

public class TaskLoadKML implements Task {

	@Override
	public void run() {
		File dir = RoadmapFileManager.Factory.getInstance().getKmlDirectory();
		if (!dir.exists()) {
			dir.mkdirs();
		}
		this._loadKmlFilesInDir(dir, 6);
	}

	private void _loadKmlFilesInDir(File dir, int depthLimit) {
		if (depthLimit <= 0) {
			System.err.println(this
					+ "._loadKmlFilesInDir() - dir is too deep!");
			return;
		}
		for (final File ch : dir.listFiles()) {
			if (ch.exists()) {
				if (ch.isDirectory()) {
					this._loadKmlFilesInDir(ch, depthLimit - 1);
				} else {
					this._loadKmlFile(ch);
				}
			}
		}
	}

	private void _loadKmlFile(File kmlfile) {

		String name = kmlfile.getName().toLowerCase();
		if (!name.endsWith(".kml")) {
			return;
		}
		System.out.println("load " + kmlfile.getAbsolutePath());

		try {
			KMLObjectTreeBuilder builder = KMLObjectTreeBuilder.Factory
					.newInstance();
			SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
			parser.parse(kmlfile, builder.getHandler());

		} catch (Exception e) {

			e.printStackTrace();
		}

	}
}
