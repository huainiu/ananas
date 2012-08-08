package ananas.lib.blueprint.impl;

import ananas.lib.blueprint.IDocumentBuilder;
import ananas.lib.blueprint.IDocumentBuilderFactory;
import ananas.lib.blueprint.IImplementation;

class ImplDocumentBuilderFactory implements IDocumentBuilderFactory {

	@Override
	public IDocumentBuilder createDocumentBuilder(IImplementation impl) {

		impl.getNamespaceRegistrar().loadNamespace(
				ananas.lib.blueprint.elements.base.NamespaceLoader.class);

		return new ImplDocumentBuilder(impl);
	}

}
