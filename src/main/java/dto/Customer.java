package dto;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import lombok.Data;

@Entity
@Data
public class Customer {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int id;
private String Name;
private String Password;
private long Mobile;
private String Email;
private String gender;
private String country;
private LocalDate dob;
private int age;
@Lob
private byte[] picture;
}
