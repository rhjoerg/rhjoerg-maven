package ch.rhjoerg.maven.starter.core;

import static com.google.inject.name.Names.named;

import org.apache.maven.artifact.repository.layout.ArtifactRepositoryLayout;
import org.apache.maven.artifact.repository.layout.DefaultRepositoryLayout;
import org.apache.maven.artifact.repository.metadata.io.DefaultMetadataReader;
import org.apache.maven.artifact.repository.metadata.io.MetadataReader;
import org.apache.maven.artifact.resolver.DefaultResolutionErrorHandler;
import org.apache.maven.artifact.resolver.ResolutionErrorHandler;
import org.apache.maven.configuration.BeanConfigurator;
import org.apache.maven.configuration.internal.DefaultBeanConfigurator;
import org.apache.maven.exception.DefaultExceptionHandler;
import org.apache.maven.exception.ExceptionHandler;
import org.apache.maven.execution.MojoExecutionListener;
import org.apache.maven.execution.scope.MojoExecutionScoped;
import org.apache.maven.execution.scope.internal.MojoExecutionScope;
import org.apache.maven.plugin.MojoExecution;
import org.apache.maven.project.MavenProject;

import com.google.inject.AbstractModule;
import com.google.inject.Key;

public class MavenCoreModule extends AbstractModule
{
	private final MojoExecutionScope mojoExecutionScope = new MojoExecutionScope();

	@Override
	protected void configure()
	{
		configureExceptionHandler();
		configureArtifactRepositoryLayout();
		configureMetadataReader();
		configureResolutionErrorHandler();
		configureBeanConfigurator();
		configureMojoExecution();
	}

	// org.apache.maven.exception

	private void configureExceptionHandler()
	{
		Key<DefaultExceptionHandler> key = Key.get(DefaultExceptionHandler.class, named("default"));

		bind(key).to(DefaultExceptionHandler.class);
		bind(Key.get(ExceptionHandler.class, named("default"))).to(key);
	}

	// org.apache.maven.artifact.repository.layout

	private void configureArtifactRepositoryLayout()
	{
		Key<DefaultRepositoryLayout> key = Key.get(DefaultRepositoryLayout.class, named("default"));

		bind(key).to(DefaultRepositoryLayout.class);
		bind(Key.get(ArtifactRepositoryLayout.class, named("default"))).to(key);
	}

	// org.apache.maven.artifact.repository.metadata

	private void configureMetadataReader()
	{
		Key<DefaultMetadataReader> key = Key.get(DefaultMetadataReader.class, named("default"));

		bind(key).to(DefaultMetadataReader.class);
		bind(Key.get(MetadataReader.class, named("default"))).to(key);
	}

	// org.apache.maven.artifact.resolver

	private void configureResolutionErrorHandler()
	{
		Key<DefaultResolutionErrorHandler> key = Key.get(DefaultResolutionErrorHandler.class, named("default"));

		bind(key).to(DefaultResolutionErrorHandler.class);
		bind(Key.get(ResolutionErrorHandler.class, named("default"))).to(key);
	}

	// org.apache.maven.configuration

	private void configureBeanConfigurator()
	{
		Key<DefaultBeanConfigurator> key = Key.get(DefaultBeanConfigurator.class, named("default"));

		bind(key).to(DefaultBeanConfigurator.class);
		bind(Key.get(BeanConfigurator.class, named("default"))).to(key);
	}

	private void configureMojoExecution()
	{
		bindScope(MojoExecutionScoped.class, mojoExecutionScope);
		bind(MojoExecutionScope.class).toInstance(mojoExecutionScope);

		bind(MavenProject.class).toProvider(MojoExecutionScope.<MavenProject>seededKeyProvider()).in(mojoExecutionScope);
		bind(MojoExecution.class).toProvider(MojoExecutionScope.<MojoExecution>seededKeyProvider()).in(mojoExecutionScope);

		bind(MojoExecutionListener.class).toInstance(mojoExecutionScope);
	}
}
