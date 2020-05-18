package com.tt.newsup.controller;

import com.alibaba.fastjson.JSON;
import com.tt.newsup.model.*;
import com.tt.newsup.server.GetAccessTokenService;
import com.tt.newsup.server.GetOpenIDService;
import com.tt.newsup.server.InformationOpenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
public class InformationOpenController {

    private static final int SUCCESS_CODE = 200 ;
    @Autowired
    private InformationOpenService informationOpenService;


    @Autowired
    private GetOpenIDService getOpenIDService;

    @Autowired
    private GetAccessTokenService getAccessTokenService;

    /**
     * 查询一级界面内容
     * @return
     */

    @RequestMapping("/getbossall")
    @CrossOrigin
    public RetrunMessModel getbossAll(@RequestParam("bossMerberNum")String bossMerberNum,
                                      @RequestParam("bossGroupCoding")String bossGroupCoding,
                                      @RequestParam("bossSetName")String bossSetName,
                                      @RequestParam("bossBusinessName")String bossBusinessName,
                                      @RequestParam("informationLinkMan") String informationLinkMan,
                                      @RequestParam("userid")String userid
    ){

        BlackListModel blackListModel =  informationOpenService.isblacklist(userid);
        LineWhiteListModel lineWhiteListModel = informationOpenService.iswhitelist(userid);
        RetrunMessModel retrunMessModel = new RetrunMessModel();
        System.out.println(userid);
        if(blackListModel != null) {
            retrunMessModel.setCode("1");
            return retrunMessModel;
        }else if(lineWhiteListModel != null){
             List<List<DataItemsModel>> DataItemModelList = informationOpenService.getbossAll(bossMerberNum, bossGroupCoding, bossSetName, bossBusinessName,informationLinkMan);
             getOpenIDService.inserlineuserclick(userid);
             retrunMessModel.setCode("0");
             retrunMessModel.setObjectlist(DataItemModelList);
             return retrunMessModel;
        }else{
            List<String> namelist = informationOpenService.isManager(userid);
            if (namelist.size() == 0 ){
                retrunMessModel.setCode("2");
                return retrunMessModel;
            }else{
                List<List<DataItemsModel>> DataItemModelList = informationOpenService.getlinkmanboss(bossMerberNum, bossGroupCoding, bossSetName, bossBusinessName, informationLinkMan,userid);
                if(DataItemModelList.size() !=0){
                    retrunMessModel.setCode("0");
                    retrunMessModel.setObjectlist(DataItemModelList);
                    getOpenIDService.inserlineuserclick(userid);
                    return retrunMessModel;
                }else{
                    retrunMessModel.setCode("3");
                    return retrunMessModel;
                }


            }
        }


    }


    /**
     * 获取流程
     * @param
     * @return
     */
    @RequestMapping("/getliucheng")
    @CrossOrigin
    public List<ActivitiesModel> getliucheng(@RequestParam("bossMerberNum")String bossMerberNum){
        List<ActivitiesModel> activitList = informationOpenService.getliucheng(bossMerberNum);
        return activitList;
    }


    /**
     * 获取催单的电话号码
     * @param mess
     * @return
     */

    @RequestMapping("/getphone")
    @CrossOrigin
    public String getphone(@RequestParam("mess")String mess){
        String phonemess = mess;
        String result =null;
        Pattern p = Pattern.compile("1[345678]\\d{9}");  //("1[345678]\\d{9}");
        Matcher m = p.matcher(phonemess);
        while(m.find()){
           result = m.group();
        }

        return result;

    }

    /**
     * 获取userid
     * @param code
     * @return
     * @throws IOException
     * @throws URISyntaxException
     */

    @RequestMapping("/getopenid")
    @CrossOrigin
    public String getopenid(@RequestParam("code")String code) throws IOException, URISyntaxException {

        String accesstoken = getAccessTokenService.getAccessToken();
        String userid =  getOpenIDService.getAccessToken(code,accesstoken);
        return userid;
    }

    @RequestMapping("/geterci")
    @CrossOrigin
    public List<List<DataItemsModel>> geterci(@RequestBody String str){
        String obj = JSON.parseObject(str).getString("obj");
        TwoConditionModel obj1 = JSON.parseObject(obj,TwoConditionModel.class);
        System.out.println(obj);
        List<List<DataItemsModel>>  dataItemslist =  informationOpenService.getTwoCondition(obj1);
        return  dataItemslist ;
    }

}
