package com.thoughtmechanix.authentication.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "user_orgs")
public class UserOrganization {

  @Id
  @Column(name = "user_name", nullable = false)
  private String userName;

  @Column(name = "organization_id", nullable = false)
  private String organizationId;
}
