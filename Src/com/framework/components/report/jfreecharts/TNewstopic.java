package com.framework.components.report.jfreecharts;

import java.util.ArrayList;
import java.util.List;

/**
 * AbstractTNewstopic entity provides the base persistence definition of the
 * TNewstopic entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class TNewstopic  {

	// Fields

	private Integer id;
	private String topic;
	private String ntSfbdate;
	private String ntSzqdate;
	private Integer ntNpointnum;
	private Integer ntNreviewnum;
	private Double ntNzzqz;
	private String ntSfbuser;
	private String ntSzzgz;
	private Integer ntNsiteseq;
	private Integer isCustTopic;
	private String ntSfbsitename;
	private List TReviewinfos = new ArrayList();
	private List TNewscontents = new ArrayList();
	private List TNewsTypeses = new ArrayList();

	// Constructors

	/** default constructor */
	public TNewstopic() {
	}


	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTopic() {
		return topic;
	}


	public void setTopic(String topic) {
		this.topic = topic;
	}


	public String getNtSfbdate() {
		return this.ntSfbdate;
	}

	public void setNtSfbdate(String ntSfbdate) {
		this.ntSfbdate = ntSfbdate;
	}

	public String getNtSzqdate() {
		return this.ntSzqdate;
	}

	public void setNtSzqdate(String ntSzqdate) {
		this.ntSzqdate = ntSzqdate;
	}

	public Integer getNtNpointnum() {
		return this.ntNpointnum;
	}
	public Integer getPointNum() {
		return this.ntNpointnum;
	}

	public void setNtNpointnum(Integer ntNpointnum) {
		this.ntNpointnum = ntNpointnum;
	}

	public Integer getNtNreviewnum() {
		return this.ntNreviewnum;
	}
	
	public Integer getReviewNum() {
		return this.ntNreviewnum;
	}

	public void setNtNreviewnum(Integer ntNreviewnum) {
		this.ntNreviewnum = ntNreviewnum;
	}

	public Double getNtNzzqz() {
		return this.ntNzzqz;
	}

	public void setNtNzzqz(Double ntNzzqz) {
		this.ntNzzqz = ntNzzqz;
	}

	public String getNtSfbuser() {
		return this.ntSfbuser;
	}

	public void setNtSfbuser(String ntSfbuser) {
		this.ntSfbuser = ntSfbuser;
	}

	public String getNtSzzgz() {
		return this.ntSzzgz;
	}

	public void setNtSzzgz(String ntSzzgz) {
		this.ntSzzgz = ntSzzgz;
	}

	public Integer getNtNsiteseq() {
		return this.ntNsiteseq;
	}

	public void setNtNsiteseq(Integer ntNsiteseq) {
		this.ntNsiteseq = ntNsiteseq;
	}

	public List getTReviewinfos() {
		return this.TReviewinfos;
	}

	public void setTReviewinfos(List TReviewinfos) {
		this.TReviewinfos = TReviewinfos;
	}

	public List getTNewscontents() {
		return this.TNewscontents;
	}

	public void setTNewscontents(List TNewscontents) {
		this.TNewscontents = TNewscontents;
	}

	public List getTNewsTypeses() {
		return this.TNewsTypeses;
	}

	public void setTNewsTypeses(List TNewsTypeses) {
		this.TNewsTypeses = TNewsTypeses;
	}

	public Integer getIsCustTopic() {
		return this.isCustTopic;
	}

	public void setIsCustTopic(Integer isCustTopic) {
		this.isCustTopic = isCustTopic;
	}

	public String getNtSfbsitename() {
		return this.ntSfbsitename;
	}

	public void setNtSfbsitename(String ntSfbsitename) {
		this.ntSfbsitename = ntSfbsitename;
	}
}