# FloatingShortcutButton_Android

Android library that show a floating button in your app screens you can drag and drop it in any location of the screen and on presse in it navigate to any location in the screen.

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

It's very simple type this code in your application class.

it takes instance of your application class 

`public class DemoApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        
      FloatingShortcutButtonController floatingShortcut = new FloatingShortcutButtonController(this);
      
      ....rest of configurations....
      
      }`

but here your activity that button start to appear in your application 

`floatingShortcutButtonController.setStartActivityName(LoginActivity.class);`

And but here the activity appear when press in the button 

`floatingShortcutButtonController.setHelpActivityName(HomeActivity.class);`

button icon and the background color 

`floatingShortcutButtonController.setButtonIcon(R.drawable.default_user , Color.WHITE);`

