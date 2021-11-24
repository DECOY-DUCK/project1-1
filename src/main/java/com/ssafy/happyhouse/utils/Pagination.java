package com.ssafy.happyhouse.utils;

import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class Pagination {
	public void setStartIndex(Map<String, Object> map, Map<String, Object> param) {
		int spp = Integer.parseInt((String)map.get("sizePerPage"));
		int pageNo = Integer.parseInt((String)map.get("pageNo"));
	
		int start = pageNo == 0 ? 0 : spp * pageNo + 1;

		param.put("start", start);
		param.put("sizePerPage", spp);
	}
}
