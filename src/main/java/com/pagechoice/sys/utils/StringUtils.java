// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   StringUtils.java

package com.pagechoice.sys.utils;

import java.util.Date;
import java.util.Random;

import org.apache.http.impl.cookie.DateUtils;


	
// Referenced classes of package org.simpro.core.util:
//			ClassUtils, DateUtils, Base64Utils, LogUtils

public abstract class StringUtils{

	private static long generateCount = 0L;
	
	public static synchronized String getUniqueString(int n){
		if (generateCount > 0xf423fL)
			generateCount = 0L;
		String strDateTime = DateUtils.formatDate(new Date(), "yyyyMMddHHmmss");
		String uniqueNumber = (new StringBuilder()).append(strDateTime).append(Long.toString(generateCount)).toString();
		int intLength = uniqueNumber.length();
		if (intLength < n)
		{
			Random random = new Random(System.currentTimeMillis());
			uniqueNumber = (new StringBuilder()).append(uniqueNumber).append(Math.abs(random.nextLong())).toString();
		}
		generateCount++;
		return uniqueNumber.substring(0, n);
	}


	public static void main(String[] args){
		
		System.out.println(StringUtils.getUniqueString(18));
		System.out.println(StringUtils.getUniqueString(18));
		System.out.println(StringUtils.getUniqueString(18));
		System.out.println(StringUtils.getUniqueString(18));
		System.out.println(StringUtils.getUniqueString(18));
		
		
	}
	
	
}
