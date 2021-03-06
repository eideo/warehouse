package com.fh.entity;

public class ThoughtEntity extends BaseEntity {
	/**
		 * 
		 */
	private static final long serialVersionUID = 2037230424677013849L;

	/**
	 * “ “thought”: { “topic”: string, “key_word”: string }
	 * 
	 * 
	 */
	private int id;

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	private int userid;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getKey_word() {
		return key_word;
	}

	public void setKey_word(String key_word) {
		this.key_word = key_word;
	}

	private String key_word;

	private String topic;

}
