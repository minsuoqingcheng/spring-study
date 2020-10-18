package cn.edu.nju.lc.springstudy.environment;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestPropertySourceTest.class)   //spring 注解驱动测试注解
@TestPropertySource(properties = "user.name = test")
public class TestPropertySourceTest {

    @Value("${user.name}")
    private String username;

    @Test
    public void testUserName() {
        assertEquals("", username, "test");
    }

}
