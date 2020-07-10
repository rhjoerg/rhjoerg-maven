package ch.rhjoerg.maven.starter.core;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.net.URL;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import org.codehaus.plexus.PlexusContainer;
import org.codehaus.plexus.component.annotations.Component;

import ch.rhjoerg.commons.io.Read;
import ch.rhjoerg.commons.tool.ExcludingClassLoader;
import ch.rhjoerg.maven.starter.security.EnablePlexusCipher;
import ch.rhjoerg.plexus.starter.test.WithPlexus;
import ch.rhjoerg.plexus.starter.test.component.ComponentEntry;
import ch.rhjoerg.plexus.starter.test.component.ComponentParser;
import ch.rhjoerg.plexus.starter.test.component.NamedParser;

@WithPlexus
@EnablePlexusCipher
public abstract class AbstractLibraryTests
{
	public final static String NAMED = "META-INF/sisu/javax.inject.Named";
	public final static String COMPONENTS = "META-INF/plexus/components.xml";

	@Inject
	private PlexusContainer container;

	@Inject
	private ExcludingClassLoader excludingClassLoader;

	protected void testNamedClasses(String library) throws Exception
	{
		List<String> entries = namedClasses(library);
		boolean error = false;

		for (String entry : entries)
		{
			if (!container.hasComponent(entry))
			{
				error = true;
				System.err.println("missing named entry: " + entry);
			}
		}

		assertFalse(error);
	}

	protected void testComponents(String library) throws Exception
	{
		List<ComponentEntry> entries = componentEntries(library);
		boolean error = false;

		for (ComponentEntry entry : entries)
		{
			Component component = entry.component();

			if (!container.hasComponent(component.role(), component.hint()))
			{
				error = true;
				System.err.println("missing component: " + component.role() + " (" + component.hint() + ")");
			}
		}

		assertFalse(error);
	}

	protected List<String> namedClasses(String library) throws Exception
	{
		URL url = findUrl(NAMED, library);
		String src = Read.string(url, UTF_8);
		NamedParser parser = new NamedParser();

		return parser.parse(src);
	}

	protected List<ComponentEntry> componentEntries(String library) throws Exception
	{
		URL url = findUrl(COMPONENTS, library);
		String xml = Read.string(url, UTF_8);
		ComponentParser parser = new ComponentParser(excludingClassLoader);

		return parser.parse(xml);
	}

	protected URL findUrl(String name, String library) throws Exception
	{
		List<URL> urls = findUrls(name);

		return urls.stream().filter(u -> u.toString().contains(library)).findFirst().get();
	}

	protected List<URL> findUrls(String name) throws Exception
	{
		ClassLoader classLoader = excludingClassLoader.getParent();

		return Collections.list(classLoader.getResources(name));
	}
}
