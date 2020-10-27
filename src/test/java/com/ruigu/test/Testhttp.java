package com.ruigu.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sun.deploy.Environment;
import okhttp3.*;
import org.junit.Test;
import org.springframework.data.redis.cache.RedisCacheConfiguration;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author hugangquan
 * @date 2020/10/19 21:52
 */
public class Testhttp {

    @Test
    public void getSkuCodeList() throws IOException {
        int page = 1;
        int pagesize = 100;

        String url = "http://api.ruigushop.com/v0.2/plist";

        List<String> skuList = new ArrayList<>();

        while(page<=30){
            getPlist(url,skuList,page++,pagesize,"spid");
        }

        System.out.println(skuList.size());

        File file = new File("D:\\work\\doc\\201111优化整备(接口测试)\\project\\csv\\prod_plist_spid.txt");
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
        skuList.forEach(sku->{
            try {
                writer.write(sku);
                writer.write("\r\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        writer.close();

    }


    public void getPlist(String url,List<String > skulist,int page,int pagesize,String key) throws IOException {
        OkHttpClient client = new OkHttpClient();
        //去构建
        FormBody.Builder builder = new FormBody.Builder();
        FormBody formBody = builder
                .add("user_id","4860")
                .add("token","3175f543a855f78121ce1e81a3706bb4")
                .add("ordertype","4")
                .add("entityType","1")
                .add("order","1")
                .add("page",page+"")
                .add("pagesize",""+pagesize)
                .add("search_name"," ")
                .add("rg_ver","2.69")
                .add("rg_id","android")
                .add("province_code","")
                .add("region_code","")
                .add("city_code","")
                .build();
        final Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();
        Call call = client.newCall(request);
        Response execute = call.execute();
        String string = execute.body().string();
        Map map = JSON.parseObject(string, Map.class);
        if(map.get("code")!= null &&map.get("code").toString().equals("200")){
            JSONArray array = (JSONArray) map.get("data");
            Iterator<Object> iterator = array.stream().iterator();

            while(iterator.hasNext()){
                JSONObject next = (JSONObject) iterator.next();
                String sku = next.getString(key);
                skulist.add(sku);
            }
        }

    }

}
