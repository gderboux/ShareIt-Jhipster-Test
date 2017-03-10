package com.shareit.app.service.dto;


import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the Feedback entity.
 */
public class FeedbackDTO implements Serializable {

    private Long id;

    @NotNull
    private String comment;

    @NotNull
    private Boolean visible;

    @NotNull
    private Integer rank;

    private Long bookingId;

    private Long userId;

    private Long reporterId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }
    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Long getBookingId() {
        return bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long appUserId) {
        this.userId = appUserId;
    }

    public Long getReporterId() {
        return reporterId;
    }

    public void setReporterId(Long appUserId) {
        this.reporterId = appUserId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        FeedbackDTO feedbackDTO = (FeedbackDTO) o;

        if ( ! Objects.equals(id, feedbackDTO.id)) { return false; }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "FeedbackDTO{" +
            "id=" + id +
            ", comment='" + comment + "'" +
            ", visible='" + visible + "'" +
            ", rank='" + rank + "'" +
            '}';
    }
}
