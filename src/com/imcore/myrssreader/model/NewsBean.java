package com.imcore.myrssreader.model;

public class NewsBean {
	public String title;
	public String link;
	public String description;
	public String pubDate;
	public String source;
	public String author;

	@Override
	public String toString() {
		return "title:" + this.title + ", link:" + this.link + ", description:"
				+ this.description;
	}
}
