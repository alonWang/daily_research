package com.github.alonwang.clu.idiom;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import cn.hutool.core.collection.ConcurrentHashSet;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import net.sourceforge.pinyin4j.PinyinHelper;

/**
 * @description:
 * @author: alonwang
 * @create: 2019-11-16 10:12
 **/
public class IdiomManager {
    private static volatile String current;
    private static Set<String> idioms;
    private static Iterator<String> iterator;

    public static void init() {
        //解析文件,获取所有四字成语
        byte[] raws = FileUtil.readBytes("idiom.json");
        JSONArray jsonArray = JSON.parseArray(new String(raws));
        idioms = jsonArray.parallelStream().map(o -> ((JSONObject) o).getString("word")).filter(s -> !StrUtil.isEmpty(s) && s.length() == 4).collect(Collectors.toCollection(ConcurrentHashSet::new));
        iterator = idioms.iterator();
        current = iterator.next();
    }

    public static String next() {
        iterator.remove();
        current = iterator.next();
        return current;
    }

    public static String current() {
        return current;
    }

    public static boolean correct(String word) {
        String[] pinyins = PinyinHelper.toHanyuPinyinStringArray(word.charAt(0));
        if (pinyins == null) {
            return false;
        }
        List<String> currentEndPinyins = Arrays.stream(PinyinHelper.toHanyuPinyinStringArray(current.charAt(3))).map(s -> s.substring(0, s.length() - 1)).collect(Collectors.toList());
        return Arrays.stream(pinyins).map(s -> s.substring(0, s.length() - 1)).collect(Collectors.toList()).stream().anyMatch(currentEndPinyins::contains);
    }

}
