package com.github.alonwang.clu.idiom;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import cn.hutool.core.collection.CollectionUtil;
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
    private static Multimap<String, String> idioms = ArrayListMultimap.create();
    private static Set<String> usedIdioms = new ConcurrentHashSet<>();

    public static void init() {
        //解析文件,获取所有四字成语
        byte[] raws = FileUtil.readBytes("idiom.json");
        JSONArray jsonArray = JSON.parseArray(new String(raws));
        jsonArray.parallelStream().map(o -> ((JSONObject) o).getString("word")).filter(s -> !StrUtil.isEmpty(s) && s.length() == 4).forEach(word -> {
            List<String> pinyins = getLastPinyins(word);
            if (!CollectionUtil.isEmpty(pinyins)) {
                pinyins.forEach(pinyin -> idioms.put(pinyin, word));
            }
        });

        current = next(null);
    }

    private static List<String> getLastPinyins(String word) {
        String[] pinyins = PinyinHelper.toHanyuPinyinStringArray(word.charAt(3));
        if (pinyins == null) {
            return null;
        }
        return Arrays.stream(pinyins).map(s -> s.substring(0, s.length() - 1)).collect(Collectors.toList());
    }

    private static List<String> getFirstPinyins(String word) {
        String[] pinyins = PinyinHelper.toHanyuPinyinStringArray(word.charAt(0));
        if (pinyins == null) {
            return null;
        }
        return Arrays.stream(pinyins).map(s -> s.substring(0, s.length() - 1)).collect(Collectors.toList());
    }

    private static String next(String prev) {
        if (prev == null) {
            current = idioms.values().iterator().next();
            usedIdioms.add(current);
        } else {
            List<String> pinyins = getLastPinyins(prev);
            current = pinyins.stream().map(pinyin ->
                    idioms.get(pinyin)).filter(c -> !CollectionUtil.isEmpty(c)).flatMap(Collection::stream).filter(word -> !usedIdioms.contains(word)).findAny().get();

        }
        return current;

    }


    public static String current() {
        return current;
    }

    public static boolean correct(String word) {
        List<String> pinyins = getFirstPinyins(word);
        if (pinyins == null) {
            return false;
        }
        List<String> currentEndPinyins = getLastPinyins(current);
        boolean correct = pinyins.stream().anyMatch(currentEndPinyins::contains);
        if (correct) {
            next(current);
        }
        return correct;
    }

}
