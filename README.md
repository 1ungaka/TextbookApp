# 📚 Textbook Market — CSC313 Assignment 2

**University of Fort Hare | Department of Computer Science**  
OOP Android App · Java · Android Studio

---

## Overview

A second-hand textbook marketplace Android app that lets students browse, list, and search for textbooks for sale. Built to satisfy all four functional requirements of CSC313 Assignment 2, plus all OOP concepts (Interfaces, Abstract Classes, Exception Handling, Encapsulation, Inheritance).

---

## Functional Requirements Checklist

| # | Requirement | Status |
|---|-------------|--------|
| 1 | Browse all available textbooks | ✅ BrowseFragment + RecyclerView |
| 2 | List a textbook with details (copies, price, banking info) | ✅ AddBookFragment with Material form |
| 3 | Submit listing; prevent duplicate entries | ✅ DuplicateBookException thrown by DataManager |
| 4 | Search by seller name or book title | ✅ SearchFragment with live filtering |

---

## OOP Concepts Used

| Concept | Where |
|---------|-------|
| **Interface** | `Listable.java` — defines the contract for any item listed for sale |
| **Abstract Class** | `ListedItem.java` — base class implementing `Listable`; `getSummary()` is abstract |
| **Inheritance** | `Textbook` → `ListedItem` → `Listable` |
| **Encapsulation** | All `Textbook` fields are `private` with getters/setters |
| **Custom Exception** | `DuplicateBookException` thrown when title+seller already exists |
| **Singleton Pattern** | `DataManager.getInstance()` manages the shared data store |
| **Polymorphism** | `BookAdapter` works with any `Textbook` (extensible to other `ListedItem` subtypes) |

---

## Project Structure

```
app/src/main/
├── java/com/example/textbookapp/
│   ├── Listable.java               ← Interface
│   ├── ListedItem.java             ← Abstract class
│   ├── Textbook.java               ← Concrete model (extends ListedItem)
│   ├── DuplicateBookException.java ← Custom exception
│   ├── DataManager.java            ← Singleton data store + search
│   ├── MainActivity.java           ← Host activity with BottomNavigationView
│   ├── BrowseFragment.java         ← Browse all books
│   ├── AddBookFragment.java        ← List a new textbook
│   ├── SearchFragment.java         ← Search by title / seller
│   ├── BookAdapter.java            ← RecyclerView adapter
│   └── BookDetailBottomSheet.java  ← Detail popup (BottomSheetDialogFragment)
└── res/
    ├── layout/
    │   ├── activity_main.xml
    │   ├── fragment_browse.xml
    │   ├── fragment_add_book.xml
    │   ├── fragment_search.xml
    │   ├── item_book.xml
    │   └── bottom_sheet_book_detail.xml
    ├── menu/bottom_nav_menu.xml
    ├── values/colors.xml · strings.xml · themes.xml
    ├── color/nav_selector.xml
    └── drawable/bg_price_badge.xml
```

---

## Getting Started

1. **Clone / open** this folder in Android Studio (Electric Eel or newer).
2. Let Gradle sync automatically — all dependencies are in `app/build.gradle`.
3. **Run** on an emulator (API 24+) or a physical device.
4. No internet permission required — data is stored in-memory.

### Dependencies (auto-downloaded by Gradle)

```
androidx.appcompat:appcompat:1.6.1
com.google.android.material:material:1.11.0
androidx.recyclerview:recyclerview:1.3.2
androidx.cardview:cardview:1.0.0
androidx.coordinatorlayout:coordinatorlayout:1.2.0
```

---

## GitHub Setup (per assignment instructions)

```bash
git init
git add .
git commit -m "feat: initial TextbookApp implementation — CSC313 Assignment 2"
git branch -M main
git remote add origin https://github.com/<your-org>/TextbookApp-CSC313-A2.git
git push -u origin main
```
Then add all group members **and the lecturer** as collaborators under *Settings → Collaborators*.

---

## App UI Flow

```
Launch
  └─ MainActivity
       ├─ [Browse]  → RecyclerView of all books → tap card → BookDetailBottomSheet
       ├─ [Add]     → Form with validation → DuplicateBookException on duplicate → success Toast
       └─ [Search]  → Live search field → filtered RecyclerView → tap card → BookDetailBottomSheet
```

---

*Submitted for CSC313 Assignment 2 — Due 12 May 2026*
