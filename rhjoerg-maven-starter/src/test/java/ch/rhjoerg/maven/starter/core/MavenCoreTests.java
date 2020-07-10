package ch.rhjoerg.maven.starter.core;

import java.net.URL;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import org.codehaus.plexus.PlexusContainer;
import org.junit.jupiter.api.Test;

import ch.rhjoerg.commons.tool.ExcludingClassLoader;
import ch.rhjoerg.plexus.starter.test.WithPlexus;

@WithPlexus
public class MavenCoreTests
{
	@SuppressWarnings("unused")
	@Inject
	private PlexusContainer container;

	@Inject
	private ExcludingClassLoader excludingClassLoader;

	@Test
	public void test() throws Exception
	{
		ClassLoader classLoader = excludingClassLoader.getParent();
		List<URL> nameds = Collections.list(classLoader.getResources("META-INF/sisu/javax.inject.Named"));

		nameds.forEach(url -> System.out.println(url));
	}
}
