package ch.rhjoerg.maven.starter.core;

import javax.inject.Inject;

import org.apache.maven.artifact.repository.layout.ArtifactRepositoryLayout;
import org.apache.maven.artifact.repository.layout.DefaultRepositoryLayout;
import org.apache.maven.artifact.repository.metadata.io.DefaultMetadataReader;
import org.apache.maven.artifact.repository.metadata.io.MetadataReader;
import org.codehaus.plexus.PlexusContainer;
import org.junit.jupiter.api.Test;

import ch.rhjoerg.maven.starter.TestConfiguration;
import ch.rhjoerg.plexus.starter.test.WithPlexus;

@WithPlexus(TestConfiguration.class)
public class MavenCoreModuleTests
{
	@Inject
	private PlexusContainer container;

	@Test
	public void testArtifactRepositoryLayout() throws Exception
	{
		container.lookup(DefaultRepositoryLayout.class, "default");
		container.lookup(ArtifactRepositoryLayout.class, "default");
	}

	@Test
	public void testMetadataReader() throws Exception
	{
		container.lookup(DefaultMetadataReader.class, "default");
		container.lookup(MetadataReader.class, "default");
	}
}
