package com.tt.newsup.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ：tt
 * @date ：Created in 2020/5/7 1:30 下午
 * @description：体验者执行表临时存放图片的模型
 * @modified By：
 * @version:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceTasteImgModel {
    private Integer serviceTasteImgId;//service_taste_img_id
    private String serviceTasteMarketImgUserid;//service_taste_market_img_userid
    private Integer service_taste_market_img_table_id;//service_taste_market_img_table_id
    private Integer serviceTasteMarketImgIndex;//service_taste_market_img_index
    private String serviceTasteMarketImgPath;//service_taste_market_img_path

}
