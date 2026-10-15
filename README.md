# 🚀 Quick Pay Project

> Hello User 👋 Thank You For Downloading Our Application **Quick Pay** 😍.

---

## 💡 1. Project Overview

**Make Managing Your Money Much Easier And Smoother!**  
Quick Pay is a Java-based console application designed to simulate a digital wallet system efficiently.

---

## ✨ 2. Functional Requirements

### 👤 User Features:
* 1️⃣ **Simple User Interface Menu:** Easy navigation across panels.
* 2️⃣ **Account Management:** Register a new account with a unique system ID.
* 3️⃣ **All-in-One Options:** Gathered all money management options in one User Panel.
* 4️⃣ **Easy Money Transfers:** Only Phone Number and Username are required to transfer money!
* 5️⃣ **Transaction History:** View detailed logs of all sent and received transactions.
* 6️⃣ **Favorites Menu:** Add, remove, and show your favorite contacts.
* 7️⃣ **Recipient Search:** Search for other users by username, email, or phone number.

### 🛠️ Admin Features:
* 🔑 **Admin Authentication:** Secure and easy login method using (`admin` / `123`).
* 📊 **Show Statistics Option:** Allows Admin to view all registered, blocked, and active accounts easily.
* 🔍 **User Search:** Search for any user and view complete account information.

---

## 📜 3. Business Rules

* 🚫 Users **cannot** transfer money to their own account.
* 🚫 Transfers to or from **blocked accounts** are strictly rejected.
* 🚫 Transactions with amounts **$\le 0$** are completely rejected.
* ⭐ **Favorites Rules:** Users cannot add themselves or duplicate an already existing user in their favorites list.

---

## 🗂️ 4. Data Structure Explanation

* **User Data Arrays:** `UserIDs`, `Usernames`, `Emails`, `UserPassword`, `PhoneNumbers`, `Sbalance`, and `ActiveAccount` store all user credentials and account statuses synchronously.
* **Transaction Records:** `tIDS`, `Tamounts`, `Tresult`, `Tsenders`, and `Treceivers` record historical transaction logs indexed by `Tcount`.

---

## 🛠️ 5. Methods Documentation

| Method Name | Description |
| :--- | :--- |
| `AddUser()` | Registers a new user with initialized balance and active status. |
| `TransByPhone()` & `Trans()` | Validates and executes money transfers between accounts. |
| `CanTrans()` | Helper function checking balance sufficiency and account status. |
| `updateProfile()` | Enables users to update their personal profile details easily. |

---

## 🧪 6. Test Cases

| Scenario | Input / Action | Expected Output | Status |
| :--- | :--- | :--- | :---: |
| **Successful Transfer** | Transfer to active receiver with sufficient balance | "transfer successful", updated balances | ✅ |
| **Insufficient Balance** | Transfer amount > sender's current balance | "Transfer Failed" | ❌ |
| **Non-existent User** | Transfer to an unregistered phone number | "User Not Found" | ❌ |

---

## ⚠ How To Use Our Application: Step-By-Step Tutorial 🤔

* **Step 1️⃣:** Press the **Start** button to proceed.
* **Step 2️⃣:** Choose an option from the **Main Menu** (From 1 to 3).
* **Step 3️⃣:** 
  * If **1** is chosen: Enter Admin credentials to proceed.
  * If **2** is chosen: Choose an option (`1. Register Account` / `2. Login`).
* **Step 4️⃣:** 
  * **Admin Panel:** Choose an option from 1 to 9.
  * **User Panel:** Choose an option from 1 to 12.
* **Last Step:** Enjoy using **Quick Pay**! Don't forget to rate us ⭐⭐⭐⭐⭐!

---

## 👥 TEAM MEMBERS

* **Team Leader:** Mohamed Yasser
* **Team Members:** 
  * AbdelAziz Reda
  * Arwa Osama
  * Yasmeen AlaaEldeen
