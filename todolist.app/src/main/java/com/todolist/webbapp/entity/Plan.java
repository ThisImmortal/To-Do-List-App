package com.todolist.webbapp.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "plan")
public class Plan {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;

	@Column(name = "title")
	private String title;

	@Column(name = "description")
	private String description;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "begin_date")
	private Date beginDate;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "end_date")
	private Date endDate;

	@Column(name = "available")
	private int available;

	public Plan() {

		available = 1;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getAvailable() {
		return available;
	}

	public void setAvailable(int available) {
		this.available = available;
	}

	public Plan(String title, String description, Date beginDate, Date endDate) {

		this.title = title;
		this.description = description;
		this.beginDate = beginDate;
		this.endDate = endDate;

	}

	@Override
	public String toString() {
		return "Plan [id=" + id + ", title=" + title + ", description=" + description + ", beginDate=" + beginDate
				+ ", endDate=" + endDate + ", status=" + "]";
	}

}
