package ch.rhjoerg.maven.starter.core;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.inject.Inject;

import org.codehaus.plexus.PlexusContainer;
import org.junit.jupiter.api.Test;

import ch.rhjoerg.commons.function.ThrowingConsumer;
import ch.rhjoerg.commons.tool.ExcludingClassLoader;
import ch.rhjoerg.maven.starter.TestConfiguration;
import ch.rhjoerg.plexus.core.util.Keys;
import ch.rhjoerg.plexus.starter.dependency.Dependencies;
import ch.rhjoerg.plexus.starter.dependency.Dependencies.Entry;
import ch.rhjoerg.plexus.starter.test.WithPlexus;

@WithPlexus(TestConfiguration.class)
public class MavenDependenciesTests
{
	public final static String NAMED = "META-INF/sisu/javax.inject.Named";
	public final static String COMPONENTS = "META-INF/plexus/components.xml";

	@Inject
	private PlexusContainer container;

	@Inject
	private ExcludingClassLoader excludingClassLoader;

	protected List<URL> findUrls(String name) throws Exception
	{
		ClassLoader classLoader = excludingClassLoader.getParent();

		return Collections.list(classLoader.getResources(name));
	}

	@Test
	public void test() throws Exception
	{
		Dependencies dependencies = new Dependencies(container);

		findUrls(NAMED).forEach(ThrowingConsumer.wrap(url -> dependencies.addNameds(url)));
		findUrls(COMPONENTS).forEach(ThrowingConsumer.wrap(url -> dependencies.addComponents(url)));

		List<Entry> resolvable = dependencies.resolvable();

		System.out.println("-- MavenDependenciesTests --");
		System.out.println("entries: " + dependencies.size());
		System.out.println("unresolvable: " + dependencies.unresolvable().size());
		System.out.println("resolvable: " + resolvable.size());

		Map<String, List<Entry>> map = new TreeMap<>();

		for (Entry entry : resolvable)
		{
			String pkg = Keys.type(entry.key).getPackageName();
			List<Entry> list = map.get(pkg);

			if (list == null)
			{
				map.put(pkg, list = new ArrayList<>());
			}

			list.add(entry);
		}

		List<String> pkgs = new ArrayList<>(map.keySet());

		Comparator<String> sizeComp = (s1, s2) -> map.get(s1).size() - map.get(s2).size();
		Comparator<String> nameComp = (s1, s2) -> s1.compareTo(s2);
		Comparator<String> comp = sizeComp.thenComparing(nameComp);

		Collections.sort(pkgs, comp);

		String pkg = pkgs.get(0);
		System.out.println("proposed package: " + pkg);

		map.get(pkg).forEach(entry -> System.out.println("- " + entry.key + " (" + entry.source + ")"));
	}
}
