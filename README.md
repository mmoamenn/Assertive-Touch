# FloatingShortcutButton_Android

Android library shows a floating button in the app screen, you can drag and drop the button in any place of the screen and on press it navigate to any place in the app.

**Sample**
 
 ![Floating Shortcut button](https://github.com/mmoamenn/FloatingShortcutButton_Android/blob/master/samples/floating_example.gif)
 
 
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


    @Override
    public void onCreate() {
        super.onCreate();
        
      FloatingShortcutButtonController floatingShortcut = new FloatingShortcutButtonController(this);
      
      ....The rest of configurations....
      
      }

but here your activity that button start to appear in your application 

    floatingShortcut.setStartActivityName(LoginActivity.class);

And but here the activity appear when press in the button 

    floatingShortcut.setHelpActivityName(HomeActivity.class);

button icon and the background color 

    floatingShortcut.setButtonIcon(R.drawable.default_user , Color.WHITE);

