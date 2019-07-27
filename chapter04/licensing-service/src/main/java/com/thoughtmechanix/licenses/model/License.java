package com.thoughtmechanix.licenses.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import lombok.Data;

@Data
@Entity
@Table(name = "licenses")
public class License {

  @Id
  @Column(name = "license_id", nullable = false)
  private String licenseId;

  @Column(name = "organization_id", nullable = false)
  private String organizationId;

  @Transient
  private String organizationName;

  @Transient
  private String contactName;

  @Transient
  private String contactPhone;

  @Transient
  private String contactEmail;

  @Column(name = "product_name", nullable = false)
  private String productName;

  @Column(name = "license_type", nullable = false)
  private String licenseType;

  @Column(name = "license_max", nullable = false)
  private String licenseMax;

  @Column(name = "license_allocated", nullable = false)
  private Integer licenseAllocated;

  @Column(name = "comment")
  private String comment;
}
