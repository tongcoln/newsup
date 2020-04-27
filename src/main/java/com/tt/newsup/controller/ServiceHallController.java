package com.tt.newsup.controller;

import com.tt.newsup.model.*;
import com.tt.newsup.server.ServiceHallService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author ：tt
 * @date ：Created in 2020/4/27 10:19 上午
 * @description：站厅服务的Controller层
 * @modified By：
 * @version:
 */
@RestController
public class ServiceHallController {
    @Autowired
    private ServiceHallService serviceHallService;



    /**
     * 获取网络线条的默认课题
     * @return
     */

    @RequestMapping("/getnetworktopic")
    @CrossOrigin
    public List<ServiceStoreTopicNetworkDomeModel> getTopicNetworkDome(){
        List<ServiceStoreTopicNetworkDomeModel> serviceStoreTopicNetworkDomeModel = serviceHallService.getTopicNetworkDome();
        return serviceStoreTopicNetworkDomeModel;
    }

    /**
     * 获取市场线条默认的课题
     * @return
     */
    @RequestMapping("/getmarketplacetopic")
    @CrossOrigin
    public  List<ServiceStoreTopicMarketplaceDomeModel> getmarketplacetopic(){
        List<ServiceStoreTopicMarketplaceDomeModel> serviceStoreTopicMarketplaceDomeModels = serviceHallService.getmarketplacetopic();
        return serviceStoreTopicMarketplaceDomeModels;
    }

    /**
     *保存选择后网络线条课题
     * @param networktopic
     * @param user
     */

    @RequestMapping("/savenetworktopic")
    @CrossOrigin
    public void savenetworktopic(@Param("networktopic")String networktopic, @Param("user")String user){
        serviceHallService.savenetworktopic(networktopic,user);
    }


    /**
     * 保存市场线条课题
     * @param markettopic
     * @param user
     */

    @RequestMapping("/savemarkettopic")
    @CrossOrigin
    public void savemarkettopic(@Param("networktopic")String markettopic, @Param("user")String user){
        serviceHallService.savemarkettopic(markettopic,user);
    }


    /**
     *获取我的课题
     */
    @RequestMapping("/getmytopiclist")
    @CrossOrigin
    public MytopicListModel getmytopiclist(@Param("user")String user){
        ServiceStoreTopicProceedModel serviceStoreTopicProceedModel = serviceHallService.getmytopiclist(user);
        if(serviceStoreTopicProceedModel != null){
            Integer type = serviceStoreTopicProceedModel.getServiceStoreMytopicType();
            Integer mytopicId = serviceStoreTopicProceedModel.getServiceStoreMytopicId();
            List<String> stringList = new ArrayList<>();
            if (type == 1){
                //如果课题类型为1，则获取网络课题列表
                stringList = serviceHallService.getnetworktopic();
            }else{
                stringList = serviceHallService.getmarkettopic();
            }
            //todo 如果课题类型不为1，则获取市场的默认课题列表
            String zidingyistring = serviceStoreTopicProceedModel.getServiceStoreMytopicContext();
            List<String> stringLists = new ArrayList<>();
            String[] strArr = zidingyistring.split("%\\^&\\*");

            for (int i = 1;i<strArr.length;i++){
                if (strArr[i].endsWith(",")){
                    String newstr = strArr[i].substring(0,strArr[i].length()-1);
                    stringLists.add(newstr);
                }else{
                    stringLists.add(strArr[i]);
                }

            }

            MytopicListModel mytopicListModel = new MytopicListModel();
            mytopicListModel.setType(type);
            mytopicListModel.setMytopicId(mytopicId);
            mytopicListModel.setMorenlist(stringList);
            mytopicListModel.setZidingyilist(stringLists);

            return mytopicListModel;
        }else{
            return null;
        }

    }

    /**
     *获取要巡检站厅列表
     * @param
     * @return
     */
    @RequestMapping("/getServiceHall")
    @CrossOrigin
    public List<ServiceHallModel>  getServiceHall(){
        List<ServiceHallModel>  serviceHallModels = serviceHallService.getAllHall();
        return  serviceHallModels;
    }

    /**
     * 获取后7天的日期
     * @return
     */

    @RequestMapping("/getgrabdate")
    @CrossOrigin
    public String[] getgrabdate(){
        String[] arr = new String[7];
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for(int i = 0;i<7;i++){
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(new Date());
            calendar.add(Calendar.DATE, i+1);
            String date = sdf.format(calendar.getTime());
            arr[i] =date;
        }
        return arr;
    }


    /**
     * 首页搜索按钮
     * @param results
     * @return
     */
    @RequestMapping("/getServiceHallbyname")
    @CrossOrigin
    public List<ServiceHallModel>  getServiceHallByName(
            @RequestParam("results") String results
    ){
        List<ServiceHallModel>  DataItemList = serviceHallService.getAllHallByName(results);
        return  DataItemList;
    }


    /**
     * 抢单操作，插入抢单信息
     * @param serviceStoreDitchCode
     * @param user
     * @param datemodel
     * @param datamodel1
     * @return
     */

    @RequestMapping("/progress")
    @CrossOrigin
    public String  insertprogress(@RequestParam("serviceStoreDitchCode")String serviceStoreDitchCode,
                                  @RequestParam("user")String user,
                                  @RequestParam("datemodel")String datemodel,
                                  @RequestParam("datamodel1")String datamodel1,
                                  @RequestParam("mytopicId")Integer mytopicId
    ){

        //查询正在进行中的是否有任务，如果有任务则提醒已有任务，抢单失败
        ServiceHallProceedModel serviceHallProceedModel = serviceHallService.getServiceHallProceed(user);

        //查询同一站厅的同一时间段是否有2条记录，如果有2条记录了则提醒该站厅该时间段预约已满，请选择其他站厅或其他时间段
        Integer count = serviceHallService.getcount(serviceStoreDitchCode,datemodel,datamodel1);

        if(serviceHallProceedModel  != null) {
            return "0";
        }else if(count>=2){
            return "2";
        }else{
            serviceHallService.insertprogress(serviceStoreDitchCode, user, datemodel, datamodel1,mytopicId);
            return "1";
        }



    }

}
