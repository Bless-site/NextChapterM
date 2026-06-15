## App Purpose

NextChapter addresses the high cost of textbooks faced by students in African higher education by providing a structured, mobile-first platform where students can list, search, and inquire about second-hand textbooks within their academic community — replacing unreliable WhatsApp groups and Facebook Marketplace listings.

**Target Audience:** Undergraduate and postgraduate students (NQF Levels 5–7) at African universities and colleges.

---

## Core Features (MVP)

1. **User Registration & Login** – Full name, student email, password, and institution fields with full input validation
2. **Home Screen / Book Listings** – RecyclerView displaying recent textbook listings with cover, title, course, condition, and price
3. **Search & Filtering** – Real-time keyword search across title, author, course, and description
4. **Add Book Listing (Sell)** – Form to list a textbook for sale with condition selector and validation
5. **Book Detail View** – Expanded view with seller information, badges, and description
6. **Inquiry / Messaging** – Send inquiries to sellers with quick-reply templates and contact preferences
7. **Navigation** – Bottom navigation bar (Home, Search, Sell) with full screen-to-screen routing

---

## Tech Stack

- **Language:** Kotlin
- **UI:** XML Layouts with View Binding
- **Architecture:** MVVM (Model-View-ViewModel)
- **State Management:** LiveData + ViewModel (in-memory data, no database/backend)
- **Navigation:** Android Navigation Component (NavGraph, NavHostFragment, Safe Args)
- **UI Components:** RecyclerView, BottomNavigationView, TextInputLayout, MaterialButton, CardView, ConstraintLayout, RadioGroup, ChipGroup
- **Minimum SDK:** API 24 (Android 7.0)
- **Target / Compile SDK:** API 35 (Android 15)
- **Build System:** Gradle with Kotlin DSL (`build.gradle.kts`)

---

## Project Structure

```
com.nextchapter.app
├── model/
│   ├── Book.kt              # Textbook data class
│   └── User.kt              # User data class
├── viewmodel/
│   └── BookViewModel.kt      # Shared state via LiveData
├── adapter/
│   └── BookAdapter.kt        # RecyclerView ListAdapter + DiffUtil
├── ui/
│   ├── MainActivity.kt
│   ├── auth/
│   │   └── RegistrationFragment.kt
│   ├── home/
│   │   └── HomeFragment.kt
│   ├── search/
│   │   └── SearchFragment.kt
│   ├── sell/
│   │   └── SellFragment.kt
│   ├── detail/
│   │   └── BookDetailFragment.kt
│   └── inquiry/
│       └── InquiryFragment.kt
res/
├── layout/        # All fragment and item layouts
├── values/        # colors.xml, strings.xml, themes.xml
├── navigation/    # nav_graph.xml
├── menu/          # bottom_nav_menu.xml
└── drawable/      # App icon and book cover graphics
```

---

## Design System – African Aesthetic

| Colour | Hex | Usage |
|---|---|---|
| African Green | `#2B5F4A` | Headers, buttons, navigation bar, badges |
| Kente Gold | `#E8860A` | Price badges, Kente strip, notification badge |
| Terracotta | `#C94A2C` | Book cover placeholder, Kente strip |
| Warm Ivory | `#FDF8F2` | App-wide background |
| Soft Sage | `#EAF3EF` | Book cover placeholders, row shading |
| Dark Charcoal | `#1A1A1A` | Primary body text |
| Mid Grey | `#777777` | Subtitles and metadata |

The Kente-inspired colour strip and African green headers appear consistently across all six screens, with seller avatars using initials on a green background as a culturally grounded identity indicator.

---

## App Screens

1. **Registration / Login** – App entry point with input validation
2. **Home** – Recent listings with search bar and filter chips
3. **Search** – Live-filtered search results
4. **Add Book (Sell)** – Listing creation form
5. **Book Detail** – Expanded book information with seller details
6. **Inquiry** – Messaging screen with quick templates

---

## Getting Started

1. Clone this repository
   ```bash
   git clone https://github.com/<your-username>/NextChapter-MADA372.git
   ```
2. Open the project in **Android Studio** (latest stable version)
3. Let Gradle sync complete
4. Run on an emulator or physical device with **API 24+**

---

## Branching Strategy

| Branch | Purpose |
|---|---|
| `main` | Stable, production-ready code |
| `development` | Active development and feature additions |

---

## Project Phases

| Phase | Title | Key Deliverables |
|---|---|---|
| **SS1** | Strategic Planning & Systems Thinking | Problem statement, MVP scope, functional requirements, conceptual architecture |
| **SS2** | UX/UI Design & Prototyping | Design strategy, personas, sitemap, wireframes, prototype, style guide |
| **SS3** | Application Development & Implementation | Kotlin + XML implementation, navigation logic, MVP features, GitHub setup |
| **SS4** | Final Application & Deployment | Code quality, data binding, UI validation, final GitHub submission |

---

## Author

**Blessing Rugoyi**
Student Number: 24301417
MADA372 – Mobile Application Development A
STADIO Higher Education, 2026
