package ch.zli.m223.model;

import javax.persistence.*;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.Set;

@Entity
@Table(name = "application_user")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Schema(readOnly = true)
  private Long id;

  @Column(nullable = false)
  private String username;

  @Column(nullable = false)
  private String role;

  public String getRole() {
    return role;
}

  public void setRole(String role) {
    this.role = role;
  }

    @OneToMany(mappedBy = "user")
    @JsonManagedReference("user-entries")
    private Set<Entry> entries;


  public Set<Entry> getEntries() {
    return entries;
}

  public void setEntries(Set<Entry> entries) {
    this.entries = entries;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  
}