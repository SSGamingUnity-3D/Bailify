# 📱 Excuse Generator App

A fun and functional Android app that lets users generate, rate, and save creative excuses across categories like School, Work, and Sci-fi. Built with Java, SQLite, and Material Design components.

---

## ✨ Features

- ✅ **First-Time Registration**
  - First-time users register once.
  - Returning users log in directly.

- 🔐 **Login Screen**
  - Simple login form.
  - "Forgot password" placeholder included for future support.

- 💡 **Excuse Generator**
  - Built-in excuses for categories like:
    - School
    - Work
    - Aliens / Sci-fi
  - 10 excuses per category included by default.

- 🌟 **Favorites**
  - Users can rate excuses.
  - Save top-rated excuses as favorites.
  - View, edit, or delete favorites.

- ➕ **Custom Excuses**
  - Users can add their own excuses by category.

- 🌓 **Light/Dark Theme Support**
  - Based on Material Components for consistent look and feel.

---

## 🧰 Tech Stack

- **Language:** Java
- **Database:** SQLite (via `SQLiteOpenHelper`)
- **UI Framework:** Material Design Components
- **IDE:** Android Studio

---

## 📦 App Structure

```bash
app/
├── activities/
│   ├── LauncherActivity.java
│   ├── RegisterActivity.java
│   ├── LoginActivity.java
│   ├── MainActivity.java
│   ├── GeneratedExcuseActivity.java
│   ├── FavoriteExcusesActivity.java
│   └── AddCustomExcuseActivity.java
├── database/
│   └── DatabaseHelper.java
├── layouts/
│   ├── activity_register.xml
│   ├── activity_login.xml
│   ├── activity_main.xml
│   ├── activity_generated_excuse.xml
│   ├── activity_favorite_excuses.xml
│   ├── item_favorite.xml
│   └── activity_add_custom_excuse.xml
└── README.md
