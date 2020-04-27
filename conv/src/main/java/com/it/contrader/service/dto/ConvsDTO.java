package com.it.contrader.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Convs entity.
 */
public class ConvsDTO implements Serializable {

    private Long id;

    @NotNull
    private String source;

    @NotNull
    private String sourcetype;

    @NotNull
    private String outputtype;

    @NotNull
    private Long usr;

    private String tagname;

    private String removedtag;

    private String tagposition;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getSourcetype() {
        return sourcetype;
    }

    public void setSourcetype(String sourcetype) {
        this.sourcetype = sourcetype;
    }

    public String getOutputtype() {
        return outputtype;
    }

    public void setOutputtype(String outputtype) {
        this.outputtype = outputtype;
    }

    public Long getUsr() {
        return usr;
    }

    public void setUsr(Long usr) {
        this.usr = usr;
    }

    public String getTagname() {
        return tagname;
    }

    public void setTagname(String tagname) {
        this.tagname = tagname;
    }

    public String getRemovedtag() {
        return removedtag;
    }

    public void setRemovedtag(String removedtag) {
        this.removedtag = removedtag;
    }

    public String getTagposition() {
        return tagposition;
    }

    public void setTagposition(String tagposition) {
        this.tagposition = tagposition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ConvsDTO convsDTO = (ConvsDTO) o;
        if (convsDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), convsDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ConvsDTO{" +
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
