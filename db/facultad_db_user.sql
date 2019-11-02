CREATE DATABASE IF NOT EXISTS facultad CHARACTER SET utf8 COLLATE utf8_general_ci;

CREATE USER 'facultad_user'@'%' IDENTIFIED BY 'datlucaf';
GRANT ALL PRIVILEGES ON facultad.* TO 'facultad_user'@'%';
FLUSH PRIVILEGES;
