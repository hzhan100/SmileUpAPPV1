package com.smileup.adapters.viewdownloaders;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.util.Log;

class Utils {
	public static void CopyStream(InputStream inStream, File f) {
		try {
			ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
			//ͨ��file������һ��outputStream��������outputStream��д���ݾ�����file��д����
			//�ƺ�outputStream����һ���������ĸ������д���Ķ���ôд���Ǹ��ݾ�����������ģ�����Ӧ���ж��ִ������ķ���
			OutputStream os = new BufferedOutputStream(new FileOutputStream(f));
			byte[] buffer = new byte[1024];
			int len = -1;
			while ((len = inStream.read(buffer)) != -1) {
				outByteStream.write(buffer, 0, len);
			}
			outByteStream.close();
			inStream.close();
			outByteStream.writeTo(os);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void CopyStream(InputStream inputStream,
			OutputStream outputStream) {
		int buffer_size = 1024;
		try {
			byte[] bytes = new byte[buffer_size];

			for (;;) {
				int count = inputStream.read(bytes, 0, buffer_size);
				if (count == -1)
					break;
				outputStream.write(bytes, 0, count);

			}
			inputStream.close();
			outputStream.close();
			//===================================================//
			//������ϵͳ���������
			// while (true) {
			// int i = inputStream.read(arrayOfByte, 0, 1024);
			// if (i == -1) {
			// inputStream.close();
			// outputStream.close();
			// return;
			// }
			// outputStream.write(arrayOfByte, 0, i);
			// }
		} catch (Exception e) {
			e.printStackTrace();
			Log.v("Utils", "CopySream exception");
		}
	}

	public static byte[] read(InputStream instream) throws Exception {
		ByteArrayOutputStream outstream = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = 0;
		while ((len = instream.read(buffer)) != -1) {
			outstream.write(buffer, 0, len);
		}
		instream.close();
		return outstream.toByteArray();
	}
}

/*
 * Location: C:\Users\Hanyu\Desktop\classes_dex2jar.jar Qualified Name:
 * com.smileup.appv1.picloading.Utils JD-Core Version: 0.6.0
 */