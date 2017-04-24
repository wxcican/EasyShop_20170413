package com.fuicuiedu.xc.easyshop_20170413.main.shop.details;

import com.fuicuiedu.xc.easyshop_20170413.model.GoodsDetail;
import com.fuicuiedu.xc.easyshop_20170413.model.GoodsDetailResult;
import com.fuicuiedu.xc.easyshop_20170413.network.EasyShopClient;
import com.fuicuiedu.xc.easyshop_20170413.network.UICallBack;
import com.google.gson.Gson;
import com.hannesdorfmann.mosby.mvp.MvpNullObjectBasePresenter;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;

/**
 * Created by Administrator on 2017/4/21 0021.
 */

public class GoodsDetailPresenter extends MvpNullObjectBasePresenter<GoodsDetailView>{

    //获取详情的call
    private Call getDetailCall;

    @Override
    public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);
        if (getDetailCall != null) getDetailCall.cancel();
    }

    //获取商品详细数据
    public void getData(String uuid){
        getView().showProgress();
        getDetailCall = EasyShopClient.getInstance().getGoodsData(uuid);
        getDetailCall.enqueue(new UICallBack() {
            @Override
            public void onFailureUI(Call call, IOException e) {
                getView().hideProgress();
                getView().showMessage(e.getMessage());
            }

            @Override
            public void onResponseUI(Call call, String body) {
                getView().hideProgress();
                GoodsDetailResult result = new Gson().fromJson(body,GoodsDetailResult.class);
                if (result.getCode() == 1){
                    //商品详情
                    GoodsDetail goodsDetail = result.getDatas();
                    //用来存放图片路径的集合
                    ArrayList<String> list = new ArrayList<String>();
                    for (int i = 0; i < goodsDetail.getPages().size(); i++) {
                        String page = goodsDetail.getPages().get(i).getUri();
                        list.add(page);
                    }
                    getView().setImageData(list);
                    getView().setData(goodsDetail,result.getUser());
                }else{
                    getView().showError();
                }
            }
        });
    }

    // TODO: 2017/4/24 0024  删除商品
}
