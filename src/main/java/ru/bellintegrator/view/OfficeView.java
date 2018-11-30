package ru.bellintegrator.view;

import javax.validation.constraints.NotEmpty;

public class OfficeView {

    @NotEmpty
    public long id;

    @NotEmpty
    public long orgId;

    @NotEmpty
    public String name;

    @NotEmpty
    public String address;

    public String phone;

    public Boolean isActive;

    @Override
    public String toString() {
        return "{" +
                "\"id\":\"" + id +
                "\", \"orgId\": \"" + orgId +
                "\", \"name\": \"" + name +
                "\", \"address\": \"" + address +
                "\", \"phone\": \"" + phone +
                "\", \"isActive\": \"" + isActive +
                "\"}";
    }
}
