package com.zcy.server.program.jiji.schedule;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.ScriptResult;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.zcy.server.program.common.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${passwd}")
    private String passwd;

    public static void main(String[] args) {
        JijiSchedule jijiSchedule = new JijiSchedule();
        jijiSchedule.execute();
    }

    //@Scheduled(cron = "0 * * * * ? ")
    public void execute() {
        System.out.println("执行");
        // 实例化Web客户端
        WebClient webClient = null;
        try {
            webClient = new WebClient(BrowserVersion.CHROME);
            webClient.setJavaScriptTimeout(10000);
            //接受任何主机连接 无论是否有有效证书
            webClient.getOptions().setUseInsecureSSL(true);
            //设置支持javascript脚本
            webClient.getOptions().setJavaScriptEnabled(true);
            //禁用css支持
            webClient.getOptions().setCssEnabled(false);
            //js运行错误时不抛出异常
            webClient.getOptions().setThrowExceptionOnScriptError(false);
            //设置连接超时时间
            //webClient.getOptions().setTimeout(20000);
            webClient.getOptions().setDoNotTrackEnabled(false);

            //  解析获取页面
            HtmlPage page = webClient.getPage("https://jiji.ws/signin");
            //HtmlPage page = webClient.getPage("https://www.baidu.com");

            System.out.println(page.asText());

            page.executeJavaScript("document.getElementsByName('Email')[0].value='xiadao12@yeah.net'");
            page.executeJavaScript("document.getElementsByName('Password')[0].value='" + passwd + "'");
            DomElement button_login = page.getElementById("login");

            ScriptResult scriptResult = page.executeJavaScript("document.getElementsByName('Email')[0].value");
            ScriptResult scriptResult1 = page.executeJavaScript("document.getElementsByName('Password')[0].value");

            button_login.click();

            Thread.sleep(15 * 1000);

            System.out.println(page.getBaseURI());


            ScriptResult scriptResult2 = page.executeJavaScript("document.getElementById('result')");

            page.executeJavaScript("document.getElementById('result').remove()");
            page.executeJavaScript("document.querySelector('.modal-backdrop').remove();");

            Thread.sleep(1 * 1000);

            // 点击签到签判断是否已签过
            if (!haveSigned(page)) {
                DomElement buttonCheckin = page.getElementById("checkin");
                buttonCheckin.click();

                Thread.sleep(5 * 1000);

                if (!haveSigned(page)) {
                    System.out.println("几鸡领取流量失败");
                    emailService.sendEmail("几鸡领取流量失败", "几鸡领取流量失败");
                }

                System.out.println("几鸡签到成功");
            }
        } catch (Exception e) {
            System.out.println("几鸡领取流量失败" + e.toString());
            emailService.sendEmail("几鸡领取流量失败", "几鸡领取流量失败" + e.toString());
        } finally {
            if (webClient != null) {
                webClient.close();
            }
        }

    }

    /**
     * 判断是否已签过
     *
     * @param page
     * @return
     */
    private Boolean haveSigned(HtmlPage page) {
        ScriptResult siginButtonValue = page.executeJavaScript("document.querySelector('.checkin-btn>button').value");
        if (siginButtonValue.getJavaScriptResult().toString().contains("今日已签")) {
            return true;
        }

        return false;
    }

}
