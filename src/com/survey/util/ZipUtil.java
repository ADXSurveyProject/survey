package com.survey.util;

import java.io.ByteArrayOutputStream;
import java.util.Iterator;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipUtil {

	public static Object prepareZipFile(Map<String, byte[]> files) throws Exception {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ZipOutputStream zos = new ZipOutputStream(bos);
		
		Iterator<String> iter = files.keySet().iterator();
		
		while (iter.hasNext()) {
			String fileName = iter.next();
			ZipEntry ze = new ZipEntry(fileName);
			zos.putNextEntry(ze);
			
			byte[] data = files.get(fileName);
			zos.write(data);
			zos.closeEntry();

			zos.flush();
			bos.flush();

		}
		zos.close();
		byte data[] = bos.toByteArray();
		return data;
	}

}
