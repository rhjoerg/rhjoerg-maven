package ch.rhjoerg.maven.starter.test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.inject.Inject;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.google.inject.Injector;

@ExtendWith(MavenExtension.class)
public class MavenExtensionTests
{
	@Inject
	private Injector injector;

	@Test
	public void test()
	{
		assertNotNull(injector);
	}
}
