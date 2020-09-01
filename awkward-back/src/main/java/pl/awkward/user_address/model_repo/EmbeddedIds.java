package pl.awkward.user_address.model_repo;

import lombok.Data;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
public class EmbeddedIds implements Serializable {

    private Long userId;

    private Long addressId;

}
