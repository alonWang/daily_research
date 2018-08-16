package com.github.alonwang.leetcode;

/**
 * @author weilong.wang Created on 2018/8/16
 */

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Definition for an interval.
 */
class Interval {
	int start;
	int end;

	Interval() {
		start = 0;
		end = 0;
	}

	Interval(int s, int e) {
		start = s;
		end = e;
	}
}

public class Q56 {
	public List<Interval> merge(List<Interval> intervals) {
		if (intervals.size() == 0) {
			return new ArrayList();
		}

		List<Interval> result = new ArrayList();

		// make sure the start is ordered
		intervals.sort(Comparator.comparingInt(val -> val.start));
		// if the first two element can merge,merge it and replace the first two
		// element with merged element.nor remove the first and add to result
		while (intervals.size() > 1) {
			Interval firstVal = intervals.get(0);
			Interval secondVal = intervals.get(1);
			if (firstVal.end >= secondVal.start) {
				intervals = intervals.subList(2, intervals.size());
				intervals.add(0, new Interval(firstVal.start,
						Math.max(firstVal.end, secondVal.end)));
			} else {
				intervals.remove(0);
				result.add(firstVal);
			}
		}
		result.add(intervals.get(0));
		return result;
	}
}