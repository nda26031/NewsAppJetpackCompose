# Giai thich cau hinh EditorConfig va ktlint

File `.editorconfig` giup thong nhat cach format code giua cac editor/IDE va giua cac thanh vien trong du an. Voi Kotlin, file nay con duoc ktlint doc de kiem tra style code.

## Cau hinh chung

### `root = true`

Day la file `.editorconfig` goc cua du an.

Khi editor doc cau hinh, no se dung lai o file nay va khong tiep tuc tim `.editorconfig` o cac thu muc cha.

### `[*]`

Ap dung cac rule ben duoi cho tat ca file.

`*` co nghia la moi ten file va moi loai file.

### `charset = utf-8`

Dung bo ma ky tu UTF-8.

UTF-8 giup file hien thi on dinh cac ky tu tieng Viet, ky tu dac biet va da ngon ngu.

### `end_of_line = lf`

Dung kieu xuong dong `LF`.

Co 2 kieu xuong dong pho bien:

- `LF`: thuong dung tren Linux/macOS.
- `CRLF`: thuong dung tren Windows.

Dung thong nhat `LF` giup tranh viec file bi thay doi hang loat chi vi khac kieu xuong dong.

### `indent_style = space`

Dung dau cach de thut dong.

Khong dung tab.

### `indent_size = 4`

Moi cap thut dong bang 4 dau cach.

Vi du:

```kotlin
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}
```

### `insert_final_newline = true`

Dam bao moi file ket thuc bang mot dong moi.

Neu file khong co newline cuoi file, ktlint co the bao loi:

```text
File must end with a newline
```

### `trim_trailing_whitespace = true`

Tu dong xoa khoang trang thua o cuoi dong.

Vi du dong nay co khoang trang thua sau dau `)`:

```kotlin
println("Hello")    
```

Sau khi format:

```kotlin
println("Hello")
```

## Cau hinh rieng cho Kotlin

### `[*.{kt,kts}]`

Ap dung cac rule ben duoi cho file Kotlin.

- `.kt`: file Kotlin source binh thuong.
- `.kts`: Kotlin script, vi du `build.gradle.kts`.

### `ij_kotlin_allow_trailing_comma = false`

Khong cho phep trailing comma trong khai bao.

`trailing comma` la dau phay nam o cuoi danh sach.

Khong hop le theo cau hinh nay:

```kotlin
data class Article(
    val title: String,
    val url: String,
)
```

Nen viet:

```kotlin
data class Article(
    val title: String,
    val url: String
)
```

### `ij_kotlin_allow_trailing_comma_on_call_site = false`

Khong cho phep trailing comma tai noi goi ham.

`call site` la vi tri goi ham.

Khong hop le theo cau hinh nay:

```kotlin
Greeting(
    name = "Android",
    modifier = Modifier,
)
```

Nen viet:

```kotlin
Greeting(
    name = "Android",
    modifier = Modifier
)
```

### `ij_kotlin_imports_layout = *`

Quy dinh cach sap xep import cua IntelliJ/Android Studio.

Gia tri `*` nghia la dung mot layout import don gian, khong chia thanh nhieu nhom dac biet.

Vi du:

```kotlin
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import com.example.newsappjetpackcompose.ui.theme.NewsAppJetpackComposeTheme
```

### `ij_kotlin_packages_to_use_import_on_demand`

Quy dinh package nao duoc phep dung wildcard import.

`wildcard import` la import bang dau `*`.

Vi du:

```kotlin
import java.util.*
```

Thay vi:

```kotlin
import java.util.Date
import java.util.Locale
```

Cau hinh hien tai:

```ini
ij_kotlin_packages_to_use_import_on_demand = java.util.*, kotlinx.android.synthetic.**
```

Y nghia:

- `java.util.*`: cho phep wildcard import voi package `java.util`.
- `kotlinx.android.synthetic.**`: cho phep wildcard import voi cac package con cua `kotlinx.android.synthetic`.

Luu y: `kotlinx.android.synthetic` la API cu, khong nen dung cho du an Compose moi.

### `ktlint_code_style = android_studio`

Bao ktlint kiem tra code theo style gan voi Android Studio.

Gia tri nay phu hop voi du an Android vi Android Studio la IDE chinh de phat trien Android.

### `ktlint_function_naming_ignore_when_annotated_with = Composable`

Bo qua rule dat ten ham neu ham co annotation `@Composable`.

Binh thuong Kotlin function nen bat dau bang chu thuong:

```kotlin
fun loadNews() {
}
```

Nhung Jetpack Compose co convention dat ten UI composable bang PascalCase:

```kotlin
@Composable
fun HomeScreen() {
}
```

Neu khong co cau hinh nay, ktlint co the bao loi vi `HomeScreen` bat dau bang chu hoa.

### `ktlint_function_signature_body_expression_wrapping = default`

Quy dinh cach wrap function signature khi function dung expression body.

`function signature` la phan ten ham, tham so va kieu tra ve.

Vi du:

```kotlin
fun fullName(firstName: String, lastName: String): String
```

`expression body` la cach viet ham ngan bang dau `=`.

Vi du:

```kotlin
fun fullName(firstName: String, lastName: String): String = "$firstName $lastName"
```

Gia tri `default` nghia la dung hanh vi mac dinh cua ktlint.

### `ktlint_function_signature_rule_force_multiline_when_parameter_count_greater_or_equal_than = unset`

Khong ep function signature xuong nhieu dong dua tren so luong tham so.

Neu cau hinh nay duoc dat bang mot so, vi du `3`, thi ham co tu 3 tham so tro len co the bi ep xuong multiline.

Voi `unset`, ktlint khong ap them nguong bat buoc nay.

Vi du co the giu mot dong neu van gon:

```kotlin
fun Greeting(name: String, modifier: Modifier = Modifier) {
}
```

### `ktlint_ignore_back_ticked_identifier = false`

Khong bo qua identifier dat trong backtick.

`back-ticked identifier` la ten ham/bien duoc dat trong dau backtick.

Thuong gap trong unit test:

```kotlin
@Test
fun `login should fail when password is empty`() {
}
```

Khi dat `false`, ktlint van kiem tra nhung ten nay.

### `max_line_length = 100`

Gioi han moi dong toi da 100 ky tu.

Neu dong dai hon 100 ky tu, ktlint co the bao loi.

Khong nen viet:

```kotlin
proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
```

Nen tach dong:

```kotlin
proguardFiles(
    getDefaultProguardFile("proguard-android-optimize.txt"),
    "proguard-rules.pro"
)
```

## Cau hinh YAML

### `[*.{yml,yaml}]`

Ap dung cho file YAML.

YAML thuong dung trong CI/CD, config GitHub Actions, config tool.

### `indent_size = 2`

File YAML thuong dung thut dong 2 spaces thay vi 4 spaces.

Vi du:

```yaml
name: Android CI
on:
  push:
    branches:
      - main
```

## Tom tat nhanh

- Dung UTF-8 de ho tro tieng Viet va ky tu dac biet.
- Dung LF de thong nhat xuong dong.
- Dung 4 spaces cho Kotlin.
- Dung 2 spaces cho YAML.
- Khong dung trailing comma.
- Gioi han dong Kotlin/KTS o 100 ky tu.
- Cho phep ham `@Composable` dat ten PascalCase.
- Ktlint se doc cac rule nay de kiem tra style code.
