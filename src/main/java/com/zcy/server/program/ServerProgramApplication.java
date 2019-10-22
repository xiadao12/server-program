package com.zcy.server.program;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ServerProgramApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(ServerProgramApplication.class, args);

        //GuoPeiSchedule guoPeiSchedule = new GuoPeiSchedule();
        //guoPeiSchedule.execute();

        //JijiSchedule jijiSchedule = new JijiSchedule();
        //jijiSchedule.execute();
    }


    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ServerProgramApplication.class);
    }

}
