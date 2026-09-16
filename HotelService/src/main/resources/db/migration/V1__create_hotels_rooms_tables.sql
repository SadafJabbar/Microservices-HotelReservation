CREATE TABLE hotel (
                       hotelId BIGINT AUTO_INCREMENT PRIMARY KEY,
                       name VARCHAR(30) NOT NULL,
                       address VARCHAR(100) NOT NULL
);

CREATE TABLE room (
                      roomId BIGINT AUTO_INCREMENT PRIMARY KEY,
                      roomNo BIGINT NOT NULL,
                      type VARCHAR(50) NOT NULL,
                      hotel_id BIGINT NOT NULL,
                      status VARCHAR(50) NOT NULL,

                      CONSTRAINT fk_room_hotel
                          FOREIGN KEY (hotel_id)
                              REFERENCES hotel(hotelId)
);