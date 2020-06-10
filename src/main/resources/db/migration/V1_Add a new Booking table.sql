CREATE TABLE Booking (
  bookingId INT NOT NULL AUTO_INCREMENT,
  src VARCHAR(100),
  dest VARCHAR(100),
  status VARCHAR(50),
  userId INT,
  carId INT,
  PRIMARY KEY (bookingId)
)ENGINE=InnoDB;