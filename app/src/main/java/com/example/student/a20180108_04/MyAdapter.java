package com.example.student.a20180108_04;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Student on 2018/1/9.
 */

public class MyAdapter extends BaseAdapter {
    ArrayList<Map<String, Object>> mylist = new ArrayList<>();
    //boolean chks[] = new boolean[8];      將class MyAdapter移出去後，MainActivity跟MyAdapter的chks就是不同的chks(會導致Toast出不來的bug)，所以要改成指向同一個chks所以本行要改成如下一行
    boolean chks[];
    Context context;    //移過來要加這段

    //移過來要加建構式
    public MyAdapter(Context context, ArrayList<Map<String, Object>> mylist,boolean chks[]) {   //同第23行原因，新增引數boolean chks[](由於MyAdapter是我們自己創，所以可以自由編輯引數)
        this.context=context;
        this.mylist=mylist;
        this.chks=chks;     //同第23行原因，新增此行
    }

    @Override
    public int getCount() {
        //return str.length;
        return mylist.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int position, View v, ViewGroup viewGroup) {
        //TextView tv = new TextView(MainActivity.this);        自己做layout的話，這行要改成下面那行
        //LayoutInflater inflater = LayoutInflater.from(MainActivity.this);   引數要改成下方那樣
        ViewHolder viewHolder;
        //新增下方if，getView中引數View是關鍵，而這行的意思是，View是null就重抓View，不是就不抓，
        //由於新增viewHolder，所以變數如TextView全部要改成viewHolder
        if(v == null)
        {
            LayoutInflater inflater = LayoutInflater.from(context);
            v = inflater.inflate(R.layout.myitem, null);   //解壓器，詳閱20180105_01的Dialog
            viewHolder = new ViewHolder();  //若不加這行，次行會無法初始化
            viewHolder.tv = v.findViewById(R.id.textView);
            viewHolder.tv2 = v.findViewById(R.id.textView2);
            viewHolder.img = v.findViewById(R.id.imageView);
            viewHolder.chk = (CheckBox) v.findViewById(R.id.checkBox);
            v.setTag(viewHolder);   //新增此行
        }
        else    //因為每次刷新時要findViewById太浪費時間，所以要設立一個Tag(回收區)，讓被刷掉的View存進去，新的view不為null時，就直接去Tag取View
        {
            viewHolder= (ViewHolder) v.getTag();   //上標籤
        }

//        整理語法，把findViewById都放一起，然後移到上方if裡
//        TextView tv = v.findViewById(R.id.textView);
//        TextView tv2 = v.findViewById(R.id.textView2);
//        ImageView img = v.findViewById(R.id.imageView);
//        CheckBox chk = (CheckBox) v.findViewById(R.id.checkBox);
//              下方全加viewHolder
//        tv.setText(mylist.get(position).get("city").toString());
//        tv2.setText(mylist.get(position).get("code").toString());
//        img.setImageResource((Integer) mylist.get(position).get("img"));

          viewHolder.tv.setText(mylist.get(position).get("city").toString());
          viewHolder.tv2.setText(mylist.get(position).get("code").toString());
          viewHolder.img.setImageResource((Integer) mylist.get(position).get("img"));
          viewHolder.chk.setOnCheckedChangeListener(null);  //新增此行，原本第一次新增時checkbox是null(例如第0個)，打勾時變true，被刷掉後丟進回收桶，等別的checkbox取用回收桶資源時(例如第5個)，再將之轉成null，再回到第0個時，因為上方else條件而取Tag等等....總之不加此行的話，滑過頭勾還是會消失
          viewHolder.chk.setChecked(chks[position]);
          viewHolder.chk.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean b) {
                chks[position] = b;

            }
        });
        //return tv;    自己做layout的話，改傳v
        return v;
    }

    static class ViewHolder     //新增class，把原本要回收的View放進來?
    {
        TextView tv;
        TextView tv2;
        ImageView img;
        CheckBox chk;
    }
}
//  當我們在用Adapter時，會不知道寫法有沒有降低效能，但如果照著RecycleView(可上網找寫法)的話，就能優化較能(但寫法嚴謹)