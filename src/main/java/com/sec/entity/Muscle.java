package com.sec.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "muscles")
public class Muscle {

	@Id
	@Column(length = 30)
	private String muscleName;
	
	@Column
	private String muscleImage;
	
	@Column
	private String url;

	@OneToMany(mappedBy = "muscle")
	private List<Exercise> exercises;

	@ManyToOne(cascade = CascadeType.ALL)
	private TrainingPlan trainingPlan;

	public Muscle() {

	}

	public Muscle(String muscleName, String muscleImage, List<Exercise> exercises, TrainingPlan trainingPlan) {
		super();
		this.muscleName = muscleName;
		this.muscleImage = muscleImage;
		this.exercises = exercises;
		this.trainingPlan = trainingPlan;
	}

	public String getMuscleName() {
		return muscleName;
	}

	public void setMuscleName(String muscleName) {
		this.muscleName = muscleName;
	}

	public String getMuscleImage() {
		return muscleImage;
	}

	public void setMuscleImage(String muscleImage) {
		this.muscleImage = muscleImage;
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	
	
	/*public String getUrl() {
		String url = muscleName.toLowerCase()
				.replace(" ", "-")
				.replace("á", "a")
				.replace("í", "i")
				.replace('é','e')
				.replace('ű','u')
				.replace('ú','u')
				.replace('ü','u')
				.replace('ö','o')
				.replace('ó','o')
				.replace('ő','o');
		return url;
	}*/

}
