package com.tt.newsup.server;

import com.tt.newsup.dao.InformationOpenDao;
import com.tt.newsup.enumutils.ponLine;
import com.tt.newsup.model.*;
import com.tt.newsup.utils.getAreaPhone;
import com.tt.newsup.utils.replaceString;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class InformationOpenService {

    @Autowired
    private InformationOpenDao informationOpenDao;

    /**
     * 查询一级界面
     * @return
     */
    public List<List<DataItemsModel>> getbossAll(String bossMerberNum,String bossGroupCoding,String bossSetName,String bossBusinessName,String informationLinkMan) {
        List<InformationOpenModel> informationOpenModelList = informationOpenDao.getbossAll(bossMerberNum,bossGroupCoding,bossSetName,bossBusinessName,informationLinkMan);
        List<List<DataItemsModel>> dataItemslist = new ArrayList<>();
        for(int i = 0;i<informationOpenModelList.size();i++){
            List<DataItemsModel> dataItemsModels = new ArrayList<>();
            String bossMerberNum1 = informationOpenModelList.get(i).getBossMerberNum(); //成员号码
            String bossBusinessTime = informationOpenModelList.get(i).getBossBusinessTime();//商机生成时间
            String bossDebugStart = informationOpenModelList.get(i).getBossDebugStart();
            String bossBusinessName1 = informationOpenModelList.get(i).getBossBusinessName();
            String ponStateRejected = informationOpenModelList.get(i).getPonStateRejected();
            String ponState = informationOpenModelList.get(i).getPonState();
            String prospectSate = informationOpenModelList.get(i).getProspectSate();
            String prospectStateReject = informationOpenModelList.get(i).getProspectStateReject();
            String ponProcessClassification  = informationOpenModelList.get(i).getPonProcessClassification();
            String prospectProcessClassification = informationOpenModelList.get(i).getProspectProcessClassification();
            DataItemsModel dataItemsModel = new DataItemsModel();
            dataItemsModel.setLabel("成员号码");
            dataItemsModel.setValue(bossMerberNum1);
            dataItemsModels.add(dataItemsModel);

            DataItemsModel dataItemsModel1 = new DataItemsModel();
            dataItemsModel1.setLabel("商机生成时间");
            dataItemsModel1.setValue(bossBusinessTime);
            dataItemsModels.add(dataItemsModel1);

            DataItemsModel dataItemsModel2 = new DataItemsModel();
            dataItemsModel2.setLabel("流程类型");
            if(!StringUtils.isBlank(ponProcessClassification)) {
                dataItemsModel2.setValue(ponProcessClassification);
            }else{
                dataItemsModel2.setValue(prospectProcessClassification);
            }
            dataItemsModels.add(dataItemsModel2);



            DataItemsModel dataItemsModel3 = new DataItemsModel();
            dataItemsModel3.setLabel("是否有资源");
            if(StringUtils.isBlank(bossDebugStart)){
                dataItemsModel3.setValue("有资源");
            }else {
                dataItemsModel3.setValue("无资源");
            }

            dataItemsModels.add(dataItemsModel3);



            DataItemsModel dataItemsModel4 = new DataItemsModel();
            dataItemsModel4.setLabel("专线当前状态");
            if("归档".equals(ponState) && (StringUtils.isBlank(ponStateRejected))){
                dataItemsModel4.setValue("已开通");
            }else if(("驳回").equals(ponStateRejected)){
                dataItemsModel4.setValue("现场开通被驳回");
            }else if("驳回".equals(prospectStateReject) ){
                dataItemsModel4.setValue("现场查勘被驳回");
            }else if(((StringUtils.isBlank(ponStateRejected) ) && "归档".equals(prospectSate) && (!"归档".equals(ponState))|| (StringUtils.isBlank(bossDebugStart)) && (StringUtils.isBlank(ponStateRejected)) && (!"归档".equals(ponState) )) ){
                dataItemsModel4.setValue("现场开通中");
            }else if(!StringUtils.isBlank(bossDebugStart)  && (!"归档".equals(prospectSate)) ){
                dataItemsModel4.setValue("现场查勘中");
            }else {
                dataItemsModel4.setValue("还未派发工单");
            }
            dataItemsModels.add(dataItemsModel4);

            DataItemsModel dataItemsModel5 = new DataItemsModel();
            dataItemsModel5.setLabel("商机名称");
            String bossBusinessName2 = replaceString.replaceString2Star(bossBusinessName1,11,0);
            dataItemsModel5.setValue(bossBusinessName2);
            dataItemsModels.add(dataItemsModel5);


            dataItemslist.add(dataItemsModels);
        }
        return dataItemslist;
    }


    /**
     * 获取工单流程
     * @param
     * @return
     */
    public List<ActivitiesModel> getliucheng(String bossMerberNum) {
        InformationOpenModel informationOpenModel = informationOpenDao.getliucheng(bossMerberNum);
        String openNetwokArea = informationOpenModel.getOpenNetworkArea();
        String ponProcessClassification = informationOpenModel.getPonProcessClassification(); //PON开通流程
        String ponState = informationOpenModel.getPonState();//PON现在状态-->>归档
        String ponEndTime = informationOpenModel.getPonEndTime(); // 开通工单结束时间
        String ponStateNow = informationOpenModel.getPonStateNow();// 开通阶段状态
        String ponStateRejected = informationOpenModel.getPonStateRejected(); //开通阶段是否被驳回
        String prospectExplorationTime = informationOpenModel.getProspectExplorationTime(); //到达现场查勘时间
        String prospectContractTime = informationOpenModel.getProspectContractTime();//到达合同签订时间
        String prospectConstructionTime = informationOpenModel.getProspectConstructionTime();//到达施工时间
        String ponOpenTime = informationOpenModel.getPonOpenTime();//到达开通时间
        String prospectProcessClassification = informationOpenModel.getProspectProcessClassification(); //查勘流程
        String prospectOrderTime = informationOpenModel.getProspectOrderTime();//查勘工单开始时间
        String prospectSate = informationOpenModel.getProspectSate(); //查勘工单状态 -->归档
        String prospectEndTime = informationOpenModel.getProspectEndTime(); //查勘工单结束时间
        String prospectStatwNow = informationOpenModel.getProspectStatwNow(); //查勘工单现在的状态
        String prospectStateReject = informationOpenModel.getProspectStateReject(); //查勘工单是否被驳回 -->驳回
        String informationPremierTime = informationOpenModel.getInformationPremierTime(); //开通工单首响时间
        String informationCilentOrderTime = informationOpenModel.getInformationCilentOrderTime(); //开通工单客户预约时间
        String informationOrderAcceptTime = informationOpenModel.getInformationOrderAcceptTime(); //开通工单接单时间
        String informationCompany = informationOpenModel.getInformationCompany();//施工公司
        String informationDesignRejectCause =informationOpenModel.getInformationDesignRejectCause();//查勘阶段,驳回原因
        String informationDredgeRejectCause = informationOpenModel.getInformationDredgeRejectCause();//开通阶段,驳回原因
        String bossBusinessName = informationOpenModel.getBossBusinessName(); //商机名称
        String bossSetName = informationOpenModel.getBossSetName();//创建人
        String bossBandwidth = informationOpenModel.getBossBandwidth(); //带宽
        String bossDebugStart = informationOpenModel.getBossDebugStart(); //查勘开始时间
        String contactsInstallCompany = informationOpenModel.getContactsInstallCompany(); //装机人姓名
        String contactsPhone = informationOpenModel.getContactsPhone(); //装机人电话
        String bossBusinessTime = informationOpenModel.getBossBusinessTime();
        String bossLineName = informationOpenModel.getBossLineName();
        String areaPone = getAreaPhone.getPhone(openNetwokArea);
        String buildname = getAreaPhone.getBuildPhone(openNetwokArea);
        List<ActivitiesModel> activitiesModels = new ArrayList<>();
        if(StringUtils.isBlank(bossDebugStart)) {//有资源
            if (StringUtils.isBlank(ponProcessClassification)) { //判断还未派单
                ActivitiesModel activitiesModel = new ActivitiesModel();
                activitiesModel.setContent("还未派单，等待派单");
                activitiesModel.setColor("red");
                activitiesModel.setIcon("el-icon-close");
                activitiesModel.setSize("large");
                activitiesModel.setTimestamp(bossBusinessName);
                activitiesModels.add(activitiesModel);

            }//判断没有判断的情况
            if ("归档".equals(ponState) && !"驳回".equals(ponStateRejected)) {  //判断有资源已开通的专线
                if("本地PON专线开通流程".equals(ponProcessClassification)){
                    ActivitiesModel activitiesModel = new ActivitiesModel();
                    String content ="商机生成,"+"派单时间:"+bossBusinessTime+",专线名称:"+bossLineName+",带宽:"+bossBandwidth;
                    activitiesModel.setContent(content);//呈现内容
                    activitiesModel.setColor("green"); //颜色
                    activitiesModel.setTimestamp(bossBusinessName); //时间或提示信息
                    activitiesModel.setIcon("el-icon-download");//图标
                    activitiesModel.setSize("large");//大小
                    activitiesModels.add(activitiesModel);


                    ActivitiesModel activitiesModel1 = new ActivitiesModel();
                    String content1 ="方案设计";
                    String contract1 = "环节联系人:"+ ponLine.ZONGDIAO.getName()+",联系电话:"+ponLine.ZONGDIAO.getPhone();
                    activitiesModel1.setContent(content1);
                    activitiesModel1.setTimestamp(contract1);
                    activitiesModel1.setColor("green");
                    activitiesModel1.setIcon("el-icon-check");
                    activitiesModel1.setSize("large");
                    activitiesModels.add(activitiesModel1);

                    ActivitiesModel activitiesModel2 = new ActivitiesModel();
                    String content2 ="核心网数据制作";
                    String contract2 = "环节联系人:"+ ponLine.ZONGDIAO.getName()+",联系电话:"+ponLine.ZONGDIAO.getPhone();
                    activitiesModel2.setTimestamp(contract2);
                    activitiesModel2.setContent(content2);
                    activitiesModel2.setColor("green");
                    activitiesModel2.setIcon("el-icon-check");
                    activitiesModel2.setSize("large");
                    activitiesModels.add(activitiesModel2);

                    ActivitiesModel activitiesModel3 = new ActivitiesModel();
                    String content3 ="PON网管数据制作";
                    String contract3 = "环节联系人:"+ ponLine.ZONGDIAO.getName()+",联系电话:"+ponLine.ZONGDIAO.getPhone();
                    activitiesModel3.setContent(content3);
                    activitiesModel3.setTimestamp(contract3);
                    activitiesModel3.setColor("green");
                    activitiesModel3.setIcon("el-icon-check");
                    activitiesModel3.setSize("large");
                    activitiesModels.add(activitiesModel3);

                    ActivitiesModel activitiesModel4 = new ActivitiesModel();
                    String content4 = "装机人员首响";
                    activitiesModel4.setContent(content4);
                    String contract4 = "环节联系人:"+ contactsInstallCompany+",联系电话:"+contactsPhone+",到达环节时间:"+informationPremierTime;
                    activitiesModel4.setSize("large");
                    activitiesModel4.setTimestamp(contract4);
                    activitiesModel4.setColor("green");
                    activitiesModel4.setIcon("el-icon-check");
                    activitiesModels.add(activitiesModel4);

                    ActivitiesModel activitiesModel5 = new ActivitiesModel();
                    String conten5 = "客户已预约";
                    String  contentbc ="客户预约时间:"+informationCilentOrderTime;
                    String contract5 = "环节联系人:"+ contactsInstallCompany+",联系电话:"+contactsPhone+",到达环节时间:"+contentbc;
                    activitiesModel5.setContent(conten5);
                    activitiesModel5.setSize("large");
                    activitiesModel5.setTimestamp(contract5);
                    activitiesModel5.setColor("green");
                    activitiesModel5.setIcon("el-icon-check");
                    activitiesModels.add(activitiesModel5);

                    ActivitiesModel activitiesModel6 = new ActivitiesModel();
                    String conten6 = "装机完成";
                    activitiesModel6.setContent(conten6);
                    activitiesModel6.setTimestamp(ponEndTime);
                    activitiesModel6.setColor("green");
                    activitiesModel6.setIcon("el-icon-check");
                    activitiesModel6.setSize("large");
                    activitiesModels.add(activitiesModel6);

                    ActivitiesModel activitiesModel7 = new ActivitiesModel();
                    String conten7 = "归档";
                    activitiesModel7.setContent(conten7);
                    activitiesModel7.setTimestamp(ponEndTime);
                    activitiesModel7.setColor("green");
                    activitiesModel7.setIcon("el-icon-remove-outline");
                    activitiesModel7.setSize("large");
                    activitiesModels.add(activitiesModel7);

                }

                if("本地非PON专线开通流程".equals(ponProcessClassification)){
                    ActivitiesModel activitiesModel = new ActivitiesModel();
                    String content ="商机生成,"+"派单时间:"+bossBusinessTime+",专线名称:"+bossLineName+",带宽:"+bossBandwidth;
                    activitiesModel.setContent(content);//呈现内容
                    activitiesModel.setColor("green"); //颜色
                    activitiesModel.setTimestamp(bossBusinessName); //时间或提示信息
                    activitiesModel.setIcon("el-icon-download");//图标
                    activitiesModel.setSize("large");//大小
                    activitiesModels.add(activitiesModel);

                    ActivitiesModel activitiesModel1 = new ActivitiesModel();
                    String content1 ="方案设计";
                    String contract1 = areaPone;
                    activitiesModel1.setTimestamp(contract1);
                    activitiesModel1.setContent(content1);
                    activitiesModel1.setColor("green");
                    activitiesModel1.setIcon("el-icon-check");
                    activitiesModel1.setSize("large");
                    activitiesModels.add(activitiesModel1);

                    ActivitiesModel activitiesModel2 = new ActivitiesModel();
                    String content2 ="数据制作";
                    String contract2 = areaPone;
                    activitiesModel2.setContent(content2);
                    activitiesModel2.setTimestamp(contract2);
                    activitiesModel2.setColor("green");
                    activitiesModel2.setIcon("el-icon-check");
                    activitiesModel2.setSize("large");
                    activitiesModels.add(activitiesModel2);

                    ActivitiesModel activitiesModel3 = new ActivitiesModel();
                    String content3 ="光、电路调度";
                    activitiesModel3.setContent(content3);
                    String contract3 =  areaPone;
                    activitiesModel3.setColor("green");
                    activitiesModel3.setTimestamp(contract3);
                    activitiesModel3.setIcon("el-icon-check");
                    activitiesModel3.setSize("large");
                    activitiesModels.add(activitiesModel3);

                    ActivitiesModel activitiesModel4 = new ActivitiesModel();
                    String content4 = "装机人员首响";
                    String contract4 = "环节联系人:"+ contactsInstallCompany+",联系电话:"+contactsPhone+",到达环节时间:"+informationPremierTime;
                    activitiesModel4.setContent(content4);
                    activitiesModel4.setSize("large");
                    activitiesModel4.setTimestamp(contract4);
                    activitiesModel4.setColor("green");
                    activitiesModel4.setIcon("el-icon-check");
                    activitiesModels.add(activitiesModel4);

                    ActivitiesModel activitiesModel5 = new ActivitiesModel();
                    String conten5 = "客户已预约";
                    String contentbc ="客户预约时间:"+informationCilentOrderTime;
                    String contract5 = "环节联系人:"+ contactsInstallCompany+",联系电话:"+contactsPhone+",到达环节时间:"+contentbc;
                    activitiesModel5.setContent(conten5);
                    activitiesModel5.setSize("large");
                    activitiesModel5.setTimestamp(contract5);
                    activitiesModel5.setColor("green");
                    activitiesModel5.setIcon("el-icon-check");
                    activitiesModels.add(activitiesModel5);

                    ActivitiesModel activitiesModel6 = new ActivitiesModel();
                    String conten6 = "装机完成";
                    activitiesModel6.setContent(conten6);
                    activitiesModel6.setTimestamp(ponEndTime);
                    activitiesModel6.setColor("green");
                    activitiesModel6.setIcon("el-icon-check");
                    activitiesModel6.setSize("large");
                    activitiesModels.add(activitiesModel6);

                    ActivitiesModel activitiesModel7 = new ActivitiesModel();
                    String conten7 = "归档";
                    activitiesModel7.setContent(conten7);
                    activitiesModel7.setTimestamp(ponEndTime);
                    activitiesModel7.setColor("green");
                    activitiesModel7.setIcon("el-icon-remove-outline");
                    activitiesModel7.setSize("large");
                    activitiesModels.add(activitiesModel7);

                }

            } //判断有资源已开通了的流程
            if(!"驳回".equals(ponStateRejected) && !"归档".equals(ponState)) {//运行中的工单 //pon网管数据制作(自动激活)
                if ("本地PON专线开通流程".equals(ponProcessClassification)) {
                    if (ponStateNow.contains("核心网")) {
                        ActivitiesModel activitiesModel = new ActivitiesModel();
                        String content = "商机生成," + "派单时间:" + bossBusinessTime + ",专线名称:" + bossLineName + ",带宽:" + bossBandwidth;
                        activitiesModel.setContent(content);//呈现内容
                        activitiesModel.setColor("green"); //颜色
                        activitiesModel.setTimestamp(bossBusinessName); //时间或提示信息
                        activitiesModel.setIcon("el-icon-download");//图标
                        activitiesModel.setSize("large");//大小
                        activitiesModels.add(activitiesModel);


                        ActivitiesModel activitiesModel1 = new ActivitiesModel();
                        String content1 ="方案设计";
                        String contract1 = "环节联系人:"+ ponLine.ZONGDIAO.getName()+",联系电话:"+ponLine.ZONGDIAO.getPhone();
                        activitiesModel1.setContent(content1);
                        activitiesModel1.setTimestamp(contract1);
                        activitiesModel1.setColor("green");
                        activitiesModel1.setIcon("el-icon-check");
                        activitiesModel1.setSize("large");
                        activitiesModels.add(activitiesModel1);


                        ActivitiesModel activitiesModel2 = new ActivitiesModel();
                        String content2 = "核心网数据制作中";
                        String contract2 = "环节联系人:"+ ponLine.ZONGDIAO.getName()+",联系电话:"+ponLine.ZONGDIAO.getPhone();
                        activitiesModel2.setContent(content2);
                        activitiesModel2.setTimestamp(contract2);
                        activitiesModel2.setIcon("el-icon-loading");
                        activitiesModel2.setSize("large");
                        activitiesModels.add(activitiesModel2);
                    }
                    if (ponStateNow.contains("pon网管")) {
                        ActivitiesModel activitiesModel = new ActivitiesModel();
                        String content = "商机生成," + "派单时间:" + bossBusinessTime + ",专线名称:" + bossLineName + ",带宽:" + bossBandwidth;
                        activitiesModel.setContent(content);//呈现内容
                        activitiesModel.setColor("green"); //颜色
                        activitiesModel.setTimestamp(bossBusinessName); //时间或提示信息
                        activitiesModel.setIcon("el-icon-download");//图标
                        activitiesModel.setSize("large");//大小
                        activitiesModels.add(activitiesModel);


                        ActivitiesModel activitiesModel1 = new ActivitiesModel();
                        String content1 = "方案设计";
                        String contract1 = "环节联系人:"+ ponLine.ZONGDIAO.getName()+",联系电话:"+ponLine.ZONGDIAO.getPhone();
                        activitiesModel1.setContent(content1);
                        activitiesModel1.setTimestamp(contract1);
                        activitiesModel1.setColor("green");
                        activitiesModel1.setIcon("el-icon-check");
                        activitiesModel1.setSize("large");
                        activitiesModels.add(activitiesModel1);

                        ActivitiesModel activitiesModel2 = new ActivitiesModel();
                        String content2 = "核心网数据制作";
                        String contract2 = "环节联系人:"+ ponLine.ZONGDIAO.getName()+",联系电话:"+ponLine.ZONGDIAO.getPhone();
                        activitiesModel2.setContent(content2);
                        activitiesModel2.setTimestamp(contract2);
                        activitiesModel2.setColor("green");
                        activitiesModel2.setIcon("el-icon-check");
                        activitiesModel2.setSize("large");
                        activitiesModels.add(activitiesModel2);

                        ActivitiesModel activitiesModel3 = new ActivitiesModel();
                        String content3 = "PON网管数据制作";
                        String contract3 = "环节联系人:"+ ponLine.ZONGDIAO.getName()+",联系电话:"+ponLine.ZONGDIAO.getPhone();
                        activitiesModel3.setContent(content3);
                        activitiesModel3.setTimestamp(contract3);
                        activitiesModel3.setColor("green");
                        activitiesModel3.setIcon("el-icon-loading");
                        activitiesModel3.setSize("large");
                        activitiesModels.add(activitiesModel3);
                    }
                    if (ponStateNow.contains("现场开通")) {
                        if (StringUtils.isBlank(informationPremierTime) && StringUtils.isBlank(informationCilentOrderTime) ) {
                            ActivitiesModel activitiesModel = new ActivitiesModel();
                            String content = "商机生成," + "派单时间:" + bossBusinessTime + ",专线名称:" + bossLineName + ",带宽:" + bossBandwidth;
                            activitiesModel.setContent(content);//呈现内容
                            activitiesModel.setColor("green"); //颜色
                            activitiesModel.setTimestamp(bossBusinessName); //时间或提示信息
                            activitiesModel.setIcon("el-icon-download");//图标
                            activitiesModel.setSize("large");//大小
                            activitiesModels.add(activitiesModel);


                            ActivitiesModel activitiesModel1 = new ActivitiesModel();
                            String content1 = "方案设计";
                            String contract1 = "环节联系人:"+ ponLine.ZONGDIAO.getName()+",联系电话:"+ponLine.ZONGDIAO.getPhone();
                            activitiesModel1.setContent(content1);
                            activitiesModel1.setTimestamp(contract1);
                            activitiesModel1.setColor("green");
                            activitiesModel1.setIcon("el-icon-check");
                            activitiesModel1.setSize("large");
                            activitiesModels.add(activitiesModel1);

                            ActivitiesModel activitiesModel2 = new ActivitiesModel();
                            String content2 = "核心网数据制作";
                            String contract2 = "环节联系人:"+ ponLine.ZONGDIAO.getName()+",联系电话:"+ponLine.ZONGDIAO.getPhone();
                            activitiesModel2.setContent(content2);
                            activitiesModel2.setTimestamp(contract2);
                            activitiesModel2.setColor("green");
                            activitiesModel2.setIcon("el-icon-check");
                            activitiesModel2.setSize("large");
                            activitiesModels.add(activitiesModel2);

                            ActivitiesModel activitiesModel3 = new ActivitiesModel();
                            String content3 ="PON网管数据制作";
                            String contract3 = "环节联系人:"+ ponLine.ZONGDIAO.getName()+",联系电话:"+ponLine.ZONGDIAO.getPhone();
                            activitiesModel3.setContent(content3);
                            activitiesModel3.setTimestamp(contract3);
                            activitiesModel3.setColor("green");
                            activitiesModel3.setIcon("el-icon-check");
                            activitiesModel3.setSize("large");
                            activitiesModels.add(activitiesModel3);

                            ActivitiesModel activitiesModel4 = new ActivitiesModel();
                            String content4 = "等待装机人员响应";
                            activitiesModel4.setContent(content4);
                            activitiesModel4.setSize("large");
                            activitiesModel4.setIcon("el-icon-loading");
                            activitiesModels.add(activitiesModel4);
                        }

                        if (!StringUtils.isBlank(informationPremierTime) && StringUtils.isBlank(informationCilentOrderTime)) {
                            ActivitiesModel activitiesModel = new ActivitiesModel();
                            String content = "商机生成," + "派单时间:" + bossBusinessTime + ",专线名称:" + bossLineName + ",带宽:" + bossBandwidth;
                            activitiesModel.setContent(content);//呈现内容
                            activitiesModel.setColor("green"); //颜色
                            activitiesModel.setTimestamp(bossBusinessName); //时间或提示信息
                            activitiesModel.setIcon("el-icon-download");//图标
                            activitiesModel.setSize("large");//大小
                            activitiesModels.add(activitiesModel);


                            ActivitiesModel activitiesModel1 = new ActivitiesModel();
                            String content1 = "方案设计";
                            String contract1 = "环节联系人:"+ ponLine.ZONGDIAO.getName()+",联系电话:"+ponLine.ZONGDIAO.getPhone();
                            activitiesModel1.setContent(content1);
                            activitiesModel1.setTimestamp(contract1);
                            activitiesModel1.setColor("green");
                            activitiesModel1.setIcon("el-icon-check");
                            activitiesModel1.setSize("large");
                            activitiesModels.add(activitiesModel1);

                            ActivitiesModel activitiesModel2 = new ActivitiesModel();
                            String content2 = "核心网数据制作";
                            String contract2 = "环节联系人:"+ ponLine.ZONGDIAO.getName()+",联系电话:"+ponLine.ZONGDIAO.getPhone();
                            activitiesModel2.setContent(content2);
                            activitiesModel2.setTimestamp(contract2);
                            activitiesModel2.setColor("green");
                            activitiesModel2.setIcon("el-icon-check");
                            activitiesModel2.setSize("large");
                            activitiesModels.add(activitiesModel2);

                            ActivitiesModel activitiesModel3 = new ActivitiesModel();
                            String content3 = "PON网管数据制作";
                            String contract3 = "环节联系人:"+ ponLine.ZONGDIAO.getName()+",联系电话:"+ponLine.ZONGDIAO.getPhone();
                            activitiesModel3.setContent(content3);
                            activitiesModel3.setTimestamp(contract3);
                            activitiesModel3.setColor("green");
                            activitiesModel3.setIcon("el-icon-check");
                            activitiesModel3.setSize("large");
                            activitiesModels.add(activitiesModel3);

                            ActivitiesModel activitiesModel4 = new ActivitiesModel();
                            String content4 = "装机人员首响";
                            String contract4 = "环节联系人:"+ contactsInstallCompany+",联系电话:"+contactsPhone+",到达环节时间:"+informationPremierTime;
                            activitiesModel4.setContent(content4);
                            activitiesModel4.setSize("large");
                            activitiesModel4.setTimestamp(contract4);
                            activitiesModel4.setColor("green");
                            activitiesModel4.setIcon("el-icon-check");
                            activitiesModels.add(activitiesModel4);

                            ActivitiesModel activitiesModel5 = new ActivitiesModel();
                            String conten5 = "与客户确认装机时间中";
                            String contract5 = "环节联系人:"+ contactsInstallCompany+",联系电话:"+contactsPhone+",到达环节时间:";
                            activitiesModel5.setContent(contract5);
                            activitiesModel5.setSize("large");
                            activitiesModel5.setIcon("el-icon-loading");
                            activitiesModels.add(activitiesModel5);
                        }

                        if (!StringUtils.isBlank(informationCilentOrderTime)) {
                            ActivitiesModel activitiesModel = new ActivitiesModel();
                            String content = "商机生成," + "派单时间:" + bossBusinessTime + ",专线名称:" + bossLineName + ",带宽:" + bossBandwidth;
                            activitiesModel.setContent(content);//呈现内容
                            activitiesModel.setColor("green"); //颜色
                            activitiesModel.setTimestamp(bossBusinessName); //时间或提示信息
                            activitiesModel.setIcon("el-icon-download");//图标
                            activitiesModel.setSize("large");//大小
                            activitiesModels.add(activitiesModel);


                            ActivitiesModel activitiesModel1 = new ActivitiesModel();
                            String content1 = "方案设计";
                            String contract1 = "环节联系人:"+ ponLine.ZONGDIAO.getName()+",联系电话:"+ponLine.ZONGDIAO.getPhone();
                            activitiesModel1.setContent(content1);
                            activitiesModel1.setTimestamp(contract1);
                            activitiesModel1.setColor("green");
                            activitiesModel1.setIcon("el-icon-check");
                            activitiesModel1.setSize("large");
                            activitiesModels.add(activitiesModel1);

                            ActivitiesModel activitiesModel2 = new ActivitiesModel();
                            String content2 = "核心网数据制作";
                            String contract2 = "环节联系人:"+ ponLine.ZONGDIAO.getName()+",联系电话:"+ponLine.ZONGDIAO.getPhone();
                            activitiesModel2.setContent(content2);
                            activitiesModel2.setTimestamp(contract2);
                            activitiesModel2.setColor("green");
                            activitiesModel2.setIcon("el-icon-check");
                            activitiesModel2.setSize("large");
                            activitiesModels.add(activitiesModel2);

                            ActivitiesModel activitiesModel3 = new ActivitiesModel();
                            String content3 = "PON网管数据制作";
                            String contract3 = "环节联系人:"+ ponLine.ZONGDIAO.getName()+",联系电话:"+ponLine.ZONGDIAO.getPhone();
                            activitiesModel3.setContent(content3);
                            activitiesModel3.setTimestamp(contract3);
                            activitiesModel3.setColor("green");
                            activitiesModel3.setIcon("el-icon-check");
                            activitiesModel3.setSize("large");
                            activitiesModels.add(activitiesModel3);

                            ActivitiesModel activitiesModel4 = new ActivitiesModel();
                            String content4 = "装机人员首响";
                            String contract4 = "环节联系人:"+ contactsInstallCompany+",联系电话:"+contactsPhone+",到达环节时间:"+informationPremierTime;
                            activitiesModel4.setContent(content4);
                            activitiesModel4.setSize("large");
                            activitiesModel4.setTimestamp(contract4);
                            activitiesModel4.setColor("green");
                            activitiesModel4.setIcon("el-icon-check");
                            activitiesModels.add(activitiesModel4);

                            ActivitiesModel activitiesModel5 = new ActivitiesModel();
                            String conten5 = "客户已预约";
                            String contentbc ="客户预约时间:"+informationCilentOrderTime;
                            String contract5 = "环节联系人:"+ contactsInstallCompany+",联系电话:"+contactsPhone+",到达环节时间:"+contentbc;
                            activitiesModel5.setContent(conten5);
                            activitiesModel5.setSize("large");
                            activitiesModel5.setTimestamp(contract5);
                            activitiesModel5.setColor("green");
                            activitiesModel5.setIcon("el-icon-check");
                            activitiesModels.add(activitiesModel5);

                            ActivitiesModel activitiesModel6 = new ActivitiesModel();
                            String conten6 = "等待装机";
                            String contract6 = "环节联系人:"+ contactsInstallCompany+",联系电话:"+contactsPhone;
                            activitiesModel6.setContent(conten6);
                            activitiesModel6.setTimestamp(contract6);
                            activitiesModel6.setIcon("el-icon-loading");
                            activitiesModel6.setSize("large");
                            activitiesModels.add(activitiesModel6);

                        }
                    }
                }

                if ("本地非PON专线开通流程".equals(ponProcessClassification)) {
                    if (ponStateNow.contains("方案设计")) {
                        ActivitiesModel activitiesModel = new ActivitiesModel();
                        String content = "商机生成," + "派单时间:" + bossBusinessTime + ",专线名称:" + bossLineName + ",带宽:" + bossBandwidth;
                        activitiesModel.setContent(content);//呈现内容
                        activitiesModel.setColor("green"); //颜色
                        activitiesModel.setTimestamp(bossBusinessName); //时间或提示信息
                        activitiesModel.setIcon("el-icon-download");//图标
                        activitiesModel.setSize("large");//大小
                        activitiesModels.add(activitiesModel);

                        ActivitiesModel activitiesModel1 = new ActivitiesModel();
                        String content1 = "方案设计中";
                        String contract1 = areaPone;
                        activitiesModel1.setContent(content1);
                        activitiesModel1.setTimestamp(contract1);
                        activitiesModel1.setIcon("el-icon-loading");
                        activitiesModel1.setSize("large");
                        activitiesModels.add(activitiesModel1);
                    }

                    if (ponStateNow.contains("数据制作")) {
                        ActivitiesModel activitiesModel = new ActivitiesModel();
                        String content = "商机生成," + "派单时间:" + bossBusinessTime + ",专线名称:" + bossLineName + ",带宽:" + bossBandwidth;
                        activitiesModel.setContent(content);//呈现内容
                        activitiesModel.setColor("green"); //颜色
                        activitiesModel.setTimestamp(bossBusinessName); //时间或提示信息
                        activitiesModel.setIcon("el-icon-download");//图标
                        activitiesModel.setSize("large");//大小
                        activitiesModels.add(activitiesModel);

                        ActivitiesModel activitiesModel1 = new ActivitiesModel();
                        String content1 = "方案设计";
                        String contract1 = areaPone;
                        activitiesModel1.setContent(content1);
                        activitiesModel1.setTimestamp(contract1);
                        activitiesModel1.setColor("green");
                        activitiesModel1.setIcon("el-icon-check");
                        activitiesModel1.setSize("large");
                        activitiesModels.add(activitiesModel1);

                        ActivitiesModel activitiesModel2 = new ActivitiesModel();
                        String content2 = "数据制作中";
                        String contract2 = areaPone;
                        activitiesModel2.setContent(content2);
                        activitiesModel2.setTimestamp(contract2);
                        activitiesModel2.setIcon("el-icon-loading");
                        activitiesModel2.setSize("large");
                        activitiesModels.add(activitiesModel2);
                    }

                    if (ponStateNow.contains("调度")) {
                        ActivitiesModel activitiesModel = new ActivitiesModel();
                        String content = "商机生成," + "派单时间:" + bossBusinessTime + ",专线名称:" + bossLineName + ",带宽:" + bossBandwidth;
                        activitiesModel.setContent(content);//呈现内容
                        activitiesModel.setColor("green"); //颜色
                        activitiesModel.setTimestamp(bossBusinessName); //时间或提示信息
                        activitiesModel.setIcon("el-icon-download");//图标
                        activitiesModel.setSize("large");//大小
                        activitiesModels.add(activitiesModel);

                        ActivitiesModel activitiesModel1 = new ActivitiesModel();
                        String content1 = "方案设计";
                        String contract1 = areaPone;
                        activitiesModel1.setContent(content1);
                        activitiesModel1.setTimestamp(contract1);
                        activitiesModel1.setColor("green");
                        activitiesModel1.setIcon("el-icon-check");
                        activitiesModel1.setSize("large");
                        activitiesModels.add(activitiesModel1);

                        ActivitiesModel activitiesModel2 = new ActivitiesModel();
                        String content2 = "数据制作";
                        String contract2 = areaPone;
                        activitiesModel2.setContent(content2);
                        activitiesModel2.setTimestamp(contract2);
                        activitiesModel2.setColor("green");
                        activitiesModel2.setIcon("el-icon-check");
                        activitiesModel2.setSize("large");
                        activitiesModels.add(activitiesModel2);

                        ActivitiesModel activitiesModel3 = new ActivitiesModel();
                        String content3 = "光、电路调度中";
                        String contract3 =  areaPone;
                        activitiesModel3.setContent(content3);
                        activitiesModel3.setTimestamp(contract3);
                        activitiesModel3.setIcon("el-icon-loading");
                        activitiesModel3.setSize("large");
                        activitiesModels.add(activitiesModel3);
                    }

                    if (ponStateNow.contains("现场开通")) {
                        if (StringUtils.isBlank(informationPremierTime) && StringUtils.isBlank(informationCilentOrderTime)  && StringUtils.isBlank(ponEndTime)) {
                            ActivitiesModel activitiesModel = new ActivitiesModel();
                            String content ="商机生成,"+"派单时间:"+bossBusinessTime+",专线名称:"+bossLineName+",带宽:"+bossBandwidth;
                            activitiesModel.setContent(content);//呈现内容
                            activitiesModel.setColor("green"); //颜色
                            activitiesModel.setTimestamp(bossBusinessName); //时间或提示信息
                            activitiesModel.setIcon("el-icon-download");//图标
                            activitiesModel.setSize("large");//大小
                            activitiesModels.add(activitiesModel);

                            ActivitiesModel activitiesModel1 = new ActivitiesModel();
                            String content1 ="方案设计";
                            String contract1 = areaPone;
                            activitiesModel1.setContent(content1);
                            activitiesModel1.setTimestamp(contract1);
                            activitiesModel1.setColor("green");
                            activitiesModel1.setIcon("el-icon-check");
                            activitiesModel1.setSize("large");
                            activitiesModels.add(activitiesModel1);

                            ActivitiesModel activitiesModel2 = new ActivitiesModel();
                            String content2 ="数据制作";
                            String contract2 = areaPone;
                            activitiesModel2.setContent(content2);
                            activitiesModel2.setTimestamp(contract2);
                            activitiesModel2.setColor("green");
                            activitiesModel2.setIcon("el-icon-check");
                            activitiesModel2.setSize("large");
                            activitiesModels.add(activitiesModel2);

                            ActivitiesModel activitiesModel3 = new ActivitiesModel();
                            String content3 ="光、电路调度";
                            String contract3 =  areaPone;
                            activitiesModel3.setContent(content3);
                            activitiesModel3.setTimestamp(contract3);
                            activitiesModel3.setColor("green");
                            activitiesModel3.setIcon("el-icon-check");
                            activitiesModel3.setSize("large");
                            activitiesModels.add(activitiesModel3);

                            ActivitiesModel activitiesModel4 = new ActivitiesModel();
                            String content4 = "等待装机人员响应";
                            String contract4 = "环节联系人:"+ contactsInstallCompany+",联系电话:"+contactsPhone;
                            activitiesModel4.setContent(content4);
                            activitiesModel4.setTimestamp(contract4);
                            activitiesModel4.setSize("large");
                            activitiesModel4.setIcon("el-icon-loading");
                            activitiesModels.add(activitiesModel4);
                        }

                        if (StringUtils.isBlank(informationCilentOrderTime) && !StringUtils.isBlank(informationPremierTime)) {
                            ActivitiesModel activitiesModel = new ActivitiesModel();
                            String content ="商机生成,"+"派单时间:"+bossBusinessTime+",专线名称:"+bossLineName+",带宽:"+bossBandwidth;
                            activitiesModel.setContent(content);//呈现内容
                            activitiesModel.setColor("green"); //颜色
                            activitiesModel.setTimestamp(bossBusinessName); //时间或提示信息
                            activitiesModel.setIcon("el-icon-download");//图标
                            activitiesModel.setSize("large");//大小
                            activitiesModels.add(activitiesModel);

                            ActivitiesModel activitiesModel1 = new ActivitiesModel();
                            String content1 ="方案设计";
                            String contract1 = areaPone;
                            activitiesModel1.setContent(content1);
                            activitiesModel1.setTimestamp(contract1);
                            activitiesModel1.setColor("green");
                            activitiesModel1.setIcon("el-icon-check");
                            activitiesModel1.setSize("large");
                            activitiesModels.add(activitiesModel1);

                            ActivitiesModel activitiesModel2 = new ActivitiesModel();
                            String content2 ="数据制作";
                            String contract2 = areaPone;
                            activitiesModel2.setContent(content2);
                            activitiesModel2.setTimestamp(contract2);
                            activitiesModel2.setColor("green");
                            activitiesModel2.setIcon("el-icon-check");
                            activitiesModel2.setSize("large");
                            activitiesModels.add(activitiesModel2);

                            ActivitiesModel activitiesModel3 = new ActivitiesModel();
                            String content3 ="光、电路调度";
                            String contract3 =  areaPone;
                            activitiesModel3.setContent(content3);
                            activitiesModel3.setTimestamp(contract3);
                            activitiesModel3.setColor("green");
                            activitiesModel3.setIcon("el-icon-check");
                            activitiesModel3.setSize("large");
                            activitiesModels.add(activitiesModel3);

                            ActivitiesModel activitiesModel4 = new ActivitiesModel();
                            String content4 = "装机人员首响";
                            String contract4 = "环节联系人:"+ contactsInstallCompany+",联系电话:"+contactsPhone+",到达环节时间:"+informationPremierTime;
                            activitiesModel4.setContent(content4);
                            activitiesModel4.setSize("large");
                            activitiesModel4.setTimestamp(contract4);
                            activitiesModel4.setColor("green");
                            activitiesModel4.setIcon("el-icon-check");
                            activitiesModels.add(activitiesModel4);

                            ActivitiesModel activitiesModel5 = new ActivitiesModel();
                            String conten5 = "与客户确认装机时间中";
                            String contract5 = "环节联系人:"+ contactsInstallCompany+",联系电话:"+contactsPhone;
                            activitiesModel5.setTimestamp(contract5);
                            activitiesModel5.setContent(conten5);
                            activitiesModel5.setSize("large");
                            activitiesModel5.setIcon("el-icon-loading");
                            activitiesModels.add(activitiesModel5);
                        }

                        if (!StringUtils.isBlank(informationCilentOrderTime) && StringUtils.isBlank(ponEndTime)) {
                            ActivitiesModel activitiesModel = new ActivitiesModel();
                            String content ="商机生成,"+"派单时间:"+bossBusinessTime+",专线名称:"+bossLineName+",带宽:"+bossBandwidth;
                            activitiesModel.setContent(content);//呈现内容
                            activitiesModel.setColor("green"); //颜色
                            activitiesModel.setTimestamp(bossBusinessName); //时间或提示信息
                            activitiesModel.setIcon("el-icon-download");//图标
                            activitiesModel.setSize("large");//大小
                            activitiesModels.add(activitiesModel);

                            ActivitiesModel activitiesModel1 = new ActivitiesModel();
                            String content1 ="方案设计";
                            String contract1 = areaPone;
                            activitiesModel1.setTimestamp(contract1);
                            activitiesModel1.setContent(content1);
                            activitiesModel1.setColor("green");
                            activitiesModel1.setIcon("el-icon-check");
                            activitiesModel1.setSize("large");
                            activitiesModels.add(activitiesModel1);

                            ActivitiesModel activitiesModel2 = new ActivitiesModel();
                            String content2 ="数据制作";
                            String contract2 = areaPone;
                            activitiesModel2.setContent(content2);
                            activitiesModel2.setTimestamp(contract2);
                            activitiesModel2.setColor("green");
                            activitiesModel2.setIcon("el-icon-check");
                            activitiesModel2.setSize("large");
                            activitiesModels.add(activitiesModel2);

                            ActivitiesModel activitiesModel3 = new ActivitiesModel();
                            String content3 ="光、电路调度";
                            String contract3 =  areaPone;
                            activitiesModel3.setContent(content3);
                            activitiesModel3.setTimestamp(contract3);
                            activitiesModel3.setColor("green");
                            activitiesModel3.setIcon("el-icon-check");
                            activitiesModel3.setSize("large");
                            activitiesModels.add(activitiesModel3);

                            ActivitiesModel activitiesModel4 = new ActivitiesModel();
                            String content4 = "装机人员首响";
                            String contract4 = "环节联系人:"+ contactsInstallCompany+",联系电话:"+contactsPhone+",到达环节时间:"+informationPremierTime;
                            activitiesModel4.setContent(content4);
                            activitiesModel4.setSize("large");
                            activitiesModel4.setTimestamp(contract4);
                            activitiesModel4.setColor("green");
                            activitiesModel4.setIcon("el-icon-check");
                            activitiesModels.add(activitiesModel4);

                            ActivitiesModel activitiesModel5 = new ActivitiesModel();
                            String conten5 = "客户已预约";
                            String contentbc ="客户预约时间:"+informationCilentOrderTime;
                            String contract5 = "环节联系人:"+ contactsInstallCompany+",联系电话:"+contactsPhone+",到达环节时间:"+contentbc;
                            activitiesModel5.setContent(conten5);
                            activitiesModel5.setSize("large");
                            activitiesModel5.setTimestamp(contract5);
                            activitiesModel5.setColor("green");
                            activitiesModel5.setIcon("el-icon-check");
                            activitiesModels.add(activitiesModel5);

                            ActivitiesModel activitiesModel6 = new ActivitiesModel();
                            String conten6 = "等待装机";
                            String contract6 = "环节联系人:"+ contactsInstallCompany+",联系电话:"+contactsPhone+",到达环节时间:"+contentbc;
                            activitiesModel6.setContent(conten6);
                            activitiesModel6.setTimestamp(contract6);
                            activitiesModel6.setIcon("el-icon-loading");
                            activitiesModel6.setSize("large");
                            activitiesModels.add(activitiesModel6);
                        }

                    }
                }
            } //判断有资源运行中的流程
            if ("驳回".equals(ponStateRejected)) {
                if("本地PON专线开通流程".equals(ponProcessClassification)){ //本地PON专线开通驳回流程
                    if(StringUtils.isBlank(informationPremierTime) && StringUtils.isBlank(informationCilentOrderTime)  && StringUtils.isBlank(ponEndTime)){ //判断没有首响时间，在数据阶段被驳回
                        ActivitiesModel activitiesModel = new ActivitiesModel();
                        String content = "商机生成," + "派单时间:" + bossBusinessTime + ",专线名称:" + bossLineName + ",带宽:" + bossBandwidth;
                        activitiesModel.setContent(content);//呈现内容
                        activitiesModel.setColor("green"); //颜色
                        activitiesModel.setTimestamp(bossBusinessName); //时间或提示信息
                        activitiesModel.setIcon("el-icon-download");//图标
                        activitiesModel.setSize("large");//大小
                        activitiesModels.add(activitiesModel);


                        ActivitiesModel activitiesModel1 = new ActivitiesModel();
                        String content1 = "方案设计";
                        String contract1 = "环节联系人:"+ ponLine.ZONGDIAO.getName()+",联系电话:"+ponLine.ZONGDIAO.getPhone();
                        activitiesModel1.setContent(content1);
                        activitiesModel1.setTimestamp(contract1);
                        activitiesModel1.setColor("green");
                        activitiesModel1.setIcon("el-icon-check");
                        activitiesModel1.setSize("large");
                        activitiesModels.add(activitiesModel1);

                        ActivitiesModel activitiesModel2 = new ActivitiesModel();
                        String bohui;
                        if(!StringUtils.isBlank(informationDredgeRejectCause)){
                            bohui = ",,驳回原因:"+informationDredgeRejectCause;

                        }else{
                            bohui = ",,驳回原因:"+informationDesignRejectCause;

                        }
                        String contract2 = "环节联系人:"+ ponLine.ZONGDIAO.getName()+",联系电话:"+ponLine.ZONGDIAO.getPhone()+bohui;
                        String content2 = "数据制作阶段被驳回";
                        activitiesModel2.setContent(content2);
                        activitiesModel2.setTimestamp(contract2);
                        activitiesModel2.setColor("red");
                        activitiesModel2.setIcon("el-icon-close");
                        activitiesModel2.setSize("large");
                        activitiesModels.add(activitiesModel2);
                    }

                    if (StringUtils.isBlank(informationCilentOrderTime) && !StringUtils.isBlank(informationPremierTime)){
                        ActivitiesModel activitiesModel = new ActivitiesModel();
                        String content = "商机生成," + "派单时间:" + bossBusinessTime + ",专线名称:" + bossLineName + ",带宽:" + bossBandwidth;
                        activitiesModel.setContent(content);//呈现内容
                        activitiesModel.setColor("green"); //颜色
                        activitiesModel.setTimestamp(bossBusinessName); //时间或提示信息
                        activitiesModel.setIcon("el-icon-download");//图标
                        activitiesModel.setSize("large");//大小
                        activitiesModels.add(activitiesModel);


                        ActivitiesModel activitiesModel1 = new ActivitiesModel();
                        String content1 = "方案设计";
                        String contract1 = "环节联系人:"+ ponLine.ZONGDIAO.getName()+",联系电话:"+ponLine.ZONGDIAO.getPhone();
                        activitiesModel1.setContent(content1);
                        activitiesModel1.setTimestamp(contract1);
                        activitiesModel1.setColor("green");
                        activitiesModel1.setIcon("el-icon-check");
                        activitiesModel1.setSize("large");
                        activitiesModels.add(activitiesModel1);

                        ActivitiesModel activitiesModel2 = new ActivitiesModel();
                        String content2 = "核心网数据制作";
                        String contract2 = "环节联系人:"+ ponLine.ZONGDIAO.getName()+",联系电话:"+ponLine.ZONGDIAO.getPhone();
                        activitiesModel2.setContent(content2);
                        activitiesModel2.setTimestamp(contract2);
                        activitiesModel2.setColor("green");
                        activitiesModel2.setIcon("el-icon-check");
                        activitiesModel2.setSize("large");
                        activitiesModels.add(activitiesModel2);

                        ActivitiesModel activitiesModel3 = new ActivitiesModel();
                        String content3 = "PON网管数据制作";
                        String contract3 = "环节联系人:"+ ponLine.ZONGDIAO.getName()+",联系电话:"+ponLine.ZONGDIAO.getPhone();
                        activitiesModel3.setContent(content3);
                        activitiesModel3.setTimestamp(contract3);
                        activitiesModel3.setColor("green");
                        activitiesModel3.setIcon("el-icon-check");
                        activitiesModel3.setSize("large");
                        activitiesModels.add(activitiesModel3);

                        ActivitiesModel activitiesModel4 = new ActivitiesModel();
                        String content4 = "装机人员首响";
                        String contract4 = "环节联系人:"+ contactsInstallCompany+",联系电话:"+contactsPhone+",到达环节时间:"+informationPremierTime;
                        activitiesModel4.setContent(content4);
                        activitiesModel4.setSize("large");
                        activitiesModel4.setTimestamp(contract4);
                        activitiesModel4.setColor("green");
                        activitiesModel4.setIcon("el-icon-check");
                        activitiesModels.add(activitiesModel4);

                        ActivitiesModel activitiesModel5 = new ActivitiesModel();
                        String bohui;
                        if(!StringUtils.isBlank(informationDredgeRejectCause)){
                            bohui = ",驳回原因:"+informationDredgeRejectCause;

                        }else{
                            bohui = ",驳回原因:"+informationDesignRejectCause;

                        }

                        String content5 = "客户预约装机阶段被驳回";
                        String contract5 = "环节联系人:"+ contactsInstallCompany+",联系电话:"+contactsPhone+bohui;
                        activitiesModel5.setContent(content5);
                        activitiesModel5.setTimestamp(contract5);
                        activitiesModel5.setColor("red");
                        activitiesModel5.setIcon("el-icon-close");
                        activitiesModel5.setSize("large");
                        activitiesModels.add(activitiesModel5);
                    }

                    if (!StringUtils.isBlank(informationCilentOrderTime) ){
                        ActivitiesModel activitiesModel = new ActivitiesModel();
                        String content = "商机生成," + "派单时间:" + bossBusinessTime + ",专线名称:" + bossLineName + ",带宽:" + bossBandwidth;
                        activitiesModel.setContent(content);//呈现内容
                        activitiesModel.setColor("green"); //颜色
                        activitiesModel.setTimestamp(bossBusinessName); //时间或提示信息
                        activitiesModel.setIcon("el-icon-download");//图标
                        activitiesModel.setSize("large");//大小
                        activitiesModels.add(activitiesModel);


                        ActivitiesModel activitiesModel1 = new ActivitiesModel();
                        String content1 = "方案设计";
                        String contract1 = "环节联系人:"+ ponLine.ZONGDIAO.getName()+",联系电话:"+ponLine.ZONGDIAO.getPhone();
                        activitiesModel1.setContent(content1);
                        activitiesModel1.setTimestamp(contract1);
                        activitiesModel1.setColor("green");
                        activitiesModel1.setIcon("el-icon-check");
                        activitiesModel1.setSize("large");
                        activitiesModels.add(activitiesModel1);

                        ActivitiesModel activitiesModel2 = new ActivitiesModel();
                        String content2 = "核心网数据制作";
                        String contract2 = "环节联系人:"+ ponLine.ZONGDIAO.getName()+",联系电话:"+ponLine.ZONGDIAO.getPhone();
                        activitiesModel2.setContent(content2);
                        activitiesModel2.setTimestamp(contract2);
                        activitiesModel2.setColor("green");
                        activitiesModel2.setIcon("el-icon-check");
                        activitiesModel2.setSize("large");
                        activitiesModels.add(activitiesModel2);

                        ActivitiesModel activitiesModel3 = new ActivitiesModel();
                        String content3 = "PON网管数据制作";
                        String contract3 = "环节联系人:"+ ponLine.ZONGDIAO.getName()+",联系电话:"+ponLine.ZONGDIAO.getPhone();
                        activitiesModel3.setContent(content3);
                        activitiesModel3.setTimestamp(contract3);
                        activitiesModel3.setColor("green");
                        activitiesModel3.setIcon("el-icon-check");
                        activitiesModel3.setSize("large");
                        activitiesModels.add(activitiesModel3);

                        ActivitiesModel activitiesModel4 = new ActivitiesModel();
                        String content4 = "装机人员首响";
                        String contract4 = "环节联系人:"+ contactsInstallCompany+",联系电话:"+contactsPhone+",到达环节时间:"+informationPremierTime;
                        activitiesModel4.setContent(content4);
                        activitiesModel4.setSize("large");
                        activitiesModel4.setTimestamp(contract4);
                        activitiesModel4.setColor("green");
                        activitiesModel4.setIcon("el-icon-check");
                        activitiesModels.add(activitiesModel4);

                        ActivitiesModel activitiesModel5 = new ActivitiesModel();
                        String conten5 = "客户已预约";
                        String  contentbc ="客户预约时间:"+informationCilentOrderTime;
                        String contract5 = "环节联系人:"+ contactsInstallCompany+",联系电话:"+contactsPhone+",到达环节时间:"+contentbc;
                        activitiesModel5.setContent(conten5);
                        activitiesModel5.setSize("large");
                        activitiesModel5.setTimestamp(contract5);
                        activitiesModel5.setColor("green");
                        activitiesModel5.setIcon("el-icon-check");
                        activitiesModels.add(activitiesModel5);

                        ActivitiesModel activitiesModel6 = new ActivitiesModel();
                        String bohui;
                        if(!StringUtils.isBlank(informationDredgeRejectCause)){
                            bohui = ",驳回原因:"+informationDredgeRejectCause;

                        }else{
                            bohui = ",驳回原因:"+informationDesignRejectCause;

                        }
                        String content6 = "现场装机被驳回";
                        String contract6 = "环节联系人:"+ contactsInstallCompany+",联系电话:"+contactsPhone+bohui;
                        activitiesModel6.setContent(content6);
                        activitiesModel6.setTimestamp(contract6);
                        activitiesModel6.setColor("red");
                        activitiesModel6.setIcon("el-icon-close");
                        activitiesModel6.setSize("large");
                        activitiesModels.add(activitiesModel6);
                    }

                }
                if ("本地非PON专线开通流程".equals(ponProcessClassification)){
                    if (StringUtils.isBlank(informationPremierTime) && StringUtils.isBlank(informationCilentOrderTime)  && StringUtils.isBlank(ponEndTime)){
                        ActivitiesModel activitiesModel = new ActivitiesModel();
                        String content ="商机生成,"+"派单时间:"+bossBusinessTime+",专线名称:"+bossLineName+",带宽:"+bossBandwidth;
                        activitiesModel.setContent(content);//呈现内容
                        activitiesModel.setColor("green"); //颜色
                        activitiesModel.setTimestamp(bossBusinessName); //时间或提示信息
                        activitiesModel.setIcon("el-icon-download");//图标
                        activitiesModel.setSize("large");//大小
                        activitiesModels.add(activitiesModel);

                        ActivitiesModel activitiesModel1 = new ActivitiesModel();
                        String content1 ="方案设计";
                        String contract1 = areaPone;
                        activitiesModel1.setContent(content1);
                        activitiesModel1.setTimestamp(contract1);
                        activitiesModel1.setColor("green");
                        activitiesModel1.setIcon("el-icon-check");
                        activitiesModel1.setSize("large");
                        activitiesModels.add(activitiesModel1);

                        ActivitiesModel activitiesModel2 = new ActivitiesModel();
                        String content2 ="数据制作";
                        String contract2 = areaPone;
                        activitiesModel2.setContent(content2);
                        activitiesModel2.setTimestamp(contract2);
                        activitiesModel2.setColor("green");
                        activitiesModel2.setIcon("el-icon-check");
                        activitiesModel2.setSize("large");
                        activitiesModels.add(activitiesModel2);

                        ActivitiesModel activitiesModel3 = new ActivitiesModel();
                        String bohui;
                        if(!StringUtils.isBlank(informationDredgeRejectCause)){
                            bohui = ",驳回原因:"+informationDredgeRejectCause;

                        }else{
                            bohui = ",驳回原因:"+informationDesignRejectCause;

                        }
                        String content3 = "光路调度阶段被驳回";
                        String contract3 =  areaPone+bohui;
                        activitiesModel3.setContent(content3);
                        activitiesModel3.setTimestamp(contract3);
                        activitiesModel3.setColor("red");
                        activitiesModel3.setIcon("el-icon-close");
                        activitiesModel3.setSize("large");
                        activitiesModels.add(activitiesModel3);
                    }
                    if (StringUtils.isBlank(informationCilentOrderTime) && !StringUtils.isBlank(informationPremierTime)){
                        ActivitiesModel activitiesModel = new ActivitiesModel();
                        String content ="商机生成,"+"派单时间:"+bossBusinessTime+",专线名称:"+bossLineName+",带宽:"+bossBandwidth;
                        activitiesModel.setContent(content);//呈现内容
                        activitiesModel.setColor("green"); //颜色
                        activitiesModel.setTimestamp(bossBusinessName); //时间或提示信息
                        activitiesModel.setIcon("el-icon-download");//图标
                        activitiesModel.setSize("large");//大小
                        activitiesModels.add(activitiesModel);

                        ActivitiesModel activitiesModel1 = new ActivitiesModel();
                        String content1 ="方案设计";
                        String contract1 = areaPone;
                        activitiesModel1.setContent(content1);
                        activitiesModel1.setTimestamp(contract1);
                        activitiesModel1.setColor("green");
                        activitiesModel1.setIcon("el-icon-check");
                        activitiesModel1.setSize("large");
                        activitiesModels.add(activitiesModel1);

                        ActivitiesModel activitiesModel2 = new ActivitiesModel();
                        String content2 ="数据制作";
                        String contract2 = areaPone;
                        activitiesModel2.setContent(content2);
                        activitiesModel2.setTimestamp(contract2);
                        activitiesModel2.setColor("green");
                        activitiesModel2.setIcon("el-icon-check");
                        activitiesModel2.setSize("large");
                        activitiesModels.add(activitiesModel2);

                        ActivitiesModel activitiesModel3 = new ActivitiesModel();
                        String content3 ="光、电路调度";
                        String contract3 =  areaPone;
                        activitiesModel3.setContent(content3);
                        activitiesModel3.setTimestamp(contract3);
                        activitiesModel3.setColor("green");
                        activitiesModel3.setIcon("el-icon-check");
                        activitiesModel3.setSize("large");
                        activitiesModels.add(activitiesModel3);

                        ActivitiesModel activitiesModel4 = new ActivitiesModel();
                        String content4 = "装机人员首响";
                        String contract4 = "环节联系人:"+ contactsInstallCompany+",联系电话:"+contactsPhone+",到达环节时间:"+informationPremierTime;
                        activitiesModel4.setContent(content4);
                        activitiesModel4.setSize("large");
                        activitiesModel4.setTimestamp(contract4);
                        activitiesModel4.setColor("green");
                        activitiesModel4.setIcon("el-icon-check");
                        activitiesModels.add(activitiesModel4);

                        ActivitiesModel activitiesModel5 = new ActivitiesModel();
                        String bohui;
                        if(!StringUtils.isBlank(informationDredgeRejectCause)){
                            bohui = ",驳回原因:"+informationDredgeRejectCause;

                        }else{
                            bohui = ",驳回原因:"+informationDesignRejectCause;

                        }
                        String content5 = "客户预约阶段被驳回";
                        String contract5 = "环节联系人:"+ contactsInstallCompany+",联系电话:"+contactsPhone+bohui;
                        activitiesModel5.setContent(content5);
                        activitiesModel5.setTimestamp(contract5);
                        activitiesModel5.setColor("red");
                        activitiesModel5.setIcon("el-icon-close");
                        activitiesModel5.setSize("large");
                        activitiesModels.add(activitiesModel5);
                    }
                    if (!StringUtils.isBlank(informationCilentOrderTime) ){
                        ActivitiesModel activitiesModel = new ActivitiesModel();
                        String content ="商机生成,"+"派单时间:"+bossBusinessTime+",专线名称:"+bossLineName+",带宽:"+bossBandwidth;
                        activitiesModel.setContent(content);//呈现内容
                        activitiesModel.setColor("green"); //颜色
                        activitiesModel.setTimestamp(bossBusinessName); //时间或提示信息
                        activitiesModel.setIcon("el-icon-download");//图标
                        activitiesModel.setSize("large");//大小
                        activitiesModels.add(activitiesModel);

                        ActivitiesModel activitiesModel1 = new ActivitiesModel();
                        String content1 ="方案设计";
                        String contract1 = areaPone;
                        activitiesModel1.setContent(content1);
                        activitiesModel1.setTimestamp(contract1);
                        activitiesModel1.setColor("green");
                        activitiesModel1.setIcon("el-icon-check");
                        activitiesModel1.setSize("large");
                        activitiesModels.add(activitiesModel1);

                        ActivitiesModel activitiesModel2 = new ActivitiesModel();
                        String content2 ="数据制作";
                        String contract2 = areaPone;
                        activitiesModel2.setContent(content2);
                        activitiesModel2.setTimestamp(contract2);
                        activitiesModel2.setColor("green");
                        activitiesModel2.setIcon("el-icon-check");
                        activitiesModel2.setSize("large");
                        activitiesModels.add(activitiesModel2);

                        ActivitiesModel activitiesModel3 = new ActivitiesModel();
                        String content3 ="光、电路调度";
                        String contract3 =  areaPone;
                        activitiesModel3.setContent(content3);
                        activitiesModel3.setTimestamp(contract3);
                        activitiesModel3.setColor("green");
                        activitiesModel3.setIcon("el-icon-check");
                        activitiesModel3.setSize("large");
                        activitiesModels.add(activitiesModel3);

                        ActivitiesModel activitiesModel4 = new ActivitiesModel();
                        String content4 = "装机人员首响";
                        String contract4 = "环节联系人:"+ contactsInstallCompany+",联系电话:"+contactsPhone+",到达环节时间:"+informationPremierTime;
                        activitiesModel4.setContent(content4);
                        activitiesModel4.setSize("large");
                        activitiesModel4.setTimestamp(contract4);
                        activitiesModel4.setColor("green");
                        activitiesModel4.setIcon("el-icon-check");
                        activitiesModels.add(activitiesModel4);

                        ActivitiesModel activitiesModel5 = new ActivitiesModel();
                        String conten5 = "客户已预约";
                        String contentbc ="客户预约时间:"+informationCilentOrderTime;
                        String contract5 = "环节联系人:"+ contactsInstallCompany+",联系电话:"+contactsPhone+",到达环节时间:"+contentbc;
                        activitiesModel5.setContent(conten5);
                        activitiesModel5.setSize("large");
                        activitiesModel5.setTimestamp(contract5);
                        activitiesModel5.setColor("green");
                        activitiesModel5.setIcon("el-icon-check");
                        activitiesModels.add(activitiesModel5);

                        ActivitiesModel activitiesModel6 = new ActivitiesModel();
                        String bohui;
                        if(!StringUtils.isBlank(informationDredgeRejectCause)){
                            bohui = ",驳回原因:"+informationDredgeRejectCause;

                        }else{
                            bohui = ",驳回原因:"+informationDesignRejectCause;

                        }
                        String content6 = "装机阶段被驳回";
                        String contract6 = "环节联系人:"+ contactsInstallCompany+",联系电话:"+contactsPhone+bohui;
                        activitiesModel6.setContent(content6);
                        activitiesModel6.setTimestamp(contract6);
                        activitiesModel6.setColor("red");
                        activitiesModel6.setIcon("el-icon-close");
                        activitiesModel6.setSize("large");
                        activitiesModels.add(activitiesModel6);

                    }
                }
            } //判断有资源被驳回了的流程
            return activitiesModels;
        } else {//无资源

            if (StringUtils.isBlank(prospectProcessClassification)) { //判断
                ActivitiesModel activitiesModel = new ActivitiesModel();
                activitiesModel.setContent("还未派单，等待派单");
                activitiesModel.setColor("red");
                activitiesModel.setTimestamp(bossBusinessName);
                activitiesModels.add(activitiesModel);
                return activitiesModels;
            }//判断未派单的情况
            if("归档".equals(ponState) && !"驳回".equals(ponStateRejected)){
                if("本地PON专线开通流程".equals(ponProcessClassification)){
                    ActivitiesModel activitiesModel = new ActivitiesModel();
                    String content = "商机生成," + "派单时间:" + bossBusinessTime + ",专线名称:" + bossLineName + ",带宽:" + bossBandwidth;
                    activitiesModel.setContent(content);//呈现内容
                    activitiesModel.setColor("green"); //颜色
                    activitiesModel.setTimestamp(bossBusinessName); //时间或提示信息
                    activitiesModel.setIcon("el-icon-download");//图标
                    activitiesModel.setSize("large");//大小
                    activitiesModels.add(activitiesModel);

                    ActivitiesModel activitiesModel0 = new ActivitiesModel();
                    String content0 ="资源预判";
                    String contract0 = "环节联系人:"+ ponLine.RESOURCEJIEDUAN.getName()+",联系电话:"+ponLine.RESOURCEJIEDUAN.getPhone();
                    activitiesModel0.setContent(content0);
                    activitiesModel0.setTimestamp(contract0);
                    activitiesModel0.setColor("green");
                    activitiesModel0.setIcon("el-icon-check");
                    activitiesModel0.setSize("large");
                    activitiesModels.add(activitiesModel0);

                    ActivitiesModel activitiesModel1 = new ActivitiesModel();
                    String content1 ="现场查勘";
                    activitiesModel1.setContent(content1);
                    activitiesModel1.setTimestamp(prospectExplorationTime);
                    activitiesModel1.setColor("green");
                    activitiesModel1.setIcon("el-icon-check");
                    activitiesModel1.setSize("large");
                    activitiesModels.add(activitiesModel1);

                    ActivitiesModel activitiesModel2 = new ActivitiesModel();
                    String content2 ="方案审定";
                    String contract2 = areaPone;
                    activitiesModel2.setContent(content2);
                    activitiesModel2.setTimestamp(contract2);
                    activitiesModel2.setColor("green");
                    activitiesModel2.setIcon("el-icon-check");
                    activitiesModel2.setSize("large");
                    activitiesModels.add(activitiesModel2);

                    ActivitiesModel activitiesModel3 = new ActivitiesModel();
                    String content3 ="合同签订";
                    activitiesModel3.setContent(content3);
                    activitiesModel3.setTimestamp(prospectContractTime);
                    activitiesModel3.setColor("green");
                    activitiesModel3.setIcon("el-icon-check");
                    activitiesModel3.setSize("large");
                    activitiesModels.add(activitiesModel3);

                    ActivitiesModel activitiesModel4 = new ActivitiesModel();
                    String content4 ="接入网施工";
                    String contract4 = buildname;
                    activitiesModel4.setContent(content4);
                    activitiesModel4.setTimestamp(contract4);
                    activitiesModel4.setColor("green");
                    activitiesModel4.setIcon("el-icon-check");
                    activitiesModel4.setSize("large");
                    activitiesModels.add(activitiesModel4);

                    ActivitiesModel activitiesModel5 = new ActivitiesModel();
                    String content5 ="综调审核";
                    String contract5 = "环节联系人:"+ ponLine.ZONGDIAO.getName()+",联系电话:"+ponLine.ZONGDIAO.getPhone()+",综调审核时间:"+prospectConstructionTime;
                    activitiesModel5.setContent(content5);
                    activitiesModel5.setTimestamp(contract5);
                    activitiesModel5.setColor("green");
                    activitiesModel5.setIcon("el-icon-check");
                    activitiesModel5.setSize("large");
                    activitiesModels.add(activitiesModel5);

                    ActivitiesModel activitiesModel6 = new ActivitiesModel();
                    String content6 ="方案设计";
                    String contract6 = "环节联系人:"+ ponLine.ZONGDIAO.getName()+",联系电话:"+ponLine.ZONGDIAO.getPhone();
                    activitiesModel6.setContent(content6);
                    activitiesModel6.setTimestamp(contract6);
                    activitiesModel6.setColor("green");
                    activitiesModel6.setIcon("el-icon-check");
                    activitiesModel6.setSize("large");
                    activitiesModels.add(activitiesModel6);

                    ActivitiesModel activitiesModel7 = new ActivitiesModel();
                    String content7 ="核心网数据制作";
                    String contract7 = "环节联系人:"+ ponLine.ZONGDIAO.getName()+",联系电话:"+ponLine.ZONGDIAO.getPhone();
                    activitiesModel7.setContent(content7);
                    activitiesModel7.setTimestamp(contract7);
                    activitiesModel7.setColor("green");
                    activitiesModel7.setIcon("el-icon-check");
                    activitiesModel7.setSize("large");
                    activitiesModels.add(activitiesModel7);

                    ActivitiesModel activitiesModel8 = new ActivitiesModel();
                    String content8 ="PON网管数据制作";
                    String contract8 = "环节联系人:"+ ponLine.ZONGDIAO.getName()+",联系电话:"+ponLine.ZONGDIAO.getPhone();
                    activitiesModel8.setContent(content8);
                    activitiesModel6.setTimestamp(contract8);
                    activitiesModel8.setColor("green");
                    activitiesModel8.setIcon("el-icon-check");
                    activitiesModel8.setSize("large");
                    activitiesModels.add(activitiesModel8);

                    ActivitiesModel activitiesModel9 = new ActivitiesModel();
                    String content9 = "装机人员首响";
                    String contract9 = "环节联系人:"+ contactsInstallCompany+",联系电话:"+contactsPhone+",到达环节时间:"+informationPremierTime+",首响时间:"+informationPremierTime;
                    activitiesModel9.setContent(content9);
                    activitiesModel9.setTimestamp(contract9);
                    activitiesModel9.setSize("large");
                    activitiesModel9.setColor("green");
                    activitiesModel9.setIcon("el-icon-check");
                    activitiesModels.add(activitiesModel9);

                    ActivitiesModel activitiesModel10 = new ActivitiesModel();
                    String conten10 = "客户已预约";
                    String  contentbc ="客户预约时间:"+informationCilentOrderTime;
                    String contract10 = "环节联系人:"+ contactsInstallCompany+",联系电话:"+contactsPhone+",到达环节时间:"+contentbc;
                    activitiesModel10.setContent(conten10);
                    activitiesModel10.setSize("large");
                    activitiesModel10.setTimestamp(contract10);
                    activitiesModel10.setColor("green");
                    activitiesModel10.setIcon("el-icon-check");
                    activitiesModels.add(activitiesModel10);

                    ActivitiesModel activitiesModel11 = new ActivitiesModel();
                    String conten11 = "装机完成";
                    activitiesModel11.setContent(conten11);
                    activitiesModel11.setTimestamp(ponEndTime);
                    activitiesModel11.setColor("green");
                    activitiesModel11.setIcon("el-icon-check");
                    activitiesModel11.setSize("large");
                    activitiesModels.add(activitiesModel11);

                    ActivitiesModel activitiesModel12 = new ActivitiesModel();
                    String conten12 = "归档";
                    activitiesModel12.setContent(conten12);
                    activitiesModel12.setTimestamp(ponEndTime);
                    activitiesModel12.setColor("green");
                    activitiesModel12.setIcon("el-icon-remove-outline");
                    activitiesModel12.setSize("large");
                    activitiesModels.add(activitiesModel12);
                }
                if ("本地非PON专线开通流程".equals(ponProcessClassification)){
                    ActivitiesModel activitiesModel = new ActivitiesModel();
                    String content = "商机生成," + "派单时间:" + bossBusinessTime + ",专线名称:" + bossLineName + ",带宽:" + bossBandwidth;
                    activitiesModel.setContent(content);//呈现内容
                    activitiesModel.setColor("green"); //颜色
                    activitiesModel.setTimestamp(bossBusinessName); //时间或提示信息
                    activitiesModel.setIcon("el-icon-download");//图标
                    activitiesModel.setSize("large");//大小
                    activitiesModels.add(activitiesModel);

                    ActivitiesModel activitiesModel0 = new ActivitiesModel();
                    String content0 ="资源预判";
                    String contract0 = "环节联系人:"+ ponLine.RESOURCEJIEDUAN.getName()+",联系电话:"+ponLine.RESOURCEJIEDUAN.getPhone();
                    activitiesModel0.setContent(content0);
                    activitiesModel0.setTimestamp(contract0);
                    activitiesModel0.setColor("green");
                    activitiesModel0.setIcon("el-icon-check");
                    activitiesModel0.setSize("large");
                    activitiesModels.add(activitiesModel0);

                    ActivitiesModel activitiesModel1 = new ActivitiesModel();
                    String content1 ="现场查勘";
                    activitiesModel1.setContent(content1);
                    activitiesModel1.setTimestamp(prospectExplorationTime);
                    activitiesModel1.setColor("green");
                    activitiesModel1.setIcon("el-icon-check");
                    activitiesModel1.setSize("large");
                    activitiesModels.add(activitiesModel1);

                    ActivitiesModel activitiesModel2 = new ActivitiesModel();
                    String content2 ="方案审定";
                    String contract2 = areaPone;
                    activitiesModel2.setContent(content2);
                    activitiesModel2.setTimestamp(contract2);
                    activitiesModel2.setColor("green");
                    activitiesModel2.setIcon("el-icon-check");
                    activitiesModel2.setSize("large");
                    activitiesModels.add(activitiesModel2);

                    ActivitiesModel activitiesModel3 = new ActivitiesModel();
                    String content3 ="合同签订";
                    activitiesModel3.setContent(content3);
                    activitiesModel3.setTimestamp(prospectContractTime);
                    activitiesModel3.setColor("green");
                    activitiesModel3.setIcon("el-icon-check");
                    activitiesModel3.setSize("large");
                    activitiesModels.add(activitiesModel3);

                    ActivitiesModel activitiesModel4 = new ActivitiesModel();
                    String content4 ="接入网施工";
                    String contract4 = buildname;
                    activitiesModel4.setContent(content4);
                    activitiesModel4.setTimestamp(contract4);
                    activitiesModel4.setColor("green");
                    activitiesModel4.setIcon("el-icon-check");
                    activitiesModel4.setSize("large");
                    activitiesModels.add(activitiesModel4);

                    ActivitiesModel activitiesModel5 = new ActivitiesModel();
                    String content5 ="综调审核";
                    String contract5 = "环节联系人:"+ ponLine.ZONGDIAO.getName()+",联系电话:"+ponLine.ZONGDIAO.getPhone();
                    activitiesModel5.setContent(content5);
                    activitiesModel5.setTimestamp(contract5);
                    activitiesModel5.setColor("green");
                    activitiesModel5.setIcon("el-icon-check");
                    activitiesModel5.setSize("large");
                    activitiesModels.add(activitiesModel5);

                    ActivitiesModel activitiesModel6 = new ActivitiesModel();
                    String content6 ="方案设计";
                    String contract6 = areaPone;
                    activitiesModel6.setContent(content6);
                    activitiesModel6.setTimestamp(contract6);
                    activitiesModel6.setColor("green");
                    activitiesModel6.setIcon("el-icon-check");
                    activitiesModel6.setSize("large");
                    activitiesModels.add(activitiesModel6);

                    ActivitiesModel activitiesModel7 = new ActivitiesModel();
                    String content7 ="数据制作";
                    String contract7 = areaPone;
                    activitiesModel7.setContent(content7);
                    activitiesModel2.setTimestamp(contract7);
                    activitiesModel7.setColor("green");
                    activitiesModel7.setIcon("el-icon-check");
                    activitiesModel7.setSize("large");
                    activitiesModels.add(activitiesModel7);

                    ActivitiesModel activitiesModel8 = new ActivitiesModel();
                    String content8 ="光、电路调度";
                    String contract8 =  areaPone;
                    activitiesModel8.setContent(content8);
                    activitiesModel3.setTimestamp(contract8);
                    activitiesModel8.setColor("green");
                    activitiesModel8.setIcon("el-icon-check");
                    activitiesModel8.setSize("large");
                    activitiesModels.add(activitiesModel8);

                    ActivitiesModel activitiesModel9 = new ActivitiesModel();
                    String content9 = "装机人员首响";
                    String contract9 = "环节联系人:"+ contactsInstallCompany+",联系电话:"+contactsPhone+",到达环节时间:"+informationPremierTime;
                    activitiesModel9.setContent(content9);
                    activitiesModel9.setSize("large");
                    activitiesModel9.setTimestamp(contract9);
                    activitiesModel9.setColor("green");
                    activitiesModel9.setIcon("el-icon-check");
                    activitiesModels.add(activitiesModel9);

                    ActivitiesModel activitiesModel10 = new ActivitiesModel();
                    String conten10 = "客户已预约";
                    String contentbc ="客户预约时间:"+informationCilentOrderTime;
                    String contract10 = "环节联系人:"+ contactsInstallCompany+",联系电话:"+contactsPhone+",到达环节时间:"+contentbc;
                    activitiesModel10.setContent(conten10);
                    activitiesModel10.setSize("large");
                    activitiesModel10.setTimestamp(contract10);
                    activitiesModel10.setColor("green");
                    activitiesModel10.setIcon("el-icon-check");
                    activitiesModels.add(activitiesModel10);

                    ActivitiesModel activitiesModel11 = new ActivitiesModel();
                    String conten11 = "装机完成";
                    activitiesModel11.setContent(conten11);
                    activitiesModel11.setTimestamp(ponEndTime);
                    activitiesModel11.setColor("green");
                    activitiesModel11.setIcon("el-icon-check");
                    activitiesModel11.setSize("large");
                    activitiesModels.add(activitiesModel11);

                    ActivitiesModel activitiesModel12 = new ActivitiesModel();
                    String conten12 = "归档";
                    activitiesModel12.setContent(conten12);
                    activitiesModel12.setTimestamp(ponEndTime);
                    activitiesModel12.setColor("green");
                    activitiesModel12.setIcon("el-icon-remove-outline");
                    activitiesModel12.setSize("large");
                    activitiesModels.add(activitiesModel12);

                }
            }//判断已开通了的情况
            if(!"归档".equals(prospectSate) && !"驳回".equals(prospectStateReject)  ){
                if(prospectStatwNow.contains("资源预判")){
                    ActivitiesModel activitiesModel = new ActivitiesModel();
                    String content = "商机生成," + "派单时间:" + bossBusinessTime + ",专线名称:" + bossLineName + ",带宽:" + bossBandwidth;
                    activitiesModel.setContent(content);//呈现内容
                    activitiesModel.setColor("green"); //颜色
                    activitiesModel.setTimestamp(bossBusinessName); //时间或提示信息
                    activitiesModel.setIcon("el-icon-download");//图标
                    activitiesModel.setSize("large");//大小
                    activitiesModels.add(activitiesModel);

                    ActivitiesModel activitiesModel1 = new ActivitiesModel();
                    String content1 ="资源预判中";
                    String contract1 = "环节联系人:"+ ponLine.RESOURCEJIEDUAN.getName()+",联系电话:"+ponLine.RESOURCEJIEDUAN.getPhone();
                    activitiesModel1.setContent(content1);
                    activitiesModel1.setTimestamp(contract1);
                    activitiesModel1.setIcon("el-icon-loading");
                    activitiesModel1.setSize("large");
                    activitiesModels.add(activitiesModel1);
                }
                if (prospectStatwNow.contains("现场勘察")){
                    ActivitiesModel activitiesModel = new ActivitiesModel();
                    String content = "商机生成," + "派单时间:" + bossBusinessTime + ",专线名称:" + bossLineName + ",带宽:" + bossBandwidth;
                    activitiesModel.setContent(content);//呈现内容
                    activitiesModel.setColor("green"); //颜色
                    activitiesModel.setTimestamp(bossBusinessName); //时间或提示信息
                    activitiesModel.setIcon("el-icon-download");//图标
                    activitiesModel.setSize("large");//大小
                    activitiesModels.add(activitiesModel);

                    ActivitiesModel activitiesModel0 = new ActivitiesModel();
                    String content0 ="资源预判";
                    String contract1 = "环节联系人:"+ ponLine.RESOURCEJIEDUAN.getName()+",联系电话:"+ponLine.RESOURCEJIEDUAN.getPhone();
                    activitiesModel0.setContent(content0);
                    activitiesModel0.setTimestamp(contract1);
                    activitiesModel0.setColor("green");
                    activitiesModel0.setIcon("el-icon-check");
                    activitiesModel0.setSize("large");
                    activitiesModels.add(activitiesModel0);

                    ActivitiesModel activitiesModel1 = new ActivitiesModel();
                    String content1 ="现场查勘中";
                    activitiesModel1.setContent(content1);
                    activitiesModel1.setIcon("el-icon-loading");
                    activitiesModel1.setSize("large");
                    activitiesModels.add(activitiesModel1);
                }
                if (prospectStatwNow.contains("方案审定")){
                    ActivitiesModel activitiesModel = new ActivitiesModel();
                    String content = "商机生成," + "派单时间:" + bossBusinessTime + ",专线名称:" + bossLineName + ",带宽:" + bossBandwidth;
                    activitiesModel.setContent(content);//呈现内容
                    activitiesModel.setColor("green"); //颜色
                    activitiesModel.setTimestamp(bossBusinessName); //时间或提示信息
                    activitiesModel.setIcon("el-icon-download");//图标
                    activitiesModel.setSize("large");//大小
                    activitiesModels.add(activitiesModel);

                    ActivitiesModel activitiesModel0 = new ActivitiesModel();
                    String content0 ="资源预判";
                    String contract0 = "环节联系人:"+ ponLine.RESOURCEJIEDUAN.getName()+",联系电话:"+ponLine.RESOURCEJIEDUAN.getPhone();
                    activitiesModel0.setContent(content0);
                    activitiesModel0.setTimestamp(contract0);
                    activitiesModel0.setColor("green");
                    activitiesModel0.setIcon("el-icon-check");
                    activitiesModel0.setSize("large");
                    activitiesModels.add(activitiesModel0);

                    ActivitiesModel activitiesModel1 = new ActivitiesModel();
                    String content1 ="现场查勘";
                    activitiesModel1.setContent(content1);
                    activitiesModel1.setTimestamp(prospectExplorationTime);
                    activitiesModel1.setColor("green");
                    activitiesModel1.setIcon("el-icon-check");
                    activitiesModel1.setSize("large");
                    activitiesModels.add(activitiesModel1);

                    ActivitiesModel activitiesModel2 = new ActivitiesModel();
                    String content2 ="方案审定中";
                    String contract2 = areaPone;
                    activitiesModel2.setContent(content2);
                    activitiesModel2.setTimestamp(contract2);
                    activitiesModel2.setIcon("el-icon-loading");
                    activitiesModel2.setSize("large");
                    activitiesModels.add(activitiesModel2);
                }
                if (prospectStatwNow.contains("合同签订")){
                    ActivitiesModel activitiesModel = new ActivitiesModel();
                    String content = "商机生成," + "派单时间:" + bossBusinessTime + ",专线名称:" + bossLineName + ",带宽:" + bossBandwidth;
                    activitiesModel.setContent(content);//呈现内容
                    activitiesModel.setColor("green"); //颜色
                    activitiesModel.setTimestamp(bossBusinessName); //时间或提示信息
                    activitiesModel.setIcon("el-icon-download");//图标
                    activitiesModel.setSize("large");//大小
                    activitiesModels.add(activitiesModel);

                    ActivitiesModel activitiesModel0 = new ActivitiesModel();
                    String content0 ="资源预判";
                    activitiesModel0.setContent(content0);
                    activitiesModel0.setTimestamp(prospectExplorationTime);
                    activitiesModel0.setColor("green");
                    activitiesModel0.setIcon("el-icon-check");
                    activitiesModel0.setSize("large");
                    activitiesModels.add(activitiesModel0);

                    ActivitiesModel activitiesModel1 = new ActivitiesModel();
                    String content1 ="现场查勘";
                    activitiesModel1.setContent(content1);
                    activitiesModel1.setTimestamp(prospectExplorationTime);
                    activitiesModel1.setColor("green");
                    activitiesModel1.setIcon("el-icon-check");
                    activitiesModel1.setSize("large");
                    activitiesModels.add(activitiesModel1);

                    ActivitiesModel activitiesModel2 = new ActivitiesModel();
                    String content2 ="方案审定";
                    String contract2 = areaPone;
                    activitiesModel2.setContent(content2);
                    activitiesModel2.setTimestamp(contract2);
                    activitiesModel2.setColor("green");
                    activitiesModel2.setIcon("el-icon-check");
                    activitiesModel2.setSize("large");
                    activitiesModels.add(activitiesModel2);


                    ActivitiesModel activitiesModel3 = new ActivitiesModel();
                    String content3 ="合同签订中";
                    activitiesModel3.setContent(content3);
                    activitiesModel3.setIcon("el-icon-loading");
                    activitiesModel3.setSize("large");
                    activitiesModels.add(activitiesModel3);

                }
                if (prospectStatwNow.contains("接入网施工")){
                    ActivitiesModel activitiesModel = new ActivitiesModel();
                    String content = buildname;
                    activitiesModel.setContent(content);//呈现内容
                    activitiesModel.setColor("green"); //颜色
                    activitiesModel.setTimestamp(bossBusinessName); //时间或提示信息
                    activitiesModel.setIcon("el-icon-download");//图标
                    activitiesModel.setSize("large");//大小
                    activitiesModels.add(activitiesModel);

                    ActivitiesModel activitiesModel0 = new ActivitiesModel();
                    String content0 ="资源预判";
                    String contract0 = "环节联系人:"+ ponLine.RESOURCEJIEDUAN.getName()+",联系电话:"+ponLine.RESOURCEJIEDUAN.getPhone();
                    activitiesModel0.setContent(content0);
                    activitiesModel0.setTimestamp(contract0);
                    activitiesModel0.setColor("green");
                    activitiesModel0.setIcon("el-icon-check");
                    activitiesModel0.setSize("large");
                    activitiesModels.add(activitiesModel0);

                    ActivitiesModel activitiesModel1 = new ActivitiesModel();
                    String content1 ="现场查勘";
                    activitiesModel1.setContent(content1);
                    activitiesModel1.setTimestamp(prospectExplorationTime);
                    activitiesModel1.setColor("green");
                    activitiesModel1.setIcon("el-icon-check");
                    activitiesModel1.setSize("large");
                    activitiesModels.add(activitiesModel1);

                    ActivitiesModel activitiesModel2 = new ActivitiesModel();
                    String content2 ="方案审定";
                    String contract2 = areaPone;
                    activitiesModel2.setContent(content2);
                    activitiesModel2.setTimestamp(contract2);
                    activitiesModel2.setColor("green");
                    activitiesModel2.setIcon("el-icon-check");
                    activitiesModel2.setSize("large");
                    activitiesModels.add(activitiesModel2);

                    ActivitiesModel activitiesModel3 = new ActivitiesModel();
                    String content3 ="合同签订";
                    activitiesModel3.setContent(content3);
                    activitiesModel3.setTimestamp(prospectContractTime);
                    activitiesModel3.setColor("green");
                    activitiesModel3.setIcon("el-icon-check");
                    activitiesModel3.setSize("large");
                    activitiesModels.add(activitiesModel3);


                    ActivitiesModel activitiesModel4 = new ActivitiesModel();
                    String content4 ="接入网施工中";
                    String contract4 = buildname;
                    activitiesModel4.setContent(content4);
                    activitiesModel4.setTimestamp(contract4);
                    activitiesModel4.setIcon("el-icon-loading");
                    activitiesModel4.setSize("large");
                    activitiesModels.add(activitiesModel4);
                }
            }//判断在查勘阶段中运行的工单
            if("归档".equals(prospectSate) && !"归档".equals(ponState) && !"驳回".equals(ponStateRejected)  && !"驳回".equals(prospectStateReject) ){
                if (StringUtils.isBlank(ponProcessClassification)){
                    ActivitiesModel activitiesModel = new ActivitiesModel();
                    String content = "商机生成," + "派单时间:" + bossBusinessTime + ",专线名称:" + bossLineName + ",带宽:" + bossBandwidth;
                    activitiesModel.setContent(content);//呈现内容
                    activitiesModel.setColor("green"); //颜色
                    activitiesModel.setTimestamp(bossBusinessName); //时间或提示信息
                    activitiesModel.setIcon("el-icon-download");//图标
                    activitiesModel.setSize("large");//大小
                    activitiesModels.add(activitiesModel);


                    ActivitiesModel activitiesModel1 = new ActivitiesModel();
                    String content1 ="现场查勘";
                    activitiesModel1.setContent(content1);
                    activitiesModel1.setTimestamp(prospectExplorationTime);
                    activitiesModel1.setColor("green");
                    activitiesModel1.setIcon("el-icon-check");
                    activitiesModel1.setSize("large");
                    activitiesModels.add(activitiesModel1);

                    ActivitiesModel activitiesModel2 = new ActivitiesModel();
                    String content2 ="方案审定";
                    String contract2 = areaPone;
                    activitiesModel2.setContent(content2);
                    activitiesModel2.setTimestamp(contract2);
                    activitiesModel2.setColor("green");
                    activitiesModel2.setIcon("el-icon-check");
                    activitiesModel2.setSize("large");
                    activitiesModels.add(activitiesModel2);

                    ActivitiesModel activitiesModel3 = new ActivitiesModel();
                    String content3 ="合同签订";
                    activitiesModel3.setContent(content3);
                    activitiesModel3.setTimestamp(prospectContractTime);
                    activitiesModel3.setColor("green");
                    activitiesModel3.setIcon("el-icon-check");
                    activitiesModel3.setSize("large");
                    activitiesModels.add(activitiesModel3);

                    ActivitiesModel activitiesModel4 = new ActivitiesModel();
                    String content4 ="接入网施工";
                    String contract4 = buildname;
                    activitiesModel4.setContent(content4);
                    activitiesModel4.setTimestamp(contract4);
                    activitiesModel4.setColor("green");
                    activitiesModel4.setIcon("el-icon-check");
                    activitiesModel4.setSize("large");
                    activitiesModels.add(activitiesModel4);

                    ActivitiesModel activitiesModel5 = new ActivitiesModel();
                    String content5 ="综调审核";
                    String contract5 = "环节联系人:"+ ponLine.ZONGDIAO.getName()+",联系电话:"+ponLine.ZONGDIAO.getPhone();
                    activitiesModel5.setContent(content5);
                    activitiesModel5.setTimestamp(contract5);
                    activitiesModel5.setColor("green");
                    activitiesModel5.setIcon("el-icon-check");
                    activitiesModel5.setSize("large");
                    activitiesModels.add(activitiesModel5);


                    ActivitiesModel activitiesModel6 = new ActivitiesModel();
                    String content6 = "方案设计中";
                    String contract6 = areaPone;
                    activitiesModel6.setContent(content6);
                    activitiesModel6.setTimestamp(contract6);
                    activitiesModel6.setIcon("el-icon-loading");
                    activitiesModel6.setSize("large");
                    activitiesModels.add(activitiesModel6);

                }
                if ("本地PON专线开通流程".equals(ponProcessClassification)){
                    ActivitiesModel activitiesModel = new ActivitiesModel();
                    String content = "商机生成," + "派单时间:" + bossBusinessTime + ",专线名称:" + bossLineName + ",带宽:" + bossBandwidth;
                    activitiesModel.setContent(content);//呈现内容
                    activitiesModel.setColor("green"); //颜色
                    activitiesModel.setTimestamp(bossBusinessName); //时间或提示信息
                    activitiesModel.setIcon("el-icon-download");//图标
                    activitiesModel.setSize("large");//大小
                    activitiesModels.add(activitiesModel);


                    ActivitiesModel activitiesModel1 = new ActivitiesModel();
                    String content1 ="现场查勘";
                    String contract2 = areaPone;
                    activitiesModel1.setContent(content1);
                    activitiesModel1.setTimestamp(prospectExplorationTime);
                    activitiesModel1.setColor("green");
                    activitiesModel1.setIcon("el-icon-check");
                    activitiesModel1.setSize("large");
                    activitiesModels.add(activitiesModel1);

                    ActivitiesModel activitiesModel2 = new ActivitiesModel();
                    String content2 ="方案审定";
                    activitiesModel2.setContent(content2);
                    activitiesModel2.setTimestamp(contract2);
                    activitiesModel2.setColor("green");
                    activitiesModel2.setIcon("el-icon-check");
                    activitiesModel2.setSize("large");
                    activitiesModels.add(activitiesModel2);

                    ActivitiesModel activitiesModel3 = new ActivitiesModel();
                    String content3 ="合同签订";
                    activitiesModel3.setContent(content3);
                    activitiesModel3.setTimestamp(prospectContractTime);
                    activitiesModel3.setColor("green");
                    activitiesModel3.setIcon("el-icon-check");
                    activitiesModel3.setSize("large");
                    activitiesModels.add(activitiesModel3);

                    ActivitiesModel activitiesModel4 = new ActivitiesModel();
                    String content4 ="接入网施工";
                    String contract4 = buildname;
                    activitiesModel4.setContent(content4);
                    activitiesModel4.setTimestamp(contract4);
                    activitiesModel4.setColor("green");
                    activitiesModel4.setIcon("el-icon-check");
                    activitiesModel4.setSize("large");
                    activitiesModels.add(activitiesModel4);

                    ActivitiesModel activitiesModel5 = new ActivitiesModel();
                    String content5 ="综调审核";
                    String contract5 = "环节联系人:"+ ponLine.ZONGDIAO.getName()+",联系电话:"+ponLine.ZONGDIAO.getPhone();
                    activitiesModel5.setContent(content5);
                    activitiesModel5.setTimestamp(contract5);
                    activitiesModel5.setColor("green");
                    activitiesModel5.setIcon("el-icon-check");
                    activitiesModel5.setSize("large");
                    activitiesModels.add(activitiesModel5);
                    if (ponStateNow.contains("核心网")) {


                        ActivitiesModel activitiesModel6 = new ActivitiesModel();
                        String content6 = "方案设计";
                        String contract6 = "环节联系人:"+ ponLine.ZONGDIAO.getName()+",联系电话:"+ponLine.ZONGDIAO.getPhone();
                        activitiesModel6.setContent(content6);
                        activitiesModel6.setTimestamp(contract6);
                        activitiesModel6.setColor("green");
                        activitiesModel6.setIcon("el-icon-check");
                        activitiesModel6.setSize("large");
                        activitiesModels.add(activitiesModel6);

                        ActivitiesModel activitiesModel7 = new ActivitiesModel();
                        String content7 = "核心网数据制作中";
                        String contract7 = "环节联系人:"+ ponLine.ZONGDIAO.getName()+",联系电话:"+ponLine.ZONGDIAO.getPhone();
                        activitiesModel7.setContent(content7);
                        activitiesModel7.setTimestamp(contract7);
                        activitiesModel7.setIcon("el-icon-loading");
                        activitiesModel7.setSize("large");
                        activitiesModels.add(activitiesModel7);
                    }
                    if (ponStateNow.contains("pon网管")) {


                        ActivitiesModel activitiesModel6 = new ActivitiesModel();
                        String content6 ="方案设计";
                        String contract6 = "环节联系人:"+ ponLine.ZONGDIAO.getName()+",联系电话:"+ponLine.ZONGDIAO.getPhone();
                        activitiesModel6.setContent(content6);
                        activitiesModel6.setTimestamp(contract6);
                        activitiesModel6.setColor("green");
                        activitiesModel6.setIcon("el-icon-check");
                        activitiesModel6.setSize("large");
                        activitiesModels.add(activitiesModel6);

                        ActivitiesModel activitiesModel7 = new ActivitiesModel();
                        String content7 ="核心网数据制作";
                        String contract7 = "环节联系人:"+ ponLine.ZONGDIAO.getName()+",联系电话:"+ponLine.ZONGDIAO.getPhone();
                        activitiesModel7.setContent(content7);
                        activitiesModel7.setTimestamp(contract7);
                        activitiesModel7.setColor("green");
                        activitiesModel7.setIcon("el-icon-check");
                        activitiesModel7.setSize("large");
                        activitiesModels.add(activitiesModel7);

                        ActivitiesModel activitiesModel8 = new ActivitiesModel();
                        String content8 = "PON网管数据制作中";
                        String contract8 = "环节联系人:"+ ponLine.ZONGDIAO.getName()+",联系电话:"+ponLine.ZONGDIAO.getPhone();
                        activitiesModel8.setContent(content8);
                        activitiesModel7.setTimestamp(contract8);
                        activitiesModel8.setColor("green");
                        activitiesModel8.setIcon("el-icon-loading");
                        activitiesModel8.setSize("large");
                        activitiesModels.add(activitiesModel8);
                    }
                    if (ponStateNow.contains("现场开通")) {
                        if (StringUtils.isBlank(informationPremierTime) && StringUtils.isBlank(informationCilentOrderTime)  && StringUtils.isBlank(ponEndTime)) {

                            ActivitiesModel activitiesModel6 = new ActivitiesModel();
                            String content6 ="方案设计";
                            String contract6 = "环节联系人:"+ ponLine.ZONGDIAO.getName()+",联系电话:"+ponLine.ZONGDIAO.getPhone();
                            activitiesModel6.setContent(content6);
                            activitiesModel6.setTimestamp(contract6);
                            activitiesModel6.setColor("green");
                            activitiesModel6.setIcon("el-icon-check");
                            activitiesModel6.setSize("large");
                            activitiesModels.add(activitiesModel6);

                            ActivitiesModel activitiesModel7 = new ActivitiesModel();
                            String content7 ="核心网数据制作";
                            String contract7 = "环节联系人:"+ ponLine.ZONGDIAO.getName()+",联系电话:"+ponLine.ZONGDIAO.getPhone();
                            activitiesModel7.setContent(content7);
                            activitiesModel7.setTimestamp(contract7);
                            activitiesModel7.setColor("green");
                            activitiesModel7.setIcon("el-icon-check");
                            activitiesModel7.setSize("large");
                            activitiesModels.add(activitiesModel7);

                            ActivitiesModel activitiesModel8 = new ActivitiesModel();
                            String content8 ="PON网管数据制作";
                            String contract8 = "环节联系人:"+ ponLine.ZONGDIAO.getName()+",联系电话:"+ponLine.ZONGDIAO.getPhone();
                            activitiesModel8.setContent(content8);
                            activitiesModel6.setTimestamp(contract8);
                            activitiesModel8.setColor("green");
                            activitiesModel8.setIcon("el-icon-check");
                            activitiesModel8.setSize("large");
                            activitiesModels.add(activitiesModel8);

                            ActivitiesModel activitiesModel9 = new ActivitiesModel();
                            String content9 = "等待装机人员响应";
                            String contract9 = "环节联系人:"+ contactsInstallCompany+",联系电话:"+contactsPhone+",到达环节时间:"+informationPremierTime;
                            activitiesModel9.setContent(content9);
                            activitiesModel9.setTimestamp(contract9);
                            activitiesModel9.setSize("large");
                            activitiesModel9.setColor("green");
                            activitiesModel9.setIcon("el-icon-loading");
                            activitiesModels.add(activitiesModel9);
                        }

                        if (!StringUtils.isBlank(informationPremierTime) && StringUtils.isBlank(ponEndTime)) {

                            ActivitiesModel activitiesModel6 = new ActivitiesModel();
                            String content6 ="方案设计";
                            String contract6 = "环节联系人:"+ ponLine.ZONGDIAO.getName()+",联系电话:"+ponLine.ZONGDIAO.getPhone();
                            activitiesModel6.setContent(content6);
                            activitiesModel6.setTimestamp(contract6);
                            activitiesModel6.setColor("green");
                            activitiesModel6.setIcon("el-icon-check");
                            activitiesModel6.setSize("large");
                            activitiesModels.add(activitiesModel6);

                            ActivitiesModel activitiesModel7 = new ActivitiesModel();
                            String content7 ="核心网数据制作";
                            String contract7 = "环节联系人:"+ ponLine.ZONGDIAO.getName()+",联系电话:"+ponLine.ZONGDIAO.getPhone();
                            activitiesModel7.setContent(content7);
                            activitiesModel7.setTimestamp(contract7);
                            activitiesModel7.setColor("green");
                            activitiesModel7.setIcon("el-icon-check");
                            activitiesModel7.setSize("large");
                            activitiesModels.add(activitiesModel7);

                            ActivitiesModel activitiesModel8 = new ActivitiesModel();
                            String content8 ="PON网管数据制作";
                            String contract8 = "环节联系人:"+ ponLine.ZONGDIAO.getName()+",联系电话:"+ponLine.ZONGDIAO.getPhone();
                            activitiesModel8.setContent(content8);
                            activitiesModel6.setTimestamp(contract8);
                            activitiesModel8.setColor("green");
                            activitiesModel8.setIcon("el-icon-check");
                            activitiesModel8.setSize("large");
                            activitiesModels.add(activitiesModel8);

                            ActivitiesModel activitiesModel9 = new ActivitiesModel();
                            String content9 = "装机人员首响";
                            String contract9 = "环节联系人:"+ contactsInstallCompany+",联系电话:"+contactsPhone+",到达环节时间:"+informationPremierTime+",首响时间:"+informationPremierTime;
                            activitiesModel9.setContent(content9);
                            activitiesModel9.setTimestamp(contract9);
                            activitiesModel9.setSize("large");
                            activitiesModel9.setColor("green");
                            activitiesModel9.setIcon("el-icon-check");
                            activitiesModels.add(activitiesModel9);

                            ActivitiesModel activitiesModel10 = new ActivitiesModel();
                            String conten10 = "与客户确认装机时间中";
                            String contract10 = "环节联系人:"+ contactsInstallCompany+",联系电话:"+contactsPhone;
                            activitiesModel10.setContent(conten10);
                            activitiesModel10.setTimestamp(contract10);
                            activitiesModel10.setSize("large");
                            activitiesModel10.setIcon("el-icon-loading");
                            activitiesModels.add(activitiesModel10);
                        }

                        if (!StringUtils.isBlank(informationCilentOrderTime) && StringUtils.isBlank(ponEndTime)) {



                            ActivitiesModel activitiesModel6 = new ActivitiesModel();
                            String content6 ="方案设计";
                            String contract6 = "环节联系人:"+ ponLine.ZONGDIAO.getName()+",联系电话:"+ponLine.ZONGDIAO.getPhone();
                            activitiesModel6.setContent(content6);
                            activitiesModel6.setTimestamp(contract6);
                            activitiesModel6.setColor("green");
                            activitiesModel6.setIcon("el-icon-check");
                            activitiesModel6.setSize("large");
                            activitiesModels.add(activitiesModel6);

                            ActivitiesModel activitiesModel7 = new ActivitiesModel();
                            String content7 ="核心网数据制作";
                            String contract7 = "环节联系人:"+ ponLine.ZONGDIAO.getName()+",联系电话:"+ponLine.ZONGDIAO.getPhone();
                            activitiesModel7.setContent(content7);
                            activitiesModel7.setTimestamp(contract7);
                            activitiesModel7.setColor("green");
                            activitiesModel7.setIcon("el-icon-check");
                            activitiesModel7.setSize("large");
                            activitiesModels.add(activitiesModel7);

                            ActivitiesModel activitiesModel8 = new ActivitiesModel();
                            String content8 ="PON网管数据制作";
                            String contract8 = "环节联系人:"+ ponLine.ZONGDIAO.getName()+",联系电话:"+ponLine.ZONGDIAO.getPhone();
                            activitiesModel8.setContent(content8);
                            activitiesModel6.setTimestamp(contract8);
                            activitiesModel8.setColor("green");
                            activitiesModel8.setIcon("el-icon-check");
                            activitiesModel8.setSize("large");
                            activitiesModels.add(activitiesModel8);

                            ActivitiesModel activitiesModel9 = new ActivitiesModel();
                            String content9 = "装机人员首响";
                            String contract9 = "环节联系人:"+ contactsInstallCompany+",联系电话:"+contactsPhone+",到达环节时间:"+informationPremierTime+",首响时间:"+informationPremierTime;
                            activitiesModel9.setContent(content9);
                            activitiesModel9.setTimestamp(contract9);
                            activitiesModel9.setSize("large");
                            activitiesModel9.setColor("green");
                            activitiesModel9.setIcon("el-icon-check");
                            activitiesModels.add(activitiesModel9);

                            ActivitiesModel activitiesModel10 = new ActivitiesModel();
                            String conten10 = "客户已预约";
                            String  contentbc ="客户预约时间:"+informationCilentOrderTime;
                            String contract10 = "环节联系人:"+ contactsInstallCompany+",联系电话:"+contactsPhone+",到达环节时间:"+contentbc;
                            activitiesModel10.setContent(conten10);
                            activitiesModel10.setSize("large");
                            activitiesModel10.setTimestamp(contract10);
                            activitiesModel10.setColor("green");
                            activitiesModel10.setIcon("el-icon-check");
                            activitiesModels.add(activitiesModel10);

                            ActivitiesModel activitiesModel11 = new ActivitiesModel();
                            String conten11 = "等待装机";
                            String contract11 = "环节联系人:"+ contactsInstallCompany+",联系电话:"+contactsPhone+",到达环节时间:"+contentbc;
                            activitiesModel11.setContent(conten11);
                            activitiesModel1.setTimestamp(contract11);
                            activitiesModel11.setIcon("el-icon-loading");
                            activitiesModel11.setSize("large");
                            activitiesModels.add(activitiesModel11);

                        }
                    }
                }//判断PON开通在开通阶段
                if ("本地非PON专线开通流程".equals(ponProcessClassification)){
                    ActivitiesModel activitiesModel = new ActivitiesModel();
                    String content = "商机生成," + "派单时间:" + bossBusinessTime + ",专线名称:" + bossLineName + ",带宽:" + bossBandwidth;
                    activitiesModel.setContent(content);//呈现内容
                    activitiesModel.setColor("green"); //颜色
                    activitiesModel.setTimestamp(bossBusinessName); //时间或提示信息
                    activitiesModel.setIcon("el-icon-download");//图标
                    activitiesModel.setSize("large");//大小
                    activitiesModels.add(activitiesModel);

                    ActivitiesModel activitiesModel0 = new ActivitiesModel();
                    String content0 ="资源预判";
                    String contract0 = "环节联系人:"+ ponLine.RESOURCEJIEDUAN.getName()+",联系电话:"+ponLine.RESOURCEJIEDUAN.getPhone();
                    activitiesModel0.setContent(content0);
                    activitiesModel0.setTimestamp(contract0);
                    activitiesModel0.setColor("green");
                    activitiesModel0.setIcon("el-icon-check");
                    activitiesModel0.setSize("large");
                    activitiesModels.add(activitiesModel0);


                    ActivitiesModel activitiesModel1 = new ActivitiesModel();
                    String content1 ="现场查勘";
                    activitiesModel1.setContent(content1);
                    activitiesModel1.setTimestamp(prospectExplorationTime);
                    activitiesModel1.setColor("green");
                    activitiesModel1.setIcon("el-icon-check");
                    activitiesModel1.setSize("large");
                    activitiesModels.add(activitiesModel1);

                    ActivitiesModel activitiesModel2 = new ActivitiesModel();
                    String content2 ="方案审定";
                    String contract2 = areaPone;
                    activitiesModel2.setContent(content2);
                    activitiesModel2.setTimestamp(contract2);
                    activitiesModel2.setColor("green");
                    activitiesModel2.setIcon("el-icon-check");
                    activitiesModel2.setSize("large");
                    activitiesModels.add(activitiesModel2);

                    ActivitiesModel activitiesModel3 = new ActivitiesModel();
                    String content3 ="合同签订";
                    activitiesModel3.setContent(content3);
                    activitiesModel3.setTimestamp(prospectContractTime);
                    activitiesModel3.setColor("green");
                    activitiesModel3.setIcon("el-icon-check");
                    activitiesModel3.setSize("large");
                    activitiesModels.add(activitiesModel3);

                    ActivitiesModel activitiesModel4 = new ActivitiesModel();
                    String content4 ="接入网施工";
                    String contract4 = buildname;
                    activitiesModel4.setContent(content4);
                    activitiesModel4.setTimestamp(contract4);
                    activitiesModel4.setColor("green");
                    activitiesModel4.setIcon("el-icon-check");
                    activitiesModel4.setSize("large");
                    activitiesModels.add(activitiesModel4);

                    ActivitiesModel activitiesModel5 = new ActivitiesModel();
                    String content5 ="综调审核";
                    String contract5 = "环节联系人:"+ ponLine.ZONGDIAO.getName()+",联系电话:"+ponLine.ZONGDIAO.getPhone();
                    activitiesModel5.setContent(content5);
                    activitiesModel5.setTimestamp(contract5);
                    activitiesModel5.setColor("green");
                    activitiesModel5.setIcon("el-icon-check");
                    activitiesModel5.setSize("large");
                    activitiesModels.add(activitiesModel5);

                    if (ponStateNow.contains("方案设计")) {

                        ActivitiesModel activitiesModel6 = new ActivitiesModel();
                        String content6 = "方案设计中";
                        String contract6 = areaPone;
                        activitiesModel6.setContent(content6);
                        activitiesModel6.setTimestamp(contract6);
                        activitiesModel6.setIcon("el-icon-loading");
                        activitiesModel6.setSize("large");
                        activitiesModels.add(activitiesModel1);
                    }

                    if (ponStateNow.contains("数据制作")) {

                        ActivitiesModel activitiesModel6 = new ActivitiesModel();
                        String content6 = "方案设计";
                        String contract6 = areaPone;
                        activitiesModel6.setContent(content6);
                        activitiesModel6.setTimestamp(contract6);
                        activitiesModel6.setColor("green");
                        activitiesModel6.setIcon("el-icon-check");
                        activitiesModel6.setSize("large");
                        activitiesModels.add(activitiesModel6);

                        ActivitiesModel activitiesModel7 = new ActivitiesModel();
                        String content7 = "数据制作中";
                        String contract7 = areaPone;
                        activitiesModel7.setContent(content7);
                        activitiesModel2.setTimestamp(contract7);
                        activitiesModel7.setIcon("el-icon-loading");
                        activitiesModel7.setSize("large");
                        activitiesModels.add(activitiesModel7);
                    }

                    if (ponStateNow.contains("调度")) {

                        ActivitiesModel activitiesModel6 = new ActivitiesModel();
                        String content6 ="方案设计";
                        String contract6 = areaPone;
                        activitiesModel6.setContent(content6);
                        activitiesModel6.setTimestamp(contract6);
                        activitiesModel6.setColor("green");
                        activitiesModel6.setIcon("el-icon-check");
                        activitiesModel6.setSize("large");
                        activitiesModels.add(activitiesModel6);

                        ActivitiesModel activitiesModel7 = new ActivitiesModel();
                        String content7 ="数据制作";
                        String contract7 = areaPone;
                        activitiesModel7.setContent(content7);
                        activitiesModel2.setTimestamp(contract7);
                        activitiesModel7.setColor("green");
                        activitiesModel7.setIcon("el-icon-check");
                        activitiesModel7.setSize("large");
                        activitiesModels.add(activitiesModel7);

                        ActivitiesModel activitiesModel8 = new ActivitiesModel();
                        String content8 = "光、电路调度中";
                        String contract8 =  areaPone;
                        activitiesModel8.setContent(content8);
                        activitiesModel3.setTimestamp(contract8);
                        activitiesModel8.setIcon("el-icon-loading");
                        activitiesModel8.setSize("large");
                        activitiesModels.add(activitiesModel8);
                    }
                    if(ponStateNow.contains("交维")){

                        ActivitiesModel activitiesModel6 = new ActivitiesModel();
                        String content6 ="方案设计";
                        String contract6 = areaPone;
                        activitiesModel6.setContent(content6);
                        activitiesModel6.setTimestamp(contract6);
                        activitiesModel6.setColor("green");
                        activitiesModel6.setIcon("el-icon-check");
                        activitiesModel6.setSize("large");
                        activitiesModels.add(activitiesModel6);

                        ActivitiesModel activitiesModel7 = new ActivitiesModel();
                        String content7 ="数据制作";
                        String contract7 = areaPone;
                        activitiesModel7.setContent(content7);
                        activitiesModel2.setTimestamp(contract7);
                        activitiesModel7.setColor("green");
                        activitiesModel7.setIcon("el-icon-check");
                        activitiesModel7.setSize("large");
                        activitiesModels.add(activitiesModel7);

                        ActivitiesModel activitiesModel8 = new ActivitiesModel();
                        String content8 ="光、电路调度";
                        String contract8 =  areaPone;
                        activitiesModel8.setContent(content8);
                        activitiesModel3.setTimestamp(contract8);
                        activitiesModel8.setColor("green");
                        activitiesModel8.setIcon("el-icon-check");
                        activitiesModel8.setSize("large");
                        activitiesModels.add(activitiesModel8);

                        ActivitiesModel activitiesModel9 = new ActivitiesModel();
                        String content9 = "装机人员首响";
                        String contract9 = "环节联系人:"+ contactsInstallCompany+",联系电话:"+contactsPhone+",到达环节时间:"+informationPremierTime;
                        activitiesModel9.setContent(content9);
                        activitiesModel9.setSize("large");
                        activitiesModel9.setTimestamp(contract9);
                        activitiesModel9.setColor("green");
                        activitiesModel9.setIcon("el-icon-check");
                        activitiesModels.add(activitiesModel9);

                        ActivitiesModel activitiesModel10 = new ActivitiesModel();
                        String conten10 = "客户已预约";
                        String contentbc ="客户预约时间:"+informationCilentOrderTime;
                        String contract10 = "环节联系人:"+ contactsInstallCompany+",联系电话:"+contactsPhone+",到达环节时间:"+contentbc;
                        activitiesModel10.setContent(conten10);
                        activitiesModel10.setSize("large");
                        activitiesModel10.setTimestamp(contract10);
                        activitiesModel10.setColor("green");
                        activitiesModel10.setIcon("el-icon-check");
                        activitiesModels.add(activitiesModel10);

                        ActivitiesModel activitiesModel11 = new ActivitiesModel();
                        String conten11 = "装机完成";
                        activitiesModel11.setContent(conten11);
                        activitiesModel11.setTimestamp(ponEndTime);
                        activitiesModel11.setColor("green");
                        activitiesModel11.setIcon("el-icon-check");
                        activitiesModel11.setSize("large");
                        activitiesModels.add(activitiesModel11);

                        ActivitiesModel activitiesModel12 = new ActivitiesModel();
                        String conten12 = "等待交维";
                        activitiesModel12.setContent(conten12);
                        activitiesModel12.setIcon("el-icon-loading");
                        activitiesModel12.setSize("large");
                        activitiesModels.add(activitiesModel12);
                    }


                    if (ponStateNow.contains("现场开通")) {
                        if (StringUtils.isBlank(informationPremierTime) && StringUtils.isBlank(informationCilentOrderTime)  && StringUtils.isBlank(ponEndTime)) {

                            ActivitiesModel activitiesModel6 = new ActivitiesModel();
                            String content6 ="方案设计";
                            String contract6 = areaPone;
                            activitiesModel6.setContent(content6);
                            activitiesModel6.setTimestamp(contract6);
                            activitiesModel6.setColor("green");
                            activitiesModel6.setIcon("el-icon-check");
                            activitiesModel6.setSize("large");
                            activitiesModels.add(activitiesModel6);

                            ActivitiesModel activitiesModel7 = new ActivitiesModel();
                            String content7 ="数据制作";
                            String contract7 = areaPone;
                            activitiesModel7.setContent(content7);
                            activitiesModel2.setTimestamp(contract7);
                            activitiesModel7.setColor("green");
                            activitiesModel7.setIcon("el-icon-check");
                            activitiesModel7.setSize("large");
                            activitiesModels.add(activitiesModel7);

                            ActivitiesModel activitiesModel8 = new ActivitiesModel();
                            String content8 ="光、电路调度";
                            String contract8 =  areaPone;
                            activitiesModel8.setContent(content8);
                            activitiesModel3.setTimestamp(contract8);
                            activitiesModel8.setColor("green");
                            activitiesModel8.setIcon("el-icon-check");
                            activitiesModel8.setSize("large");
                            activitiesModels.add(activitiesModel8);

                            ActivitiesModel activitiesModel9 = new ActivitiesModel();
                            String content9 = "等待装机人员响应";
                            String contract9 = "环节联系人:"+ contactsInstallCompany+",联系电话:"+contactsPhone;
                            activitiesModel9.setContent(content9);
                            activitiesModel9.setTimestamp(contract9);
                            activitiesModel9.setSize("large");
                            activitiesModel9.setColor("green");
                            activitiesModel9.setIcon("el-icon-loading");
                            activitiesModels.add(activitiesModel9);
                        }

                        if (StringUtils.isBlank(informationCilentOrderTime) && !StringUtils.isBlank(informationPremierTime)) {

                            ActivitiesModel activitiesModel6 = new ActivitiesModel();
                            String content6 ="方案设计";
                            String contract6 = areaPone;
                            activitiesModel6.setContent(content6);
                            activitiesModel6.setTimestamp(contract6);
                            activitiesModel6.setColor("green");
                            activitiesModel6.setIcon("el-icon-check");
                            activitiesModel6.setSize("large");
                            activitiesModels.add(activitiesModel6);

                            ActivitiesModel activitiesModel7 = new ActivitiesModel();
                            String content7 ="数据制作";
                            String contract7 = areaPone;
                            activitiesModel7.setContent(content7);
                            activitiesModel2.setTimestamp(contract7);
                            activitiesModel7.setColor("green");
                            activitiesModel7.setIcon("el-icon-check");
                            activitiesModel7.setSize("large");
                            activitiesModels.add(activitiesModel7);

                            ActivitiesModel activitiesModel8 = new ActivitiesModel();
                            String content8 ="光、电路调度";
                            String contract8 =  areaPone;
                            activitiesModel8.setContent(content8);
                            activitiesModel3.setTimestamp(contract8);
                            activitiesModel8.setColor("green");
                            activitiesModel8.setIcon("el-icon-check");
                            activitiesModel8.setSize("large");
                            activitiesModels.add(activitiesModel8);

                            ActivitiesModel activitiesModel9 = new ActivitiesModel();
                            String content9 = "装机人员首响";
                            String contract9 = "环节联系人:"+ contactsInstallCompany+",联系电话:"+contactsPhone+",到达环节时间:"+informationPremierTime;
                            activitiesModel9.setContent(content9);
                            activitiesModel9.setSize("large");
                            activitiesModel9.setTimestamp(contract9);
                            activitiesModel9.setColor("green");
                            activitiesModel9.setIcon("el-icon-check");
                            activitiesModels.add(activitiesModel9);

                            ActivitiesModel activitiesModel10 = new ActivitiesModel();
                            String conten10 = "与客户确认装机时间中";
                            String contract10 = "环节联系人:"+ contactsInstallCompany+",联系电话:"+contactsPhone;
                            activitiesModel10.setContent(conten10);
                            activitiesModel10.setTimestamp(contract10);
                            activitiesModel10.setSize("large");
                            activitiesModel10.setIcon("el-icon-loading");
                            activitiesModels.add(activitiesModel10);
                        }

                        if (!StringUtils.isBlank(informationCilentOrderTime) && StringUtils.isBlank(ponEndTime)) {

                            ActivitiesModel activitiesModel6 = new ActivitiesModel();
                            String content6 ="方案设计";
                            String contract6 = areaPone;
                            activitiesModel6.setContent(content6);
                            activitiesModel6.setTimestamp(contract6);
                            activitiesModel6.setColor("green");
                            activitiesModel6.setIcon("el-icon-check");
                            activitiesModel6.setSize("large");
                            activitiesModels.add(activitiesModel6);

                            ActivitiesModel activitiesModel7 = new ActivitiesModel();
                            String content7 ="数据制作";
                            String contract7 = areaPone;
                            activitiesModel7.setContent(content7);
                            activitiesModel2.setTimestamp(contract7);
                            activitiesModel7.setColor("green");
                            activitiesModel7.setIcon("el-icon-check");
                            activitiesModel7.setSize("large");
                            activitiesModels.add(activitiesModel7);

                            ActivitiesModel activitiesModel8 = new ActivitiesModel();
                            String content8 ="光、电路调度";
                            String contract8 =  areaPone;
                            activitiesModel8.setContent(content8);
                            activitiesModel3.setTimestamp(contract8);
                            activitiesModel8.setColor("green");
                            activitiesModel8.setIcon("el-icon-check");
                            activitiesModel8.setSize("large");
                            activitiesModels.add(activitiesModel8);

                            ActivitiesModel activitiesModel9 = new ActivitiesModel();
                            String content9 = "装机人员首响";
                            String contract9 = "环节联系人:"+ contactsInstallCompany+",联系电话:"+contactsPhone+",到达环节时间:"+informationPremierTime;
                            activitiesModel9.setContent(content9);
                            activitiesModel9.setSize("large");
                            activitiesModel9.setTimestamp(contract9);
                            activitiesModel9.setColor("green");
                            activitiesModel9.setIcon("el-icon-check");
                            activitiesModels.add(activitiesModel9);

                            ActivitiesModel activitiesModel10 = new ActivitiesModel();
                            String conten10 = "客户已预约";
                            String contentbc ="客户预约时间:"+informationCilentOrderTime;
                            String contract10 = "环节联系人:"+ contactsInstallCompany+",联系电话:"+contactsPhone+",到达环节时间:"+contentbc;
                            activitiesModel10.setContent(conten10);
                            activitiesModel10.setSize("large");
                            activitiesModel10.setTimestamp(contract10);
                            activitiesModel10.setColor("green");
                            activitiesModel10.setIcon("el-icon-check");
                            activitiesModels.add(activitiesModel10);

                            ActivitiesModel activitiesModel11 = new ActivitiesModel();
                            String conten11 = "等待装机";
                            String contract11 = "环节联系人:"+ contactsInstallCompany+",联系电话:"+contactsPhone;
                            activitiesModel11.setContent(conten11);
                            activitiesModel11.setTimestamp(contract11);
                            activitiesModel11.setIcon("el-icon-loading");
                            activitiesModel11.setSize("large");
                            activitiesModels.add(activitiesModel6);
                        }
                    }

                }//判断非PON开通在开通阶段
            }//判断在开通阶段中运行的工单
            if ("驳回".equals(ponStateRejected) || "驳回".equals(prospectStateReject)){
                if ("驳回".equals(prospectStateReject)){
                    if (StringUtils.isBlank(prospectExplorationTime) && StringUtils.isBlank(prospectContractTime) && StringUtils.isBlank(prospectConstructionTime)) {
                        ActivitiesModel activitiesModel = new ActivitiesModel();
                        String content = "商机生成," + "派单时间:" + bossBusinessTime + ",专线名称:" + bossLineName + ",带宽:" + bossBandwidth;
                        activitiesModel.setContent(content);//呈现内容
                        activitiesModel.setColor("green"); //颜色
                        activitiesModel.setTimestamp(bossBusinessName); //时间或提示信息
                        activitiesModel.setIcon("el-icon-download");//图标
                        activitiesModel.setSize("large");//大小
                        activitiesModels.add(activitiesModel);

                        ActivitiesModel activitiesModel1 = new ActivitiesModel();
                        String content1 = "现场查勘被驳回";
                        activitiesModel1.setContent(content1);
                        String bohui;
                        if(!StringUtils.isBlank(informationDredgeRejectCause)){
                            bohui = ",驳回原因:"+informationDredgeRejectCause;
                        }else{
                            bohui = ",驳回原因:"+informationDesignRejectCause;
                        }
                        activitiesModel1.setTimestamp(bohui);
                        activitiesModel1.setColor("red");
                        activitiesModel1.setIcon("el-icon-close");
                        activitiesModels.add(activitiesModel1);
                    }
                    if (!StringUtils.isBlank(prospectExplorationTime) && StringUtils.isBlank(prospectContractTime) && StringUtils.isBlank(prospectConstructionTime)){
                        ActivitiesModel activitiesModel = new ActivitiesModel();
                        String content = "商机生成," + "派单时间:" + bossBusinessTime + ",专线名称:" + bossLineName + ",带宽:" + bossBandwidth;
                        activitiesModel.setContent(content);//呈现内容
                        activitiesModel.setColor("green"); //颜色
                        activitiesModel.setTimestamp(bossBusinessName); //时间或提示信息
                        activitiesModel.setIcon("el-icon-download");//图标
                        activitiesModel.setSize("large");//大小
                        activitiesModels.add(activitiesModel);


                        ActivitiesModel activitiesModel1 = new ActivitiesModel();
                        String content1 ="现场查勘";
                        activitiesModel1.setContent(content1);
                        activitiesModel1.setTimestamp(prospectExplorationTime);
                        activitiesModel1.setColor("green");
                        activitiesModel1.setIcon("el-icon-check");
                        activitiesModel1.setSize("large");
                        activitiesModels.add(activitiesModel1);

                        ActivitiesModel activitiesModel2 = new ActivitiesModel();
                        String content2 ="方案审定";
                        String contract2 = areaPone;
                        activitiesModel2.setContent(content2);
                        activitiesModel2.setTimestamp(contract2);
                        activitiesModel2.setColor("green");
                        activitiesModel2.setIcon("el-icon-check");
                        activitiesModel2.setSize("large");
                        activitiesModels.add(activitiesModel2);

                        ActivitiesModel activitiesModel3 = new ActivitiesModel();
                        String content3 = "合同签订被驳回";
                        activitiesModel3.setContent(content3);
                        String bohui;
                        if(!StringUtils.isBlank(informationDredgeRejectCause)){
                            bohui = ",驳回原因:"+informationDredgeRejectCause;
                        }else{
                            bohui = ",驳回原因:"+informationDesignRejectCause;
                        }
                        activitiesModel3.setTimestamp(bohui);
                        activitiesModel3.setColor("red");
                        activitiesModel3.setIcon("el-icon-close");
                        activitiesModels.add(activitiesModel3);
                    }
                    if(!StringUtils.isBlank(prospectContractTime) && StringUtils.isBlank(prospectConstructionTime)){
                        ActivitiesModel activitiesModel = new ActivitiesModel();
                        String content = "商机生成," + "派单时间:" + bossBusinessTime + ",专线名称:" + bossLineName + ",带宽:" + bossBandwidth;
                        activitiesModel.setContent(content);//呈现内容
                        activitiesModel.setColor("green"); //颜色
                        activitiesModel.setTimestamp(bossBusinessName); //时间或提示信息
                        activitiesModel.setIcon("el-icon-download");//图标
                        activitiesModel.setSize("large");//大小
                        activitiesModels.add(activitiesModel);


                        ActivitiesModel activitiesModel1 = new ActivitiesModel();
                        String content1 ="现场查勘";
                        activitiesModel1.setContent(content1);
                        activitiesModel1.setTimestamp(prospectExplorationTime);
                        activitiesModel1.setColor("green");
                        activitiesModel1.setIcon("el-icon-check");
                        activitiesModel1.setSize("large");
                        activitiesModels.add(activitiesModel1);

                        ActivitiesModel activitiesModel2 = new ActivitiesModel();
                        String content2 ="方案审定";
                        String contract2 = areaPone;
                        activitiesModel2.setContent(content2);
                        activitiesModel2.setTimestamp(contract2);
                        activitiesModel2.setColor("green");
                        activitiesModel2.setIcon("el-icon-check");
                        activitiesModel2.setSize("large");
                        activitiesModels.add(activitiesModel2);

                        ActivitiesModel activitiesModel3 = new ActivitiesModel();
                        String content3 ="合同签订";
                        activitiesModel3.setContent(content3);
                        activitiesModel3.setTimestamp(prospectContractTime);
                        activitiesModel3.setColor("green");
                        activitiesModel3.setIcon("el-icon-check");
                        activitiesModel3.setSize("large");
                        activitiesModels.add(activitiesModel3);

                        ActivitiesModel activitiesModel4 = new ActivitiesModel();
                        String bohui;
                        if(!StringUtils.isBlank(informationDredgeRejectCause)){
                            bohui = ",驳回原因:"+informationDredgeRejectCause;
                        }else{
                            bohui = ",驳回原因:"+informationDesignRejectCause;
                        }
                        String content4 = "现场施工被驳回";
                        String contract4 = "环节联系人:"+ ponLine.ZONGDIAO.getName()+",联系电话:"+ponLine.ZONGDIAO.getPhone()+bohui;
                        activitiesModel4.setContent(content4);
                        activitiesModel4.setTimestamp(contract4);
                        activitiesModel4.setColor("red");
                        activitiesModel4.setIcon("el-icon-close");
                        activitiesModels.add(activitiesModel4);
                    }
                    if (!StringUtils.isBlank(prospectConstructionTime)){
                        ActivitiesModel activitiesModel = new ActivitiesModel();
                        String content = "商机生成," + "派单时间:" + bossBusinessTime + ",专线名称:" + bossLineName + ",带宽:" + bossBandwidth;
                        activitiesModel.setContent(content);//呈现内容
                        activitiesModel.setColor("green"); //颜色
                        activitiesModel.setTimestamp(bossBusinessName); //时间或提示信息
                        activitiesModel.setIcon("el-icon-download");//图标
                        activitiesModel.setSize("large");//大小
                        activitiesModels.add(activitiesModel);


                        ActivitiesModel activitiesModel1 = new ActivitiesModel();
                        String content1 ="现场查勘";
                        activitiesModel1.setContent(content1);
                        activitiesModel1.setTimestamp(prospectExplorationTime);
                        activitiesModel1.setColor("green");
                        activitiesModel1.setIcon("el-icon-check");
                        activitiesModel1.setSize("large");
                        activitiesModels.add(activitiesModel1);

                        ActivitiesModel activitiesModel2 = new ActivitiesModel();
                        String content2 ="方案审定";
                        String contract2 = areaPone;
                        activitiesModel2.setContent(content2);
                        activitiesModel2.setTimestamp(contract2);
                        activitiesModel2.setColor("green");
                        activitiesModel2.setIcon("el-icon-check");
                        activitiesModel2.setSize("large");
                        activitiesModels.add(activitiesModel2);

                        ActivitiesModel activitiesModel3 = new ActivitiesModel();
                        String content3 ="合同签订";
                        activitiesModel3.setContent(content3);
                        activitiesModel3.setTimestamp(prospectContractTime);
                        activitiesModel3.setColor("green");
                        activitiesModel3.setIcon("el-icon-check");
                        activitiesModel3.setSize("large");
                        activitiesModels.add(activitiesModel3);

                        ActivitiesModel activitiesModel4 = new ActivitiesModel();
                        String content4 ="接入网施工";
                        String contract4 = buildname;
                        activitiesModel4.setContent(content4);
                        activitiesModel4.setTimestamp(contract4);
                        activitiesModel4.setColor("green");
                        activitiesModel4.setIcon("el-icon-check");
                        activitiesModel4.setSize("large");
                        activitiesModels.add(activitiesModel4);

                        ActivitiesModel activitiesModel5 = new ActivitiesModel();
                        String bohui;
                        if(!StringUtils.isBlank(informationDredgeRejectCause)){
                            bohui = ",驳回原因:"+informationDredgeRejectCause;
                        }else{
                            bohui = ",驳回原因:"+informationDesignRejectCause;
                        }
                        String content5 = "综调审核被驳回";
                        String contract5 = "环节联系人:"+ ponLine.ZONGDIAO.getName()+",联系电话:"+ponLine.ZONGDIAO.getPhone()+bohui;
                        activitiesModel5.setContent(content5);
                        activitiesModel5.setTimestamp(contract5);
                        activitiesModel5.setColor("red");
                        activitiesModel5.setIcon("el-icon-close");
                        activitiesModels.add(activitiesModel5);
                    }
                }//判断在查勘阶段被驳回
                if ("驳回".equals(ponStateRejected) &&  "归档".equals(prospectSate) && !"驳回".equals(prospectStateReject)){
                    ActivitiesModel activitiesModel = new ActivitiesModel();
                    String content = "商机生成," + "派单时间:" + bossBusinessTime + ",专线名称:" + bossLineName + ",带宽:" + bossBandwidth;
                    activitiesModel.setContent(content);//呈现内容
                    activitiesModel.setColor("green"); //颜色
                    activitiesModel.setTimestamp(bossBusinessName); //时间或提示信息
                    activitiesModel.setIcon("el-icon-download");//图标
                    activitiesModel.setSize("large");//大小
                    activitiesModels.add(activitiesModel);


                    ActivitiesModel activitiesModel1 = new ActivitiesModel();
                    String content1 ="现场查勘";
                    activitiesModel1.setContent(content1);
                    activitiesModel1.setTimestamp(prospectExplorationTime);
                    activitiesModel1.setColor("green");
                    activitiesModel1.setIcon("el-icon-check");
                    activitiesModel1.setSize("large");
                    activitiesModels.add(activitiesModel1);

                    ActivitiesModel activitiesModel2 = new ActivitiesModel();
                    String content2 ="方案审定";
                    String contract2 = areaPone;
                    activitiesModel2.setContent(content2);
                    activitiesModel2.setTimestamp(contract2);
                    activitiesModel2.setColor("green");
                    activitiesModel2.setIcon("el-icon-check");
                    activitiesModel2.setSize("large");
                    activitiesModels.add(activitiesModel2);

                    ActivitiesModel activitiesModel3 = new ActivitiesModel();
                    String content3 ="合同签订";
                    activitiesModel3.setContent(content3);
                    activitiesModel3.setTimestamp(prospectContractTime);
                    activitiesModel3.setColor("green");
                    activitiesModel3.setIcon("el-icon-check");
                    activitiesModel3.setSize("large");
                    activitiesModels.add(activitiesModel3);

                    ActivitiesModel activitiesModel4 = new ActivitiesModel();
                    String content4 ="接入网施工";
                    String contract4 = buildname;
                    activitiesModel4.setContent(content4);
                    activitiesModel4.setTimestamp(contract4);
                    activitiesModel4.setColor("green");
                    activitiesModel4.setIcon("el-icon-check");
                    activitiesModel4.setSize("large");
                    activitiesModels.add(activitiesModel4);

                    ActivitiesModel activitiesModel5 = new ActivitiesModel();
                    String content5 ="综调审核";
                    String contract5 = "环节联系人:"+ ponLine.ZONGDIAO.getName()+",联系电话:"+ponLine.ZONGDIAO.getPhone();
                    activitiesModel5.setContent(content5);
                    activitiesModel5.setTimestamp(contract5);
                    activitiesModel5.setColor("green");
                    activitiesModel5.setIcon("el-icon-check");
                    activitiesModel5.setSize("large");
                    activitiesModels.add(activitiesModel5);
                    if ("本地PON专线开通流程".equals(ponProcessClassification)){
                        if(StringUtils.isBlank(informationPremierTime) && StringUtils.isBlank(informationCilentOrderTime) ){ //判断没有首响时间，在数据阶段被驳回

                            ActivitiesModel activitiesModel6 = new ActivitiesModel();
                            String content6 ="方案设计";
                            String contract6 = areaPone;
                            activitiesModel6.setContent(content6);
                            activitiesModel6.setTimestamp(contract6);
                            activitiesModel6.setColor("green");
                            activitiesModel6.setIcon("el-icon-check");
                            activitiesModel6.setSize("large");
                            activitiesModels.add(activitiesModel6);

                            ActivitiesModel activitiesModel7 = new ActivitiesModel();
                            String bohui;
                            if(!StringUtils.isBlank(informationDredgeRejectCause)){
                                bohui = ",驳回原因:"+informationDredgeRejectCause;

                            }else{
                                bohui = ",驳回原因:"+informationDesignRejectCause;

                            }
                            String contract7 = areaPone+bohui;
                            String content7 = "数据制作阶段被驳回";
                            activitiesModel7.setContent(content7);
                            activitiesModel7.setTimestamp(contract7);
                            activitiesModel7.setColor("red");
                            activitiesModel7.setIcon("el-icon-close");
                            activitiesModel7.setSize("large");
                            activitiesModels.add(activitiesModel7);
                        }

                        if (StringUtils.isBlank(informationCilentOrderTime) && !StringUtils.isBlank(informationPremierTime)){

                            ActivitiesModel activitiesModel6 = new ActivitiesModel();
                            String content6 ="方案设计";
                            String contract6 = "环节联系人:"+ ponLine.ZONGDIAO.getName()+",联系电话:"+ponLine.ZONGDIAO.getPhone();
                            activitiesModel6.setContent(content6);
                            activitiesModel6.setTimestamp(contract6);
                            activitiesModel6.setColor("green");
                            activitiesModel6.setIcon("el-icon-check");
                            activitiesModel6.setSize("large");
                            activitiesModels.add(activitiesModel6);

                            ActivitiesModel activitiesModel7 = new ActivitiesModel();
                            String content7 ="核心网数据制作";
                            String contract7 = "环节联系人:"+ ponLine.ZONGDIAO.getName()+",联系电话:"+ponLine.ZONGDIAO.getPhone();
                            activitiesModel7.setContent(content7);
                            activitiesModel7.setTimestamp(contract7);
                            activitiesModel7.setColor("green");
                            activitiesModel7.setIcon("el-icon-check");
                            activitiesModel7.setSize("large");
                            activitiesModels.add(activitiesModel7);

                            ActivitiesModel activitiesModel8 = new ActivitiesModel();
                            String content8 ="PON网管数据制作";
                            String contract8 = "环节联系人:"+ ponLine.ZONGDIAO.getName()+",联系电话:"+ponLine.ZONGDIAO.getPhone();
                            activitiesModel8.setContent(content8);
                            activitiesModel6.setTimestamp(contract8);
                            activitiesModel8.setColor("green");
                            activitiesModel8.setIcon("el-icon-check");
                            activitiesModel8.setSize("large");
                            activitiesModels.add(activitiesModel8);

                            ActivitiesModel activitiesModel9 = new ActivitiesModel();
                            String content9 = "装机人员首响";
                            String contract9 = "环节联系人:"+ contactsInstallCompany+",联系电话:"+contactsPhone+",到达环节时间:"+informationPremierTime+",首响时间:"+informationPremierTime;
                            activitiesModel9.setContent(content9);
                            activitiesModel9.setTimestamp(contract9);
                            activitiesModel9.setSize("large");
                            activitiesModel9.setColor("green");
                            activitiesModel9.setIcon("el-icon-check");
                            activitiesModels.add(activitiesModel9);

                            ActivitiesModel activitiesModel10 = new ActivitiesModel();
                            String bohui;
                            if(!StringUtils.isBlank(informationDredgeRejectCause)){
                                bohui = ",驳回原因:"+informationDredgeRejectCause;

                            }else{
                                bohui = ",驳回原因:"+informationDesignRejectCause;

                            }
                            String contract10 = "环节联系人:"+ contactsInstallCompany+",联系电话:"+contactsPhone+bohui;
                            String content10 = "客户预约装机阶段被驳回";
                            activitiesModel10.setContent(content10);
                            activitiesModel10.setTimestamp(contract10);
                            activitiesModel10.setColor("red");
                            activitiesModel10.setIcon("el-icon-close");
                            activitiesModel10.setSize("large");
                            activitiesModels.add(activitiesModel10);
                        }

                        if (!StringUtils.isBlank(informationCilentOrderTime) && !StringUtils.isBlank(informationCilentOrderTime)){

                            ActivitiesModel activitiesModel6 = new ActivitiesModel();
                            String content6 ="方案设计";
                            String contract6 = "环节联系人:"+ ponLine.ZONGDIAO.getName()+",联系电话:"+ponLine.ZONGDIAO.getPhone();
                            activitiesModel6.setContent(content6);
                            activitiesModel6.setTimestamp(contract6);
                            activitiesModel6.setColor("green");
                            activitiesModel6.setIcon("el-icon-check");
                            activitiesModel6.setSize("large");
                            activitiesModels.add(activitiesModel6);

                            ActivitiesModel activitiesModel7 = new ActivitiesModel();
                            String content7 ="核心网数据制作";
                            String contract7 = "环节联系人:"+ ponLine.ZONGDIAO.getName()+",联系电话:"+ponLine.ZONGDIAO.getPhone();
                            activitiesModel7.setContent(content7);
                            activitiesModel7.setTimestamp(contract7);
                            activitiesModel7.setColor("green");
                            activitiesModel7.setIcon("el-icon-check");
                            activitiesModel7.setSize("large");
                            activitiesModels.add(activitiesModel7);

                            ActivitiesModel activitiesModel8 = new ActivitiesModel();
                            String content8 ="PON网管数据制作";
                            String contract8 = "环节联系人:"+ ponLine.ZONGDIAO.getName()+",联系电话:"+ponLine.ZONGDIAO.getPhone();
                            activitiesModel8.setContent(content8);
                            activitiesModel6.setTimestamp(contract8);
                            activitiesModel8.setColor("green");
                            activitiesModel8.setIcon("el-icon-check");
                            activitiesModel8.setSize("large");
                            activitiesModels.add(activitiesModel8);

                            ActivitiesModel activitiesModel9 = new ActivitiesModel();
                            String content9 = "装机人员首响";
                            String contract9 = "环节联系人:"+ contactsInstallCompany+",联系电话:"+contactsPhone+",到达环节时间:"+informationPremierTime+",首响时间:"+informationPremierTime;
                            activitiesModel9.setContent(content9);
                            activitiesModel9.setTimestamp(contract9);
                            activitiesModel9.setSize("large");
                            activitiesModel9.setColor("green");
                            activitiesModel9.setIcon("el-icon-check");
                            activitiesModels.add(activitiesModel9);

                            ActivitiesModel activitiesModel10 = new ActivitiesModel();
                            String conten10 = "客户已预约";
                            String  contentbc ="客户预约时间:"+informationCilentOrderTime;
                            String contract10 = "环节联系人:"+ contactsInstallCompany+",联系电话:"+contactsPhone+",到达环节时间:"+contentbc;
                            activitiesModel10.setContent(conten10);
                            activitiesModel10.setSize("large");
                            activitiesModel10.setTimestamp(contract10);
                            activitiesModel10.setColor("green");
                            activitiesModel10.setIcon("el-icon-check");
                            activitiesModels.add(activitiesModel10);

                            ActivitiesModel activitiesModel11 = new ActivitiesModel();
                            String bohui;
                            if(!StringUtils.isBlank(informationDredgeRejectCause)){
                                bohui = ",驳回原因:"+informationDredgeRejectCause;

                            }else{
                                bohui = ",驳回原因:"+informationDesignRejectCause;

                            }
                            String content11 = "现场装机被驳回";
                            String contract11 = "环节联系人:"+ contactsInstallCompany+",联系电话:"+contactsPhone+bohui;
                            activitiesModel11.setContent(content11);
                            activitiesModel11.setTimestamp(contract11);
                            activitiesModel11.setColor("red");
                            activitiesModel11.setIcon("el-icon-close");
                            activitiesModel11.setSize("large");
                            activitiesModels.add(activitiesModel11);
                        }
                    }//pon开通阶段被驳回
                    if("本地非PON专线开通流程".equals(ponProcessClassification)){
                        if (StringUtils.isBlank(informationPremierTime) && StringUtils.isBlank(informationCilentOrderTime)){

                            ActivitiesModel activitiesModel6 = new ActivitiesModel();
                            String content6 ="方案设计";
                            String contract6 = areaPone;
                            activitiesModel6.setContent(content6);
                            activitiesModel6.setTimestamp(contract6);
                            activitiesModel6.setColor("green");
                            activitiesModel6.setIcon("el-icon-check");
                            activitiesModel6.setSize("large");
                            activitiesModels.add(activitiesModel6);

                            ActivitiesModel activitiesModel7 = new ActivitiesModel();
                            String content7 ="数据制作";
                            String contract7 = areaPone;
                            activitiesModel7.setContent(content7);
                            activitiesModel2.setTimestamp(contract7);
                            activitiesModel7.setColor("green");
                            activitiesModel7.setIcon("el-icon-check");
                            activitiesModel7.setSize("large");
                            activitiesModels.add(activitiesModel7);

                            ActivitiesModel activitiesModel8 = new ActivitiesModel();
                            String bohui;
                            if(!StringUtils.isBlank(informationDredgeRejectCause)){
                                bohui = ",驳回原因:"+informationDredgeRejectCause;

                            }else{
                                bohui = ",驳回原因:"+informationDesignRejectCause;

                            }
                            String content8 = "光路调度阶段被驳回";
                            String contract8 =  areaPone+bohui;

                            activitiesModel8.setContent(content8);

                            activitiesModel8.setTimestamp(contract8);
                            activitiesModel8.setColor("red");
                            activitiesModel8.setIcon("el-icon-close");
                            activitiesModel8.setSize("large");
                            activitiesModels.add(activitiesModel8);
                        }
                        if (StringUtils.isBlank(informationCilentOrderTime) && !StringUtils.isBlank(informationPremierTime)){

                            ActivitiesModel activitiesModel6 = new ActivitiesModel();
                            String content6 ="方案设计";
                            String contract6 = areaPone;
                            activitiesModel6.setContent(content6);
                            activitiesModel6.setTimestamp(contract6);
                            activitiesModel6.setColor("green");
                            activitiesModel6.setIcon("el-icon-check");
                            activitiesModel6.setSize("large");
                            activitiesModels.add(activitiesModel6);

                            ActivitiesModel activitiesModel7 = new ActivitiesModel();
                            String content7 ="数据制作";
                            String contract7 = areaPone;
                            activitiesModel7.setContent(content7);
                            activitiesModel2.setTimestamp(contract7);
                            activitiesModel7.setColor("green");
                            activitiesModel7.setIcon("el-icon-check");
                            activitiesModel7.setSize("large");
                            activitiesModels.add(activitiesModel7);

                            ActivitiesModel activitiesModel8 = new ActivitiesModel();
                            String content8 ="光、电路调度";
                            String contract8 =  areaPone;
                            activitiesModel8.setContent(content8);
                            activitiesModel3.setTimestamp(contract8);
                            activitiesModel8.setColor("green");
                            activitiesModel8.setIcon("el-icon-check");
                            activitiesModel8.setSize("large");
                            activitiesModels.add(activitiesModel8);

                            ActivitiesModel activitiesModel9 = new ActivitiesModel();
                            String content9 = "装机人员首响";
                            String contract9 = "环节联系人:"+ contactsInstallCompany+",联系电话:"+contactsPhone+",到达环节时间:"+informationPremierTime;
                            activitiesModel9.setContent(content9);
                            activitiesModel9.setSize("large");
                            activitiesModel9.setTimestamp(contract9);
                            activitiesModel9.setColor("green");
                            activitiesModel9.setIcon("el-icon-check");
                            activitiesModels.add(activitiesModel9);

                            ActivitiesModel activitiesModel10 = new ActivitiesModel();
                            String bohui;
                            if(!StringUtils.isBlank(informationDredgeRejectCause)){
                                bohui = ",驳回原因:"+informationDredgeRejectCause;

                            }else{
                                bohui = ",驳回原因:"+informationDesignRejectCause;

                            }
                            String content10 = "客户预约阶段被驳回";
                            String contract10 = "环节联系人:"+ contactsInstallCompany+",联系电话:"+contactsPhone+bohui;
                            activitiesModel10.setContent(content10);
                            activitiesModel10.setTimestamp(contract10);
                            activitiesModel10.setColor("red");
                            activitiesModel10.setIcon("el-icon-close");
                            activitiesModel10.setSize("large");
                            activitiesModels.add(activitiesModel10);
                        }
                        if (!StringUtils.isBlank(informationCilentOrderTime) && !StringUtils.isBlank(informationCilentOrderTime) ){

                            ActivitiesModel activitiesModel6 = new ActivitiesModel();
                            String content6 ="方案设计";
                            String contract6 = areaPone;
                            activitiesModel6.setContent(content6);
                            activitiesModel6.setTimestamp(contract6);
                            activitiesModel6.setColor("green");
                            activitiesModel6.setIcon("el-icon-check");
                            activitiesModel6.setSize("large");
                            activitiesModels.add(activitiesModel6);

                            ActivitiesModel activitiesModel7 = new ActivitiesModel();
                            String content7 ="数据制作";
                            String contract7 = areaPone;
                            activitiesModel7.setContent(content7);
                            activitiesModel2.setTimestamp(contract7);
                            activitiesModel7.setColor("green");
                            activitiesModel7.setIcon("el-icon-check");
                            activitiesModel7.setSize("large");
                            activitiesModels.add(activitiesModel7);

                            ActivitiesModel activitiesModel8 = new ActivitiesModel();
                            String content8 ="光、电路调度";
                            String contract8 =  areaPone;
                            activitiesModel8.setContent(content8);
                            activitiesModel3.setTimestamp(contract8);
                            activitiesModel8.setColor("green");
                            activitiesModel8.setIcon("el-icon-check");
                            activitiesModel8.setSize("large");
                            activitiesModels.add(activitiesModel8);

                            ActivitiesModel activitiesModel9 = new ActivitiesModel();
                            String content9 = "装机人员首响";
                            String contract9 = "环节联系人:"+ contactsInstallCompany+",联系电话:"+contactsPhone+",到达环节时间:"+informationPremierTime;
                            activitiesModel9.setContent(content9);
                            activitiesModel9.setSize("large");
                            activitiesModel9.setTimestamp(contract9);
                            activitiesModel9.setColor("green");
                            activitiesModel9.setIcon("el-icon-check");
                            activitiesModels.add(activitiesModel9);

                            ActivitiesModel activitiesModel10 = new ActivitiesModel();
                            String conten10 = "客户已预约";
                            String contentbc ="客户预约时间:"+informationCilentOrderTime;
                            String contract10 = "环节联系人:"+ contactsInstallCompany+",联系电话:"+contactsPhone+",到达环节时间:"+contentbc;
                            activitiesModel10.setContent(conten10);
                            activitiesModel10.setSize("large");
                            activitiesModel10.setTimestamp(contract10);
                            activitiesModel10.setColor("green");
                            activitiesModel10.setIcon("el-icon-check");
                            activitiesModels.add(activitiesModel10);

                            ActivitiesModel activitiesModel11 = new ActivitiesModel();
                            String bohui;
                            if(!StringUtils.isBlank(informationDredgeRejectCause)){
                                bohui = ",驳回原因:"+informationDredgeRejectCause;

                            }else{
                                bohui = ",驳回原因:"+informationDesignRejectCause;

                            }
                            String content11 = "装机阶段被驳回";
                            String contract11 = "环节联系人:"+ contactsInstallCompany+",联系电话:"+contactsPhone+bohui;
                            activitiesModel11.setContent(content11);
                            activitiesModel11.setTimestamp(contract11);
                            activitiesModel11.setTimestamp(bohui);
                            activitiesModel11.setColor("red");
                            activitiesModel11.setIcon("el-icon-close");
                            activitiesModel11.setSize("large");
                            activitiesModels.add(activitiesModel11);

                        }
                    }//非PON开通被驳回

                }//判断在开通阶段被驳回
            }//被驳回阶段工单

            return activitiesModels;
        }


    }

    public  List<List<DataItemsModel>> getTwoCondition(TwoConditionModel obj) {
        List<String> dataItem = obj.getDataItem();
        String value = obj.getValue();
        List<List<DataItemsModel>> dataItemslist = new ArrayList<>();
        List<InformationOpenModel> informationOpenModels =  informationOpenDao.getbossAll("","","",value,"");
        for(int i = 0 ; i<dataItem.size();i++){
            String dataItemString = dataItem.get(i);
            for(int j =0;j<informationOpenModels.size();j++){
                String bossMerberNum = informationOpenModels.get(j).getBossMerberNum();
                if(dataItemString.equals(bossMerberNum)){
                    String bossBusinessTime = informationOpenModels.get(j).getBossBusinessTime();//商机生成时间
                    String bossDebugStart = informationOpenModels.get(j).getBossDebugStart();
                    String bossBusinessName1 = informationOpenModels.get(j).getBossBusinessName();
                    String ponStateRejected = informationOpenModels.get(j).getPonStateRejected();
                    String ponState = informationOpenModels.get(j).getPonState();
                    String prospectSate = informationOpenModels.get(j).getProspectSate();
                    String prospectStateReject = informationOpenModels.get(j).getProspectStateReject();
                    String ponProcessClassification  = informationOpenModels.get(j).getPonProcessClassification();
                    String prospectProcessClassification = informationOpenModels.get(j).getProspectProcessClassification();
                    List<DataItemsModel> dataItemsModels = new ArrayList<>();
                    DataItemsModel dataItemsModel = new DataItemsModel();
                    dataItemsModel.setLabel("成员号码");
                    dataItemsModel.setValue(bossMerberNum);
                    dataItemsModels.add(dataItemsModel);

                    DataItemsModel dataItemsModel1 = new DataItemsModel();
                    dataItemsModel1.setLabel("商机生成时间");
                    dataItemsModel1.setValue(bossBusinessTime);
                    dataItemsModels.add(dataItemsModel1);

                    DataItemsModel dataItemsModel2 = new DataItemsModel();
                    dataItemsModel2.setLabel("流程类型");
                    if(!StringUtils.isBlank(ponProcessClassification)) {
                        dataItemsModel2.setValue(ponProcessClassification);
                    }else{
                        dataItemsModel2.setValue(prospectProcessClassification);
                    }
                    dataItemsModels.add(dataItemsModel2);



                    DataItemsModel dataItemsModel3 = new DataItemsModel();
                    dataItemsModel3.setLabel("是否有资源");
                    if(StringUtils.isBlank(bossDebugStart)){
                        dataItemsModel3.setValue("有资源");
                    }else {
                        dataItemsModel3.setValue("无资源");
                    }

                    dataItemsModels.add(dataItemsModel3);



                    DataItemsModel dataItemsModel4 = new DataItemsModel();
                    dataItemsModel4.setLabel("专线当前状态");
                    if("归档".equals(ponState) && (StringUtils.isBlank(ponStateRejected))){
                        dataItemsModel4.setValue("已开通");
                    }else if(("驳回").equals(ponStateRejected)){
                        dataItemsModel4.setValue("现场开通被驳回");
                    }else if("驳回".equals(prospectStateReject) ){
                        dataItemsModel4.setValue("现场查勘被驳回");
                    }else if(((StringUtils.isBlank(ponStateRejected) ) && "归档".equals(prospectSate) && (!"归档".equals(ponState))|| (StringUtils.isBlank(bossDebugStart)) && (StringUtils.isBlank(ponStateRejected)) && (!"归档".equals(ponState) )) ){
                        dataItemsModel4.setValue("现场开通中");
                    }else if(!StringUtils.isBlank(bossDebugStart)  && (!"归档".equals(prospectSate)) ){
                        dataItemsModel4.setValue("现场查勘中");
                    }else {
                        dataItemsModel4.setValue("还未派发工单");
                    }
                    dataItemsModels.add(dataItemsModel4);

                    DataItemsModel dataItemsModel5 = new DataItemsModel();
                    dataItemsModel5.setLabel("商机名称");
                    String bossBusinessName2 = replaceString.replaceString2Star(bossBusinessName1,11,0);
                    dataItemsModel5.setValue(bossBusinessName2);
                    dataItemsModels.add(dataItemsModel5);


                    dataItemslist.add(dataItemsModels);
                }
            }
        }
        return dataItemslist;

    }
    //判断黑名单
    public BlackListModel isblacklist(String userid) {
        BlackListModel blackListModel =  informationOpenDao.isblacklist(userid);
        return blackListModel;
    }
    //判断白名单
    public LineWhiteListModel iswhitelist(String userid) {
        LineWhiteListModel lineWhiteListModel =  informationOpenDao.iswhitelist(userid);
        return lineWhiteListModel;
    }
    //是否是客户经理
    public List<String> isManager(String userid) {
        List<String>  namelist =  informationOpenDao.isManager(userid);
        return namelist ;
    }

    public List<List<DataItemsModel>> getlinkmanboss(String bossMerberNum, String bossGroupCoding, String bossSetName, String bossBusinessName, String informationLinkMan, String userid) {
        List<InformationOpenModel> informationOpenModelList = informationOpenDao.getlinkmanboss(bossMerberNum,bossGroupCoding,bossSetName,bossBusinessName,informationLinkMan,userid);
        List<List<DataItemsModel>> dataItemslist = new ArrayList<>();
        for(int i = 0;i<informationOpenModelList.size();i++){
            List<DataItemsModel> dataItemsModels = new ArrayList<>();
            String bossMerberNum1 = informationOpenModelList.get(i).getBossMerberNum(); //成员号码
            String bossBusinessTime = informationOpenModelList.get(i).getBossBusinessTime();//商机生成时间
            String bossDebugStart = informationOpenModelList.get(i).getBossDebugStart();
            String bossBusinessName1 = informationOpenModelList.get(i).getBossBusinessName();
            String ponStateRejected = informationOpenModelList.get(i).getPonStateRejected();
            String ponState = informationOpenModelList.get(i).getPonState();
            String prospectSate = informationOpenModelList.get(i).getProspectSate();
            String prospectStateReject = informationOpenModelList.get(i).getProspectStateReject();
            String ponProcessClassification  = informationOpenModelList.get(i).getPonProcessClassification();
            String prospectProcessClassification = informationOpenModelList.get(i).getProspectProcessClassification();
            DataItemsModel dataItemsModel = new DataItemsModel();
            dataItemsModel.setLabel("成员号码");
            dataItemsModel.setValue(bossMerberNum1);
            dataItemsModels.add(dataItemsModel);

            DataItemsModel dataItemsModel1 = new DataItemsModel();
            dataItemsModel1.setLabel("商机生成时间");
            dataItemsModel1.setValue(bossBusinessTime);
            dataItemsModels.add(dataItemsModel1);

            DataItemsModel dataItemsModel2 = new DataItemsModel();
            dataItemsModel2.setLabel("流程类型");
            if(!StringUtils.isBlank(ponProcessClassification)) {
                dataItemsModel2.setValue(ponProcessClassification);
            }else{
                dataItemsModel2.setValue(prospectProcessClassification);
            }
            dataItemsModels.add(dataItemsModel2);



            DataItemsModel dataItemsModel3 = new DataItemsModel();
            dataItemsModel3.setLabel("是否有资源");
            if(StringUtils.isBlank(bossDebugStart)){
                dataItemsModel3.setValue("有资源");
            }else {
                dataItemsModel3.setValue("无资源");
            }

            dataItemsModels.add(dataItemsModel3);



            DataItemsModel dataItemsModel4 = new DataItemsModel();
            dataItemsModel4.setLabel("专线当前状态");
            if("归档".equals(ponState) && (StringUtils.isBlank(ponStateRejected))){
                dataItemsModel4.setValue("已开通");
            }else if(("驳回").equals(ponStateRejected)){
                dataItemsModel4.setValue("现场开通被驳回");
            }else if("驳回".equals(prospectStateReject) ){
                dataItemsModel4.setValue("现场查勘被驳回");
            }else if(((StringUtils.isBlank(ponStateRejected) ) && "归档".equals(prospectSate) && (!"归档".equals(ponState))|| (StringUtils.isBlank(bossDebugStart)) && (StringUtils.isBlank(ponStateRejected)) && (!"归档".equals(ponState) )) ){
                dataItemsModel4.setValue("现场开通中");
            }else if(!StringUtils.isBlank(bossDebugStart)  && (!"归档".equals(prospectSate)) ){
                dataItemsModel4.setValue("现场查勘中");
            }else {
                dataItemsModel4.setValue("还未派发工单");
            }
            dataItemsModels.add(dataItemsModel4);

            DataItemsModel dataItemsModel5 = new DataItemsModel();
            dataItemsModel5.setLabel("商机名称");
            String bossBusinessName2 = replaceString.replaceString2Star(bossBusinessName1,11,0);
            dataItemsModel5.setValue(bossBusinessName2);
            dataItemsModels.add(dataItemsModel5);


            dataItemslist.add(dataItemsModels);
        }
        return dataItemslist;


    }
}
