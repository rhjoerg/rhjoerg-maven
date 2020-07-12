package ch.rhjoerg.maven.starter.core;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.net.URL;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import org.codehaus.plexus.PlexusContainer;
import org.codehaus.plexus.component.annotations.Component;
import org.codehaus.plexus.component.repository.exception.ComponentLookupException;

import ch.rhjoerg.commons.io.Read;
import ch.rhjoerg.commons.tool.ExcludingClassLoader;
import ch.rhjoerg.maven.starter.security.EnablePlexusCipher;
import ch.rhjoerg.plexus.starter.dependency.ComponentParser;
import ch.rhjoerg.plexus.starter.dependency.ComponentParser.Descriptor;
import ch.rhjoerg.plexus.starter.dependency.NamedParser;
import ch.rhjoerg.plexus.starter.test.WithPlexus;

@WithPlexus
@EnablePlexusCipher
public abstract class AbstractLibraryTests
{
	public final static String NAMED = "META-INF/sisu/javax.inject.Named";
	public final static String COMPONENTS = "META-INF/plexus/components.xml";

	@Inject
	protected PlexusContainer container;

	@Inject
	private ExcludingClassLoader excludingClassLoader;

	protected void testNamedClasses(String library) throws Exception
	{
		List<Class<?>> entries = namedClasses(library);
		boolean error = false;

		for (Class<?> entry : entries)
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
		List<Descriptor> entries = componentEntries(library);
		boolean error = false;

		for (Descriptor entry : entries)
		{
			Component component = entry.component();

			if (!testComponent(component))
			{
				error = true;
				System.err.println("missing component: " + component.role() + " (" + component.hint() + ")");
			}
		}

		assertFalse(error);
	}

	protected boolean testComponent(Component component)
	{
		try
		{
			container.lookup(component.role(), component.hint());

			return true;
		}
		catch (ComponentLookupException e)
		{
			return false;
		}
	}

	protected List<Class<?>> namedClasses(String library) throws Exception
	{
		URL url = findUrl(NAMED, library);
		String src = Read.string(url, UTF_8);
		NamedParser parser = new NamedParser(excludingClassLoader);

		return parser.parse(src);
	}

	protected List<Descriptor> componentEntries(String library) throws Exception
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
