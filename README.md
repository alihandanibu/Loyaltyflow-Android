# LoyaltyFlow Android

LoyaltyFlow is an Android mobile application developed as part of the **Mobile Programming** course project.

This repository currently contains the implementation for **Assignment 1 – Project Proposal + UI Foundations**.

The purpose of this milestone was to build a clean and working UI foundation using **Jetpack Compose** and **MVVM architecture**, while following the project structure and patterns covered during the first weeks of the course.

---

## Project Idea

The idea behind LoyaltyFlow is to simulate a simple digital loyalty system where users can:

- view their loyalty points
- check available offers
- submit purchase/receipt information
- browse rewards
- open reward details
- view profile information

At this stage, the application is focused on **UI structure, navigation, state handling, and architecture**, while the data is still mock/static and prepared for future expansion.

---

## What I Implemented

For Assignment 1, I implemented the basic structure of the app with multiple screens and reusable UI components.

### Implemented screens

- **Home Screen**
  - shows welcome message
  - displays current loyalty points and tier
  - contains quick action buttons

- **Offers Screen**
  - displays a list of available promotional offers
  - uses reusable `OfferCard` composables

- **Scan Screen**
  - simulates a receipt submission form
  - includes input validation
  - enables submit only when input is valid
  - shows success message after valid submission

- **Rewards Screen**
  - displays a list of rewards
  - uses reusable `RewardCard` composables
  - handles empty-state scenario

- **Reward Details Screen**
  - opens details of a selected reward
  - demonstrates navigation with route arguments

- **Profile Screen**
  - displays sample user information
  - shows loyalty-related statistics

---

## Architecture

This project follows the **MVVM (Model–View–ViewModel)** pattern.

### Model
The model layer contains data classes used to represent app data, such as:
- dashboard stats
- offers
- rewards
- profile information

### ViewModel
Each screen has its own ViewModel that:
- stores screen state
- handles user actions
- updates UI state

### View (UI)
The UI is written using **Jetpack Compose**.
Composable functions are responsible for displaying data and sending user actions to the ViewModel.

### State flow in the app

The main flow used in the project is:

**UI → ViewModel → State → UI**

This means:
- user interacts with the UI
- the ViewModel processes the action
- state is updated
- Compose automatically refreshes the screen

---

## Project Structure

The project is organized by features.

```text
app/src/main/java/com/example/loyalityflow/

MainActivity.kt
data/model/Models.kt

ui/
  navigation/
    LoyaltyFlowApp.kt
    Routes.kt

  screens/
    home/
      HomeScreen.kt
      HomeUiState.kt
      HomeViewModel.kt
      components/
        DashboardCard.kt
        QuickActionButton.kt
        SectionHeader.kt

    offers/
      OffersScreen.kt
      OffersUiState.kt
      OffersViewModel.kt
      components/
        OfferCard.kt

    scan/
      ScanScreen.kt
      ScanUiState.kt
      ScanViewModel.kt
      components/
        ScanInputField.kt
        StatusMessage.kt

    rewards/
      RewardsScreen.kt
      RewardsUiState.kt
      RewardsViewModel.kt
      components/
        RewardCard.kt
        EmptyRewardsState.kt

    rewarddetails/
      RewardDetailsScreen.kt
      RewardDetailsUiState.kt
      RewardDetailsViewModel.kt
      components/
        DetailRow.kt
        RedeemButton.kt

    profile/
      ProfileScreen.kt
      ProfileUiState.kt
      ProfileViewModel.kt
      components/
        ProfileHeader.kt
        StatItem.kt

  theme/
    Color.kt
    Theme.kt
    Type.kt

This structure helps keep the project cleaner and makes reusable UI parts easier to manage.

Features Demonstrated in Assignment 1

This assignment demonstrates:

Android Studio project setup
Kotlin-based Android development
Jetpack Compose UI
Navigation Compose
MVVM architecture
reusable UI components
state-driven UI updates
basic form validation
handling of edge cases such as empty lists
Validation and Edge Cases

One of the important parts of this milestone was implementing simple logic and validation.

Validation example

On the Scan Screen:

receipt code cannot be blank
receipt code must meet minimum length
store name cannot be blank
submit button is enabled only when input is valid
Edge case example

On the Rewards Screen:

if there are no rewards, an empty-state message is displayed instead of leaving the UI blank
Technologies Used
Kotlin
Jetpack Compose
Material 3
Navigation Compose
MVVM
Android Studio
Current Limitations

At this stage, the project still uses mock/static data.

The following parts are not yet implemented:

database persistence
API/backend integration
authentication
real QR/receipt scanning
reward redemption logic connected to storage

These parts are planned for future milestones.

How to Run the Project
Open the project in Android Studio
Let Gradle sync finish
Run the app on an emulator or physical Android device
The app starts from the Home Screen
Assignment 1 Coverage

This milestone covers the following requirements:

runnable Android project
GitHub repository with assignment branch
Compose-based UI
at least 5 screens
reusable components placed in components folders
MVVM structure
navigation between screens
basic input validation
state-based UI updates
Author

Ali Handan
IT student
Mobile Programming course project

Branch

This implementation is prepared on the assignment-1 branch.
