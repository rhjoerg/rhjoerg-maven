package ch.rhjoerg.maven.starter.core;

import javax.inject.Inject;

import org.codehaus.plexus.PlexusContainer;
import org.junit.jupiter.api.Test;

import ch.rhjoerg.commons.function.ThrowingConsumer;
import ch.rhjoerg.plexus.starter.dependency.Dependencies;

public class MavenDependenciesTests extends AbstractLibraryTests
{
	@Inject
	private PlexusContainer container;

	@Test
	public void test() throws Exception
	{
		Dependencies dependencies = new Dependencies(container);

		findUrls(NAMED).forEach(ThrowingConsumer.wrap(url -> dependencies.addNameds(url)));
	}
}
