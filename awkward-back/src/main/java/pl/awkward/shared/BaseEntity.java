package pl.awkward.shared;

public interface BaseEntity {
    void setId(Long id);
    Long getId();
    void setActive(Boolean active);
    Boolean getActive();
}
