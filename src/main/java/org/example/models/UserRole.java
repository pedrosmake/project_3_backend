package org.example.models;

import java.util.Map;

public class UserRole {

    public static final String HR = "HR";
    public static final String MANAGER = "Manager";
    public static final String SALES = "Sales";
    int roleId;

    private static final Map<Integer,String> rolesMap = Map.of(
            1, HR,
            2, MANAGER,
            3, SALES
    );

    public UserRole(int roleId) {
        setRoleId(roleId);
    }

    public String getRoleName(){
        return rolesMap.get(getRoleId());
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }
}
