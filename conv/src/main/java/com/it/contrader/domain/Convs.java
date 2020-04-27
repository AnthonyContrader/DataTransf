package com.it.contrader.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Convs.
 */
@Entity
@Table(name = "convs")
public class Convs implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "source", nullable = false)
    private String source;

    @NotNull
    @Column(name = "sourcetype", nullable = false)
    private String sourcetype;

    @NotNull
    @Column(name = "outputtype", nullable = false)
    private String outputtype;

    @NotNull
    @Column(name = "usr", nullable = false)
    private Long usr;

    @Column(name = "tagname")
    private String tagname;

    @Column(name = "removedtag")
    private String removedtag;

    @Column(name = "tagposition")
    private String tagposition;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSource() {
        return source;
    }

    public Convs source(String source) {
        this.source = source;
        return this;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getSourcetype() {
        return sourcetype;
    }

    public Convs sourcetype(String sourcetype) {
        this.sourcetype = sourcetype;
        return this;
    }

    public void setSourcetype(String sourcetype) {
        this.sourcetype = sourcetype;
    }

    public String getOutputtype() {
        return outputtype;
    }

    public Convs outputtype(String outputtype) {
        this.outputtype = outputtype;
        return this;
    }

    public void setOutputtype(String outputtype) {
        this.outputtype = outputtype;
    }

    public Long getUsr() {
        return usr;
    }

    public Convs usr(Long usr) {
        this.usr = usr;
        return this;
    }

    public void setUsr(Long usr) {
        this.usr = usr;
    }

    public String getTagname() {
        return tagname;
    }

    public Convs tagname(String tagname) {
        this.tagname = tagname;
        return this;
    }

    public void setTagname(String tagname) {
        this.tagname = tagname;
    }

    public String getRemovedtag() {
        return removedtag;
    }

    public Convs removedtag(String removedtag) {
        this.removedtag = removedtag;
        return this;
    }

    public void setRemovedtag(String removedtag) {
        this.removedtag = removedtag;
    }

    public String getTagposition() {
        return tagposition;
    }

    public Convs tagposition(String tagposition) {
        this.tagposition = tagposition;
        return this;
    }

    public void setTagposition(String tagposition) {
        this.tagposition = tagposition;
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
        Convs convs = (Convs) o;
        if (convs.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), convs.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Convs{" +
            "id=" + getId() +
            ", source='" + getSource() + "'" +
            ", sourcetype='" + getSourcetype() + "'" +
            ", outputtype='" + getOutputtype() + "'" +
            ", usr=" + getUsr() +
            ", tagname='" + getTagname() + "'" +
            ", removedtag='" + getRemovedtag() + "'" +
            ", tagposition='" + getTagposition() + "'" +
            "}";
    }
}
