package com.thomas.sfgpetclinic.sfgpetclinic;

import com.thomas.sfgpetclinic.sfgpetclinic.controller.MyController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SfgpetclinicApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(SfgpetclinicApplication.class, args);

		MyController myController = (MyController) ctx.getBean("myController");

		String greeting = myController.syaHello();

		System.out.println(greeting);
	}

}
