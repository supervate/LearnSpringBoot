package com.learn.SpringBoot_Jpa.organization.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

@Data
public class User_Dept_Key implements Serializable {

    private String userId;
    private String DeptId;

    @Override
    public int hashCode() {
        return (Objects.isNull(userId)? 0 : userId.hashCode()) + (Objects.isNull(DeptId) ? 0 : DeptId.hashCode());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        final User_Dept_Key other = (User_Dept_Key)obj;
        if (this.userId == null){
            if (other.getUserId() != null) return false;
        }else {
            if (!this.userId.equals(other.getUserId())) return false;
        }
        if (this.DeptId == null){
            if (other.getDeptId() != null) return false;
        }else {
            if (!this.DeptId.equals(other.getDeptId())) return false;
        }
        return true;
    }
}
