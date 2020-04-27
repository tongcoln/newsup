package com.tt.newsup.server;

import com.tt.newsup.dao.ServiceHallDao;
import com.tt.newsup.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ：tt
 * @date ：Created in 2020/4/27 10:36 上午
 * @description：
 * @modified By：
 * @version:
 */
@Service
public class ServiceHallService {

    @Autowired
    private ServiceHallDao serviceHallDao;


    /**
     * 获取网络线条的默认课题
     * @return
     */
    public List<ServiceStoreTopicNetworkDomeModel> getTopicNetworkDome() {
        List<ServiceStoreTopicNetworkDomeModel>  serviceStoreTopicNetworkDomeModel = serviceHallDao.getTopicNetworkDome();
        return serviceStoreTopicNetworkDomeModel;
    }

    /**
     * 获取市场线条的默认课题
     * @return
     */
    public List<ServiceStoreTopicMarketplaceDomeModel> getmarketplacetopic() {
        List<ServiceStoreTopicMarketplaceDomeModel> storeTopicMarketplaceDomeModels = serviceHallDao.getmarketplacetopic();
        return storeTopicMarketplaceDomeModels;
    }

    /**
     * 保存选择了网络线条的课题
     * @param networktopic
     * @param user
     */
    public void savenetworktopic(String networktopic, String user) {
        serviceHallDao.savenetworktopic(networktopic,user);
    }

    /**
     * 获取我的课题列表
     * @param user
     * @return
     */
    public ServiceStoreTopicProceedModel getmytopiclist(String user) {
        ServiceStoreTopicProceedModel serviceStoreTopicProceedModel = serviceHallDao.getmytopiclist(user);
        return serviceStoreTopicProceedModel;
    }

    /**
     * 获取网络线条的默认课题列表
     * @return
     */
    public List<String> getnetworktopic() {
        List<String> strings = serviceHallDao.getnetworktopic();
        return strings;
    }

    /**
     * 保存选择的市场线条的课题
     * @param markettopic
     * @param user
     */
    public void savemarkettopic(String markettopic, String user) {
        serviceHallDao.savemarkettopic(markettopic,user);
    }

    /**
     * 查询市场线条的默认课题
     * @return
     */
    public List<String> getmarkettopic() {
        List<String>  strings = serviceHallDao.getmarkettopic();
        return strings;
    }

    /**
     * 获取要巡检站厅列表
     * @param
     * @return
     */
    public List<ServiceHallModel> getAllHall() {
        List<ServiceHallModel> serviceHallModels =  serviceHallDao.selectAll();
        return serviceHallModels;
    }

    /**
     *
     * @param user
     * @return
     */
    public ServiceHallProceedModel getServiceHallProceed(String user) {
        ServiceHallProceedModel serviceHallProceedModel = serviceHallDao.getServiceHallProceed(user);
        return serviceHallProceedModel;
    }

    //查询同一站厅的同一时间段是否有2条记录，如果有2条记录了则提醒该站厅该时间段预约已满，请选择其他站厅或其他时间段
    public Integer getcount(String serviceStoreDitchCode, String datemodel, String datemodel1) {
        Integer count = serviceHallDao.getcount(serviceStoreDitchCode,datemodel,datemodel1);
        return count;
    }

    /**
     * 插入抢单信息
     * @param serviceStoreDitchCode 站点编码
     * @param user 用户
     * @param datemodel 日期
     * @param datamodel1 上午或者下午
     * @param mytopicId 课题ID
     */

    public void insertprogress(String serviceStoreDitchCode, String user, String datemodel, String datamodel1, Integer mytopicId) {
        serviceHallDao.insertprogress(serviceStoreDitchCode,user,datemodel,datamodel1,mytopicId);
    }

    /**
     * 首页搜索按钮，信息筛选
     * @param results
     * @return
     */
    public List<ServiceHallModel> getAllHallByName(String results) {
        List<ServiceHallModel> serviceHallModels =  serviceHallDao.selectAllByName(results);
        return serviceHallModels;
    }
}
