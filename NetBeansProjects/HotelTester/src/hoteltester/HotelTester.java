package hoteltester;

import hotel.ejb.AccountSessionBean;
import hotel.ejb.AccountSessionBeanRemote;
import hotel.ejb.BillSessionBean;
import hotel.ejb.BillSessionBeanRemote;
import hotel.ejb.CheckinSessionBean;
import hotel.ejb.CheckinSessionBeanRemote;
import hotel.ejb.CheckoutSessionBean;
import hotel.ejb.CheckoutSessionBeanRemote;
import hotel.ejb.CustomerBookedSessionBean;
import hotel.ejb.CustomerBookedSessionBeanRemote;
import hotel.ejb.CustomerSessionBean;
import hotel.ejb.CustomerSessionBeanRemote;
import hotel.ejb.EmployeeSessionBean;
import hotel.ejb.EmployeeSessionBeanRemote;
import hotel.ejb.RoomSessionBean;
import hotel.ejb.RoomSessionBeanRemote;
import hotel.ejb.StatusSessionBean;
import hotel.ejb.StatusSessionBeanRemote;
import hotel.ejb.TobookSessionBean;
import hotel.ejb.TobookSessionBeanRemote;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import hotel.ejb.UseradminSessionBean;
import hotel.ejb.UseradminSessionBeanRemote;
import hotel.entiry.Account;
import hotel.entiry.Bill;
import hotel.entiry.Checkin;
import hotel.entiry.Checkout;
import hotel.entiry.Customer;
import hotel.entiry.Customerbooked;
import hotel.entiry.Employee;
import hotel.entiry.Room;
import hotel.entiry.Status;
import hotel.entiry.Tobook;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class HotelTester {

    //create customer
    private Customer cm;
    private Properties props;
    private InitialContext ctx;

    public HotelTester() {
        readJNDI();
    }

    private int compareTwoDay(String day1, String day2) throws ParseException {
        Date d1 = null;
        Date d2 = null;
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        d1 = format.parse(day1);
        d2 = format.parse(day2);
        return (d1.compareTo(d2));
    }

    private int diffMonths(String day1, String day2) throws ParseException {
        Date startDate = null;
        Date endDate = null;
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        startDate = format.parse(day1);
        endDate = format.parse(day2);

        Calendar startCalendar = Calendar.getInstance();
        startCalendar.setTime(startDate);
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(endDate);

        int diffYear = endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR);
        int diffMonth = diffYear * 12 + endCalendar.get(Calendar.MONTH) - startCalendar.get(Calendar.MONTH);

        return diffMonth;
    }

    private long diffDays(String dateStart, String dateStop) throws ParseException {

        Date d1 = null;
        Date d2 = null;
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        d1 = format.parse(dateStart);
        d2 = format.parse(dateStop);

        //in milliseconds
        long diff = d2.getTime() - d1.getTime();

        long diffDays = diff / (24 * 60 * 60 * 1000);

        return diffDays;
    }

    /**
     * Read the JNDI properties file
     */
    private void readJNDI() {
        props = new Properties();

        try {
            props.load(new FileInputStream("jndi.properties"));
        } catch (IOException e) {
            System.err.println(e.toString());
        }

        try {
            ctx = new InitialContext(props);
            //ctx.close();
        } catch (NamingException ex) {
            System.err.println(ex.toString());
        }
    }

    //-------------------------------------The first Login ---------------------------------------------------
    private String getJNDI_employee() {
        String appName = "";
        String moduleName = "HotelEJB";
        String distinctName = "";
        String sessionBeanName = EmployeeSessionBean.class.getSimpleName();
        String viewClassName = EmployeeSessionBeanRemote.class.getName();

        return "ejb:" + appName + "/" + moduleName + "/" + distinctName + "/" + sessionBeanName + "!" + viewClassName;
    }

    private String getJNDI_useradmin() {
        String appName = "";
        String moduleName = "HotelEJB";
        String distinctName = "";
        String sessionBeanName = UseradminSessionBean.class.getSimpleName();
        String viewClassName = UseradminSessionBeanRemote.class.getName();

        return "ejb:" + appName + "/" + moduleName + "/" + distinctName + "/" + sessionBeanName + "!" + viewClassName;
    }

    private String getJNDI_Account() {
        String appName = "";
        String moduleName = "HotelEJB";
        String distinctName = "";
        String sessionBeanName = AccountSessionBean.class.getSimpleName();
        String viewClassName = AccountSessionBeanRemote.class.getName();

        return "ejb:" + appName + "/" + moduleName + "/" + distinctName + "/" + sessionBeanName + "!" + viewClassName;
    }

    private String getJNDI_Status() {
        String appName = "";
        String moduleName = "HotelEJB";
        String distinctName = "";
        String sessionBeanName = StatusSessionBean.class.getSimpleName();
        String viewClassName = StatusSessionBeanRemote.class.getName();

        return "ejb:" + appName + "/" + moduleName + "/" + distinctName + "/" + sessionBeanName + "!" + viewClassName;
    }

    private String getJNDI_Customer() {
        String appName = "";
        String moduleName = "HotelEJB";
        String distinctName = "";
        String sessionBeanName = CustomerSessionBean.class.getSimpleName();
        String viewClassName = CustomerSessionBeanRemote.class.getName();

        return "ejb:" + appName + "/" + moduleName + "/" + distinctName + "/" + sessionBeanName + "!" + viewClassName;
    }

    private String getJNDI_Tobook() {
        String appName = "";
        String moduleName = "HotelEJB";
        String distinctName = "";
        String sessionBeanName = TobookSessionBean.class.getSimpleName();
        String viewClassName = TobookSessionBeanRemote.class.getName();

        return "ejb:" + appName + "/" + moduleName + "/" + distinctName + "/" + sessionBeanName + "!" + viewClassName;
    }

    private String getJNDI_Room() {
        String appName = "";
        String moduleName = "HotelEJB";
        String distinctName = "";
        String sessionBeanName = RoomSessionBean.class.getSimpleName();
        String viewClassName = RoomSessionBeanRemote.class.getName();

        return "ejb:" + appName + "/" + moduleName + "/" + distinctName + "/" + sessionBeanName + "!" + viewClassName;
    }

    private String getJNDI_Bill() {
        String appName = "";
        String moduleName = "HotelEJB";
        String distinctName = "";
        String sessionBeanName = BillSessionBean.class.getSimpleName();
        String viewClassName = BillSessionBeanRemote.class.getName();

        return "ejb:" + appName + "/" + moduleName + "/" + distinctName + "/" + sessionBeanName + "!" + viewClassName;
    }

    private String getJNDI_Checkin() {
        String appName = "";
        String moduleName = "HotelEJB";
        String distinctName = "";
        String sessionBeanName = CheckinSessionBean.class.getSimpleName();
        String viewClassName = CheckinSessionBeanRemote.class.getName();

        return "ejb:" + appName + "/" + moduleName + "/" + distinctName + "/" + sessionBeanName + "!" + viewClassName;
    }

    private String getJNDI_CustomerBooked() {
        String appName = "";
        String moduleName = "HotelEJB";
        String distinctName = "";
        String sessionBeanName = CustomerBookedSessionBean.class.getSimpleName();
        String viewClassName = CustomerBookedSessionBeanRemote.class.getName();

        return "ejb:" + appName + "/" + moduleName + "/" + distinctName + "/" + sessionBeanName + "!" + viewClassName;
    }

    private String getJNDI_Checkout() {
        String appName = "";
        String moduleName = "HotelEJB";
        String distinctName = "";
        String sessionBeanName = CheckoutSessionBean.class.getSimpleName();
        String viewClassName = CheckoutSessionBeanRemote.class.getName();

        return "ejb:" + appName + "/" + moduleName + "/" + distinctName + "/" + sessionBeanName + "!" + viewClassName;
    }

    /**
     * Show the GUI in console window
     */
    private void showGUI() {
        System.out.println("-----------------------------------------------------------------------------");
        System.out.println("======================Welcome to Hotel Management============================ ");
        System.out.println("-----------------------------------------------------------------------------");
        System.out.printf("%20s %25s %25s ", "1. Administrator", "2. Receptionist", "3. Customer\n");
        System.out.println("---------------------------------------------------------------------------");
        System.out.print("Enter choice:  ");

    }
    // ---------------------------------End The first Login ------------------------------------------------

    //------------------------------------------Customer-----------------------------------------------------
    private void showGUICustomer() {

        System.out.println("-----------------------------------------------------------------------------");
        System.out.println("======================= Welcome to Customer System ========================= ");
        System.out.println("-----------------------------------------------------------------------------");
        System.out.println(".......................1. Book online room hotel.............................");
        System.out.println(".......................2. Canncel a reservation..............................");
        System.out.println(".......................3. Pay a bill.........................................");
        System.out.println("-----------------------------------------------------------------------------");
        System.out.println();
        System.out.print("Enter choice:  ");

    }

    public void mainCustomer() throws NamingException, ParseException {
        this.showGUICustomer();

        Scanner sc = new Scanner(System.in);

        int choice = 0;

        //connect SessionBeanRemote
        StatusSessionBeanRemote statusBean = (StatusSessionBeanRemote) ctx.lookup(getJNDI_Status());
        RoomSessionBeanRemote roomBean = (RoomSessionBeanRemote) ctx.lookup(getJNDI_Room());
        CustomerSessionBeanRemote customerBean = (CustomerSessionBeanRemote) ctx.lookup(getJNDI_Customer());
        TobookSessionBeanRemote tobookBean = (TobookSessionBeanRemote) ctx.lookup(getJNDI_Tobook());
        BillSessionBeanRemote billBean = (BillSessionBeanRemote) ctx.lookup(getJNDI_Bill());

        while (choice != 4) {

            //Use this approach because nextInt will cause error to nextLine()
            choice = Integer.parseInt(sc.nextLine());

            Date today = new Date();
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.HOUR_OF_DAY, 0);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            cal.set(Calendar.MILLISECOND, 0);
            today = cal.getTime();

            if (choice == 1) {

                System.out.println("-----------------------------Book Room Hotel---------------------------------");
                System.out.print("Please input date you will Check In (dd/mm/yyyy):   ");
                String datein = sc.nextLine();
                Date date1;
                try {
                    date1 = new SimpleDateFormat("dd/MM/yyyy").parse(datein);
                    if (date1.compareTo(today) < 0) {
                        System.out.println(".......................................................");
                        System.out.println("Date not correct! Date can't in the PAST! try it again!");
                        System.out.println("......................................................");
                        this.showGUICustomer();
                        continue;
                    }
                } catch (ParseException ex) {
                    System.out.println("...........................................................");
                    System.out.println("Date not correct! Please check Input date and try it again!");
                    System.out.println("...........................................................");
                    this.showGUICustomer();
                    continue;
                }

                System.out.print("Please input date you will Check Out (dd/mm/yyyy):   ");
                String dateout = sc.nextLine();
                Date date2;
                try {
                    date2 = new SimpleDateFormat("dd/MM/yyyy").parse(dateout);
                    if (date2.compareTo(date1) < 0) {
                        System.out.println(".............................................................");
                        System.out.println("Date not correct! Date out can't < Date checkin! Try it again!");
                        System.out.println(".............................................................");
                        this.showGUICustomer();
                        continue;
                    }
                } catch (ParseException ex) {
                    System.out.println("...........................................................");
                    System.out.println("Date not correct! Please check Input date and try it again!");
                    System.out.println("...........................................................");
                    this.showGUICustomer();
                    continue;
                }

                List<Status> status_lst = new ArrayList<Status>();
                List<Status> status_lst_booked = new ArrayList<Status>();
                List<Status> status_lst_notfree = new ArrayList<Status>();
                List<Room> rooms = new ArrayList<Room>();
                //list room nobody in here (free room)
                List<Room> rooms_free = new ArrayList<Room>();

                //list room free from Status databse
                status_lst = statusBean.freeRoom();
                //list room booked from Status databse
                status_lst_booked = statusBean.freeRoomBook();
                //list from have person
                status_lst_notfree = statusBean.NotfreeRoom();
                //List from room datase 
                rooms = roomBean.getRoom();

                if (status_lst.isEmpty() && status_lst_booked.isEmpty()) {
                    System.out.println("...........................................................");
                    System.out.println(".........All room is full! Please comeback other day!......");
                    System.out.println("...........................................................");
                    this.showGUICustomer();
                } else {
                    //add room free -> room_free
                    if (!status_lst.isEmpty()) {
                        for (Status s : status_lst) {
                            for (Room r : rooms) {
                                if (r.getIdRoom() == s.getIdRoom()) {
                                    rooms_free.add(r);
                                }
                            }
                        }
                    }

                    if (!status_lst_booked.isEmpty()) {
                        //add room booked -> rooms_free
                        for (Status s : status_lst_booked) {
                            for (Room r : rooms) {
                                //kiem tra xem trong danh sach da duoc dat phong
                                //Ngay check in trong phong do lon hon ngay khach hang nay check out
                                //Thi cho phep khach hang nay dat phong` do!
                                //Vi du Room_ID = 1 Co stastus = 2 (co nguoi dat phong) vao date_checkin = "10/10/2019"
                                //Nhung khach hang` nay den dat phong` va ngay` check out la 28/04/2019
                                //Thi cho phep khach hang nay` dat phong` nay
                                if (compareTwoDay(dateout, s.getDateCheckin()) <= 0 && r.getIdRoom() == s.getIdRoom() && !rooms_free.contains(r)) {
                                    rooms_free.add(r);
                                    System.out.println("Room:" + r.getIdRoom());
                                }

                                if (compareTwoDay(datein, s.getDateCheckout()) >= 0 && r.getIdRoom() == s.getIdRoom() && !rooms_free.contains(r)) {
                                    rooms_free.add(r);
                                    System.out.println("Room:" + r.getIdRoom());

                                }
                            }
                        }
                    }

                    if (!status_lst_notfree.isEmpty()) {
                        //add room booked -> rooms_free
                        for (Status s : status_lst_notfree) {
                            for (Room r : rooms) {
                                //kiem tra xem trong danh sach da duoc dat phong
                                //Ngay check in trong phong do lon hon ngay khach hang nay check out
                                //Thi cho phep khach hang nay dat phong` do!
                                //Vi du Room_ID = 1 Co stastus = 2 (co nguoi dat phong) vao date_checkin = "10/10/2019"
                                //Nhung khach hang` nay den dat phong` va ngay` check out la 28/04/2019
                                //Thi cho phep khach hang nay` dat phong` nay
                                if (compareTwoDay(dateout, s.getDateCheckin()) <= 0 && r.getIdRoom() == s.getIdRoom() && !rooms_free.contains(r)) {
                                    rooms_free.add(r);
                                    System.out.println("Room:" + r.getIdRoom());
                                }

                                if (compareTwoDay(datein, s.getDateCheckout()) >= 0 && r.getIdRoom() == s.getIdRoom() && !rooms_free.contains(r)) {
                                    rooms_free.add(r);
                                    System.out.println("Room:" + r.getIdRoom());

                                }
                            }
                        }
                    }

                    System.out.println("---------------------List Free Room-----------------------");
                    System.out.println();
                    System.out.printf("%10s %10s %20s", "Room ID", "Type", "Price");
                    System.out.println();
                    System.out.println("........................................................");
                    for (Room r : rooms_free) {
                        System.out.printf("%10s %10s %20s", r.getIdRoom(), r.getType(), r.getPrice());
                        System.out.println();
                    }
                    System.out.println("........................................................");
                    System.out.println();
                    System.out.println();
                    System.out.print("Enter id room you want to book:  ");
                    try {
                        //kiem tra xem id room co ton tai hay khong
                        int idroom = Integer.parseInt(sc.nextLine());
                        boolean check = false;
                        for (Room r : rooms_free) {
                            if (r.getIdRoom() == idroom) {
                                check = true;
                                break;
                            }
                        }
                        //add database tobook and customer
                        if (check) {

                            //add customer
                            customerBean.createCustomer(cm);

                            //get id customer
                            int idcus = 0;
                            List<Customer> customers = new ArrayList<Customer>();
                            customers = customerBean.getCustomers();
                            for (Customer c : customers) {
                                if (c.getIdentityCard().equals(cm.getIdentityCard())) {
                                    idcus = c.getIdCustomer();
                                }
                            }

                            //set set customer
                            cm.setIdCustomer(idcus);

                            Tobook tbook = new Tobook();
                            tbook.setIdCustomer(idcus);
                            tbook.setIdRoom(idroom);
                            tbook.setDateCheckin(datein);
                            tbook.setDateCheckout(dateout);

                            //create tobook to database
                            tobookBean.createBook(tbook);
                            System.out.println("......................................................................");
                            System.out.println("--------You to book the room Succsessfully! Thank you very much!------");
                            System.out.println("Please wait for the reception to call you to confirm this transaction!");
                            System.out.println("......................................................................");
                        } else {
                            System.out.println("............................................................");
                            System.out.println("ID room not correct! Please che ck Input id and try it again!");
                            System.out.println("............................................................");
                            this.showGUICustomer();
                            continue;
                        }

                    } catch (NumberFormatException ex) {
                        System.out.println("...........................................................");
                        System.out.println("ID room not correct! Please check Input id and try it again!");
                        System.out.println("...........................................................");
                        this.showGUICustomer();
                        continue;
                    }
                    this.showGUICustomer();

                }

            } //end choice == 1  
            else if (choice == 2) {
                System.out.println("-----------------------------Canncel a reservation---------------------------------");
                List<Tobook> tb_lst = new ArrayList<Tobook>();
                tb_lst = tobookBean.getTobook();
                //Xet xem customer nay` co book phong` hay chua
                //Neu co thi tra ve true con khong co thi false
                boolean check = false;
                for (Tobook tb : tb_lst) {
                    if (cm.getIdCustomer() == null) {
                        break;
                    }
                    if (tb.getIdCustomer() == cm.getIdCustomer()) {
                        check = true;
                        break;
                    }
                }

                //Truong hop customer da thoat khoi he thong roi dang nhap lai
                //Check xem idenitity card cua customer da book phong trong he thong chua
                //Neu booked thi check = True
                List<Customer> cm_lst = new ArrayList<Customer>();
                cm_lst = customerBean.getCustomers();
                for (Customer c : cm_lst) {
                    if (c.getIdentityCard().equals(cm.getIdentityCard())) {
                        check = true;
                        cm.setIdCustomer(c.getIdCustomer());
                        break;
                    }
                }

                //truong hop customer da book phong
                if (check) {
                    System.out.println("-----------------------------List Book Room---------------------------------");
                    System.out.println();
                    System.out.printf("%10s %10s %15s %15s", "ID Book", "ID Room", "Date Check in", "Date Check out");
                    System.out.println();
                    System.out.println("............................................................................");
                    List<Tobook> tobook_cus = new ArrayList<Tobook>();
                    for (Tobook tb : tb_lst) {
                        for (Customer c : cm_lst) {
                            if (tb.getIdCustomer() == c.getIdCustomer() && c.getIdentityCard().equals(cm.getIdentityCard())) {
                                System.out.printf("%10s %10s %15s %15s", tb.getIdBook(), tb.getIdRoom(), tb.getDateCheckin(), tb.getDateCheckout());
                                System.out.println();
                                tobook_cus.add(tb);
                            }
                        }
                    }
                    System.out.println(".............................................................................");
                    System.out.println();
                    System.out.println();
                    System.out.print("Enter id book you want to cancle:  ");
                    try {
                        int idbook = Integer.parseInt(sc.nextLine());
                        boolean checkidbook = false;
                        if (tobook_cus.isEmpty()) {
                            checkidbook = false;
                        } else {
                            for (Tobook tb : tobook_cus) {
                                if (tb.getIdBook() == idbook) {
                                    checkidbook = true;
                                    break;
                                }
                            }
                        }
                        if (checkidbook) {
                            //xoa book da dat
                            tobookBean.deleteBooked(idbook);
                            //xoa customer luon vi customer nay khong con la khach hang cua minh nua
                            //luc nay` anh xa tren le tan thi khong thay xuat hien thang nay nua
                            customerBean.deleteCustomer(cm.getIdCustomer());
                            System.out.println("........................................................");
                            System.out.println("Delete your booked succsessfully! Thanks you very much!");
                            System.out.println("........................................................");
                            this.showGUICustomer();

                        } else {
                            System.out.println("...........................................................");
                            System.out.println("ID book not correct! Please check Input id and try it again!");
                            System.out.println("...........................................................");
                            this.showGUICustomer();
                        }

                    } catch (NumberFormatException ex) {
                        System.out.println("...........................................................");
                        System.out.println("ID book not correct! Please check Input id and try it again!");
                        System.out.println("...........................................................");
                        this.showGUICustomer();
                    }
                } else {
                    //Truong hop thang nay chua bao gio dat phong
                    System.out.println(".............................................................");
                    System.out.println("You never to book room! Please check to book and try it again!");
                    System.out.println(".............................................................");
                    this.showGUICustomer();
                }

            }//end choice == 2
            else if (choice == 3) {

                System.out.println("------------------------Pay a bill----------------------------");
                System.out.println(".............................................................");
                System.out.print("Enter your bill id:   ");
                int idbill = 0;
                try {
                    idbill = Integer.parseInt(sc.nextLine());
                } catch (NumberFormatException ex) {
                    System.out.println("............................................................");
                    System.out.println("Bill ID not correct! Please check bill id and try it again!");
                    System.out.println("............................................................");
                    this.showGUICustomer();
                    continue;
                }
                System.out.println(".............................................................");
                List<Bill> bills = new ArrayList<Bill>();
                bills = billBean.getBills();
                int amounts = 0;
                boolean check = false;
                for (Bill b : bills) {
                    if (b.getIdBill() == idbill) {
                        check = true;
                        amounts = b.getAmounts();
                        break;
                    }
                }
                if (check) {
                    System.out.println("...........................");
                    System.out.println("Your amounts:   " + amounts);
                    System.out.println("...........................");
                    this.showGUICustomer();

                } else {
                    System.out.println("............................................................");
                    System.out.println("Bill ID not correct! Please check bill id and try it again!");
                    System.out.println("............................................................");
                    this.showGUICustomer();
                }
            } //end choice == 3
            else {
                break;
            }
        }
    }
    //------------------------------------------End Customer-------------------------------------------------

    //------------------------------------------Le tan-----------------------------------------------------
    private void showGUIRec() {
        System.out.println("-----------------------------------------------------------------------------");
        System.out.println("=================== Welcome to Receptionist System ========================= ");
        System.out.println("-----------------------------------------------------------------------------");
        System.out.printf("%30s %40s", "1. List free room status", "2. Check in for a customer\n");
        System.out.printf("%30s %40s", "3. List not free room  ", "4. Check out for a customer\n");
        System.out.printf("%30s %40s", "5. Create a bill", "6. Check List room booked\n");
        System.out.printf("%30s %40s", "7. Agree to book", "8. Refuse to book\n");
        System.out.printf("%30s %40s", "9. List customer in hotel", "10. List customer book confirmed\n");
        System.out.println("-----------------------------------------------------------------------------");
        System.out.println();
        System.out.print("Enter choice:  ");

    }

    private void mainRec() throws NamingException, ParseException {
        this.showGUIRec();

        Scanner sc = new Scanner(System.in);

        int choice = 0;

        //connect SessionBeanRemote
        StatusSessionBeanRemote statusBean = (StatusSessionBeanRemote) ctx.lookup(getJNDI_Status());
        RoomSessionBeanRemote roomBean = (RoomSessionBeanRemote) ctx.lookup(getJNDI_Room());
        CheckinSessionBeanRemote ckinBean = (CheckinSessionBeanRemote) ctx.lookup(getJNDI_Checkin());
        CustomerBookedSessionBeanRemote cusBookedBean = (CustomerBookedSessionBeanRemote) ctx.lookup(getJNDI_CustomerBooked());
        CheckoutSessionBeanRemote ckoutBean = (CheckoutSessionBeanRemote) ctx.lookup(getJNDI_Checkout());
        CustomerSessionBeanRemote customerBean = (CustomerSessionBeanRemote) ctx.lookup(getJNDI_Customer());
        TobookSessionBeanRemote tobookBean = (TobookSessionBeanRemote) ctx.lookup(getJNDI_Tobook());

        while (choice != 11) {

            //Use this approach because nextInt will cause error to nextLine()
            choice = Integer.parseInt(sc.nextLine());

            Date today = new Date();
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.HOUR_OF_DAY, 0);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            cal.set(Calendar.MILLISECOND, 0);
            today = cal.getTime();

            Date todayString = new Date();
            SimpleDateFormat datecover = new SimpleDateFormat("dd/MM/yyyy");
            String todayS = datecover.format(todayString);

            if (choice == 1) {

                List<Status> status_list = new ArrayList<Status>();
                status_list = statusBean.getStatuss();
                if (!status_list.isEmpty()) {
                    System.out.println("-----------------------------Status rooms---------------------------------");
                    System.out.println();
                    System.out.printf("%10s %10s %15s %15s %15s", "Status ID", "Room id", "Status", "date checkin", "date checkout");
                    System.out.println();
                    System.out.println("............................................................................");
                    for (Status s : status_list) {
                        int number = s.getStatus();
                        String str = "";
                        if (number == 0) {
                            str = "Free room";
                        } else if (number == 2) {
                            str = "Room booked";
                        }
                        if (number == 0 || number == 2) {
                            System.out.format("%10s %10s %15s %15s %15s", s.getIdStatus(), s.getIdRoom(), str, s.getDateCheckin(), s.getDateCheckout());
                            System.out.println();
                        }
                    }
                    System.out.println("............................................................................");
                    System.out.println();

                    this.showGUIRec();
                } else {
                    System.out.println(".....................................................................");
                    System.out.println("You have not stastus room! Please update status room and try it again!");
                    System.out.println(".....................................................................");
                    System.out.println();
                    this.showGUIRec();
                }

            } // end choice == 1
            else if (choice == 2) {
                System.out.println("-----------------------------Check in for a customer---------------------------------");
                System.out.println();
                System.out.println("......................................................................................");

                Checkin ckin = new Checkin();
                System.out.print("Enter customer's name:                ");
                ckin.setName(sc.nextLine());
                System.out.print("Enter customer's identity card:       ");
                ckin.setIdentityCard(sc.nextLine());
                System.out.print("Enter customer's phone:               ");

                String phone = sc.nextLine();
                try {
                    int p = Integer.parseInt(phone);
                    ckin.setPhone(phone);

                } catch (NumberFormatException ex) {
                    System.out.println("............................................................");
                    System.out.println("Your phone number not true! Please check it and try it again!");
                    System.out.println("............................................................");
                    System.out.println();
                    this.showGUIRec();
                    continue;
                }
                System.out.print("Enter customer's address:             ");
                ckin.setAddress(sc.nextLine());

                Status st = new Status();

                System.out.println("Date Checkin (dd/MM/yyyy): " + todayS);
                System.out.print("Enter date Checkout (dd/MM/yyyy):      ");
                String dateout = sc.nextLine();
                Date date2;
                try {
                    date2 = new SimpleDateFormat("dd/MM/yyyy").parse(dateout);
                    if (date2.compareTo(today) < 0) {
                        System.out.println(".............................................................");
                        System.out.println("Date not correct! Date out can't < Date checkin! Try it again!");
                        System.out.println(".............................................................");
                        this.showGUIRec();
                        continue;
                    }
                } catch (ParseException ex) {
                    System.out.println("...........................................................");
                    System.out.println("Date not correct! Please check Input date and try it again!");
                    System.out.println("...........................................................");
                    this.showGUIRec();
                    continue;
                }

                //Lay List Room con trong hoac da duoc dat nhung co the cho khach vao 
                List<Status> freeroom_lst = new ArrayList<Status>();
                List<Status> freeroombooked_lst = new ArrayList<Status>();
                List<Status> roomcancheckin_lst = new ArrayList<Status>();

                freeroom_lst = statusBean.freeRoom();
                freeroombooked_lst = statusBean.freeRoomBook();
                for (Status s : freeroombooked_lst) {
                    if (compareTwoDay(dateout, s.getDateCheckin()) <= 0) {
                        roomcancheckin_lst.add(s);
                    }
                }

                if (freeroom_lst.isEmpty() && roomcancheckin_lst.isEmpty()) {
                    System.out.println(".....................................................");
                    System.out.println("All room is full! You can not checkin for a customer!");
                    System.out.println(".....................................................");
                    this.showGUIRec();
                } else {
                    List<Room> rooms = new ArrayList<Room>();
                    rooms = roomBean.getRoom();
                    List<Room> rooms_free = new ArrayList<Room>();

                    //add room free -> room_free
                    if (!freeroom_lst.isEmpty()) {
                        for (Status s : freeroom_lst) {
                            for (Room r : rooms) {
                                if (r.getIdRoom() == s.getIdRoom()) {
                                    rooms_free.add(r);
                                }
                            }
                        }
                    }
                    if (!roomcancheckin_lst.isEmpty()) {
                        //add room booked -> rooms_free
                        for (Status s : roomcancheckin_lst) {
                            for (Room r : rooms) {
                                if (r.getIdRoom() == s.getIdRoom() && !rooms_free.contains(r)) {
                                    rooms_free.add(r);
                                }
                            }
                        }
                    }
                    //lay danh sach room booked hom nay
                    List<Room> r_booked = new ArrayList<Room>();
                    List<Status> st_booked_today = new ArrayList<Status>();

                    for (Status s : freeroombooked_lst) {
                        if (compareTwoDay(todayS, s.getDateCheckin()) == 0) {
                            st_booked_today.add(s);
                        }
                    }
                    if (!st_booked_today.isEmpty()) {
                        //add room booked -> rooms_free
                        for (Status s : st_booked_today) {
                            for (Room r : rooms) {
                                if (r.getIdRoom() == s.getIdRoom() && !r_booked.contains(r)) {
                                    r_booked.add(r);
                                }
                            }
                        }
                    }
                    System.out.println("---------------------List Free Room-----------------------");
                    System.out.println();
                    System.out.printf("%10s %10s %20s", "Room ID", "Type", "Price");
                    System.out.println();
                    System.out.println("........................................................");
                    for (Room r : rooms_free) {
                        System.out.printf("%10s %10s %20s", r.getIdRoom(), r.getType(), r.getPrice());
                        System.out.println();
                    }
                    System.out.println("........................................................");
                    System.out.println();
                    //list room booked today
                    System.out.println("-----------------List Booked Room Today------------------");
                    System.out.println();
                    System.out.println("..........................................................");

                    if (r_booked.isEmpty()) {
                        System.out.println("----------Hotel don't have room booked today----------");
                        System.out.println("......................................................");
                    } else {
                        System.out.printf("%10s %10s %20s", "Room ID", "Type", "Price");
                        System.out.println();
                        System.out.println("......................................................");
                        for (Room r : r_booked) {
                            System.out.printf("%10s %10s %20s", r.getIdRoom(), r.getType(), r.getPrice());
                            System.out.println();
                        }
                    }
                    System.out.println(".........................................................");
                    System.out.println();
                    System.out.println();
                    System.out.print("Enter id room you want to checkin:    ");
                    try {
                        //kiem tra du lieu dau vao co phai la integer khong
                        //kiem tra xem idroom nay co hop le hay khong
                        int idroom = Integer.parseInt(sc.nextLine());
                        boolean check = false;
                        for (Room r : rooms_free) {
                            if (r.getIdRoom() == idroom) {
                                check = true;
                                break;
                            }
                        }
                        for (Room r : r_booked) {
                            if (r.getIdRoom() == idroom) {
                                check = true;
                                break;
                            }
                        }
                        if (check) {

                            st.setIdRoom(idroom);
                            //status bang 1 vi` phong` nay` co nguoi o 
                            st.setStatus(1);
                            //id room va id status bang nhau
                            st.setIdStatus(idroom);
                            //update status -> room = 0 => room = 1 
                            //update date in 
                            st.setDateCheckin(todayS);
                            //update date out
                            st.setDateCheckout(dateout);
                            //update
                            statusBean.updateStatus(st);

                            ckin.setIdRoom(idroom);
                            //tao checkin 
                            ckinBean.createCheckin(ckin);

                            //detele dat phong
                            //lay danh sach cac phong da dat ra
                            //check xem thang customer nay da dat phong chua
                            //neu customer da dat phong thi xoa no di
                            //dong thoi xoa di trang thai phong customer da dat trong status databse
                            List<Status> st_booked = new ArrayList<Status>();
                            st_booked = statusBean.freeRoomBook();
                            if (!st_booked.isEmpty()) {
                                for (Status s : st_booked) {
                                    if (s.getIdRoom() == idroom && todayS.equals(s.getDateCheckin()) && dateout.equals(s.getDateCheckout())) {
                                        statusBean.deleteStatus(s.getIdStatus());
                                    }
                                }
                            }
                            List<Customerbooked> customerbooked_lst = new ArrayList<Customerbooked>();
                            customerbooked_lst = cusBookedBean.getCustomerBookedlist();
                            if (!customerbooked_lst.isEmpty()) {
                                for (Customerbooked cb : customerbooked_lst) {
                                    if (idroom == cb.getIdRoom() && today.equals(cb.getDatecheckin()) && dateout.equals(cb.getDatecheckout())) {
                                        cusBookedBean.deleteCustomerBooked(cb.getIdCusbooked());
                                        break;
                                    }
                                }
                            }
                            System.out.println(".....................................");
                            System.out.println("Check in for a customer successfully!");
                            System.out.println(".....................................");
                            this.showGUIRec();
                        } else {
                            System.out.println(".........................................................");
                            System.out.println("ID Room not correct! You must check id and try it again !");
                            System.out.println(".........................................................");
                            this.showGUIRec();

                        }
                    } catch (NumberFormatException ex) {
                        System.out.println(".........................................................");
                        System.out.println("ID Room not correct! You must check id and try it again !");
                        System.out.println(".........................................................");
                        this.showGUIRec();

                    }
                }

            }// end choice == 2
            else if (choice == 3) {

                //Lay List Not Room con trong hoac da duoc dat nhung co the cho khach vao 
                List<Status> notfreeroom_lst = new ArrayList<Status>();

                notfreeroom_lst = statusBean.NotfreeRoom();

                if (notfreeroom_lst.isEmpty()) {
                    System.out.println(".....................................................");
                    System.out.println("All room is free! Nobody was checked in in the hotel!");
                    System.out.println(".....................................................");
                    this.showGUIRec();
                } else {
                    List<Room> rooms = new ArrayList<Room>();
                    rooms = roomBean.getRoom();
                    List<Room> not_rooms_free = new ArrayList<Room>();

                    //add room free -> room_free
                    for (Status s : notfreeroom_lst) {
                        for (Room r : rooms) {
                            if (r.getIdRoom() == s.getIdRoom()) {
                                not_rooms_free.add(r);
                            }
                        }
                    }

                    System.out.println("---------------------List Not Free Room------------------");
                    System.out.println();
                    System.out.printf("%10s %10s %20s", "Room ID", "Type", "Price");
                    System.out.println();
                    System.out.println("........................................................");
                    for (Room r : not_rooms_free) {
                        System.out.printf("%10s %10s %20s", r.getIdRoom(), r.getType(), r.getPrice());
                        System.out.println();
                    }
                    System.out.println("........................................................");
                    System.out.println();
                    System.out.println();
                }
                this.showGUIRec();

            }// end choice == 3
            else if (choice == 4) {

                //Lay List Not Room con trong hoac da duoc dat nhung co the cho khach vao 
                List<Status> notfreeroom_lst = new ArrayList<Status>();

                notfreeroom_lst = statusBean.NotfreeRoom();

                if (notfreeroom_lst.isEmpty()) {
                    System.out.println(".....................................................");
                    System.out.println("All room is free! You can not check out for a customer!");
                    System.out.println(".....................................................");
                    this.showGUIRec();
                } else {

                    List<Room> rooms = new ArrayList<Room>();
                    rooms = roomBean.getRoom();
                    List<Room> not_rooms_free = new ArrayList<Room>();

                    //add room free -> room_free
                    for (Status s : notfreeroom_lst) {
                        for (Room r : rooms) {
                            if (r.getIdRoom() == s.getIdRoom()) {
                                not_rooms_free.add(r);
                            }
                        }
                    }

                    System.out.println("---------------------List Room not free------------------");
                    System.out.println();
                    System.out.printf("%10s %10s %20s", "Room ID", "Type", "Price");
                    System.out.println();
                    System.out.println("........................................................");
                    for (Room r : not_rooms_free) {
                        System.out.printf("%10s %10s %20s", r.getIdRoom(), r.getType(), r.getPrice());
                        System.out.println();
                    }
                    System.out.println("........................................................");
                    System.out.println();
                    System.out.println();
                    System.out.print("Enter id room you want to checkout:  ");
                    try {
                        //kiem tra du lieu dau vao co phai la integer khong
                        //kiem tra xem idroom nay co hop le hay khong
                        int idroom = Integer.parseInt(sc.nextLine());
                        boolean check = false;
                        for (Room r : not_rooms_free) {
                            if (r.getIdRoom() == idroom) {
                                check = true;
                                break;
                            }
                        }
                        if (check) {

                            //tao checkout 
                            Checkout co = new Checkout();
                            List<Checkin> ci_lst = new ArrayList<Checkin>();
                            ci_lst = ckinBean.getChekinlist();
                            for (Checkin ci : ci_lst) {
                                if (ci.getIdRoom() == idroom) {
                                    co.setName(ci.getName());
                                    co.setIdentityCard(ci.getIdentityCard());
                                    co.setPhone(ci.getPhone());
                                    co.setIdRoom(idroom);
                                    co.setAddress(ci.getAddress());
                                }
                            }

                            List<Status> status_lst = new ArrayList<Status>();
                            status_lst = statusBean.NotfreeRoom();
                            for (Status s : status_lst) {
                                if (s.getIdRoom() == idroom) {
                                    co.setDateCheckin(s.getDateCheckin());
                                    break;
                                }
                            }

                            co.setDateCheckout(todayS);

                            ckoutBean.createCheckout(co);

                            //update status ve lai bang 0 (phong` trong^')
                            Status st = new Status();
                            st.setIdRoom(idroom);
                            //status bang 0 vi phong nay trong 
                            st.setIdStatus(0);
                            //id room va id status bang nhau
                            st.setIdStatus(idroom);
                            //set datein and dateout ve default value is 0/0/0
                            st.setDateCheckin("0/0/0");
                            st.setDateCheckout("0/0/0");
                            //update status -> room = 0 => room = 1 
                            statusBean.updateStatus(st);
                            //delete thang check in 
                            ckinBean.deleteCheckin(idroom);

                        } else {
                            System.out.println(".........................................................");
                            System.out.println("ID Room not correct! You must check id and try it again !");
                            System.out.println(".........................................................");
                            this.showGUIRec();
                            continue;
                        }
                    } catch (NumberFormatException ex) {
                        System.out.println(".........................................................");
                        System.out.println("ID Room not correct! You must check id and try it again !");
                        System.out.println(".........................................................");
                        this.showGUIRec();
                        continue;
                    }
                }
                System.out.println("......................................");
                System.out.println("Check out for a customer successfully!");
                System.out.println("......................................");
                this.showGUIRec();

            }// end choice == 4
            else if (choice == 5) {

                //Lay list Check out
                List<Checkout> co_lst = new ArrayList<Checkout>();
                co_lst = ckoutBean.getCheckOutlist();

                //Lay list check out ngay hom nay ra
                List<Checkout> co_today_lst = new ArrayList<Checkout>();

                for (Checkout co : co_lst) {
                    if (co.getDateCheckout().equals(todayS)) {
                        co_today_lst.add(co);
                    }
                }

                if (co_today_lst.isEmpty()) {
                    System.out.println(".................................................................");
                    System.out.println("You don't have customer check out! Please check out and try again!");
                    System.out.println(".................................................................");
                    this.showGUIRec();

                } else {

                    System.out.println("---------------------List Check out today--------------------------");
                    System.out.println();
                    System.out.println("....................................................................");
                    System.out.printf("%15s %15s %20s ", "Check out ID", "ID Room", "Name");
                    System.out.println();
                    for (Checkout co : co_today_lst) {
                        System.out.printf("%15s %15s %20s ", co.getIdCheckout(), co.getIdRoom(), co.getName());
                        System.out.println();
                    }
                    System.out.println("....................................................................");
                    System.out.println();
                    System.out.println();
                    System.out.print("Enter id check out you want to create bill:   ");
                    try {
                        //kiem tra du lieu dau vao co phai la integer khong
                        //kiem tra xem id checkout nay co hop le hay khong
                        int idco = Integer.parseInt(sc.nextLine());
                        System.out.print("Enter Service charge  :     ");
                        int charge = Integer.parseInt(sc.nextLine());
                        int idroom = 0;
                        long numofday = 0;
                        boolean check = false;
                        for (Checkout co : co_today_lst) {
                            if (co.getIdCheckout() == idco) {
                                check = true;
                                idroom = co.getIdRoom();
                                numofday = diffDays(co.getDateCheckin(), co.getDateCheckout());
                                break;
                            }
                        }
                        if (check) {
                            int price = 0;
                            List<Room> room_lst = new ArrayList<Room>();
                            room_lst = roomBean.getRoom();
                            for (Room r : room_lst) {
                                if (r.getIdRoom() == idroom) {
                                    price = r.getPrice();
                                    break;
                                }
                            }
                            System.out.println("---------------------Create a bill--------------------------");
                            System.out.println();
                            System.out.println("............................................................");
                            System.out.println("Number of nights: " + numofday);
                            double money = price * numofday;
                            System.out.println("Room charge     : " + String.format("%,.0f", money));
                            System.out.println("Service charge  : " + String.format("%,.0f", (double) charge));
                            double sum = money + charge;
                            System.out.println("The total amount: " + String.format("%,.0f", sum));
                            System.out.println("............................................................");
                            Bill bill = new Bill();
                            bill.setIdCheckout(idco);
                            bill.setAmounts((int) sum);
                        } else {
                            System.out.println(".................................................................");
                            System.out.println("Your id check out not correct! Please id check out and try again!");
                            System.out.println(".................................................................");
                            this.showGUIRec();
                        }
                    } catch (NumberFormatException ex) {
                        System.out.println("...................................................");
                        System.out.println("Your id Enter not correct! Please id and try again!");
                        System.out.println("...................................................");
                        this.showGUIRec();
                        continue;
                    }
                    this.showGUIRec();
                }

            }// end choice == 5
            else if (choice == 6) {

                List<Status> st_booked = new ArrayList<Status>();
                st_booked = statusBean.freeRoomBook();
                if (st_booked.isEmpty()) {
                    System.out.println(".......................................................");
                    System.out.println(" All room are vacancy! Nobody to book room in the hotel");
                    System.out.println(".......................................................");
                    this.showGUIRec();
                } else {
                    System.out.println("-----------------------------Status rooms-----------------------------------");
                    System.out.println();
                    System.out.printf("%10s %10s %15s %15s %15s", "Status ID", "Room id", "Status", "date checkin", "date checkout");
                    System.out.println();
                    System.out.println("............................................................................");

                    for (Status s : st_booked) {
                        int number = s.getStatus();
                        String str = "";
                        if (number == 2) {
                            str = "Room booked";
                            System.out.format("%10s %10s %15s %15s %15s", s.getIdStatus(), s.getIdRoom(), str, s.getDateCheckin(), s.getDateCheckout());
                            System.out.println();
                        }
                    }
                    System.out.println("............................................................................");
                    System.out.println();
                    this.showGUIRec();
                }
            }// end choice == 6
            else if (choice == 7) {
                //Lay database tu customer da dat online, toBook and Customer
                List<Tobook> tb_lst = new ArrayList<Tobook>();
                tb_lst = tobookBean.getTobook();
                Tobook tb = new Tobook();
                List<Customer> cm_lst = new ArrayList<Customer>();
                cm_lst = customerBean.getCustomers();
                Customer cus = new Customer();
                //Hien thi danh sach khach hang da dat phong online
                if (tb_lst.isEmpty()) {
                    System.out.println("............................");
                    System.out.println(" Nobody to book room online!");
                    System.out.println("............................");
                    this.showGUIRec();
                    continue;
                } else {
                    System.out.println("------------------------------------------------List Customer to book online-------------------------------------------------");
                    System.out.println();
                    System.out.printf("%15s %15s %10s %10s %15s %20s", "ID Book", "Name Customer", "Phone" , "ID Room", "Date Check in", "Date Check out");
                    System.out.println();
                    System.out.println(".............................................................................................................................");
                    for (Tobook t : tb_lst) {
                        for (Customer x:cm_lst) {
                            if (t.getIdCustomer() == x.getIdCustomer()) {
                                System.out.printf("%15s %15s %10s %10s %15s %20s", t.getIdBook(), x.getName(), x.getPhone(), t.getIdRoom(), t.getDateCheckin(), t.getDateCheckout());
                                System.out.println();
                                break;
                            }
                        }
                    }
                    System.out.println("............................................................................................................................");

                }
                //Yeu cau nhap vao id book ma le tan muon dat
                System.out.print("Enter id book you want to check customer can book the room:   ");
                try {
                    int idbook = Integer.parseInt(sc.nextLine());
                    //kiem tra xem so nay co trong list to book khong
                    boolean check = false;
                    for (Tobook t : tb_lst) {
                        if (t.getIdBook() == idbook) {
                            check = true;
                            tb.setIdBook(idbook);
                            tb.setDateCheckin(t.getDateCheckin());
                            tb.setDateCheckout(t.getDateCheckout());
                            tb.setIdCustomer(t.getIdCustomer());
                            tb.setIdRoom(t.getIdRoom());
                            break;
                        }
                    }
                    //kiem tra xem la cho khach dat phong duoc hay khong
                    if (check) {
                        //list status = 1
                        List<Status> st_not_freeroom_lst = new ArrayList<Status>();
                        st_not_freeroom_lst = statusBean.NotfreeRoom();
                        //list status = 2
                        List<Status> st_roombooked_lst = new ArrayList<Status>();
                        st_roombooked_lst = statusBean.freeRoomBook();
                        //list status = 0
                        List<Status> st_freeroom = new ArrayList<Status>();
                        st_freeroom = statusBean.freeRoom();
                        boolean checkcan = false;
                        boolean checkroom = false;
                        for (Status st : st_freeroom) {
                            if (st.getIdRoom() == tb.getIdRoom()) {
                                checkroom = true;
                            }
                        }
                        for (Status st : st_roombooked_lst) {
                            if (st.getIdRoom() == tb.getIdRoom() && compareTwoDay(tb.getDateCheckout(), st.getDateCheckin()) <= 0) {
                                checkcan = true;
                            }
                        }
                        for (Status st : st_not_freeroom_lst) {
                            if (st.getIdRoom() == tb.getIdRoom() && compareTwoDay(tb.getDateCheckout(), st.getDateCheckin()) > 0) {
                                checkcan = false;
                            }
                        }
                        //Lay thong tin cua khach hang
                        for (Customer c : cm_lst) {
                            if (c.getIdCustomer() == tb.getIdCustomer()) {
                                cus.setIdCustomer(tb.getIdCustomer());
                                cus.setAddress(c.getAddress());
                                cus.setIdentityCard(c.getIdentityCard());
                                cus.setName(c.getName());
                                cus.setPhone(c.getPhone());
                            }
                        }
                        if (checkcan) {
                            //Xac nhan cho khach dat phong
                            System.out.println("----------------------------------Customer Information---------------------------------");
                            System.out.println();
                            System.out.printf("%15s %15s %10s %15s %20s", "Name", "Phone", "ID Room", "Date Check in", "Date Check out");
                            System.out.println();
                            System.out.println(".......................................................................................");
                            System.out.printf("%15s %15s %10s %15s %20s", cus.getName(), cus.getPhone(), tb.getIdRoom(), tb.getDateCheckin(), tb.getDateCheckout());
                            System.out.println();
                            System.out.println(".......................................................................................");
                            System.out.print("The customer can to book this room! Please enter (Y/N) to confirm the reservation:       ");
                            String cf = sc.nextLine();
                            if (cf.equals("Y")) {
                                //Tao database customerbooked
                                Customerbooked cusBooked = new Customerbooked();
                                cusBooked.setName(cus.getName());
                                cusBooked.setAddress(cus.getAddress());
                                cusBooked.setDatecheckin(tb.getDateCheckin());
                                cusBooked.setDatecheckout(tb.getDateCheckout());
                                cusBooked.setIdentityCard(cus.getIdentityCard());
                                cusBooked.setIdRoom(tb.getIdRoom());
                                cusBooked.setPhone(cus.getPhone());
                                cusBookedBean.createCustomerBooked(cusBooked);
                                //tao status room
                                Status stnew = new Status();
                                stnew.setDateCheckin(tb.getDateCheckin());
                                stnew.setDateCheckout(tb.getDateCheckout());
                                stnew.setIdRoom(tb.getIdRoom());
                                stnew.setStatus(2);
                                stnew.setIdStatus(tb.getIdRoom());
                                statusBean.createStatus(stnew);
                                //xoa customer
                                customerBean.deleteCustomer(cus.getIdCustomer());
                                //xoa tobook
                                tobookBean.deleteBooked(idbook);
                                System.out.println("...............................................................................");
                                System.out.println("----------------------- confirm the reservation successfully!------------------");
                                System.out.println("...............................................................................");
                                this.showGUIRec();

                            } else {
                                System.out.println(".........................................................................");
                                System.out.println("-----------------------Refuse to successfully book a room----------------");
                                System.out.println("----------------------The to book and customer was delete----------------");
                                System.out.println(".........................................................................");
                                tobookBean.deleteBooked(idbook);
                                customerBean.deleteCustomer(cus.getIdCustomer());
                                this.showGUIRec();
                            }
                        } else if (!checkcan && checkroom) {
                            //Xac nhan cho khach dat phong
                            System.out.println("----------------------------------Customer Information---------------------------------");
                            System.out.println();
                            System.out.printf("%15s %15s %10s %15s %20s", "Name", "Phone", "ID Room", "Date Check in", "Date Check out");
                            System.out.println();
                            System.out.println(".......................................................................................");
                            System.out.printf("%15s %15s %10s %15s %20s", cus.getName(), cus.getPhone(), tb.getIdRoom(), tb.getDateCheckin(), tb.getDateCheckout());
                            System.out.println();
                            System.out.println(".......................................................................................");
                            System.out.print("The customer can to book this room! Please enter (Y/N) to confirm the reservation:       ");
                            String cf = sc.nextLine();
                            if (cf.equals("Y")) {
                                //Tao database customerbooked
                                Customerbooked cusBooked = new Customerbooked();
                                cusBooked.setName(cus.getName());
                                cusBooked.setAddress(cus.getAddress());
                                cusBooked.setDatecheckin(tb.getDateCheckin());
                                cusBooked.setDatecheckout(tb.getDateCheckout());
                                cusBooked.setIdentityCard(cus.getIdentityCard());
                                cusBooked.setIdRoom(tb.getIdRoom());
                                cusBooked.setPhone(cus.getPhone());
                                cusBookedBean.createCustomerBooked(cusBooked);
                                //tao status room
                                Status stnew = new Status();
                                stnew.setDateCheckin(tb.getDateCheckin());
                                stnew.setDateCheckout(tb.getDateCheckout());
                                stnew.setIdRoom(tb.getIdRoom());
                                stnew.setIdStatus(tb.getIdRoom());
                                stnew.setStatus(2);
                                statusBean.createStatus(stnew);
                                //xoa customer
                                customerBean.deleteCustomer(cus.getIdCustomer());
                                //xoa tobook
                                tobookBean.deleteBooked(idbook);
                                System.out.println("...............................................................................");
                                System.out.println("----------------------- confirm the reservation successfully!------------------");
                                System.out.println("...............................................................................");
                                this.showGUIRec();
                            } else {
                                System.out.println(".........................................................................");
                                System.out.println("-----------------------Refuse to successfully book a room----------------");
                                System.out.println("----------------------The to book and customer was delete----------------");
                                System.out.println(".........................................................................");
                                tobookBean.deleteBooked(idbook);
                                customerBean.deleteCustomer(cus.getIdCustomer());
                                this.showGUIRec();

                            }
                        } else {
                            System.out.println(".......................................................................................");
                            System.out.println("-------------------------This room have person stay or booked!-------------------------");
                            System.out.println("----------------------Please call the customer and cancel a reservation!---------------");
                            System.out.println(".......................................................................................");
                            System.out.println("----------------------------------Customer Information---------------------------------");
                            System.out.println();
                            System.out.printf("%15s %15s %10s %15s %20s", "Name", "Phone", "ID Room", "Date Check in", "Date Check out");
                            System.out.println();
                            System.out.println(".......................................................................................");
                            System.out.printf("%15s %15s %10s %15s %20s", cus.getName(), cus.getPhone(), tb.getIdRoom(), tb.getDateCheckin(), tb.getDateCheckout());
                            System.out.println();
                            System.out.println(".......................................................................................");
                            System.out.println("-----------------------------Refuse to successfully book a room------------------------");
                            System.out.println("-----------------------------The to book and customer was delete-----------------------");
                            System.out.println(".......................................................................................");
                            tobookBean.deleteBooked(idbook);
                            customerBean.deleteCustomer(cus.getIdCustomer());
                            this.showGUIRec();
                        }
                    } else {
                        System.out.println(".....................");
                        System.out.println(" ID book not correct!");
                        System.out.println(".....................");
                        this.showGUIRec();
                    }

                } catch (NumberFormatException ex) {
                    System.out.println("..........................");
                    System.out.println(" ID book must is e number!");
                    System.out.println("..........................");
                    this.showGUIRec();

                }

            }// end choice == 7
            else if (choice == 8) {
                //hien danh sach customerbooked
                List<Customerbooked> cmb_lst = new ArrayList<Customerbooked>();
                cmb_lst = cusBookedBean.getCustomerBookedlist();
                if (cmb_lst.isEmpty()) {
                    System.out.println(".................................");
                    System.out.println(" Nobody to book room in the hotel");
                    System.out.println(".................................");
                    this.showGUIRec();
                } else {
                    System.out.println("----------------------------------Customer Booked Information---------------------------------");
                    System.out.println();
                    System.out.printf("%15s %15s %10s %15s %20s", "ID Customer", "Name", "ID Room", "Date Check in", "Date Check out");
                    System.out.println();
                    System.out.println("..............................................................................................");
                    System.out.println();
                    for (Customerbooked cb : cmb_lst) {
                        System.out.printf("%15s %15s %10s %15s %20s", cb.getIdCusbooked(), cb.getName(), cb.getIdRoom(), cb.getDatecheckin(), cb.getDatecheckout());
                        System.out.println();
                    }
                    System.out.println("..............................................................................................");
                    System.out.println();

                    //Nhap vao id muon xoa
                    System.out.print("Enter id customer you want to cancel a reservation:       ");
                    try {
                        int idcmb = Integer.parseInt(sc.nextLine());
                        int idroomcmb = 0;
                        String datein = "";
                        String dateout = "";
                        //kiem tra xem so nay co trong list customer khong
                        boolean check = false;
                        for (Customerbooked cb : cmb_lst) {
                            if (cb.getIdCusbooked() == idcmb) {
                                check = true;
                                idroomcmb = cb.getIdRoom();
                                datein = cb.getDatecheckin();
                                dateout = cb.getDatecheckout();
                                break;
                            }
                        }
                        if (check) {
                            List<Status> st_lst = statusBean.freeRoomBook();
                            int idst = 0;
                            for (Status st : st_lst) {
                                if (st.getIdRoom() == idroomcmb && st.getDateCheckin().equals(datein) && st.getDateCheckout().equals(dateout)) {
                                    idst = st.getIdStatus();
                                    break;
                                }
                            }
                            //Xoa khoi he thong status va customerbooked
                            statusBean.deleteStatus(idst);
                            cusBookedBean.deleteCustomerBooked(idcmb);
                            System.out.println("...............................................................................");
                            System.out.println("----------------------- Cancle the reservation successfully!------------------");
                            System.out.println("...............................................................................");
                            this.showGUIRec();
                        } else {
                            System.out.println(".......................................");
                            System.out.println(" ID customer not correct! try it agian!");
                            System.out.println(".......................................");
                            this.showGUIRec();
                        }

                    } catch (EnumConstantNotPresentException ex) {
                        System.out.println(".......................................");
                        System.out.println(" ID customer not correct! try it agian!");
                        System.out.println(".......................................");
                        this.showGUIRec();

                    }
                }
            }// end choice == 8
            else if (choice == 9) {
                List<Checkin> ckin = new ArrayList<Checkin>();
                ckin = ckinBean.getChekinlist();
                if (ckin.isEmpty()) {
                    System.out.println("................................");
                    System.out.println(" The hotel don't have customer! ");
                    System.out.println("................................");
                    this.showGUIRec();
                } else {
                    System.out.println("----------------------------------Customer in the hotel--------------------------------------");
                    System.out.println();
                    System.out.printf("%10s %15s %10s %20s %15s", "ID ROOM", "NAME", "PHONE", "IDENTITY CARD", "ADDRESSS");
                    System.out.println();
                    System.out.println("..............................................................................................");
                    System.out.println();
                    for (Checkin ck : ckin) {
                        System.out.printf("%10s %15s %10s %20s %15s", ck.getIdRoom(), ck.getName(), ck.getPhone(), ck.getIdentityCard(), ck.getAddress());
                        System.out.println();
                    }
                    System.out.println("..............................................................................................");
                    System.out.println();

                    this.showGUIRec();
                }
            }// end choice == 9
            else if (choice == 10) {
                List<Customerbooked> cm_booked = new ArrayList<Customerbooked>();
                cm_booked = cusBookedBean.getCustomerBookedlist();
                if (cm_booked.isEmpty()) {
                    System.out.println(".................................");
                    System.out.println(" Nobody to book room in the hotel");
                    System.out.println(".................................");
                    this.showGUIRec();
                } else {
                    System.out.println("---------------------------------------Customer booked--------------------------------------");
                    System.out.println();
                    System.out.printf("%10s %10s %10s %103s %15s %15s %15s", "ID ROOM", "NAME", "PHONE NUMBER", "IDENTITY CARD", "ADDRESSS", "DATE CHECK IN", "DATE CHECK OUT");
                    System.out.println();
                    System.out.println("...................................................................................................");
                    System.out.println();
                    for (Customerbooked ck : cm_booked) {
                        System.out.printf("%10s %10s %10s %103s %15s %15s %15s", ck.getIdRoom(), ck.getName(), ck.getPhone(), ck.getIdentityCard(), ck.getAddress(), ck.getDatecheckin(), ck.getDatecheckout());
                        System.out.println();
                    }
                    System.out.println("...................................................................................................");
                    System.out.println();
                    this.showGUIRec();
                }
            }// end choice == 10
            else {
                break;
            }
        }
    }
    //------------------------------------------End Le tan-----------------------------------------------------
    //----------------------------------------Adminstrator-------------------------------------------------

    private void showGUIAdmin() {

        System.out.println("-----------------------------------------------------------------------------");
        System.out.println("=================== Welcome to Administrator System ========================= ");
        System.out.println("-----------------------------------------------------------------------------");
        System.out.printf("%30s %30s", "1. Change password", "2. List employees\n");
        System.out.printf("%30s %30s", "3. Room status", "4. Create employee\n");
        System.out.printf("%30s %30s", "5. Delete employee", "6. Create account employee\n");
        System.out.printf("%30s %30s", "7. Change account employee", "8. delete account employee\n");
        System.out.printf("%30s %30s ", "9. Show account employee", "10.Report \n");
        System.out.println("-----------------------------------------------------------------------------");

        System.out.print("Enter choice:  ");

    }

    private void mainAmin() throws NamingException {
        this.showGUIAdmin();

        Scanner sc = new Scanner(System.in);

        int choice = 0;

        //connect SessionBeanRemote
        UseradminSessionBeanRemote useradminBean = (UseradminSessionBeanRemote) ctx.lookup(getJNDI_useradmin());
        EmployeeSessionBeanRemote employeeBean = (EmployeeSessionBeanRemote) ctx.lookup(getJNDI_employee());
        StatusSessionBeanRemote statusBean = (StatusSessionBeanRemote) ctx.lookup(getJNDI_Status());
        AccountSessionBeanRemote accBean = (AccountSessionBeanRemote) ctx.lookup(getJNDI_Account());
        BillSessionBeanRemote billBean = (BillSessionBeanRemote) ctx.lookup(getJNDI_Bill());
        CheckoutSessionBeanRemote ckoutBean = (CheckoutSessionBeanRemote) ctx.lookup(getJNDI_Checkout());

        while (choice != 11) {

            //Use this approach because nextInt will cause error to nextLine()
            choice = Integer.parseInt(sc.nextLine());

            //Truong hop chon chuc nang "change password"
            if (choice == 1) {
                System.out.println("-----------------------------Change password---------------------------------");
                System.out.print("Enter new password:  ");
                String newpass1 = sc.nextLine();
                System.out.print("Enter new password:  ");
                String newpass2 = sc.nextLine();
                if (newpass1.equals(newpass2)) {
                    useradminBean.changePassword(newpass2);
                    System.out.println("............................................................................");
                    System.out.print("                      Change password successfully \n ");
                    System.out.println("............................................................................");
                    this.showGUIAdmin();

                } else {
                    System.out.println("............................................................................");
                    System.out.print("------------Change password fail! password not true! Try it again!-------------\n");
                    System.out.println("............................................................................");
                    this.showGUIAdmin();

                }

            }// end choice = 1 
            else if (choice == 2) {
                List<Employee> employees = new ArrayList<Employee>();
                employees = employeeBean.getEmployees();
                if (!employees.isEmpty()) {
                    System.out.println("----------------------------------List employee--------------------------------------");
                    System.out.println();

                    System.out.printf("%10s %15s %15s %15s %15s %10s", "EMPLOYEE ID", "NAME", "PHONE", "POSITION", "SALARY", "DATE START");
                    System.out.println();
                    System.out.println(".................................................................................");
                    for (Employee e : employees) {
                        System.out.format("%10s %15s %15s %15s %15s %10s", e.getIdEmployee(), e.getName(), e.getPhone(), e.getPosition(), e.getSalary(), e.getDateStart());
                        System.out.println();
                    }
                    System.out.println(".................................................................................");
                    System.out.println();

                    this.showGUIAdmin();
                } else {
                    System.out.println("......................................................................");
                    System.out.println("Employee have not been exist! Please update employee and try it again!");
                    System.out.println("......................................................................");
                    System.out.println();
                    this.showGUIAdmin();

                }

            } //end choice = 2
            else if (choice == 3) {
                List<Status> status_list = new ArrayList<Status>();
                status_list = statusBean.getStatuss();
                if (!status_list.isEmpty()) {
                    System.out.println("-----------------------------Status rooms---------------------------------");
                    System.out.println();
                    System.out.printf("%10s %10s %15s %15s %15s", "Status ID", "Room id", "Status", "date checkin", "date checkout");
                    System.out.println();
                    System.out.println("............................................................................");
                    for (Status s : status_list) {
                        int number = s.getStatus();
                        String str = "";
                        if (number == 0) {
                            str = "Free room";
                        } else if (number == 1) {
                            str = "Person in room";
                        } else if (number == 2) {
                            str = "Room booked";
                        }
                        System.out.format("%10s %10s %15s %15s %15s", s.getIdStatus(), s.getIdRoom(), str, s.getDateCheckin(), s.getDateCheckout());
                        System.out.println();
                    }
                    System.out.println("............................................................................");
                    System.out.println();

                    this.showGUIAdmin();
                } else {
                    System.out.println("............................................................");
                    System.out.println("You have not stastus! Please update status and try it again!");
                    System.out.println("............................................................");
                    System.out.println();
                    this.showGUIAdmin();

                }

            } //end choice = 3 
            else if (choice == 4) {
                System.out.println("-----------------------------Create employee---------------------------------");
                System.out.println();
                Employee e = new Employee();
                System.out.print("Enter name:       ");
                e.setName(sc.nextLine());
                System.out.print("Enter Phone:      ");
                try {
                    String phone = sc.nextLine();
                    int temp = Integer.parseInt(phone);
                    e.setPhone(phone);

                } catch (NumberFormatException ex) {
                    System.out.println("..........................................................................");
                    System.out.println("It isn't phone number! Please check Input phone number and try it again!");
                    System.out.println("..........................................................................");
                    this.showGUIAdmin();
                    continue;

                }

                System.out.print("Enter Position:     ");
                e.setPosition(sc.nextLine());
                System.out.print("Enter Salary:       ");
                try {
                    e.setSalary(Integer.parseInt(sc.nextLine()));
                } catch (NumberFormatException ex) {
                    System.out.println("..........................................................................");
                    System.out.println("It isn't Integer number! Please check Input phone number and try it again!");
                    System.out.println("..........................................................................");
                    this.showGUIAdmin();
                    continue;

                }
                System.out.print("Enter Date start (dd/MM/yyyy) :     ");
                String dain = sc.nextLine();
                try {
                    Date date = new SimpleDateFormat("dd/MM/yyyy)").parse(dain);
                    e.setDateStart(dain);
                } catch (ParseException ex) {
                    System.out.println("......................................................");
                    System.out.println("Date not correct! Please check input and try it agian!");
                    System.out.println("......................................................");
                    continue;
                }
                if (employeeBean.createEmployee(e)) {
                    System.out.println("............................................................................");
                    System.out.printf("%50s", "create new employee successfully!\n");
                    System.out.println("............................................................................");
                    System.out.println();
                    this.showGUIAdmin();

                } else {
                    System.out.println("......................................................................");
                    System.out.println("You can't create employee! Please check input employee and try it again!");
                    System.out.println("......................................................................");
                    System.out.println();
                    this.showGUIAdmin();

                }

            } //end choice = 4
            else if (choice == 5) {
                List<Employee> employees = new ArrayList<Employee>();
                employees = employeeBean.getEmployees();
                if (!employees.isEmpty()) {
                    System.out.println("-----------------------------Delete employee---------------------------------");
                    System.out.println();
                    System.out.println("..............................List employee..................................");
                    System.out.printf("%10s %15s %15s %15s %15s", "EMPLOYEE ID", "NAME", "PHONE", "POSITION", "SALARY");
                    System.out.println();
                    System.out.println("............................................................................");
                    for (Employee e : employees) {
                        System.out.format("%10s %15s %15s %15s %15s", e.getIdEmployee(), e.getName(), e.getPhone(), e.getPosition(), e.getSalary());
                        System.out.println();
                    }
                    System.out.println("............................................................................");

                    System.out.print("Enter id employee you want to delete:   ");
                    try {
                        int id = Integer.parseInt(sc.nextLine());
                        boolean check = false;
                        for (Employee e : employees) {
                            if (id == e.getIdEmployee()) {
                                check = true;
                                break;
                            }
                        }
                        if (check) {
                            employeeBean.deleteEmployee(id);
                            System.out.println("............................................................................");
                            System.out.printf("%55s", "delete employee successfully!\n");
                            System.out.println("............................................................................");
                            System.out.println();
                            this.showGUIAdmin();
                        } else {
                            System.out.println("............................................................................");
                            System.out.println("You can't delete employee! Please check input id employee and try it again!");
                            System.out.println("............................................................................");
                            System.out.println();
                            this.showGUIAdmin();
                        } //end delete employee
                    } catch (NumberFormatException ex) {
                        System.out.println("............................................................................");
                        System.out.println("You can't delete employee! Please check input id employee and try it again!");
                        System.out.println("............................................................................");
                        System.out.println();
                        this.showGUIAdmin();

                    }
                } else {
                    System.out.println("......................................................................");
                    System.out.println("Employee have not been exist! Please update employee and try it again!");
                    System.out.println("......................................................................");
                    System.out.println();
                    this.showGUIAdmin();

                }
            } //end choice = 5
            else if (choice == 6) {
                System.out.println("---------------------------Create Account employee------------------------------");
                System.out.println();
                Account acc = new Account();
                System.out.print("Enter username:       ");
                acc.setUsername(sc.nextLine());
                System.out.print("Enter Password:      ");
                acc.setPassword(sc.nextLine());

                if (accBean.userExist(acc.getUsername()) == false) {
                    accBean.createAccount(acc);
                    System.out.println("............................................................................");
                    System.out.printf("%45s", "create new account employee successfully!\n");
                    System.out.println("............................................................................");
                    System.out.println();
                    this.showGUIAdmin();

                } else {
                    System.out.println("............................................................................");
                    System.out.println("You can't create new account employee! Maybe account exist and try it again!");
                    System.out.println("...........................................................................");
                    System.out.println();
                    this.showGUIAdmin();

                }

            } //end choice = 6
            else if (choice == 7) {
                System.out.println("--------------------------Change account employee-----------------------------");
                System.out.print("Enter username:    ");
                String user = sc.nextLine();
                if (accBean.userExist(user)) {
                    System.out.print("Enter new password:  ");
                    String newpass1 = sc.nextLine();
                    System.out.print("Enter new password:  ");
                    String newpass2 = sc.nextLine();
                    if (newpass1.equals(newpass2)) {
                        accBean.changePassword(newpass2, user);
                        System.out.println("..........................................................................");
                        System.out.printf("%50s", "Change password successfully \n ");
                        System.out.println("..........................................................................");
                        this.showGUIAdmin();

                    } else {
                        System.out.println("..........................................................................");
                        System.out.print("----------Change password fail! account not exits! Try it again!-----------\n");
                        System.out.println("..........................................................................");
                        this.showGUIAdmin();
                    }
                } else {
                    System.out.println("..........................................................................");
                    System.out.print("----------Change password fail! username not correct! Try it again!--------\n");
                    System.out.println("..........................................................................");
                    this.showGUIAdmin();
                }
            } //end choice = 7
            else if (choice == 8) {
                System.out.println("--------------------------Delete account employee-----------------------------");
                System.out.println();
                System.out.println("..............................List account..................................");
                System.out.println();
                System.out.printf("%20s %20s %20s", "ACCOUNT ID", "USERNAME", "PASSWORD");
                System.out.println();
                List<Account> accounts = new ArrayList<Account>();
                accounts = accBean.getAccounts();
                for (Account acc : accounts) {
                    System.out.printf("%20s %20s %20s", acc.getIdAccout(), acc.getUsername(), acc.getPassword());
                    System.out.println();

                }
                System.out.println("............................................................................");
                System.out.println();

                System.out.print("Enter id account you want to delete:    ");
                try {
                    int id = Integer.parseInt(sc.nextLine());
                    if (accBean.userExist(id)) {
                        accBean.deleteAccout(id);
                        System.out.println("...........................................................................");
                        System.out.printf("%50s", "Delete account successfully!\n");
                        System.out.println("...........................................................................");
                        this.showGUIAdmin();
                    } else {
                        System.out.println("...........................................................................");
                        System.out.print("----------Delete account fail! id account not true! Try it again!-----------\n");
                        System.out.println("...........................................................................");
                        this.showGUIAdmin();
                        System.out.println();
                    }
                } catch (NumberFormatException ex) {
                    System.out.println("...........................................................................");
                    System.out.print("----------Delete account fail! id account not true! Try it again!-----------\n");
                    System.out.println("...........................................................................");
                    this.showGUIAdmin();

                }
            } //end choice = 8
            else if (choice == 9) {
                System.out.println("--------------------------List account-----------------------------");
                System.out.printf("%20s %20s %20s", "ACCOUNT ID", "USERNAME", "PASSWORD\n");
                System.out.println(".....................................................................");

                List<Account> accounts = new ArrayList<Account>();
                accounts = accBean.getAccounts();
                for (Account acc : accounts) {
                    System.out.printf("%20s %20s %20s", acc.getIdAccout(), acc.getUsername(), acc.getPassword());
                    System.out.println();
                }
                System.out.println(".....................................................................");
                System.out.println();
                this.showGUIAdmin();

            } else if (choice == 10) {
                System.out.println("--------------------------Report-----------------------------");
                System.out.println(".............................................................");
                System.out.print("Enter your date you want to start compute (dd/MM/yyyy):        ");
                String datein = sc.nextLine();
                Date date1, date2;
                try {
                    date1 = new SimpleDateFormat("dd/MM/yyyy").parse(datein);
                    System.out.print("Enter your date you want to end compute (dd/MM/yyyy):      ");
                    String dateout = sc.nextLine();
                    date2 = new SimpleDateFormat("dd/MM/yyyy").parse(dateout);

                    //tinh tien o list bill
                    double sum_bill = 0;
                    List<Bill> bills = new ArrayList<Bill>();
                    bills = billBean.getBills();
                    for (Bill b : bills) {
                        sum_bill = sum_bill + b.getAmounts();
                    }
                    //tinh tien nhan vien
                    double sum_em = 0;
                    List<Employee> em_lst = new ArrayList<Employee>();
                    em_lst = employeeBean.getEmployees();
                    for (Employee e : em_lst) {
                        if (compareTwoDay(e.getDateStart(), datein) <= 0) {
                            int months = diffMonths(datein, dateout);
                            sum_em = sum_em + e.getSalary() * months;
                        }
                    }

                    //so khach o phong
                    int[] count_room_array = new int[10];
                    //tong so khach hang
                    int count_customer = 0;
                    //tinh so khach hang moi
                    int count_new_customer = 0;
                    //tong so ngay khach hang da o
                    int sumdays = 0;
                    List<Checkout> cb_lst = new ArrayList<Checkout>();
                    cb_lst = ckoutBean.getCheckOutlist();
                    for (Checkout cb : cb_lst) {
                        count_room_array[cb.getIdRoom()]++;
                        for (Checkout c : cb_lst) {
                            if (cb.getIdentityCard().equals(c.getIdentityCard())) {
                                count_new_customer++;
                            }
                        }
                        count_customer++;
                        sumdays = sumdays + (int) diffDays(cb.getDateCheckin(), cb.getDateCheckout());
                    }
                    count_new_customer = count_new_customer - 1;
                    //tinh so khach hang quay lai
                    int count_old_customer = count_customer - count_new_customer;
                    System.out.println("------------------------------Your Report---------------------------------");
                    System.out.println("...........................................................................");
                    System.out.println("AMOUNT OF BILLS (INCOME) (VND):     " + String.format("%,.0f", sum_bill));
                    System.out.println("AMOUNT OF SALARY (OUTCOME) (VND):   " + String.format("%,.0f", sum_em));
                    System.out.println("REVENUE AMOUNT (VND):               " + String.format("%,.0f", sum_bill - sum_em));
                    System.out.println("CUSTOMERS NUMBER:                   " + String.format("%,.0f", (double) count_customer));
                    System.out.println("CUSTOMERS NEW NUMBER:               " + String.format("%,.0f", (double) count_new_customer));
                    System.out.println("CUSTOMERS OLD NUMBER:               " + String.format("%,.0f", (double) count_old_customer));
                    System.out.println("NUMBER CUSTOMERS CHECKIN ROOM ID 1: " + String.format("%,.0f", (double) count_room_array[1]));
                    System.out.println("NUMBER CUSTOMERS CHECKIN ROOM ID 2: " + String.format("%,.0f", (double) count_room_array[2]));
                    System.out.println("NUMBER CUSTOMERS CHECKIN ROOM ID 3: " + String.format("%,.0f", (double) count_room_array[3]));
                    System.out.println("NUMBER CUSTOMERS CHECKIN ROOM ID 4: " + String.format("%,.0f", (double) count_room_array[4]));
                    System.out.println("NUMBER CUSTOMERS CHECKIN ROOM ID 5: " + String.format("%,.0f", (double) count_room_array[5]));
                    System.out.println("NUMBER CUSTOMERS CHECKIN ROOM ID 6: " + String.format("%,.0f", (double) count_room_array[6]));
                    System.out.println("NUMBER CUSTOMERS CHECKIN ROOM ID 7: " + String.format("%,.0f", (double) count_room_array[7]));
                    System.out.println("NUMBER CUSTOMERS CHECKIN ROOM ID 8: " + String.format("%,.0f", (double) count_room_array[8]));
                    System.out.println("NUMBER CUSTOMERS CHECKIN ROOM ID 9: " + String.format("%,.0f", (double) count_room_array[9]));
                    System.out.println("...........................................................................");

                } catch (ParseException ex) {
                    System.out.println("...........................................................................");
                    System.out.print("-------------Date not correct! Please check your input and try it again-----\n");
                    System.out.println("...........................................................................");
                    this.showGUIAdmin();
                    continue;
                }
                this.showGUIAdmin();
            } else {
                break;
            }
        } //end while

    }
    //  ----------------------------------------End Adminstrator----------------------------------------------

    /**
     * Test the Stateless EJB
     */
    public void testStatelessEJB() throws ParseException {
        try {
            // Scanner definition
            Scanner sc = new Scanner(System.in);

            int choice = 0;
            while (choice != 4) {
                this.showGUI();

                // Use this approach because nextInt will cause error to nextLine()
                choice = Integer.parseInt(sc.nextLine());

                if (choice == 1) {
                    // Lookup the UseradminSessionBeanRemote
                    UseradminSessionBeanRemote useradminBean = (UseradminSessionBeanRemote) ctx.lookup(getJNDI_useradmin());
                    useradminBean.getUseradmins();
                    System.out.println("======================Please login Admintrator============================ ");
                    System.out.print("Enter username:  ");
                    String useradmin = sc.nextLine();
                    System.out.print("Enter password:  ");
                    String passadmin = sc.nextLine();

                    //kiem user co ton tai trong useradmin hay khong
                    if (useradminBean.checkUser(useradmin, passadmin)) {

                        //MAIN ADMINSTRATOR SYSTEM
                        this.mainAmin();
                        //------------------------

                    } else {
                        System.out.println("------------connection failed!-----------");

                    } // end check useradmin

                } //end choice = 1
                else if (choice == 2) {

                    // Lookup the AccountSessionBeanRemote
                    AccountSessionBeanRemote accountBean = (AccountSessionBeanRemote) ctx.lookup(getJNDI_Account());
                    accountBean.getAccounts();

                    System.out.println("======================Please login Receptionist============================ ");
                    System.out.print("Enter username:  ");
                    String useradmin = sc.nextLine();
                    System.out.print("Enter password:  ");
                    String passadmin = sc.nextLine();

                    //kiem user co ton tai trong useradmin hay khong
                    if (accountBean.checkUser(useradmin, passadmin)) {
                        this.mainRec();
                    } else {
                        System.out.println("------------connection failed!-----------");
                    } // end check account
                }// end choice == 2
                else if (choice == 3) {

                    //create object
                    cm = new Customer();

                    System.out.println("======================Please Enter Your Information========================== ");
                    System.out.print("Enter name:  ");
                    cm.setName(sc.nextLine());
                    System.out.print("Enter identity card:  ");
                    cm.setIdentityCard(sc.nextLine());
                    System.out.print("Enter phone:  ");
                    try {
                        String phone = sc.nextLine();
                        int n = Integer.parseInt(phone);
                        cm.setPhone(phone);
                    } catch (NumberFormatException ex) {
                        System.out.println("...........................................................................");
                        System.out.print("----------Input formation failed! Your phone number must number!-----------\n");
                        System.out.println("...........................................................................");
                        continue;
                    }
                    System.out.print("Address:  ");
                    cm.setAddress(sc.nextLine());
                    this.mainCustomer();
                } else {
                    // Exit
                    break;
                }
            }

            sc.close();

        } catch (NamingException ex) {
            System.err.println(ex.toString());
        }
    }

}
