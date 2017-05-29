package com.hstech.monkey.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang3.math.NumberUtils;

public class StringUtils extends org.apache.commons.lang3.StringUtils {
	public static final String EMPTY = "";

	private StringUtils() {
	}

	public static boolean isEmpty(String text) {
		return org.apache.commons.lang3.StringUtils.isEmpty(text);
	}

	public static boolean isNotEmpty(String text) {
		return org.apache.commons.lang3.StringUtils.isNotEmpty(text);
	}

	public static boolean isBlank(String text) {
		return org.apache.commons.lang3.StringUtils.isBlank(text);
	}

	public static boolean isNotBlank(String text) {
		return org.apache.commons.lang3.StringUtils.isNotBlank(text);
	}

	public static boolean isAllNotBlank(String... texts) {
		for (String text : texts) {
			if (isBlank(text)) {
				return false;
			}
		}
		return true;
	}

	public static boolean isAllBlank(String... texts) {
		for (String text : texts) {
			if (isNotBlank(text)) {
				return false;
			}
		}
		return true;
	}

	public static boolean isAllNumber(String... texts) {
		for (String text : texts) {
			if (!NumberUtils.isNumber(text)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 去掉areaCode中的成对00,须保证类似500100的地区替换为5001,又要保证000000被替换为空
	 */
	public static String reduceAreaCode(String areaCode) {
		return areaCode.replaceAll("00$", "").replaceAll("00$", "").replaceAll("00$", "");
	}

	/*
	 * 目标整数
	 * @param destinationInt 目标加密整数
	 * @param strLength加密后字符串要求长度
	 */
	public static String intStrConcat(Long destinationInt, int strLength) {
		String base = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz"; // 字符集
		String destinationStr = destinationInt + "";
		int destinationStrLength = destinationStr.length();
		if (destinationStrLength > strLength) {
			return destinationInt.toString();
		}
		// 从字符集中随机抽取strLength个字符组成字符串
		StringBuffer sb = new StringBuffer();
		Random random = new Random();
		for (int i = 0; i < strLength; i++) {
			int number = random.nextInt(base.length() - 1);
			sb.append(base.charAt(number));
		}
		// 拼接int和str,随机抽取字符串中的指定位置并且替换成int
		HashSet<Integer> set = new HashSet<Integer>();
		int midInt = 0;
		while (true) {
			midInt = random.nextInt(strLength); // 随机生成strLength之内的int作为意向替换index
			set.add(midInt);
			if (set.size() == destinationStrLength) {
				break;
			}
		}
		//index排序
		List<Integer> list = new ArrayList<Integer>();
		for (Integer value : set) {
			list.add(value);
		}
		Collections.sort(list);
		// 字符替换
		midInt = 0;
		for (Integer indexInt : list) {
			sb.replace(indexInt, indexInt + 1, destinationStr.charAt(midInt) + "");
			midInt++;
		}
		return sb.toString();
	}

	//提取字符串中的数字
	public static String decodeToken(String str) {
		StringBuffer str2 = new StringBuffer();
		if (str != null && !"".equals(str)) {
			for (int i = 0; i < str.length(); i++) {
				if (str.charAt(i) >= 48 && str.charAt(i) <= 57) {
					str2.append(str.charAt(i));
				}
			}
		}
		return str2.toString();
	}
}
