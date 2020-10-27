package com.ruigu.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import okhttp3.*;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author hugangquan
 * @date 2020/10/19 21:52
 */
public class Testhttp2 {

    @Test
    public void fetchSpid2() throws IOException {
        int page = 1;
        int pagesize=10;

        List<String> spidList = new ArrayList<>();


        boolean b = fetchSpid(spidList, page++, pagesize);

        while(spidList.size()<50 && b){
            b = fetchSpid(spidList, page++, pagesize);
        }

        System.out.println(spidList.size());

        File file = new File("D:\\work\\doc\\201111优化整备(接口测试)\\project\\csv\\get_parts_by_host_spid.txt");
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
        spidList.forEach(sku->{
            try {
                writer.write(sku);
                writer.write("\r\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        writer.close();

    }

    public boolean fetchSpid(List<String> spidList,int page,int pagesize) throws IOException {

        String url = "http://api.ruigushop.com/v0.2/plist";
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
                String spid = next.getString("spid");
                getSkuCodeList(spid,spidList);
            }
            return true;
        }
        return false;
    }

    public void getSkuCodeList(String product_id,List<String> spidList) throws IOException {

        String url = "http://api.ruigushop.com/v0.2/get_parts_by_host";

        //去构建
        //user_id=4860&token=58b946f5c16a34c938602ab15069d008&ordertype=4&product_id=62888&rg_ver=2.69&rg_id=android
        FormBody.Builder builder = new FormBody.Builder();
        String productId = null;
        FormBody formBody = builder
                .add("user_id","4860")
                .add("token","3175f543a855f78121ce1e81a3706bb4")
                .add("ordertype","4")
                .add("product_id",product_id)
                .add("rg_ver","2.69")
                .add("rg_id","android")
                .build();

        OkHttpClient client = new OkHttpClient();

        final Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();
        Call call = client.newCall(request);
        Response execute = call.execute();
        String string = execute.body().string();
        Map map = JSON.parseObject(string, Map.class);
        if(map.get("code")!= null &&map.get("code").toString().equals("200")){
            spidList.add(product_id);
        }

    }

}
