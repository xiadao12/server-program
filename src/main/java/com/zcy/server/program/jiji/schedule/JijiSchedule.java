package com.zcy.server.program.jiji.schedule;

import com.zcy.server.program.common.service.EmailService;
import com.zcy.server.program.common.service.FileService;
import com.zcy.server.program.common.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 几鸡
 *
 * @author yangzhouchuan
 * @version 1.0
 * @date 2019-10-16 16:16
 */
@Component
public class JijiSchedule {

    @Autowired
    private EmailService emailService;

    @Autowired
    private RequestService requestService;

    @Autowired
    private FileService fileService;

    public static void main(String[] args) throws Exception {
        JijiSchedule jijiSchedule = new JijiSchedule();
        jijiSchedule.execute();
    }

    @Scheduled(cron = "0,30 * * * * ? ")
    // @Scheduled(cron = "0 0 9,21 * * ? ")
    public void execute() {
        try {

            Long sleepTime = new Double(Math.random() * 1000 * 60 * 60).longValue();
            try {
                // Thread.sleep(sleepTime);
            } catch (Exception e) {
                e.printStackTrace();
            }

            System.out.println("******************************");
            System.out.println("几鸡开始领取流量");

            String cookieFileUrl = "/zcy/program/config/jiji/cookie.txt";
            // String cookieFileUrl = "d:/zcy/jiji/cookie.txt";

            String url = "https://jiji.ws/user/checkin";

            String cookie = fileService.getFileContent(cookieFileUrl);

            try {
                String result = requestService.sendPost(url, cookie);

                System.out.println(result);
            } catch (Exception e) {
                System.out.println("几鸡领取流量失败" + e.toString());
                emailService.sendEmail("几鸡领取流量失败", "几鸡领取流量失败" + e.toString());
            }
        } catch (Exception e) {
            System.out.println("几鸡领取流量失败" + e.toString());
            emailService.sendEmail("几鸡领取流量失败", "几鸡领取流量失败" + e.toString());
        }
    }

}
