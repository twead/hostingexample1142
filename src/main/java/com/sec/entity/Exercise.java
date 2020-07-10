package com.sec.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "exercises")
public class Exercise {

	@Id
	@Column(length = 50)
	private String exerciseName;
	
	@Column
	private String exerciseImage;

	@Column(columnDefinition = "TEXT")
	private String effect;

	@Column(columnDefinition = "TEXT")
	private String initialPosition;

	@Column(columnDefinition = "TEXT")
	private String motion;

	@Column(columnDefinition = "TEXT")
	private String trainingTip;

	@Column
	private int set;

	@Column
	private int reps;
	
	@Column(length = 50)
	private String url;

	@ManyToOne(cascade = CascadeType.ALL)
	private Muscle muscle;

	public Exercise() {
		super();
	}

	public Exercise(String exerciseName, String exerciseImage, String effect, String initialPosition, String motion, String trainingTip,
			int set, int reps, Muscle muscle) {
		super();
		this.exerciseName = exerciseName;
		this.exerciseImage = exerciseImage;
		this.effect = effect;
		this.initialPosition = initialPosition;
		this.motion = motion;
		this.trainingTip = trainingTip;
		this.set = set;
		this.reps = reps;
		this.muscle = muscle;
	}

	public String getExerciseName() {
		return exerciseName;
	}

	public void setExerciseName(String exerciseName) {
		this.exerciseName = exerciseName;
	}

	public String getExerciseImage() {
		return exerciseImage;
	}

	public void setExerciseImage(String exerciseImage) {
		this.exerciseImage = exerciseImage;
	}

	public String getEffect() {
		return effect;
	}

	public void setEffect(String effect) {
		this.effect = effect;
	}

	public String getInitialPosition() {
		return initialPosition;
	}

	public void setInitialPosition(String initialPosition) {
		this.initialPosition = initialPosition;
	}

	public String getMotion() {
		return motion;
	}

	public void setMotion(String motion) {
		this.motion = motion;
	}

	public String getTrainingTip() {
		return trainingTip;
	}

	public void setTrainingTip(String trainingTip) {
		this.trainingTip = trainingTip;
	}

	public int getSet() {
		return set;
	}

	public void setSet(int set) {
		this.set = set;
	}

	public int getReps() {
		return reps;
	}

	public void setReps(int reps) {
		this.reps = reps;
	}

	public Muscle getMuscle() {
		return muscle;
	}

	public void setMuscle(Muscle muscle) {
		this.muscle = muscle;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	
//	public String getUrl() {
//		String url = exerciseName.toLowerCase()
//				.replace(" ", "-")
//				.replace("á", "a")
//				.replace("í", "i")
//				.replace('é','e')
//				.replace('ű','u')
//				.replace('ú','u')
//				.replace('ü','u')
//				.replace('ö','o')
//				.replace('ó','o')
//				.replace('ő','o');
//		return url;
//	}

}
