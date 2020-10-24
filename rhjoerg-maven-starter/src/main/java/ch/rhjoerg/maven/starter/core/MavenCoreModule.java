package ch.rhjoerg.maven.starter.core;

import static com.google.inject.name.Names.named;

import org.apache.maven.SessionScoped;
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
import org.apache.maven.execution.MavenSession;
import org.apache.maven.execution.MojoExecutionListener;
import org.apache.maven.execution.scope.MojoExecutionScoped;
import org.apache.maven.execution.scope.internal.MojoExecutionScope;
import org.apache.maven.plugin.LegacySupport;
import org.apache.maven.plugin.MojoExecution;
import org.apache.maven.plugin.internal.DefaultLegacySupport;
import org.apache.maven.project.DefaultProjectRealmCache;
import org.apache.maven.project.MavenProject;
import org.apache.maven.project.ProjectRealmCache;
import org.apache.maven.rtinfo.RuntimeInformation;
import org.apache.maven.rtinfo.internal.DefaultRuntimeInformation;
import org.apache.maven.session.scope.internal.SessionScope;
import org.apache.maven.toolchain.DefaultToolchainsBuilder;
import org.apache.maven.toolchain.ToolchainFactory;
import org.apache.maven.toolchain.ToolchainsBuilder;
import org.apache.maven.toolchain.java.JavaToolchainFactory;

import com.google.inject.AbstractModule;
import com.google.inject.Key;

@SuppressWarnings("deprecation")
public class MavenCoreModule extends AbstractModule
{
	private final SessionScope sessionScope = new SessionScope();
	private final MojoExecutionScope mojoExecutionScope = new MojoExecutionScope();

	@Override
	protected void configure()
	{
		configureRuntimeInformation();
		configureExceptionHandler();
		configureArtifactRepositoryLayout();
		configureMetadataReader();
		configureResolutionErrorHandler();
		configureBeanConfigurator();
		configureLegacySupport();
		configureProjectRealmCache();
		configureToolchainsBuilder();
		configureToolchainFactory();
		configureSessionScope();
		configureMojoExecution();
	}

	private void configureRuntimeInformation()
	{
		Key<DefaultRuntimeInformation> key = Key.get(DefaultRuntimeInformation.class, named("default"));

		bind(key).to(DefaultRuntimeInformation.class);
		bind(Key.get(RuntimeInformation.class, named("default"))).to(key);
	}

	private void configureExceptionHandler()
	{
		Key<DefaultExceptionHandler> key = Key.get(DefaultExceptionHandler.class, named("default"));

		bind(key).to(DefaultExceptionHandler.class);
		bind(Key.get(ExceptionHandler.class, named("default"))).to(key);
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

	private void configureResolutionErrorHandler()
	{
		Key<DefaultResolutionErrorHandler> key = Key.get(DefaultResolutionErrorHandler.class, named("default"));

		bind(key).to(DefaultResolutionErrorHandler.class);
		bind(Key.get(ResolutionErrorHandler.class, named("default"))).to(key);
	}

	private void configureBeanConfigurator()
	{
		Key<DefaultBeanConfigurator> key = Key.get(DefaultBeanConfigurator.class, named("default"));

		bind(key).to(DefaultBeanConfigurator.class);
		bind(Key.get(BeanConfigurator.class, named("default"))).to(key);
	}

	private void configureLegacySupport()
	{
		Key<DefaultLegacySupport> key = Key.get(DefaultLegacySupport.class, named("default"));

		bind(key).to(DefaultLegacySupport.class);
		bind(Key.get(LegacySupport.class, named("default"))).to(key);
	}

	private void configureProjectRealmCache()
	{
		Key<DefaultProjectRealmCache> key = Key.get(DefaultProjectRealmCache.class, named("default"));

		bind(key).to(DefaultProjectRealmCache.class);
		bind(Key.get(ProjectRealmCache.class, named("default"))).to(key);
	}

	private void configureToolchainsBuilder()
	{
		Key<DefaultToolchainsBuilder> key = Key.get(DefaultToolchainsBuilder.class, named("default"));

		bind(key).to(DefaultToolchainsBuilder.class);
		bind(Key.get(ToolchainsBuilder.class, named("default"))).to(key);
	}

	private void configureToolchainFactory()
	{
		Key<JavaToolchainFactory> key = Key.get(JavaToolchainFactory.class, named("jdk"));

		bind(key).to(JavaToolchainFactory.class);
		bind(Key.get(ToolchainFactory.class, named("jdk"))).to(key);
	}

	private void configureSessionScope()
	{
		bindScope(SessionScoped.class, sessionScope);
		bind(SessionScope.class).toInstance(sessionScope);

		bind(MavenSession.class).toProvider(SessionScope.<MavenSession>seededKeyProvider()).in(sessionScope);
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
