package com.hstech.monkey.pojo;

import java.io.Serializable;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("xml")
public class WXOutputMessage implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1924339220918128445L;

	@XStreamAlias("ToUserName")  
    @XStreamCDATA  
    private String ToUserName;  
  
    @XStreamAlias("FromUserName")  
    @XStreamCDATA  
    private String FromUserName;  
  
    @XStreamAlias("CreateTime")  
    private Long CreateTime;  
  
    @XStreamAlias("MsgType")  
    @XStreamCDATA  
    private String MsgType = "text";  
  
    private ImageMessage Image;

	public String getToUserName() {
		return ToUserName;
	}

	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}

	public String getFromUserName() {
		return FromUserName;
	}

	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}

	public Long getCreateTime() {
		return CreateTime;
	}

	public void setCreateTime(Long createTime) {
		CreateTime = createTime;
	}

	public String getMsgType() {
		return MsgType;
	}

	public void setMsgType(String msgType) {
		MsgType = msgType;
	}

	public ImageMessage getImage() {
		return Image;
	}

	public void setImage(ImageMessage image) {
		Image = image;
	}
    
}
