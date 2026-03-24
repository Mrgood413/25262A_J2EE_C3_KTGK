INSERT INTO `role` (`name`) VALUES ('ADMIN')
ON DUPLICATE KEY UPDATE `name` = VALUES(`name`);

INSERT INTO `role` (`name`) VALUES ('PATIENT')
ON DUPLICATE KEY UPDATE `name` = VALUES(`name`);

INSERT INTO `department` (`id`, `name`) VALUES (1, 'Khoa Nội tổng quát')
ON DUPLICATE KEY UPDATE `name` = VALUES(`name`);
INSERT INTO `department` (`id`, `name`) VALUES (2, 'Khoa Ngoại tổng quát')
ON DUPLICATE KEY UPDATE `name` = VALUES(`name`);
INSERT INTO `department` (`id`, `name`) VALUES (3, 'Khoa Tim mạch')
ON DUPLICATE KEY UPDATE `name` = VALUES(`name`);
INSERT INTO `department` (`id`, `name`) VALUES (4, 'Khoa Nhi')
ON DUPLICATE KEY UPDATE `name` = VALUES(`name`);
INSERT INTO `department` (`id`, `name`) VALUES (5, 'Khoa Da liễu')
ON DUPLICATE KEY UPDATE `name` = VALUES(`name`);
INSERT INTO `department` (`id`, `name`) VALUES (6, 'Khoa Tai Mũi Họng')
ON DUPLICATE KEY UPDATE `name` = VALUES(`name`);
INSERT INTO `department` (`id`, `name`) VALUES (7, 'Khoa Răng Hàm Mặt')
ON DUPLICATE KEY UPDATE `name` = VALUES(`name`);
INSERT INTO `department` (`id`, `name`) VALUES (8, 'Khoa Chẩn đoán hình ảnh')
ON DUPLICATE KEY UPDATE `name` = VALUES(`name`);

INSERT INTO `doctor` (`id`, `name`, `image`, `specialty`, `department_id`) VALUES
(1, 'BS. Nguyễn Minh An', 'https://images.unsplash.com/photo-1612349317150-e413f6a5b16d?w=800', 'Nội tổng quát', 1),
(2, 'BS. Trần Thị Thu Hà', 'https://images.unsplash.com/photo-1594824475317-dc2b8e6e2d0f?w=800', 'Ngoại tổng quát', 2),
(3, 'BS. Lê Hoàng Nam', 'https://images.unsplash.com/photo-1537368910025-700350fe46c7?w=800', 'Tim mạch can thiệp', 3),
(4, 'BS. Phạm Ngọc Lan', 'https://images.unsplash.com/photo-1651008376811-b90baee60c1f?w=800', 'Nhi khoa', 4),
(5, 'BS. Đỗ Quỳnh Chi', 'https://images.unsplash.com/photo-1559839734-2b71ea197ec2?w=800', 'Da liễu thẩm mỹ', 5),
(6, 'BS. Vũ Quốc Bảo', 'https://images.unsplash.com/photo-1622253692010-333f2da6031d?w=800', 'Nội tiết', 1),
(7, 'BS. Bùi Thanh Tùng', 'https://images.unsplash.com/photo-1622902046580-2b47f47f5471?w=800', 'Ngoại chấn thương', 2),
(8, 'BS. Đặng Khánh Ly', 'https://images.unsplash.com/photo-1582750433449-648ed127bb54?w=800', 'Tim mạch lâm sàng', 3),
(9, 'BS. Nguyễn Hoài Thương', 'https://images.unsplash.com/photo-1614608682850-e0d6ed316d47?w=800', 'Nhi sơ sinh', 4),
(10, 'BS. Trương Gia Hân', 'https://images.unsplash.com/photo-1579684385127-1ef15d508118?w=800', 'Da liễu dị ứng', 5),
(11, 'BS. Võ Nhật Minh', 'https://images.unsplash.com/photo-1551601651-2a8555f1a136?w=800', 'Tai Mũi Họng tổng quát', 6),
(12, 'BS. Phan Thị Mai', 'https://images.unsplash.com/photo-1550831107-1553da8c8464?w=800', 'Răng trẻ em', 7),
(13, 'BS. Lý Quốc Khánh', 'https://images.unsplash.com/photo-1614436163996-25cee5f54290?w=800', 'Chẩn đoán hình ảnh', 8),
(14, 'BS. Hồ Thanh Vân', 'https://images.unsplash.com/photo-1527613426441-4da17471b66d?w=800', 'Nội thần kinh', 1),
(15, 'BS. Nguyễn Đức Toàn', 'https://images.unsplash.com/photo-1584982751601-97dcc096659c?w=800', 'Ngoại tiêu hóa', 2),
(16, 'BS. Trần Gia Bảo', 'https://images.unsplash.com/photo-1638202993928-7d1138b8f08b?w=800', 'Can thiệp mạch vành', 3),
(17, 'BS. Lâm Bích Ngọc', 'https://images.unsplash.com/photo-1598256989800-fe5f95da9787?w=800', 'Nhi hô hấp', 4),
(18, 'BS. Đỗ Thành Nam', 'https://images.unsplash.com/photo-1597764699510-1a3f2be30766?w=800', 'Da liễu điều trị mụn', 5),
(19, 'BS. Tạ Hữu Nghĩa', 'https://images.unsplash.com/photo-1623854767648-e7bb8009f0db?w=800', 'Viêm xoang - dị ứng', 6),
(20, 'BS. Bùi Ngọc Ánh', 'https://images.unsplash.com/photo-1537368910025-700350fe46c7?w=800', 'Cấy ghép implant', 7)
ON DUPLICATE KEY UPDATE
`name` = VALUES(`name`),
`image` = VALUES(`image`),
`specialty` = VALUES(`specialty`),
`department_id` = VALUES(`department_id`);

