package pl.awkward.shared;

import java.time.LocalDateTime;

public interface BaseEntity {
    void setId(Long id);
    Long getId();
    void setActive(Boolean active);
    Boolean getActive();
    void setAddDate(LocalDateTime time);
    LocalDateTime getAddDate();
}
