package com.example.mac.exam;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toolbar;


import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//这个继承必须是fragment，不然后面那个new不成功。
public class SisterFragment extends Fragment{

    private String test;
    private TextView testv;
    private Button btn;
    private String url;
    private Toolbar toolbar;
    private ListView listview;
    private SimpleAdapter adapter;


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_sister, container, false);
        listview = (ListView) view.findViewById(android.R.id.list);
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        url = "http://jandan.net/?oxwlxojflwblxbsapi=jandan.get_ooxx_comments&page=1";
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .detectDiskReads()
                .detectDiskWrites()
                .detectNetwork()   // or .detectAll() for all detectable problems
                .penaltyLog()
                .build());
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                .detectLeakedSqlLiteObjects()
                .detectLeakedClosableObjects()
                .penaltyLog()
                .penaltyDeath()
                .build());
        new Thread(){
            @Override
            public void run() {
                super.run();
                handler.sendEmptyMessage(0);

            }
        }.start();
    }

    private Handler handler = new Handler() {

        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    try {
                        test = readParse("http://jandan.net/?oxwlxojflwblxbsapi=jandan.get_ooxx_comments&page=1");

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
            }
        };
    };

    public String[] analyticJson(String result){
        try {
            String[] ss=new String[5];//为了演示方便让其返回String[]
            JSONObject jsonObject = new JSONObject(result).getJSONObject("comments");
            JSONArray jsonArray=jsonObject.getJSONArray("info");
            for(int i=0;i<jsonArray.length();i++){
                JSONObject jo = (JSONObject)jsonArray.opt(i);
                ss[i]=jo.getInt("id")+"  "+jo.getString("name")+"  "+jo.getString("地址");
            }
            return ss;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 从指定的URL中获取数组
     * @param urlPath
     * @return
     * @throws Exception
     */
    public static String readParse(String urlPath) throws Exception {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] data = new byte[1024];
        int len = 0;
        URL url = new URL(urlPath);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        InputStream inStream = conn.getInputStream();
        while ((len = inStream.read(data)) != -1) {
            outStream.write(data, 0, len);
        }
        inStream.close();
        Log.i("1", new String(outStream.toByteArray()));
        return new String(outStream.toByteArray());//通过out.Stream.toByteArray获取到写的数据
    }

}
