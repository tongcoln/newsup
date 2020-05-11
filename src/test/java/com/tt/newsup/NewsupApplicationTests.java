package com.tt.newsup;


import com.tt.newsup.model.DataItemsModel;
import com.tt.newsup.server.*;
import com.tt.newsup.utils.replaceString;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@SpringBootTest
class NewsupApplicationTests<pubilc> {

    @Autowired
    private OpenNetworkService openNetworkService;

    @Autowired
    private InformationOpenService informationOpenService;

    @Autowired
    private SendMailService sendMailService;

    @Autowired
    private MailService mailService;

    @Autowired
    private ServiceHallService serviceHallService;

    @Test
    void contextLoads() {
    }

//    @Test
//    void getAllTest(){
//      List<List<DataItemsModel>> lists = informationOpenService.getbossAll();
//      for(List<DataItemsModel>e:lists){
//          System.out.println(e);
//      }
//    }

    /**
     * 更改资源属性
     */


    @Test
    public void repaceStringTest() {
        String countd = "dsadsadsadsasasadsad";
        String old = replaceString.replaceString2Star(countd, 10, 0);
        System.out.println(old);
    }


    @Test
    public void liuchengTest() throws IOException {

        openNetworkService.getAll();


    }


    @Test
    public void getdata() {
        long currentTime = System.currentTimeMillis();
        currentTime -= 30 * 60 * 1000;
        Date date = new Date(currentTime);
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");
        System.out.println(dateFormat.format(date));

    }

    @Test
    public void sendMailServiceTest() {
        sendMailService.sendmail();
    }

    @Test
    public void sendMailTest() {
        mailService.sendMail("xxx.com", "这里是邮件的主题", "助理是邮件的内容");//第一个参数是邮件的接收方
    }

    @Test
    public void getTableIDTest() {

////        serviceHallService.getmanageImage("13981853804","演示专区(60分)");
//        serviceHallService.savemanageAswer("13981853804", 1);
//        serviceHallService.getManaRodios(0,25,2);
        serviceHallService.getManageImgs(0,25,2);
//        String str = "ImageModel(url=http://127.0.0.1:8081/upload/1b938fd8b62841d0a068effecf51c0ff.jpg)";
//        String[] index = str.split("=");
//        if (index.length > 0) {
//            String indexstr = index[1];
//
//            String[] index2 = indexstr.split("\\)");
//            String result = index2[0];
//            System.out.println(result);
//        }
    }
}



