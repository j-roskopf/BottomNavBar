# BottomNavBar

A bottom bar with an expanding effect.

* Written in Kotlin with :heart:
* Min SDK 21
* Customizable
* Supports unlimited amount of tabs


![gif](https://github.com/j-roskopf/BottomNavBar/blob/master/github/lib.gif?raw=true)

# Dependencies

    build.gradle

    allprojects {
    	repositories {
    		...
    		maven { url 'https://jitpack.io' }
    	}
    }

    app/build.gradle

	dependencies {
	    implementation 'com.github.j-roskopf:BottomNavBar:1.0'
	}

# Code Example

    <joetr.com.bottomnavbar.BottomNavBar android:id="@+id/bottomBar"
                                     android:layout_width="match_parent"
                                     android:layout_height="wrap_content">

        <joetr.com.bottomnavbar.BottomNavBarIcon android:layout_width="wrap_content"
                                                 android:id="@+id/homeContainer"
                                                 app:navIcon="@drawable/ic_home_black_24dp"
                                                 app:navText="Home"
                                                 app:navBackgroundTint="@color/homeColorTint"
                                                 app:navForegroundTint="@color/homeText"
                                                 android:layout_height="wrap_content"
                                                 app:selected="false"/>

     </joetr.com.bottomnavbar.BottomNavBar>

# Attributes

| Attribute             	| Explanation                         	|
|-----------------------	|--------------------------------------	|
| app:navIcon           	| Icon to display                      	|
| app:navText           	| Text to display                      	|
| app:navBackgroundTint 	| Background color of tab              	|
| app:navForegroundTint 	| Foreground color of tab (Text color) 	|