package ch.rhjoerg.maven.starter.model;

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
@PlexusPackages({ "org.apache.maven.model.composition", "org.apache.maven.model.inheritance", "org.apache.maven.model.locator",
		"org.apache.maven.model.normalization", "org.apache.maven.model.validation" })
public @interface EnableMavenModel
{
}
