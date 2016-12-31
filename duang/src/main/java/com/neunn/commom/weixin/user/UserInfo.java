package com.neunn.commom.weixin.user;

public class UserInfo {
	
	/**
	 * 是否已关注
	 */
	private Integer subscribe;
	
	/**
	 * 用户OpenId
	 */
	private String openid;
	
	/**
	 * 昵称
	 */
	private String nickname;
	
	/**
	 * 性别
	 */
	private Integer sex;
	
	/**
	 * 语言
	 */
	private String language;
	
	/**
	 * 城市
	 */
	private String city;
	
	/**
	 * 省
	 */
	private String province;
	
	/**
	 * 国家
	 */
	private String country;
	
	/**
	 * 头像地址
	 */
	private String headimgurl;
	
	/**
	 * 关注事件
	 */
	private Long subscribe_time;
	
	/**
	 * UnionId
	 */
	private String unionid;
	
	/**
	 * 备注
	 */
	private String remark;
	
	/**
	 * 分组ID
	 */
	private Integer groupid;

	public Integer getSubscribe() {
		return subscribe;
	}

	public void setSubscribe(Integer subscribe) {
		this.subscribe = subscribe;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getHeadimgurl() {
		return headimgurl;
	}

	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}

	public Long getSubscribe_time() {
		return subscribe_time;
	}

	public void setSubscribe_time(Long subscribe_time) {
		this.subscribe_time = subscribe_time;
	}

	public String getUnionid() {
		return unionid;
	}

	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getGroupid() {
		return groupid;
	}

	public void setGroupid(Integer groupid) {
		this.groupid = groupid;
	}
	
	
}
