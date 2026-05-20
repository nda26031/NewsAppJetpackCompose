# News App Playlist Implementation Plan

Nguon tham khao:
- YouTube playlist: https://www.youtube.com/playlist?list=PLzZEuVaFb9Exi-pc8qtHBrrLg8bUn-TP6
- Note tong hop playlist: https://hackmd.io/@RainBowT/BJoy3W_P6

## Muc tieu

Xay dung ung dung doc tin tuc bang Jetpack Compose theo MVVM va Clean Architecture. App can co onboarding, home feed co paging, search, article detail, chia se/mo bai viet tren browser, bookmark bai viet bang Room va navigation bang bottom bar.

## Chuc nang chinh can hoan thanh

- Onboarding screen cho lan dau mo app, co page indicator va nut dieu huong.
- Luu trang thai da xem onboarding bang DataStore Preferences.
- Splash screen va dieu huong start destination theo app entry state.
- Home screen hien thi danh sach tin tuc tu News API, co paging va loading state.
- Search screen tim tin tuc theo query, dung paging rieng cho search.
- Detail screen hien thi thong tin bai viet, co nut mo link goc, share va bookmark.
- Bookmark screen hien thi cac bai viet da luu trong Room database.
- Bottom navigation cho Home, Search va Bookmark.
- Clean Architecture gom 3 layer: data, domain va presentation.
- Dependency Injection bang Dagger Hilt.

## Roadmap de trien khai trong repo hien tai

### 1. Project setup

- Kiem tra lai Gradle, Kotlin, Compose va Android plugin version.
- Them dependency vao version catalog:
  - `androidx.navigation:navigation-compose`
  - `androidx.lifecycle:lifecycle-viewmodel-compose`
  - `androidx.hilt:hilt-navigation-compose`
  - `com.google.dagger:hilt-android` va kapt/ksp compiler
  - `androidx.datastore:datastore-preferences`
  - `androidx.core:core-splashscreen`
  - `com.squareup.retrofit2:retrofit`
  - converter JSON, vi du Gson hoac Kotlin serialization
  - `com.squareup.okhttp3:logging-interceptor`
  - `androidx.paging:paging-runtime` va `androidx.paging:paging-compose`
  - `androidx.room:room-runtime`, `room-ktx`, compiler
  - `io.coil-kt:coil-compose`
- Tao `NewsApplication` va khai bao trong `AndroidManifest.xml`.
- Cau hinh API key an toan qua `local.properties` hoac `BuildConfig`, khong hard-code key trong source.

### 2. Cau truc Clean Architecture

Tao package chinh duoi `com.example.newsappjetpackcompose`:

```text
data/
  local/
  remote/
  repository/
di/
domain/
  manager/
  model/
  repository/
  usecase/
presentation/
  common/
  details/
  home/
  news_navigator/
  onboarding/
  search/
  bookmark/
util/
```

Quy tac phu thuoc:
- `presentation` goi `domain`.
- `domain` dinh nghia model, repository interface va use case.
- `data` implement repository interface va lam viec voi API, DataStore, Room.
- `di` noi cac implementation vao interface bang Hilt.

### 3. Splash va Onboarding

- Them splash theme trong `res/values/themes.xml` va neu can them ban `values-night`.
- Goi `installSplashScreen()` trong `MainActivity`.
- Tao model `Page` cho noi dung onboarding.
- Tao cac composable:
  - `OnBoardingPage`
  - `OnBoardingScreen`
  - `PageIndicator`
  - button dung lai trong `presentation.common`
- Xu ly nut Back/Next/Get Started.
- Khi bam Get Started, gui event vao ViewModel de luu app entry.

### 4. DataStore app entry

- Tao interface `LocalUserManager` trong domain.
- Tao implementation trong data dung DataStore Preferences.
- Tao use cases:
  - `ReadAppEntry`
  - `SaveAppEntry`
  - `AppEntryUseCases`
- Tao `OnBoardingViewModel` de xu ly event `SaveAppEntry`.
- Tao `MainViewModel` doc app entry va quyet dinh start destination.

### 5. Navigation

- Tao sealed class hoac object `Route` gom:
  - `OnBoardingScreen`
  - `HomeScreen`
  - `SearchScreen`
  - `DetailsScreen`
  - `BookmarkScreen`
  - `AppStartNavigation`
  - `NewsNavigation`
- Tao `NavGraph` co nested graph cho onboarding va main app.
- Dung state tu `MainViewModel` de chon start destination.
- Them `NewsNavigator` voi `Scaffold` va bottom navigation.
- Chi hien bottom bar o Home, Search va Bookmark; an o Details.

### 6. Remote News API va Paging

- Tao DTO tu response cua News API:
  - `NewsResponse`
  - `ArticleDto` hoac map truc tiep sang domain model `Article`
  - `Source`
- Tao `NewsApi` bang Retrofit:
  - endpoint lay tin moi
  - endpoint search
- Tao `NewsPagingSource` cho home.
- Tao `SearchNewsPagingSource` cho search.
- Implement `NewsRepositoryImpl` tra ve `Flow<PagingData<Article>>`.
- Tao use cases:
  - `GetNews`
  - `SearchNews`
  - gom vao `NewsUseCases`.

### 7. Home screen

- Tao `HomeViewModel`, inject `NewsUseCases`.
- Cache paging flow trong `viewModelScope`.
- Tao UI:
  - search bar click de navigate sang Search
  - headline/title section
  - list article bang `LazyPagingItems`
  - loading shimmer
  - error/empty state
- Tao component dung chung:
  - `ArticleCard`
  - `ArticlesList`
  - `SearchBar`
  - shimmer item.

### 8. Search screen

- Tao `SearchViewModel` quan ly query va paging result.
- Debounce query neu can de tranh goi API qua nhieu.
- UI gom top search input, result list, loading shimmer, empty state.
- Click article navigate sang Details.

### 9. Detail screen

- Truyen article sang Details. Neu object lon, can can nhac luu JSON route argument hoac shared state.
- UI hien:
  - image
  - title
  - author/source/date
  - description/content
  - action bar gom Back, Share, Open in browser, Bookmark.
- Dung implicit intent de mo URL va share.
- Tao `DetailsViewModel` xu ly bookmark/unbookmark va side effect message.

### 10. Room va Bookmark

- Tao `ArticleEntity`.
- Tao `NewsDao`:
  - upsert article
  - delete article
  - select all articles
  - select article by url/title neu can check bookmark state
- Tao `NewsDatabase` va TypeConverter neu article co nested object.
- Mo rong `NewsRepository` voi local operations.
- Tao use cases:
  - `UpsertArticle`
  - `DeleteArticle`
  - `SelectArticles`
  - `SelectArticle`
- Tao `BookmarkViewModel`.
- Tao `BookmarkScreen` dung lai `ArticlesList`, click sang Details.

### 11. System UI va theme

- Dieu chinh status bar/navigation bar theo light/dark theme.
- Dung Compose side effect de cap nhat system bars.
- Dam bao UI khong bi che boi status bar/navigation bar bang padding phu hop.

### 12. Hoan thien va kiem thu

- Chay `ktlintCheck` va format source truoc khi commit.
- Chay compile debug sau moi nhom thay doi lon.
- Test thu cong:
  - Lan dau mo app thay onboarding.
  - Bam Get Started lan sau vao thang Home.
  - Home load paging thanh cong.
  - Search co ket qua va error state ro rang.
  - Details mo browser/share duoc.
  - Bookmark luu/xoa va hien trong tab Bookmark.
  - Rotate screen khong mat state quan trong.
- Viet unit test cho use case va repository fake neu co thoi gian.

## Thu tu lam khuyen nghi

1. Cai dependency va Hilt/Application class.
2. Tao skeleton package Clean Architecture.
3. Lam Splash, DataStore va Onboarding.
4. Lam Navigation va bottom bar.
5. Lam remote API, repository va paging.
6. Lam Home screen.
7. Lam Search screen.
8. Lam Details screen.
9. Lam Room bookmark.
10. Polish UI, error states va test.

## Ghi chu cho du an hien tai

- Repo hien tai dang la template Compose co `MainActivity`, theme va test mau. Nen tach UI hien co sang `presentation` som de tranh source root bi phinh to.
- Ktlint va `.editorconfig` da co, nen moi file Kotlin moi can pass style ngay tu dau.
- Can tao tai khoan News API va lay API key truoc khi lam remote data.
- Khong commit `local.properties` hoac API key len source control.
