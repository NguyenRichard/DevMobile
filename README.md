# DevMobile

android{
...
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies{
...

    androidTestImplementation "org.jetbrains.kotlin:kotlin-stdlib-jdk7:$kotlin_version"
    androidTestImplementation 'androidx.test.ext:junit:1.1.0'
    androidTestImplementation 'androidx.test:core:1.1.0'
    androidTestImplementation 'androidx.test.ext:junit-ktx:1.1.0'
    androidTestImplementation 'androidx.test.espresso:espresso-core:3.1.1'
    androidTestImplementation 'androidx.test:runner:1.1.0'
}
