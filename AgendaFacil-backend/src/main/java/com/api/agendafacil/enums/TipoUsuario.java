package com.api.agendafacil.enums;

/**
 * Enum dos roles do usuario
 * 
 * @author Allan
 */
public enum TipoUsuario {
	ADMIN("ROLE_ADMIN"),
    USUARIO("ROLE_USUARIO");

	private String role;
	
	TipoUsuario(String role) {
		this.role = role;
	}
	
	public String getRole() {
		return role;
	}
}