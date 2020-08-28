package pl.awkward.shared;

import java.time.LocalDateTime;

public interface BaseEntity {

    // id
    void setId(Long id);
    Long getId();

    // active
    void setActive(Boolean active);
    Boolean getActive();

    // addDate
    void setAddDate(LocalDateTime time);
    LocalDateTime getAddDate();

    // deleteDate
    void setDeleteDate(LocalDateTime time);
    LocalDateTime getDeleteDate();

}
