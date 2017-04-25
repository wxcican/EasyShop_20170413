package com.fuicuiedu.xc.easyshop_20170413.main.me.goodsupload;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fuicuiedu.xc.easyshop_20170413.model.ImageItem;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/4/25 0025.
 */

public class GoodsUpLoadAdapter extends RecyclerView.Adapter{

    //适配器的数据
    private ArrayList<ImageItem> list = new ArrayList<>();
    private LayoutInflater inflater;

    public GoodsUpLoadAdapter(Context context,ArrayList<ImageItem> list){
        inflater = LayoutInflater.from(context);
        this.list = list;
    }

    // ###############################    逻辑：模式的选择 start ###################
    //编辑时的模式，1 = 有图，2 = 无图（显示加号图片的布局）
    public static final int MODE_NORMAL = 1;
    public static final int MODE_MULTI_SELECT = 2;

    //代表图片的编辑模式
    public int mode;

    //用枚举，便是item类型，有图或无图
    public enum ITEM_TYPE{
        ITEM_NORMAL,ITEM_ADD
    }

    //模式设置
    public void changeMode(int mode){
        this.mode = mode;
        notifyDataSetChanged();
    }

    //获取当前模式
    public int getMode(){
        return mode;
    }
    // ###############################    逻辑：模式的选择 end ###################

    // ############################  外部调用的相关方法  start ##################
    //添加图片（imageitem）
    public void add(ImageItem imageItem){
        list.add(imageItem);
    }

    public int getSize(){
        return list.size();
    }

    //获取数据
    public ArrayList<ImageItem> getList(){
        return list;
    }

    //刷新数据
    public void notifyData(){
        notifyDataSetChanged();
    }
    // ############################  外部调用的相关方法  end ##################

    //确定ViewType的值
    @Override
    public int getItemViewType(int position) {
        //当position与图片数量相同时，则为加号布局
        if (position == list.size()) return ITEM_TYPE.ITEM_ADD.ordinal();
        return ITEM_TYPE.ITEM_NORMAL.ordinal();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        if (viewType == ???){
//            ????
//        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    //显示添加按钮的ViewHolder
    public static class ItemAddViewHolder extends RecyclerView.ViewHolder{

        public ItemAddViewHolder(View itemView) {
            super(itemView);
        }
    }

    //已经有图片的ViewHolder
    public static class ItemSelectViewHolder extends RecyclerView.ViewHolder{

        public ItemSelectViewHolder(View itemView) {
            super(itemView);
        }
    }
}
