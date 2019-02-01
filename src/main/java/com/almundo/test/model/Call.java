package com.almundo.test.model;

import java.time.LocalTime;

/**
 * @see Clase que representa la llamada
 * @author Daniel Sarmiento
 *
 */
public class Call {
    private String idCall;
    private CallStatus callStatus;
    private Long timeOfCall;
    private LocalTime beginHour;
    private LocalTime endHour;
    
    public String getIdCall() {
		return idCall;
	}
	public void setIdCall(String idCall) {
		this.idCall = idCall;
	}
	public CallStatus getCallStatus() {
		return callStatus;
	}
	public void setCallStatus(CallStatus callStatus) {
		this.callStatus = callStatus;
	}
	public Long getTimeOfCall() {
		return timeOfCall;
	}
	public void setTimeOfCall(Long timeOfCall) {
		this.timeOfCall = timeOfCall;
	}
	public LocalTime getBeginHour() {
		return beginHour;
	}
	public void setBeginHour(LocalTime beginHour) {
		this.beginHour = beginHour;
	}
	public LocalTime getEndHour() {
		return endHour;
	}
	public void setEndHour(LocalTime endHour) {
		this.endHour = endHour;
	}
	@Override
	public String toString() {
		return "Call [idCall=" + idCall + ", callStatus=" + callStatus + ", timeOfCall=" + timeOfCall + ", beginHour="
				+ beginHour + ", endHour=" + endHour + "]";
	}
    @Override
    public boolean equals(Object o) { 
  
        // If the object is compared with itself then return true   
        if (o == this) { 
            return true; 
        } 
  
        /* Check if o is an instance of Complex or not 
          "null instanceof [type]" also returns false */
        if (!(o instanceof Call)) { 
            return false; 
        } 
          
        if(this.idCall == ((Call)o).idCall) {
        	return true;
        }
        
        return false;

    } 	

    
    
}
