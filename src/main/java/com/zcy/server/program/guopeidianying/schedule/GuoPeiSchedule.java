package com.zcy.server.program.guopeidianying.schedule;

import com.zcy.server.program.common.service.EmailService;
import com.zcy.server.program.common.service.FileService;
import com.zcy.server.program.common.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author yangzhouchuan
 * @version 1.0
 * @date 2019-09-24 11:19
 */
@Component
public class GuoPeiSchedule {

    @Autowired
    private EmailService emailService;

    @Autowired
    private RequestService requestService;

    @Autowired
    private FileService fileService;

    public static void main(String[] args) throws Exception {
        GuoPeiSchedule guoPeiSchedule = new GuoPeiSchedule();
        guoPeiSchedule.execute();
    }

    //@Scheduled(cron = "0,20,40 * * * * ? ")
    @Scheduled(cron = "0 0 9,21 * * ? ")
    public void execute() {
        try {
            Long sleepTime = new Double(Math.random() * 1000 * 60 * 60).longValue();
            Thread.sleep(sleepTime);

            System.out.println("******************************");
            System.out.println("国配电影开始领取金币");

            String cookieFileUrl = "/zcy/program/config/guopeidianying/cookie.txt";
            //String cookieFileUrl = "d:/zcy/guopeidianying/cookie.txt";

            String url = "http://www.ccvnn.com/plugin.php";

            String cookie = fileService.getFileContent(cookieFileUrl);
            //String cookie = "gVOp_0a52_saltkey=VCxdlK58; gVOp_0a52_lastvisit=1569199085; gVOp_0a52_auth=f966C1WRczy87gyCqkpbUKYM7kNuZqTyKunuYo3y49m5zU0DDjlcUIey%2FNs9OMXzCjJAbmh0i4GCM0O2Tkrd1kSrKZA; gVOp_0a52_lastcheckfeed=184592%7C1569202702; gVOp_0a52_connect_is_bind=0; gVOp_0a52_nofavfid=1; gVOp_0a52_ignore_notice=1; mykyo_user=184592%7C1569283742%7Cxiadao12%7Cxiadao12%40yeah.net%7C0%7C219%7C13%7C1%7C13%7C0%7C12%7C0%7C0%7C0%7C0%7C1563895595; gVOp_0a52_pc_size_c=0deabcf; gVOp_0a52_sid=hTXV79; gVOp_0a52_lip=121.239.230.154%2C1569293067; gVOp_0a52_ulastactivity=b9e3LOkznd3EOZTqFhKGtKibdUXvTiGM5Zy9o3eamVWbHsmvmvRn; gVOp_0a52_checkpm=1; gVOp_0a52_noticeTitle=1; gVOp_0a52_lastact=1569293131%09plugin.php%09";

            // 刷新
            try {
                String refreshParam = "id=hux_miner:hux_miner&ac=re&formhash=b08bcc49&t=0";
                requestService.sendPost(url, refreshParam, cookie);
            } catch (Exception e) {
                System.out.println("刷新金币异常" + e.toString());
                emailService.sendEmail("国配电影领取金币失败", "刷新金币异常" + e.toString());
            }
            System.out.println("刷新金币成功");

            // 领取
            try {
                String param = "id=hux_miner:hux_miner&ac=draw&formhash=b08bcc49&t=0";
                String result = requestService.sendPost(url, param, cookie);

                Date date = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                String currentDateString = sdf.format(date);

                System.out.println("时间：" + currentDateString + "  " + result);

                // 如果失败则发邮件
                if (!result.contains("操作成功")) {
                    emailService.sendEmail("国配电影领取金币失败", result);
                }
            } catch (Exception e) {
                System.out.println("领取金币异常");
                e.printStackTrace();
                emailService.sendEmail("国配电影领取金币失败", "领取金币异常" + e.toString());
            }

            Thread.sleep(10000);
        } catch (Exception e) {
            System.out.println(e.toString());
            emailService.sendEmail("国配电影领取金币失败", e.toString());
        }
    }

}
