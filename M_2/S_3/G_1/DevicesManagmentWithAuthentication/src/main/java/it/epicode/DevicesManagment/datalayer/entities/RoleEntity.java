package it.epicode.DevicesManagment.datalayer.entities;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
@Entity
@Table(name = "roles")
@Builder(setterPrefix = "with")
@AllArgsConstructor
@NoArgsConstructor
public class RoleEntity extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "roles_seq")
	@SequenceGenerator(name = "roles_seq", sequenceName = "roles_seq")
	private long id;
	@Column(length = 15, unique = true)
	private String name;
	@ManyToMany(mappedBy = "roles")
	@JsonBackReference
	private final List<Employee> employees = new ArrayList<>();
}
