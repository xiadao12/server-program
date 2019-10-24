package com.zcy.server.program.test;

import com.zcy.server.program.jiji.schedule.JijiSchedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yangzhouchuan
 * @version 1.0
 * @date 2019-09-24 14:58
 */
@RestController
@CrossOrigin
@RequestMapping("/test")
public class TestController {

    @Autowired
    JijiSchedule jijiSchedule;

    @GetMapping("/jiji")
    public void test() {
        // jijiSchedule.execute();
        //jijiSchedule.receivePoint();
    }
}
