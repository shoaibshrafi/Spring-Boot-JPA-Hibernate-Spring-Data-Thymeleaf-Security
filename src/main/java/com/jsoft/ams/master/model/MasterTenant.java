package com.jsoft.ams.master.model;

import lombok.Data;

@Data
public class MasterTenant {

    private Long id;

    private String name;

    private String db;

    private String username;

    /**
     * For simplicity, we are not storing an encrypted password. In production
     * this should be a encrypted password.
     */
    private String password;

}
