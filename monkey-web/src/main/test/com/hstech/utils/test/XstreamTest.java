package com.hstech.utils.test;

import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import com.thoughtworks.xstream.io.xml.CompactWriter;
import com.thoughtworks.xstream.io.xml.DomDriver;

@XStreamAlias("XstreamTest")
public class XstreamTest {
	// 标记为节点属性
	@XStreamAsAttribute
	protected String xmlns = "http://s3.amazonaws.com/doc/2006-03-01";

	// 忽略该属性
	@XStreamOmitField
	public String ignoreProperty;

	// 序列化别名
	@XStreamAlias("Name222")
	public String name;

	@XStreamAlias("Type")
	public String type;

	public Map<String, String> map = new HashMap<String, String>();

	@XStreamAlias("Users")
	public List<User> users = new ArrayList<User>();

	public List<Content> contents = new ArrayList<Content>();

	public XstreamTest()  
    {  
        super();  
    }

	public XstreamTest(String name, String type)  
    {  
        super();  
        this.name = name;  
        this.type = type;  
    }

	/**
	 * 使用DEMO
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		XstreamTest tx = new XstreamTest("我带中文,yes", "我也特殊字符哈<f>");
		tx.contents.add(new Content("1", "name1", "value1"));
		tx.contents.add(new Content("2", "name2", "value2"));
		tx.users.add(new User("1", "zhansgdfasdf"));
		tx.users.add(new User("2", "asdfasdfasdf"));
		tx.map.put("1", "234234");
		tx.map.put("2", "234234");

		// 对象序列化
		XStream xstream = new XStream(new DomDriver());
		xstream.autodetectAnnotations(true);
		// 不序列化contents属性，但是序列化下面的子对象
		xstream.addImplicitCollection(XstreamTest.class, "contents");
		// 格式化输出
		System.out.println(xstream.toXML(tx));
		// 无格式输出
		Writer writer = new StringWriter();
		xstream.marshal(tx, new CompactWriter(writer));
		String seri = writer.toString();
		System.out.println(seri);
		// 反序列化
		Object object = xstream.fromXML(seri, new XstreamTest());
		System.out.println(object);
	}
}
