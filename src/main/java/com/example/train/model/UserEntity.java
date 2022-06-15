package com.example.train.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="users")
public class UserEntity {

  @Id
  @Column(name = "id", nullable = false)
  private Long id;

  @Size(min = 4, max = 20)
  @Column(name = "name")
  private String userName;

  @Size(min = 6, max = 20)
  @Column(name = "login_id", nullable = false)
  private String loginId;

  @Size(min = 8, max = 255)
  @Column(name = "login_pass", nullable = false)
  private String loginPass;

}
