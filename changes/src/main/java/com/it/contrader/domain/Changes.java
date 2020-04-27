package com.it.contrader.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Changes.
 */
@Entity
@Table(name = "changes")
public class Changes implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "jhi_user", nullable = false)
    private Long user;

    @Column(name = "tag_name")
    private String tag_name;

    @Column(name = "removed_tag")
    private String removed_tag;

    @Column(name = "tag_position")
    private String tag_position;

    @NotNull
    @Column(name = "changes_name", nullable = false)
    private String changes_name;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUser() {
        return user;
    }

    public Changes user(Long user) {
        this.user = user;
        return this;
    }

    public void setUser(Long user) {
        this.user = user;
    }

    public String getTag_name() {
        return tag_name;
    }

    public Changes tag_name(String tag_name) {
        this.tag_name = tag_name;
        return this;
    }

    public void setTag_name(String tag_name) {
        this.tag_name = tag_name;
    }

    public String getRemoved_tag() {
        return removed_tag;
    }

    public Changes removed_tag(String removed_tag) {
        this.removed_tag = removed_tag;
        return this;
    }

    public void setRemoved_tag(String removed_tag) {
        this.removed_tag = removed_tag;
    }

    public String getTag_position() {
        return tag_position;
    }

    public Changes tag_position(String tag_position) {
        this.tag_position = tag_position;
        return this;
    }

    public void setTag_position(String tag_position) {
        this.tag_position = tag_position;
    }

    public String getChanges_name() {
        return changes_name;
    }

    public Changes changes_name(String changes_name) {
        this.changes_name = changes_name;
        return this;
    }

    public void setChanges_name(String changes_name) {
        this.changes_name = changes_name;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Changes changes = (Changes) o;
        if (changes.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), changes.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Changes{" +
            "id=" + getId() +
            ", user=" + getUser() +
            ", tag_name='" + getTag_name() + "'" +
            ", removed_tag='" + getRemoved_tag() + "'" +
            ", tag_position='" + getTag_position() + "'" +
            ", changes_name='" + getChanges_name() + "'" +
            "}";
    }
}
