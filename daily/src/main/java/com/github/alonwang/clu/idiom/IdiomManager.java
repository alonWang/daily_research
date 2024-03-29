package com.github.alonwang.clu.idiom;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.collection.ConcurrentHashSet;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import net.sourceforge.pinyin4j.PinyinHelper;

/**
 * @description:
 * @author: alonwang
 * @create: 2019-11-16 10:12
 **/
public class IdiomManager {
    private static volatile boolean idle;
    private static volatile String current;
    private static Multimap<String, String> firstPYs = ArrayListMultimap.create();
    private static Multimap<String, String> lastPYs = ArrayListMultimap.create();
    private static Set<String> usedIdioms = new ConcurrentHashSet<>();
    private static Set<String> idioms = new HashSet<>();

    public static void init() {
        //解析文件,获取所有四字成语
        byte[] raws = FileUtil.readBytes("idiom.json");
        JSONArray jsonArray = JSON.parseArray(new String(raws));
        jsonArray.parallelStream().map(o -> ((JSONObject) o).getString("word")).filter(s -> !StrUtil.isEmpty(s) && s.length() == 4).forEach(word -> {
            idioms.add(word);
            List<String> lastPinyins = getLastPinyins(word);
            if (!CollectionUtil.isEmpty(lastPinyins)) {
                lastPinyins.forEach(pinyin -> lastPYs.put(pinyin, word));
            }
            List<String> firstPinyins = getFirstPinyins(word);
            if (!CollectionUtil.isEmpty(firstPinyins)) {
                firstPinyins.forEach(pinyin -> firstPYs.put(pinyin, word));
            }
        });

        current = next(null);
        idle = true;
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

    public static String next(String prev) {
        if (prev == null) {
            Iterator<String> iter = firstPYs.values().iterator();
            IntStream.range(0, RandomUtil.randomInt(20)).forEach(i -> iter.next());
            current = iter.next();
            usedIdioms.add(current);
        } else {
            usedIdioms.add(current);
            usedIdioms.add(prev);
            List<String> pinyins = getLastPinyins(prev);
            current = pinyins.stream().map(pinyin ->
                    firstPYs.get(pinyin)).filter(c -> !CollectionUtil.isEmpty(c)).flatMap(Collection::stream).filter(word -> !usedIdioms.contains(word)).findAny().get();

        }
        return current;

    }


    public static String current() {
        return current;
    }

    public static boolean correct(String word) {
        idle = false;
        if (!isIdiom(word)) {
            return false;
        }
        List<String> pinyins = getFirstPinyins(word);
        if (pinyins == null) {
            return false;
        }
        List<String> currentEndPinyins = getLastPinyins(current);
        boolean correct = pinyins.stream().anyMatch(currentEndPinyins::contains);
        if (correct) {
            next(word);
        }
        return correct;
    }

    public static boolean isIdiom(String word) {
        return idioms.contains(word);
    }

    public static boolean idle() {
        return idle;
    }

    public static void resetIdle() {
        idle = true;
    }
}
