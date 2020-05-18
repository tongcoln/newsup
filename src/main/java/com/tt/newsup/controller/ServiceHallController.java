package com.tt.newsup.controller;

import com.tt.newsup.model.*;
import com.tt.newsup.server.ServiceHallService;
import com.tt.newsup.utils.DistanceUtils;
import com.tt.newsup.utils.FileNameUtil;
import com.tt.newsup.utils.FileUploadUtil;
import jdk.nashorn.internal.ir.RuntimeNode;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
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
        System.out.println(serviceStoreTopicProceedModel != null);
        System.out.println(user);
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

    /**
     * 根据userid查询出抢单的站厅
     * @param userid
     * @return
     */
//    @RequestMapping("/getstoremess")
//    @CrossOrigin
//    public ServiceHallModel getstoremess(@RequestParam("userid") String userid){
//        ServiceHallModel serviceHallModel =  serviceHallService.getstoremess(userid);
//        return serviceHallModel;
//    }


    /**
     * 查询用户
     * @param user
     * @return
     */
    @RequestMapping("/getactive")
    @CrossOrigin
    public Integer getactive(String user){
        Integer active =  serviceHallService.getactive(user);
        return active;
    }

    /**
     * 查询服务营销动作表的项目名称
     * @param type
     * @return
     */

    @RequestMapping("/getmanagenamelist")
    @CrossOrigin
    public List<String> getmanagenamelist(@RequestParam("type")Integer type){
        List<String> stringList = null;
        if(type ==1){ //查询网络线条的项目名称
          stringList = serviceHallService.getmaagenetworkname();
        }

        if(type ==2){ //查询市场线条的项目名称
             stringList = serviceHallService.getmaagemarketname();
        }
        return stringList;
    }

    /**
     * 根据项目名称查询服务营销动作表的具体内容
     */
    @RequestMapping("/getmanagequestion")
    @CrossOrigin
    public List<String> getmanagequestion(@RequestParam("type") Integer type,
                                          @RequestParam("manageTableName") String manageTableName
    ){
        List<String> stringList = null;
        if(type == 1){
            stringList = serviceHallService.getmanagequestionnetwork(manageTableName);
        }

        if(type ==2){
            stringList = serviceHallService.getmanagequestionmarket(manageTableName);

        }
        return stringList;
    }


    @RequestMapping("/uploadmanageimg")
    @CrossOrigin
    public String uploadmanageimg(@RequestParam("file") MultipartFile file,
                                  @RequestParam("manageTableName")String manageTableName,
                                  @RequestParam("index") Integer index,
                                  @RequestParam("tableId")Integer tableId,
                                  @RequestParam("userid")String userid,
                                  HttpServletRequest request ){


        String localPath="/Users/mac/Documents/upload/"; //MAC路径
//        String localPath ="D:/images/";  //windows路径

        String fileName=file.getOriginalFilename();
        String newfileName = FileNameUtil.getFileName(fileName);

        if(FileUploadUtil.upload(file, localPath, newfileName)){
            // 将上传的文件写入到服务器端文件夹
            // 获取当前项目的完整url
            String requestURL = request.getRequestURL().toString();
            // 获取当前项目的请求路径url
            String requestURI = request.getRequestURI();
            // 得到去掉了uri的路径
            String url = requestURL.substring(0, requestURL.length()-requestURI.length() + 1)+localPath+newfileName;
            //  url= localPath+fileName;

            url = url.replaceAll("//Users/mac/Documents/upload/","/upload/");  //mac版本
            //url=url.replaceAll("D:/images/","upload/");

            System.out.println(url);
            serviceHallService.insermanageImage(manageTableName,index,tableId,url,userid);

            return "上传成功";

        }

        return "上传失败";
    }

    /**
     * 获取管理者执行表的图片
     */

    @RequestMapping("/getmanageImage")
    @CrossOrigin
    public List<List<ImageModel>> getmanageImage(@RequestParam("userid") String userid,
                                                 @RequestParam("tablename")String tablename
                                                    ){
        List<List<ImageModel>>  images =  serviceHallService.getmanageImage(userid,tablename);
        return images;

    }

    /**
     * 保存管理者临时答案
     */
    @RequestMapping("/saveAnswerBlank")
    @CrossOrigin
    public String saveAnswerBlank(@RequestParam("radio")List<String> radio,
                                  @RequestParam("message")List<String> message,
                                  @RequestParam("userid")String userid,
                                  @RequestParam("type")Integer type,
                                  @RequestParam("manageTableName")String manageTableName){
        serviceHallService.saveAnswerBlank(radio,message,userid,type,manageTableName);
        return null;
    }
    /**
     * 删除管理者执行表的图片
     */

    @RequestMapping("/delemanageimg")
    @CrossOrigin
    public String delemanageimg(@RequestParam("url")String  url,
                                @RequestParam("type")Integer type
                                ){

       serviceHallService.delemanageimg(url,type);

        return null;
    }

    @RequestMapping("/getmanageaswerblack")
    @CrossOrigin
    public List<ServiceManageAnswerModel> getmanageaswerblack(@RequestParam("userid")String userid,
                                      @RequestParam("manageTableName")String manageTableName,
                                      @RequestParam("type")Integer type
                                      ){

        List<ServiceManageAnswerModel> serviceManageAnswerModels =   serviceHallService.getmanageaswerblack(userid,manageTableName,type);
        return  serviceManageAnswerModels;
    }

    @RequestMapping("/savemanageAswer")
    @CrossOrigin
    public String savemanageAswer(@RequestParam("userid")String userid,
                                  @RequestParam("type")Integer type
                                ){

        String code = serviceHallService.savemanageAswer(userid,type);
        return code;
    }

    /**
     * 查询体验者执行表的问题列表
     */
    @RequestMapping("/gettastedata")
    @CrossOrigin
    public List<ServiceHallTasteQuestionModel> gettastedata(@RequestParam("type")Integer type){
        List<ServiceHallTasteQuestionModel> serviceHallTasteQuestionModels =  serviceHallService.gettastedata(type);

        return serviceHallTasteQuestionModels;
    }

    /**
     *保存体验者执行表中的图片
     *
     */
    @RequestMapping("/uploadtasteimg")
    @CrossOrigin
    public String uploadtasteimg(@RequestParam("file") MultipartFile file,
                                 @RequestParam("index") Integer index,
                                 @RequestParam("tableId")Integer tableId,
                                 @RequestParam("userid")String userid,
                                 HttpServletRequest request
                                 ){
        String localPath="/Users/mac/Documents/upload/"; //MAC路径
        //String localPath ="D:/images/";  //windows路径

        String fileName=file.getOriginalFilename();
        String newfileName = FileNameUtil.getFileName(fileName);

        if(FileUploadUtil.upload(file, localPath, newfileName)){
            // 将上传的文件写入到服务器端文件夹
            // 获取当前项目的完整url
            String requestURL = request.getRequestURL().toString();
            // 获取当前项目的请求路径url
            String requestURI = request.getRequestURI();
            // 得到去掉了uri的路径
            String url = requestURL.substring(0, requestURL.length()-requestURI.length() + 1)+localPath+newfileName;
            //  url= localPath+fileName;

            url = url.replaceAll("//Users/mac/Documents/upload/","/upload/");  //mac版本
            //url=url.replaceAll("D:/images/","upload/");

            System.out.println(url);
            serviceHallService.insertasteImage(index,tableId,url,userid);

            return "上传成功";

        }

        return "上传失败";
    }

    /**
     * 获取体验者执行表图片
     * @param userid
     * @return
     */
    @RequestMapping("/gettasteImage")
    @CrossOrigin
    public List<List<ImageModel>> gettasteImage(@RequestParam("userid") String userid
    ){
        System.out.println("开始");
        List<List<ImageModel>>  images =  serviceHallService.gettasteImage(userid);
        return images;
    }

    /**
     * 删除体验者执行表图片
     */

    @RequestMapping("/deletasteimg")
    @CrossOrigin
    public  void deletasteimg(@RequestParam("url")String url){
        serviceHallService.deletasteimg(url);
    }


    @RequestMapping("/savetasteaswer")
    @CrossOrigin
    public String saveTasteaswer(@RequestParam("userid")String userid,
                                 @RequestParam("radio")List<String> radio,
                                 @RequestParam("message")List<String> message,
                                 @RequestParam("type")Integer type
                                  ){
        String code = serviceHallService.insertTasteAnswer(userid,radio,message,type);



        return code;
    }



    @RequestMapping("/savesummary")
    @CrossOrigin
    public String saveSummary(@RequestParam("userid")String userid,
                              @RequestParam("type")Integer type,
                              @RequestParam("message")String message
                              ){

        String code = serviceHallService.saveSummary(userid,type,message);

        return code;

    }
    @RequestMapping("/saveproblemimgblank")
    @CrossOrigin
    public String saveProblemImgBlank(@RequestParam("file")MultipartFile file,HttpServletRequest request) {

        String localPath = "/Users/mac/Documents/upload/"; //MAC路径
        //String localPath ="D:/images/";  //windows路径

        String fileName = file.getOriginalFilename();
        String newfileName = FileNameUtil.getFileName(fileName);

        if (FileUploadUtil.upload(file, localPath, newfileName)) {
            // 将上传的文件写入到服务器端文件夹
            // 获取当前项目的完整url
            String requestURL = request.getRequestURL().toString();
            // 获取当前项目的请求路径url
            String requestURI = request.getRequestURI();
            // 得到去掉了uri的路径
            String url = requestURL.substring(0, requestURL.length() - requestURI.length() + 1) + localPath + newfileName;
            //  url= localPath+fileName;

            url = url.replaceAll("//Users/mac/Documents/upload/", "/upload/");  //mac版本
            //url=url.replaceAll("D:/images/","upload/");
            return url;
        }else {
            return "上传失败";
        }

    }

    //保存管理者执行表的一线直通车
    @RequestMapping("/savemanageproblem")
    @CrossOrigin
    public String saveManageProblem(@RequestParam("userid")String userid,
                                    @RequestParam("tablename")String tablename,
                                    @RequestParam("problemId")Integer problemId,
                                    @RequestParam("problemtitle")String problemtitle,
                                    @RequestParam("problemcontext")String problemcontext,
                                    @RequestParam("imgs")List<String> imgs
                                    ){
        String code = serviceHallService.saveManageProblem(userid,tablename,problemId,problemtitle,problemcontext,imgs);

        return code;
    }

    @RequestMapping("/savetasteproblem")
    @CrossOrigin
    public String saveTasteProblem(@RequestParam("userid")String userid,
                                   @RequestParam("problemId")Integer problemId,
                                   @RequestParam("problemtitle")String problemtitle,
                                   @RequestParam("problemcontext")String problemcontext,
                                   @RequestParam("imgs")List<String> imgs){

        String code = serviceHallService.saveTasteProblem(userid,problemId,problemtitle,problemcontext,imgs);
        return null;
    }

    @RequestMapping("/getjudgelist")
    @CrossOrigin
    public List<ServiceStoreProceedEndModel> getJudgeList(@RequestParam("userid")String userid){

        List<ServiceStoreProceedEndModel> serviceStoreProceedEndModels = serviceHallService.getServiceEnd(userid);


        return serviceStoreProceedEndModels;
    }


    //根据传过来的serviceStoreMytopicId查找课题线条是网络类还是市场类
    @RequestMapping("/judgetype")
    @CrossOrigin
    public Integer judgeType(@RequestParam("serviceStoreMytopicId")Integer serviceStoreMytopicId){
        Integer type = serviceHallService.getMytopicType(serviceStoreMytopicId);

        return type;
    }
    //获取评价表中的管理者执行表的按钮数组

    @RequestMapping("/getmanarodios")
    @CrossOrigin
    public List<String>  getManaRodios(@RequestParam("name")Integer name,
                                       @RequestParam("serviceStoreMytopicId")Integer serviceStoreMytopicId,
                                       @RequestParam("type")Integer type){

        List<String> rodisList = serviceHallService.getManaRodios(name,serviceStoreMytopicId,type);

        return rodisList;
    }

    //获取评价表中的管理执行表中的input框信息
    @RequestMapping("/getmanagemessages")
    @CrossOrigin
    public List<String> getManageMessages(@RequestParam("name")Integer name,
                                            @RequestParam("serviceStoreMytopicId")Integer serviceStoreMytopicId,
                                            @RequestParam("type")Integer type){

        List<String> messagelist = serviceHallService.getManageMessages(name,serviceStoreMytopicId,type);

        return messagelist;
    }


    //获取评价表中的管理执行表中的input框信息
    @RequestMapping("/getmamageimglist")
    @CrossOrigin
    public List<List<ImageModel>>getManageImgList(@RequestParam("name")Integer name,
                                          @RequestParam("serviceStoreMytopicId")Integer serviceStoreMytopicId,
                                          @RequestParam("type")Integer type){

        List<List<ImageModel>>manageImgList = serviceHallService.getManageImgs(name,serviceStoreMytopicId,type);

        return manageImgList;
    }

    @RequestMapping("/gettasterodios")
    @CrossOrigin
    public String[] getTasteRodios( @RequestParam("serviceStoreMytopicId")Integer serviceStoreMytopicId,
                                        @RequestParam("type")Integer type){

        String[] rodioList =  serviceHallService.getTasteRodios(serviceStoreMytopicId,type);
        return rodioList;
    }

    @RequestMapping("/gettastemessage")
    @CrossOrigin
    public String[] getTasteMessage(@RequestParam("serviceStoreMytopicId")Integer serviceStoreMytopicId,
                                    @RequestParam("type")Integer type){
        String[] messageList = serviceHallService.getTasteMessage(serviceStoreMytopicId,type);
        return messageList;
    }

    @RequestMapping("/gettasteimgs")
    @CrossOrigin
    public List<List<ImageModel>> getTasteImgs(@RequestParam("serviceStoreMytopicId")Integer serviceStoreMytopicId,
                                               @RequestParam("type")Integer type){

        List<List<ImageModel>> tasteImgs = serviceHallService.getTasteImgs(serviceStoreMytopicId,type);
        return tasteImgs;
    }


    @RequestMapping("/dingwei")
    @CrossOrigin
    public RestResponseModel dingwei(@RequestParam("lng")double lng,
                        @RequestParam("lat")double lat,
                        @RequestParam("userid")String userid
                        ) throws ParseException {
        //先查出有没有已抢单正在进行中的数据

        RestResponseModel restResponseModel = serviceHallService.getServiceProceed(lng,lat,userid);

        return  restResponseModel;
    }



    //获取课题总结表的线条类型
    @RequestMapping("/getsummarylisttype")
    @CrossOrigin
    public Integer getSummarylistType(@RequestParam("serviceStoreMytopicId")Integer serviceStoreMytopicId){


        Integer type = serviceHallService.getSummarylistType(serviceStoreMytopicId);

        return  type;

    }

    @RequestMapping("/getsummarymoren")
    @CrossOrigin
    public List<String> getSummaryMoren(@RequestParam("serviceStoreMytopicId")Integer serviceStoreMytopicId,
                                        @RequestParam("type")Integer type){
        List<String> Stringstr = serviceHallService.getSummaryMoren(serviceStoreMytopicId,type);
        return Stringstr;
    }

    @RequestMapping("/getsummartzidingyi")
    @CrossOrigin
    public List<String> getSummartZidingYi(@RequestParam("serviceStoreMytopicId")Integer serviceStoreMytopicId){

        List<String> stringList =  serviceHallService.getSummartZidingYi(serviceStoreMytopicId);
        return stringList;
    }

    @RequestMapping("/getsummarymessage")
    @CrossOrigin
    public String getSummaryMessage(@RequestParam("serviceStoreMytopicId")Integer serviceStoreMytopicId){
        String message = serviceHallService.getSummaryMessage(serviceStoreMytopicId);
        return message;
    }

    @RequestMapping("/savejudge")
    @CrossOrigin
    public void savejudge(@RequestParam("serviceStoreMytopicId")Integer serviceStoreMytopicId,
                          @RequestParam("value")List<String> value){
        serviceHallService.saveJudge(serviceStoreMytopicId,value);
    }


    @RequestMapping("/proceedclock")
    @CrossOrigin
    public ServiceHallModel proceedClock(@RequestParam("serviceStoreDitchCode")String serviceStoreDitchCode,
                                         @RequestParam("userid")String userid) throws ParseException {

        ServiceHallModel serviceHallModel = serviceHallService.proceedClock(serviceStoreDitchCode,userid);


        return serviceHallModel;


    }

    @RequestMapping("/getgathercount")
    @CrossOrigin
    public ServiceCountModel getGatherCount(@RequestParam("userid")String userid){

        ServiceCountModel serviceCountModel = serviceHallService.getGatherCount(userid);

        return  serviceCountModel;
    }

}
