plugins {
    id("application-optimize-plugin")
    id("privacy-plugin")
}

android {
    compileSdk = libs.versions.compileSdkVersion.get().toInt()
    defaultConfig {
        applicationId = "com.silencefly96.fundark"
        minSdk = libs.versions.minSdkVersion.get().toInt()
        targetSdk = libs.versions.targetSdkVersion.get().toInt()
        versionCode = libs.versions.versionCode.get().toInt()
        versionName = libs.versions.versionName.get()
        ndk {
            abiFilters.add("armeabi-v7a")
            abiFilters.add("arm64-v8a")
        }
    }
    ndkVersion = "19.0.5232133"
    aaptOptions {
        noCompress.addAll(arrayOf(".unity3d", ".ress", ".resource", ".obb", "*.js", "*.js.map", "*.ts"))
        ignoreAssetsPattern = "!.svn:!.git:!.ds_store:!*.scc:.*:!CVS:!thumbs.db:!picasa.ini:!*~"
    }
    android {
        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_1_8
            targetCompatibility = JavaVersion.VERSION_1_8
        }
    }
    signingConfigs {
        create("release") {
            keyAlias = "xrdemo"
            keyPassword = "123456"
            storeFile = file("../xrdemo.jks")
            storePassword = "123456"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            signingConfig = signingConfigs.getByName("release")
//            isDebuggable = false
        }
        getByName("debug") {
            signingConfig = signingConfigs.getByName("release")
//            isDebuggable = false
        }
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar","*.aar"))))
    implementation("com.huawei.hms:arenginesdk:3.7.0.3")
    implementation("com.local:androidpermissionmanager:1.0.0")
    implementation("com.local:arcore_client:1.0.0")
    implementation("com.local:arpresto:1.0.0")
    implementation("com.local:huawei_ar_engine_plugin_required:1.0.0")
    implementation("com.local:huawei_ar_engine_unityplugin:1.0.0")
    implementation("com.local:nativegallery:1.0.0")
    implementation("com.local:nativeshare:1.0.0")
    implementation("com.local:runtimepermissions:1.0.0")
    implementation("com.local:standardar:1.0.0")
    implementation("com.local:unityandroidpermissions:1.0.0")
    implementation("com.local:unityarcore:1.0.0")
    implementation("com.local:unitylibrary_release:1.0.0")
    //测试相关
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    //从基础库继承各个依赖
    implementation(project(":module_base"))
}