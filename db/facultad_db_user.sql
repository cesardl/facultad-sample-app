CREATE DATABASE IF NOT EXISTS facultad CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

CREATE USER 'facultad_user'@'%' IDENTIFIED BY 'datlucaf';
GRANT ALL PRIVILEGES ON facultad.* TO 'facultad_user'@'%';
FLUSH PRIVILEGES;
