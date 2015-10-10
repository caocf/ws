package com.cplatform.mall.back.cont.mms.util;

import java.util.Collection;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Sets;
import com.google.common.primitives.Ints;

/**
 * Title. <br>
 * Description.
 * <p/>
 * Copyright: Copyright (c) 12-12-20 下午3:12
 * <p/>
 * Company: 北京宽连十方数字技术有限公司
 * <p/>
 * Author: chengyao
 * <p/>
 * Version: 1.0
 * <p/>
 */
public class ParamUtil {

	public static java.util.Set<Integer> parseFilter(int[] filter, Integer... all) {
		java.util.Set<Integer> allSet = Sets.newHashSet(all);
		if (filter == null || filter.length == 0)
			return allSet;
		java.util.Set<Integer> given = Sets.newHashSet(Ints.asList(filter));
		java.util.Set<Integer> result = Sets.intersection(given, allSet);
		if (result.size() == 0)
			return Sets.newHashSet(-1);
		else
			return result;
	}

	public static java.util.Set<Integer> parseFilter(int[] filter, Collection<Integer> all) {
		java.util.Set<Integer> allSet = Sets.newHashSet(all);
		if (filter == null || filter.length == 0)
			return allSet;
		java.util.Set<Integer> given = Sets.newHashSet(Ints.asList(filter));
		java.util.Set<Integer> result = Sets.intersection(given, allSet);
		if (result.size() == 0)
			return Sets.newHashSet(-1);
		else
			return result;
	}

	public static String filtersToStr(Set<Integer> filters) {
		return StringUtils.join(filters.toArray(new Integer[0]), ",");
	}

}
