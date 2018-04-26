package com.hjcrm.resource.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DownLoadExcelUtil {

	public static boolean downLoadFile(String filePath,
			HttpServletResponse response, HttpServletRequest request,
			String fileName, String fileType) {
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		try {
			response.setContentType("text/html;charset=utf-8");
			request.setCharacterEncoding("UTF-8");
			String downLoadPath = filePath;
			long fileLength = new File(downLoadPath).length();
			if ("pdf".equals(fileType)) {
				response.setContentType("application/pdf;charset=UTF-8");
			} else if ("xls".equals(fileType)) {
				response.setContentType("application/x-msdownload;");
//				response.setContentType("application/msexcel;charset=UTF-8");
			} else if ("doc".equals(fileType)) {
				response.setContentType("application/msword;charset=UTF-8");
			}
			response.setHeader("Content-disposition", "attachment; filename=" + new String(fileName.getBytes("utf-8"), "ISO8859-1"));
			response.setHeader("Content-Length", String.valueOf(fileLength));
			bis = new BufferedInputStream(new FileInputStream(downLoadPath));
			bos = new BufferedOutputStream(response.getOutputStream());
			byte[] buff = new byte[2048];
			int bytesRead;
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);
			}
		} catch (Exception e) {
		} finally {
			if (bis != null)
				try {
					bis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			if (bos != null)
				try {
					bos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		return true;

	}

}
