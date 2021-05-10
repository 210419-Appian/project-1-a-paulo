package com.revature.models;

import java.io.Serializable;

public class AccountStatus implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int statusId; // primary key
    private String status; // not null, unique

    public AccountStatus(){
        super();
    }

    public AccountStatus(int statusId, String status){
        super();
        this.statusId = statusId;
        this.status = status;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    @Override
    public String toString() {
        return "AccountStatus{" +
                "statusId=" + statusId +
                ", status='" + status + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AccountStatus that = (AccountStatus) o;

        if (statusId != that.statusId) return false;
        return status != null ? status.equals(that.status) : that.status == null;
    }

    @Override
    public int hashCode() {
        int result = statusId;
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }

}
