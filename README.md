# Assertive Touch in Android

Assertive Touch is a library that lets you create a floating button which shows in your app screens. You can drag and drop it anywhere in your application and you can configure it on runtime to navigate through your predefined shortcuts. 

**Installing**
 
 Add it in your root build.gradle at the end of repositories:
 
 	allprojects {
 		repositories {
 			...
 			maven { url 'https://jitpack.io' }
 		}
 	}
 	
 Step 2. Add the dependency
 
 	dependencies {
 		compile 'com.github.mmoamenn:FloatingShortcutButton_Android:1.0.1'
 	}

**How to use in your application**

Add the following few lines in your application class.
```java
public class DemoApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        
        FSButton.setup(this) ;
        FSButton.getInstance().setTargetClass(HelpActivity.class);
        FSButton.getInstance().setIcon(R.drawable.help);
        FSButton.getInstance().setBackgroundColor(Color.WHITE);
      }
      ```
 
 To start showing the floating button through the application, use the following line.
 
 `FSButton.getInstance().show();`
 
 To make the floating button disppear through the application, use the following line.
 
  `FSButton.getInstance().hide();`

