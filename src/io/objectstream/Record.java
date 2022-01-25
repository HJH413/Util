package io.objectstream;

import java.io.Serializable;

public class Record implements Serializable { // 직렬화를 시켜야한다
	private String name;
	private int age;
	private double height;
	private char bloodType;
	
	
	// setter와 getter
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public double getHeight() {
		return height;
	}
	public void setHeight(double height) {
		this.height = height;
	}
	public char getBloodType() {
		return bloodType;
	}
	public void setBloodType(char bloodType) {
		this.bloodType = bloodType;
	}
	
	//생성자
	public Record() {}; // 습관적으로 기본 생성자 함수를 만들자
	
	public Record(String name, int age, double height, char bloodType) {
		super();
		this.name = name;
		this.age = age;
		this.height = height;
		this.bloodType = bloodType;
	}
	
}
