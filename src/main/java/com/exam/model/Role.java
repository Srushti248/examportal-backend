package com.exam.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "roles")
public class Role {

@Id
private int roleId;
private String roleName;

@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "role")
@JsonIgnore
private Set<UserRole> userRoles= new HashSet <>();

public Role() {
	super();
}
public int getRoleId() {
	return roleId;
}
public Role(int roleId, String roleName) {
	super();
	this.roleId = roleId;
	this.roleName = roleName;
}
public void setRoleId(int roleId) {
	this.roleId = roleId;
}
public String getRoleName() {
	return roleName;
}
public void setRoleName(String roleName) {
	this.roleName = roleName;
}

}
