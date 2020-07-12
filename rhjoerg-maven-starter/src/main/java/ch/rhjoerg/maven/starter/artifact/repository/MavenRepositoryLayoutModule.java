package ch.rhjoerg.maven.starter.artifact.repository;

import static com.google.inject.name.Names.named;

import javax.inject.Named;

import org.apache.maven.artifact.repository.layout.ArtifactRepositoryLayout;
import org.apache.maven.artifact.repository.layout.DefaultRepositoryLayout;

import com.google.inject.AbstractModule;
import com.google.inject.Key;

@Named
public class MavenRepositoryLayoutModule extends AbstractModule
{
	@Override
	protected void configure()
	{
		bind(MavenRepositoryLayoutModule.class).toInstance(this);

		Key<DefaultRepositoryLayout> key = Key.get(DefaultRepositoryLayout.class, named("default"));

		bind(key).to(DefaultRepositoryLayout.class);
		bind(Key.get(ArtifactRepositoryLayout.class, named("default"))).to(key);
	}
}
