package com.sec.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "muscles")
public class Muscle {

	@GeneratedValue
	@Id
	private int id;

	@Column(length = 30)
	private String muscleName;

	@OneToMany(mappedBy = "muscle")
	private List<Exercise> exercises;

	@ManyToOne(cascade = CascadeType.ALL)
	private TrainingPlan trainingPlan;

	public Muscle() {

	}

	public Muscle(String muscleName, List<Exercise> exercises, TrainingPlan trainingPlan) {
		super();
		this.muscleName = muscleName;
		this.exercises = exercises;
		this.trainingPlan = trainingPlan;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMuscleName() {
		return muscleName;
	}

	public void setMuscleName(String muscleName) {
		this.muscleName = muscleName;
	}

	public List<Exercise> getExercises() {
		return exercises;
	}

	public void setExercises(List<Exercise> exercises) {
		this.exercises = exercises;
	}

	public TrainingPlan getTrainingPlan() {
		return trainingPlan;
	}

	public void setTrainingPlan(TrainingPlan trainingPlan) {
		this.trainingPlan = trainingPlan;
	}

}
