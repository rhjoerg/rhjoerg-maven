package ch.rhjoerg.maven.starter.core;

import org.junit.jupiter.api.Test;

public class MavenSettingsBuilderLibraryTests extends AbstractLibraryTests
{
	@Test
	public void test() throws Exception
	{
		namedClasses("maven-settings-builder").forEach(c -> System.out.println(c));
	}
}
