package ch.rhjoerg.maven.starter.security;

import org.junit.jupiter.api.Test;

import ch.rhjoerg.maven.starter.core.AbstractLibraryTests;

public class PlexusCipherTests extends AbstractLibraryTests
{
	@Test
	public void test() throws Exception
	{
		testNamedClasses("plexus-cipher");
		testComponents("plexus-cipher");
	}
}
