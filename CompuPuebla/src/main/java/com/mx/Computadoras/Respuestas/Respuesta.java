package com.mx.Computadoras.Respuestas;

public class Respuesta {
	
	String mensjae;
	boolean succes;
	Object obj;
	
	public Respuesta() {
		
	}

	public Respuesta(String mensjae, boolean succes, Object obj) {
		super();
		this.mensjae = mensjae;
		this.succes = succes;
		this.obj = obj;
	}

	@Override
	public String toString() {
		return "Respuesta [mensjae=" + mensjae + ", succes=" + succes + ", obj=" + obj + "]";
	}

	public String getMensjae() {
		return mensjae;
	}

	public void setMensjae(String mensjae) {
		this.mensjae = mensjae;
	}

	public boolean isSucces() {
		return succes;
	}

	public void setSucces(boolean succes) {
		this.succes = succes;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}
	
	

}
