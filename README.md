# SHOPBOOK
HDSD: clone project về -> Tạo database shopbook.sql (import file sql vào - nếu tên db khác thì sửa tên db trong file JDBCConnectionUtil.java) -> import hoặc copy project vào eclipse ee -> run project với server tomcat.
Account: Xem trong table users (pass: 123) hoặc có thể đổi pass bằng cách update password trực tiếp trong ứng dụng xampp và chọn MD5
Public: Thao tác trực tiếp trên client, các chức năng:
  - Xem thông tin sách, tìm kiếm
  - Thêm giỏ hàng, thao tác với giỏ hàng (tăng, giảm, xoá)
  - Đặt hàng (có tài khoản hoặc không), gửi email khi đặt hàng thành công
  - Quản lý đơn hàng (mua hàng với tài khoản có trong hệ thống)
  - Thanh toán online (paypal (tạo tài khoản sandbox để test tại https://www.sandbox.paypal.com/) và vnpay (lấy thông tin tại https://sandbox.vnpayment.vn/apis/vnpay-demo/ để test))
  - đánh giá sản phẩm, yêu thích sản phẩm

Admin: Login và có các chức năng:
  - Xem thông tin, tìm kiếm
  - Thêm, sửa, xoá: users, product,...
  - Phần quyền: bật/tắt quyền cho user có role là MOD (moderator)
  - Quản lý đơn hàng của user, có thể export đơn hàng ra file excel
  - ...
