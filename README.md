# 🚀 Quick Pay Project
### Hello User 👋 Thank You For Downloading Our Application **Quick Pay** 😍 .
---

1. ### Project Overview ###
Make Managing Your Money Much Easier And Smoother!
Quick Pay is a Java-based console application designed to simulate a digital wallet system.
---
2. ### Functional Requirements ###
👤 User Features: 
1️⃣ **Simple User Interface Menu .** 
2️⃣ **Account Management: Register a new account with a unique ID.**
3️⃣ **Gathered All Your Money Management Options** In 1️⃣ User Panel .  
4️⃣ **Money Transfer Became Easy ! Only Phone Number and Username are required to Transfter Money** !
5️⃣ **Transaction History: View detailed logs of all sent and received transactions.**
6️⃣ **Favourite Menu : Add , Remove , Show Your Favourite Contacts**
7️⃣ **Recipient Search: Search for other users by username, email, or phone number.**

🛠️ Admin Features:
Admin Login : Easy Admin Authentication Method using (admin/123).
Show Statistics Option : Allowing Admin to See all registered Accounts / Blocked / unblocked Easily .
User Search : Search for any user and view all his account information.
---

3. Business Rules :
   
User Cannot Transfer money to his own account
No transfers to blocked account
Transactions are rejected on Transfers <= 0 Or On Blocked Accounts .
Favourite Menu Rules : Users Cannot add themselves to Favourite menu or Add a user already Exists.
---
4. Data Structure Explanation

User Data Arrays: UserIDs, Usernames, Emails, UserPassword, PhoneNumbers, Sbalance, and ActiveAccount Store User Credentials .
Transaction Records: tIDS, Tamounts, Tresult, Tsenders, and Treceivers record historical transaction logs  by Tcount .
---
5. Methods Doccumentation :

AddUser(): Registers a new user .
TransByPhone(Sindex) & Trans(): Validates Transfering Money Between Users .
CanTrans() : Check Whether a user can transfer money or Not .
updateProfile(): Allows users to update their profile Easily .
---

6. Test Cases :

Successful Money Transfer ..... Transfering Money from sender account to receiver with sufficent balance ..... Output : "transfer successful", updated sender and receiver balances.
Insufficient Balance : if the transfered Money balance is greater than the sender’s Current Balance ...... Output : "Transfer Failed" . 
Transfering Money to A None Existint User : ..... Output : Transaction Failed ... User Not Found .
---

### ⚠ How To Use Our Application : Step By Step Tutorial  🤔

* **Step 1️⃣ :**  
  Press The Start Button To Proceed .

* **Step 2️⃣ :**  
  Choose An Option From The Main Menu (From 1 to 3) .

* **Step 3️⃣ :**  
  * If **Number 1** Is Chosen , Please Enter Your Credentials To Proceed .  
  * If **Number 2** Is Chosen , Please Choose An Option (`1.Register An Account` / `2.Login Account`).

* **Step 4️⃣ :**  
  * If Logged In As **Admin**... You Can Choose An Option From The Admin Panel (From 1 to 9) .  
  * If Logged In As **User**.... You Can Choose An Option From The User Panel (From 1 to 10) .

* **Last Step :**  
  Enjoy Using Our Application **Quick Pay** ! , Please Don’t Forget To Rate Us ⭐⭐⭐⭐⭐ On Google Play Store & Apple Store !

![Quick Pay Flow Chart](./Flow%20Chart%20Try%20Catchers.jpg)
---
