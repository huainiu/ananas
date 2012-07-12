package jananas.json.impl;

import jananas.json.JSONSerializer;
import jananas.json.JSONSerializerFactory;
import jananas.json.object.JSON_object;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

class JSONSerializerFactoryImpl implements JSONSerializerFactory {

	@Override
	public JSONSerializer createSerializer() {

		return new MyImpl();

	}

	class MyImpl implements JSONSerializer {

		@Override
		public void serialize(JSON_object obj, Writer writer) {
			try {
				writer.write(this.serialize(obj));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		@Override
		public void serialize(JSON_object obj, OutputStream os) {
			try {
				OutputStreamWriter writer = new OutputStreamWriter(os, "utf-8");
				this.serialize(obj, writer);
				writer.flush();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		@Override
		public String serialize(JSON_object obj) {
			return obj.toString();
		}
	}
}
