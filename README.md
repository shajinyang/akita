# Akita安卓开发框架（自用）
![](timg.jpg)
### 如何使用

#### Android Studio

第一步：在项目的gradle里配置

      allprojects {
        repositories {
            ...
            maven { url 'https://jitpack.io' }
        }
      }

第二步：在module的gradle里配置

     dependencies {
         implementation 'com.github.shajinyang:akita:1.2.0'
     }
















