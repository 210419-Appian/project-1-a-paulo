package com.revature.models;

public class AccountType {

    private int typeId; // primary key
    private String type; // not null, unique

    public AccountType(){
        super();
    }

    public AccountType(int typeId, String type){
        this.typeId = typeId;
        this.type = type;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "AccountType{" +
                "typeId=" + typeId +
                ", type='" + type + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AccountType that = (AccountType) o;

        if (typeId != that.typeId) return false;
        return type != null ? type.equals(that.type) : that.type == null;
    }

    @Override
    public int hashCode() {
        int result = typeId;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }

}
