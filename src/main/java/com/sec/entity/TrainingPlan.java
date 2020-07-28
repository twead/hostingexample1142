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

	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false, length = 32)
	private String planName;

	@Column(columnDefinition = "TEXT")
	private String planDetail;

	@OneToMany(mappedBy = "trainingPlan")
	private List<Muscle> muscles;

	public TrainingPlan() {

	}

	public TrainingPlan(String planName, String planDetail, List<Muscle> muscles) {
		this.planName = planName;
		this.planDetail = planDetail;
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

	public String getPlanDetail() {
		return planDetail;
	}

	public void setPlanDetail(String planDetail) {
		this.planDetail = planDetail;
	}

	public List<Muscle> getMuscles() {
		return muscles;
	}

	public void setMuscles(List<Muscle> muscles) {
		this.muscles = muscles;
	}

}
