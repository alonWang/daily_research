package com.github.alonwang.clu.manager;

import com.github.promeg.pinyinhelper.Pinyin;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import cn.hutool.core.collection.ConcurrentHashSet;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;

/**
 * @description:
 * @author: alonwang
 * @create: 2019-11-16 10:12
 **/
public class IdiomManager {

	private static volatile String current;

	private static Set<String> usedIdioms = new ConcurrentSkipListSet<>();
	private static Map<String, Set<String>> headPinyins;
	private static Set<String> idioms;

	public static void init() {
		// 解析文件,获取所有四字成语
		byte[] raws = FileUtil.readBytes("idiom.txt");
		idioms = Arrays.stream(new String(raws).split("\r\n")).parallel()
				.filter(w -> w.length() == 4)
				.collect(Collectors.toCollection(ConcurrentHashSet::new));
		headPinyins = idioms.parallelStream()
				.collect(Collectors.groupingBy(
						(String s) -> Pinyin.toPinyin(s.charAt(0)),
						Collectors.toCollection(ConcurrentHashSet::new)));
		current = next(null);
	}

	public static String next(String prev) {
		if (prev == null) {
			current = idioms.stream()
					.skip(ThreadLocalRandom.current().nextInt(20)).findAny()
					.orElse("没成语了");
			idioms.remove(current);
			usedIdioms.add(current);
		} else {
			usedIdioms.add(current);
			usedIdioms.add(prev);
			idioms.remove(current);
			idioms.remove(prev);
			String newIdiom = findNextAndRemove(prev);
			if (StrUtil.isEmpty(newIdiom)) {
				return next(null);
			}
			current = newIdiom;
		}
		return current;

	}

	// TODO 增加随机性
	private static String findNextAndRemove(String idiom) {
		String next = null;
		String prevLastPinyin = Pinyin.toPinyin(idiom.charAt(3));
		Set<String> corrIdioms = headPinyins.get(prevLastPinyin);
		Iterator<String> iter = corrIdioms.iterator();
		if (iter.hasNext()) {
			next = iter.next();
			iter.remove();
		}
		return next;

	}

	private static String getHeadPinyin(String word) {
		return Pinyin.toPinyin(word.charAt(0));
	}

	private static String getTailPinyin(String word) {
		return Pinyin.toPinyin(word.charAt(3));
	}

	public static String current() {
		return current;
	}

	// 区分 已使用,正确,错误
	public static boolean correct(String word) {
		if (!unusedIdiom(word)) {
			return false;
		}
		boolean correct = getTailPinyin(current).equals(getHeadPinyin(word));
		if (correct) {
			next(word);
		}
		return correct;
	}

	private static boolean unusedIdiom(String word) {
		return idioms.contains(word);
	}

}
