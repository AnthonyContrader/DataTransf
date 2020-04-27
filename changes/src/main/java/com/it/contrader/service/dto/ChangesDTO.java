package com.it.contrader.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Changes entity.
 */
public class ChangesDTO implements Serializable {

    private Long id;

    @NotNull
    private Long user;

    private String tag_name;

    private String removed_tag;

    private String tag_position;

    @NotNull
    private String changes_name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUser() {
        return user;
    }

    public void setUser(Long user) {
        this.user = user;
    }

    public String getTag_name() {
        return tag_name;
    }

    public void setTag_name(String tag_name) {
        this.tag_name = tag_name;
    }

    public String getRemoved_tag() {
        return removed_tag;
    }

    public void setRemoved_tag(String removed_tag) {
        this.removed_tag = removed_tag;
    }

    public String getTag_position() {
        return tag_position;
    }

    public void setTag_position(String tag_position) {
        this.tag_position = tag_position;
    }

    public String getChanges_name() {
        return changes_name;
    }

    public void setChanges_name(String changes_name) {
        this.changes_name = changes_name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ChangesDTO changesDTO = (ChangesDTO) o;
        if (changesDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), changesDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ChangesDTO{" +
            "id=" + getId() +
            ", user=" + getUser() +
            ", tag_name='" + getTag_name() + "'" +
            ", removed_tag='" + getRemoved_tag() + "'" +
            ", tag_position='" + getTag_position() + "'" +
            ", changes_name='" + getChanges_name() + "'" +
            "}";
    }
}
