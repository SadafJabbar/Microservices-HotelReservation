CREATE TABLE Booking (
                         bookingId BIGINT AUTO_INCREMENT PRIMARY KEY,
                         hotelId BIGINT NOT NULL,
                         roomId BIGINT NOT NULL,
                         guestName VARCHAR(100) NOT NULL,
                         checkIn DATE NOT NULL,
                         checkOut DATE NOT NULL,
                         status VARCHAR(30) NOT NULL
);