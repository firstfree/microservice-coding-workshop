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
  private String licenseId;

  @Column(nullable = false)
  private String organizationId;

  @Column(nullable = false)
  private String productName;

  @Column(nullable = false)
  private String licenseType;

  @Column(nullable = false)
  private String licenseMax;

  @Column(nullable = false)
  private Integer licenseAllocated;

  private String comment;

  @Transient
  private String organizationName;

  @Transient
  private String contactName;

  @Transient
  private String contactPhone;

  @Transient
  private String contactEmail;

  public License withId(String id) {
    setLicenseId(id);
    return this;
  }

  public License withOrganizationId(String organizationId) {
    setOrganizationId(organizationId);
    return this;
  }

  public License withProductName(String productName) {
    setProductName(productName);
    return this;
  }

  public License withOrganizationName(String organizationName) {
    setOrganizationName(organizationName);
    return this;
  }

  public License withContactName(String contactName) {
    setContactName(contactName);
    return this;
  }

  public License withContactEmail(String contactEmail) {
    setContactEmail(contactEmail);
    return this;
  }

  public License withContactPhone(String contactPhone) {
    setContactPhone(contactPhone);
    return this;
  }

  public License withComment(String comment) {
    setComment(comment);
    return this;
  }
}
