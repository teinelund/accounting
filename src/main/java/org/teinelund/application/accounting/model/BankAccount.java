package org.teinelund.application.accounting.model;

public class BankAccount {
    private String name;
    private String description;
    private String edit;
    private String delete;

    public BankAccount(String name, String description, String edit, String delete) {
        this.name = name;
        this.description = description;
        this.edit = edit;
        this.delete = delete;
    }

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

    public String getEdit() {
        return edit;
    }

    public void setEdit(String edit) {
        this.edit = edit;
    }

    public String getDelete() {
        return delete;
    }

    public void setDelete(String delete) {
        this.delete = delete;
    }
}
