package com.cos.photogramstart.utill;

public class Script {
	
	
	//경고 팝업 후 이전 페이지 이동 
	public static String back(String msg) {
		StringBuffer sb = new StringBuffer();
		
		sb.append("<script>");
			sb.append("alert('"+msg+"');");
			sb.append("history.back();");
		sb.append("</script>");
		
		return sb.toString();
	}
	

}
