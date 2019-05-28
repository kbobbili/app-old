package com.kalbob.app.department;

import com.kalbob.app.BaseEntity;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"department"})
@EqualsAndHashCode(exclude = {"department"}, callSuper = true)
@Accessors(chain = true)
@Entity
@Table(name = "address")
public class Address extends BaseEntity {

  private String aptNum;
  private String street;
  private String city;
  private String state;
  private String zipCode;
  @OneToOne(mappedBy = "address", fetch = FetchType.LAZY)
  private Department department;

  public Address setDepartment(Department department){
    if(department != null) department.setAddress_Internal(this);
    this.department = department;
    return this;
  }

  public Address removeDepartment() {
    if(department != null) {
      this.department.setAddress(null);
      this.department = null;
    }
    return this;
  }

}
