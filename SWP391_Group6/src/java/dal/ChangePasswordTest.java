package dal;

import model.Customer;

public class ChangePasswordTest {

    public static void main(String[] args) {
        CustomerDBContext customerDbContext = new CustomerDBContext();

        int testCustomerId = 1; // ID của khách hàng cần kiểm tra
        String currentPassword = "234"; // Mật khẩu hiện tại
        String newPassword = "123"; // Mật khẩu mới

        // Lấy thông tin khách hàng
        Customer customer = customerDbContext.getCustomerById(testCustomerId);

        if (customer == null) {
            System.out.println("Không tìm thấy khách hàng với ID: " + testCustomerId);
            return;
        }

        // Kiểm tra mật khẩu hiện tại
        if (customer.getPassword().equals(currentPassword)) {
            // Thay đổi mật khẩu
            boolean isChanged = customerDbContext.changePassword(testCustomerId, newPassword);
            if (isChanged) {
                System.out.println("Đã thay đổi mật khẩu thành công cho khách hàng ID: " + testCustomerId);
            } else {
                System.out.println("Không thể thay đổi mật khẩu.");
            }
        } else {
            System.out.println("Mật khẩu hiện tại không đúng.");
        }
    }
}
