package ch.rhjoerg.maven.starter.core;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import ch.rhjoerg.maven.starter.model.EnableMavenModel;
import ch.rhjoerg.plexus.starter.annotation.PlexusModules;

@Documented
@Retention(RUNTIME)
@Target(TYPE)
@Inherited
@EnableMavenModel
@PlexusModules(MavenCoreModule.class)
public @interface EnableMavenCore
{
}
