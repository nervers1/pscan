package com.iruen.pscan.vo;

import org.codehaus.jackson.annotate.JsonIgnore;

import kpic.model.CheckResult;


public class CheckResultInfo{
	@JsonIgnore  
	private boolean replace = true;
	private int typecd;
	private String name;
	@JsonIgnore  
	private String searchWord;	
	private String replaceWord;
	private String smitmNm;
	private int page;
	private int pos;
	
	@JsonIgnore  
	private CheckResult check;
	
	public CheckResultInfo( String name, CheckResult check ) {
		super();
		this.typecd = check.getPattern().getTypeCd();
		this.name = name;
		this.searchWord = check.getSearchWord();
		this.replaceWord = check.getReplaceWord();
		this.smitmNm = check.getPattern().getSmitmNm();
		this.page = check.getPage();
		this.pos = check.getPos();
		
		this.check = check;
	}
	
	public int getKey() {
		String key = getKeyString();
		return key.hashCode();
	}
	
	public String getKeyString(){
		String key = String.format("%s_%d_%s_%d", name, page, searchWord, pos );
		return key;
	}

	@JsonIgnore 
	public boolean isReplace() {
		return replace;
	}
	public void setReplace(boolean replace) {
		this.replace = replace;
	}
	
	public int getTypecd() {
		return typecd;
	}

	public void setTypecd(int typecd) {
		this.typecd = typecd;
	}

	public int getPos() {
		return pos;
	}

	public void setPos(int pos) {
		this.pos = pos;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@JsonIgnore  
	public String getSearchWord() {
		return searchWord;
	}
	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}
	
	public String getReplaceWord() {
		return replaceWord;
	}

	public void setReplaceWord(String replaceWord) {
		this.replaceWord = replaceWord;
	}

	public String getSmitmNm() {
		return smitmNm;
	}
	public void setSmitmNm(String smitmNm) {
		this.smitmNm = smitmNm;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	
	@JsonIgnore 
	public CheckResult getCheck() {
		return check;
	}
	
	
			
}