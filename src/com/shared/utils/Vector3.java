package com.shared.utils;

import java.io.Serializable;


public class Vector3 implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -663648887447528420L;
	public float x;
	public float y;
	public float z;
	
	public Vector3(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Vector3() {}
	
	public void normalize() {
		float length = (float) Math.sqrt(x*x + y*y + z*z);
		if (length == 0)
			return;
		x /= length;
		y /= length;
		z /= length;
	}
	
	public void multScalar(float scalar) {
		this.x *= scalar;
		this.y *= scalar;
		this.z *= scalar;
	}
	
	public void mult(Vector3 vector) {
		
	}
	
	public void left() {
		float temp = x;
		x = -y;
		y = temp;
	}
	
	public void right() {
		float temp = x;
		x = y;
		y = -temp;
	}
	
	public String toString() {
		return "[" + x + ", " + y + ", " + z + "]";
	}
	
	public static float dist(float x, float y) {
		return (float)Math.sqrt(x*x + y*y);
	}

	public static Vector3 getVectorBetween(Vector3 from, Vector3 to) {
		// TODO Auto-generated method stub
		Vector3 deltaVector = new Vector3(to.x - from.x, to.y - from.y, to.z - from.z);
		return deltaVector;
	}
}
