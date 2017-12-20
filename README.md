
# Assertive Touch in Android

Assertive Touch is a library that lets you create a floating button which shows in your app screens. You can drag and drop it anywhere in your application and you can configure it on runtime to navigate through your predefined shortcuts. 

**Sample**
 
 ![Floating Shortcut button](https://github.com/mmoamenn/FloatingShortcutButton_Android/blob/master/samples/sample.gif)

**Installing**
 
 Add it in your root build.gradle at the end of repositories:
 
 ```groovy
 	allprojects {
 		repositories {
 			...
 			maven { url 'https://jitpack.io' }
 		}
 	}
  ```
 	
 Step 2. Add the dependency
 
 ```groovy
 	dependencies {
 		compile 'com.github.mmoamenn:Assertive-Touch:1.2.0'
 	}
 ```
**How to use in your application**

Add the following few lines in your application class.

```java
public class DemoApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        
        ATButton.setup(this) ;
        ATButton.getInstance().setTargetClass(HelpActivity.class);
        ATButton.getInstance().setIcon(R.drawable.help);
        ATButton.getInstance().setBackgroundColor(Color.WHITE);
      }
 ```
 
 To start showing the floating button through the application, use the following line.
 
 ```java
 ATButton.getInstance().show();
```
 
 To make the floating button disppear through the application, use the following line.
 
  ```java
  ATButton.getInstance().hide();
 ```
 
Change Button size 

```java
  ATButton.getInstance().setSize(height,width);
 ```
 
Check button visibility
 
  ```java
  ATButton.getInstance().isViewAttached();
 ```



