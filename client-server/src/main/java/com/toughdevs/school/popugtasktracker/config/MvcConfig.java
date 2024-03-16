package com.toughdevs.school.popugtasktracker.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/dashboard").setViewName("dashboard");
		registry.addViewController("/tasksAdd").setViewName("tasksAdd");
		registry.addViewController("/tasksList").setViewName("tasksList");
		registry.addViewController("/").setViewName("dashboard");
	}

}
