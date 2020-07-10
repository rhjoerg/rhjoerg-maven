package ch.rhjoerg.maven.starter.core;

import javax.inject.Inject;

import org.codehaus.plexus.PlexusContainer;
import org.junit.jupiter.api.Test;

import ch.rhjoerg.plexus.starter.test.WithPlexus;

@WithPlexus
public class MavenCoreTests
{
	@SuppressWarnings("unused")
	@Inject
	private PlexusContainer container;

	@Test
	public void test() throws Exception
	{

	}
}
