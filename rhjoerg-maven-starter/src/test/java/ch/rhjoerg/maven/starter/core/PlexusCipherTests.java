package ch.rhjoerg.maven.starter.core;

import static java.nio.charset.StandardCharsets.UTF_8;

import java.net.URL;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import org.codehaus.plexus.PlexusContainer;
import org.junit.jupiter.api.Test;

import ch.rhjoerg.commons.io.Read;
import ch.rhjoerg.commons.tool.ExcludingClassLoader;
import ch.rhjoerg.plexus.starter.annotation.PlexusPackages;
import ch.rhjoerg.plexus.starter.test.WithPlexus;
import ch.rhjoerg.plexus.starter.test.component.ComponentEntry;
import ch.rhjoerg.plexus.starter.test.component.ComponentParser;
import ch.rhjoerg.plexus.starter.test.component.NamedParser;

@WithPlexus
@PlexusPackages("org.sonatype.plexus.components.cipher")
public class PlexusCipherTests
{
	@Inject
	private PlexusContainer container;

	@Inject
	private ExcludingClassLoader excludingClassLoader;

	@Test
	public void test1() throws Exception
	{
		ClassLoader classLoader = excludingClassLoader.getParent();
		List<URL> urls = Collections.list(classLoader.getResources("META-INF/sisu/javax.inject.Named"));
		URL url = urls.stream().filter(u -> u.toString().contains("plexus-cipher")).findFirst().get();
		String src = Read.string(url, UTF_8);
		NamedParser parser = new NamedParser();
		List<String> entries = parser.parse(src);

		for (String entry : entries)
		{
			if (container.hasComponent(entry))
			{
				continue;
			}

			System.out.println("missing component: " + entry);
		}
	}

	@Test
	public void test2() throws Exception
	{
		ClassLoader classLoader = excludingClassLoader.getParent();
		List<URL> urls = Collections.list(classLoader.getResources("META-INF/plexus/components.xml"));
		URL url = urls.stream().filter(u -> u.toString().contains("plexus-cipher")).findFirst().get();
		String xml = Read.string(url, UTF_8);
		ComponentParser parser = new ComponentParser(excludingClassLoader);
		List<ComponentEntry> entries = parser.parse(xml);

		for (ComponentEntry entry : entries)
		{
			if (!container.hasComponent(entry.component().role(), entry.component().hint()))
			{
				System.out.println("missing component: " + entry.component().role());
			}
		}
	}
}
