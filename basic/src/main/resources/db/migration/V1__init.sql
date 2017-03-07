CREATE TABLE users (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  email varchar(255) DEFAULT NULL,
  password varchar(255) DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE user_roles (
  id      BIGINT(20)  NOT NULL AUTO_INCREMENT,
  user_id BIGINT(20)  NOT NULL,
  role    VARCHAR(45) NOT NULL,
  PRIMARY KEY (id)
)  ENGINE = InnoDB  DEFAULT CHARSET = utf8;