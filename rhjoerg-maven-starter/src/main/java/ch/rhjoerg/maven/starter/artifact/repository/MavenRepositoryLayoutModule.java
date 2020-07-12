package ch.rhjoerg.maven.starter.artifact.repository;

import javax.inject.Named;

import org.apache.maven.artifact.repository.layout.DefaultRepositoryLayout;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

@Named
public class MavenRepositoryLayoutModule extends AbstractModule
{
	@Override
	protected void configure()
	{
		bind(DefaultRepositoryLayout.class).annotatedWith(Names.named("default")).to(DefaultRepositoryLayout.class);
	}
}
