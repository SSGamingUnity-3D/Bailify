# 🤖 Excuse Generator App

An Android app to generate, rate, and save funny or custom excuses. Great for entertainment or light-hearted scenarios like skipping school, being late to work, or dealing with aliens. Built using **Java**, **SQLite**, and **Material Components** with optional **OpenAI GPT integration**.

---

## 📱 Features

- 📝 **User Registration & Login**
  - First-time users register, then only login is shown.
  - Optional "Forgot Password" placeholder included.

- 💬 **Excuse Generation**
  - Default categories: `School`, `Work`, `Aliens/Sci-fi`, etc.
  - Each category includes 10 predefined excuses.
  - Optional AI-powered excuse generator using OpenAI GPT API.

- 🌟 **Favorite Excuses**
  - Rate and save generated excuses.
  - View all saved favorites with rating and category.
  - Edit or delete favorites from a custom list view.

- ➕ **Add Custom Excuses**
  - Users can create their own excuses and add them by category.

- 🌓 **Material Design + Light/Dark Theme Support**

---

## 🧰 Tech Stack

- **Language:** Java
- **Database:** SQLite (via `SQLiteOpenHelper`)
- **UI:** Material Design (`MaterialComponents`)
- **AI Integration (Optional):** OpenAI GPT (`text-davinci-003`, `gpt-3.5`, or `gpt-4`)
- **IDE:** Android Studio

---

## 📦 Project Structure

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

