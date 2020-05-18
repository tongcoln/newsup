package com.tt.newsup.server;

import com.tt.newsup.dao.ServiceHallDao;
import com.tt.newsup.model.*;
import com.tt.newsup.utils.ContrastUtils;
import com.tt.newsup.utils.DistanceUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author ：tt
 * @date ：Created in 2020/4/27 10:36 上午
 * @description：
 * @modified By：
 * @version:
 */
@Service
@Transactional
public class ServiceHallService {

    @Autowired
    private ServiceHallDao serviceHallDao;


    /**
     * 获取网络线条的默认课题
     *
     * @return
     */
    public List<ServiceStoreTopicNetworkDomeModel> getTopicNetworkDome() {
        List<ServiceStoreTopicNetworkDomeModel> serviceStoreTopicNetworkDomeModel = serviceHallDao.getTopicNetworkDome();
        return serviceStoreTopicNetworkDomeModel;
    }

    /**
     * 获取市场线条的默认课题
     *
     * @return
     */
    public List<ServiceStoreTopicMarketplaceDomeModel> getmarketplacetopic() {
        List<ServiceStoreTopicMarketplaceDomeModel> storeTopicMarketplaceDomeModels = serviceHallDao.getmarketplacetopic();
        return storeTopicMarketplaceDomeModels;
    }

    /**
     * 保存选择了网络线条的课题
     *
     * @param networktopic
     * @param user
     */
    public void savenetworktopic(String networktopic, String user) {
        serviceHallDao.savenetworktopic(networktopic, user);
    }

    /**
     * 获取我的课题列表
     *
     * @param user
     * @return
     */
    public ServiceStoreTopicProceedModel getmytopiclist(String user) {
        ServiceStoreTopicProceedModel serviceStoreTopicProceedModel = serviceHallDao.getmytopiclist(user);
        return serviceStoreTopicProceedModel;
    }

    /**
     * 获取网络线条的默认课题列表
     *
     * @return
     */
    public List<String> getnetworktopic() {
        List<String> strings = serviceHallDao.getnetworktopic();
        return strings;
    }

    /**
     * 保存选择的市场线条的课题
     *
     * @param markettopic
     * @param user
     */
    public void savemarkettopic(String markettopic, String user) {
        serviceHallDao.savemarkettopic(markettopic, user);
    }

    /**
     * 查询市场线条的默认课题
     *
     * @return
     */
    public List<String> getmarkettopic() {
        List<String> strings = serviceHallDao.getmarkettopic();
        return strings;
    }

    /**
     * 获取要巡检站厅列表
     *
     * @param
     * @return
     */
    public List<ServiceHallModel> getAllHall() {
        List<ServiceHallModel> serviceHallModels = serviceHallDao.selectAll();
        return serviceHallModels;
    }

    /**
     * @param user
     * @return
     */
    public ServiceHallProceedModel getServiceHallProceed(String user) {
        ServiceHallProceedModel serviceHallProceedModel = serviceHallDao.getServiceHallProceed(user);
        return serviceHallProceedModel;
    }

    //查询同一站厅的同一时间段是否有2条记录，如果有2条记录了则提醒该站厅该时间段预约已满，请选择其他站厅或其他时间段
    public Integer getcount(String serviceStoreDitchCode, String datemodel, String datemodel1) {
        Integer count = serviceHallDao.getcount(serviceStoreDitchCode, datemodel, datemodel1);
        return count;
    }

    /**
     * 插入抢单信息
     *
     * @param serviceStoreDitchCode 站点编码
     * @param user                  用户
     * @param datemodel             日期
     * @param datamodel1            上午或者下午
     * @param mytopicId             课题ID
     */

    public void insertprogress(String serviceStoreDitchCode, String user, String datemodel, String datamodel1, Integer mytopicId) {
        serviceHallDao.insertprogress(serviceStoreDitchCode, user, datemodel, datamodel1, mytopicId);
    }

    /**
     * 首页搜索按钮，信息筛选
     *
     * @param results
     * @return
     */
    public List<ServiceHallModel> getAllHallByName(String results) {
        List<ServiceHallModel> serviceHallModels = serviceHallDao.selectAllByName(results);
        return serviceHallModels;
    }

    /**
     * 获取正在进行中的站厅信息
     *
     * @param userid
     * @return
     */
//    public ServiceHallModel getstoremess(String userid) {
//        String serviceStoreDitchCode = serviceHallDao.getStoreId(userid);
//        ServiceHallModel serviceHallModel = serviceHallDao.getStoremess(serviceStoreDitchCode);
//        return serviceHallModel;
//    }

    /**
     * 查询用户的调查表做到第几步
     *
     * @param user
     * @return
     */
    public Integer getactive(String user) {
        Integer active = serviceHallDao.getactive(user);
        return active;
    }

    /**
     * 查询网络线条的项目名称
     *
     * @return
     */
    public List<String> getmaagenetworkname() {
        List<String> strings = serviceHallDao.getmaagenetworkname();
        return strings;
    }

    /**
     * 查询市场线条的项目名称
     *
     * @return
     */
    public List<String> getmaagemarketname() {
        List<String> strings = serviceHallDao.getmaagemarketname();
        return strings;
    }


    /**
     * 查询服务营销动作表市场具体内容
     *
     * @param manageTableName
     * @return
     */
    public List<String> getmanagequestionmarket(String manageTableName) {
        List<String> strings = serviceHallDao.getmanagequestionmarket(manageTableName);
        return strings;
    }

    /**
     * 查询服务营销动作表网络线条具体内容
     *
     * @param manageTableName
     * @return
     */
    public List<String> getmanagequestionnetwork(String manageTableName) {
        List<String> strings = serviceHallDao.getmanagequestionnetwork(manageTableName);
        return strings;
    }

    /**
     * 保存图片的PATH
     *
     * @param manageTableName
     * @param index
     * @param tableId
     * @param url
     */
    public void insermanageImage(String manageTableName, Integer index, Integer tableId, String url, String userid) {

        serviceHallDao.insermanageImage(manageTableName, index, tableId, url, userid);
    }

    /**
     * 获取管理者图片
     *
     * @param userid
     * @param tablename
     */
    public  List<List<ImageModel>>  getmanageImage(String userid, String tablename) {
//      List<ServiceManageImgModel> serviceManageImgModels =   serviceHallDao.getmanageImage(userid,tablename);
        Integer serviceMaxManageNetworkImgTableId = serviceHallDao.getMaxTableId(userid, tablename);
        if (serviceMaxManageNetworkImgTableId != null) {
            List<List<ImageModel>> ImagemodelList = new ArrayList<>();
            for (int i = 0; i <= serviceMaxManageNetworkImgTableId; i++) {
                List<ServiceManageImgModel> serviceManageImgModels = serviceHallDao.getmanageImage(userid, tablename, i);
                if (serviceManageImgModels.size() != 0) {
                    List<ImageModel> imageModels = new ArrayList<>();
                    for (int j = 0; j < serviceManageImgModels.size(); j++) {
                        ImageModel imageModel = new ImageModel();
                        String serviceManageNetworkImgPath = serviceManageImgModels.get(j).getServiceManageNetworkImgPath();
                        imageModel.setUrl(serviceManageNetworkImgPath);
                        imageModels.add(imageModel);
                    }
                    ImagemodelList.add(imageModels);
                } else {
                    List<ImageModel> imageModels = new ArrayList<>();
                    ImagemodelList.add(imageModels);
                }
            }

            for (int i = 0;i<ImagemodelList.size();i++){
                for(int j = 0;j<ImagemodelList.get(i).size();j++){
                    System.out.println(ImagemodelList.get(i).get(j).getUrl());
                }
            }
            return ImagemodelList;
        }else {
            return  null;
        }

    }

    /**
     * 保存临时的答案
     * @param radio
     * @param message
     * @param userid
     * @param type
     * @param manageTableName
     */

    public void saveAnswerBlank(List<String> radio, List<String>  message, String userid, Integer type,String manageTableName) {
        /**
         * 判断该用户该类型的答案是否存在吗，如果存在删除原来的答案保存现有的，如果没有就直接插入
         */
        List<ServiceManageAnswerModel> serviceManageAnswerModels = new ArrayList<ServiceManageAnswerModel>();
        if (type ==1) {
            serviceManageAnswerModels = serviceHallDao.getManageBlackAnswerNetwork(userid, manageTableName);
            if(serviceManageAnswerModels.size() != 0 ){ //如果存在了临时答案则删除原有的同类型的答案
                serviceHallDao.delManageBlackAnswerNetwork(userid, manageTableName);
            }
        }else{
            serviceManageAnswerModels = serviceHallDao.getManageBlackAnswerMarket(userid, manageTableName);
            if(serviceManageAnswerModels.size() != 0){//如果存在了临时答案则删除原有的同类型的答案
                serviceHallDao.delManageBlackAnswerMarket(userid, manageTableName);
            }
        }

        for(int i =0;i<radio.size();i++){
            String radioContext = radio.get(i);
            String messageContext = null;
            Integer messagelenth = message.size();
            System.out.println(messagelenth);
            if(i<messagelenth){
                 messageContext = message.get(i);
            }else{
                 messageContext = null;
            }
            Integer ansertNum = i+1;
            if(type == 1){
                serviceHallDao.saveManageAnswerBlank(radioContext,messageContext,userid,ansertNum,manageTableName);
            }else{
                serviceHallDao.saveManageAnswerBlankMarket(radioContext,messageContext,userid,ansertNum,manageTableName);
            }
        }

        }

    /**
     * 管理者执行表删除图片操作
     */
    public void delemanageimg(String url,Integer type) {

            serviceHallDao.delemanagenetworkimg(url);

    }

    /**
     * 获取临时表的答案
     * @param userid
     * @param manageTableName
     * @param type
     */

    public  List<ServiceManageAnswerModel> getmanageaswerblack(String userid, String manageTableName, Integer type) {
        List<ServiceManageAnswerModel> serviceManageAnswerModels = new ArrayList<ServiceManageAnswerModel>();
        if(type == 1){
          serviceManageAnswerModels =    serviceHallDao.getmanagenetworkaswerblack(userid,manageTableName);
        }else {
            serviceManageAnswerModels =serviceHallDao.getmanagemarketaswerblack(userid,manageTableName);
        }
        return  serviceManageAnswerModels;
    }

    /**
     * 保存管理者执行表的答案
     * @param userid
     * @param type
     * @return
     */
    public String savemanageAswer(String userid, Integer type) {
        String code = "2";
        StringBuilder roidzongti = new StringBuilder(); //把按钮的结果保存到这个字符串
        StringBuilder messagezongti = new StringBuilder();//把输入框的结果保存到这个字符串
        StringBuilder imgpathzongti = new StringBuilder(); //上传的图片结果保存到这个字符串
        Integer manageAnswerTopicId = serviceHallDao.getAnswerTopId(userid);
        Integer manageAnswerProceedId = serviceHallDao.getAnswerProceedId(userid);
        if(type == 1){ //管理网络线条的管理者执行表的答案
            List<String> namelist =  serviceHallDao.getmaagenetworkname();

            for (int i = 0;i<namelist.size();i++){
                StringBuilder imgpathzongti1 = new StringBuilder();
                String manageTableName =namelist.get(i);
                List<List<ImageModel>>  getmanageImage = this.getmanageImage(userid,manageTableName);
                if(getmanageImage != null ) {
                    for (int j = 0; j < getmanageImage.size(); j++) {

                        StringBuilder imgpathes = new StringBuilder();
                        for (int k = 0; k < getmanageImage.get(j).size(); k++) {
                            imgpathes.append(getmanageImage.get(j).get(k)).append("!@#");
                        }
                        imgpathzongti1.append(imgpathes).append("#@!");
                    }
                    imgpathzongti.append(imgpathzongti1).append("!!@@##");
                }





                List<ServiceManageAnswerModel> serviceManageAnswerModels =  serviceHallDao.getmanagenetworkaswerblack(userid,manageTableName);
                StringBuilder roids = new StringBuilder();
                StringBuilder messages = new StringBuilder();
                for(int o =0;o<serviceManageAnswerModels.size();o++){

                    String rodi = serviceManageAnswerModels.get(o).getManageAnswerRadio();
                    if (rodi == null|| rodi ==""){
                        code ="0";//还有未完成的
                        break;
                    }
                    String message = serviceManageAnswerModels.get(o).getManageAnswerTextarea();
                    roids.append(rodi).append("!@#");
                    messages.append(message).append("!@#");
                }

                roidzongti.append(roids).append("#@!");
                messagezongti.append(messages).append("#@!");
            }
            String roidzongtistr = roidzongti+"";
            String messagezongtistr = messagezongti+"";
            String imgpathzongtistr = imgpathzongti+"";
            //插入到营销执行表的最终答案中去
            serviceHallDao.inserNetworkAnswer(userid,roidzongtistr,messagezongtistr,imgpathzongtistr,manageAnswerTopicId,manageAnswerProceedId);
            //改变进行中页面的步骤
            serviceHallDao.updatanetworkactive(userid);
            //根据userid删除临时表中的临时答案和临时图片
            serviceHallDao.delemanagenetAnswerBlack(userid);
            serviceHallDao.delemanagenetworkimgall(userid);
            code = "1";

        }

        if(type == 2){ //保存市场线条的管理者执行表的答案

            List<String> namelist =  serviceHallDao.getmaagemarketname();

            for (int i = 0;i<namelist.size();i++){
                StringBuilder imgpathzongti1 = new StringBuilder();
                String manageTableName =namelist.get(i);
                List<List<ImageModel>>  getmanageImage = this.getmanageImage(userid,manageTableName);
                if(getmanageImage !=null) {
                    for (int j = 0; j < getmanageImage.size(); j++) {
                        StringBuilder imgpathes = new StringBuilder();
                        for (int k = 0; k < getmanageImage.get(j).size(); k++) {
                            imgpathes.append(getmanageImage.get(j).get(k)).append("!@#");
                        }
                        imgpathzongti1.append(imgpathes).append("#@!");
                    }
                    imgpathzongti.append(imgpathzongti1).append("!!@@##");
                }


                List<ServiceManageAnswerModel> serviceManageAnswerModels =  serviceHallDao.getmanagemarketaswerblack(userid,manageTableName);
                StringBuilder roids = new StringBuilder();
                StringBuilder messages = new StringBuilder();
                for(int o =0;o<serviceManageAnswerModels.size();o++){

                    String rodi = serviceManageAnswerModels.get(o).getManageAnswerRadio();
                    if (rodi == null|| rodi ==""){
                        code ="0";//还有未完成的
                        break;
                    }
                    String message = serviceManageAnswerModels.get(o).getManageAnswerTextarea();
                    roids.append(rodi).append("!@#");
                    messages.append(message).append("!@#");
                }

                roidzongti.append(roids).append("#@!");
                messagezongti.append(messages).append("#@!");
            }
            String roidzongtistr = roidzongti+"";
            String messagezongtistr = messagezongti+"";
            String imgpathzongtistr = imgpathzongti+"";
            //插入到营销执行表的最终答案中去
            serviceHallDao.inserMarketAnswer(userid,roidzongtistr,messagezongtistr,imgpathzongtistr,manageAnswerTopicId,manageAnswerProceedId);
            //改变进行中页面的步骤
            serviceHallDao.updatanetworkactive(userid);
            //根据userid删除临时表中的临时答案和临时图片
            serviceHallDao.delemanageMarketAnswerBlack(userid);

            serviceHallDao.delemanagenetworkimgall(userid);
            code = "1";

        }
        return code;
    }

    /**
     * 查询体验者执行表的
     * @param type
     * @return
     */

    public List<ServiceHallTasteQuestionModel> gettastedata(Integer type) {
        List<ServiceHallTasteQuestionModel> serviceHallTasteQuestionModels = new ArrayList<ServiceHallTasteQuestionModel>();
        if (type ==1){
            serviceHallTasteQuestionModels = serviceHallDao.gettastedatanetwork();
        }else{
            serviceHallTasteQuestionModels = serviceHallDao.gettastedatamarket();
        }

        return serviceHallTasteQuestionModels;
    }

    /**
     * 保存体验者图片
     * @param index 图片顺序
     * @param tableId 题目ID
     * @param url
     * @param userid
     */
    public void insertasteImage(Integer index, Integer tableId, String url, String userid) {

        serviceHallDao.inserTasteImg(index,tableId,url,userid);
    }

    /**
     * 获取体验者图片
     * @param userid
     * @return
     */
    public List<List<ImageModel>> gettasteImage(String userid) {
        Integer serviceMaxManageNetworkImgTableId = serviceHallDao.gettasteMaxTableId(userid);
        if (serviceMaxManageNetworkImgTableId != null) {
            List<List<ImageModel>> ImagemodelList = new ArrayList<>();
            for (int i = 0; i <= serviceMaxManageNetworkImgTableId; i++) {
                List<ServiceTasteImgModel> serviceTasteImgModels = serviceHallDao.gettasteImage(userid, i);
                if (serviceTasteImgModels.size() != 0) {
                    List<ImageModel> imageModels = new ArrayList<>();
                    for (int j = 0; j < serviceTasteImgModels.size(); j++) {
                        ImageModel imageModel = new ImageModel();
                        String serviceManageNetworkImgPath = serviceTasteImgModels.get(j).getServiceTasteMarketImgPath();
                        imageModel.setUrl(serviceManageNetworkImgPath);
                        imageModels.add(imageModel);
                    }
                    ImagemodelList.add(imageModels);
                } else {
                    List<ImageModel> imageModels = new ArrayList<>();
                    ImagemodelList.add(imageModels);
                }
            }

            return ImagemodelList;
        }else {
            return  null;
        }

    }

    /**
     * 删除体验者图片
     * @param url
     */
    public void deletasteimg(String url) {

        serviceHallDao.deletasteimg(url);

    }

    /**
     * 保存体验者执行表的答案
     * @param userid
     * @param radio
     * @param message
     * @param type
     * @return
     */

    public String insertTasteAnswer(String userid, List<String> radio, List<String> message, Integer type) {

        String code = "2";
        StringBuilder roidzongti = new StringBuilder(); //把按钮的结果保存到这个字符串
        StringBuilder messagezongti = new StringBuilder();//把输入框的结果保存到这个字符串
        StringBuilder imgpathzongti = new StringBuilder(); //上传的图片结果保存到这个字符串
        Integer manageAnswerTopicId = serviceHallDao.getAnswerTopId(userid);
        Integer manageAnswerProceedId = serviceHallDao.getAnswerProceedId(userid);
        if(type == 1) {

            for (int i = 0; i < radio.size(); i++) {
                roidzongti.append(radio.get(i)).append("!@#");
            }

            for (int j = 0; j < message.size(); j++) {
                messagezongti.append(message.get(j)).append("!@#");
            }

            List<List<ImageModel>> getmanageImage = this.gettasteImage(userid);
            if(getmanageImage != null) {
                for (int j = 0; j < getmanageImage.size(); j++) {
                    StringBuilder imgpathes = new StringBuilder();
                    for (int k = 0; k < getmanageImage.get(j).size(); k++) {
                        imgpathes.append(getmanageImage.get(j).get(k)).append("!@#");
                    }
                    imgpathzongti.append(imgpathes).append("#@!");
                }
            }
            String roidzongtistr = roidzongti+"";
            String messagezongtistr = messagezongti+"";
            String imgpathzongtistr = imgpathzongti+"";
            serviceHallDao.insertTasteAnswer(userid,roidzongtistr,messagezongtistr,imgpathzongtistr,manageAnswerTopicId,manageAnswerProceedId);
            //删除临时图片库的图片
            serviceHallDao.deleteTasteImg(userid);
            //更改active的值
            serviceHallDao.updatetasteActive(userid);
            code ="1";
        }


        if(type ==2 ){

            for (int i = 0; i < radio.size(); i++) {
                roidzongti.append(radio.get(i)).append("!@#");
            }

            for (int j = 0; j < message.size(); j++) {
                messagezongti.append(message.get(j)).append("!@#");
            }

            List<List<ImageModel>> getmanageImage = this.gettasteImage(userid);
            if(getmanageImage != null) {
                for (int j = 0; j < getmanageImage.size(); j++) {
                    StringBuilder imgpathes = new StringBuilder();
                    for (int k = 0; k < getmanageImage.get(j).size(); k++) {
                        imgpathes.append(getmanageImage.get(j).get(k)).append("!@#");
                    }
                    imgpathzongti.append(imgpathes).append("#@!");
                }
            }
            String roidzongtistr = roidzongti+"";
            String messagezongtistr = messagezongti+"";
            String imgpathzongtistr = imgpathzongti+"";


            serviceHallDao.insertMarketTasteAnswer(userid,roidzongtistr,messagezongtistr,imgpathzongtistr,manageAnswerTopicId,manageAnswerProceedId);
            //删除临时图片库的图片
            serviceHallDao.deleteTasteImg(userid);
            //更改active的值
            serviceHallDao.updatetasteActive(userid);
            code = "1";

        }




        return code;
    }

    /**
     * 保存课题总结页面
     * @param userid
     * @param type
     * @param message
     * @return
     */
    public String saveSummary(String userid, Integer type, String message) {
        String code= "2";

            Integer manageAnswerTopicId = serviceHallDao.getAnswerTopId(userid);
            Integer manageAnswerProceedId = serviceHallDao.getAnswerProceedId(userid);
            //保存答案
            serviceHallDao.saveSummary(userid, message, manageAnswerTopicId, manageAnswerProceedId);
            //改变active的值
            serviceHallDao.updateSummaryActive(userid);
            //把进行中的数据插入到完成的数据
            serviceHallDao.insertProgreeEnd(userid);
            //删除进行中的数据
            serviceHallDao.deleteProgree(userid);
            //要把进行中的我的课题进行保存
            serviceHallDao.inserMytopicEnd(userid);
            //删除课题表中正在进行的课题
            serviceHallDao.deleteMytopicProgree(userid);
            code ="1";


        return code;
    }

    /**
     * 管理类执行表派发一线直通车工单
     * @param userid
     * @param tablename
     * @param problemId
     * @param problemtitle
     * @return
     */
    public String  saveManageProblem(String userid, String tablename, Integer problemId, String problemtitle,String problemcontext,List<String> imgs) {
        String code = "2";
        Integer manageAnswerProceedId = serviceHallDao.getAnswerProceedId(userid);
        StringBuilder sb = new StringBuilder();
        if (imgs.size()!=0){
            for(int i = 0;i<imgs.size();i++){
                sb.append(imgs.get(i)).append("!@#");
            }
        }

        String imgString =sb+"";
        serviceHallDao.saveManageProblem(userid,tablename,problemId,problemtitle,manageAnswerProceedId,problemcontext,imgString);


        return code;
    }

    public String saveTasteProblem(String userid, Integer problemId, String problemtitle, String problemcontext, List<String> imgs) {
        String code ="2";
        Integer manageAnswerProceedId = serviceHallDao.getAnswerProceedId(userid);
        StringBuilder sb = new StringBuilder();
        if (imgs.size()!=0){
            for(int i = 0;i<imgs.size();i++){
                sb.append(imgs.get(i)).append("!@#");
            }
        }

        String imgString =sb+"";
        serviceHallDao.saveTasteProblem(userid,problemId,problemtitle,manageAnswerProceedId,problemcontext,imgString);

        return code;
    }

    /**
     * 根据用户ID 获取待评价的列表，并传给前端
     * @param userid
     * @return
     */

    public List<ServiceStoreProceedEndModel> getServiceEnd(String userid) {
        //根据userid查询是否是厅经理
        List<ServiceHallModel> serviceHallModels = serviceHallDao.getStoreHallByUserId(userid);
        List<ServiceStoreProceedEndModel> serviceStoreProceedEndModelList =new ArrayList<ServiceStoreProceedEndModel>();
        if (serviceHallModels.size() != 0) {
            for (int i = 0; i < serviceHallModels.size(); i++) {
                String serviceStoreDitchCode = serviceHallModels.get(i).getServiceStoreDitchCode();
                String serviceStoreDitchName = serviceHallModels.get(i).getServiceStoreDitchName();
                //根据取到编码获取是否有待评价的条目
                List<ServiceStoreProceedEndModel> serviceStoreProceedEndModels = serviceHallDao.getServiceStoreEnd(serviceStoreDitchCode);
                if (serviceStoreProceedEndModels.size() != 0) {
                    for(int j = 0 ;j<serviceStoreProceedEndModels.size();j++){
                        serviceStoreProceedEndModels.get(j).setServiceStoreDitchCode(serviceStoreDitchName);
                        String useridstr = serviceStoreProceedEndModels.get(j).getServiceStoreUser();
                        //根据userid查询姓名
                        String username = serviceHallDao.getUserName(useridstr);
                        serviceStoreProceedEndModels.get(j).setServiceStoreUser(username);
                        serviceStoreProceedEndModelList.add( serviceStoreProceedEndModels.get(j));
                    }

                }

            }
            return serviceStoreProceedEndModelList;


        }
        return null;
    }

    //评价界面查找管理者执行表的线条类型
    public Integer getMytopicType(Integer serviceStoreMytopicId) {

        Integer type = serviceHallDao.getMytopicType(serviceStoreMytopicId);
        return type;
    }

    public List<String> getManaRodios(Integer name, Integer serviceStoreMytopicId, Integer type) {
        String rodiosStr = null;
        if(type ==1){
            rodiosStr =  serviceHallDao.getManaRodiosNetwork(serviceStoreMytopicId);
        }

        if (type ==2){
            rodiosStr = serviceHallDao.getManaRodiosMarket(serviceStoreMytopicId);
        }

        String[] rodios = rodiosStr.split("#@!");
        List<String> stringlist = new ArrayList<>();
        for(int i = 0;i<rodios.length;i++){

            if (i == name){
                String[] rodiosretuen = rodios[i].split("!@#");
                for(int j = 0;j<rodiosretuen.length;j++){
                    if (rodiosretuen[j] == null){
                        stringlist.add("0");
                    }else{
                        stringlist.add(rodiosretuen[j]);
                    }
                }

            }
        }
        return stringlist;
    }

    public List<String> getManageMessages(Integer name, Integer serviceStoreMytopicId, Integer type) {
        String messageStr = null;
        if(type ==1){
            messageStr =  serviceHallDao.getManaMessagesNetwork(serviceStoreMytopicId);
        }

        if (type ==2){
            messageStr = serviceHallDao.getManaMessagesMarket(serviceStoreMytopicId);
        }

        String[] messages = messageStr.split("#@!");
        List<String> stringlist = new ArrayList<>();
        for(int i = 0;i<messages.length;i++){
            if (i == name){
                String[] messageretuen = messages[i].split("!@#");
                for(int j = 0;j<messageretuen.length;j++){
                    if (messageretuen[j].equals("null")){
                        stringlist.add("未留下任何意见");
                    }else{
                        stringlist.add(messageretuen[j]);
                    }
                }

            }
        }
        return stringlist;
    }





    public List<List<ImageModel>> getManageImgs(Integer name, Integer serviceStoreMytopicId, Integer type) {
        String ImgsList = null;
        if(type ==1){
            ImgsList =  serviceHallDao.getManaImgsNetwork(serviceStoreMytopicId);
        }

        if (type ==2){
            ImgsList = serviceHallDao.getManaImgsMarket(serviceStoreMytopicId);
        }

        String[] imgs = ImgsList.split("!!@@##");
        for(int i = 0;i<imgs.length;i++){
            List<List<ImageModel>> imgList = new ArrayList<>();
            if (i == name){
                String[] imgsls = imgs[i].split("#@!");
                for(int j = 0;j<imgsls.length;j++){
                    String[] imgretrun = imgsls[j].split("!@#");
                    List<ImageModel> imageModels = new ArrayList<ImageModel>();
                    for(int k =0;k<imgretrun.length;k++){
                        int index1 = imgretrun[k].indexOf("=");
                        if(index1>0) {
                            String result = imgretrun[k].substring(index1 + 1,imgretrun[k].length()-1);
                            ImageModel imageModel = new ImageModel();
                            imageModel.setUrl(result);
                            imageModels.add(imageModel);
                        }

                    }

                    imgList.add(imageModels);

                }
                return imgList;
            }

        }

        return null;
    }


    //获取体验者执行表的按钮信息
    public String[] getTasteRodios(Integer serviceStoreMytopicId, Integer type) {
        String rodiosList =null;
        if(type ==1){
            rodiosList =  serviceHallDao.getTasteRodiosNetwork(serviceStoreMytopicId);
        }

        if(type ==2){
            rodiosList = serviceHallDao.getTasteRodiosmarket(serviceStoreMytopicId);
        }

        String[]  rodisretrun =  rodiosList.split("!@#");
        return rodisretrun;
    }
    //获取体验者执行表的input框的信息
    public String[] getTasteMessage(Integer serviceStoreMytopicId, Integer type) {
        String messageList= null;

        if(type ==1){
            messageList =  serviceHallDao.getTasteMessageNetwork(serviceStoreMytopicId);
        }

        if(type ==2){
            messageList = serviceHallDao.getTasteMessagemarket(serviceStoreMytopicId);
        }

        String[]  rodisretrun =  messageList.split("!@#");

        return rodisretrun;
    }

    public List<List<ImageModel>> getTasteImgs(Integer serviceStoreMytopicId, Integer type) {
        String Imgstr = null;

        if (type == 1){
            Imgstr =  serviceHallDao.getTasteImgsNetwork(serviceStoreMytopicId);
        }

        if (type ==2){
            Imgstr = serviceHallDao.getTasteImgsmarket(serviceStoreMytopicId);
        }

        String[] imgfstrs =  Imgstr.split("#@!");
        List<List<ImageModel>>  imglists = new ArrayList<>();
        for(int i =0;i<imgfstrs.length;i++){
            String imgs= imgfstrs[i];
            String[] imgstring = imgs.split("!@#");
            List<ImageModel> imageModels = new ArrayList<>();
            for(int j = 0;j<imgstring.length;j++){
                int index1 = imgstring[j].indexOf("=");
                if(index1>0) {
                    String result = imgstring[j].substring(index1 + 1,imgstring[j].length()-1);
                    ImageModel imageModel = new ImageModel();
                    imageModel.setUrl(result);
                    imageModels.add(imageModel);
                }
            }
            imglists.add(imageModels);
        }
        return imglists;
    }

    //根据课题ID获取课题的线条类型
     public Integer getSummarylistType(Integer serviceStoreMytopicId) {
        Integer type = serviceHallDao.getSummarylistType(serviceStoreMytopicId);

        return null;
    }

    public List<String> getSummaryMoren(Integer serviceStoreMytopicId, Integer type) {
        List<String> stringList = new ArrayList<>();
        if (type == 1){
            stringList =serviceHallDao.getSummaryMorenNetworl();
        }

        if (type  ==2){
            stringList = serviceHallDao.getSummaryMorenMarket();
        }
        return stringList;
    }

    public List<String> getSummartZidingYi(Integer serviceStoreMytopicId) {
        String zidingyi = serviceHallDao.getSummartZidingYi(serviceStoreMytopicId);
        String[] strArr = zidingyi.split("%\\^&\\*");
        List<String> stringLists = new ArrayList<>();
        for (int i = 1; i < strArr.length; i++) {
            if (strArr[i].endsWith(",")) {
                String newstr = strArr[i].substring(0, strArr[i].length() - 1);
                stringLists.add(newstr);
            } else {
                stringLists.add(strArr[i]);
            }
        }
        return  stringLists;
    }

    public String getSummaryMessage(Integer serviceStoreMytopicId) {
        String message = serviceHallDao.getSummaryMessage(serviceStoreMytopicId);
        System.out.println(message);
        return message;
    }

    public void saveJudge(Integer serviceStoreMytopicId, List<String> value) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0;i<value.size();i++){
            sb.append(value.get(i)).append(",");
        }
        String sb1 = sb+"";
        serviceHallDao.insertJudgeEnd();
        serviceHallDao.deleteJudge(serviceStoreMytopicId);
        serviceHallDao.saveJudge(serviceStoreMytopicId,sb1);
    }

    //判断是否有正在进行中的任务
    public RestResponseModel getServiceProceed(double lng, double lat, String userid) throws ParseException {
        RestResponseModel restResponseModel = new RestResponseModel();
        ServiceHallProceedModel serviceHallProceedModel = serviceHallDao.getServiceHallProceed(userid);


        if (serviceHallProceedModel == null){ //判断是否查询出有正在进行的站厅，没有则返回1
            restResponseModel.setCode(1);
            return restResponseModel;
        }else {
            String serviceStoreDitchCode = serviceHallProceedModel.getServiceStoreDitchCode();
            ServiceHallModel serviceHallModel = serviceHallDao.getServiceHallModel(serviceStoreDitchCode);
            if (serviceHallProceedModel.getServiceStoreClock() != null) {
                restResponseModel.setCode(10);
                restResponseModel.setData(serviceHallModel);
                return  restResponseModel;
            } else {
                double storeLng = serviceHallModel.getServiceStoreLong();
                double storeLat = serviceHallModel.getServiceStoreLat();
                double s = DistanceUtils.getDistance(lng, lat, storeLng, storeLat);
                System.out.println(s);
                String storeDate = serviceHallProceedModel.getServiceStoreDate();
                String storetime = serviceHallProceedModel.getServiceStoreTime();
                if (!ContrastUtils.contrastDateUtil(storeDate)) { //如果抢站的日期跟现在的日期不一致 则返回2
                    restResponseModel.setMsg(ContrastUtils.retrunTimeString(storeDate,storetime));
                    restResponseModel.setCode(2);
                    restResponseModel.setData(serviceHallProceedModel);
                    return restResponseModel;
                } else {
                    if (!ContrastUtils.contrastTimeUtil(storetime)) { //日期相等后，如果时间段不对则返回3
                        restResponseModel.setMsg(ContrastUtils.retrunTimeString(storeDate,storetime));
                        restResponseModel.setCode(3);
                        restResponseModel.setData(serviceHallProceedModel);
                        return restResponseModel;
                    } else { //时间段也对上了，就判断 距离是否小于0.6KM
                        if (s > 0.6) { //大于0.6 返回4
                            restResponseModel.setCode(4);
                            restResponseModel.setData(serviceHallProceedModel);
                            return restResponseModel;
                        } else {
                            restResponseModel.setCode(0);
                            restResponseModel.setData(serviceHallProceedModel);
                            return restResponseModel;
                        }
                    }
                }

            }
        }

    }

    public ServiceHallModel proceedClock(String serviceStoreDitchCode,String userid) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String clockDate = sdf.format(date);
        serviceHallDao.insertClockDate(clockDate,userid);
        ServiceHallModel serviceHallModel = serviceHallDao.proceedClock(serviceStoreDitchCode);

        return serviceHallModel;

    }

    public ServiceCountModel getGatherCount(String userid) {
        ServiceCountModel serviceCountModel = new ServiceCountModel();
        Integer grabCount = serviceHallDao.getGrabCount();
        Integer prolCount = serviceHallDao.getProlCount(userid);
        Integer judgeCount = serviceHallDao.getJudgeCount(userid);
        Integer endCount = 0;

        serviceCountModel.setGrabCount(grabCount);
        serviceCountModel.setProlCount(prolCount);
        serviceCountModel.setJudgeCount(judgeCount);
        serviceCountModel.setEndCount(endCount);
        return serviceCountModel;
    }
}

