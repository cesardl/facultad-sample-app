CREATE DATABASE IF NOT EXISTS facultad CHARACTER SET utf8 COLLATE utf8_general_ci;

CREATE USER 'facultad'@'%' IDENTIFIED BY 'facultad';
GRANT ALL PRIVILEGES ON facultad.* TO 'facultad'@'%';
FLUSH PRIVILEGES;
