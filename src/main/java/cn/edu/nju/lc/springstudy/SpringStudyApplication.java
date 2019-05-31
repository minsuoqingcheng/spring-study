package cn.edu.nju.lc.springstudy;

import cn.edu.nju.lc.springstudy.properties.Person;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpringStudyApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(SpringStudyApplication.class, args);
		Person person = context.getBean(Person.class);
		System.out.println(person.toString());
	}

}
