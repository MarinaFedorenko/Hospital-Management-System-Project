package nix.bean;

import java.sql.Timestamp;

public class Role extends AbstractEntity{

    public static final int ADMIN = 1;
    public static final int RECEPTIONIST = 2;
    public static final int DOCTOR= 3;

    private String name;

    private String description;

    public Role(Long id, String name, String description, String createdBy, String modifiedBy,
                Timestamp createdDatetime, Timestamp modifiedDatetime) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.createdBy = createdBy;
        this.modifiedBy = modifiedBy;
        this.createdDatetime = createdDatetime;
        this.modifiedDatetime = modifiedDatetime;
    }

    public Role(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }




}
