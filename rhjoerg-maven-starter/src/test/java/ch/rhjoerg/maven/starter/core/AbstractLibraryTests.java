package ch.rhjoerg.maven.starter.core;

import java.net.URL;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import org.codehaus.plexus.PlexusContainer;

import ch.rhjoerg.commons.tool.ExcludingClassLoader;
import ch.rhjoerg.maven.starter.security.EnablePlexusCipher;
import ch.rhjoerg.plexus.starter.test.WithPlexus;

@WithPlexus
@EnablePlexusCipher
@EnableMavenCore
public abstract class AbstractLibraryTests
{
	public final static String NAMED = "META-INF/sisu/javax.inject.Named";
	public final static String COMPONENTS = "META-INF/plexus/components.xml";

	@Inject
	protected PlexusContainer container;

	@Inject
	private ExcludingClassLoader excludingClassLoader;

	protected List<URL> findUrls(String name) throws Exception
	{
		ClassLoader classLoader = excludingClassLoader.getParent();

		return Collections.list(classLoader.getResources(name));
	}
}
