package com.example.reptile;


import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 * @author:lifuyi
 * @Date: 2019/1/9 15:36
 * @Description:
 */
public class Client {

    private final static Logger logger = LoggerFactory.getLogger(Client.class);

    private String accout;
    private String password;
    /**
     * 实例化httpclient
     */
    HttpClient client = HttpClients.createDefault();
    HttpResponse response = null;
    String rawHtml;


    public boolean login() {
        try {
            HttpGet getLoginPage = new HttpGet("http://www.gzzyfc.com.cn/Client/ZunYi/Second/Detail/ProjectInfo/ProjectDetail.aspx?id=5203210000000212&RelationCID=2&PageSize=5");
            getVerifyingCode(client);

            //提醒用户并输入验证码,后面把这部分改为图像识别就可以自动爬取
            logger.info("请输入验证码");
//            System.out.println("请输入验证码");
            String code;
            Scanner in = new Scanner(System.in);
            code = in.nextLine();
            in.close();

            Date date = new Date();
            System.out.println(date);
            HttpPost post = new HttpPost("http://www.gzzyfc.com.cn/Client/ZunYi/Second/Detail/ProjectInfo/ProjectDetail.aspx?&fid=5203210000000953" +
                    "&vcode=" + code+"&rnd="+date);

            ArrayList<NameValuePair> postData = new ArrayList<NameValuePair>();
            post.setEntity(new UrlEncodedFormEntity(postData));
            //执行登陆行为
            response = client.execute(post);
            rawHtml = EntityUtils.toString(response.getEntity(), "utf-8");
//            System.out.println("打印网页内容");
            logger.info("打印网页内容");
            System.out.println(rawHtml);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("出错！！！！！！！！！！！！！！");
            return false;
        }
    }

    void getVerifyingCode(HttpClient client) {
        //验证码get
        HttpGet getVerifyCode = new HttpGet("http://www.gzzyfc.com.cn/ValidateCode/ValidateCode.aspx");
        FileOutputStream fileOutputStream = null;
        HttpResponse response;
        try {
            //获取验证码
            response = client.execute(getVerifyCode);
            /*验证码写入文件,当前工程的根目录,保存为verifyCode.jped*/
            fileOutputStream = new FileOutputStream(new File("verifyCode.jpeg"));
            response.getEntity().writeTo(fileOutputStream);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Client client = new Client();
        client.login();
    }

}
