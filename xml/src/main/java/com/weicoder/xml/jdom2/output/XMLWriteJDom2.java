package com.weicoder.xml.jdom2.output;

import java.io.IOException;
import java.io.OutputStream;

import org.jdom2.output.XMLOutputter;

import com.weicoder.common.log.Logs;
import com.weicoder.common.util.CloseUtil;
import com.weicoder.xml.Document;
import com.weicoder.xml.jdom2.DocumentJDom2;
import com.weicoder.xml.output.Format;
import com.weicoder.xml.output.XMLWrite;

/**
 * XMLOutput接口 JDom实现
 * @author WD
 * @version 1.0
 */
public final class XMLWriteJDom2 implements XMLWrite {
	// JDom XMLWriter
	private XMLOutputter	writer;
	// Format对象
	private Format			format;
	// OutputStream对象
	private OutputStream	out;

	/**
	 * 构造方法
	 * @param format 输出格式
	 */
	public XMLWriteJDom2(Format format) {
		// 设置Format实例
		this.format = format;
	}

	/**
	 * 输出XML文档
	 * @param doc Document对象
	 * @param out 输出流
	 */
	public void output(Document doc, OutputStream out) {
		// 获得out
		this.out = out;
		// 实例化 XMLOutputter
		writer = new XMLOutputter(((FormatJDom2) format).getFormat());
		// 写Document
		try {
			writer.output(((DocumentJDom2) doc).getDocument(), out);
		} catch (IOException e) {
			Logs.error(e);
		}
	}

	/**
	 * 输出XML文档
	 * @param doc Document对象
	 * @param os 输出流
	 * @param format 输出格式
	 */
	public void output(Document doc, OutputStream os, Format format) {
		// 设置格式
		this.format = format;
		// 调用自己方法
		output(doc, os);
	}

	/**
	 * 关闭资源
	 */
	public void close() {
		// 关闭流
		CloseUtil.close(out);
		// format置null
		format = null;
		// writer置null
		writer = null;

	}

	/**
	 * 设置输出格式
	 * @param format 格式
	 */
	public void setFormat(Format format) {
		this.format = format;
	}
}
