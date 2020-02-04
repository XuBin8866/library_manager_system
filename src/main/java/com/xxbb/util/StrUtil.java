package com.xxbb.util;
/**  
    * @Title: StrUtil.java
    * @Package com.xxbb.util
    * @Description: TODO(用一句话描述该文件做什么)
    * @author 徐斌
    * @date 2020年1月27日
    * @version V1.0  
    */

public class StrUtil {
	//处理字符串中得空值
	public static final String nullToString(String v,String toV) {
		if(v==null||"".equals(v)) {
			v=toV;
		}
		return v;
	}
	//过滤危险字符
	public static final String filterStr(String str) {
		str=str.replaceAll(";","");
		str=str.replaceAll("&","&amp;");
		str=str.replaceAll("<","&lt;");
		str=str.replaceAll(">","&gt;");
		str=str.replaceAll("'","");
		str=str.replaceAll("--"," ");
		str=str.replaceAll("/","");
		str=str.replaceAll("%","");
		return str;
	}
	
}
