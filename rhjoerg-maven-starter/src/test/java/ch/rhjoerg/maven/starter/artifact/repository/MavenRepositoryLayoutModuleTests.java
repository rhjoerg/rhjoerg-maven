package ch.rhjoerg.maven.starter.artifact.repository;

import org.apache.maven.artifact.repository.layout.ArtifactRepositoryLayout;
import org.apache.maven.artifact.repository.layout.DefaultRepositoryLayout;
import org.junit.jupiter.api.Test;

import ch.rhjoerg.maven.starter.core.AbstractLibraryTests;

public class MavenRepositoryLayoutModuleTests extends AbstractLibraryTests
{
	@Test
	public void test() throws Exception
	{
		container.lookup(DefaultRepositoryLayout.class, "default");
		container.lookup(ArtifactRepositoryLayout.class, "default");
	}
}
