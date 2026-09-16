package hotelservice.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "hotel")
@Builder
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hotelId")
    private Long hotelId;

    @Column(nullable = false)
    @Size(min = 3, max = 30, message = "hotel name must be of valid length")
    private String name;

    @Column(nullable = false)
    @Size(min = 3, max = 100, message = "hotel address must be of valid length")
    private String address;
}
