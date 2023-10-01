plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("maven-publish")
    id("signing")
}

group = properties["group"].toString()
version = properties["version.name"].toString()

data class PomProperties(
    val artifactId: String = properties["artifactId"].toString(),
    val name: String = properties["pom.name"].toString(),
    val description: String = properties["pom.description"].toString(),
    val url: String = properties["pom.url"].toString(),
    val licenseName: String = properties["pom.licenseName"].toString(),
    val licenseUrl: String = properties["pom.licenseUrl"].toString(),
    val devId: String = properties["pom.devId"].toString(),
    val devName: String = properties["pom.devName"].toString(),
    val devEmail: String = properties["pom.devEmail"].toString(),
    val devGit: String = properties["pom.devGit"].toString(),
    val scmConnection: String = properties["pom.scmConnection"].toString(),
)
val pomProperties = PomProperties()

publishing {
    publications {
        create<MavenPublication>("release") {
            afterEvaluate {
                from(components["release"])
            }

            pom {
                artifactId = pomProperties.artifactId
                name.set(pomProperties.name)
                description.set(pomProperties.description)
                url.set(pomProperties.url)
                licenses {
                    license {
                        name.set(pomProperties.licenseName)
                        url.set(pomProperties.licenseUrl)
                    }
                }
                developers {
                    developer {
                        id.set(pomProperties.devId)
                        name.set(pomProperties.devName)
                        email.set(pomProperties.devEmail)
                    }
                }
                scm {
                    connection.set(pomProperties.scmConnection)
                    developerConnection.set(pomProperties.devGit)
                    url.set(pomProperties.scmConnection)
                }
            }
        }
    }
}

signing {
    sign(publishing.publications["release"])
}

android {
    namespace = pomProperties.artifactId
    compileSdk = 33

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    publishing {
        singleVariant("release") {
            withSourcesJar()
            withJavadocJar()
        }
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.10.1")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    /* ====================================================
    *                      Compose
    * ===================================================*/
    implementation("androidx.activity:activity-compose:1.7.2")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    /* ====================================================
    *                      Room
    * ===================================================*/
    implementation(Room.runtime)
    implementation(Room.ktx)
    kapt(Room.annotationProcessor)

    /* ====================================================
    *                      Dagger
    * ===================================================*/
    implementation(Dagger.dagger)
    kapt(Dagger.daggerKapt)

    /* ====================================================
    *                      Utils
    * ===================================================*/
    implementation(Gson.gson)

}