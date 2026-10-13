import java.util.*;
public class  QuickPayProject {
    static Scanner input = new Scanner(System.in);
    static int UserCount = 0;
     static double []Sbalance = new double [999];
    static boolean []ActiveAccount=new boolean [999];
    static int Tcount=0;
    //Tcount = transaction count
    static int [] tIDS = new int[999];
    //tIDS=Transactions IDS
    static double [] Tamounts =new double[999];
    //Tamounts = Transaction amounts
    static boolean [] Tresult = new boolean[999];
    // Tresult = Transaction result
    static int [] Tsenders= new int[999];
    //Tsenders = Transaction senders
    static int [] Treceivers= new int[999];
    //Treceivers = Transaction receivers
    static String[] Usernames = new String[100];
    static String[] Emails = new String[100];
    static String[] UserPassword = new String[100];
    static int[] PhoneNumbers = new int[100];
    static int[] UserIDs = new int[100];
    static int Id = 100;
static boolean CanTrans(double Tbalance,double Sbalance,boolean ActiveAccount){
    //Tbalance = transfer balance
    //Sbalance = sender balance
    //ActiveAccount = sender account status
    //دي الاختصرات الي هستخدمها في الكود عشان مطولش الكلام
    if(!ActiveAccount){
        System.out.println("your Account is blocked");
        return false;
    }
    //دا الجزء الي بيشوف حاله الحساب لو مفعل او لا
    if (Tbalance<=0||Tbalance>Sbalance){
        System.out.println("invalid transfer amount");
        return false;
    }
    // دا الجزء الي بيشوف لو المبلغ الي هيتبعت اكبر من 0 ولا لا وبرضوا بيشوف لو المبلغ الي هيتبعت اكبر من المبلغ الي في الحساب ولا لا
    return true;
    }  
    public static void AddUser (String name , String Passwords , String Email, int PhoneNumber ){
        UserIDs[UserCount] = Id;
        Usernames[UserCount] = name;
        Emails[UserCount] = Email;
        UserPassword[UserCount] = Passwords;
        PhoneNumbers[UserCount] = PhoneNumber;
        Sbalance[UserCount] = 0.0;
        ActiveAccount[UserCount] = true;
        UserCount++;
        Id++;
    }
    static void Trans (int Sindex,int Rindex,double amount){
    //Sindex = sender index
    //Rindex = receiver index
    //Trans = 
if (Sindex<0|| Sindex >= UserCount ||Rindex<0||Rindex>= UserCount ){
    System.out.println("invalid account index");
    Rtransaction(Sindex, Rindex, amount, false);
return;
}

if (!ActiveAccount[Rindex]){
    System.out.println("receiver account is blocked");
    Rtransaction(Sindex, Rindex, amount, false);
    return;
}
if(Sindex==Rindex){
    System.out.println("can,t transfer to the same account");
    Rtransaction(Sindex, Rindex, amount, false);
    return;
}
if(!CanTrans(amount,Sbalance[Sindex],ActiveAccount[Sindex])){
    Rtransaction(Sindex, Rindex, amount, false);
    return;
}
//علشان نتاكد ان الحساب ميبعتش لنفسه ونتاكد ان حساب المرسل نشط ومعاه فلوس كفايه
Sbalance[Sindex]-=amount;
Sbalance[Rindex]+=amount;
//علشلن نخصم من الي بيبعت ونزود عند الي بيستلم الفلوس
Rtransaction(Sindex, Rindex, amount, true);
System.out.println("transfer successful");
System.out.println("sender balance: "+Sbalance[Sindex]);
System.out.println("receiver balance: "+Sbalance[Rindex]);

}
static int Freciver (int PhoneNumber){
    //Freciver = find reciver
    for (int i = 0 ; i < UserCount ; i++) {
        if (PhoneNumbers[i] == PhoneNumber) {
            return i;
        }
    }
    return -1;
}
//الجزء ده بيشوف لو الرقم الي دخلته موجود في الحسابات ولا لا وبيرجعلك الاي اندكس بتاعه لو موجود و بيرجعلك -1 لو مش موجود
static void TransByPhone(int Sindex){
    System.out.println("Enter the Phone Number of the Receiver : ");
    int PhoneNumber = input.nextInt();
    int Rindex = Freciver(PhoneNumber);
    if (Rindex == -1) {
        System.out.println("Receiver Not Found [❌]");
        return;
    }
    System.out.println("Enter the Amount to Transfer : ");
    double amount = input.nextDouble();
    Trans(Sindex, Rindex, amount);
}
static void Rtransaction(int Sindex,int Rindex,double amount,boolean result){
        //Rtranactions=
        if (Tcount>=tIDS.length){
            System.out.println("Transaction history is full");
            return;
        }
    //دا الجزء الي بيضيف المعاملات الي حصلت في الحساب وبيشوف لو اتملي ولا لسه 
    tIDS[Tcount]=Tcount+1;
    Tamounts[Tcount]=amount;
    Tresult[Tcount]=result;
    Tsenders[Tcount]=Sindex;
    Treceivers[Tcount]=Rindex;
    Tcount++;
}
static void STransHistory(int Sindex){
    //STransHistory = Show Transaction History
    boolean Found = false;
    for (int i = 0 ; i < Tcount ; i++) {
        if (Tsenders[i] == Sindex || Treceivers[i] == Sindex) {
            Found = true;
            System.out.println("Transaction ID: " + tIDS[i]);
            if (Tsenders[i] == Sindex) {
                System.out.println ("Transaction Type: Sent");
                System.out.println("Receiver: " + Usernames[Treceivers[i]]);
            } else {
                System.out.println("Transaction Type: Received");
                System.out.println("Sender: " + Usernames[Tsenders[i]]);
            }
            System.out.println("Amount: " + Tamounts[i]);
            if (Tresult[i]) {
                System.out.println("Result: Successful");
            } else {
                System.out.println("Result: Failed");
            }
            System.out.println("--------------------------------------------------------------");
        }
        //الحزء ده بيشوف لو الحساب ده بعت او استلم فلوس وبيطبع كل التفاصيل بتاعت المعاملة
    }
    if (!Found) {
        System.out.println("No Transactions Found [❌]");
    }
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
                            UserOptions(i);
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
public static void UserOptions(int Sindex) {
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
            TransByPhone(Sindex);
            break;
        case 3 :
            STransHistory(Sindex);
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

