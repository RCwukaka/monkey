package com.hstech.master.web.wx;

import java.io.IOException;
import java.util.Calendar;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hstech.monkey.pojo.WXInputMessage;
import com.hstech.monkey.pojo.WXOutputMessage;
import com.hstech.monkey.utils.SerializeXMLUtils;
import com.hstech.monkey.utils.WXCheckUtil;
import com.thoughtworks.xstream.XStream;

@Controller
@RequestMapping("/wx")
public class WXController {

	private Logger log = Logger.getLogger(this.getClass());

	@Value("#{configProperties['wx.token']}")
	private String token;

	/**
	 * 微信接入接口
	 * 
	 * @param req
	 * @param res
	 * @throws IOException 
	 */
	@RequestMapping(value = "chat", method = { RequestMethod.GET, RequestMethod.POST })
	public void chat(HttpServletRequest req, HttpServletResponse res) throws IOException {
		boolean isGet = req.getMethod().toLowerCase().equals("get");
		if (isGet) {
			String signature = req.getParameter("signature");
			String timestamp = req.getParameter("timestamp");
			String nonce = req.getParameter("nonce");
			String echostr = req.getParameter("echostr");
			boolean isSign = WXCheckUtil.checkSignature(token, signature, timestamp, nonce);
			if (isSign) {
				try {
					res.getWriter().write(echostr);
				} catch (IOException e) {
					log.error("微信接口接入失败" + e);
				}
			} else {
				log.error("微信接口接入失败");
			}
		} else {
			acceptMessage(req, res);
		}
	}

	// 正式处理微信请求内容
	private void acceptMessage(HttpServletRequest req, HttpServletResponse res) throws IOException {
		ServletInputStream in = req.getInputStream();
		// 将POST流转换为XStream对象
		XStream xs = SerializeXMLUtils.createXstream();
		xs.processAnnotations(WXInputMessage.class);
		xs.processAnnotations(WXOutputMessage.class);
		xs.alias("xml", WXInputMessage.class);
		StringBuilder xmlMsg = new StringBuilder();
		byte[] b = new byte[4096];
		for (int n; (n = in.read(b)) != -1;) {
			xmlMsg.append(new String(b, 0, n, "UTF-8"));
		}
		WXInputMessage inputMessage = (WXInputMessage) xs.fromXML(xmlMsg.toString());
		returnMsgWX(inputMessage, res);
	}

	// 返回微信服务端数据
	private void returnMsgWX(WXInputMessage inputMessage, HttpServletResponse res) throws IOException {
		String servername = inputMessage.getToUserName();// 服务端  
        String custermname = inputMessage.getFromUserName();// 客户端  
        Long returnTime = Calendar.getInstance().getTimeInMillis() / 1000;// 返回时间
		StringBuffer str = new StringBuffer();
		str.append("<xml>");
		str.append("<ToUserName><![CDATA[" + custermname + "]]></ToUserName>");
		str.append("<FromUserName><![CDATA[" + servername + "]]></FromUserName>");
		str.append("<CreateTime>" + returnTime + "</CreateTime>");
		str.append("<MsgType><![CDATA[" + inputMessage.getMsgType() + "]]></MsgType>");
		str.append("<Content><![CDATA[你说的是：" + inputMessage.getContent() + "，吗？]]></Content>");
		str.append("</xml>");
		res.getWriter().write(str.toString());
	}

}
