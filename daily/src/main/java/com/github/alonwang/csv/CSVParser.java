package com.github.alonwang.csv;

import cn.hutool.core.io.FileUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author alonwang
 * @date 2021/2/24 2:42 下午
 */
public class CSVParser {
    public static void main(String[] args) {
        List<PlayerData> datas = new ArrayList<>();
        EasyExcel.read("", PlayerData.class, new DataReader(datas)).sheet().doRead();
        EasyExcel.read("", PlayerData.class, new DataReader(datas)).sheet().doRead();
        EasyExcel.read("", PlayerData.class, new DataReader(datas)).sheet().doRead();
        System.out.println(datas);
        JSONArray allCity = JSON.parseArray(FileUtil.readString("members.json", "UTF-8"));
        Set<Long> memeberIds = new HashSet<>();
        allCity.stream().forEach(city -> {
            String membersStr = ((JSONObject) city).getString("membersData");
            if (membersStr.isEmpty()) {
                return;
            }
            JSONObject members = JSON.parseObject(membersStr);
            System.out.println(members);
            members.keySet().stream().forEach(id -> {
                memeberIds.add(Long.parseLong(id));
            });

        });
        datas = datas.stream().filter(data -> memeberIds.contains(data.getPlayerId())).distinct().collect(Collectors.toList());
        EasyExcel.write("", PlayerData.class).sheet("Sheet").doWrite(datas);
    }
}
