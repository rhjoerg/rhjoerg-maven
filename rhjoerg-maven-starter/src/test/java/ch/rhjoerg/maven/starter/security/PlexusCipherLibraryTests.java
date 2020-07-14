package ch.rhjoerg.maven.starter.security;

import javax.inject.Inject;

import org.codehaus.plexus.PlexusContainer;
import org.codehaus.plexus.logging.Logger;
import org.junit.jupiter.api.Test;
import org.sonatype.plexus.components.cipher.PlexusCipher;
import org.sonatype.plexus.components.sec.dispatcher.SecDispatcher;

import ch.rhjoerg.maven.starter.TestConfiguration;
import ch.rhjoerg.plexus.starter.test.WithPlexus;

@WithPlexus(TestConfiguration.class)
public class PlexusCipherLibraryTests
{
	@Inject
	private PlexusContainer container;

	@Test
	public void test() throws Exception
	{
		container.lookup(Logger.class);
		container.lookup(PlexusCipher.class, "default");
		container.lookup(SecDispatcher.class, "default");
	}
}
