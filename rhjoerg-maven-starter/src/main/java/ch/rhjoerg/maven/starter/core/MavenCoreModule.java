package ch.rhjoerg.maven.starter.core;

import static com.google.inject.name.Names.named;

import javax.inject.Named;

import org.apache.maven.artifact.repository.layout.ArtifactRepositoryLayout;
import org.apache.maven.artifact.repository.layout.DefaultRepositoryLayout;
import org.apache.maven.artifact.repository.metadata.io.DefaultMetadataReader;
import org.apache.maven.artifact.repository.metadata.io.MetadataReader;

import com.google.inject.AbstractModule;
import com.google.inject.Key;

@Named
public class MavenCoreModule extends AbstractModule
{
	@Override
	protected void configure()
	{
		bind(MavenCoreModule.class).toInstance(this);

		configureArtifactRepositoryLayout();
		configureMetadataReader();
	}

	private void configureArtifactRepositoryLayout()
	{
		Key<DefaultRepositoryLayout> key = Key.get(DefaultRepositoryLayout.class, named("default"));

		bind(key).to(DefaultRepositoryLayout.class);
		bind(Key.get(ArtifactRepositoryLayout.class, named("default"))).to(key);
	}

	private void configureMetadataReader()
	{
		Key<DefaultMetadataReader> key = Key.get(DefaultMetadataReader.class, named("default"));

		bind(key).to(DefaultMetadataReader.class);
		bind(Key.get(MetadataReader.class, named("default"))).to(key);
	}
}
