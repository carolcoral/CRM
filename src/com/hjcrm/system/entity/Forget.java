package com.hjcrm.system.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import com.hjcrm.publics.util.BaseEntity;

/**
 * 忘记密码信息
 * 
 * @author likang
 * @date 2016-10-19 上午10:15:06
 */
public class Forget extends BaseEntity implements Serializable{
	private static final long serialVersionUID = 1L;


	private Long forgetid;// 主键ID
	private Long userid;// 用户id
	private String email;// 邮箱
	private Timestamp create_time;// 创建时间
	private Timestamp expiration_time;// 过期时间
	private String randomcode;// 随机MD5值
	private Integer linknumber;// 链接点击次数 最大值10次
	private Integer dr;// 删除标志 0未删除 1已删除
	
	
	

	public Long getForgetid() {
		return forgetid;
	}

	public void setForgetid(Long forgetid) {
		this.forgetid = forgetid;
	}

	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Timestamp getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Timestamp create_time) {
		this.create_time = create_time;
	}

	public Timestamp getExpiration_time() {
		return expiration_time;
	}

	public void setExpiration_time(Timestamp expiration_time) {
		this.expiration_time = expiration_time;
	}

	public String getRandomcode() {
		return randomcode;
	}

	public void setRandomcode(String randomcode) {
		this.randomcode = randomcode;
	}

	public Integer getLinknumber() {
		return linknumber;
	}

	public void setLinknumber(Integer linknumber) {
		this.linknumber = linknumber;
	}

	public Integer getDr() {
		return dr;
	}

	public void setDr(Integer dr) {
		this.dr = dr;
	}

	@Override
	public String getIdColumnName() {
		// TODO Auto-generated method stub
		return "forgetid";
	}

}
