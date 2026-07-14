CREATE TABLE notificaciones (
                                id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                destinatario VARCHAR(100) NOT NULL,
                                asunto VARCHAR(150),
                                mensaje TEXT NOT NULL,
                                tipo VARCHAR(20) NOT NULL,
                                estado VARCHAR(20) NOT NULL,
                                fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);