package com.ruigu.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.io.Files;
import javafx.util.Pair;
import okhttp3.*;
import org.junit.Test;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author hugangquan
 * @date 2020/10/19 21:52
 */
public class Testhttp3 {

    @Test
    public void getCategroyAndBrandList() throws Exception {
        String path = "C:\\Users\\user\\Desktop\\搜索\\";
        List<String> fistCategroyList = Files.readLines(new File(path + "first_category_list.txt"), Charset.forName("utf8"));

        List<Pair<String,String>> result = new ArrayList<>();

        fistCategroyList.forEach(cate-> {
            try {
                List<String> brandList = getBrandList(cate);
                if (brandList.size() > 0) {
                    brandList.forEach(brand -> {
                        try {
                            getCategroyAndBrandList(result, cate, brand);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    });
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        BufferedWriter bufferedWriter = java.nio.file.Files.newBufferedWriter(Paths.get(path + "/first_categroy_brand.txt"));
        result.forEach(pair -> {
            try {
                bufferedWriter.write(pair.getKey()+","+pair.getValue());
                bufferedWriter.newLine();
                bufferedWriter.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        bufferedWriter.close();

    }

    public List<String> getBrandList(String cate) throws IOException {

        List<String> brandIds = new ArrayList<>();

        String url = "http://api.ruigushop.com/v0.2/channel/get_category_brand";
        OkHttpClient client = new OkHttpClient();
        //去构建
        //user_id=4860&token=58b946f5c16a34c938602ab15069d008&order_type=4&channel_id=2171&rg_ver=2.69&rg_id=android
        FormBody.Builder builder = new FormBody.Builder();
        FormBody formBody = builder
                .add("user_id","4860")
                .add("token","3175f543a855f78121ce1e81a3706bb4")
                .add("order_type","4")
                .add("channel_id",cate)
                .add("search_name"," ")
                .add("rg_ver","2.69")
                .add("rg_id","android")
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
            JSONObject obj = (JSONObject) map.get("data");
            JSONArray array = (JSONArray) obj.get("list");
            Iterator<Object> iterator = array.stream().iterator();

            while(iterator.hasNext()){
                JSONObject next = (JSONObject) iterator.next();
                String brandId = next.getString("id");
                brandIds.add(brandId);
            }
        }

        return brandIds;
    }

    public void getCategroyAndBrandList(List<Pair<String,String>> result,String cate,String brand) throws IOException {

        int page = 1;
        int pagesize = 5;

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
                .add("channel_id",cate)
                .add("brand_params",brand)
                .add("rg_ver","2.69")
                .add("rg_id","android")
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
            if(array.size()>0){
                result.add(new Pair<>(cate,brand));
            }
        }

    }


}
