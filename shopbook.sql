-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th10 08, 2021 lúc 05:27 AM
-- Phiên bản máy phục vụ: 10.4.18-MariaDB
-- Phiên bản PHP: 7.4.16

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `shopbook`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `books`
--

CREATE TABLE `books` (
  `id` int(11) NOT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `author` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `description` text COLLATE utf8_unicode_ci NOT NULL,
  `detail` text COLLATE utf8_unicode_ci NOT NULL,
  `number` int(11) NOT NULL,
  `price` int(11) NOT NULL,
  `purchases` int(11) NOT NULL DEFAULT 0,
  `picture` text COLLATE utf8_unicode_ci NOT NULL,
  `cat_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `books`
--

INSERT INTO `books` (`id`, `name`, `author`, `description`, `detail`, `number`, `price`, `purchases`, `picture`, `cat_id`) VALUES
(1, 'Đắc Nhân Tâm', 'Dale Carnegie', 'Đắc nhân tâm – How to win friends and Influence People  của Dale Carnegie là quyển sách nổi tiếng nhất, bán chạy nhất và có tầm ảnh hưởng nhất của mọi thời đại. Tác phẩm đã được chuyển ngữ sang hầu hết các thứ tiếng trên thế giới và có mặt ở hàng trăm quốc gia. \r\n<br />\r\nĐây là quyển sách duy nhất về thể loại self-help liên tục đứng đầu danh mục sách bán chạy nhất (best-selling Books) do báo The New York Times bình chọn suốt 10 năm liền. Riêng bản tiếng Anh của sách đã bán được hơn 15 triệu bản trên thế giới. Tác phẩm có sức lan toả vô cùng rộng lớn – dù bạn đi đến bất cứ nơi đâu, bất kỳ quốc gia nào cũng đều có thể nhìn thấy. Tác phẩm được đánh giá là quyển sách đầu tiên và hay nhất, có ảnh hưởng làm thay đổi cuộc đời của hàng triệu người trên thế giới.', 'Đắc nhân tâm – How to win friends and Influence People  của Dale Carnegie là quyển sách nổi tiếng nhất, bán chạy nhất và có tầm ảnh hưởng nhất của mọi thời đại. Tác phẩm đã được chuyển ngữ sang hầu hết các thứ tiếng trên thế giới và có mặt ở hàng trăm quốc gia. \r\n<br />\r\nĐây là quyển sách duy nhất về thể loại self-help liên tục đứng đầu danh mục sách bán chạy nhất (best-selling Books) do báo The New York Times bình chọn suốt 10 năm liền. Riêng bản tiếng Anh của sách đã bán được hơn 15 triệu bản trên thế giới. Tác phẩm có sức lan toả vô cùng rộng lớn – dù bạn đi đến bất cứ nơi đâu, bất kỳ quốc gia nào cũng đều có thể nhìn thấy. Tác phẩm được đánh giá là quyển sách đầu tiên và hay nhất, có ảnh hưởng làm thay đổi cuộc đời của hàng triệu người trên thế giới.\r\n<br />\r\nKhông còn nữa khái niệm giới hạn, Đắc Nhân Tâm là nghệ thuật thu phục lòng người, là làm cho tất cả mọi người yêu mến mình. Đắc nhân tâm và cái Tài trong mỗi người chúng ta. Đắc Nhân Tâm trong ý nghĩa đó cần được thụ đắc bằng sự hiểu rõ bản thân, thành thật với chính mình, hiểu biết và quan tâm đến những người xung quanh để nhìn ra và khơi gợi những tiềm năng ẩn khuất nơi họ, giúp họ phát triển lên một tầm cao mới. Đây chính là nghệ thuật cao nhất về con người và chính là ý nghĩa sâu sắc nhất đúc kết từ những nguyên tắc vàng của Dale Carnegie.\r\n<br />\r\nQuyển sách Đắc nhắn tâm là cuốn sách “đầu tiên và hay nhất mọi thời đại về nghệ thuật giao tiếp và ứng xử”, quyển sách đã từng mang đến thành công và hạnh phúc cho hàng triệu người trên khắp thế giới.\r\n<br />\r\nĐắc Nhân Tâm là cuốn sách gối đầu giường về đối nhân xử thế.', 46, 79000, 40, 'dac-nhan-tam.jpg', 1),
(2, 'Tây Du Ký', 'Ngô Thừa Ân', 'Tây Du Ký là một trong những tác phẩm kinh điển trong văn học Trung Hoa. Được xuất bản với tác giả giấu tên trong những năm 1590 và không có bằng chứng trực tiếp còn tồn tại để biết tác giả của nó, nhưng tác phẩm này thường được cho là của tác giả Ngô Thừa Ân. Tiểu thuyết thuật lại chuyến đi đến Ấn Độ của nhà sư Huyền Trang (Đường Tam Tạng) đi lấy kinh.', 'Trong tiểu thuyết, Trần Huyền Trang được Quan Âm Bồ Tát bảo đến Tây Trúc (Ấn Độ) thỉnh kinh Phật giáo mang về Trung Quốc. Theo ông là 3 đệ tử - một khỉ đá tên Tôn Ngộ Không, một yêu quái nửa người nửa lợn tên Trư Ngộ Năng và một thủy quái tên Sa Ngộ Tĩnh - họ đều đồng ý giúp ông thỉnh kinh để chuộc tội. Con ngựa Huyền Trang cưỡi cũng là một hoàng tử của Long Vương (Bạch Long Mã).\r\n<br />\r\nNhững chương đầu thuật lại những kì công của Tôn Ngộ Không, từ khi ra đời từ một hòn đá ở biển Hoa Đông, xưng vương ở Hoa Quả Sơn, tầm sư học đạo, đại náo thiên cung, sau đó bị Phật Tổ Như Lai bắt nhốt trong núi Ngũ Hành 500 năm. Truyện kể lại Huyền Trang trở thành một nhà sư ra sao và được hoàng đế nhà Đường gửi đi thỉnh kinh sau khi hoàng đế thoát chết.\r\n<br />\r\nPhần tiếp của câu chuyện kể về các hiểm nguy mà thầy trò Đường Tam Tạng phải đối đầu, trong đó nhiều yêu quái là đồ đệ của các vị Tiên, Phật. Một số yêu tinh muốn ăn thịt Huyền Trang, một số khác muốn cám dỗ họ bằng cách biến thành những mỹ nhân. Tôn Ngộ Không phải sử dụng phép thuật và quan hệ của mình với thế giới yêu quái và Tiên, Phật để đánh bại các kẻ thù nhiều mánh khóe, như Ngưu Ma Vương hay Thiết Phiến Công chúa,...\r\n<br />\r\nCuối cùng khi đã đến cửa Phật, thầy trò họ lại phải đổi Bát vàng của Hoàng đế Đường Thái Tông tặng để nhận được kinh thật. Đây cũng được tính là một khổ nạn cho bốn thầy trò. Khi qua sông Thông Thiên, Tam Tạng gặp lại Lão Rùa năm xưa chở ông qua sông. Khi đang chở Tam Tạng qua giữa sông, Lão Rùa hỏi Tam Tạng rằng ông có hỏi Phật Tổ giúp lão rằng bao giờ lão tu đắc chính quả không, vì Tam Tạng quên hỏi nên bị Lão Rùa hất cả bốn thầy trò lẫn kinh văn xuống sông. Kinh văn bị ướt, sau khi phơi khô một số bị rách. Vì thế mà kinh đến Trung thổ không được toàn vẹn.', 21, 259000, 4, 'tay-du-ky.jpg', 2),
(3, 'Thám tử lừng danh Conan', 'Aoyama Ghoso', 'Thám tử lừng danh Conan là một bộ manga Nhật Bản dành cho lứa tuổi thiếu niên thuộc thể loại trinh thám được vẽ và minh họa bởi Aoyama Gosho. Bộ truyện này ban đầu là những chương truyện nhỏ được đăng trên tuần báo Shōnen Sunday của Shogakukan từ ngày 19 tháng 1 năm 1994 sau đó được đóng thành các tập tankōbon. Tác phẩm xoay quanh câu chuyện về chàng thám tử trung học Kudo Shinichi trong một lần đang điều tra đã bị Tổ chức Áo Đen ép uống thuốc độc, khiến cho cơ thể của cậu bị teo nhỏ và phải sống dưới thân phận là cậu bé Edogawa Conan.', 'Kudo Shinichi là một thám tử trung học rất nổi tiếng, thường xuyên giúp cảnh sát phá án các vụ án khó khăn. Trong một lần khi đang theo dõi một vụ tống tiền, cậu đã bị thành viên của Tổ chức Áo đen phát hiện. Chúng đánh gục Shinichi, làm cậu bất tỉnh và ép cậu uống loại thuốc độc APTX 4869 mà Tổ chức vừa điều chế nhằm bịt đầu mối. Tuy vậy, thứ thuốc ấy không giết chết cậu mà lại khiến cậu teo nhỏ thành một đứa trẻ. Sau đó, cậu tự xưng là Edogawa Conan và được Mori Ran - bạn gái của cậu khi còn là Kudo Shinichi - nhận nuôi và mang về văn phòng thám tử của bố cô là Mori Kogoro. Xuyên suốt series, Conan đã âm thầm hỗ trợ ông Mori phá các vụ án. Đồng thời cậu cũng phải nhập học Tiểu học, nhờ đó mà kết thân với nhiều người và lập ra Đội thám tử nhí.\r\n<br />\r\nVề sau một học sinh tiểu học bất đắc dĩ khác tên là Haibara Ai đã vào học lớp của Conan và tiết lộ rằng cô chính là thành viên của người đã tạo ra loại thuốc APTX 4869 mà Conan đã bị ép uống, vì muốn thoát khỏi Tổ chức Áo đen nên đã uống viên thuốc đó để tự sát. Kết quả là, thay vì chết thì cơ thể Haibara cũng bị teo nhỏ như Conan. Trong vài vụ án liên quan đến Tổ chức Áo đen, Conan đã hỗ trợ các điệp viên của FBI và CIA.\r\n<br />\r\nNăm 2007, Aoyama đã lên kế hoạch cho cái kết cho series nhưng đến hiện tại vẫn chưa ra mắt.', 176, 20000, 48, 'conan.jpg', 4),
(4, 'Thần điêu hiệp lữ', 'Kim Dung', 'Thần điêu hiệp lữ là một tiểu thuyết võ hiệp của Kim Dung, còn có tên khác là Thần Điêu đại hiệp. Tác phẩm được đăng tải lần đầu tiên trên tờ Minh báo vào ngày 20 tháng 5 năm 1959 và liên tục trong ba năm.\r\n\r\nThần điêu hiệp lữ là phần hai trong bộ Xạ điêu tam bộ khúc. Bối cảnh của Thần điêu hiệp lữ là vào cuối thời Nam Tống, khi quân Mông Cổ đã lớn mạnh, tiêu diệt hầu hết châu Á, châu Âu, đang trực tiếp uy hiếp an nguy của Nam Tống.\r\n\r\nCâu chuyện xoay quanh tình yêu của hai nhân vật chính là Dương Quá và Tiểu Long Nữ giữa những cuộc chiến tang thương đẫm máu cả trên giang hồ lẫn chiến trường.', 'Lý Mạc Sầu (trong bản đầu tiên có tên là Lý Mạc Thu) tìm cách giết hại gia đình Lục Lập Đỉnh. Nguyên nhân bởi những mối hận tình trong quá khứ của người anh trai ông và chị dâu là Lục Triển Nguyên và Hà Nguyên Quân. Sau đó hai vợ chồng Võ Tam Thông lại đứng ra bảo vệ con gái (Lục Vô Song) và cháu gái (Trình Anh) của Lục Lập Đỉnh, vì Lý Mạc Sầu định giết luôn 2 cô bé (Lục Vô Song bị thương ở chân). Lúc đó cô bé Quách Phù và Kha Trấn Ác (sư phụ của Quách Tĩnh) trên đường đi tìm tung tích Hoàng Dược Sư khi đi ngang qua Giang Nam cũng ra tay giúp sức đánh Lý Mạc Sầu.\r\n<br />\r\nQuách Phù sai đôi chim điêu tấn công Lý Mạc Sầu. Lúc đó thì cậu bé Dương Quá xuất hiện cố gắng giải nguy cho 2 cô bé Trình Anh và Lục Vô Song. Lý Mạc Sầu định phóng ngân châm hạ độc Dương Quá nhưng Hoàng Dược Sư xuất hiện kịp thời nên không thể ra tay hạ Dương Quá, chỉ kịp bắt Lục Vô Song bỏ đi, Hoàng Dược Sư cứu được Trình Anh. Trong lúc đuổi theo Lý Mạc Sầu, Dương Quá do nghịch châm nên đã bị trúng độc. Sau đó ông bỏ đi trước khi vợ chồng Quách Tĩnh, Hoàng Dung đến làm phiền mình.\r\n<br />\r\nÂu Dương Phong xuất hiện, luôn trong tư thế trồng cây chuối vì bị tẩu hỏa nhập ma. Sau khi ép Dương Quá nhận làm cha nuôi, ông cứu chữa, truyền Cáp Mô Công cho cậu bé rồi trốn đi. Sau này, Dương Quá đã gặp lại Âu Dương Phong ở đỉnh Hoa Sơn khi Quách Tĩnh và Hoàng Dung xuất hiện. Hai vợ chồng gặp lại con gái Quách Phù và Kha Trấn Ác rồi họ giúp chữa chạy cho Dương Quá.\r\n<br />\r\nDương Quá một mặt theo gia đình Quách Tĩnh - Hoàng Dung, một mặt vẫn bí mật gặp gỡ và giúp đỡ Âu Dương Phong và được ông ta truyền thụ \"Cáp mô công\" (Một số bản dịch là \"Hàm mô công\").\r\n<br />\r\nQuách Tĩnh - Hoàng Dung đưa Dương Quá và hai người con trai của Võ Tam Thông (tên Vũ Tu Văn và Vũ Đôn Nhu) ra đảo Đào Hoa. Càng lớn, Dương Quá càng giống cha mình (Dương Khang) khiến Hoàng Dung lo sợ lẫn nghi ngờ Dương Quá. Hoàng Dung không truyền thụ võ công cho cậu bé mà chỉ dạy cậu ta sách Luận ngữ và các đạo đức, lễ nghĩa làm người. Trong một lần đánh nhau với anh em họ Võ, Dương Quá sử dụng \"Cáp mô công\" đả thương 2 anh em, bị Hoàng Dung phát giác. Do vậy, Quách Tĩnh phải đưa Dương Quá lên núi Chung Nam xin gia nhập phái Toàn Chân, mời Triệu Chí Kính làm sư phụ.\r\n<br />\r\nTuy nhiên Dương Quá rất ghét tính nhỏ nhen của Triệu, và Triệu chỉ truyền khẩu quyết chứ không dạy võ công cho Dương Quá. Vì vậy, trong cuộc tỉ thí cuối năm Dương Quá không biết cách thi triển võ công của phái Toàn Chân, và bất đắc dĩ dùng Hàm Mô Công làm bị thương một vài đệ tử lớn hơn. Dương Quá bỏ chạy sang Cổ Mộ và được Tiểu Long Nữ cứu giúp...', 18, 399000, 2, 'than-dieu-hiep-lu.jpg', 3),
(5, 'Thế Giới Phẳng', 'Thomas Friedman', 'Thế giới phẳng (tiếng Anh: The World is Flat) là một tác phẩm của Thomas Friedman, một biên tập viên chuyên mục ngoại giao và kinh tế của tạp chí New York Times có những tác phẩm và công trình nghiên cứu về vấn đề toàn cầu hoá rất thành công: Nóng, Phẳng, Chật, Từ Beirut đến Jerusalem, Chiếc Lexus và cây ôliu, Từng là bá chủ.\r\n<br />\r\nNăm 2005,cuốn sách này được trao giải thưởng Cuốn sách hay nhất năm do Financial Times và Goldman Sachs bình chọn và Thomas Friedman cũng được bình chọn là một trong những nhà lãnh đạo xuất sắc nhất Hoa Kỳ.', 'Thế giới phẳng (tiếng Anh: The World is Flat) là một tác phẩm của Thomas Friedman, một biên tập viên chuyên mục ngoại giao và kinh tế của tạp chí New York Times có những tác phẩm và công trình nghiên cứu về vấn đề toàn cầu hoá rất thành công: Nóng, Phẳng, Chật, Từ Beirut đến Jerusalem, Chiếc Lexus và cây ôliu, Từng là bá chủ.\r\n<br />\r\nNăm 2005,cuốn sách này được trao giải thưởng Cuốn sách hay nhất năm do Financial Times và Goldman Sachs bình chọn và Thomas Friedman cũng được bình chọn là một trong những nhà lãnh đạo xuất sắc nhất Hoa Kỳ.\r\n<br />\r\nHiện nay \"thế giới phẳng\" đã trở thành thuật ngữ quen thuộc chỉ sự phát triển toàn cầu hóa từ những năm đầu của thế kỷ XXI khi mười nhân tố lớn liên quan đến kinh tế và khoa học kỹ thuật trên thế giới cùng nhau tác động, khiến cho các mô hình xã hội, chính trị và xã hội đã bị thay đổi và thế giới trở nên phẳng hơn bao giờ hết khi sự tiếp xúc giữa các cá nhân trở nên dễ dàng và chặt chẽ hơn trước.\r\n<br />\r\nBản tiếng Việt - Thế giới phẳng: Tóm lược Lịch sử Thế giới Thế kỷ XXI - do Nguyễn Quang A, Nguyễn Hồng Quang, Vũ Duy Thành, Lã Việt Hà, Lê Hồng Vân, Hà Thị Thanh Huyền dịch và hiệu đính, Nhà xuất bản Trẻ xuất bản tháng 8 năm 2006 dưới hình thức sách bìa mềm dày 820 trang. Bản ebook Nhà xuất bản Trẻ 1399 trang. Sách được dịch và xuất bản bằng tiếng Việt dưới sự hỗ trợ về tài chính của Phòng Thông tin - Văn hóa. Tổng lãnh sự quán Hoa Kỳ tại Thành phố Hồ Chí Minh.', 39, 69000, 1, 'the-gioi-phang.jpg', 9),
(6, 'Cảm Xúc Là Kẻ Thù Số 1 Của Thành Công', 'TS Lê Thẩm Dương', 'Tuyển tập Cảm xúc là kẻ thù số 1 của thành công là cuốn sách đầu tiên trong tủ sách hướng nghiệp do báo Sinh Viên Việt Nam – Hoa Học Trò và Trung tâm tiếng Anh Langmaster thành lập. Cuốn sách gồm những bài nói chuyện và bài viết nổi tiếng của TS Lê Thẩm Dương có tác động lớn đến giới trẻ trong thời gian vừa qua liên quan đến việc hướng nghiệp, chọn nghề nghiệp, học tập, nghiên cứu, lao động, chọn vợ/chồng, thái độ sống tích cực... Sách được chia thành 4 phần: Thất nghiệp là tín hiệu tuyệt vời; Cái gì không mua được bằng tiền?;  Chọn vợ/chồng theo hàm số hay biến số?; Những bài học cuộc sống TS Lê Thẩm Dương khuyên đọc.', 'Tuyển tập Cảm xúc là kẻ thù số 1 của thành công còn có postcard tuyệt đẹp minh hoạ cho những câu nói nổi tiếng của TS Lê Thẩm Dương.\r\n<br />\r\nĐặc biệt, Tuyển tập Cảm xúc là kẻ thù số 1 của thành công có sự tương tác rất cao với bạn đọc: Tên cuốn sách, tên các phần của cuốn sách, bố cục của cuốn sách, bìa sách… được lựa chọn sau khi đã tham khảo ý kiến của đông đảo bạn đọc trên mạng xã hội.\r\n<br />\r\nTS Lê Thẩm Dương giảng dạy tại Học viện Ngân hàng Hà Nội từ năm 1982, sau đó chuyển vào Đại học Ngân hàng TP.HCM. Hiện ông đang là Trưởng khoa Quản trị Kinh doanh, ĐH Ngân hàng TP.HCM. Ông cũng là giảng viên chính chương trình cấp chứng chỉ hành nghề của Uỷ ban Chứng khoán Nhà nước, giảng viên thỉnh giảng của nhiều tập đoàn, trường Đại học, chương trình đào tạo. Ông là khách mời thường xuyên của nhiều diễn đàn cấp quốc gia và khu vực, nhiều chương trình truyền hình uy tín…TS Lê Thẩm Dương nổi tiếng trên mạng với những bài giảng \"gây bão\". \r\n<br />\r\nNhà báo Nguyễn Tuấn Anh,Thư ký Toà soạn báo Sinh Viên Việt Nam, Trưởng ban biên soạn cuốn sách cho biết:\r\n<br />\r\n\"Nhiều người hâm mộ TS Lê Thẩm Dương và cũng có không ít người không thích ông. Nhưng có một điều chắc chắn là TS Lê Thẩm Dương được đông đảo học sinh, sinh viên yêu thích đặc biệt trên mạng. Ông có kiến thức rất rộng và biết cách làm đơn giản, gần gũi những vấn đề tưởng như rất phức tạp. Nhiều vấn đề của đời sống thường nhật như chọn người yêu, vợ/chồng, nghề nghiệp... lại được ông giải thích dưới góc độ toán học, kinh tế học hết sức thuyết phục. Qua các cuộc trò truyện trực tiếp cùng ông, chúng tôi còn nhận thấy ông là người rất tâm huyết với giới trẻ, muốn truyền cho họ phương pháp tư duy, học tập và đặc biệt là cảm hứng sống, hướng họ đến với các giá trị chân, thiện mỹ, đặc biệt trong thời điểm Việt Nam đang hội nhập rất nhanh với khu vực và thế giới\".', 49, 119000, 1, 'cam-xuc-la-ke-thu.jpg', 1),
(7, 'Cha Giàu Cha Nghèo', 'Robert Kiyosaki và Sharon Lechter', 'Bạn sẽ làm gì khi được cả hai người cha truyền dạy cho mình về chủ đề tiền bạc và sự lựa chọn cách sống trong đời. Một người là bố ruột, còn người kia là bố người bạn thân nhất. Một người có nền học vấn rất cao trong khi người kia chỉ học tới trung học, nhưng cả hai đều thành công trong sự nghiệp và có ảnh hưởng đến người khác.', 'Nội dung của cuốn sách này nói về nguyên do khiến tác giả Robert Kiyosaki được như ngày hôm nay chính là đúc kết từ những quan điểm và ý kiến trái chiều từ hai người cha thân thiết của mình. \r\n<br />\r\nTrong đó, người cha ruột của ông là người tuy nghèo nhưng lại có học thức, trong khi người cha nuôi còn lại tuy chỉ học đến lớp 8, nhưng lại là người giàu có và có khối tài sản kếch xù.\r\n<br />\r\nChính vì xuất thân và hoàn cảnh có phần khác biệt này đã khiến cho tác giả cảm thấy phân vân và khó xử vì người cha nào cũng đều đưa ra những lời khuyên trái ngược dành cho ông.\r\n<br />\r\nĐối với người cha ruột thì cho rằng: “Ham mê tiền bạc là nguồn gốc của mọi điều xấu”, còn người cha nuôi kia thì lại phản bác rằng: “Thiếu thốn tiền bạc là nguồn gốc của mọi điều xấu”.', 57, 39000, 3, 'cha-giau-cha-ngheo.jpg', 1),
(8, 'Nhà Giả Kim', 'Paulo Coelho', 'Tất cả những trải nghiệm trong chuyến phiêu du theo đuổi vận mệnh của mình đã giúp Santiago thấu hiểu được ý nghĩa sâu xa nhất của hạnh phúc, hòa hợp với vũ trụ và con người.\r\n<br />\r\nTiểu thuyết Nhà giả kim của Paulo Coelho như một câu chuyện cổ tích giản dị, nhân ái, giàu chất thơ, thấm đẫm những minh triết huyền bí của phương Đông. Trong lần xuất bản đầu tiên tại Brazil vào năm 1988, sách chỉ bán được 900 bản. Nhưng, với số phận đặc biệt của cuốn sách dành cho toàn nhân loại, vượt ra ngoài biên giới quốc gia, Nhà giả kim đã làm rung động hàng triệu tâm hồn, trở thành một trong những cuốn sách bán chạy nhất mọi thời đại, và có thể làm thay đổi cuộc đời người đọc.', 'Tất cả những trải nghiệm trong chuyến phiêu du theo đuổi vận mệnh của mình đã giúp Santiago thấu hiểu được ý nghĩa sâu xa nhất của hạnh phúc, hòa hợp với vũ trụ và con người.\r\n<br />\r\nTiểu thuyết Nhà giả kim của Paulo Coelho như một câu chuyện cổ tích giản dị, nhân ái, giàu chất thơ, thấm đẫm những minh triết huyền bí của phương Đông. Trong lần xuất bản đầu tiên tại Brazil vào năm 1988, sách chỉ bán được 900 bản. Nhưng, với số phận đặc biệt của cuốn sách dành cho toàn nhân loại, vượt ra ngoài biên giới quốc gia, Nhà giả kim đã làm rung động hàng triệu tâm hồn, trở thành một trong những cuốn sách bán chạy nhất mọi thời đại, và có thể làm thay đổi cuộc đời người đọc.\r\n<br />\r\n“Nhưng nhà luyện kim đan không quan tâm mấy đến những điều ấy. Ông đã từng thấy nhiều người đến rồi đi, trong khi ốc đảo và sa mạc vẫn là ốc đảo và sa mạc. Ông đã thấy vua chúa và kẻ ăn xin đi qua biển cát này, cái biển cát thường xuyên thay hình đổi dạng vì gió thổi nhưng vẫn mãi mãi là biển cát mà ông đã biết từ thuở nhỏ. Tuy vậy, tự đáy lòng mình, ông không thể không cảm thấy vui trước hạnh phúc của mỗi người lữ khách, sau bao ngày chỉ có cát vàng với trời xanh nay được thấy chà là xanh tươi hiện ra trước mắt. ‘Có thể Thượng đế tạo ra sa mạc chỉ để cho con người biết quý trọng cây chà là,’ ông nghĩ.”', 27, 79000, 3, 'nha-gia-kim.jpg', 10),
(9, 'Đời Ngắn Đừng Ngủ Dài', 'Robin Sharma', '“Mọi lựa chọn đều giá trị. Mọi bước đi đều quan trọng. Cuộc sống vẫn diễn ra theo cách của nó, không phải theo cách của ta. Hãy kiên nhẫn. Tin tưởng. Hãy giống như người thợ cắt đá, đều đặn từng nhịp, ngày qua ngày. Cuối cùng, một nhát cắt duy nhất sẽ phá vỡ tảng đá và lộ ra viên kim cương. Người tràn đầy nhiệt huyết và tận tâm với việc mình làm không bao giờ bị chối bỏ. Sự thật là thế.”\r\n<br />\r\nBằng những lời chia sẻ thật ngắn gọn, dễ hiểu về những trải nghiệm và suy ngẫm trong đời, Robin Sharma tiếp tục phong cách viết của ông từ cuốn sách Điều vĩ đại đời thường để mang đến cho độc giả những bài viết như lời tâm sự, vừa chân thành vừa sâu sắc.', 'Cuốn sách Đời ngắn đừng ngủ dài chứa đựng nhiều câu chuyện khác nhau, nhưng điểm chung là chúng đều truyền cảm hứng và động lực cho người đọc rất thực tế, giúp chúng ta nhận ra rằng, trong cuộc sống bộn bề ngày nay, đôi khi chúng ta cũng nên dành thời gian để nghỉ ngơi và sống chậm lại, thay vì cứ cố gắng hết sức dù cho bản thân mình đã và đang rất mệt mỏi.\r\n<br />\r\nTrong cuộc sống của mỗi người, đôi khi sẽ cảm thấy bế tắc khi gặp một vấn đề nào đó, những lúc như vậy, ta chợt nhận ra, những sự cố gắng, những mục tiêu mơ ước của mình đang trôi về đâu, có còn ý nghĩa gì nữa hay không và liệu chúng ta có đang thực hiện đúng hướng?\r\n<br />\r\nThay vì chúng ta đang tự chất vấn chính bản thân mình, tại sao chúng ta không tự mở lối đi riêng cho mình bằng cách tự tìm tòi, khám phá bản thân đang thật sự cần gì và muốn gì. Khi đọc cuốn sách này, bạn sẽ tự nhận thêm cho bản thân rất nhiều bài học quý giá. ', 22, 79000, 3, 'doi-ngan-dung-ngu-dai.jpg', 1),
(10, 'Chí Phèo', 'Nam Cao', 'Truyện ngắn Chí Phèo, nguyên có tên là Cái lò gạch cũ; khi in thành sách lần đầu năm 1941, Nhà Xuất bản Đời mới – Hà Nội tự ý đổi tên là Đôi lứa xứng đôi. Đến khi in lại trong tập Luống cày (do Hội Văn hóa cứu quốc xuất bản, Hà Nội, 1946), Nam Cao đặt lại tên là Chí Phèo.\r\n\r\nNam Cao bắt đầu sáng tác từ năm 1936, nhưng đến tác phẩm Chí Phèo, nhà văn mới khẳng định được tài năng của mình. Chí Phèo là một kiệt tác trong văn xuôi Việt Nam hiện đại, một truyện ngắn có giá trị hiện thực và nhân đạo sâu sắc, mới mẻ, chứng tỏ trình độ nghệ thuật bậc thầy của một nhà văn lớn.', 'Ở làng Vũ Đại. Một sáng tinh sương, một anh thả ống lươn nhặt được đứa bé mới đẻ xám ngắt đùm trong cái váy đụp vứt ở lò gạch cũ. Anh ta rước lấy đem về cho người đàn bà góa mù, bà này bán lại cho bác phó cối. Khi bác phó cối chết, hắn bơ vơ, mãi năm 20 tuổi hắn làm canh điền cho Bá Kiến. Vợ ba Bá Kiến bắt Chí bóp chân và xem Chí như một vật lợi dụng. Bá Kiến biết được và thế là Chí bị người ta giải huyện... Đi tù bảy, tám năm sau hắn trở lại làng, mặt mày trông khác hẳn, trông gớm chết trông như một thằng săng đá! Về hôm trước thì hôm sau hắn đã ngồi ở chợ uống rượu với ăn thịt chó từ trưa đến xế chiều, rồi xách vỏ chai đến thẳng nhà Bá Kiến gây sự. Xô xát với Lý Cường, hắn đập vỏ chai, rạch mặt kêu trời ăn vạ. Sau cái vụ Năm Thọ, Binh chức, cụ Bá róc đời xử nhũn với Chí Phèo. Cụ mời hắn vào nhà, giết gà đãi rượu, lúc hắn ra về còn đãi một đống bạc uống thuốc.\r\n<br />\r\nBốn hôm sau, Chí Phèo đốt quán bà bán rượu... Hắn mang theo một con dao nhọn đến xin Cụ Bá đi ở tù. Chỉ một câu nói khích, cụ đã sai được Chí Phèo đến nhà đội Tảo đòi 50 đồng bạc nợ cho cụ. Chẳng phải giao tranh đổ máu, hắn đã đòi được nợ đem về. Cụ bá cho hắn 5 đồng và bán cho hắn 5 sào vườn ngoài bãi sông mới cắm thuế của một người làng. Năm đó Chí 27 hay 28 tuổi, hắn bỗng thành có nhà. Hắn trở thành anh đầy tớ chân tay mới của Bá Kiến, chuyên đâm thuê chém mướn, rạch mặt ăn vạ. Hắn đập đầu, rạch mặt, chửi bới, dọa nạt trong lúc say, uống rượu trong lúc say, để rồi say mãi, say vô tận. Hắn chửi trời, chửi cả làng Vũ Đại, chửi con mẹ chết tiệt nào đẻ ra hắn cho hắn khổ. Năm đó hắn ngoài 40, cái mặt như mặt một con vật lạ. Cả làng Vũ Đại đều sợ hắn một khi hắn đi qua trước mặt.', 42, 199000, 8, 'chi-pheo.jpg', 6),
(11, 'Ỷ Thiên Đồ Long Ký', 'Kim Dung', 'Ỷ Thiên Đồ Long ký, còn được dịch ra tiếng Việt là Cô gái Đồ Long ( chính là Chu Chỉ Nhược vì cô đã khám phá ra được bí mật), là một tiểu thuyết võ hiệp của nhà văn Kim Dung. Đây là cuốn cuối cùng trong bộ tiểu thuyết Xạ điêu tam khúc. Tiểu thuyết được Hương Cảng Thương báo xuất bản lần đầu năm 1961 tại Hồng Kông và sau đó bản tiếng Việt được Nhà xuất bản Văn học xuất bản tại Việt Nam.', 'Bối cảnh tiểu thuyết lấy vào cuối thời nhà Nguyên, hơn 100 năm sau sự kiện trên đỉnh Hoa Sơn trong Thần điêu hiệp lữ, lúc này nhà Nguyên đang bị suy yếu bởi các cuộc khởi nghĩa và vì sự xa hoa lãng phí của triều đình. Truyện xoay quanh Trương Vô Kỵ và mối tình phức tạp với 4 cô gái, bên cạnh đó là những âm mưu thủ đoạn đầy máu tanh trên giang hồ nhằm chiếm đoạt 2 món báu vật là Đồ Long đao và Ỷ Thiên kiếm, với lời đồn nếu tìm được bí mật trong đao kiếm sẽ hiệu triệu được thiên hạ. Truyện kết thúc với sự sụp đổ của nhà Nguyên, người Mông Cổ phải rút về thảo nguyên phía bắc cùng với sự thành lập của nhà Minh bởi Minh Thái Tổ Chu Nguyên Chương.\r\n<br />\r\nTrương Vô Kỵ được xem là có nội tâm phức tạp hơn so với Quách Tĩnh và Dương Quá trong khi không có những phẩm chất anh hùng như hai người kia. Điều này làm cho nhân vật trở nên thực tế hơn. Anh là người hay bị tác động bởi người khác, hay tự đặt mình vào thế đã rồi và anh đã không thể thoát khỏi cái hiện thực tàn khốc đó. Trương Vô Kỵ vào cuối truyện xem Triệu Mẫn là tình yêu của đời mình, tuy nhiên khó có thể biết được chính xác ai mới là anh yêu nhất, kể cả chính anh. Mặc dù có võ công cao cường, gần như vô địch đương thời, chỉ thua có Trương Tam Phong và là một người chính trực khảng khái, người có thể truyền cảm hứng cho người khác, Trương Vô Kỵ không hề có những tố chất cần thiết của một nhà lãnh đạo là lòng ham muốn mãnh liệt đối với quyền lực và tâm kế để duy trì quyền lực. Chính vì vậy vào cuối truyện, anh đã bị Chu Nguyên Chương lừa nên bỏ đi cùng Triệu Mẫn và quyền lực Minh giáo dần rơi vào tay Chu Nguyên Chương, làm bước đệm cho y tranh thiên hạ.', 25, 240000, 22, 'y-thien-do-long-ky.jpg', 3),
(12, 'Naruto', 'Kishimoto Masashi', 'Naruto là loạt manga Nhật Bản bằng văn bản và minh họa bởi tác giả Kishimoto Masashi, đã được dựng thành anime (phim hoạt hình Nhật). Nhân vật chính là Uzumaki Naruto, một thiếu niên ồn ào, hiếu động, một ninja luôn muốn tìm cách khẳng định mình để được mọi người công nhận, rất muốn trở thành Hokage (Hỏa Ảnh) - người lãnh đạo ninja cả làng, được tất cả mọi người kính trọng. Kishimoto ban đầu đã phác hoạ Naruto trong một ấn bản Akamaru Jump vào tháng 8 năm 1997. Sự khác biệt ở chỗ Naruto là chủ thể của Hồ Ly Chín Đuôi được cha ruột của mình phong ấn vào người, và câu chuyện được đặt trong bối cảnh hiện đại hơn. Phiên bản ban đầu của Naruto này đã có khả năng biến thành một phụ nữ quyến rũ – nhưng khi cậu ta làm vậy, một cái đuôi cáo xuất hiện. Kishimoto sau đó mới sáng tác lại câu chuyện thành hiện trạng, và được phát hành lần đầu bởi Shueisha vào năm 1999 trong ấn bản thứ 43 của tạp chí Tuần san thiếu niên Jump tại Nhật.', 'Mười hai năm trước khi bộ truyện này bắt đầu, con Hồ Ly Chín Đuôi đã tấn công ngôi làng ninja làng Lá. Đủ sức mạnh để khiến sóng thần nổi dậy và san bằng núi non chỉ với một trong số chín cái đuôi của nó, nó đã gây nên sự hỗn loạn tột cùng và giết chóc nhiều người, cho tới khi người lãnh đạo của làng Lá – ngài Hokage Đệ Tứ (Namikaze Minato) đã hi sinh để phong ấn con quái vật bằng thuật cấm: Kin Jutsu Ogi! \"Shiki Fuin\" (Thi Quỷ Phong Tận - Một thuật cấm phải đánh đổi bằng tính mạng) vào người con trai mình Naruto khi cậu ta mới sinh. Hokage Đệ Tứ, người được vinh danh vì đã phong ấn con yêu hồ li, mong muốn Naruto được người dân tôn trọng vì đã là thân xác chứa đựng con quái vật.\r\n<br />\r\nThứ bậc của các nguyên tố chaxra.\r\nDù vậy, làng Lá lại xa lánh cậu ta, đối xử với Naruto như chính cậu ta là con yêu hồ và ngược đãi cậu trong suốt thời thơ ấu của cậu, đến mức Naruto đã từng phải bật khóc. Một quy định được ban ra từ Hokage Đệ Tam đã ngăn cấm mọi người không được bàn luận hay đề cập đến vụ tấn công của con yêu hồ với bất kì ai, thậm chí cả con cái mình. Dù sao, điều này không ngăn cản họ khỏi việc đối xử với cậu ta như một kẻ ngoài lề và vì vậy cậu lớn lên mồ côi, không có bạn bè, gia đình, hay sự thừa nhận nào. Cậu ta không thể bắt mọi người làm bạn với cậu, vậy nên cậu tìm ra cách được công nhận và chú ý duy nhất – qua các trò phá phách và nghịch ngợm.', 35, 20000, 30, 'naruto.jpg/Faker-167682592682800.jpg/Faker1-167682603971900.jpg/Faker2-167682623158000.jpg', 4),
(13, 'Tam Quốc Diễn Nghĩa', 'La Quán Trung', 'Tam quốc diễn nghĩa nguyên tên là Tam quốc chí thông tục diễn nghĩa, là một tiểu thuyết lịch sử Trung Quốc được La Quán Trung viết vào thế kỷ 14 kể về thời kỳ hỗn loạn Tam Quốc (190–280) với 120 chương hồi, theo phương pháp bảy thực ba hư (bảy phần thực ba phần hư cấu). Tiểu thuyết này được xem là một trong Tứ đại danh tác của văn học Trung Quốc.', 'Truyện lấy bối cảnh vào thời suy vi của nhà Hán khi mà những hoàng đế cuối cùng của nhà Hán quá tin dùng giới hoạn quan mà gạt bỏ những bề tôi trung trực. Triều đình ngày càng bê tha, hư nát, khiến kinh tế suy sụp và an ninh bất ổn. Đến đời Hán Linh Đế, năm 184, loạn giặc Khăn Vàng nổ ra do Trương Giác, một người đã học được nhiều ma thuật và bùa phép chữa bệnh, cầm đầu. Sau đó là sự xuất hiện của ba anh em Lưu Bị, Quan Vũ và Trương Phi, cả ba người đều muốn dẹp loạn yên dân nên đã kết nghĩa với nhau ở vườn đào.\r\n<br />\r\nHà Tiến chỉ huy các quan đại thần đi trấn áp chẳng mấy chốc dập tắt cuộc khởi nghĩa Khăn Vàng. Hà Tiến là anh rể vua và nhờ đó mà nhậm được chức đại tướng quân của triều đình. Sau khi Hán Linh Đế mất vào tháng 5 năm 189, Hà Tiến lập Hán Thiếu Đế kế vị. Điều đó khiến Đổng thái hậu (mẹ của Hán Linh Đế) không hài lòng. Hà Tiến phải đầu độc giết bà ta để trừ họa. Sau đó Hà Tiến lại có mâu thuẫn với bọn hoạn quan, đặc biệt là 2 hoạn quan Trương Nhượng và Kiển Thạc nên muốn giết sạch hết bọn chúng để có uy quyền tuyệt đối trong triều. Hà Tiến lấy chuyện này bàn với Viên Thiệu. Viên Thiệu khuyên Hà Tiến nên triệu tập các trấn khắp cả nước vào Lạc Dương diệt hoạn quan. Tiến phê chuẩn ngay, kêu gọi quân đội ở các trấn vào cung giết hoạn quan. Hành động này của Hà Tiến bị Tào Tháo phản đối và cho rằng ông là kẻ làm loạn thiên hạ. Bọn hoạn quan về sau cũng biết tin này, cũng lo đối phó trước. Tháng 8 năm 189, khi mà mưu đồ diệt hoạn quan của Hà Tiến chưa thành thì ông lại mắc mưu của đám hoạn quan, bị chúng lừa vào cung Trường Lạc và giết chết. Liền sau đó các quan đại thần do Viên Thiệu cầm đầu đem quân vào cung giết sạch đám hoạn quan này, báo thù cho Hà Tiến.', 30, 350000, 24, 'tam-quoc-dien-nghia.jpg', 2),
(14, 'Harry Potter', 'J. K. Rowling', 'Harry Potter là tên của series tiểu thuyết huyền bí gồm bảy phần của nữ nhà văn Anh Quốc J. K. Rowling. Bộ truyện viết về những cuộc phiêu lưu phù thủy của cậu bé Harry Potter cùng hai người bạn thân là Ronald Weasley và Hermione Granger, lấy bối cảnh tại Trường Phù thủy và Pháp sư Hogwarts nước Anh. Những cuộc phiêu lưu tập trung vào cuộc chiến của Harry Potter trong việc chống lại tên Chúa tể hắc ám Voldemort – người có tham vọng muốn trở nên bất tử, thống trị thế giới phù thủy, nô dịch hóa những người phi pháp thuật và tiêu diệt những ai cản đường hắn, đặc biệt là Harry Potter.\r\n<br />\r\nBộ truyện kết hợp nhiều thể loại, bao gồm cả giả tưởng và giai đoạn tuổi mới lớn (với các yếu tố huyền bí, kinh dị, phiêu lưu và lãng mạn), nhiều ý nghĩa về văn hóa và tư liệu tham khảo. Cũng theo tác giả J. K. Rowling, chủ đề chính xuyên suốt là Cái chết.', 'Câu chuyện mở ra với bữa tiệc mừng của một thế giới phù thủy mà nhiều năm nay đã bị khủng hoảng bởi Chúa tể Hắc ám Voldemort. Đêm trước đó, Voldemort đã tìm thấy nơi sinh sống của gia đình Potter tại thung lũng Godric và giết chết Lily cũng như James Potter vì một lời tiên tri dự đoán sẽ ảnh hưởng đến Voldemort rằng hắn sẽ bị đánh bại bởi \"đứa trẻ sinh ra khi tháng bảy tàn đi\" mà Voldemort tin đứa trẻ là Harry Potter. Tuy vậy, khi hắn định giết Harry, Lời nguyền Chết chóc Avada Kedavra đã bật lại, Voldemort bị tiêu diệt, chỉ còn là một linh hồn, không sống mà cũng chẳng chết. Trong lúc đó, Harry bị lưu lại một vết sẹo hình tia chớp đặc biệt trên trán mình, dấu hiệu bên ngoài duy nhất từ lời nguyền. Cậu là người sống sót duy nhất khi trúng phải lời nguyền này, và việc đánh bại Voldemort đầy bí ẩn đó đã được cộng đồng phù thủy phong cho cậu danh hiệu \"Đứa bé sống sót\", \"Đứa bé vẫn sống\".\r\n<br />\r\nVào đêm tiếp theo, một phù thủy nhưng không được sử dụng pháp thuật (Rubeus Hagrid) mang Harry tới gia đình dì dượng của cậu, nơi sẽ là chỗ ở của cậu trong 10 năm sắp tới. Cậu bé Harry mồ côi sau đó được nuôi lớn bởi gia đình Dursley - những người không phải là phù thủy và luôn tìm mọi cách để ngăn cản cậu đến với quyền năng phép thuật. Dì và dượng của Harry luôn hắt hủi cậu, vì họ rất ghét phép thuật nên dì và dượng của Harry đã giấu tất cả những vật tài sản phép thuật mà cậu thừa kế cũng như luôn phạt cậu thật nặng khi có điều gì đó lạ thường xảy ra.', 10, 499000, 10, 'harry-potter.jpg', 10),
(15, 'Quẳng Gánh Lo Đi Và Vui Sống', 'Dale Carnegie', 'Quẳng Gánh Lo Đi Và Vui Sống là cuốn sách mà cái tên đã nói lên tất cả nội dung chuyển tải trên những trang giấy.\r\n<br />\r\nBất kỳ ai đang sống đều sẽ có những lo lắng thường trực về học hành, công việc, những hoá đơn, chuyện nhà cửa,... Cuộc sống không dễ dàng giải thoát bạn khỏi căng thẳng, ngược lại, nếu quá lo lắng, bạn có thể mắc bệnh trầm cảm. Quẳng Gánh Lo Đi Và Vui Sống khuyên bạn hãy khóa chặt dĩ vãng và tương lai lại để sống trong cái phòng kín mít của ngày hôm nay. Mọi vấn đề đều có thể được giải quyết, chỉ cần bạn bình tĩnh và xác định đúng hành động cần làm vào đúng thời điểm.', 'Quẳng Gánh Lo Đi Và Vui Sống là cuốn sách mà cái tên đã nói lên tất cả nội dung chuyển tải trên những trang giấy.\r\n<br />\r\nBất kỳ ai đang sống đều sẽ có những lo lắng thường trực về học hành, công việc, những hoá đơn, chuyện nhà cửa,... Cuộc sống không dễ dàng giải thoát bạn khỏi căng thẳng, ngược lại, nếu quá lo lắng, bạn có thể mắc bệnh trầm cảm. Quẳng Gánh Lo Đi Và Vui Sống khuyên bạn hãy khóa chặt dĩ vãng và tương lai lại để sống trong cái phòng kín mít của ngày hôm nay. Mọi vấn đề đều có thể được giải quyết, chỉ cần bạn bình tĩnh và xác định đúng hành động cần làm vào đúng thời điểm.\r\n<br />\r\nNói thì có vẻ dễ nhưng những vấn đề liên quan đến các trạng thái tinh thần chẳng bao giờ dễ giải quyết. Chấm dứt lo lắng là điều không thể nhưng bớt đi sự lo lắng thì có thể, chỉ cần bạn đủ quyết tâm.\r\n<br />\r\nQuẳng Gánh Lo Đi Và Vui Sống khuyên bạn những cách để giảm thiểu lo lắng rất đơn giản như chia sẻ nó với người khác, tìm cách giải quyết vấn đề, quên tất cả những điều lo lắng nằm ngoài tầm tay,... Cố gắng thực tập điều này hàng ngày và trong cuộc sống chắc hẳn bạn sẽ thành công, có thể, không được như bạn muốn, nhưng chỉ cần bớt đi một chút phiền muộn thì cuộc sống của bạn đã có thêm một niềm vui.', 31, 100000, 9, 'quang-ganh-lo.jpg', 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `borders`
--

CREATE TABLE `borders` (
  `id` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `phone` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `address` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `order_note` text COLLATE utf8_unicode_ci NOT NULL,
  `date_create` timestamp NOT NULL DEFAULT current_timestamp(),
  `status` int(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `borders`
--

INSERT INTO `borders` (`id`, `id_user`, `name`, `phone`, `email`, `address`, `order_note`, `date_create`, `status`) VALUES
(23, 0, 'DungIT', '0968259212', 'ezreal2048@gmail.com', '120 Nguyễn Lương Bằng', 'Vui lòng ship đến 100 Tôn Đức Thắng', '2020-12-08 09:38:40', 3),
(24, 4, 'Đỗ Duy Khánh', '0777456123', 'levi@gmail.com', '66 Ngô Thì Nhậm', 'Vui lòng ship đến 888 Tôn Đức Thắng', '2020-12-08 09:39:41', 3),
(25, 6, 'Tiểu Long Nữ', '036457854', 'lytieulong2048@gmail.com', '24 Nam Cao', 'Vui lòng ship đến 120 Nguyễn Lương Bằng', '2020-12-08 10:06:32', 3),
(26, 5, 'Dương Quá', '0905242525', 'quanhi@gmail.com', '50 Phạm Như Xương', 'Vui lòng ship đến 888 Tôn Đức Thắng', '2020-12-08 10:10:46', 3),
(27, 10, 'Phùng Thanh Độ', '0776858585', 'ngoclan387596@gmail.com', '90 Nguyễn Lương Bằng', 'Vui lòng ship đến 120 Nguyễn Lương Bằng', '2020-12-08 10:37:04', 3),
(28, 9, 'Thầy Giáo Ba', '0376454888', 'thayba@gmail.com', '30 Ngô Sỹ Liên', 'Vui lòng ship đến 888 Tôn Đức Thắng', '2020-12-08 10:41:36', 3),
(29, 9, 'Thầy Giáo Ba', '0376454888', 'thayba@gmail.com', '30 Ngô Sỹ Liên', 'Vui lòng ship đến 100 Tôn Đức Thắng', '2020-12-08 10:43:14', 4),
(30, 10, 'Phùng Thanh Độ', '0776858585', 'ngoclan387596@gmail.com', '90 Nguyễn Lương Bằng', 'Vui lòng ship đến 100 Tôn Đức Thắng', '2020-12-08 11:09:16', 3),
(31, 10, 'Phùng Thanh Độ', '0776858585', 'ngoclan387596@gmail.com', '90 Nguyễn Lương Bằng', 'Vui lòng ship đến 888 Tôn Đức Thắng', '2020-12-09 09:23:50', 3),
(32, 6, 'Tiểu Long Nữ', '036457854', 'lytieulong2048@gmail.com', '24 Nam Cao', 'Vui lòng ship đến 120 Nguyễn Lương Bằng', '2020-12-09 10:43:28', 4),
(33, 0, 'Bi Rain', '0968259228', 'khanhvu2048@gmail.com', '120 Nguyễn Lương Bằng', 'Vui lòng ship đến 100 Tôn Đức Thắng', '2020-12-10 15:05:18', 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `categories`
--

CREATE TABLE `categories` (
  `id` int(255) NOT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `parent_id` int(11) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `categories`
--

INSERT INTO `categories` (`id`, `name`, `parent_id`) VALUES
(1, 'Tâm lý - Kỹ năng sống', 0),
(2, 'Tiểu thuyết Trung Quốc', 0),
(3, 'Kiếm hiệp - Tiên hiệp', 2),
(4, 'Truyện tranh', 0),
(5, 'Hồi ký - Tuỳ bút', 12),
(6, 'Văn học Việt Nam', 12),
(7, 'Cổ tích - Thần thoại', 0),
(8, 'Tài liệu học tập', 0),
(9, 'Kinh tế - Quản lý', 0),
(10, 'Huyền bí - Giả tưởng', 0),
(12, 'Văn học', 0),
(13, 'Android', 20),
(15, 'Manga', 4),
(16, 'Toán cao cấp', 8),
(17, 'Công nghệ thông tin', 8),
(19, 'JSP/SERVLET', 21),
(20, 'Công nghệ di động', 17),
(21, 'Công nghệ WEB', 17);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `contacts`
--

CREATE TABLE `contacts` (
  `id` int(11) NOT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `message` text COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `contacts`
--

INSERT INTO `contacts` (`id`, `name`, `email`, `message`) VALUES
(2, 'Dương Quá', 'quanhi@gmail.com', 'Hy vọng được gặp chủ Shop'),
(3, 'Minh Lộc', 'loc@gmail.com', 'Chủ shop đẹp trai'),
(4, 'Thầy Giáo Ba', 'baga@gmail.com', 'Liên hệ với tôi sớm'),
(5, 'Độ Phùng', 'dochet@gmail.com', 'Liên hệ với tôi'),
(6, 'Faker', 'faker@gmail.com', 'Hí ae'),
(7, 'Ba Gà', 'ba@gmail.com', 'Shop ok');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `favourites`
--

CREATE TABLE `favourites` (
  `id` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `id_book` int(11) NOT NULL,
  `status` int(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `favourites`
--

INSERT INTO `favourites` (`id`, `id_user`, `id_book`, `status`) VALUES
(1, 2, 1, 1),
(2, 4, 11, 1),
(3, 4, 7, 0),
(4, 5, 11, 1),
(5, 6, 14, 0),
(6, 2, 13, 1),
(7, 4, 9, 1),
(9, 4, 3, 0),
(10, 4, 1, 0),
(11, 4, 4, 1),
(12, 5, 3, 1),
(13, 5, 14, 0),
(14, 6, 11, 0),
(15, 6, 2, 1),
(16, 6, 9, 0),
(17, 6, 15, 1),
(18, 6, 12, 1),
(19, 5, 2, 0),
(20, 5, 4, 0),
(21, 9, 2, 0),
(22, 9, 4, 1),
(23, 9, 12, 0),
(24, 9, 3, 1),
(25, 9, 1, 0),
(26, 9, 6, 0),
(27, 9, 11, 0),
(28, 9, 15, 1),
(29, 9, 13, 1),
(30, 9, 14, 1),
(31, 5, 7, 1),
(32, 5, 12, 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `forbiddenword`
--

CREATE TABLE `forbiddenword` (
  `id` int(11) NOT NULL,
  `word` varchar(255) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `forbiddenword`
--

INSERT INTO `forbiddenword` (`id`, `word`) VALUES
(1, 'vl'),
(2, 'dm'),
(3, 'dkm'),
(4, 'vcl'),
(6, 'kkk');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `module`
--

CREATE TABLE `module` (
  `id` int(11) NOT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `module`
--

INSERT INTO `module` (`id`, `name`) VALUES
(1, 'Quản lý danh mục'),
(2, 'Quản lý sách'),
(3, 'Quản lý người dùng'),
(4, 'Quản lý đơn hàng'),
(5, 'Quản lý đánh giá'),
(6, 'Quản lý sách sale'),
(7, 'Quản lý liên hệ'),
(8, 'Quản lý từ cấm'),
(11, 'Quản lý phân quyền');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `order_details`
--

CREATE TABLE `order_details` (
  `id` int(11) NOT NULL,
  `id_order` int(11) NOT NULL,
  `number` int(11) NOT NULL,
  `id_book` int(11) NOT NULL,
  `sale` int(3) NOT NULL DEFAULT 0,
  `price` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `order_details`
--

INSERT INTO `order_details` (`id`, `id_order`, `number`, `id_book`, `sale`, `price`) VALUES
(46, 23, 2, 12, 10, 20000),
(47, 23, 1, 10, 15, 199000),
(48, 24, 2, 3, 40, 20000),
(49, 24, 1, 2, 30, 259000),
(50, 24, 1, 11, 0, 240000),
(51, 25, 1, 13, 0, 350000),
(52, 25, 1, 8, 0, 79000),
(53, 26, 1, 7, 0, 39000),
(54, 26, 1, 9, 0, 79000),
(55, 26, 1, 13, 0, 350000),
(56, 27, 1, 6, 20, 119000),
(57, 27, 1, 5, 0, 69000),
(58, 27, 1, 4, 20, 399000),
(59, 28, 1, 1, 30, 79000),
(60, 28, 1, 4, 20, 399000),
(61, 29, 1, 15, 0, 100000),
(62, 29, 1, 11, 0, 240000),
(63, 30, 1, 1, 30, 79000),
(64, 30, 1, 3, 40, 20000),
(65, 31, 1, 11, 0, 240000),
(66, 31, 2, 12, 10, 20000),
(67, 31, 1, 10, 15, 199000),
(68, 32, 1, 10, 15, 199000),
(69, 32, 1, 11, 0, 240000),
(70, 32, 2, 3, 40, 20000),
(71, 33, 2, 8, 0, 79000),
(72, 33, 1, 7, 0, 39000),
(73, 33, 1, 10, 15, 199000);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `reviews`
--

CREATE TABLE `reviews` (
  `id` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `id_book` int(11) NOT NULL,
  `star` double NOT NULL,
  `comment` text COLLATE utf8_unicode_ci NOT NULL,
  `date_create` timestamp NOT NULL DEFAULT current_timestamp(),
  `status` int(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `reviews`
--

INSERT INTO `reviews` (`id`, `id_user`, `id_book`, `star`, `comment`, `date_create`, `status`) VALUES
(1, 2, 1, 5, 'Shop giao hàng rất nhanh và đúng hạn, đóng gói đẹp, sách ok, ủng hộ shop', '2020-11-11 05:40:07', 1),
(3, 4, 6, 4.5, 'Shop chất lượng, ủng hộ shop', '2020-11-16 13:21:27', 1),
(4, 5, 11, 5, 'Tuyệt vời', '2020-11-16 13:21:27', 1),
(5, 6, 14, 5, 'Shop chất lượng quá', '2020-11-11 13:30:49', 1),
(7, 6, 2, 5, 'Tuổi thơ ùa về', '2020-11-16 13:21:26', 1),
(8, 5, 2, 4.5, 'Rất hài lòng với shop', '2020-11-12 07:29:43', 1),
(9, 5, 10, 4, 'Sách hay, mới, chất lượng', '2020-11-17 07:38:37', 1),
(10, 4, 13, 3.5, 'Sản phẩm tuyệt vời abc', '2020-11-27 10:41:52', 1),
(11, 5, 13, 4.5, 'Tuyệt vời', '2020-11-27 11:16:31', 1),
(12, 6, 3, 4.5, 'Truyện hay quá', '2020-11-27 11:19:30', 1),
(13, 9, 12, 4, 'Shop chất lượng', '2020-11-27 11:21:18', 1),
(14, 10, 11, 4, 'Hay quá', '2020-11-27 11:23:15', 1),
(15, 6, 12, 5, 'ABC', '2020-11-29 04:54:55', 1),
(16, 5, 11, 4, 'Shop ok', '2020-11-29 05:19:39', 1),
(19, 5, 3, 5, 'Ok shop', '2020-12-03 11:02:18', 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `roles`
--

CREATE TABLE `roles` (
  `id` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `id_module` int(11) NOT NULL,
  `status` int(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `roles`
--

INSERT INTO `roles` (`id`, `id_user`, `id_module`, `status`) VALUES
(1, 3, 2, 1),
(2, 3, 1, 1),
(3, 7, 8, 1),
(4, 7, 7, 1),
(5, 7, 3, 1),
(6, 12, 5, 1),
(7, 12, 4, 1),
(8, 3, 6, 1),
(12, 14, 6, 1),
(13, 14, 4, 1),
(14, 14, 2, 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `sale_off`
--

CREATE TABLE `sale_off` (
  `id` int(11) NOT NULL,
  `id_book` int(11) NOT NULL,
  `sale` int(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `sale_off`
--

INSERT INTO `sale_off` (`id`, `id_book`, `sale`) VALUES
(1, 4, 20),
(2, 1, 30),
(3, 3, 40),
(4, 2, 30),
(5, 6, 20),
(6, 10, 15),
(8, 12, 10);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `users`
--

CREATE TABLE `users` (
  `id` int(255) NOT NULL,
  `username` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `fullname` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `address` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `phone` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `role` int(1) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `users`
--

INSERT INTO `users` (`id`, `username`, `password`, `fullname`, `address`, `email`, `phone`, `role`) VALUES
(1, 'admin', '202cb962ac59075b964b07152d234b70', 'Administrator', '120 Nguyễn Lương Bằng', 'shopbook123@gmail.com', '0832656789', 2),
(2, 'nam', '202cb962ac59075b964b07152d234b70', 'Lê Trung Nam', '144 Nguyễn Lương Bằng', 'trungnam@gmail.com', '0765541245', 0),
(3, 'mod1', '202cb962ac59075b964b07152d234b70', 'Tiến Dũng', '300 Tôn Đức Thằng', 'tiendung@gmail.com', '0832245789', 1),
(4, 'levi', '202cb962ac59075b964b07152d234b70', 'Đỗ Duy Khánh', '66 Ngô Thì Nhậm', 'levi@gmail.com', '0777456123', 0),
(5, 'quanhi', '202cb962ac59075b964b07152d234b70', 'Dương Quá', '50 Phạm Như Xương', 'quanhi@gmail.com', '0905242525', 0),
(6, 'longnhi', '202cb962ac59075b964b07152d234b70', 'Tiểu Long Nữ', '24 Nam Cao', 'lytieulong2048@gmail.com', '036457854', 0),
(7, 'mod2', '202cb962ac59075b964b07152d234b70', 'Trần Hiếu', '74 Lạc Long Quân', 'hieu@gmail.com', '0778457102', 1),
(9, 'baga', 'caf1a3dfb505ffed0d024130f58c5cfa', 'Thầy Giáo Ba', '30 Ngô Sỹ Liên', 'thayba@gmail.com', '0376454888', 0),
(10, 'mixi', '202cb962ac59075b964b07152d234b70', 'Phùng Thanh Độ', '90 Nguyễn Lương Bằng', 'ngoclan387596@gmail.com', '0776858585', 0),
(11, 'chibinh', '202cb962ac59075b964b07152d234b70', 'Doãn Chí Bình', '98 Nguyễn Lương Bằng', 'chibinh@gmail.com', '0832254445', 0),
(12, 'mod3', '202cb962ac59075b964b07152d234b70', 'Nguyễn Thanh Tùng', '27 Trần Phú', 'tung@gmail.com', '0124521444', 1),
(13, 'admin2', '202cb962ac59075b964b07152d234b70', 'Phương Tuấn', '27 Nguyễn Lương Bằng', 'jack@gmail.com', '0124512589', 2),
(14, 'mod4', '202cb962ac59075b964b07152d234b70', 'Nguyễn Lan', '124 Nguyễn Chánh', 'lan@gmail.com', '0214512541', 1),
(17, 'sela', '202cb962ac59075b964b07152d234b70', 'Trương Tuấn Tú', '72 Ngô Thì Nhậm', 'sela@gmail.com', '0924555668', 0);

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `books`
--
ALTER TABLE `books`
  ADD PRIMARY KEY (`id`),
  ADD KEY `cat_id` (`cat_id`);

--
-- Chỉ mục cho bảng `borders`
--
ALTER TABLE `borders`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_user` (`id_user`);

--
-- Chỉ mục cho bảng `categories`
--
ALTER TABLE `categories`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `contacts`
--
ALTER TABLE `contacts`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `favourites`
--
ALTER TABLE `favourites`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_user` (`id_user`),
  ADD KEY `id_book` (`id_book`);

--
-- Chỉ mục cho bảng `forbiddenword`
--
ALTER TABLE `forbiddenword`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `module`
--
ALTER TABLE `module`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `order_details`
--
ALTER TABLE `order_details`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_order` (`id_order`),
  ADD KEY `id_book` (`id_book`);

--
-- Chỉ mục cho bảng `reviews`
--
ALTER TABLE `reviews`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_book` (`id_book`),
  ADD KEY `id_user` (`id_user`);

--
-- Chỉ mục cho bảng `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_user` (`id_user`),
  ADD KEY `id_module` (`id_module`);

--
-- Chỉ mục cho bảng `sale_off`
--
ALTER TABLE `sale_off`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_book` (`id_book`);

--
-- Chỉ mục cho bảng `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `books`
--
ALTER TABLE `books`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT cho bảng `borders`
--
ALTER TABLE `borders`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=34;

--
-- AUTO_INCREMENT cho bảng `categories`
--
ALTER TABLE `categories`
  MODIFY `id` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=36;

--
-- AUTO_INCREMENT cho bảng `contacts`
--
ALTER TABLE `contacts`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT cho bảng `favourites`
--
ALTER TABLE `favourites`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=33;

--
-- AUTO_INCREMENT cho bảng `forbiddenword`
--
ALTER TABLE `forbiddenword`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT cho bảng `module`
--
ALTER TABLE `module`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT cho bảng `order_details`
--
ALTER TABLE `order_details`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=74;

--
-- AUTO_INCREMENT cho bảng `reviews`
--
ALTER TABLE `reviews`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT cho bảng `roles`
--
ALTER TABLE `roles`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT cho bảng `sale_off`
--
ALTER TABLE `sale_off`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT cho bảng `users`
--
ALTER TABLE `users`
  MODIFY `id` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `books`
--
ALTER TABLE `books`
  ADD CONSTRAINT `books_ibfk_1` FOREIGN KEY (`cat_id`) REFERENCES `categories` (`id`);

--
-- Các ràng buộc cho bảng `favourites`
--
ALTER TABLE `favourites`
  ADD CONSTRAINT `favourites_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `favourites_ibfk_2` FOREIGN KEY (`id_book`) REFERENCES `books` (`id`);

--
-- Các ràng buộc cho bảng `order_details`
--
ALTER TABLE `order_details`
  ADD CONSTRAINT `order_details_ibfk_1` FOREIGN KEY (`id_order`) REFERENCES `borders` (`id`);

--
-- Các ràng buộc cho bảng `reviews`
--
ALTER TABLE `reviews`
  ADD CONSTRAINT `reviews_ibfk_1` FOREIGN KEY (`id_book`) REFERENCES `books` (`id`),
  ADD CONSTRAINT `reviews_ibfk_2` FOREIGN KEY (`id_user`) REFERENCES `users` (`id`);

--
-- Các ràng buộc cho bảng `roles`
--
ALTER TABLE `roles`
  ADD CONSTRAINT `roles_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `roles_ibfk_2` FOREIGN KEY (`id_module`) REFERENCES `module` (`id`);

--
-- Các ràng buộc cho bảng `sale_off`
--
ALTER TABLE `sale_off`
  ADD CONSTRAINT `sale_off_ibfk_1` FOREIGN KEY (`id_book`) REFERENCES `books` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
