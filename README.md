# Hotel Management System 
Hệ thống quản lí khách sạn gồm 3 actor: Người quản trị, nhân viên lễ tân và khách hàng giúp cho khách sạn tối ưu hóa trong việc quản lí và tiết kiệm nhiều thời gian cho nhân viên và khách hàng.

## Cài đặt
1. Chuyển 2 thư mục ```HotelEJB```và ```HotelTester``` trong thư mục ```NetBeansProjects``` vào trong thư mục ```NetBeansProjects``` của ```NetBeans 8.2``` trong máy tính của bạn sau đó thêm 2 thư mục đó vào project của phần mềm ```NetBeans```
2. Lưu thư mục ```EAP-7.0.0``` vào trong máy tính của bạn. Sau đó mở phần mềm ```NetBeans```, vào phần ```Server```, chuột phải vào ```WildFly Application Server``` và chọn ```Properties```, sau đó ```Configuration File``` chính xác thư mục chứa standalone.xml, ví dụ:

         EAP-7.0.0/standalone/configuration/standalone.xml

3. Mở sang tab Projects, chọn `Properties` của `HotelEJB`, sau đó chọn tab `Run`, chỉnh lại server ` WildFly Application Server` cho phù hợp sao cho NetBeans không báo lỗi nữa.
4. Cài đặt postgres 9.6 và pgAdmin 4. Sau đó tạo database mới và chọn khôi phục (restore) ở thư mục ```database```, nếu yêu cầu nhập user và password thì nhập ```postgres``` và ```password```.
5. Bây giờ thì start WildFly Application Server, mở trình duyệt và truy cập vào `Administration Console` của `http://localhost:8080/`, nhập đúng `username` và `password` là `admin` và `123456`, nếu bạn đã từng cài EAP khác thì hãy nhập đúng `username` và `password` của bạn

6. Tạo `Datasource` để kết nối database ở bước 4 và sử dụng Detected Driver là `postgresql-9.3-1104.jdbc41.jar`, lưu ý khi tạo thì hãy chọn `jdbc:postgresql://localhost:xxxx/postgres
` và `username` với `password` chính xác với hệ thống postgres mà bạn đã tạo trên máy bạn. Ví dụ:

   jdbc:postgresql://localhost:5433/postgres với username là *postgres* và password là *password*

Nếu trường hợp bạn không tìm được `postgresql-9.3-1104.jdbc41.jar`, hãy chắc chắn rằng bạn đã tạo `postgresql-9.3-1104.jdbc41.jar` và `module.xml` trong 

`EAP-7.0.0/modules/system/layers/base/org/postgresql/main`

7. Quay lại NetBeans và mở project `HotelEJB`, trong `package` `hotel.ejb`, trong toàn bộ file trong đó có 

```java
        String url = "java:/PostgresDS";
        String user = "postgres";
        String password = "password";
```
Hãy sửa toàn bộ file sao cho `url`, `user`, `password` chính xác với posgres trong datasource đã tạo trong bước 6.

8. Clean and build project `HotelEJB`, start ` WildFly Application Server`

9. Deploy project `HotelEJB`
10. Nếu bạn đã từng cài EAP thì sửa `host`, `port`, `username`, `password` trong  `jndi.properties`chính xác với EAP của bạn, `jboss-ejb-client.properties` cũng tương tự

11. Chạy `Tester.java` trong project `HotelTester` để thực hiện chương trình

## Giới thiệu về chương trình

 **Chương trình gồm 3 Actor là Người quản trị, lễ tân, Khách hàng.**

**Chương trình sử dụng Enterprise Java Beans (EJB) với Red Hat JBoss Enterprise Application Platform (EAP) 7**

**Kết nối database qua Datasource ở Session Bean**

**1. Khách hàng**

Khách hàng chỉ có 3 chức năng là `Đặt phòng online`, `Hủy đặt phòng`, và `Xem hóa đơn`

Lúc đầu khách điền đầy đủ thông tin, sau đó sẽ thực hiện được các chức năng trên. 
Nếu khách hàng `đặt phòng online` thành công, thì hệ thống sẽ lưu khách hàng vào database. Trường hợp khách hàng đã đăng xuất nhưng lần sau đăng nhập lại vẫn hiện thông tin phòng khách hàng đã đặt nếu khách hàng chính xác `identity card` đã được nhập.

**2. Lễ tân** (username: letan, password: letan)

Lễ tân có 10 chức năng như sau: 
1. `Xem thông tin phòng trống trong khách sạn`
2. `Check in khách hàng`
3. `Xem thông tin phòng đã có người ở`
4. `Check out khách hàng` 
5. `Tạo hóa đơn` 
6. `Hiển thị danh sách thông tin khách hàng với phòng đã được khách hàng đặt phòng online` 
7. `Đồng ý đặt phòng`
8. `Từ chối đặt phòng`
9. `Hiển thị thông tin khách hàng đang ở trong khách sạn`
10. `Hiển thị danh sách khách hàng đã đặt phòng thành công (nhân viên lễ tân đã gọi và xác nhận đặt phòng)`


**3. Người quản trị** (username: admin, password: admin)


Người quản trị có 10 chức năng như sau:
1. `Thay đổi mật khẩu`
2. `Hiển thị danh sách nhân viên`
3. `Xem thông tin phòng trong khách sạn (đã có người ở, có người đặt phòng)`
4. `Tạo nhân viên` 
5. `Xóa nhân viên` 
6. `Tạo tài khoản cho nhân viên` 
7. `Thay đổi thông tin nhân viên`
8. `Xóa nhân viên`
9. `Hiển thị thông tin tài khoản nhân viên`
10. `Tạo báo cáo`
   
