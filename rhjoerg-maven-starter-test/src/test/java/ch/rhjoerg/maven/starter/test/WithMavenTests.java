package ch.rhjoerg.maven.starter.test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.inject.Inject;

import org.junit.jupiter.api.Test;

import com.google.inject.Injector;

@WithMaven
public class WithMavenTests
{
	@Inject
	private Injector injector;

	@Test
	public void test()
	{
		assertNotNull(injector);
	}
}
