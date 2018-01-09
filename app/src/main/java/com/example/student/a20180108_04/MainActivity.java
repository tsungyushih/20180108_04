package com.example.student.a20180108_04;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity {
    ListView lv;
    //String str[] = {"AA", "BB", "CCC", "DDDD", "EE"};     自己做layout，所以刪除
    ArrayList<Map<String,Object>> mylist =new ArrayList<>();
    boolean chks[]=new boolean[8];



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        HashMap<String, Object> m1 = new HashMap<>();
        m1.put("city", "台北");
        m1.put("code", "02");
        m1.put("img", R.drawable.p1);
        mylist.add(m1);
        HashMap<String, Object> m2 = new HashMap<>();
        m2.put("city", "台中");
        m2.put("code", "04");
        m2.put("img", R.drawable.p2);
        mylist.add(m2);
        HashMap<String, Object> m3 = new HashMap<>();
        m3.put("city", "台南");
        m3.put("code", "06");
        m3.put("img", R.drawable.p3);
        mylist.add(m3);
        HashMap<String, Object> m4 = new HashMap<>();
        m4.put("city", "高雄");
        m4.put("code", "07");
        m4.put("img", R.drawable.p4);
        mylist.add(m4);


        HashMap<String, Object> n8 = new HashMap<>();
        n8.put("city", "台北2");
        n8.put("code", "02");
        n8.put("img", R.drawable.p1);
        mylist.add(n8);
        HashMap<String, Object> m5 = new HashMap<>();
        m5.put("city", "台中2");
        m5.put("code", "04");
        m5.put("img", R.drawable.p2);
        mylist.add(m5);
        HashMap<String, Object> m6 = new HashMap<>();
        m6.put("city", "台南2");
        m6.put("code", "06");
        m6.put("img", R.drawable.p3);
        mylist.add(m6);
        HashMap<String, Object> m7 = new HashMap<>();
        m7.put("city", "高雄2");
        m7.put("code", "07");
        m7.put("img", R.drawable.p4);
        mylist.add(m7);

        lv = (ListView) findViewById(R.id.listView);
        //MyAdapter adapter = new MyAdapter();      為了把calss myadapter移出去，要加引數如下行
        MyAdapter adapter = new MyAdapter(MainActivity.this,mylist,chks);   //同class MyAdapter第23行原因，新增引數3chks
        lv.setAdapter(adapter);

    }

    public void click1(View v)
    {
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<chks.length;i++)
        {
            if(chks[i])     //將class MyAdapter移出去後，MainActivity跟MyAdapter的chks就是不同的chks，所以要改成指向同一個chks
            {
                sb.append(mylist.get(i).get("city")+",");
            }
        }
        Toast.makeText(MainActivity.this,sb.toString(),Toast.LENGTH_LONG).show();
    }

    /*因為已勾選部分移出手機畫面時，整個VIEW跟勾選會消失，如此反覆讀取與刪除對效能不好，
    所以優化手法有兩種，第一種為下方class MyAdapter移到外面的MyAdapter
    class MyAdapter extends BaseAdapter
    {

        @Override
        public int getCount() {
            //return str.length;
            return  mylist.size();
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
        public View getView(final int position, View view, ViewGroup viewGroup) {
            //TextView tv = new TextView(MainActivity.this);        自己做layout的話，這行要改成下面那行
            LayoutInflater inflater=LayoutInflater.from(MainActivity.this);
            View v = inflater.inflate(R.layout.myitem, null);   //解壓器，詳閱20180105_01的Dialog

            Log.d("GetView","position"+position);   //看Logcat用

            TextView tv=v.findViewById(R.id.textView);
            //tv.setText(str[position]);
            tv.setText(mylist.get(position).get("city").toString());
            TextView tv2 = v.findViewById(R.id.textView2);
            tv2.setText(mylist.get(position).get("code").toString());
            ImageView img = v.findViewById(R.id.imageView);
            img.setImageResource((Integer) mylist.get(position).get("img"));

            CheckBox chk = (CheckBox) v.findViewById(R.id.checkBox);
            chk.setChecked(chks[position]);
            chk.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean b) {
                    chks[position]=b;

                }
            });



            //return tv;    自己做layout的話，改傳v
            return  v;
        }


    }*/
}
