package ch.rhjoerg.maven.starter.core;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import ch.rhjoerg.plexus.starter.annotation.PlexusConfigurations;

@Documented
@Retention(RUNTIME)
@Target(TYPE)
@Inherited
@PlexusConfigurations(MavenCoreModule.class)
public @interface EnableMavenCore
{

}
