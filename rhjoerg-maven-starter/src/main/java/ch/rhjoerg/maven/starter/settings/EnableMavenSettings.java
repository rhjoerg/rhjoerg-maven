package ch.rhjoerg.maven.starter.settings;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import ch.rhjoerg.plexus.starter.annotation.PlexusPackages;

@Documented
@Retention(RUNTIME)
@Target(TYPE)
@Inherited
@PlexusPackages({ "org.apache.maven.settings.validation" })
public @interface EnableMavenSettings
{

}
