package com.example.DisplayCourses;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.DisplayCourses.Domain.Course;
import com.example.DisplayCourses.Domain.CourseRepository;
import com.example.DisplayCourses.Domain.Teacher;
import com.example.DisplayCourses.Domain.TeacherRepository;

@SpringBootApplication
public class DisplayCoursesApplication {

	private static final Logger log = LoggerFactory.getLogger(DisplayCoursesApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(DisplayCoursesApplication.class, args);
	}

	@Bean
	public CommandLineRunner courseDemo(CourseRepository crepository, TeacherRepository trepository) {
		
		return (args) -> {
			//Adding teachers to TeacherRepository
			log.info("save some teachers");
			trepository.save(new Teacher("Jukka Juslin"));
			trepository.save(new Teacher("Juha Hinkula"));
			trepository.save(new Teacher("Kari Silpio"));
			
			//adding demo course data in repository
			log.info("save a couple of courses");
			crepository.save(new Course("OSE", "SWD4TF043-3034", trepository.findByName("Kari Silpio").get(0)));
			crepository.save(new Course("Front End Development", "SWD4TF022-3006", trepository.findByName("Juha Hinkula").get(0)));
			crepository.save(new Course("Server Programming", "SWD4TF021-3007", trepository.findByName("Jukka Juslin").get(0)));
		
			//fetching courses
			log.info("fetch all courses");
			for(Course course : crepository.findAll()) {
				log.info(course.toString());
			}
		};
	}
}
