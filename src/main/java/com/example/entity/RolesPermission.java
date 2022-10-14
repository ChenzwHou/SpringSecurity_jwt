package com.example.entity;

import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName roles_permission
 */
@Data
public class RolesPermission implements Serializable {
    /**
     * 
     */
    private Long roleId;

    /**
     * 
     */
    private Long premission;

    private static final long serialVersionUID = 1L;

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
        RolesPermission other = (RolesPermission) that;
        return (this.getRoleId() == null ? other.getRoleId() == null : this.getRoleId().equals(other.getRoleId()))
            && (this.getPremission() == null ? other.getPremission() == null : this.getPremission().equals(other.getPremission()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getRoleId() == null) ? 0 : getRoleId().hashCode());
        result = prime * result + ((getPremission() == null) ? 0 : getPremission().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", roleId=").append(roleId);
        sb.append(", premission=").append(premission);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}