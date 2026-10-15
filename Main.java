import java.util.*;

public class Main1 {

    // ==========================================
    // 1. GLOBAL VARIABLES & ARRAYS
    // ==========================================
    static Scanner input = new Scanner(System.in);
    static int UserCount = 0;
    static double[] Sbalance = new double[999];
    static boolean[] ActiveAccount = new boolean[999];
    static int Tcount = 0;
    static int[] tIDS = new int[999];
    static double[] Tamounts = new double[999];
    static boolean[] Tresult = new boolean[999];
    static int[] Tsenders = new int[999];
    static int[] Treceivers = new int[999];
    static String[] Usernames = new String[100];
    static String[] Emails = new String[100];
    static String[] UserPassword = new String[100];
    static String[] PhoneNumbers = new String[100];
    static int[] UserIDs = new int[100];
    static int Id = 100;
    static int[] Favoritecount = new int[999];
    static int[][] userfavorites = new int[999][999];


    // ==========================================
    // 2. CORE & HELPER FUNCTIONS
    // ==========================================
    public static void AddUser(String name, String Passwords, String Email, String PhoneNumber) {
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

    static boolean CanTrans(double Tbalance, double Sbalance, boolean ActiveAccount) {
        if (!ActiveAccount) {
            System.out.println("Your Account is blocked");
            return false;
        }
        if (Tbalance <= 0 || Tbalance > Sbalance) {
            System.out.println("invalid transfer amount");
            return false;
        }
        return true;
    }

    static int Freciver(String PhoneNumber) {
        for (int i = 0; i < UserCount; i++) {
            if (PhoneNumbers[i].equals(PhoneNumber)) {
                return i;
            }
        }
        return -1;
    }

    static void Rtransaction(int Sindex, int Rindex, double amount, boolean result) {
        if (Tcount >= tIDS.length) {
            System.out.println("Transaction history is full");
            return;
        }
        tIDS[Tcount] = Tcount + 1;
        Tamounts[Tcount] = amount;
        Tresult[Tcount] = result;
        Tsenders[Tcount] = Sindex;
        Treceivers[Tcount] = Rindex;
        Tcount++;
    }


    // ==========================================
    // 3. FINANCIAL & TRANSACTION METHODS
    // ==========================================
    static void Trans(int Sindex, int Rindex, double amount) {
        if (Sindex < 0 || Sindex >= UserCount || Rindex < 0 || Rindex >= UserCount) {
            System.out.println("invalid account index");
            Rtransaction(Sindex, Rindex, amount, false);
            return;
        }
        if (!ActiveAccount[Rindex]) {
            System.out.println("receiver account is blocked");
            Rtransaction(Sindex, Rindex, amount, false);
            return;
        }
        if (Sindex == Rindex) {
            System.out.println("can,t transfer to the same account");
            Rtransaction(Sindex, Rindex, amount, false);
            return;
        }
        if (!CanTrans(amount, Sbalance[Sindex], ActiveAccount[Sindex])) {
            Rtransaction(Sindex, Rindex, amount, false);
            return;
        }

        Sbalance[Sindex] -= amount;
        Sbalance[Rindex] += amount;
        Rtransaction(Sindex, Rindex, amount, true);

        System.out.println("transfer successful");
        System.out.println("sender balance: " + Sbalance[Sindex]);
        System.out.println("receiver balance: " + Sbalance[Rindex]);
    }

    static void TransByPhone(int Sindex) {
        System.out.println("Enter the Phone Number of the Receiver : ");
        String PhoneNumber = input.next();
        int Rindex = Freciver(PhoneNumber);
        if (Rindex == -1) {
            System.out.println("Receiver Not Found [❌]");
            return;
        }
        System.out.println("Enter the Amount to Transfer : ");
        double amount = input.nextDouble();
        Trans(Sindex, Rindex, amount);
    }

    public static void deposit(int Sindex) {
        System.out.println("Enter amount to deposit :");
        double amount = input.nextDouble();
        if (amount <= 0) {
            System.out.println("invalid deposit amount!❌");
            return;
        }
        Sbalance[Sindex] += amount;
        System.out.println("Deposit successful!✅");
        System.out.println("your new balance is " + Sbalance[Sindex] + "EGP");
    }

    public static void withdraw(int Sindex) {
        System.out.println("Enter amount to withdraw :");
        double amount = input.nextDouble();
        if (amount <= 0) {
            System.out.println("invalid withdraw amount !❌");
            return;
        }
        if (amount > Sbalance[Sindex]) {
            System.out.println("insufficient balance!❌");
            return;
        }
        Sbalance[Sindex] -= amount;
        System.out.println("Withdraw successful!✅");
        System.out.println("your new balance is :" + Sbalance[Sindex] + "EGP");
    }

    public static void showMyBalance(int Sindex) {
        System.out.println("\nYour Current Balance is: " + Sbalance[Sindex] + " EGP");
    }


    // ==========================================
    // 4. USER FEATURES (Favorites, Profile, History)
    // ==========================================
    public static void addFavorite(int Sindex) {
        System.out.print("Enter the username or email to add to favorites: ");
        String textInput = input.nextLine();
        int friendIndex = -1;
        for (int i = 0; i < UserCount; i++) {
            if (Usernames[i].equalsIgnoreCase(textInput) || Emails[i].equalsIgnoreCase(textInput)) {
                friendIndex = i;
                break;
            }
        }
        if (friendIndex == -1) {
            System.out.println("This user does not exist in the system [❌].");
            return;
        }
        if (Sindex == friendIndex) {
            System.out.println("You cannot add yourself to your favorites list [❌].");
            return;
        }
        for (int i = 0; i < Favoritecount[Sindex]; i++) {
            if (userfavorites[Sindex][i] == friendIndex) {
                System.out.println("This user is already in your favorites list!");
                return;
            }
        }
        int currentCount = Favoritecount[Sindex];
        userfavorites[Sindex][currentCount] = friendIndex;
        Favoritecount[Sindex]++;
        System.out.println("Added to favorites successfully! ✅");
    }

    public static void removeFavorite(int Sindex) {
        System.out.print("Enter the username or email to remove from favorites: ");
        String textInput = input.next();
        int friendIndex = -1;
        for (int i = 0; i < UserCount; i++) {
            if (Usernames[i].equalsIgnoreCase(textInput) || Emails[i].equalsIgnoreCase(textInput)) {
                friendIndex = i;
                break;
            }
        }
        if (friendIndex == -1) {
            System.out.println("This user does not exist in the system [❌].");
            return;
        }
        int removeIndex = -1;
        for (int i = 0; i < Favoritecount[Sindex]; i++) {
            if (userfavorites[Sindex][i] == friendIndex) {
                removeIndex = i;
                break;
            }
        }
        if (removeIndex == -1) {
            System.out.println("This user is not in your favorites list [❌].");
            return;
        }
        for (int i = removeIndex; i < Favoritecount[Sindex] - 1; i++) {
            userfavorites[Sindex][i] = userfavorites[Sindex][i + 1];
        }
        Favoritecount[Sindex]--;
        System.out.println("Removed from favorites successfully! ✅");
    }

    public static void showFavorites(int Sindex) {
        System.out.println("\n--- Your Favorites List ---");
        int count = Favoritecount[Sindex];
        if (count == 0) {
            System.out.println("Your favorites list is empty.");
        } else {
            for (int i = 0; i < count; i++) {
                int friendIdx = userfavorites[Sindex][i];
                System.out.println("- " + Usernames[friendIdx] + " (Email: " + Emails[friendIdx] + ")");
            }
        }
    }

    public static void searchRecipient() {
        System.out.print("Enter username or email to search: ");
        String searchKey = input.next();
        boolean found = false;
        for (int i = 0; i < UserCount; i++) {
            if (Usernames[i].equalsIgnoreCase(searchKey) || Emails[i].equalsIgnoreCase(searchKey) || PhoneNumbers[i].equalsIgnoreCase(searchKey)) {
                System.out.println("\n--- User Found ---");
                System.out.println("Name: " + Usernames[i]);
                System.out.println("Email: " + Emails[i]);
                System.out.println("Phone: " + PhoneNumbers[i]);
                System.out.println("ID: " + UserIDs[i]);
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("No user found with this information [❌]");
        }
    }

    public static void viewProfile(int Sindex) {
        System.out.println("\n--- Your Profile Details ---");
        System.out.println("ID: " + UserIDs[Sindex]);
        System.out.println("Name: " + Usernames[Sindex]);
        System.out.println("Email: " + Emails[Sindex]);
        System.out.println("Phone: " + PhoneNumbers[Sindex]);
        System.out.println("Balance: " + Sbalance[Sindex] + " EGP");
    }

    public static void updateProfile() {
        boolean updating = true;
        while (updating) {
            System.out.print("What do you want to update?\n1. Username\n2. Email\n3. Phone Number\nEnter your choice: \n");
            int choice = input.nextInt();
            if (choice < 1 || choice > 3) {
                System.out.println("Invalid choice! Please try again. ❌");
                continue;
            }
            System.out.print("Enter Your ID to check: ");
            int searchKey = input.nextInt();
            int targetIndex = -1;
            for (int i = 0; i < Usernames.length; i++) {
                if (UserIDs[i] == searchKey) {
                    targetIndex = i;
                    break;
                }
            }
            if (targetIndex == -1) {
                System.out.println("No user found with this information! Please try again. [❌]");
                continue;
            }
            switch (choice) {
                case 1:
                    System.out.print("Enter new username: ");
                    String newUsername = input.next();
                    if (newUsername.equals(Usernames[targetIndex])) {
                        System.out.println("This is already your current username! ❌");
                    } else {
                        Usernames[targetIndex] = newUsername;
                        System.out.println("Username updated successfully! ✅\n");
                        updating = false;
                    }
                    break;
                case 2:
                    System.out.print("Enter new email: ");
                    String newEmail = input.next();
                    if (newEmail.equals(Emails[targetIndex])) {
                        System.out.println("This is already your current email! ❌");
                    } else {
                        Emails[targetIndex] = newEmail;
                        System.out.println("Email updated successfully! ✅");
                        updating = false;
                    }
                    break;
                case 3:
                    System.out.print("Enter new phone number: ");
                    String newPhone = input.next();
                    if (newPhone.equals(PhoneNumbers[targetIndex])) {
                        System.out.println("This is already your current phone number! ❌");
                    } else {
                        PhoneNumbers[targetIndex] = newPhone;
                        System.out.println("Phone updated successfully! ✅");
                        updating = false;
                    }
                    break;
            }
        }
    }

    static void STransHistory(int Sindex) {
        boolean Found = false;
        for (int i = 0; i < Tcount; i++) {
            if (Tsenders[i] == Sindex || Treceivers[i] == Sindex) {
                Found = true;
                System.out.println("Transaction ID: " + tIDS[i]);
                if (Tsenders[i] == Sindex) {
                    System.out.println("Transaction Type: Sent");
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
        }
        if (!Found) {
            System.out.println("No Transactions Found [❌]");
        }
    }


    // ==========================================
    // 5. ADMIN PANEL METHODS
    // ==========================================
    public static void ShowStatistics() {
        System.out.println("Total Number Of Registered Users: " + UserCount);
        int blockedCount = 0;
        int activeCount = 0;
        int successTx = 0;
        int faildTx = 0;
        double totalSuccessAmount = 0;
        for (int i = 0; i < UserCount; i++) {
            if (ActiveAccount[i]) {
                activeCount++;
            } else {
                blockedCount++;
            }
        }
        for (int i = 0; i < Tcount; i++) {
            if (Tresult[i]) {
                successTx++;
                totalSuccessAmount += Tamounts[i];
            } else {
                faildTx++;
            }
        }
        System.out.println("Number Of Active Accounts: " + activeCount);
        System.out.println("Number Of Blocked Accounts: " + blockedCount);
        System.out.println("Total Number Of Successful Transactions: " + successTx);
        System.out.println("Total Number Of Failed Transactions: " + faildTx);
        System.out.println("Total Successful Transfer Amount: " + totalSuccessAmount);
    }

    public static void printUserdetails(int i) {
        System.out.println("User ID: " + UserIDs[i]);
        System.out.println("Name: " + Usernames[i]);
        System.out.println("Phone Number: " + PhoneNumbers[i]);
        System.out.println("Email Address: " + Emails[i]);
        System.out.println("Sender Balance: " + Sbalance[i]);
        if (ActiveAccount[i]) {
            System.out.println("Status: Active");
        } else {
            System.out.println("Status: Blocked");
        }
    }

    public static void searshUserAdmin() {
        System.out.println("Search by User ID (1) or Name/Email/Phone (2)");
        int choice = input.nextInt();
        if (choice == 1) {
            input.nextLine();
            System.out.println("Enter the ID: ");
            int id = input.nextInt();
            boolean found = false;
            for (int i = 0; i < UserCount; i++) {
                if (UserIDs[i] == id) {
                    printUserdetails(i);
                    found = true;
                    break;
                }
            }
            if (!found) {
                System.out.println("ID Not Found");
            }
        } else {
            input.nextLine();
            System.out.println("Enter UserName/Email Address/Phone Number : ");
            String k = input.nextLine();
            boolean found1 = false;
            for (int i = 0; i < UserCount; i++) {
                if (Usernames[i].equals(k) || Emails[i].equals(k) || PhoneNumbers[i].equals(k)) {
                    printUserdetails(i);
                    found1 = true;
                    break;
                }
            }
            if (!found1) {
                System.out.println("User Not Found");
            }
        }
    }

    public static void viewAllUsers() {
        System.out.println("All users : ");
        for (int i = 0; i < UserCount; i++) {
            printUserdetails(i);
            System.out.println("==================================");
            System.out.println();
        }
    }

    public static void viewUserDetails() {
        System.out.println("Enter Your ID: ");
        int Id = input.nextInt();
        boolean found2 = false;
        for (int i = 0; i < UserCount; i++) {
            if (Id == UserIDs[i]) {
                printUserdetails(i);
                found2 = true;
                break;
            }
        }
        if (!found2) {
            System.out.println("Invalid ID");
        }
    }

    public static void viewAllTransactionAdmin() {
        if (Tcount == 0) {
            System.out.println("No Transactions");
            return;
        }
        for (int i = 0; i < Tcount; i++) {
            System.out.println("TX ID: " + tIDS[i]);
            System.out.println(" Sender: " + Usernames[Tsenders[i]]);
            System.out.println("Receiver: " + Usernames[Treceivers[i]]);
            System.out.println("Amount: " + Tamounts[i]);
            if (Tresult[i]) {
                System.out.println("status: Success ");
            } else {
                System.out.println("status: Failed ");
            }
        }
    }

    public static void BlockAccount() {
        System.out.println("Enter User ID: ");
        int iD = input.nextInt();
        boolean found3 = false;
        for (int i = 0; i < UserCount; i++) {
            if (iD == UserIDs[i]) {
                found3 = true;
                if (!ActiveAccount[i]) {
                    System.out.println("User Is Already Blocked");
                } else {
                    ActiveAccount[i] = false;
                    System.out.println("You Blocked This Account");
                }
            }
        }
        if (!found3) {
            System.out.println("User Not Found");
        }
    }

    public static void UnblockAccount() {
        System.out.println("Enter User ID: ");
        int iD = input.nextInt();
        boolean found4 = false;
        for (int i = 0; i < UserCount; i++) {
            if (iD == UserIDs[i]) {
                found4 = true;
                if (ActiveAccount[i]) {
                    System.out.println("User Is Already Active ");
                } else {
                    ActiveAccount[i] = true;
                    System.out.println("You Unblocked This Account");
                }
            }
        }
        if (!found4) {
            System.out.println("User Not Found");
        }
    }

    public static void UpdateUser() {
        System.out.println("Choose An Option To Update: ");
        System.out.println("1.Username");
        System.out.println("2.ID");
        System.out.println("3.Phone Number");
        System.out.println("4.Email Address");
        int choice1 = input.nextInt();
        boolean found5 = false;
        switch (choice1) {
            case 1:
                System.out.println("Enter Username To Update: ");
                String name1 = input.next();
                for (int i = 0; i < Usernames.length; i++) {
                    if (name1.equals(Usernames[i])) {
                        found5 = true;
                        System.out.println("New Username:");
                        String name2 = input.next();
                        Usernames[i] = name2;
                        System.out.println("Username Updated Successfully! ");
                    }
                }
                break;
            case 2:
                System.out.println("Enter ID To Update: ");
                int ID1 = input.nextInt();
                for (int i = 0; i < UserIDs.length; i++) {
                    if (ID1 == UserIDs[i]) {
                        found5 = true;
                        System.out.println("New ID:");
                        int ID2 = input.nextInt();
                        UserIDs[i] = ID2;
                        System.out.println("ID Updated Successfully! ");
                    }
                }
                break;
            case 3:
                System.out.println("Enter Phone Number To Update: ");
                String Phone1 = input.next();
                for (int i = 0; i < PhoneNumbers.length; i++) {
                    if (Phone1.equals(PhoneNumbers[i])) {
                        found5 = true;
                        System.out.println("New Phone Number :");
                        String Phone2 = input.next();
                        PhoneNumbers[i] = Phone2;
                        System.out.println("Phone Number Updated Successfully! ");
                    }
                }
                break;
            case 4:
                System.out.println("Enter Email Address To Update: ");
                String Email1 = input.next();
                for (int i = 0; i < Emails.length; i++) {
                    if (Email1.equals(Emails[i])) {
                        found5 = true;
                        System.out.println("New Email Address:");
                        String Email2 = input.next();
                        Emails[i] = Email2;
                        System.out.println("Email Address Updated Successfully! ");
                    }
                }
                break;
            default:
                System.out.println("Invalid Choice");
        }
        if (!found5) {
            System.out.println("User Not Found!");
        }
    }


    // ==========================================
    // 6. MENUS & PANELS (AdminPanel, UserPanel, Main)
    // ==========================================
    public static void LogInmenu() {
        System.out.println("=============== WELCOME TO LOG IN PAGE ===============");
        System.out.println();
        System.out.println("=============================================");
        System.out.println("\t\tPlease Enter your Credentials");
        System.out.println("\n=============================================");
        System.out.println("Enter Your Username :");
    }

    public static void AdminMenu() {
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
        System.out.print("Please Choose An Operation : ");
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
                    ShowStatistics();
                    break;
                case 2:
                    searshUserAdmin();
                    break;
                case 3:
                    viewAllUsers();
                    break;
                case 4:
                    UpdateUser();
                    break;
                case 5:
                    viewAllTransactionAdmin();
                    break;
                case 6:
                    BlockAccount();
                    break;
                case 7:
                    UnblockAccount();
                    break;
                case 8:
                    viewUserDetails();
                    break;
                case 9:
                    System.out.println("Logging Out .....THANK YOU FOR USING QUICK PAY APP ! , GOODBYE 👋 .");
                    isTrue = false;
                    System.out.println();
                    break;
                default:
                    System.out.println("Invalid Choice [❌] , Please Enter a Number from 1 to 9 ");
            }
        }
    }

    public static void UserOptions(int Sindex) {
        boolean userLogged = true;
        while (userLogged) {
            System.out.println("\n=============== User Panel ===============");
            System.out.println("1. Show Balance ");
            System.out.println("2. Transfer Money ");
            System.out.println("3. Transaction History ");
            System.out.println("4. Show Favorites ");
            System.out.println("5. Add Favorite ");
            System.out.println("6. Remove Favorite ");
            System.out.println("7. Search Recipient ");
            System.out.println("8. View Profile ");
            System.out.println("9. Update Profile ");
            System.out.println("10. Deposit Money");
            System.out.println("11. Withdraw Money");
            System.out.println("\n12. Logout ");
            System.out.print("Please Choose An Option To Proceed: ");
            int UserOption = input.nextInt();
            switch (UserOption) {
                case 1:
                    showMyBalance(Sindex);
                    break;
                case 2:
                    TransByPhone(Sindex);
                    break;
                case 3:
                    STransHistory(Sindex);
                    break;
                case 4:
                    showFavorites(Sindex);
                    break;
                case 5:
                    addFavorite(Sindex);
                    break;
                case 6:
                    removeFavorite(Sindex);
                    break;
                case 7:
                    searchRecipient();
                    break;
                case 8:
                    viewProfile(Sindex);
                    break;
                case 9:
                    updateProfile();
                    break;
                case 10:
                    deposit(Sindex);
                    break;
                case 11:
                    withdraw(Sindex);
                    break;
                case 12:
                    System.out.println("Logging Out ......... Thank You For Using Quick Pay App , GoodBye 👋 . ");
                    userLogged = false;
                    break;
                default:
                    System.out.println("Invalid choice! Please enter a number between 1 and 12.");
            }
        }
    }

    public static void userPanel() {
        boolean isRunning = true;
        while (isRunning) {
            System.out.println("\n========== USER PANEL ==========");
            System.out.println("1. Register New Account");
            System.out.println("2. Login to Your Account");
            System.out.println("3. Back to Main Menu");
            System.out.print("Please Choose An Operation : ");
            int UserChoice = input.nextInt();
            input.nextLine();
            switch (UserChoice) {
                case 1:
                    System.out.println("\n=========== Welcome To Account Register ===========");
                    System.out.println("Please Enter Your Username :");
                    String name = input.nextLine();
                    System.out.println("Please Enter Your Password :");
                    String pass = input.next();
                    System.out.println("Please Enter Your Email Address :");
                    String email = input.next();
                    System.out.println("Enter Your Phone Number : ");
                    String Phone = input.next();
                    AddUser(name, pass, email, Phone);
                    System.out.println("Your Account Has Been Registered Successfully ✅ ");
                    break;
                case 2:
                    LogInmenu();
                    String LoginUserName = input.nextLine();
                    System.out.println("Enter Your Password :");
                    String LoginPassword = input.next();
                    boolean UserFound = false;
                    int LoggedInIndex = -1;
                    for (int i = 0; i < UserCount; i++) {
                        if (Usernames[i].equalsIgnoreCase(LoginUserName) && UserPassword[i].equalsIgnoreCase(LoginPassword)) {
                            UserFound = true;
                            LoggedInIndex = i;
                            break;
                        }
                    }
                    if (UserFound) {
                        if (!ActiveAccount[LoggedInIndex]) {
                            System.out.println("This Account Has Been Blocked By Admin [❌]");
                        }else {
                            System.out.println("Login Successfully ✅ Welcome " + Usernames[LoggedInIndex] + " Your Id is " + UserIDs[LoggedInIndex]);
                            UserOptions(LoggedInIndex);
                        }
                    } else {
                        System.out.println("Incorrect Username Or Password [❌]");
                    }
                    break;
                case 3:
                    isRunning = false;
                    break;
                default:
                    System.out.println("Invalid Choice [❌]");
            }
        }
    }

    public static void MainMenu() {
        System.out.println("============================================");
        System.out.println("\t\t\tWELCOME TO QUICK PAY ");
        System.out.println("============================================");
        System.out.println();
        System.out.println("1 : Login As Admin");
        System.out.println("2 : Login As User");
        System.out.println("3 : Logout");
        System.out.print("Please Choose An Operation : ");
    }

    // ==========================================
    // 7. MAIN METHOD
    // ==========================================
    public static void main(String[] args) {
        int Choice = 0;
        // Test data pre-seeded
        AddUser("Mohamed", "1234", "Mohamed@gmail.com", "01223344556");
        AddUser("Ahmed", "12345", "Ahmed@gmail.com", "01122334456");
        AddUser("Nour", "123456", "Nour@gmail.com", "01112233445");
        AddUser("Arwa", "1234567", "Arwa@gmail.com", "01111223344");
        AddUser("Yasmeen", "12345678", "Yasmeen@gmail.com", "01224455667");

        while (Choice != 3) {
            MainMenu();
            Choice = input.nextInt();
            switch (Choice) {
                case 1:
                    admin();
                    break;
                case 2:
                    userPanel();
                    break;
                case 3:
                    System.out.println("THANK YOU FOR USING QUICK PAY APP ! , GOODBYE 👋 .");
                    break;
                default:
                    System.out.println("Invalid Choice [❌]");
            }
        }
    }
}
