package de.rieckpil.blog;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class SpringBootTestingPrimerApplicationTests {

	@Test
	void contextLoads() {
		assertEquals(1, 1);
	}

	@Test
	void testCase02(){
		assertThat("aaa", equalTo("aaa"));
	}

}
