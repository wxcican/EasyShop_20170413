package com.fuicuiedu.xc.easyshop_20170413.main.shop.details;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.fuicuiedu.xc.easyshop_20170413.R;
import com.fuicuiedu.xc.easyshop_20170413.components.AvatarLoadOptions;
import com.fuicuiedu.xc.easyshop_20170413.network.EasyShopApi;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.relex.circleindicator.CircleIndicator;

//商品图片展示的详情页面

public class GoodsDetailInfoActivity extends AppCompatActivity {

    //需要拿到图片的地址
    private static final String IMAGES = "images";

    public static Intent getStartIntent(Context context, ArrayList<String> imgUrls){
        Intent intent = new Intent(context,GoodsDetailInfoActivity.class);
        intent.putExtra(IMAGES,imgUrls);
        return intent;
    }

    @BindView(R.id.viewpager)
    ViewPager viewPager;
    @BindView(R.id.indicator)
    CircleIndicator indicator;

    private ArrayList<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_detail_info);
        list = new ArrayList<>();
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        ButterKnife.bind(this);
        list = getIntent().getStringArrayListExtra(IMAGES);
        //复用商品详情页的adapter
        GoodsDetailAdapter adapter = new GoodsDetailAdapter(getImage(list));
        viewPager.setAdapter(adapter);
        adapter.setListener(new GoodsDetailAdapter.OnItemClickListener() {
            @Override
            public void onItemClick() {
                finish();
            }
        });
        indicator.setViewPager(viewPager);
    }

    //根据路径来实现imageView集合
    private ArrayList<ImageView> getImage(ArrayList<String> list){
        ArrayList<ImageView> list_image = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            ImageView view = new ImageView(this);
            ImageLoader.getInstance()
                    .displayImage(EasyShopApi.IMAGE_URL + list.get(i),
                            view, AvatarLoadOptions.build_item());
            list_image.add(view);
        }
        return list_image;
    }
}
