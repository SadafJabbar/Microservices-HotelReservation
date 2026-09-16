package hotelservice.entities;


import hotelservice.enums.RoomStatus;
import hotelservice.enums.RoomType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "room")
@Builder
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "roomId")
    private Long roomId;

    @Column(name = "roomNo")
    private Long roomNo;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private RoomType type;

    @ManyToOne
    @JoinColumn(name = "hotel_id", referencedColumnName = "hotelId")
    private Hotel hotel;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private RoomStatus status;
}