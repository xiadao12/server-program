package com.zcy.server.program.jiji.schedule;

/**
 * @author yangzhouchuan
 * @version 1.0
 * @date 2019-10-24 09:15
 */
public class JijiHtmlUnit {

    public void execute() {
/*        // 实例化Web客户端
        WebClient webClient = new WebClient(BrowserVersion.CHROME);

        // webClient.setJavaScriptTimeout(5000);
        //接受任何主机连接 无论是否有有效证书
        webClient.getOptions().setUseInsecureSSL(true);
        //设置支持javascript脚本
        webClient.getOptions().setJavaScriptEnabled(true);
        //禁用css支持
        webClient.getOptions().setCssEnabled(false);
        //js运行错误时不抛出异常
        webClient.getOptions().setThrowExceptionOnScriptError(false);
        //设置连接超时时间
        webClient.getOptions().setTimeout(20000);
        webClient.getOptions().setDoNotTrackEnabled(false);

        try {
            //  解析获取页面
            HtmlPage page = webClient.getPage("https://jiji.ws/signin");
            // HtmlPage page = webClient.getPage("https://104.18.62.120/signin");

            System.out.println(page.asText());
            DomElement input_email = page.getElementById("email");
            DomElement input_passwd = page.getElementById("passwd");
            DomElement button_login = page.getElementById("login");
            System.out.println();

            input_email.setTextContent("xiadao12@yeah.net");
            input_passwd.setTextContent("yang12345");
            Page clickPage = button_login.click();

            System.out.println("跳转路径" + clickPage.getUrl().getPath());

            HtmlPage pageCheckin = webClient.getPage("https://jiji.ws/user");*/

 /*           DomElement shouyiShowButton = page.getElementById("shouyi_show");
            Page click = shouyiShowButton.click();
            WebResponse webResponse = click.getWebResponse();
            System.out.println();*/


/*            //  得到搜索Form
            HtmlForm form = page.getFormByName("myform");
            //  获取查询文本框
            HtmlTextInput textField = form.getInputByName("q");
            //  获取提交按钮
            HtmlSubmitInput button = form.getInputByName("submitButton");
            //  文本框“填入”数据
            textField.setValueAttribute("java");
            //  模拟点击
            HtmlPage page2 = button.click();
            System.out.println(page2.asXml());*/


/*        } catch (Exception e) {
            System.out.println(e);
            // 关闭客户端，释放内存
            webClient.close();
        }*/
    }

}
