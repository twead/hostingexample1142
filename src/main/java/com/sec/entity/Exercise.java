package com.sec.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "exercises")
public class Exercise {

	@GeneratedValue @Id
	private int id;
	
	@Column(length = 50)
	private String exerciseName;
	
	@Column(length = 50)
	private String effect;
	
	@Column(length = 50)
	private String initialPosition;
	
	@Column(length = 50)
	private String motion;	
	
	@Column(length = 50)
	private String trainingTip;
	
	@Column
	private int set;
	
	@Column
	private int reps;
	
	@ManyToOne(cascade=CascadeType.ALL)
	private Muscle muscle;

	public Exercise() {
		super();
	}

	public Exercise(String exerciseName, String effect, String initialPosition, String motion, String trainingTip,
			int set, int reps, Muscle muscle) {
		super();
		this.exerciseName = exerciseName;
		this.effect = effect;
		this.initialPosition = initialPosition;
		this.motion = motion;
		this.trainingTip = trainingTip;
		this.set = set;
		this.reps = reps;
		this.muscle = muscle;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getExerciseName() {
		return exerciseName;
	}

	public void setExerciseName(String exerciseName) {
		this.exerciseName = exerciseName;
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
	
}
