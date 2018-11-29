package com.learn.SpringBoot_Jpa.organization.pojo.MultiplePrimaryKey;

import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

@Data
public class User_Role_Key implements Serializable {

    private String userId;
    private String roleId;

    @Override
    public int hashCode() {
        return (Objects.isNull(userId)? 0 : userId.hashCode()) + (Objects.isNull(roleId) ? 0 : roleId.hashCode());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        final User_Role_Key other = (User_Role_Key)obj;
        if (this.userId == null){
            if (other.getUserId() != null) return false;
        }else {
            if (!this.userId.equals(other.getUserId())) return false;
        }
        if (this.roleId == null){
            if (other.getRoleId() != null) return false;
        }else {
            if (!this.roleId.equals(other.getRoleId())) return false;
        }
        return true;
    }
}
