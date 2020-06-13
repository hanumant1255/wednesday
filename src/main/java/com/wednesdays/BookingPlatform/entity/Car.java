package com.wednesdays.BookingPlatform.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Car", schema = "PUBLIC") 
public class Car {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int carId;
	private String currentLocation;
	private String status;
}
