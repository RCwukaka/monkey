package com.hstech.monkey.utils;

import java.util.Arrays;

public class WXCheckUtil {

	public static boolean checkSignature(String token, String signature, String timestamp, String nonce) {

		String[] arr = new String[] { token, timestamp, nonce };

		// 排序
		Arrays.sort(arr);
		// 生成字符串
		StringBuilder content = new StringBuilder();
		for (int i = 0; i < arr.length; i++) {
			content.append(arr[i]);
		}

		// sha1加密
		String temp = getSHA1String(content.toString());

		return temp.equals(signature); // 与微信传递过来的签名进行比较
	}

	private static String getSHA1String(String data) {
		return DigestUtils.encode(data); // 使用commons codec生成sha1字符串
	}
}
