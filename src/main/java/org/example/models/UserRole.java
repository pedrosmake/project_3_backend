package org.example.models;

import java.util.Map;

public class UserRole {

    public static final String HR = "HR";
    public static final String MANAGER = "Manager";
    public static final String SALES = "Sales";
    private int roleId;

    private static final Map<Integer, String> ROLES_MAP = Map.of(
            1, HR,
            2, MANAGER,
            3, SALES
    );

    public UserRole(final int roleId) {
        setRoleId(roleId);
    }

    public String getRoleName() {
        return ROLES_MAP.get(getRoleId());
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(final int roleId) {
        this.roleId = roleId;
    }
}
