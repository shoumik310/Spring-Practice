package com.kimuohs.buyit.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "users")
@NoArgsConstructor
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@NotEmpty
	@Column(nullable = false)
	private String firstName;
	private String lastName;

	@NotEmpty
	@Column(nullable = false, unique = true)
	@Email(message = "{errors.invalid_email}")
	private String email;

	@NotEmpty
	private String password;

	/**
	 * Cascade implies that if any change is made to user all dependent types are
	 * also updated This means if user is deleted all the users orders etc are
	 * deleted
	 */
	// Implies that one "Role" can have many "Users" and one "User" can have many
	// roles
	@ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@JoinTable(name = "user_role", joinColumns = {
			@JoinColumn(name = "USER_ID", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "ROLE_ID", referencedColumnName = "id") })
	private List<Role> roles; // Since it is Many to Many we need a list to hold the many roles

	public User(User user) {
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
		this.email = user.getEmail();
		this.password = user.getPassword();
		this.roles = user.getRoles();
	}
}