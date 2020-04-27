package com.tt.newsup.server;

import com.tt.newsup.dao.LineUserDao;
import com.tt.newsup.utils.GetHalfHourUtis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * @author ：tt
 * @date ：Created in 2020/4/02 10:12 下午
 * @description：发送邮件
 * @modified By：
 * @version:
 */
@Service
public class SendMailService {

    @Autowired
    private LineUserDao lineUserDao;

    @Autowired
    private MailService mailService;

    @Scheduled(cron = "0 0/30 * * * ?")
    public void sendmail(){

        Date datatime = GetHalfHourUtis.getHour();

        List<String> halfHourUser =  lineUserDao.getHalfhourUser(datatime);
        List<String> userList =  new ArrayList<>();

        for(int i=0;i<halfHourUser.size();i++){
            String userid = halfHourUser.get(i);
            Integer count = lineUserDao.getcount(userid,datatime);
            if (count>20){
                String mess = "专线随手查异常用户预警:用户"+userid+"在半个小时内访问次数达到:"+count+"次,请及时处理";
                lineUserDao.saveBlackList(userid);
                userList.add(mess);
                lineUserDao.saveMail(userid,count);
            }
        }

        if (userList.size() != 0){
            StringBuilder sb = new StringBuilder();
            for (int j =0;j< userList.size();j++){
                sb.append(userList.get(j)).append("||");
            }
            String message = ""+sb;
            mailService.sendMail("496458052@qq.com","专线随手查异常访问告警",message);
            mailService.sendMail("269990905@qq.com","专线随手查异常访问告警",message);
            mailService.sendMail("182467110@qq.com","专线随手查异常访问告警",message);
        }

        //一线直通车异常操作预警
        List<String> yixianUserList = lineUserDao.getyixianUser(datatime);
        List<String> userList1 =  new ArrayList<>();

        for(int k = 0 ;k<yixianUserList.size();k++){
            String yixianuserid = yixianUserList.get(k);
            Integer yixiancount = lineUserDao.getyixiancount(yixianuserid,datatime);
            if (yixiancount>50){
                String yixianmess = "一线直通车异常用户预警:用户"+yixianuserid+"在半个小时内访问次数达到:"+yixiancount+"次,请及时处理";
                lineUserDao.saveBlackList(yixianuserid);
                userList1.add(yixianmess);
                lineUserDao.saveMail1(yixianuserid,yixiancount);
            }
        }

        if(userList1.size() != 0 ){
            StringBuilder sb1 = new StringBuilder();
                for (int j =0;j< userList1.size();j++){
                   sb1.append(userList1.get(j)).append("||");
            }
            String message1 = ""+sb1;
            mailService.sendMail("496458052@qq.com","一线直通车异常访问告警",message1);
            mailService.sendMail("269990905@qq.com","一线直通车异常访问告警",message1);
            mailService.sendMail("182467110@qq.com","一线直通车异常访问告警",message1);
        }


    }
}
