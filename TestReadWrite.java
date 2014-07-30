package com.liang.lucene.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import org.junit.Test;

/**
 * 
 * 测试删除一条txt的记录
 * @author Administrator
 *
 */
public class TestReadWrite {

	
	@Test
	public void TestReadWrite() throws IOException{
		//打开文件
		File oldFile = new File("G:\\logs\\sshdemo.log");
		
		//新建文件
		File newFile = new File("G:\\logs\\newSshdemo.log");
		
		// read file content from file
        StringBuffer sb= new StringBuffer("");
		if(oldFile.isFile() && oldFile.exists()){
			//读入文件流 
			InputStreamReader reader = new InputStreamReader(
					new FileInputStream(oldFile));
			
			//写入文件流
			OutputStreamWriter writer = new OutputStreamWriter(
					new FileOutputStream(newFile));
			
			//创建读缓存
			BufferedReader bufferR = new BufferedReader(reader);
			//创建写缓存
			BufferedWriter bufferW = new BufferedWriter(writer);
			
			
			String lineTxt = null;
			int i=1;
			//循环判断缓存的每一行
			while( (lineTxt = bufferR.readLine()) != null){
				
				//删除其中包含eclipse文件
				if(lineTxt.contains("java")){
					System.out.println("准备删除,第["+i+"]行:"+lineTxt);
					//准备删除
				}else{
					sb.append(lineTxt+"\n");
					//写入新文件
					//bufferW.write(lineTxt+"\n");
				}
				i++;
			}
			//写入新文件
			bufferW.write(sb.toString());
			//循环结束，关闭文件读入流,文件写入流
			reader.close();
			writer.close();
		}
	}
}
