import java.util.*;
public class QuickPayProject {
    static Scanner input = new Scanner(System.in);
    static int UserCount = 0;
    static String[] Usernames = new String[100];
    static String[] Emails = new String[100];
    static String[] UserPassword = new String[100];
    static int[] PhoneNumbers = new int[100];
    static int[] UserIDs = new int[100];
    static int Id = 100;

    public static void AddUser (String name , String Passwords , String Email, int PhoneNumber ){
        UserIDs[UserCount] = Id;
        Usernames[UserCount] = name;
        Emails[UserCount] = Email;
        UserPassword[UserCount] = Passwords;
        PhoneNumbers[UserCount] = PhoneNumber;
        UserCount++;
        Id++;
    }
    public static void userPanel () {
        boolean isRunning = true;
        while (isRunning) {
            System.out.println("\n========== USER PANEL ==========");
            System.out.println("1. Register New Account");
            System.out.println("2. Login to Your Account");
            System.out.println("3. Back to Main Menu");
            System.out.print("Please Choose An Operation : ");
            int UserChoice = input.nextInt();
            switch (UserChoice) {
                case 1 :
                    System.out.println("\n=========== Welcome To Account Register ===========");
                    System.out.println("Please Enter Your User Name :");
                    String name = input.next();
                    System.out.println("Please Enter Your Password :");
                    String pass = input.next();
                    System.out.println("Please Enter Your Email Address :");
                    String email = input.next();
                    System.out.println("Enter Your Phone Number : ");
                    int Phone = input.nextInt();
                    AddUser( name ,  pass , email , Phone);
                    System.out.println("Your Account Has Been Registered Successfully ✅ ");
                        break;
                case 2 :
                   LogInmenu();
                    String LoginUserName = input.next();
                    System.out.println("Enter Your Password :");
                    String LoginPassword = input.next();
                    boolean UserFound = false;
                    for (int i = 0 ; i < UserCount ; i++) {
                        if (Usernames[i].equalsIgnoreCase(LoginUserName) && UserPassword[i].equalsIgnoreCase(LoginPassword)) {
                            System.out.println("Login Successfully ✅ Welcome" + Usernames[i] + "\"Your Id is \"" + UserIDs[i]);
                            UserOptions();
                        }
                        if (!UserFound) {
                            System.out.println("Incorrect Username Or Password [❌]");
                            break;
                        }
                    }
                case 3 :
                    isRunning = false;
                        MainMenu();
                        break;
                }
           }
        }
        public static void LogInmenu() {
            System.out.println("=============== WELCOME TO LOG IN PAGE ===============");
            System.out.println();
            System.out.println("=============================================");
            System.out.println("\t\tPlease Enter your Credentials");
            System.out.println("\n=============================================");
            System.out.println("Enter Your Username :");
        }
        public static void AdminMenu () {
            System.out.println("\n========== WELCOME TO ADMIN PANEL ==========");
            System.out.println();
            System.out.println("1. Show Statistics ");
            System.out.println("2. Search User");
            System.out.println("3. View All Users");
            System.out.println("4. Update User");
            System.out.println("5. View All Transactions");
            System.out.println("6. Block Account");
            System.out.println("7. Unblock Account");
            System.out.println("8. View User Details");
            System.out.println("9. Logout");
            System.out.println();
            System.out.println("Please Choose An Operation : ");
        }
    public static void admin() {
            LogInmenu();
            System.out.println("Name : ");
            String adminName = input.next();
        while (!adminName.equalsIgnoreCase("admin")) {
            System.out.println("Incorrect Name [❌] , Please Re-Enter Your Name ");
            System.out.print("Name : ");
            adminName = input.next();
        }
            System.out.println("Enter Password : ");
            int adminPassword = input.nextInt();
            while (adminPassword != 123) {
                System.out.println("Password Incorrect [❌] , Please Re-Enter your Password Again :");
                adminPassword = input.nextInt();
            }
        boolean isTrue = true;
        while (isTrue) {
            AdminMenu();
             int adminChoice = input.nextInt();
            switch (adminChoice) {
            case 1:
                //كل Case هتحطي تحتها الميثود بتاعتها المناظرة ليها
                //نحط ميثود الاحصائيات هنا مكان الكومينت
                break;
            case 2:
                //هتحطي هنا برضو ميثود البحث عن يوزر و هكذا بقى مع باقي الميثودز اللي فاضلة في جزء الادمن
                break;
            case 3:
                //
                break;
            case 4:
                //
                break;
            case 5:
                //
                break;
            case 6:
                //
                break;
            case 7:
                //
                break;
            case 8:
                //
                break;
            case 9:
                System.out.println("Logging Out .....THANK YOU FOR USING QUICK PAY APP ! , GOODBYE \uD83D\uDC4B .");
                isTrue = false;
                System.out.println();
                break;
            default:
                System.out.println("Invalid Choice [❌] , Please Enter a Number from 1 to 9 ");
        }
    }
}
public static void UserOptions() {
    System.out.println("\n=============== User Panel ===============");
    System.out.println("1.Show Balance ");
    System.out.println("2. Transfer Money ");
    System.out.println("3. Transaction History ");
    System.out.println("4. Show Favorites ");
    System.out.println("5. Add Favorite ");
    System.out.println("6. Remove Favorite ");
    System.out.println("7. Search Recipient ");
    System.out.println("8. View Profile ");
    System.out.println("9. Update Profile ");
    System.out.println("\n10. Logout ");
    System.out.println("Please Choose An Option To Proceed ");
    int UserOption = input.nextInt();
    switch (UserOption) {
        case 1 :
            //
            break;
        case 2 :
            //
            break;
        case 3 :
            //
            break;
        case 4 :
            //
            break;
        case 5 :
            //
            break;
        case 6 :
            //
            break;
        case 7 :
            //
            break;
        case 8 :
            //
            break;
        case 9 :
            //
            break;
        case 10 :
            System.out.println("Logging Out ......... Thank You For Using Quick Pay App , GoodBye \uD83D\uDC4B . ");
            break;
    }
}
        public static void MainMenu (){
        System.out.println("============================================");
         System.out.println("\t\t\tWELCOME TO QUICK PAY ");
         System.out.println("============================================");
         System.out.println();
         System.out.println("1 : Login As Admin");
         System.out.println("2 : Login As User");
         System.out.println("3 : Logout");
         System.out.println("Please Choose An Operation :");
         }
         public static void main(String[] args) {
             int Choice = 0;
             AddUser("Mohamed" , "1234" , "Mohamed@gmail.com" , 01223344556);
             AddUser("Ahmed" , "12345" , "Ahmed@gmail.com"  , 01122334456);
             AddUser("Nour" , "123456" , "Nour@gmail.com" , 01112233445);
             AddUser("Arwa" , "1234567" , "Arwa@gmail.com" , 01111223344);
             AddUser("Yasmeen" , "12345678" , "Yasmeen@gmail.com" , 01224455667);
             while (Choice != 3) {
                            MainMenu();
                 // هنا ده علشان اليوزر يدخل بنفسه رقم علشان يستعمل البرنامج
                 Choice = input.nextInt();
                 switch (Choice) {
                     case 1:
                         admin();
                         // هنا دي بقى ال CASE بتاعت الادمن نفسه و القوائم بتاعته يا بشمهندسة
                         break;
                     case 2:
                         userPanel();
                         // هنا ده الاختيار بتاع الدخول لقائمة اليوزر هنحط الميثود بتاعت اليوزر هنا
                         break;
                     case 3:
                         System.out.println("THANK YOU FOR USING QUICK PAY APP ! , GOODBYE \uD83D\uDC4B .");
                         break;
                     default:
                         System.out.println("Invalid Choice [❌]");
                 }
                 }
         }
}
