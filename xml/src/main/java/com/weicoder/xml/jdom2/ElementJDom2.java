package com.weicoder.xml.jdom2;

import java.util.Collections;
import java.util.List;

import com.weicoder.common.lang.Lists;
import com.weicoder.common.util.EmptyUtil;
import com.weicoder.xml.Attribute;
import com.weicoder.xml.Document;
import com.weicoder.xml.Element;

/**
 * Element接口 JDom实现
 * @author WD
 * @version 1.0
 */
public final class ElementJDom2 implements Element {
	// Document对象
	private Document			doc;
	// Dom4J节点对象
	private org.jdom2.Element	element;

	/**
	 * 构造方法
	 * @param name 根节点名
	 */
	public ElementJDom2(String name) {
		element = new org.jdom2.Element(name);
	}

	/**
	 * 构造方法
	 * @param e 节点
	 */
	public ElementJDom2(org.jdom2.Element e) {
		element = e;
	}

	/**
	 * 获得本元素的Document
	 * @return Document
	 */
	public Document getDocument() {
		return doc;
	}

	/**
	 * 设置本元素的Document
	 * @param doc Document对象
	 */
	public Element setDocument(Document doc) {
		this.doc = doc;
		return this;
	}

	/**
	 * 添加Element元素
	 * @param e Element元素对象
	 * @return this
	 */
	public Element add(Element e) {
		element.addContent(((ElementJDom2) e).getElement());
		return this;
	}

	/**
	 * 添加Element元素
	 * @param name 元素名
	 * @param text 元素内容
	 * @return this
	 */
	public Element addElement(String name, String text) {
		element.addContent(name).setText(text);
		return this;
	}

	/**
	 * 添加Element元素 空元素
	 * @param name 元素名
	 * @return this
	 */
	public Element addElement(String name) {
		element.addContent(name);
		return this;
	}

	/**
	 * 给元素添加属性
	 * @param a 属性
	 * @return this
	 */
	public Element add(Attribute a) {
		element.setAttribute(((AttributeJDom2) a).getAttribute());
		return this;
	}

	/**
	 * 给元素添加属性
	 * @param name 属性 name
	 * @param value 属性 value
	 * @return this
	 */
	public Element addAttribute(String name, String value) {
		element.setAttribute(name, value);
		return this;
	}

	/**
	 * 设置本元素名
	 * @param name 元素名
	 * @return this
	 */
	public Element setName(String name) {
		element.setName(name);
		return this;
	}

	/**
	 * 获得本元素下的node的全部元素集合
	 * @param node 元素名
	 * @return 元素集合
	 */
	public List<Element> getElements(String node) {
		return getElementList(element.getChildren(node));
	}

	/**
	 * 获得属性列表
	 * @return 属性列表
	 */
	public List<Attribute> getAttributes() {
		// 获得所以属性
		List<?> list = element.getAttributes();
		// 列表不为空
		if (EmptyUtil.isEmpty(list)) {
			// 返回空列表
			return Lists.emptyList();
		}
		// 获得列表长度
		int size = list.size();
		// 声明属性列表
		List<Attribute> attributes = Lists.newList(size);
		// 循环属性
		for (int i = 0; i < size; i++) {
			// 添加到Element接口集合
			attributes.add(new AttributeJDom2((org.jdom2.Attribute) list.get(i)));
		}
		// 返回集合
		return attributes;
	}

	/**
	 * 获得本元素下的node的全部元素集合
	 * @return 元素集合
	 */
	public List<Element> getElements() {
		return getElementList(element.getChildren());
	}

	/**
	 * 获得本元素下的node子元素
	 * @param node 元素名
	 * @return 查找的元素
	 */
	public Element getElement(String node) {
		return new ElementJDom2(element.getChild(node));
	}

	/**
	 * 获得元素名
	 * @return name
	 */
	public String getName() {
		return element.getName();
	}

	/**
	 * 获得文本内容
	 * @return text
	 */
	public String getText() {
		return element.getTextTrim();
	}

	/**
	 * 判断是否空对象
	 * @return true 空对象 false 非空对象
	 */
	public boolean isEmpty() {
		return EmptyUtil.isEmpty(element);
	}

	/**
	 * 获得属性值
	 * @param name 属性name
	 * @return 属性值
	 */
	public String getAttributeValue(String name) {
		return element.getAttributeValue(name);
	}

	/**
	 * 获得属性值
	 * @param name 属性name
	 * @return 属性
	 */
	public Attribute getAttribute(String name) {
		return new AttributeJDom2(element.getAttribute(name));
	}

	/**
	 * 设置文本节点内容
	 */
	public Element setText(String text) {
		// 设置文本节点内容
		element.setText(text);
		// 返回自己
		return this;
	}

	/**
	 * 获得JDom Element
	 * @return org.jdom2.Element
	 */
	public org.jdom2.Element getElement() {
		return element;
	}

	/**
	 * 设置JDom Element
	 * @param e org.jdom2.Element
	 */
	public void setElement(org.jdom2.Element e) {
		element = e;
	}

	/**
	 * 返回对象字符串
	 */
	public String toString() {
		return element.toString();
	}

	/**
	 * 把JDom节点集合 变成Element接口集合
	 * @param list
	 * @return
	 */
	private List<Element> getElementList(List<?> list) {
		// 判断列表不为空
		if (EmptyUtil.isEmpty(list)) {
			Collections.emptyList();
		}
		// 获得列表大小
		int size = list.size();
		// 声明节点集合
		List<Element> lsElement = Lists.newList(size);
		// 循环JDom节点集合
		for (int i = 0; i < size; i++) {
			// 添加到Element接口集合
			lsElement.add(new ElementJDom2((org.jdom2.Element) list.get(i)));
		}
		// 返回集合
		return lsElement;
	}
}
