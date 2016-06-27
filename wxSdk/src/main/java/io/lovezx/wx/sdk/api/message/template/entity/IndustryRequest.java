package io.lovezx.wx.sdk.api.message.template.entity;

import io.lovezx.wx.sdk.SelfValid;
import io.lovezx.wx.sdk.http.JSONContent;

public class IndustryRequest extends JSONContent implements SelfValid{
    private String industry_id1;
    private String industry_id2;
    public String getIndustry_id1() {
        return industry_id1;
    }
    public String getIndustry_id2() {
        return industry_id2;
    }
    public void setIndustry_id1(String industry_id1) {
        this.industry_id1 = industry_id1;
    }
    public void setIndustry_id2(String industry_id2) {
        this.industry_id2 = industry_id2;
    }
    public boolean checkValid() {
        int industry1 = 0;
        int industry2 = 0;
        try {
            industry1 = Integer.parseInt(industry_id1);
            industry2 = Integer.parseInt(industry_id2);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        /*行业设置为 1--41*/
        return !(industry1<1 || industry1>41 || industry2<1 || industry2>41);
    }
}
