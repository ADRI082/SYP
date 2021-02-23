package models;

import java.io.Serializable;
import java.sql.Date;

public class Empleado implements Serializable {
	
	
	private int idEmpleado;
	private Date ultimaSesion;
	private Date fechaContratacion;
	
	
	public Empleado(int idEmpleado, Date ultimaSesion, Date fechaContratacion) {
		this.idEmpleado = idEmpleado;
		this.ultimaSesion = ultimaSesion;
		this.fechaContratacion = fechaContratacion;
	}


	public int getIdEmpleado() {
		return idEmpleado;
	}


	public void setIdEmpleado(int idEmpleado) {
		this.idEmpleado = idEmpleado;
	}


	public Date getUltimaSesion() {
		return ultimaSesion;
	}


	public void setUltimaSesion(Date ultimaSesion) {
		this.ultimaSesion = ultimaSesion;
	}


	public Date getFechaContratacion() {
		return fechaContratacion;
	}


	public void setFechaContratacion(Date fechaContratacion) {
		this.fechaContratacion = fechaContratacion;
	}
	
	
	

}
