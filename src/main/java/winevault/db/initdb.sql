DROP DATABASE IF EXISTS winevaultdb;

CREATE DATABASE winevaultdb CHARACTER SET utf8 COLLATE utf8_general_ci;
USE winevaultdb;


DROP TABLE IF EXISTS wines;
CREATE TABLE wines (
	id INT NOT NULL AUTO_INCREMENT,
    variety VARCHAR(100) NOT NULL,
    country VARCHAR(50) DEFAULT NULL,
    avgrating DOUBLE(5,2) DEFAULT NULL,
    price_low DOUBLE(6,2) DEFAULT NULL,
    price_high DOUBLE(6,2) DEFAULT NULL,
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS users;
CREATE TABLE users (
	id INT NOT NULL AUTO_INCREMENT,
    fullname VARCHAR(100) DEFAULT NULL,
    username varchar(16) NOT NULL,
    password varchar(16) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (username)
);

DROP TABLE IF EXISTS reviews;
CREATE TABLE reviews (
	id INT NOT NULL AUTO_INCREMENT,
    wid INT NOT NULL,
    uid INT NOT NULL,
    rating INT(3) NOT NULL,
    price DOUBLE(6,2) DEFAULT NULL,
    title VARCHAR(255) NOT NULL,
    content TEXT NOT NULL,
    country VARCHAR(100) DEFAULT NULL,
    province VARCHAR(100) DEFAULT NULL,
    region VARCHAR(100) DEFAULT NULL,
    subregion VARCHAR(100) DEFAULT NULL,
    winery VARCHAR(200) DEFAULT NULL,
    vineyard VARCHAR(200) DEFAULT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (wid) REFERENCES wines(id),
    FOREIGN KEY (uid) REFERENCES users(id)
);

DELIMITER $$

CREATE TRIGGER insert_avg AFTER INSERT ON reviews FOR EACH ROW
BEGIN
	UPDATE wines w INNER JOIN (
		SELECT wid, AVG(rating) AS avgrating FROM reviews GROUP BY wid
	) r ON w.id = r.wid
    SET w.avgrating = r.avgrating;
END

$$

CREATE TRIGGER insert_country AFTER INSERT ON reviews FOR EACH ROW
BEGIN
	UPDATE wines w INNER JOIN (
		SELECT NEW.wid, NEW.country, COUNT(NEW.country) as countrycnt FROM reviews WHERE NEW.country IS NOT NULL GROUP BY NEW.country ORDER BY countrycnt DESC LIMIT 1
	) r ON w.id = r.wid
    SET w.country = r.country;
END

$$

CREATE TRIGGER insert_lowprice AFTER INSERT ON reviews FOR EACH ROW
BEGIN
	UPDATE wines w INNER JOIN (
		SELECT wid, price, MIN(price) AS minprice FROM reviews WHERE price IS NOT NULL GROUP BY wid
    ) r ON w.id = r.wid
    SET w.price_low = r.minprice;
END

$$

CREATE TRIGGER insert_highprice AFTER INSERT ON reviews FOR EACH ROW
BEGIN
	UPDATE wines w INNER JOIN (
		SELECT wid, price, MAX(price) AS maxprice FROM reviews WHERE price IS NOT NULL GROUP BY wid
    ) r ON w.id = r.wid
    SET w.price_high = r.maxprice;
END

$$

DELIMITER ;