package com.tt.newsup.server;
/**
 * 网开数据的清洗
 */

import com.tt.newsup.dao.OpenNetworkDao;
import com.tt.newsup.dao.OpenNetworkNewDao;
import com.tt.newsup.model.OpenNetworkModel;
import com.tt.newsup.model.OpenNetworkNewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class OpenNetworkService {

    @Autowired
    private OpenNetworkDao openNetworkDao;

    @Autowired
    private OpenNetworkNewDao openNetworkNewDao;

    public void  getAll(){
        /**
         *清洗工单流水号相同，产品实例标识相同，流程分类相同，选取时间较近的数据保留
         */



        /**
         * 清洗相同的产品实例标识，相同的流程分类，选取时间较近的数据保留
         */
        List<OpenNetworkModel>  openNetworkModelList = openNetworkDao.getAll();
        for(int i=0;i<openNetworkModelList.size();i++){
            Integer openNetworkId = openNetworkModelList.get(i).getOpenNetworkId();
            String openNetworkIdentifying = openNetworkModelList.get(i).getOpenNetworkIdentifying();
            String openNetworkProcessClassification = openNetworkModelList.get(i).getOpenNetworkProcessClassification();
            String openNetworkOrderTime = openNetworkModelList.get(i).getOpenNetworkOrderTime();
            for (int j =0;j<openNetworkModelList.size();j++){
                Integer openNetworkId1 = openNetworkModelList.get(j).getOpenNetworkId();
                String openNetworkIdentifying1 = openNetworkModelList.get(j).getOpenNetworkIdentifying();
                String openNetworkProcessClassification1 = openNetworkModelList.get(j).getOpenNetworkProcessClassification();
                String openNetworkOrderTime1 = openNetworkModelList.get(j).getOpenNetworkOrderTime();
                if(openNetworkIdentifying.equals(openNetworkIdentifying1) && openNetworkProcessClassification.equals(openNetworkProcessClassification1)){
                    System.out.println(openNetworkOrderTime.compareTo(openNetworkOrderTime1));
                    if (openNetworkOrderTime.compareTo(openNetworkOrderTime1)>0){
                        openNetworkDao.deledata(openNetworkId1);
                    }
                    if(openNetworkOrderTime.compareTo(openNetworkOrderTime1)<0){
                        openNetworkDao.deledata(openNetworkId);
                    }

                }

            }
        }
        /**
         * 清洗同一产品标识，非查勘阶段的开通工单选取时间较近的保留
         */
        List<OpenNetworkModel>  openNetworkModelList1 = openNetworkDao.getAll();
            for(int i =0;i<openNetworkModelList1.size();i++){
                Integer openNetworkId = openNetworkModelList1.get(i).getOpenNetworkId();
                String openNetworkIdentifying = openNetworkModelList1.get(i).getOpenNetworkIdentifying();
                String openNetworkProcessClassification = openNetworkModelList1.get(i).getOpenNetworkProcessClassification();
                String openNetworkOrderTime = openNetworkModelList1.get(i).getOpenNetworkOrderTime();
                if (!"本地专线查勘流程".equals(openNetworkProcessClassification)) {
                    for (int j = 0; j < openNetworkModelList1.size(); j++) {
                        Integer openNetworkId1 = openNetworkModelList1.get(j).getOpenNetworkId();
                        String openNetworkIdentifying1 = openNetworkModelList1.get(j).getOpenNetworkIdentifying();
                        String openNetworkProcessClassification1 = openNetworkModelList1.get(j).getOpenNetworkProcessClassification();
                        String openNetworkOrderTime1 = openNetworkModelList1.get(j).getOpenNetworkOrderTime();
                        if(!"本地专线查勘流程".equals(openNetworkProcessClassification1)) {
                            if (openNetworkIdentifying.equals(openNetworkIdentifying1)) {
                                System.out.println(openNetworkOrderTime.compareTo(openNetworkOrderTime1));
                                if (openNetworkOrderTime.compareTo(openNetworkOrderTime1) > 0) {
                                    openNetworkDao.deledata(openNetworkId1);
                                }
                                if (openNetworkOrderTime.compareTo(openNetworkOrderTime1) < 0) {
                                    openNetworkDao.deledata(openNetworkId);
                                }

                            }
                        }


                    }
                }
            }

        /**
         * 生成新的逻辑
         */

        List<String> identifyinglist = openNetworkDao.getidentifying();
        for (int i =0;i<identifyinglist.size();i++){
            String  identifying = identifyinglist.get(i);
            List<OpenNetworkModel> openNetworkModels = openNetworkDao.getAlldata(identifying);
            OpenNetworkNewModel openNetworkNewModel = new OpenNetworkNewModel();
            for (int j = 0;j<openNetworkModels.size();j++){
                String openNetworkCity = openNetworkModels.get(j).getOpenNetworkCity();
                String openNetworkArea = openNetworkModels.get(j).getOpenNetworkArea();
                String openNetworkOrderId = openNetworkModels.get(j).getOpenNetworkOrderId();
                String openNetworkIdentifying = openNetworkModels.get(j).getOpenNetworkIdentifying();
                String openNetworkTitle = openNetworkModels.get(j).getOpenNetworkTitle();
                String openNetworkProcessClassification = openNetworkModels.get(j).getOpenNetworkProcessClassification();
                String openNetworkOrderTime = openNetworkModels.get(j).getOpenNetworkOrderTime();
                String openNetworkState = openNetworkModels.get(j).getOpenNetworkState();
                String openNetworkEndTime = openNetworkModels.get(j).getOpenNetworkEndTime();
                String openNetworkStateNow = openNetworkModels.get(j).getOpenNetworkStateNow();
                String openNetworkStateRejected = openNetworkModels.get(j).getOpenNetworkStateRejected();
                String openNetworkExplorationTime = openNetworkModels.get(j).getOpenNetworkExplorationTime();
                String openNetworkContractTime = openNetworkModels.get(j).getOpenNetworkContractTime();
                String openNetworkConstructionTime = openNetworkModels.get(j).getOpenNetworkConstructionTime();
                String openNetworkOpenTime = openNetworkModels.get(j).getOpenNetworkOpenTime();
                openNetworkNewModel.setOpenNetworkCity(openNetworkCity);
                openNetworkNewModel.setOpenNetworkArea(openNetworkArea);
                openNetworkNewModel.setOpenNetworkIdentifying(openNetworkIdentifying);
                openNetworkNewModel.setOpenNetworkTitle(openNetworkTitle);
                openNetworkNewModel.setProspectExplorationTime(openNetworkExplorationTime);
                openNetworkNewModel.setProspectContractTime(openNetworkContractTime);
                openNetworkNewModel.setProspectConstructionTime(openNetworkConstructionTime);
                openNetworkNewModel.setPonOpenTime(openNetworkOpenTime);
                if("本地专线查勘流程".equals(openNetworkProcessClassification)){
                    openNetworkNewModel.setProspectProcessClassification(openNetworkProcessClassification);
                    openNetworkNewModel.setProspectOrderId(openNetworkOrderId);
                    openNetworkNewModel.setProspectOrderTime(openNetworkOrderTime);
                    openNetworkNewModel.setProspectStateReject(openNetworkStateRejected);
                    openNetworkNewModel.setProspectEndTime(openNetworkEndTime);
                    openNetworkNewModel.setProspectStatwNow(openNetworkStateNow);
                    openNetworkNewModel.setProspectSate(openNetworkState);

                }else{
                    openNetworkNewModel.setPonProcessClassification(openNetworkProcessClassification);
                    openNetworkNewModel.setPonOrderId(openNetworkOrderId);
                    openNetworkNewModel.setPonOrderTime(openNetworkOrderTime);
                    openNetworkNewModel.setPonStateRejected(openNetworkStateRejected);
                    openNetworkNewModel.setPonEndTime(openNetworkEndTime);
                    openNetworkNewModel.setPonStateNow(openNetworkStateNow);
                    openNetworkNewModel.setPonState(openNetworkState);
                }

            }

            openNetworkNewDao.save(openNetworkNewModel);
        }

    }
}
