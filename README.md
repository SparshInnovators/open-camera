# open-camera

import dependency

**Step 1** : Add it in your root build.gradle at the end of repositories:
```
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
  
 **Step 2** : Add the dependency
  ```
  dependencies {
	        implementation 'com.github.SparshInnovators:open-camera:1.3'
	}
```

**Step 3** : Starting the Camera

```
OpenCamera.begin(Context, this, Permission Status)
```
Permission Status - It is the status code that will be received in the **``` onActivityResult() ```**
And then in your Activity you can get the result in onActivityResult().

**Step 4** : Handle the Callback in your activity.

**Java**
```
protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    if (requestCode == Permission Status) {
    	  Bitmap image = (Bitmap) data.getExtras().get("data");
         //Your have got the result here
    }
}
```
**Kotlin**

```
 protected fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        if (requestCode == Permission Status) {
            val image = data.extras!!["data"] as Bitmap?
            //Your have got the result here
        }
    }
```
    
