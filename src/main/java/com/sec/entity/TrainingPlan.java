package com.sec.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "training_plans")
public class TrainingPlan {
	
	@Id @GeneratedValue
	private Long id;
	
	@Column(nullable = false, length = 32)
	private String planName;
	
	@Column(columnDefinition = "TEXT")
	private String plan_detail;
	
	@OneToMany(mappedBy = "trainingPlan")
	private List<Muscle> muscles;

	public TrainingPlan() {

	}

	public TrainingPlan(String planName, String plan_detail, List<Muscle> muscles) {
		super();
		this.planName = planName;
		this.plan_detail = plan_detail;
		this.muscles = muscles;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPlanName() {
		return planName;
	}

	public void setPlanName(String planName) {
		this.planName = planName;
	}

	public String getPlan_detail() {
		return plan_detail;
	}

	public void setPlan_detail(String plan_detail) {
		this.plan_detail = plan_detail;
	}

	public List<Muscle> getMuscles() {
		return muscles;
	}

	public void setMuscles(List<Muscle> muscles) {
		this.muscles = muscles;
	}
	
}
