package com.hitex.yousim.constant;

/**
 * @author Chidq
 * @project yousim
 * @created 31/03/2021 - 4:12 PM
 */
public class Constant {
    public static int SUCCESS = 0;
    public static int ERROR = 1;
    public static String URL_IMAGE = "https://imgyousim.semob.info/img/hopdongv2.png";
    public static String URL_IMAGE_CONTRACT= "https://imgyousim.semob.info/img/hopdong.jpg";
    public static final int OTP_NUMBER = 6;
    public static final String SECRET_KEY = "f12jfh5h3h6k4k2k4jd3nx";
    public static final String KEY = "0000000000";
    public static final String FORMAT_DATE_TIME = "yyyy-MM-dd HH:mm:ss";

    public static final String USER_1CONNECT_ITC = "1connect_itc";
    public static final String PASS_1CONNECT_ITC = "121212";
    public static final String SUCCESS_SEND_OTP_UPDATE = "He thong da gui ma so xac thuc toi khach hang";

// trạng thái mua ngoài
    public static final int PURCHASE_OUT = 2;

//    account đăng nhập
    public static final String USERNAME_BAMBO = "bamboo_itel";
    public static final String PASSWORD_BAMBO = "Itelecom@1234";
    public static final String USERNAME_BAMBO_THUONG = "bamboo_thuong_itel";
    public static final String USERNAME_1CONNECT = "1connect_itel";
    public static final String PASSWORD_1CONNECT = "Itelecom@1234";
    public static final String PASSWORD_BAMBO_THUONG = "Itelecom@1234";
    public static final String USERNAME_10TTECH="tuanlm@itelecom.vn";
    public static final String PASSWORD_10TTECH="10T@@1t3L3c0M!!";

    // Config mail
    public static final String HOST_NAME = "smtp.gmail.com";
    public static final int SSL_PORT = 465; // Port for SSL
    public static final int TSL_PORT = 587; // Port for TLS/STARTTLS

    //Order Time
    public static String ASC = "ASC";
    public static String DESC = "DESC";

    // role user
    public static int ROLE_ADMIN = 1;
    public static int ROLE_STAFF = 2;
    public static int ROLE_CUSTOMER = 3;

    // physic type
    public static int U_SIM = 1;
    public static int E_SIM = 2;

    public static int VOUCHER_VIP = 2;
    public static int ISDN_VIP = 6;

    // đơn vị giao hàng
    public static int DELIVERY_GHTK = 1 ;
    public static int DELIVERY_ECOMMERCE = 2 ;
    //domain that
    public static String IMAGE_DOMAIN = "https://imgyousim.semob.info/";// link anh thât
    public static String VOUCHER_DOMAIN="https://apivoucher.itel.vn/api/";// domain that
    public static String TOKEN_GHTK="17C815a7aA2ae2f04184d1914a17d1dCAF9A3726";// that
    public static String GHTK_DOMAIN="https://services.giaohangtietkiem.vn/services/";
    public static String DIRECTION = "/home/yousim/upload/";
    public static String DOMAIN_YOUSIM="https://apiyousim.semob.info/api/";

    //domain test
//    public static String IMAGE_DOMAIN = "https://imgyousimtest.semob.info/";// link ảnh test
//    public static String VOUCHER_DOMAIN="http://125.212.225.165:1999/voucher_test/api/";// domain test
//    public static String GHTK_DOMAIN="https://services.ghtklab.com/services/";
//    public static String TOKEN_GHTK="Db56032e28B9749b3e55f19D1023ba3bcD981235";// test
//    public static String DIRECTION = "/home/yousim_test/upload/";
////    public static String DIRECTION = "D:\\hitex\\upload\\";
//    public static String DOMAIN_YOUSIM="https://apisimtest.semob.info/api/";




    public static String PATH = IMAGE_DOMAIN + "img/";
    public static String PATH_EXCEL = IMAGE_DOMAIN + "doc/";
    public static String PATH_EXCEL_ORDER = PATH_EXCEL + "order/";
    public static String IMAGE_DIRECTION = DIRECTION + "img/";
    public static String EXCEL_DIRECTION = DIRECTION + "doc/";
    public static String EXCEL_DIRECTION_ORDER = EXCEL_DIRECTION + "order/";

    //trang thai khach hang
    public static int ACTIVE_CUSTOMER = 1;
    public static int INACTIVE_CUSTOMER = 0;
    public static int NEW_CUSTOMER = 2;

    public static int ORDER_CUSTOMER = 1;
    public static int ORDER_PARTNER = 2;
    public static int ORDER_ON_INTELPARTNER= 1;

    //trang thai don hang
    public static int NEW_ORDER = 1;// chưa duyệt
    public static int CONFIRMED_ORDER = 2;// xác nhận đơn hàng
    public static int REFUSE_ORDER = 3;// từ chối đặt hàng
    public static int RECEIVED_ORDER = 4;// đã nhận đơn hàng
    public static int CANCELED_ORDER = 5;// huỷ đơn hàng
    public static int REFUND_ORDER = 6;// GIAO HÀNG KHÔNG THÀNH CÔNG, ĐƠN HÀNG BỊ TRẢ LẠI
    public static int PACKING = 7;// ĐANG ĐÓNG GÓI
    public static int GHTK_SHIP_RECEIVED_ORDER= 8;// ĐÃ GIAO CHO ĐƠN VỊ VÂN CHUYỂN
    public static int CANCEL_GHTK= 9;// huỷ GHTK


    //trạng thái ghtk
//    public static int GHTK_RECEIVED_ORDER= 1;// GHTK ĐÃ tiếp nhận đơn
//    public static int GHTK_HAVE_NOT_RECEIVED_ORDER= 2; // ghtk chưa nhận đưuọc đơn
//    public static int GHTK_SHIP_RECEIVED_ORDER= 3;// ship đã nhận được đơn từ người bán
//    public static int GHTK_DELIVERING= 4;// ghtk đang giao hàng
//    public static int CUSTOMER_RECEIVED_PRODUCT= 5;// khách hàng đã nhận được đơn hàng
//    public static int SHIP_DONT_DELIVERED= 6;// Không giao được hàng
//    public static int CANCEL_GHTK= 7;// huỷ gửi hàng ghtk
//    public static int SHIP_DONT_DELIVERED=8;// kHÔNG GIAO ĐƯỢC HÀNG


    // phuong thuc thanh toan
    public static int VN_PAY = 1;
    public static int COD = 2;

    //trang thai thanh toan
    public static int UNPAID = 0;
    public static int PAID = 1;

    public static int ACTIVE_ORDER = 1;
    public static int INACTIVE_ORDER = 1;

    public static int IDENTITY_CARD = 1;
    public static int DRIVER_LICENSE = 1;
    public static int IDENTITY_CITIZEN = 1;
    public static int ORDER_TYPE_KIT = 1;
    public static int ORDER_TYPE_ISDN = 2;

    //User
    public static int ACTIVE_USER = 1;

    //trang thai sim KIT ISDN
    public static int AVAILABLE = 0;
    public static int BOOKED = 1;
    public static int SELL = 2;
    public static int IN_ORDER=2;
    public static int DELETED = 3;
    public static int SIM_IN_ORDER=3;

    //Query cho chuc nang filter
    public static String QUERY_FILTER = "SELECT * FROM v_number v WHERE v.status = 0 %s";
    public static String QUERY_COUNT = "SELECT * FROM v_number v WHERE v.status = 0 %s";


    //Role user Voucher
    public static int ROLE_SUPER_ADMIN = 1;
    public static int ROLE_PARTNER = 4;

    //Status
    public static int USED = 1;
    public static int NOT_USE = 0;
    // trang thai partner
    public static int ACTIVE_PARTNER = 1;
    public static int INACTIVE_PARTNER = 0;

    //    trạng thái voucher
    public static  int ACTIVE_VOUCHER_DISCOUNT = 1;
    public static  int INACTIVE_VOUCHER_DISCOUNT = 0;

    //Gioi Tinh
    public static int MALE = 1;
    public static int FEMALE = 0;

    public static int TEST_DELETED = 2;
    public static int SIM_DELETED = 3;
    public static int SIM_DELETED_TEST = 1;
    // trạng thái đã xoá của role
    public static final int DELETED_ROLE = 3;

    // trang thai cua kenh ban hang
    public static int INACTIVATE_SOURCE_CHANNEL_SALE=0;
    public static int ACTIVATE_SOURCE_CHANNEL_SALE=1;
    public static int DELETE_SOURCE_CHANNEL_SALE=2;

    //  trang thai cua khach hang
    public static String chapThuan = "APROVE";
    public static String taoMoi = "NEW";
    public static String tuChoi = "CANCEL";



    public static class PAYMENT {
        public static class VNPAY {
            public static class COMMAND {
                public static final String PAY = "pay";
                public static final String QUERYDR = "querydr";
            }

            public static class ORDER_TYPE {
                public static final String SIM = "110005";
            }

            public static class RESCODE {
                public static final String DONE = "00";
                public static final String ORDER_NOT_FOUND = "01";
                public static final String CONFIRMED = "02";
                public static final String CHECK_SUM_FAIL = "97";
                public static final String INVALID_AMOUNT = "04";
                public static final String OTHER_ERROR = "99";
            }

            public static class MESSAGE {
                public static final String SUCCESS = "Confirm Success";
                public static final String CONFIRMED = "Order already confirmed";
                public static final String ORDER_NOT_FOUND = "Order not found";
                public static final String CHECK_SUM_FAIL = "Invalid signature";
                public static final String INVALID_AMOUNT = "Invalid amount";
                public static final String OTHER_ERROR = "Unknow error";
            }
        }
        public static class TRANS {
            public static final String SUCCESS = "00";
            public static final String WAITING = "01";
            public static final String CHECKOUT_FAILED = "02";
            public static final String CANCELED = "03";
            public static final String FAILED = "04";
            public static final String REVIEWING = "05";
            public static final String DUPLICATE = "94";
            public static final String CANCELED_VNPAY = "24";
            //
            public static final int NOT_USED = 0;
            public static final int USED = 1;
        }
        public static class PARTNER {
            //            public static final String MAIN_ACCOUNT = "MAIN_ACCOUNT";
//            public static final String NGAN_LUONG = "NGAN_LUONG";
//            public static final String ALEPAY = "ALEPAY";
            public static final String VNPAY = "VNPAY";
        }
    }
    public static class TYPE_DOC {
        public static class CMT {
            public static final String CMT = "1";
            public static final String CMT_FONT = "0";
            public static final String CMT_BACK = "1";
        }
        public static class CCCD {
            public static final String CCCD = "45";
            public static final String CCCD_FONT = "2";
            public static final String CCCD_BACK = "3";
        }
    }
}
