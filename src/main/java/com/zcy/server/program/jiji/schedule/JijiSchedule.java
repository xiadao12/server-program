package com.zcy.server.program.jiji.schedule;

import com.zcy.server.program.common.service.EmailService;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
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

    public static void main(String[] args) {
        JijiSchedule jijiSchedule = new JijiSchedule();
        jijiSchedule.receiveSelenium();
    }

    @Scheduled(cron = "0 * * * * ? ")
    public void receiveSelenium() {

        System.out.println("******************************");
        System.out.println("几鸡开始领取流量");

        System.setProperty("webdriver.chrome.driver", "D:\\chromedriver_win32\\chromedriver.exe");

        // chromedriver服务地址
        ChromeDriver driver = new ChromeDriver();

        try {
            driver.get("https://jiji.ws/signin");

            WebElement inputEmail = driver.findElementsById("email").get(1);
            WebElement passwdEmail = driver.findElementsById("passwd").get(1);
            WebElement buttonLogin = driver.findElementById("login");

            inputEmail.clear();
            passwdEmail.clear();

            inputEmail.sendKeys("xiadao12@yeah.net");
            passwdEmail.sendKeys("yang12345");

            buttonLogin.click();

            Thread.sleep(10000);

            driver.executeScript("document.getElementById('result').remove()");
            driver.executeScript("document.querySelector('.modal-backdrop').remove();");

            Thread.sleep(1000);

/*        WebElement buttonCheckin = driver.findElementById("checkin");
        buttonCheckin.click();*/

            String havedSign = driver.findElementByClassName("checkin-btn").findElement(By.tagName("button")).getText();
            if (!havedSign.contains("今日已签")) {
                System.out.println("几鸡领取流量失败");
                emailService.sendEmail("几鸡领取流量失败", "几鸡领取流量失败");
            }

            System.out.println("签到成功");
        } catch (Exception e) {
            System.out.println("几鸡领取流量失败" + e.toString());
            emailService.sendEmail("几鸡领取流量失败", "几鸡领取流量失败" + e.toString());
        } finally {
            driver.close();
        }
    }

}
