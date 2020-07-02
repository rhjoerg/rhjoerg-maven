package ch.rhjoerg.maven.starter.test;

import java.util.List;

import org.junit.jupiter.api.extension.ExtensionContext;

import ch.rhjoerg.plexus.starter.test.PlexusExtension;

public class MavenExtension extends PlexusExtension
{
	@Override
	protected PlexusTestConfigurationClassVisitor createClassVisitor(ExtensionContext context)
	{
		return new MavenTestConfigurationClassVisitor();
	}

	public static class MavenTestConfigurationClassVisitor extends PlexusTestConfigurationClassVisitor
	{
		@Override
		public boolean visitClass(Class<?> type)
		{
			WithMaven withMaven = type.getAnnotation(WithMaven.class);

			if (withMaven != null)
			{
				result.addAll(List.of(withMaven.value()));
			}

			if (result.isEmpty())
			{
				return super.visitClass(type);
			}

			return false;
		}
	}
}
