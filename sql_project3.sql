
CREATE DATABASE dvh_motobike_shop CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

  
 

USE dvh_motobike_shop;

CREATE TABLE dvh_user (
    dvh_user_id    BIGINT AUTO_INCREMENT PRIMARY KEY,
    dvh_username   VARCHAR(50)  NOT NULL UNIQUE,
    dvh_password   VARCHAR(200) NOT NULL,
    dvh_full_name  VARCHAR(100) NOT NULL,
    dvh_phone      VARCHAR(20)  NOT NULL,
    dvh_email      VARCHAR(100),
    dvh_role       ENUM('ROLE_USER','ROLE_ADMIN') DEFAULT 'ROLE_USER',
    dvh_status     ENUM('ACTIVE','INACTIVE')      DEFAULT 'ACTIVE',
    dvh_created_at DATETIME DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE dvh_brand (
    dvh_brand_id      BIGINT AUTO_INCREMENT PRIMARY KEY,
    dvh_brand_name    VARCHAR(100) NOT NULL,
    dvh_brand_country VARCHAR(100),
    dvh_brand_desc    TEXT
);

CREATE TABLE dvh_motorbike (
    dvh_motorbike_id      BIGINT AUTO_INCREMENT PRIMARY KEY,
    dvh_title             VARCHAR(150) NOT NULL,
    dvh_slug              VARCHAR(200),
    dvh_price             DECIMAL(15,2) NOT NULL,
    dvh_year              INT,
    dvh_condition         ENUM('NEW','USED') DEFAULT 'NEW',
    dvh_fuel_type         ENUM('ELECTRIC','GAS') DEFAULT 'ELECTRIC',
    dvh_power_watt        INT,
    dvh_odo_km            INT,
    dvh_color             VARCHAR(50),
    dvh_location_province VARCHAR(100),
    dvh_has_paper         TINYINT(1) DEFAULT 0,
    dvh_post_status       ENUM('DRAFT','PUBLISHED','HIDDEN') DEFAULT 'PUBLISHED',
    dvh_created_at        DATETIME DEFAULT CURRENT_TIMESTAMP,
    dvh_brand_id          BIGINT NOT NULL,
    dvh_seller_id         BIGINT NOT NULL,
    CONSTRAINT fk_dvh_motorbike_brand
        FOREIGN KEY (dvh_brand_id) REFERENCES dvh_brand(dvh_brand_id),
    CONSTRAINT fk_dvh_motorbike_user
        FOREIGN KEY (dvh_seller_id) REFERENCES dvh_user(dvh_user_id)
);

CREATE TABLE dvh_motorbike_image (
    dvh_image_id      BIGINT AUTO_INCREMENT PRIMARY KEY,
    dvh_image_url     VARCHAR(255) NOT NULL,
    dvh_is_primary    TINYINT(1) DEFAULT 0,
    dvh_motorbike_id  BIGINT NOT NULL,
    CONSTRAINT fk_dvh_image_motorbike
        FOREIGN KEY (dvh_motorbike_id) REFERENCES dvh_motorbike(dvh_motorbike_id)
);

CREATE TABLE dvh_blog_post (
    dvh_blog_id      BIGINT AUTO_INCREMENT PRIMARY KEY,
    dvh_blog_title   VARCHAR(200) NOT NULL,
    dvh_blog_slug    VARCHAR(255),
    dvh_blog_content TEXT,
    dvh_created_at   DATETIME DEFAULT CURRENT_TIMESTAMP,
    dvh_author_id    BIGINT,
    CONSTRAINT fk_dvh_blog_user
        FOREIGN KEY (dvh_author_id) REFERENCES dvh_user(dvh_user_id)
);

INSERT INTO dvh_user (dvh_username, dvh_password, dvh_full_name, dvh_phone, dvh_email, dvh_role)
VALUES
('admin', '123456', 'Quản trị viên DVH', '0900000000', 'admin@motobike_dvh.vn', 'ROLE_ADMIN'),
('hieu',  '123456', 'Đinh Văn Hiếu',    '0911111111', 'hieu@example.com',       'ROLE_USER');

INSERT INTO dvh_brand (dvh_brand_name, dvh_brand_country, dvh_brand_desc)
VALUES
('VinFast', 'Việt Nam',   'Thương hiệu xe điện VinFast'),
('Yadea',   'Trung Quốc', 'Thương hiệu xe máy điện Yadea');

INSERT INTO dvh_motorbike (
  dvh_title, dvh_slug, dvh_price, dvh_year,
  dvh_condition, dvh_fuel_type, dvh_power_watt,
  dvh_odo_km, dvh_color, dvh_location_province,
  dvh_has_paper, dvh_brand_id, dvh_seller_id
) VALUES
('FELIZ S', 'feliz-s', 24000000, 2024,
 'NEW', 'ELECTRIC', 1800,
 0, 'Đen', 'Tp. Hồ Chí Minh',
 1, 1, 1),
('I CUTE', 'i-cute', 14490000, 2025,
 'NEW', 'ELECTRIC', 1200,
 0, 'Trắng', 'Hà Nội',
 1, 2, 2);

INSERT INTO dvh_motorbike_image (dvh_image_url, dvh_is_primary, dvh_motorbike_id)
VALUES
('/img/bikes/feliz_s_1.jpg', 1, 1),
('/img/bikes/feliz_s_2.jpg', 0, 1),
('/img/bikes/i_cute_1.jpg',  1, 2);

INSERT INTO dvh_blog_post (dvh_blog_title, dvh_blog_slug, dvh_blog_content, dvh_author_id)
VALUES
('Kinh nghiệm chọn xe máy điện cho sinh viên',
 'kinh-nghiem-chon-xe-may-dien-cho-sinh-vien',
 'Nội dung demo, cậu tự viết thêm sau.', 1);
