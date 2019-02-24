package com.jin.learn.entity;

import java.io.Serializable;

public class PersonAddresses implements Serializable {
    private Integer id;

    private Long ownersId;

    private Long addressesId;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getOwnersId() {
        return ownersId;
    }

    public void setOwnersId(Long ownersId) {
        this.ownersId = ownersId;
    }

    public Long getAddressesId() {
        return addressesId;
    }

    public void setAddressesId(Long addressesId) {
        this.addressesId = addressesId;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        PersonAddresses other = (PersonAddresses) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getOwnersId() == null ? other.getOwnersId() == null : this.getOwnersId().equals(other.getOwnersId()))
            && (this.getAddressesId() == null ? other.getAddressesId() == null : this.getAddressesId().equals(other.getAddressesId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getOwnersId() == null) ? 0 : getOwnersId().hashCode());
        result = prime * result + ((getAddressesId() == null) ? 0 : getAddressesId().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", ownersId=").append(ownersId);
        sb.append(", addressesId=").append(addressesId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}