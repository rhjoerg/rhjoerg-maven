package ch.rhjoerg.maven.starter.security;

import org.codehaus.plexus.logging.Logger;
import org.junit.jupiter.api.Test;
import org.sonatype.plexus.components.cipher.PlexusCipher;
import org.sonatype.plexus.components.sec.dispatcher.SecDispatcher;

import ch.rhjoerg.maven.starter.core.AbstractLibraryTests;

public class PlexusCipherLibraryTests extends AbstractLibraryTests
{
	@Test
	public void test() throws Exception
	{
		container.lookup(Logger.class);
		container.lookup(PlexusCipher.class, "default");
		container.lookup(SecDispatcher.class, "default");
	}
}
