package ch.rhjoerg.maven.starter.core;

import java.net.URL;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

public class LibrariesTests extends AbstractLibraryTests
{
	private static final Set<String> TREATED_LIBRARIES = Set.of("org.eclipse.sisu.plexus", "plexus-cipher", "plexus-sec-dispatcher");

	@Test
	public void testNamed() throws Exception
	{
		List<URL> urls = findUrls(NAMED);

		urls.forEach(url -> testUrl(url));
	}

	@Test
	public void testComponents() throws Exception
	{
		List<URL> urls = findUrls(COMPONENTS);

		urls.forEach(url -> testUrl(url));
	}

	private void testUrl(URL url)
	{
		String urlString = url.toString();

		for (String library : TREATED_LIBRARIES)
		{
			if (urlString.contains(library))
			{
				return;
			}
		}

		System.err.println("Untreated: " + urlString);
	}
}
